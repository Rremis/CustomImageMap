package fr.rremis.image.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.rremis.image.CustomImageMap;
import fr.rremis.image.utils.Util;

public class MapCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) return false;

		Player p = (Player) sender;
		if(Util.havePerm(p)){
			if(args.length == 1){
				
				if(args[0].equalsIgnoreCase("version")){
					p.sendMessage(Util.getPrefix()+ChatColor.YELLOW+"Version: "+CustomImageMap.getInstance().getDescription().getVersion());
					return true;
				}
				
				CustomImageMap.createMap(p, args[0], true, 128);
				
			} else if(args.length == 2){
				int size = 128;
				try  {  
					size = Integer.parseInt(args[1]);
				} catch(NumberFormatException nfe)  {  
					p.sendMessage(Util.getPrefix()+Util.NO_INTEGER);
				   	return true;
				} 

				CustomImageMap.createMap(p, args[0], true, size);

			} else {
				p.sendMessage(Util.getPrefix()+Util.HELP_MESSAGE);
			}
		} else {
			p.sendMessage(Util.getPrefix()+Util.NO_PERM);
		}

		return true;
	}

}
