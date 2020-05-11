package io.github.fechan.mathematikka;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Builds a map wall from an image TODO: this might change
 */
public class BuildMapWallTask extends BukkitRunnable {
    private Location bottomSouthWestCorner;
    private World world;

    public BuildMapWallTask(Location bottomSouthWestCorner) {
        this.bottomSouthWestCorner = bottomSouthWestCorner;
        this.world = bottomSouthWestCorner.getWorld();
    }

    @Override
    public void run() {
        this.world.getBlockAt(bottomSouthWestCorner).setType(Material.DIAMOND_BLOCK);

        ItemFrame frame = (ItemFrame) this.world.spawnEntity(bottomSouthWestCorner.add(1, 0, 0), EntityType.ITEM_FRAME);
        frame.setItem(createMap(this.bottomSouthWestCorner));
        
    }

    private ItemStack createMap(Location location) {
        Server server = Bukkit.getServer();
        ItemStack item = new ItemStack(Material.MAP);

        MapView map = server.createMap(location.getWorld());

        MapMeta meta = ((MapMeta) item.getItemMeta());
        meta.setMapView(map);
        item.setItemMeta(meta);
        
        return item;
    }
}