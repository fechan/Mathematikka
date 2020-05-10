package io.github.fechan.mathematikka;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Event called when a Mathematica query has completed
 */
public abstract class MathematicaQueryCompletedEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private Player initator;

    /**
     * Constructs the event with the Mathematica result
     * @param result the query result
     * @param initiator (Nullable) the player who initiated the query
     */
    public MathematicaQueryCompletedEvent(Player initiator) {
        super(true);
        this.initator = initiator;
    }

    /**
     * Get the player who initiated the query
     * @return (Nullable) the initator of the query
     */
    public Player getInitator() {
        return initator;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}