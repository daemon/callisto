package net.rocketeer.callisto.terrain;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.NoiseGenerator;
import org.bukkit.util.noise.SimplexNoiseGenerator;

import java.util.Random;

public class IcyMoonGenerator extends ChunkGenerator
{
  private NoiseGenerator _generator;

  private NoiseGenerator setGenerator(World world)
  {
    return this._generator = new SimplexNoiseGenerator(world);
  }

  @Override
  public ChunkData generateChunkData(World world, Random random, int cx, int cz, BiomeGrid grid)
  {
    if (this._generator == null)
      this.setGenerator(world);
    ChunkData chunk = this.createChunkData(world);

    for (int x = 0; x < 16; ++x)
      for (int z = 0; z < 16; ++z)
      {
        grid.setBiome(x, z, Biome.ICE_PLAINS);
        int height = (int) this._generator.noise(cx + x * 0.0625, cz + z * 0.0625, 3, 0.025, 1.5);
        for (int y = 0; y < 50 + height; ++y)
          chunk.setBlock(x, y, z, Material.STONE);
        chunk.setBlock(x, 0, z, Material.BEDROCK);
        chunk.setBlock(x, 50 + height, z, Material.SNOW);
      }

    return chunk;
  }
}
