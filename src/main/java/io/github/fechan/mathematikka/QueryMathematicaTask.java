package io.github.fechan.mathematikka;

import com.wolfram.jlink.KernelLink;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Queries Mathematica with an expression to evaluate and calls MathematicaQueryCompletedEvent when
 * it is done
 */
public class QueryMathematicaTask extends BukkitRunnable{
    private KernelLink mathematica;
    private String query;
    private Player initiator;

    /**
     * Constructs a Mathematica query task, given a mathematica kernel and the query to evaluate 
     * @param mathematica kernel that will evaluate the query
     * @param query query to evaluate
     */
    public QueryMathematicaTask(KernelLink mathematica, String query) {
        this.mathematica = mathematica;
        this.query = query;
    }

    /**
     * Constructs a Mathematica query task, given a kernel, query, and the player who initiated it
     * @param mathematica kernel that will evaluate the query
     * @param query query to evaluate
     * @param initiator player who initiated the query
     */
    public QueryMathematicaTask(KernelLink mathematica, String query, Player initiator) {
        this(mathematica, query);
        this.initiator = initiator;
    }

    /**
     * Queries Mathematica with the query and calls MathematicaQueryCompletedEvent when it's done
     */
    @Override
    public void run() {
        String result = mathematica.evaluateToOutputForm(query, 0);
        Bukkit.getPluginManager().callEvent(new MathematicaQueryCompletedEvent(result, initiator));
    }
}