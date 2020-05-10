package io.github.fechan.mathematikka;

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
     */
    public MathematicaReturnedImageEvent(byte[] result, Player initiator) {
        super(initiator);
        this.result = result;
    }
    
    /**
     * Gets the Mathematica query result
     * @return the result of the query
     */
    public byte[] getResult() {
        return result;
    }
}