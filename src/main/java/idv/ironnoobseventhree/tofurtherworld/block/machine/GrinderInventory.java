package idv.ironnoobseventhree.tofurtherworld.block.machine;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class GrinderInventory extends GrinderScreen<GrinderScreenHandler> {
    private static final Identifier TEXTURE = new Identifier("tofurtherworld:textures/gui/grinder.png");

    public GrinderInventory(GrinderScreenHandler handler, PlayerInventory playerInventory, Text title) {
        super(handler, playerInventory, title, TEXTURE);
        this.titleX = 75;
        this.titleY = 5;
    }

    protected void drawForeground(MatrixStack matrices, int mouseX, int mouseY) {
        RenderSystem.disableBlend();
        super.drawForeground(matrices, mouseX, mouseY);
    }
}
