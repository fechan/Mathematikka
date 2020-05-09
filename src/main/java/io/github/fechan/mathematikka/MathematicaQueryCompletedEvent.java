package io.github.fechan.mathematikka;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Event called when a Mathematica query has completed
 */
public class MathematicaQueryCompletedEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    private String result;

    /**
     * Constructs the event with the Mathematica result
     * @param result
     */
    public MathematicaQueryCompletedEvent(String result) {
        super(true);
        this.result = result;
    }

    /**
     * Gets the Mathematica result
     * @return Mathematica result
     */
    public String getResult() {
        return this.result;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}