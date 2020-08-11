package idv.ironnoobseventhree.tofurtherworld.block.furniture;

import idv.ironnoobseventhree.tofurtherworld.Core;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.screen.HopperScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.registry.Registry;

public class TableCSE extends LootableContainerBlockEntity {
    public  DefaultedList<ItemStack> inv;
    public static BlockEntityType<TableCSE> TableE=
            Registry.register(Registry.BLOCK_ENTITY_TYPE,"tofurtherworld:table_entity",BlockEntityType.Builder.create(TableCSE::new, Core.AluminumTable).build(null));
    public TableCSE(){
        this(TableE);
    }
    protected TableCSE(BlockEntityType<?> blockEntityType){
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
        return new TranslatableText("block.tofurtherworld.table");
    }

    @Override
        protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new HopperScreenHandler(syncId, playerInventory, this);
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
}
