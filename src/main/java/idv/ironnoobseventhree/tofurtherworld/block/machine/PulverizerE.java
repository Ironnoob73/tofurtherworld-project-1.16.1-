package idv.ironnoobseventhree.tofurtherworld.block.machine;

import idv.ironnoobseventhree.tofurtherworld.Core;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.Tickable;
import net.minecraft.util.collection.DefaultedList;

public class PulverizerE extends LootableContainerBlockEntity implements Tickable {
    public DefaultedList<ItemStack> inv;
    public PulverizerE(){
        this(Core.PulverizerE);
    }
    public int t=0;
    public PulverizerE(BlockEntityType<?> blockEntityType) {
        super(blockEntityType);
        inv=DefaultedList.ofSize(10,ItemStack.EMPTY);
    }

    @Override
    protected Text getContainerName() {
        return null;
    }

    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return null;
    }

    @Override
    protected DefaultedList<ItemStack> getInvStackList() {
        return inv;
    }

    @Override
    protected void setInvStackList(DefaultedList<ItemStack> list) {
        inv=list;
    }

    public void install(PlayerEntity p){
        if(inv.get(0).isEmpty()){
            inv.set(0,p.getMainHandStack().split(1));
        }
        markDirty();
    }

    @Override
    public int size() {
        return 10;
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
        if(!inv.get(0).isEmpty()){
            if(inv.get(0).isItemEqual(new ItemStack(Items.POTATO))){
                t++;
                world.addParticle(ParticleTypes.SMOKE, (double)pos.getX(), (double)pos.getY() + 1.0D, (double)pos.getX(), 0.0D, 0.0D, 0.0D);
                if(t>50){
                    t=0;
                    inv.set(0, new ItemStack(Items.BAKED_POTATO));
                }
            }else{
                ItemScatterer.spawn(this.getWorld(),getPos().up(1),inv);
                inv.set(0,ItemStack.EMPTY);
                t=0;
            }
        }else{
            ItemScatterer.spawn(this.getWorld(),getPos().up(1),inv);
            inv.set(0,ItemStack.EMPTY);
            t=0;
        }
    }
}
