package idv.ironnoobseventhree.tofurtherworld.block;

import idv.ironnoobseventhree.tofurtherworld.Core;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.screen.Generic3x3ContainerScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.collection.DefaultedList;

public class DrawerEntity extends LootableContainerBlockEntity {
    public  DefaultedList<ItemStack> inv;
    public DrawerEntity(){
        this(Core.Drawere);
    }
    protected DrawerEntity(BlockEntityType<?> blockEntityType){
        super(blockEntityType);
        inv=DefaultedList.ofSize(9,ItemStack.EMPTY);
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
        return new TranslatableText("block.tofurtherworld.drawer");
    }

    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new Generic3x3ContainerScreenHandler(syncId, playerInventory, this);
    }

    @Override
    public int size() {
        return 9;
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
}
