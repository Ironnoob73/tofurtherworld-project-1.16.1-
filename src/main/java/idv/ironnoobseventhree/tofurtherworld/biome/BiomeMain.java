package idv.ironnoobseventhree.tofurtherworld.biome;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;

public class BiomeMain {
    public static final Biome PoorDesert = Registry.register(Registry.BIOME, new Identifier("tofurtherworld", "poor_desert"), new PoorDesert());
}
