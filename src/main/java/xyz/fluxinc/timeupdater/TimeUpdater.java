package xyz.fluxinc.timeupdater;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class TimeUpdater extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        long time = this.getConfig().getLong(event.getPlayer().getUniqueId().toString());
        if (time > 0) {
            this.getServer().getLogger().info("Adding " + time + " to " + event.getPlayer().getName());
            String command = "cmi editplaytime " + event.getPlayer().getName() + " set " + time;
            Bukkit.dispatchCommand(this.getServer().getConsoleSender(), command);
            this.getConfig().set(event.getPlayer().getUniqueId().toString(), null);
        }
    }
}
