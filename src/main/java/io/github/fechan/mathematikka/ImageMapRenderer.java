package io.github.fechan.mathematikka;

import java.awt.image.BufferedImage;

import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

public class ImageMapRenderer extends MapRenderer{
    private BufferedImage image;

    public ImageMapRenderer(BufferedImage image) {
        this.image = image;
    }

    public void render(MapView map, MapCanvas canvas, Player player) {
        canvas.drawImage(0, 0, image);
    }
}
