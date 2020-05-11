package io.github.fechan.mathematikka;

import java.awt.image.BufferedImage;

import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

public class ImageMapRenderer extends MapRenderer{
    private BufferedImage image;

    /**
     * Constructs a map renderer that renders an image
     * @param image image to render
     */
    public ImageMapRenderer(BufferedImage image) {
        this.image = image;
    }

    /**
     * Renders the image on the map
     */
    public void render(MapView map, MapCanvas canvas, Player player) {
        canvas.drawImage(0, 0, image);
    }
}
