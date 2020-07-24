package idv.ironnoobseventhree.tofurtherworld.block.sapling;

import java.util.Random;

import net.minecraft.block.*;
import net.minecraft.block.AbstractBlock.Settings;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class SaplingMain extends SaplingBlock {
    public SaplingMain(SaplingGenerator generator,Settings settings) {
        super(generator,settings);
    }
}
