package net.rocketeer.callisto;

import net.rocketeer.callisto.terrain.IcyMoonGenerator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

public class CallistoPlugin extends JavaPlugin
{
  @Override
  public ChunkGenerator getDefaultWorldGenerator(String worldName, String id)
  {
    return new IcyMoonGenerator();
  }

  @Override
  public void onEnable()
  {

  }
}
