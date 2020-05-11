package io.github.fechan.mathematikka;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Event called when a Mathematica query has completed
 */
public abstract class MathematicaQueryCompletedEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private Player initator;
    private Location location;

    /**
     * Constructs the event with the Mathematica result
     * @param result the query result
     * @param initiator (Nullable) the player who initiated the query
     * @param location (Nullable) where the query was initiated
     */
    public MathematicaQueryCompletedEvent(Player initiator, Location location) {
        super(true);
        this.initator = initiator;
        this.location = location;
    }

    /**
     * @return (Nullable) the initator of the Mathematica query
     */
    public Player getInitator() {
        return initator;
    }

    /**
     * @return (Nullable) the location where the Mathematica query was initated
     */
    public Location getLocation() {
        return location;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}