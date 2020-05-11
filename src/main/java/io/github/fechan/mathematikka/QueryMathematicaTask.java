package io.github.fechan.mathematikka;

import com.wolfram.jlink.Expr;
import com.wolfram.jlink.KernelLink;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Queries Mathematica with an expression to evaluate and calls MathematicaQueryCompletedEvent when
 * it is done
 */
public class QueryMathematicaTask extends BukkitRunnable{
    private KernelLink mathematica;
    private Expr query;
    private Player initiator;
    private Location location;
    private String resultFormat;

    /**
     * Constructs a Mathematica query task, given a mathematica kernel and the query to evaluate 
     * @param mathematica kernel that will evaluate the query
     * @param query query to evaluate
     * @param resultFormat the format the query result should be in ("String" or "Image") 
     */
    public QueryMathematicaTask(KernelLink mathematica, Expr query, String resultFormat) {
        this.mathematica = mathematica;
        this.query = query;
        this.resultFormat = resultFormat;
    }
    
    /**
     * Constructs a Mathematica query task, given a kernel, query, and the player who initiated it
     * @param mathematica kernel that will evaluate the query
     * @param query query to evaluate
     * @param resultFormat the format the query result should be in ("String" or "Image") 
     * @param initiator player who initiated the query
     * @param location where the query was initated
     */
    public QueryMathematicaTask(KernelLink mathematica, Expr query, String resultFormat,
        Player initiator, Location location) {
        this(mathematica, query, resultFormat);
        this.initiator = initiator;
        this.location = location;
    }

    /**
     * Queries Mathematica with the query and calls a MathematicaQueryCompletedEvent when it's done
     */
    @Override
    public void run() {
        if (resultFormat.equals("String")) {
            String result = mathematica.evaluateToOutputForm(query, 0);
            Bukkit.getPluginManager()
                .callEvent(new MathematicaReturnedStringEvent(result, initiator, location));
        } else {
            byte[] result = mathematica.evaluateToImage(query, 0, 0);
            Bukkit.getPluginManager()
                .callEvent(new MathematicaReturnedImageEvent(result, initiator, location));
        }
    }
}