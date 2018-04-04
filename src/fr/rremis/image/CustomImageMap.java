package fr.rremis.image;

import java.io.IOException;
import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
import org.bukkit.plugin.java.JavaPlugin;

import fr.rremis.image.commands.CommandManager;
import fr.rremis.image.utils.ImageRenderer;
import fr.rremis.image.utils.Util;

public class CustomImageMap extends JavaPlugin {

	@Override
	public void onEnable(){
		instance = this;
		
		CommandManager.registerCommand(this);
		Util.log("CustomImageMap ON");
	}
	
	@Override
	public void onDisable(){
		Util.log("CustomImageMap OFF");
	}
	
	private static CustomImageMap instance;
	public static CustomImageMap getInstance(){
		return instance;
	}
	
	@SuppressWarnings("deprecation")
	public static void createMap(Player p, String url, boolean messages, int size){
		ItemStack objet = new ItemStack(Material.MAP);
		MapView view = Bukkit.createMap(p.getWorld());
        Iterator<MapRenderer> iter = view.getRenderers().iterator();
        while(iter.hasNext()){
            view.removeRenderer(iter.next());
        }
        
        try {
            ImageRenderer renderer = new ImageRenderer(url, size);
            view.addRenderer(renderer);
            if(messages)
            	p.sendMessage(Util.getPrefix()+Util.GOOD+ChatColor.GRAY+" ("+url+")");
            
        } catch (IOException e){
        	if(messages)
        		 p.sendMessage(Util.getPrefix()+Util.GOOD);
        	
            e.printStackTrace();
        }

		ItemMeta meta = objet.getItemMeta();
		meta.setDisplayName(ChatColor.GOLD+"Custom Map "+ChatColor.DARK_GRAY+"#"+view.getId());
		
		objet.setDurability(view.getId());
		objet.setItemMeta(meta);
		
		p.setItemInHand(objet);
		p.updateInventory();
	}
	
	@SuppressWarnings("deprecation")
	public static void createMap(Player p, String url, boolean messages, String customName, int size){
		ItemStack objet = new ItemStack(Material.MAP);
		MapView view = Bukkit.createMap(p.getWorld());
        Iterator<MapRenderer> iter = view.getRenderers().iterator();
        while(iter.hasNext()){
            view.removeRenderer(iter.next());
        }
        
        try {
            ImageRenderer renderer = new ImageRenderer(url, size);
            view.addRenderer(renderer);
            if(messages)
            	p.sendMessage(Util.getPrefix()+Util.GOOD+ChatColor.GRAY+" ("+url+")");
            
        } catch (IOException e){
        	if(messages)
        		 p.sendMessage(Util.getPrefix()+Util.GOOD);
        	
            e.printStackTrace();
        }

		ItemMeta meta = objet.getItemMeta();
		meta.setDisplayName(customName);
		
		objet.setDurability(view.getId());
		objet.setItemMeta(meta);
		
		p.setItemInHand(objet);
		p.updateInventory();
	}

}
