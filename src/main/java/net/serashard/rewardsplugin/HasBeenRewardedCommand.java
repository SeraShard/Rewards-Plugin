package net.serashard.rewardsplugin;

import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jspecify.annotations.NonNull;

import java.util.Collection;

public class ResetRewardsCommand implements BasicCommand {

    RewardsPlugin plugin;

    public ResetRewardsCommand(RewardsPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void execute(final @NonNull CommandSourceStack source, final String[] args) {
        if(args.length!=1) {
            source.getSender().sendRichMessage("<red>Unknown or incomplete command.");
            return;
        }
        String name = args[0];
        plugin.getConfig().set(name + ".awarded",false);
        plugin.saveConfig();
    }

    @Override
    public String permission() {
        return "rewardsplugin.resetrewards";
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
