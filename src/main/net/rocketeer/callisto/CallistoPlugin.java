package net.rocketeer.callisto;

import net.rocketeer.callisto.terrain.IcyMoonGenerator;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.LinkedList;
import java.util.List;

public class CallistoPlugin extends JavaPlugin
{
  public static volatile CallistoPlugin instance;
  private int _rainingTaskId;

  @Override
  public ChunkGenerator getDefaultWorldGenerator(String worldName, String id)
  {
    return new IcyMoonGenerator();
  }

  @Override
  public void onEnable()
  {
    this.saveDefaultConfig();
    instance = this;

    Bukkit.getLogger().info("Enabling Callisto...");
    Bukkit.getLogger().info("Loading worlds...");
    List<String> worldNames = this.getConfig().getStringList("world-names");
    List<World> worlds = new LinkedList<>();
    worldNames.forEach(worldName -> worlds.add(Bukkit.getWorld(worldName)));

    this._rainingTaskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> worlds.forEach(world -> world.setStorm(true)), 0, 6000);
  }

  @Override
  public void onDisable()
  {
    Bukkit.getLogger().info("Disabling Callisto...");
    Bukkit.getScheduler().cancelTask(this._rainingTaskId);
  }
}
