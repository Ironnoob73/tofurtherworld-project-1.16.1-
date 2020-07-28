package idv.ironnoobseventhree.tofurtherworld.block.furniture;

import net.minecraft.block.*;
import net.minecraft.block.enums.BedPart;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class LongTable extends HorizontalFacingBlock {
    //public static final EnumProperty<LongTableEnum> PART;
    //public static final BooleanProperty OCCUPIED;
    public LongTable(Settings settings) {
        super(settings);
        setDefaultState(this.stateManager.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH));
    }
    public static Direction getDirection(BlockView world, BlockPos pos) {
        BlockState blockState = world.getBlockState(pos);
        return blockState.getBlock() instanceof BedBlock ? (Direction)blockState.get(FACING) : null;
    }
    public VoxelShape getOutlineShape(BlockState state, BlockView view, BlockPos pos, ShapeContext context) {
        //Direction direction = (Direction)state.get(FACING);
        //if (direction.getAxis() == Direction.Axis.Z) {
        //return VoxelShapes.cuboid(-1f, 0f, 0f, 2f, 1f, 1);}
        return VoxelShapes.cuboid(0f, 0f, 0f, 1f, 1f, 1);
    }
    /*public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState newState, WorldAccess world, BlockPos pos, BlockPos posFrom) {
        if (direction == getDirectionTowardsOtherPart((LongTableEnum)state.get(PART), (Direction)state.get(FACING))) {
            return newState.isOf(this) && newState.get(PART) != state.get(PART) ? (BlockState)state.with(OCCUPIED, newState.get(OCCUPIED)) : Blocks.AIR.getDefaultState();
        } else {
            return super.getStateForNeighborUpdate(state, direction, newState, world, pos, posFrom);
        }
    }
    private static Direction getDirectionTowardsOtherPart(LongTableEnum part, Direction direction) {
        return part == LongTableEnum.MIDDLE ? direction : direction.getOpposite();
    }*/
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(Properties.HORIZONTAL_FACING);
    }
    /*protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, PART, OCCUPIED);
    }*/
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return (BlockState)this.getDefaultState().with(FACING, ctx.getPlayerFacing());
    }
    /*public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!world.isClient && player.isCreative()) {
            LongTableEnum tablePart = (LongTableEnum)state.get(PART);
            if (tablePart == LongTableEnum.MIDDLE) {
                BlockPos blockPos = pos.offset(getDirectionTowardsOtherPart(tablePart, (Direction)state.get(FACING)));
                BlockState blockState = world.getBlockState(blockPos);
                if (blockState.getBlock() == this && blockState.get(PART) == LongTableEnum.LEFT) {
                    world.setBlockState(blockPos, Blocks.AIR.getDefaultState(), 35);
                    world.syncWorldEvent(player, 2001, blockPos, Block.getRawIdFromState(blockState));
                }
            }
        }

        super.onBreak(world, pos, state, player);
    }*/

    /*public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        if (!world.isClient) {
            BlockPos blockPos = pos.offset((Direction)state.get(FACING));
            world.setBlockState(blockPos, (BlockState)state.with(PART, LongTableEnum.LEFT), 3);
            world.updateNeighbors(pos, Blocks.AIR);
            state.method_30101(world, pos, 3);
        }

    }*/

    /*public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        world.setBlockState(pos.offset((Direction)state.get(FACING)), (BlockState)state.with(PART, LongTableEnum.LEFT), 3);
    }
    static {
        PART = FurniturePart.TablePart;
        OCCUPIED = Properties.OCCUPIED;
    }*/
}
