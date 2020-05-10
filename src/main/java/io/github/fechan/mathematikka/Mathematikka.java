package io.github.fechan.mathematikka;

import java.util.logging.Logger;

import com.wolfram.jlink.*;

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

    @Override
    public void onDisable() {
        if (mathematica != null) {
            mathematica.close();
        }
    }

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
                query = "WolframAlpha[\"" + query + "\", \"ShortAnswer\"]";
                new QueryMathematicaTask(this.mathematica, query, event.getThrower())
                    .runTaskAsynchronously(this);
            }
        }
    }

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
