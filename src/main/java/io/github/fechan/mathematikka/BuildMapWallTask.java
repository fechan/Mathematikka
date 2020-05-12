package io.github.fechan.mathematikka;

import java.awt.image.BufferedImage;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemFrame;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Builds a map wall from a 2D array of image tiles
 */
public class BuildMapWallTask extends BukkitRunnable {
    private Location bottomNorthWestCorner;
    private World world;
    private BufferedImage[][] tiles;

    /**
     * Constructs a wall builder task that builds a map wall showing an image
     * @param bottomNorthWestCorner the location of the bottom northwest corner to start building
     * @param tiles the 2D array of image tiles, arranged [x][y], with (x=0,y=0) at the top right
     */
    public BuildMapWallTask(Location bottomNorthWestCorner, BufferedImage[][] tiles) {
        this.bottomNorthWestCorner = bottomNorthWestCorner;
        this.world = bottomNorthWestCorner.getWorld();
        this.tiles = tiles;
    }

    /**
     * Builds a wall of item frames with maps that show the provided image tiles
     */
    @Override
    public void run() {
        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[0].length; y++) {
                Location backboardLocation = bottomNorthWestCorner.clone().add(x, y, 0);
                this.world.getBlockAt(backboardLocation).setType(Material.OAK_PLANKS);
                ItemFrame frame = (ItemFrame) this.world.spawnEntity(
                    backboardLocation.add(0, 0, 1), EntityType.ITEM_FRAME
                    );
                frame.setItem(createMap(this.tiles[x][y]));
            }
        }
    }

    /**
     * Creates a map
     * @return a map
     */
    private ItemStack createMap(BufferedImage image) {
        ItemStack item = new ItemStack(Material.FILLED_MAP);

        MapView map = Bukkit.getServer().createMap(this.world);
        
        for (MapRenderer renderer : map.getRenderers()) {
            map.removeRenderer(renderer);
        }
        map.addRenderer(new ImageMapRenderer(image));

        MapMeta meta = ((MapMeta) item.getItemMeta());
        meta.setMapView(map);
        item.setItemMeta(meta);

        return item;
    }
}