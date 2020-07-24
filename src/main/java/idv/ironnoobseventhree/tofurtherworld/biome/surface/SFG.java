package idv.ironnoobseventhree.tofurtherworld.biome.surface;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

public abstract class SFG extends SurfaceBuilder {
    public static final BlockState SANDSTONE;
    public static final TernarySurfaceConfig POORSAND_CONFIG;
    static {
        SANDSTONE = Blocks.SANDSTONE.getDefaultState();
        POORSAND_CONFIG = new TernarySurfaceConfig(SANDSTONE, SANDSTONE, SAND);
    }

    public SFG(Codec codec) {
        super(codec);
    }
}
