package io.github.fechan.mathematikka;

import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * Event called when Mathematica returns an image result, which is GIF data in a byte[]
 */
public class MathematicaReturnedImageEvent extends MathematicaQueryCompletedEvent {
    private byte[] result;

    /**
     * Constructs the event with the Mathematica result and the player who initiated the query
     * @param result result of the Mathematica query
     * @param initiator who initiated the query
     * @param location where the query was initiated
     */
    public MathematicaReturnedImageEvent(byte[] result, Player initiator, Location location) {
        super(initiator, location);
        this.result = result;
    }
    
    /**
     * @return the result of the Mathematica query
     */
    public byte[] getResult() {
        return result;
    }
}