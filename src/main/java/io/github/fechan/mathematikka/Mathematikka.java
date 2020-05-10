package io.github.fechan.mathematikka;

import java.util.logging.Logger;

import com.wolfram.jlink.Expr;
import com.wolfram.jlink.KernelLink;
import com.wolfram.jlink.MathLinkException;
import com.wolfram.jlink.MathLinkFactory;

import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class Mathematikka extends JavaPlugin implements Listener {
    KernelLink mathematica;
    Logger console;

    /**
     * Called when the plugin is enabled. Creates a Mathematica kernel link and tests it.
     * It disables the plugin if it can't establish the link.
     */
    @Override
    public void onEnable() {
        console = getLogger();
        getServer().getPluginManager().registerEvents(this, this);

        try {
            mathematica = MathLinkFactory.createKernelLink("-linkmode launch -linkname 'math -mathlink'");
        } catch (UnsatisfiedLinkError|MathLinkException e) {
            console.severe("Failed to create link to Mathematica Kernel! " + e.getMessage());
            console.info("Disabling Mathematikka plugin...");
            getPluginLoader().disablePlugin(this);
            return;
        }

        try {
            mathematica.discardAnswer();
            mathematica.evaluate("2+2");
            mathematica.waitForAnswer();
            int result = mathematica.getInteger();
            console.info("2 + 2 = " + result);
            console.info("If you see '2 + 2 = 4' that means it's working");
        } catch (MathLinkException e) {
            console.warning("Failed to get response from Mathematica Kernel! " + e.getMessage());
        }
    }

    /**
     * Called when the plugin is disabled. Closes the Mathematica kernel link if it exists.
     */
    @Override
    public void onDisable() {
        if (mathematica != null) {
            mathematica.close();
        }
    }

    /**
     * When the player drops an item and it's a written book titled "WolframAlpha," it will query
     * WolframAlpha with its contents when it hits the ground
     * @param event the event triggering this
     */
    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        Item item = event.getItemDrop();
        ItemStack stack = item.getItemStack();
        if (stack.getType() == Material.WRITTEN_BOOK) {
            BookMeta book = (BookMeta) stack.getItemMeta();
            if (book.getTitle().equals("WolframAlpha")) {
                new CheckItemOnGroundTask(this, item, event.getPlayer()).runTaskTimer(this, 0, 1);
            }
        }
    }

    /**
     * When an item that is tracked with the CheckItemOnGroundTask is on the ground, this queries
     * WolframAlpha with the book's contents if it's titled "WolframAlpha"
     * @param event the event triggering this
     */
    @EventHandler
    public void onTrackedItemOnGround(ItemOnGroundEvent event) {
        ItemStack stack = event.getItem().getItemStack();
        if (stack.getType() == Material.WRITTEN_BOOK) {
            BookMeta book = (BookMeta) stack.getItemMeta();
            if (book.getTitle().equals("WolframAlpha")) {
                String query = "";
                for (int i = 1; i <= book.getPageCount(); i++) {
                    query += book.getPage(i);
                }
                // equivalent to a type-safe version of WolframAlpha[query, "ShortAnswer"]
                Expr expr = new Expr(
                    new Expr(Expr.SYMBOL, "WolframAlpha"),
                    new Expr[]{
                        new Expr(Expr.STRING, query), new Expr("ShortAnswer")
                    });
                new QueryMathematicaTask(this.mathematica, expr, event.getThrower())
                    .runTaskAsynchronously(this);
            }
        }
    }

    /**
     * When a Mathematica query completes, if the query was initiated by a player, tells them the
     * query result. Otherwise it gets piped to the server console.
     * @param event
     */
    @EventHandler
    public void onMathematicaQueryComplete(MathematicaQueryCompletedEvent event) {
        Player initiator = event.getInitator();
        if (initiator != null) {
            event.getInitator().sendMessage(event.getResult());
        } else {
            console.info(event.getResult());
        }
    }
}
