package idv.ironnoobseventhree.tofurtherworld.block.machine;

import com.google.common.collect.Lists;
import idv.ironnoobseventhree.tofurtherworld.Core;
import idv.ironnoobseventhree.tofurtherworld.recipe.GrinderR;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import it.unimi.dsi.fastutil.ints.IntListIterator;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.s2c.play.CraftFailedResponseS2CPacket;
import net.minecraft.network.packet.s2c.play.ScreenHandlerSlotUpdateS2CPacket;
import net.minecraft.recipe.*;
import net.minecraft.screen.*;
import net.minecraft.screen.slot.CraftingResultSlot;
import net.minecraft.screen.slot.FurnaceOutputSlot;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class GrinderScreenHandler extends GrinderSHB {
    public GrinderScreenHandler(int syncId, PlayerInventory playerInventory) {
        super(Core.GrinderScreen, Core.GrinderR, syncId, playerInventory);
    }

    public GrinderScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(Core.GrinderScreen, Core.GrinderR, syncId, playerInventory, inventory, propertyDelegate);
    }
}
abstract class GrinderSHB extends GrinderSHA<Inventory> {
    private final Inventory inventory;
    private final PropertyDelegate propertyDelegate;
    protected final World world;
    private final RecipeType<? extends GrinderR> recipeType;

    protected GrinderSHB(ScreenHandlerType<?> type, RecipeType<? extends GrinderR> recipeType, int syncId, PlayerInventory playerInventory) {
        this(type, recipeType, syncId, playerInventory, new SimpleInventory(5), new ArrayPropertyDelegate(4));
    }

