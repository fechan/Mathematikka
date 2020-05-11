package io.github.fechan.mathematikka;

import org.bukkit.Bukkit;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Checks if a certain given item is on the ground or not
 * Calls ItemOnGroundEvent and cancels itself when the item is on the ground
 * Cancels without raising an event if the item despawned
 */
public class CheckItemOnGroundTask extends BukkitRunnable implements Listener {
    private final JavaPlugin plugin;
    private Item item;
    private Player thrower;

    /**
     * Constructs the task with a given item to check for
     * @param plugin Bukkit plugin object
     * @param item item to check if it's on the ground
     */
    public CheckItemOnGroundTask(JavaPlugin plugin, Item item) {
        this.plugin = plugin;
        this.item = item;
        Bukkit.getPluginManager().registerEvents(this, this.plugin);
    }

    /**
     * Constructs the task with a given item to check for
     * @param plugin Bukkit plugin object
     * @param item item to check if it's on the ground
     * @param thrower player who threw the item
     */
    public CheckItemOnGroundTask(JavaPlugin plugin, Item item, Player thrower) {
        this(plugin, item);
        this.thrower = thrower;
    }

    /**
     * Runs the task
     */
    @Override
    public void run() {
        if (this.item.isOnGround()) {
            ItemOnGroundEvent event = new ItemOnGroundEvent(this.item, this.item.getLocation(),
                this.thrower);
            Bukkit.getServer().getPluginManager().callEvent(event);
            this.cancel();
        }
    }

    /**
     * Cancels the task if the item that despawned is this task's tracked item
     * @param event item despawn event
     */
    @EventHandler
    public void onItemDespawn(ItemDespawnEvent event) {
        if (event.getEntity() == this.item) {
            Bukkit.getLogger().info("CheckItemOnGroundTask cancelled! (Item despawned)");
            this.cancel();
        }
    }
}