package io.github.fechan.mathematikka;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Event called when a Mathematica query has completed
 */
public class MathematicaQueryCompletedEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private String result;
    private Player initator;

    /**
     * Constructs the event with the Mathematica result
     * @param result the query result
     * @param initiator (Nullable) the player who initiated the query
     */
    public MathematicaQueryCompletedEvent(String result, Player initiator) {
        super(true);
        this.result = result;
        this.initator = initiator;
    }

    /**
     * Gets the Mathematica result
     * @return Mathematica result
     */
    public String getResult() {
        return this.result;
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