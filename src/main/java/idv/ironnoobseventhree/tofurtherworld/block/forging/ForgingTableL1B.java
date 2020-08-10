package idv.ironnoobseventhree.tofurtherworld.block.forging;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;

public abstract class ForgingTableL1B extends ScreenHandler {
    protected final Inventory output = new CraftingResultInventory();
    protected final Inventory input = new SimpleInventory(10) {//这里还存在上限问题，预计会合并ForgingTableL1ScreenHandler
        public void markDirty() {
            super.markDirty();
            ForgingTableL1B.this.onContentChanged(this);
        }
    };
    protected final ScreenHandlerContext context;
    protected final PlayerEntity player;

    protected abstract boolean canTakeOutput(PlayerEntity player, boolean present);

    protected abstract ItemStack onTakeOutput(PlayerEntity player, ItemStack stack);

    protected abstract boolean canUse(BlockState state);

    public ForgingTableL1B(ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(type, syncId);
        this.context = context;
        this.player = playerInventory.player;
    }

    public abstract void updateResult();

    public void onContentChanged(Inventory inventory) {
        super.onContentChanged(inventory);
        if (inventory == this.input) {
            this.updateResult();
        }

    }

    public void close(PlayerEntity player) {
        super.close(player);
        this.context.run((world, blockPos) -> {
            this.dropInventory(player, world, this.input);
        });
    }

    public boolean canUse(PlayerEntity player) {
        return (Boolean)this.context.run((world, blockPos) -> {
            return !this.canUse(world.getBlockState(blockPos)) ? false : player.squaredDistanceTo((double)blockPos.getX() + 0.5D, (double)blockPos.getY() + 0.5D, (double)blockPos.getZ() + 0.5D) <= 64.0D;
        }, true);
    }

    protected boolean method_30025(ItemStack itemStack) {
        return false;
    }
}
