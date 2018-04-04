package fr.rremis.image.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.net.URL;

import javax.imageio.ImageIO;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

public class ImageRenderer extends MapRenderer {
	 
    // So fancy.
    private SoftReference<BufferedImage> cacheImage;
    private boolean hasRendered = false;
 
    public ImageRenderer(String url, int size) throws IOException {
        this.cacheImage = new SoftReference<>(this.getImage(url, size));
    }
 
    @SuppressWarnings("deprecation")
    @Override
    public void render(MapView view, MapCanvas canvas, Player player){
        if(this.hasRendered){
            return;
        }
 
        if(this.cacheImage.get() != null){
            canvas.drawImage(0, 0, this.cacheImage.get());
            this.hasRendered = true;
        } else {
            player.sendMessage(ChatColor.RED + "Error to get image's url !");
            Util.log(ChatColor.RED + "While rendering map ID #" + view.getId() + ", the cacheImage was null.");
            this.hasRendered = true;
        }
    }
 
    public BufferedImage getImage(String url, int size) throws IOException{
        boolean useCache = ImageIO.getUseCache();
 
        // Temporarily disable cache, if it isn't already,
        // so we can get the latest image.
        ImageIO.setUseCache(false);
 
        BufferedImage image = ImageIO.read(new URL(url));
        Util.resizeImage(image, size);
 
        // Renable it with the old value.
        ImageIO.setUseCache(useCache);
 
        return image;
    }
 
}
