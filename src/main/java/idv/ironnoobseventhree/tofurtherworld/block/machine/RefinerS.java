package idv.ironnoobseventhree.tofurtherworld.block.machine;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerListener;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;

public class RefinerS<T extends RefinerSHB> extends HandledScreen<T> implements ScreenHandlerListener {
    private Identifier texture;

    public RefinerS(T handler, PlayerInventory playerInventory, Text title, Identifier texture) {
        super(handler, playerInventory, title);
        this.texture = texture;
    }

    protected void setup() {
    }

    protected void init() {
        super.init();
        this.setup();
        ((RefinerSH)this.handler).addListener(this);
    }

    public void removed() {
        super.removed();
        ((RefinerSH)this.handler).removeListener(this);
    }

    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        this.renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        RenderSystem.disableBlend();
        this.renderForeground(matrices, mouseX, mouseY, delta);
        this.drawMouseoverTooltip(matrices, mouseX, mouseY);
    }

    protected void renderForeground(MatrixStack matrixStack, int mouseY, int i, float f) {
    }

    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.client.getTextureManager().bindTexture(this.texture);
        int i = (this.width - this.backgroundWidth) / 2;
        int j = (this.height - this.backgroundHeight) / 2;
        int l;
        this.drawTexture(matrices, i, j, 0, 0, this.backgroundWidth, this.backgroundHeight);
        this.drawTexture(matrices, i + 59, j + 20, 0, this.backgroundHeight + (((RefinerSH)this.handler).getSlot(0).hasStack() ? 0 : 16), 110, 16);
        /*if ((((HighLevelFuelAbleSH)this.handler).getSlot(0).hasStack() || ((HighLevelFuelAbleSH)this.handler).getSlot(1).hasStack() || ((HighLevelFuelAbleSH)this.handler).getSlot(2).hasStack() || ((HighLevelFuelAbleSH)this.handler).getSlot(3).hasStack()|| ((ForgingTableL1B)this.handler).getSlot(4).hasStack()|| ((ForgingTableL1B)this.handler).getSlot(5).hasStack()|| ((ForgingTableL1B)this.handler).getSlot(6).hasStack()|| ((ForgingTableL1B)this.handler).getSlot(7).hasStack()|| ((ForgingTableL1B)this.handler).getSlot(8).hasStack()) && !((ForgingTableL1B)this.handler).getSlot(9).hasStack()) {
            this.drawTexture(matrices, i + 92, j + 36, this.backgroundWidth, 0, 28, 21);
        }*/
        if (((RefinerSHB)this.handler).isBurning()) {
            l = ((RefinerSHB)this.handler).getFuelProgress();
            this.drawTexture(matrices, i + 56, j + 39 + 12 - l, 176, 12 - l, 14, l + 1);
        }

        l = ((RefinerSHB)this.handler).getCookProgress();
        this.drawTexture(matrices, i + 85, j + 37, 176, 14, l + 1, 16);
    }


    public void onHandlerRegistered(ScreenHandler handler, DefaultedList<ItemStack> stacks) {
        this.onSlotUpdate(handler, 0, handler.getSlot(0).getStack());
    }

    public void onPropertyUpdate(ScreenHandler handler, int property, int value) {
    }

    public void onSlotUpdate(ScreenHandler handler, int slotId, ItemStack stack) {
    }
}
