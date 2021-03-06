package com.stefthedev.villages.resources.commands.subcommands;

import com.stefthedev.villages.utilities.general.Chat;
import com.stefthedev.villages.utilities.general.Command;
import com.stefthedev.villages.utilities.general.Message;
import com.stefthedev.villages.data.village.Village;
import com.stefthedev.villages.managers.VillageManager;
import com.stefthedev.villages.data.village.VillageMember;
import com.stefthedev.villages.data.village.VillagePermission;
import org.bukkit.entity.Player;

public class SetHomeCommand extends Command {

    private final VillageManager villageManager;

    public SetHomeCommand(VillageManager villageManager) {
        super("sethome", "sethome");
        this.villageManager = villageManager;
    }

    @Override
    public boolean run(Player player, String[] args) {
        Village village = villageManager.getVillage(player);
        if(village != null) {
            VillageMember villageMember = village.getMember(player.getUniqueId());
            if(villageMember.hasPermission(VillagePermission.SET_HOME) || village.getOwner().equals(player.getUniqueId()) || village.hasPermission(VillagePermission.SET_HOME)) {
                village.setLocation(player.getLocation());
                player.sendMessage(Chat.format(Message.VILLAGE_SET_HOME.toString()));
            } else {
                player.sendMessage(Chat.format(Message.NO_PERMISSION.toString().replace("{0}", VillagePermission.SET_HOME.name() )));
            }
        } else {
            player.sendMessage(Chat.format(Message.VILLAGE_NULL.toString()));
        }
        return false;
    }
}
