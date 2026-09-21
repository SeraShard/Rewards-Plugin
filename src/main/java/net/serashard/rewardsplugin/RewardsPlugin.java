package net.serashard.rewardsplugin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class RewardsPlugin extends JavaPlugin implements Listener {
  
  @Override
  public void onEnable() {
    saveDefaultConfig();
    Bukkit.getPluginManager().registerEvents(this, this);
  }

  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    Player player = event.getPlayer();
    String name = player.getName();
    boolean val = getConfig().getBoolean(name + ".awarded",true);
    if (!val) {
      int num = getConfig().getInt(name + ".awards");
      for (int i = 0; i < num; i++) {
        player.getInventory().addItem(new ItemStack(Material.DIAMOND));
      }
      getConfig().set(name + ".awarded", true);
      saveConfig();

    }
  }
}


