package io.github.fechan.mathematikka;

import com.wolfram.jlink.KernelLink;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Queries Mathematica with an expression to evaluate and calls MathematicaQueryCompletedEvent when
 * it is done
 */
public class QueryMathematicaTask extends BukkitRunnable{
    private KernelLink mathematica;
    private String query;

    public QueryMathematicaTask(KernelLink mathematica, String query) {
        this.mathematica = mathematica;
        this.query = query;
    }

    @Override
    public void run() {
        String result = mathematica.evaluateToOutputForm(query, 0);
        Bukkit.getPluginManager().callEvent(new MathematicaQueryCompletedEvent(result));
    }
}