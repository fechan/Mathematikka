package io.github.fechan.mathematikka;

import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * Event called when Mathematica returns a String result
 */
public class MathematicaReturnedStringEvent extends MathematicaQueryCompletedEvent {
    private String result;

    /**
     * Constructs the event with the Mathematica result and the player who initiated the query
     * @param result result of the Mathematica query
     * @param initiator who initiated the query
     * @param location where the query was initiated
     */
    public MathematicaReturnedStringEvent(String result, Player initiator, Location location) {
        super(initiator, location);
        this.result = result;
    }
    
    /**
     * Gets the Mathematica query result
     * @return the result of the query
     */
    public String getResult() {
        return result;
    }
}