package idv.ironnoobseventhree.tofurtherworld.block.sapling;

import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import idv.ironnoobseventhree.tofurtherworld.biome.FeaturesHC;
import java.util.Random;

public class IceBirch extends SaplingGenerator {
    public IceBirch() {
    }

    @Override
    protected ConfiguredFeature<TreeFeatureConfig, ?> createTreeFeature(Random random, boolean bl) {
        return Feature.TREE.configure(bl ? FeaturesHC.ICEBIRCH_TREE_CONFIG : FeaturesHC.ICEBIRCH_TREE_CONFIG);
    }
}
