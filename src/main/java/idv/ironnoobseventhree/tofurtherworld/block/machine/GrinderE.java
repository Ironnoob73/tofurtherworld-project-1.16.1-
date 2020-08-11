package idv.ironnoobseventhree.tofurtherworld.block.machine;

import idv.ironnoobseventhree.tofurtherworld.Core;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.recipe.*;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Tickable;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;

import java.util.Iterator;

public class GrinderE extends LootableContainerBlockEntity implements Tickable, RecipeInputProvider {
    public  DefaultedList<ItemStack> inv;
    private int cookTime;
    private int cookTimeTotal;
    public static BlockEntityType<GrinderE> GrinderE=
            Registry.register(Registry.BLOCK_ENTITY_TYPE,"tofurtherworld:grinder_entity",BlockEntityType.Builder.create(GrinderE::new, Core.Grinder).build(null));
    public GrinderE(){
        this(GrinderE);
    }
    //protected final RecipeType<? extends AbstractCookingRecipe> recipeType;
    protected GrinderE(BlockEntityType<?> blockEntityType){
        super(blockEntityType);
        inv=DefaultedList.ofSize(5,ItemStack.EMPTY);
    }
    @Override
    protected DefaultedList<ItemStack> getInvStackList() {
        return inv;
    }

    @Override
    protected void setInvStackList(DefaultedList<ItemStack> list) {
        inv=list;
    }

    @Override
    protected Text getContainerName() {
        return new TranslatableText("block.tofurtherworld.grinder");
    }

    @Override
        protected GrinderScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new GrinderScreenHandler(syncId, playerInventory, (ScreenHandlerContext) this);
    }
    @Override
    public int size() {
        return 5;
    }
    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);
        this.inv = DefaultedList.ofSize(this.size(), ItemStack.EMPTY);
        if (!this.deserializeLootTable(tag)) {
            Inventories.fromTag(tag, this.inv);
        }

    }

    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);
        if (!this.serializeLootTable(tag)) {
            Inventories.toTag(tag, this.inv);
        }

        return tag;
    }
    @Override
    public void tick() {
        /*boolean bl2 = false;

        if (!this.world.isClient) {
            ItemStack itemStack = (ItemStack)this.inv.get(1);
            if (itemStack.isEmpty() || ((ItemStack)this.inv.get(0)).isEmpty()) {
                if (this.cookTime > 0) {
                    this.cookTime = MathHelper.clamp(this.cookTime - 2, 0, this.cookTimeTotal);
                }
            } else {
                Recipe<?> recipe = (Recipe)this.world.getRecipeManager().getFirstMatch(this.recipeType, this, this.world).orElse(null);
                if ( this.canAcceptRecipeOutput(recipe)) {
                    this.burnTime = this.getFuelTime(itemStack);
                    //this.fuelTime = this.burnTime;
                    if (this.isWorking()) {
                        bl2 = true;
                        if (!itemStack.isEmpty()) {
                            Item item = itemStack.getItem();
                            itemStack.decrement(1);
                            if (itemStack.isEmpty()) {
                                Item item2 = item.getRecipeRemainder();
                                this.inv.set(1, item2 == null ? ItemStack.EMPTY : new ItemStack(item2));
                            }
                        }
                    }
                }

                if (this.isWorking() && this.canAcceptRecipeOutput(recipe)) {
                    ++this.cookTime;
                    if (this.cookTime == this.cookTimeTotal) {
                        this.cookTime = 0;
                        this.cookTimeTotal = this.getWorkTime();
                        this.craftRecipe(recipe);
                        bl2 = true;
                    }
                } else {
                    this.cookTime = 0;
                }
            }
        }

        if (bl2) {
            this.markDirty();
        }*/
    }
    /*protected int getWorkTime() {
        return (Integer)this.world.getRecipeManager().getFirstMatch(this.recipeType, this, this.world).map(AbstractCookingRecipe::getWorkTime).orElse(200);
    }*/

    /*private boolean isWorking() {
        return this.burnTime > 0;
    }*/

    public void provideRecipeInputs(RecipeFinder finder) {
        Iterator var2 = this.inv.iterator();

        while(var2.hasNext()) {
            ItemStack itemStack = (ItemStack)var2.next();
            finder.addItem(itemStack);
        }

    }
    protected boolean canAcceptRecipeOutput( Recipe<?> recipe) {
        if (!((ItemStack)this.inv.get(0)).isEmpty() && recipe != null) {
            ItemStack itemStack = recipe.getOutput();
            if (itemStack.isEmpty()) {
                return false;
            } else {
                ItemStack itemStack1 = (ItemStack)this.inv.get(1);
                ItemStack itemStack2 = (ItemStack)this.inv.get(2);
                ItemStack itemStack3 = (ItemStack)this.inv.get(3);
                if (itemStack1.isEmpty()&&itemStack2.isEmpty()&&itemStack3.isEmpty()) {
                    return true;
                } else if (!itemStack1.isItemEqualIgnoreDamage(itemStack)||!itemStack2.isItemEqualIgnoreDamage(itemStack)||!itemStack3.isItemEqualIgnoreDamage(itemStack)) {
                    return false;
                } else if (itemStack1.getCount() < this.getMaxCountPerStack() && itemStack1.getCount() < itemStack1.getMaxCount()&&itemStack2.getCount() < this.getMaxCountPerStack() && itemStack2.getCount() < itemStack2.getMaxCount()&&itemStack3.getCount() < this.getMaxCountPerStack() && itemStack3.getCount() < itemStack3.getMaxCount()) {
                    return true;
                } else {
                    return itemStack1.getCount() < itemStack.getMaxCount();
                }
            }
        } else {
            return false;
        }
    }

}
