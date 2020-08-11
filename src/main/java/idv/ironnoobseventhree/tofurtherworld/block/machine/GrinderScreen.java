package idv.ironnoobseventhree.tofurtherworld.block.machine;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerListener;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;

@Environment(EnvType.CLIENT)
public class GrinderScreen<T extends GrinderB> extends HandledScreen<T> implements ScreenHandlerListener {
    private Identifier texture;

    public GrinderScreen(T handler, PlayerInventory playerInventory, Text title, Identifier texture) {
        super(handler, playerInventory, title);
        this.texture = texture;
    }

    protected void setup() {
    }

    protected void init() {
        super.init();
        this.setup();
        ((GrinderB)this.handler).addListener(this);
    }

    public void removed() {
        super.removed();
        ((GrinderB)this.handler).removeListener(this);
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
        this.drawTexture(matrices, i, j, 0, 0, this.backgroundWidth, this.backgroundHeight);
        /*this.drawTexture(matrices, i + 59, j + 20, 0, this.backgroundHeight + (((GrinderB)this.handler).getSlot(0).hasStack() ? 0 : 16), 110, 16);
        if ((((GrinderB)this.handler).getSlot(0).hasStack() || ((GrinderB)this.handler).getSlot(1).hasStack()) && !((GrinderB)this.handler).getSlot(2).hasStack()) {
            this.drawTexture(matrices, i + 99, j + 45, this.backgroundWidth, 0, 28, 21);
        }*/

    }

    public void onHandlerRegistered(ScreenHandler handler, DefaultedList<ItemStack> stacks) {
        this.onSlotUpdate(handler, 0, handler.getSlot(0).getStack());
    }

    public void onPropertyUpdate(ScreenHandler handler, int property, int value) {
    }

    public void onSlotUpdate(ScreenHandler handler, int slotId, ItemStack stack) {
    }
}
