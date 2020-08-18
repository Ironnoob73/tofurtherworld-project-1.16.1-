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
    /*@Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) { stateManager.add(Properties.HORIZONTAL_FACING); }
    //public BlockState getPlacementState(ItemPlacementContext ctx) { return (BlockState)this.getDefaultState().with(FACING, ctx.getPlayerFacing()); }
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        return VoxelShapes.cuboid(0f, 0.5f, 0f, 1f, 1f, 1);
    }*/
}
