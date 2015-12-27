package net.rocketeer.callisto.terrain;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;

import java.util.Random;

public class CraterPopulator extends BlockPopulator
{
  public static final int CHUNK_SPAWN_CHANCE = 2;

  @Override
  public void populate(World world, Random random, Chunk chunk)
  {
    if (random.nextInt(100) > CHUNK_SPAWN_CHANCE)
      return;

    // Follows N(16, 9) distribution
    int radius = (int) (3 * random.nextGaussian() + 16);
    if (radius < 4 || radius > 40)
      return;

    int depth = random.nextInt(radius / 2);
    int blockX = chunk.getX() * 16;
    int blockZ = chunk.getZ() * 16;
    int blockY = world.getHighestBlockAt(blockX, blockZ).getY();
    int centerY = blockY + radius / 4 + depth;
    Location center = new Location(world, blockX, centerY, blockZ);

    for (int i = -radius; i < radius; ++i)
      for (int j = -radius; j < radius; ++j)
        for (int k = 0; k < 60; ++k)
        {
          Block block = world.getBlockAt(blockX + i, k, blockZ + j);
          double distance = block.getLocation().distance(center);
          if (block.getType().equals(Material.AIR) || distance > radius)
            continue;

          if (distance < radius - 1 || (Math.abs(j) < radius / 2 && Math.abs(i) < radius / 2) ||
              random.nextInt(10) > 1)
          {
            block.setType(Material.AIR);
            if (distance > radius - 0.5)
              block.setType(Material.SNOW);
          }

        }
  }
}
