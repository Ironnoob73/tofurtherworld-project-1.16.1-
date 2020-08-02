package idv.ironnoobseventhree.tofurtherworld.block.forging;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Tickable;
import net.minecraft.util.collection.DefaultedList;

public class ForgingTableL1Entity /*extends LootableContainerBlockEntity implements Tickable*/ {
    //At least I comming to write this blockentity......
    //I really wanna to say F##K about it but my brain told me don't......
    //WHY THIS F##KING LITTLE BLOCK SHOULD WRITING SO MANY WORDS TO MAKE IT WORK?CAN'T BE MORE SIMPLE?
    /*public DefaultedList<ItemStack> inv;
    public ForgingTableL1Entity (BlockEntityType<?> type){
        super(type);
        inv= DefaultedList.ofSize(10,ItemStack.EMPTY);
    }

    @Override
    protected Text getContainerName() {
        return new TranslatableText("block.tofurtherworld.forging_table_l1_t");
    }

    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return ForgingTableL1ScreenHandler;
    }

    @Override
    protected DefaultedList<ItemStack> getInvStackList() {
        return inv;
    }

    @Override
    protected void setInvStackList(DefaultedList<ItemStack> list) {
        this.inv=list;
    }

    @Override
    public void tick(){

    }

    @Override
    public int size() {
        return 0;
    }*/
}
