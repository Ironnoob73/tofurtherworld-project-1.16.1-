package idv.ironnoobseventhree.tofurtherworld.biome;

import idv.ironnoobseventhree.tofurtherworld.Core;
import net.minecraft.block.BlockState;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

public class FeaturesHC{
    public static final TreeFeatureConfig ICEBIRCH_TREE_CONFIG;
    private static final BlockState ICEBIRCH_LOG;
    private static final BlockState ICEBIRCH_LEAVES;
    static {
        ICEBIRCH_LOG = Core.IceBirchLog.getDefaultState();
        ICEBIRCH_LEAVES = Core.IceBirchLeaves.getDefaultState();
        ICEBIRCH_TREE_CONFIG = (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(ICEBIRCH_LOG), new SimpleBlockStateProvider(ICEBIRCH_LEAVES), new BlobFoliagePlacer(2, 0, 0, 0, 3), new StraightTrunkPlacer(5, 2, 0), new TwoLayersFeatureSize(1, 0, 1))).ignoreVines().build();
    }
}
