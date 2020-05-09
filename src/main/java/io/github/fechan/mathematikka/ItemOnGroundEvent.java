package io.github.fechan.mathematikka;

import org.bukkit.entity.Item;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * An event called when an item is on the ground
 */
public class ItemOnGroundEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private Item item;

    /**
     * Constructs the event with the item on the ground
     */
    public ItemOnGroundEvent(Item item) {
        this.item = item;
    }

    /**
     * Gets the item on the ground
     * @return item on the ground
     */
    public Item getItem() {
        return item;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}