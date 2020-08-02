package idv.ironnoobseventhree.tofurtherworld.block.forging;

import idv.ironnoobseventhree.tofurtherworld.Core;
import idv.ironnoobseventhree.tofurtherworld.recipe.ForgingL1Recipe;
import idv.ironnoobseventhree.tofurtherworld.recipe.Recipes;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.*;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.slot.Slot;
import net.minecraft.world.World;

import java.util.List;
public class ForgingTableL1ScreenHandler extends ForgingNotForge {
    /*private final ForgingTableL1Inventory input;
    private final CraftingResultInventory result;
    private final ScreenHandlerContext context;
    private final PlayerEntity player;

    public ForgingTableL1ScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, ScreenHandlerContext.EMPTY);
    }

    public ForgingTableL1ScreenHandler(int syncId, PlayerInventory playerInventory,final ScreenHandlerContext context) {
        super(ScreenHandlerType.SMITHING, syncId);
        this.input = new ForgingTableL1Inventory(this, 3, 3);
        this.input = new SimpleInventory(1) {
            public void markDirty() {
                super.markDirty();
                ForgingTableL1ScreenHandler.this.onContentChanged(this);
                ForgingTableL1ScreenHandler.this.contentsChangedListener.run();
            }
        };
        this.result = new CraftingResultInventory();
        this.context = context;
        this.player = playerInventory.player;
        this.addSlot(new CraftingResultSlot(playerInventory.player, this.input, this.result, 0, 124, 35));

        int m;
        int l;
        for(m = 0; m < 3; ++m) {
            for(l = 0; l < 3; ++l) {
                this.addSlot(new Slot(this.input, l + m * 3, 30 + l * 18, 17 + m * 18));
            }
        }

        for(m = 0; m < 3; ++m) {
            for(l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + m * 9 + 9, 8 + l * 18, 84 + m * 18));
            }
        }

        for(m = 0; m < 9; ++m) {
            this.addSlot(new Slot(playerInventory, m, 8 + m * 18, 142));
        }

    }

    protected static void updateResult(int syncId, World world, PlayerEntity player, ForgingTableL1Inventory craftingInventory, CraftingResultInventory resultInventory) {
        if (!world.isClient) {
            ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)player;
            ItemStack itemStack = ItemStack.EMPTY;
            Optional<ForgingL1Recipe> optional = world.getServer().getRecipeManager().getFirstMatch(Recipes.ForgingL1, new ForgingTableL1Inventory(new ItemStack[]{itemStack2}),craftingInventory, world);
            if (optional.isPresent()) {
                ForgingL1Recipe craftingRecipe = (ForgingL1Recipe)optional.get();
                if (resultInventory.shouldCraftRecipe(world, serverPlayerEntity, craftingRecipe)) {
                    itemStack = craftingRecipe.craft(craftingInventory);
                }
            }
            ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)player;
            ItemStack itemStack = ItemStack.EMPTY;
            Optional<CraftingRecipe> optional = world.getServer().getRecipeManager().getFirstMatch(RecipeType.CRAFTING, new ForgingTableL1Inventory(new ItemStack[]{itemStack2}),craftingInventory, world);
            if (optional.isPresent()) {
                CraftingRecipe craftingRecipe = (CraftingRecipe)optional.get();
                if (resultInventory.shouldCraftRecipe(world, serverPlayerEntity, craftingRecipe)) {
                    itemStack = craftingRecipe.craft(craftingInventory);
                }
            }

            resultInventory.setStack(0, itemStack);
            serverPlayerEntity.networkHandler.sendPacket(new ScreenHandlerSlotUpdateS2CPacket(syncId, 0, itemStack));
        }
    }

    public void onContentChanged(Inventory inventory) {
        this.context.run((world, blockPos) -> {
            updateResult(this.syncId, world, this.player, this.input, this.result);
        });
    }

    public void populateRecipeFinder(RecipeFinder finder) {
        this.input.provideRecipeInputs(finder);
    }

    public void clearCraftingSlots() {
        this.input.clear();
        this.result.clear();
    }

    public boolean matches(Recipe<? super ForgingTableL1Inventory> recipe) {
        return recipe.matches(this.input, this.player.world);
    }

    public void close(PlayerEntity player) {
        super.close(player);
        this.context.run((world, blockPos) -> {
            this.dropInventory(player, world, this.input);
        });
    }

    public boolean canUse(PlayerEntity player) {return canUse(this.context, player, Core.ForgingTableL1);}

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
                if (!this.insertItem(itemStack2, 1, 10, false)) {
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

    public boolean canInsertIntoSlot(ItemStack stack, Slot slot) {
        return slot.inventory != this.result && super.canInsertIntoSlot(stack, slot);
    }

    public int getCraftingResultSlotIndex() {
        return 0;
    }

    public int getCraftingWidth() {
        return this.input.getWidth();
    }

    public int getCraftingHeight() {
        return this.input.getHeight();
    }

    @Environment(EnvType.CLIENT)
    public int getCraftingSlotCount() {
        return 10;
    }*/
    private final World world;
    private ForgingL1Recipe recipe;
    private final List<ForgingL1Recipe> recipeList;

    public ForgingTableL1ScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, ScreenHandlerContext.EMPTY);
    }
    public ForgingTableL1ScreenHandler(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(Core.ForgingTableL1Screen, syncId, playerInventory, context);
        this.world = playerInventory.player.world;
        this.recipeList = this.world.getRecipeManager().method_30027(Recipes.ForgingL1);
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
        List<ForgingL1Recipe> list = this.world.getRecipeManager().getAllMatches(Recipes.ForgingL1, this.input, this.world);
        if (list.isEmpty()) {
            this.output.setStack(0, ItemStack.EMPTY);
        } else {
            this.recipe = (ForgingL1Recipe)list.get(0);
            ItemStack itemStack = this.recipe.craft(this.input);
            this.output.setStack(0, itemStack);
        }

    }

    protected boolean method_30025(ItemStack itemStack) {
        return this.recipeList.stream().anyMatch((forgingL1Recipe) -> forgingL1Recipe.method_30029(itemStack));
    }
    public ItemStack transferSlot(PlayerEntity player, int index) {
        ItemStack itemStack = super.transferSlot(player, index);
        System.out.println(itemStack);
        return ItemStack.EMPTY;
    }
}
