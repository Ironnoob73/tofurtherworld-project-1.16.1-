package idv.ironnoobseventhree.tofurtherworld.block.machine;

import idv.ironnoobseventhree.tofurtherworld.Core;
import idv.ironnoobseventhree.tofurtherworld.recipe.ForgingL1Recipe;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.slot.Slot;
import net.minecraft.world.World;

import java.util.List;

public class GrinderScreenHandler extends GrinderB {
    private final World world;
    private ForgingL1Recipe recipe;
    private final List<ForgingL1Recipe> recipeList;

    public GrinderScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, ScreenHandlerContext.EMPTY);
    }
    public GrinderScreenHandler(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(Core.GrinderScreen, syncId, playerInventory, context);
        this.world = playerInventory.player.world;
        this.recipeList = this.world.getRecipeManager().method_30027(Core.ForgingL1Type);
        this.addSlot(new Slot(this.input, 0, 48, 32));
        this.addSlot(new Slot(this.output, 1, 112, 14){public boolean canInsert(ItemStack stack) { return false; }public boolean canTakeItems(PlayerEntity playerEntity) { return GrinderScreenHandler.this.canTakeOutput(playerEntity, this.hasStack()); }public ItemStack onTakeItem(PlayerEntity player, ItemStack stack) { return GrinderScreenHandler.this.onTakeOutput(player, stack); }});
        this.addSlot(new Slot(this.output, 2, 112, 32){public boolean canInsert(ItemStack stack) { return false; }public boolean canTakeItems(PlayerEntity playerEntity) { return GrinderScreenHandler.this.canTakeOutput(playerEntity, this.hasStack()); }public ItemStack onTakeItem(PlayerEntity player, ItemStack stack) { return GrinderScreenHandler.this.onTakeOutput(player, stack); }});
        this.addSlot(new Slot(this.output, 3, 112, 50){public boolean canInsert(ItemStack stack) { return false; }public boolean canTakeItems(PlayerEntity playerEntity) { return GrinderScreenHandler.this.canTakeOutput(playerEntity, this.hasStack()); }public ItemStack onTakeItem(PlayerEntity player, ItemStack stack) { return GrinderScreenHandler.this.onTakeOutput(player, stack); }});

        int k;
        for(k = 0; k < 3; ++k) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + k * 9 + 9, 8 + j * 18, 84 + k * 18));
            }
        }

        for(k = 0; k < 9; ++k) {
            this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 142));
        }

    }
    protected boolean canUse(BlockState state) { return state.isOf(Core.Grinder); }

    protected boolean canTakeOutput(PlayerEntity player, boolean pnt) {
        return this.recipe != null && this.recipe.matches(this.input, this.world);
    }

    protected ItemStack onTakeOutput(PlayerEntity player, ItemStack stack) {
        this.method_29539(0);
        this.method_29539(1);
        this.method_29539(2);
        this.method_29539(3);
        this.context.run((world, blockPos) -> {
            world.syncWorldEvent(1044, blockPos, 0);
        });
        return stack;
    }

    private void method_29539(int i) {
        ItemStack itemStack = this.input.getStack(i);
        itemStack.decrement(1);
        this.input.setStack(i, itemStack);
    }

    public void updateResult() {
        List<ForgingL1Recipe> list = this.world.getRecipeManager().getAllMatches(Core.ForgingL1Type, this.input, this.world);
        if (list.isEmpty()) {
            this.output.setStack(0, ItemStack.EMPTY);
        } else {
            this.recipe = (ForgingL1Recipe)list.get(0);
            ItemStack itemStack = this.recipe.craft(this.input);
            this.output.setStack(0, itemStack);
        }

    }
    public ItemStack transferSlot(PlayerEntity player, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = (Slot)this.slots.get(index);
        if (slot != null && slot.hasStack()) {
            ItemStack itemStack2 = slot.getStack();
            itemStack = itemStack2.copy();
            if (index == 0) {
                this.context.run((world, blockPos) -> {
                    itemStack2.getItem().onCraft(itemStack2, world, player);
                });
                if (!this.insertItem(itemStack2, 10, 46, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onStackChanged(itemStack2, itemStack);
            } else if (index >= 10 && index < 46) {
                if (!this.insertItem(itemStack2, 0, 9, false)) {
                    if (index < 37) {
                        if (!this.insertItem(itemStack2, 37, 46, false)) {
                            return ItemStack.EMPTY;
                        }
                    } else if (!this.insertItem(itemStack2, 10, 37, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            } else if (!this.insertItem(itemStack2, 10, 46, false)) {
                return ItemStack.EMPTY;
            }

            if (itemStack2.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }

            if (itemStack2.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }

            ItemStack itemStack3 = slot.onTakeItem(player, itemStack2);
            if (index == 0) {
                player.dropItem(itemStack3, false);
            }
        }

        return itemStack;
    }

}
