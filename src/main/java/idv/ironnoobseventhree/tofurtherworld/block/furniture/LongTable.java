package idv.ironnoobseventhree.tofurtherworld.block.furniture;

import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class LongTable extends HorizontalFacingBlock {
    public static final EnumProperty<LongTableEnum> PART;
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
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState newState, WorldAccess world, BlockPos pos, BlockPos posFrom) {
        if (direction == getDirectionTowardsOtherPart((LongTableEnum)state.get(PART), (Direction)state.get(FACING))) {
            return newState.isOf(this) && newState.get(PART) != state.get(PART) ? (BlockState)state/*.with(OCCUPIED, newState.get(OCCUPIED))*/ : Blocks.AIR.getDefaultState();
        } else {
            return super.getStateForNeighborUpdate(state, direction, newState, world, pos, posFrom);
        }
    }
    private static Direction getDirectionTowardsOtherPart(LongTableEnum part, Direction direction) {
        return part == LongTableEnum.MIDDLE ? direction : direction.rotateYClockwise();
    }
    @Override
    /*protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(Properties.HORIZONTAL_FACING);
    }*/
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, PART/*, OCCUPIED*/);
    }
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return (BlockState)this.getDefaultState().with(FACING, ctx.getPlayerFacing());
    }
    //onBreak和onPlaced函数亟需优化
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!world.isClient && player.isCreative()) {
            Direction direction = (Direction)state.get(FACING);
            LongTableEnum tablePart = (LongTableEnum)state.get(PART);
            if (tablePart == LongTableEnum.MIDDLE) {
                    BlockPos LE = pos.east();
                    BlockState blockStateLE = world.getBlockState(LE);
                    if (blockStateLE.getBlock() == this && blockStateLE.get(PART) == LongTableEnum.LEFT) {
                        world.setBlockState(LE, Blocks.AIR.getDefaultState(), 35);
                        world.syncWorldEvent(player, 2001, LE, Block.getRawIdFromState(blockStateLE));
                    }
                    BlockPos RW = pos.west();
                    BlockState blockStateRW = world.getBlockState(RW);
                    if (blockStateRW.getBlock() == this && blockStateRW.get(PART) == LongTableEnum.RIGHT) {
                    world.setBlockState(RW, Blocks.AIR.getDefaultState(), 35);
                    world.syncWorldEvent(player, 2001, RW, Block.getRawIdFromState(blockStateRW));
                    }
                    BlockPos LS = pos.south();
                    BlockState blockStateLS = world.getBlockState(LS);
                    if (blockStateLS.getBlock() == this && blockStateLS.get(PART) == LongTableEnum.LEFT) {
                        world.setBlockState(LS, Blocks.AIR.getDefaultState(), 35);
                        world.syncWorldEvent(player, 2001, LS, Block.getRawIdFromState(blockStateLS));
                    }
                    BlockPos RN = pos.north();
                    BlockState blockStateRN = world.getBlockState(RN);
                    if (blockStateRN.getBlock() == this && blockStateRN.get(PART) == LongTableEnum.RIGHT) {
                        world.setBlockState(RN, Blocks.AIR.getDefaultState(), 35);
                        world.syncWorldEvent(player, 2001, RN, Block.getRawIdFromState(blockStateRN));
                    }

            }
            if (tablePart == LongTableEnum.LEFT) {
                BlockPos MW = pos.west();
                BlockState blockStateMW = world.getBlockState(MW);
                if (blockStateMW.getBlock() == this && blockStateMW.get(PART) == LongTableEnum.MIDDLE) {
                    world.setBlockState(MW, Blocks.AIR.getDefaultState(), 35);
                    world.syncWorldEvent(player, 2001, MW, Block.getRawIdFromState(blockStateMW));
                }
                BlockPos MN = pos.north();
                BlockState blockStateMN = world.getBlockState(MN);
                if (blockStateMN.getBlock() == this && blockStateMN.get(PART) == LongTableEnum.MIDDLE) {
                    world.setBlockState(MN, Blocks.AIR.getDefaultState(), 35);
                    world.syncWorldEvent(player, 2001, MN, Block.getRawIdFromState(blockStateMN));
                }
                BlockPos ME = pos.east();
                BlockState blockStateME = world.getBlockState(ME);
                if (blockStateME.getBlock() == this && blockStateME.get(PART) == LongTableEnum.MIDDLE) {
                    world.setBlockState(ME, Blocks.AIR.getDefaultState(), 35);
                    world.syncWorldEvent(player, 2001, ME, Block.getRawIdFromState(blockStateME));
                }
                BlockPos MS = pos.south();
                BlockState blockStateMS = world.getBlockState(MS);
                if (blockStateMS.getBlock() == this && blockStateMS.get(PART) == LongTableEnum.MIDDLE) {
                    world.setBlockState(MS, Blocks.AIR.getDefaultState(), 35);
                    world.syncWorldEvent(player, 2001, MS, Block.getRawIdFromState(blockStateMS));
                }
            }
            if (tablePart == LongTableEnum.RIGHT) {
                BlockPos MW = pos.west();
                BlockState blockStateMW = world.getBlockState(MW);
                if (blockStateMW.getBlock() == this && blockStateMW.get(PART) == LongTableEnum.MIDDLE) {
                    world.setBlockState(MW, Blocks.AIR.getDefaultState(), 35);
                    world.syncWorldEvent(player, 2001, MW, Block.getRawIdFromState(blockStateMW));
                }
                BlockPos MN = pos.north();
                BlockState blockStateMN = world.getBlockState(MN);
                if (blockStateMN.getBlock() == this && blockStateMN.get(PART) == LongTableEnum.MIDDLE) {
                    world.setBlockState(MN, Blocks.AIR.getDefaultState(), 35);
                    world.syncWorldEvent(player, 2001, MN, Block.getRawIdFromState(blockStateMN));
                }
                BlockPos ME = pos.east();
                BlockState blockStateME = world.getBlockState(ME);
                if (blockStateME.getBlock() == this && blockStateME.get(PART) == LongTableEnum.MIDDLE) {
                    world.setBlockState(ME, Blocks.AIR.getDefaultState(), 35);
                    world.syncWorldEvent(player, 2001, ME, Block.getRawIdFromState(blockStateME));
                }
                BlockPos MS = pos.south();
                BlockState blockStateMS = world.getBlockState(MS);
                if (blockStateMS.getBlock() == this && blockStateMS.get(PART) == LongTableEnum.MIDDLE) {
                    world.setBlockState(MS, Blocks.AIR.getDefaultState(), 35);
                    world.syncWorldEvent(player, 2001, MS, Block.getRawIdFromState(blockStateMS));
                }
            }
        }

        super.onBreak(world, pos, state, player);
    }

    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        Direction direction = (Direction)state.get(FACING);
        super.onPlaced(world, pos, state, placer, itemStack);
        if (!world.isClient) {
            if(direction.getAxis() == Direction.Axis.Z) {
                BlockPos L = pos.east();
                world.setBlockState(L, (BlockState) state.with(PART, LongTableEnum.LEFT), 3);
                world.updateNeighbors(pos, Blocks.AIR);
                state.method_30101(world, pos, 3);
                BlockPos R = pos.west();
                world.setBlockState(R, (BlockState) state.with(PART, LongTableEnum.RIGHT), 3);
                world.updateNeighbors(pos, Blocks.AIR);
                state.method_30101(world, pos, 3);
            }else if(direction.getAxis() == Direction.Axis.X) {
                BlockPos L = pos.south();
                world.setBlockState(L, (BlockState) state.with(PART, LongTableEnum.LEFT), 3);
                world.updateNeighbors(pos, Blocks.AIR);
                state.method_30101(world, pos, 3);
                BlockPos R = pos.north();
                world.setBlockState(R, (BlockState) state.with(PART, LongTableEnum.RIGHT), 3);
                world.updateNeighbors(pos, Blocks.AIR);
                state.method_30101(world, pos, 3);
            }
        }

    }

    /*public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        world.setBlockState(pos.offset((Direction)state.get(FACING)), (BlockState)state.with(PART, LongTableEnum.LEFT), 3);
    }*/
    static {
        PART = FurniturePart.TablePart;
        //OCCUPIED = Properties.OCCUPIED;
    }
}
