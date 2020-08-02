package idv.ironnoobseventhree.tofurtherworld.block.forging;

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
public class ForgingTableL1ScreenHandler extends ForgingNotForge {
    private final World world;
    private ForgingL1Recipe recipe;
    private final List<ForgingL1Recipe> recipeList;

    public ForgingTableL1ScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, ScreenHandlerContext.EMPTY);
    }
    public ForgingTableL1ScreenHandler(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(Core.ForgingTableL1Screen, syncId, playerInventory, context);
        this.world = playerInventory.player.world;
        this.recipeList = this.world.getRecipeManager().method_30027(Core.ForgingL1Type);
        this.addSlot(new Slot(this.input, 0, 30, 17));
        this.addSlot(new Slot(this.input, 1, 48, 17));
        this.addSlot(new Slot(this.input, 2, 66, 17));
        this.addSlot(new Slot(this.input, 3, 30, 35));
        this.addSlot(new Slot(this.input, 4, 48, 35));
        this.addSlot(new Slot(this.input, 5, 66, 35));
        this.addSlot(new Slot(this.input, 6, 30, 53));
        this.addSlot(new Slot(this.input, 7, 48, 53));
        this.addSlot(new Slot(this.input, 8, 66, 53));
        this.addSlot(new Slot(this.output, 9, 124, 35) {
            public boolean canInsert(ItemStack stack) {
                return false;
            }

            public boolean canTakeItems(PlayerEntity playerEntity) {
                return ForgingTableL1ScreenHandler.this.canTakeOutput(playerEntity, this.hasStack());
            }

            public ItemStack onTakeItem(PlayerEntity player, ItemStack stack) {
                return ForgingTableL1ScreenHandler.this.onTakeOutput(player, stack);
            }
        });

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
    protected boolean canUse(BlockState state) { return state.isOf(Core.ForgingTableL1); }

    protected boolean canTakeOutput(PlayerEntity player, boolean pnt) {
        return this.recipe != null && this.recipe.matches(this.input, this.world);
    }

    protected ItemStack onTakeOutput(PlayerEntity player, ItemStack stack) {
        this.method_29539(0);
        this.method_29539(1);
        this.method_29539(2);
        this.method_29539(3);
        this.method_29539(4);
        this.method_29539(5);
        this.method_29539(6);
        this.method_29539(7);
        this.method_29539(8);
        this.method_29539(9);
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

    /*protected boolean method_30025(ItemStack itemStack) {
        return this.recipeList.stream().anyMatch((forgingL1Recipe) -> forgingL1Recipe.method_30029(itemStack));
    }*/
    public ItemStack transferSlot(PlayerEntity player, int index) {
        ItemStack itemStack = super.transferSlot(player, index);
        System.out.println(itemStack);
        return ItemStack.EMPTY;
    }
}