    protected GrinderSHB(ScreenHandlerType<?> type, RecipeType<? extends GrinderR> recipeType, int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(type, syncId);
        this.recipeType = recipeType;
        checkSize(inventory, 5);
        checkDataCount(propertyDelegate, 4);
        this.inventory = inventory;
        this.propertyDelegate = propertyDelegate;
        this.world = playerInventory.player.world;
        this.addSlot(new Slot(inventory, 0, 45, 22));
        this.addSlot(new Slot(inventory, 3, 65, 22));
        this.addSlot(new GrinderHighLevelFuelSlot (this, inventory, 1, 55, 54));
        this.addSlot(new FurnaceOutputSlot(playerInventory.player, inventory, 2, 114, 27));
        this.addSlot(new FurnaceOutputSlot(playerInventory.player, inventory, 4, 114, 47));

        int k;
        for(k = 0; k < 3; ++k) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + k * 9 + 9, 8 + j * 18, 84 + k * 18));
            }
        }

        for(k = 0; k < 9; ++k) {
            this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 142));
        }

        this.addProperties(propertyDelegate);
    }

    public void populateRecipeFinder(RecipeFinder finder) {
        if (this.inventory instanceof RecipeInputProvider) {
            ((RecipeInputProvider)this.inventory).provideRecipeInputs(finder);
        }

    }

    public void clearCraftingSlots() {
        this.inventory.clear();
    }

    public void fillInputSlots(boolean craftAll, Recipe<?> recipe, ServerPlayerEntity player) {
        (new GrinderISF<>(this)).fillInputSlots(player, (Recipe<Inventory>) recipe, craftAll);
    }

    public boolean matches1(Recipe<? super Inventory> recipe) {
        return recipe.matches(this.inventory, this.world);
    }

    public int getCraftingResultSlotIndex() {
        return 2;
    }

    public int getCraftingWidth() {
        return 1;
    }

    public int getCraftingHeight() {
        return 1;
    }

    @Environment(EnvType.CLIENT)
    public int getCraftingSlotCount() {
        return 5;
    }

    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    public ItemStack transferSlot(PlayerEntity player, int index) {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = (Slot)this.slots.get(index);
        if (slot != null && slot.hasStack()) {
            ItemStack itemStack2 = slot.getStack();
            itemStack = itemStack2.copy();
            if (index == 2) {
                if (!this.insertItem(itemStack2, 3, 39, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onStackChanged(itemStack2, itemStack);
            } else if (index != 1 && index != 0) {
                if (this.isSmeltable(itemStack2)) {
                    if (!this.insertItem(itemStack2, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (this.isFuel(itemStack2)) {
                    if (!this.insertItem(itemStack2, 1, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= 3 && index < 30) {
                    if (!this.insertItem(itemStack2, 30, 39, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= 30 && index < 39 && !this.insertItem(itemStack2, 3, 30, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(itemStack2, 3, 39, false)) {
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

            slot.onTakeItem(player, itemStack2);
        }

        return itemStack;
    }

    protected boolean isSmeltable(ItemStack itemStack) {
        return this.world.getRecipeManager().getFirstMatch(this.recipeType, new SimpleInventory(new ItemStack[]{itemStack}), this.world).isPresent();
    }

    protected boolean isFuel(ItemStack itemStack) {
        return AbstractFurnaceBlockEntity.canUseAsFuel(itemStack);
    }

    @Environment(EnvType.CLIENT)
    public int getCookProgress() {
        int i = this.propertyDelegate.get(2);
        int j = this.propertyDelegate.get(3);
        return j != 0 && i != 0 ? i * 24 / j : 0;
    }

    @Environment(EnvType.CLIENT)
    public int getFuelProgress() {
        int i = this.propertyDelegate.get(1);
        if (i == 0) {
            i = 200;
        }

        return this.propertyDelegate.get(0) * 13 / i;
    }

    @Environment(EnvType.CLIENT)
    public boolean isBurning() {
        return this.propertyDelegate.get(0) > 0;
    }
}
class GrinderISFB<C extends Inventory> implements RecipeGridAligner<Integer> {
    protected static final Logger LOGGER = LogManager.getLogger();
    protected final RecipeFinder recipeFinder = new RecipeFinder();
    protected PlayerInventory inventory;
    protected GrinderSHA<C> craftingScreenHandler;

    public GrinderISFB(GrinderSHA<C> gsha) {
        this.craftingScreenHandler = gsha;
    }

    public void fillInputSlots(ServerPlayerEntity entity, Recipe<C> recipe, boolean craftAll) {
        if (recipe != null && entity.getRecipeBook().contains(recipe)) {
            this.inventory = entity.inventory;
            if (this.canReturnInputs() || entity.isCreative()) {
                this.recipeFinder.clear();
                entity.inventory.populateRecipeFinder(this.recipeFinder);
                this.craftingScreenHandler.populateRecipeFinder(this.recipeFinder);
                if (this.recipeFinder.findRecipe(recipe, (IntList)null)) {
                    this.fillInputSlots(recipe, craftAll);
                } else {
                    this.returnInputs();
                    entity.networkHandler.sendPacket(new CraftFailedResponseS2CPacket(entity.currentScreenHandler.syncId, recipe));
                }

                entity.inventory.markDirty();
            }
        }
    }

    protected void returnInputs() {
        for(int i = 0; i < this.craftingScreenHandler.getCraftingWidth() * this.craftingScreenHandler.getCraftingHeight() + 1; ++i) {
            if (i != this.craftingScreenHandler.getCraftingResultSlotIndex() || !(this.craftingScreenHandler instanceof GrinderCraftingSH) /*&& !(this.craftingScreenHandler instanceof RefinerPlayerSH)*/) {
                this.returnSlot(i);
            }
        }

        this.craftingScreenHandler.clearCraftingSlots();
    }
    protected void returnAdd() {
        for(int k = 3; k < this.craftingScreenHandler.getCraftingWidth() * this.craftingScreenHandler.getCraftingHeight() + 1; ++k) {
            if (k != this.craftingScreenHandler.getCraftingResultSlotIndex() || !(this.craftingScreenHandler instanceof GrinderCraftingSH) /*&& !(this.craftingScreenHandler instanceof RefinerPlayerSH)*/) {
                this.returnSlot(k);
            }
        }

        this.craftingScreenHandler.clearCraftingSlots();
    }

    protected void returnSlot(int i) {
        ItemStack itemStack = this.craftingScreenHandler.getSlot(i).getStack();
        if (!itemStack.isEmpty()) {
            for(; itemStack.getCount() > 0; this.craftingScreenHandler.getSlot(i).takeStack(1)) {
                int j = this.inventory.getOccupiedSlotWithRoomForStack(itemStack);
                if (j == -1) {
                    j = this.inventory.getEmptySlot();
                }

                ItemStack itemStack2 = itemStack.copy();
                itemStack2.setCount(1);
                if (!this.inventory.insertStack(j, itemStack2)) {
                    LOGGER.error("Can't find any space for item in the inventory");
                }
            }

        }
    }
    protected void returnSlot2(int k) {
        ItemStack itemStack = this.craftingScreenHandler.getSlot(k).getStack();
        if (!itemStack.isEmpty()) {
            for(; itemStack.getCount() > 0; this.craftingScreenHandler.getSlot(k).takeStack(1)) {
                int j = this.inventory.getOccupiedSlotWithRoomForStack(itemStack);
                if (j == -1) {
                    j = this.inventory.getEmptySlot();
                }

                ItemStack itemStack2 = itemStack.copy();
                itemStack2.setCount(1);
                if (!this.inventory.insertStack(j, itemStack2)) {
                    LOGGER.error("Can't find any space for item in the inventory");
                }
            }

        }
    }

    protected void fillInputSlots(Recipe<C> recipe, boolean craftAll) {
        boolean bl = this.craftingScreenHandler.matches1(recipe);
        int i = this.recipeFinder.countRecipeCrafts(recipe, (IntList)null);
        int j;
        if (bl) {
            for(j = 0; j < this.craftingScreenHandler.getCraftingHeight() * this.craftingScreenHandler.getCraftingWidth() + 1; ++j) {
                if (j != this.craftingScreenHandler.getCraftingResultSlotIndex()) {
                    ItemStack itemStack = this.craftingScreenHandler.getSlot(j).getStack();
                    if (!itemStack.isEmpty() && Math.min(i, itemStack.getMaxCount()) < itemStack.getCount() + 1) {
                        return;
                    }
                }
            }
        }

        j = this.getAmountToFill(craftAll, i, bl);
        IntList intList = new IntArrayList();
        if (this.recipeFinder.findRecipe(recipe, intList, j)) {
            int l = j;
            IntListIterator var8 = intList.iterator();

            while(var8.hasNext()) {
                int m = (Integer)var8.next();
                int n = RecipeFinder.getStackFromId(m).getMaxCount();
                if (n < l) {
                    l = n;
                }
            }

            if (this.recipeFinder.findRecipe(recipe, intList, l)) {
                this.returnInputs();
                this.alignRecipeToGrid(this.craftingScreenHandler.getCraftingWidth(), this.craftingScreenHandler.getCraftingHeight(), this.craftingScreenHandler.getCraftingResultSlotIndex(), recipe, intList.iterator(), l);
            }
        }

    }

    public void acceptAlignedInput(Iterator<Integer> inputs, int slot, int amount, int gridX, int gridY) {
        Slot slot2 = this.craftingScreenHandler.getSlot(slot);
        ItemStack itemStack = RecipeFinder.getStackFromId((Integer)inputs.next());
        if (!itemStack.isEmpty()) {
            for(int i = 0; i < amount; ++i) {
                this.fillInputSlot(slot2, itemStack);
            }
        }

    }

    protected int getAmountToFill(boolean craftAll, int limit, boolean recipeInCraftingSlots) {
        int i = 1;
        if (craftAll) {
            i = limit;
        } else if (recipeInCraftingSlots) {
            i = 64;

            for(int j = 0; j < this.craftingScreenHandler.getCraftingWidth() * this.craftingScreenHandler.getCraftingHeight() + 1; ++j) {
                if (j != this.craftingScreenHandler.getCraftingResultSlotIndex()) {
                    ItemStack itemStack = this.craftingScreenHandler.getSlot(j).getStack();
                    if (!itemStack.isEmpty() && i > itemStack.getCount()) {
                        i = itemStack.getCount();
                    }
                }
            }

            if (i < 64) {
                ++i;
            }
        }

        return i;
    }

    protected void fillInputSlot(Slot slot, ItemStack itemStack) {
        int i = this.inventory.method_7371(itemStack);
        if (i != -1) {
            ItemStack itemStack2 = this.inventory.getStack(i).copy();
            if (!itemStack2.isEmpty()) {
                if (itemStack2.getCount() > 1) {
                    this.inventory.removeStack(i, 1);
                } else {
                    this.inventory.removeStack(i);
                }

                itemStack2.setCount(1);
                if (slot.getStack().isEmpty()) {
                    slot.setStack(itemStack2);
                } else {
                    slot.getStack().increment(1);
                }

            }
        }
    }

    private boolean canReturnInputs() {
        List<ItemStack> list = Lists.newArrayList();
        int i = this.getFreeInventorySlots();

        for(int j = 0; j < this.craftingScreenHandler.getCraftingWidth() * this.craftingScreenHandler.getCraftingHeight() + 1; ++j) {
            if (j != this.craftingScreenHandler.getCraftingResultSlotIndex()) {
                ItemStack itemStack = this.craftingScreenHandler.getSlot(j).getStack().copy();
                if (!itemStack.isEmpty()) {
                    int k = this.inventory.getOccupiedSlotWithRoomForStack(itemStack);
                    if (k == -1 && list.size() <= i) {
                        Iterator var6 = list.iterator();

                        while(var6.hasNext()) {
                            ItemStack itemStack2 = (ItemStack)var6.next();
                            if (itemStack2.isItemEqualIgnoreDamage(itemStack) && itemStack2.getCount() != itemStack2.getMaxCount() && itemStack2.getCount() + itemStack.getCount() <= itemStack2.getMaxCount()) {
                                itemStack2.increment(itemStack.getCount());
                                itemStack.setCount(0);
                                break;
                            }
                        }

                        if (!itemStack.isEmpty()) {
                            if (list.size() >= i) {
                                return false;
                            }

                            list.add(itemStack);
                        }
                    } else if (k == -1) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private int getFreeInventorySlots() {
        int i = 0;
        Iterator var2 = this.inventory.main.iterator();

        while(var2.hasNext()) {
            ItemStack itemStack = (ItemStack)var2.next();
            if (itemStack.isEmpty()) {
                ++i;
            }
        }

        return i;
    }
}
abstract class GrinderSHA<C extends Inventory> extends ScreenHandler {
    public GrinderSHA(ScreenHandlerType<?> screenHandlerType, int i) {
        super(screenHandlerType, i);
    }

    public void fillInputSlots(boolean craftAll, Recipe<?> recipe, ServerPlayerEntity player) {
        (new GrinderISFB(this)).fillInputSlots(player, recipe, craftAll);
    }

    public abstract void populateRecipeFinder(RecipeFinder finder);

    public abstract void clearCraftingSlots();

    public abstract boolean matches1(Recipe<? super C> recipe);

    public abstract int getCraftingResultSlotIndex();

    public abstract int getCraftingWidth();

    public abstract int getCraftingHeight();

    @Environment(EnvType.CLIENT)
    public abstract int getCraftingSlotCount();
}

class GrinderISF<C extends Inventory> extends GrinderISFB<C> {
    private boolean slotMatchesRecipe;

    public GrinderISF(GrinderSHA<C> rsha) {
        super(rsha);
    }

    protected void fillInputSlots(Recipe<C> recipe, boolean craftAll) {
        this.slotMatchesRecipe = this.craftingScreenHandler.matches1(recipe);
        int i = this.recipeFinder.countRecipeCrafts(recipe, (IntList)null);
        if (this.slotMatchesRecipe) {
            ItemStack itemStack = this.craftingScreenHandler.getSlot(0).getStack();
            if (itemStack.isEmpty() || i <= itemStack.getCount()) {
                return;
            }
        }

        int j = this.getAmountToFill(craftAll, i, this.slotMatchesRecipe);
        IntList intList = new IntArrayList();
        if (this.recipeFinder.findRecipe(recipe, intList, j)) {
            if (!this.slotMatchesRecipe) {
                this.returnSlot(this.craftingScreenHandler.getCraftingResultSlotIndex());
                this.returnSlot(0);
            }

            this.fillInputSlot(j, intList);
        }
    }

    protected void returnInputs() {
        this.returnSlot(this.craftingScreenHandler.getCraftingResultSlotIndex());
        super.returnInputs();
    }

    protected void fillInputSlot(int limit, IntList inputs) {
        Iterator<Integer> iterator = inputs.iterator();
        Slot slot = this.craftingScreenHandler.getSlot(0);
        ItemStack itemStack = RecipeFinder.getStackFromId((Integer)iterator.next());
        if (!itemStack.isEmpty()) {
            int i = Math.min(itemStack.getMaxCount(), limit);
            if (this.slotMatchesRecipe) {
                i -= slot.getStack().getCount();
            }

            for(int j = 0; j < i; ++j) {
                this.fillInputSlot(slot, itemStack);
            }

        }
    }
}

class GrinderCraftingSH extends GrinderSHA<CraftingInventory> {
    private final CraftingInventory input;
    private final CraftingResultInventory result;
    private final ScreenHandlerContext context;
    private final PlayerEntity player;

    public GrinderCraftingSH(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, ScreenHandlerContext.EMPTY);
    }

    public GrinderCraftingSH(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(ScreenHandlerType.CRAFTING, syncId);
        this.input = new CraftingInventory(this, 3, 3);
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

    protected static void updateResult(int syncId, World world, PlayerEntity player, CraftingInventory craftingInventory, CraftingResultInventory resultInventory) {
        if (!world.isClient) {
            ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)player;
            ItemStack itemStack = ItemStack.EMPTY;
            Optional<CraftingRecipe> optional = world.getServer().getRecipeManager().getFirstMatch(RecipeType.CRAFTING, craftingInventory, world);
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

    public boolean matches1(Recipe<? super CraftingInventory> recipe) {
        return recipe.matches(this.input, this.player.world);
    }

    public void close(PlayerEntity player) {
        super.close(player);
        this.context.run((world, blockPos) -> {
            this.dropInventory(player, world, this.input);
        });
    }

    public boolean canUse(PlayerEntity player) {
        return canUse(this.context, player, Blocks.CRAFTING_TABLE);
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
    }
}
class GrinderHighLevelFuelSlot extends Slot {
    private final GrinderSHB handler;

    public GrinderHighLevelFuelSlot(GrinderSHB handler, Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
        this.handler = handler;
    }

    public boolean canInsert(ItemStack stack) {
        return this.handler.isFuel(stack) || isBucket(stack);
    }

    public int getMaxStackAmount(ItemStack stack) {
        return isBucket(stack) ? 1 : super.getMaxStackAmount(stack);
    }

    public static boolean isBucket(ItemStack stack) {
        return stack.getItem() == Items.BUCKET;
    }
}/*extends GrinderB {
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

}*/
