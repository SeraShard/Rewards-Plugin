package net.serashard.rewardsplugin;

import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jspecify.annotations.NonNull;

import java.util.Collection;

public class HasBeenRewardedCommand implements BasicCommand {

    RewardsPlugin plugin;

    public HasBeenRewardedCommand(RewardsPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(final @NonNull CommandSourceStack source, final String[] args) {
        if(args.length!=2) {
            source.getSender().sendRichMessage("<red>Unknown or incomplete command.");
            return;
        }
        String name = args[0];
        boolean value = Boolean.parseBoolean(args[1]);
        plugin.getConfig().set(name + ".awarded",value);
        plugin.saveConfig();
    }

    @Override
    public String permission() {
        return "rewardsplugin.hasbeenrewarded";
    }

    @Override
    public @NonNull Collection<String> suggest(final @NonNull CommandSourceStack source, final String[] args) {
        if(args.length==0) {
            return Bukkit.getOnlinePlayers().stream().map(Player::getName).toList();
        }
        return Bukkit.getOnlinePlayers().stream()
                .map(Player::getName)
                .filter(name -> name.toLowerCase().startsWith(args[args.length-1].toLowerCase()))
                .toList();

    }

}
