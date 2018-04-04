package fr.rremis.image.utils;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Util {
	
	public static String getPrefix(){
		return ChatColor.DARK_GREEN+"CustomImageMap"+ChatColor.DARK_GRAY+"» "+ChatColor.GRAY;
	}
	
	public static void log(String message){
		System.out.println("[CiM] "+message);
	}
	
	public static void resizeImage(BufferedImage image, int size){
		Graphics2D resizer = image.createGraphics();
		// ??? Article said that it "increased image quality". Read: rip heap
		resizer.setComposite(AlphaComposite.Src);
		resizer.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		resizer.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		resizer.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		resizer.drawImage(image, 0, 0, size, size, null);
		resizer.dispose();
	}
	
	public static boolean havePerm(Player p){
		return p.isOp() || p.hasPermission("cimap.create");
	}
	
	public static String HELP_MESSAGE = ChatColor.RED + "Error, try /map <URL> !";
	public static String NO_PERM = ChatColor.RED + "Error, you don't have the permission !";
	public static String NO_INTEGER = ChatColor.RED + "Error, you don't set a number for sizing !";
	public static String NO_URL = ChatColor.RED+"Error, incorrect URL";
	public static String GOOD = ChatColor.AQUA+"Map created !";

}
