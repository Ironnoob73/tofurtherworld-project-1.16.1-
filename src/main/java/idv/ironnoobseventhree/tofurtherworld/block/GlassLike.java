package idv.ironnoobseventhree.tofurtherworld.block;

import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class GlassLike extends TransparentBlock {
    public GlassLike(Settings settings) {
        super(settings);
    }
    @Override
    public boolean isTranslucent(BlockState state, BlockView view, BlockPos pos)
    {
        return true;
    }
}
