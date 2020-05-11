package io.github.fechan.mathematikka;

import org.bukkit.Location;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * An event called when an item is on the ground
 */
public class ItemOnGroundEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private Item item;
    private Location location;
    private Player thrower;

    /**
     * Constructs the event with the item that's on the ground and the thrower of the item, if any
     * @param item item that hit the ground
     * @param location location of the item
     * @param initiator (Nullable) player who threw the item
     */
    public ItemOnGroundEvent(Item item, Location location, Player thrower) {
        this.item = item;
        this.location = location;
        this.thrower = thrower;
    }

    /**
     * Gets the item on the ground
     * @return item on the ground
     */
    public Item getItem() {
        return item;
    }

    /**
     * Gets the location of the item
     * @return the location of the item
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Gets the thrower of the item
     * @return (Nullable) the thrower if thrown by a player
     */
    public Player getThrower() {
        return thrower;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}