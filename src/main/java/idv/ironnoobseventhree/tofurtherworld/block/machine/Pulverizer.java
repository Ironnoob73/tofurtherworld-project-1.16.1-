package idv.ironnoobseventhree.tofurtherworld.block.machine;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class Pulverizer extends BlockWithEntity {
    public Pulverizer(Settings settings) {
        super(settings);
    }
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit){
        if (world.isClient) {
            return ActionResult.SUCCESS;
        } else {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof PulverizerE) {
                PulverizerE b1=(PulverizerE)blockEntity;
                b1.install(player);
            }
        }
        return ActionResult.CONSUME;
    }
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }
    @Override
    public PulverizerE createBlockEntity(BlockView blockView) {
        return new PulverizerE();
    }
}
