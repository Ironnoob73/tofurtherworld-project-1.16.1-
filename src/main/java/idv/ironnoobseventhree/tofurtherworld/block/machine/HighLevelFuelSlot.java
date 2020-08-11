package idv.ironnoobseventhree.tofurtherworld.block.machine;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.Slot;

public class HighLevelFuelSlot extends Slot {
    private final RefinerSHB handler;

    public HighLevelFuelSlot(RefinerSHB handler, Inventory inventory, int index, int x, int y) {
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
}
