package idv.ironnoobseventhree.tofurtherworld.block.machine;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class RefinerI extends RefinerS<RefinerSH> {
    private static final Identifier TEXTURE = new Identifier("tofurtherworld:textures/gui/refiner.png");

    public RefinerI(RefinerSH handler, PlayerInventory playerInventory, Text title) {
        super(handler, playerInventory, title, TEXTURE);
        this.titleX = 30;
        this.titleY = 5;
    }

    protected void drawForeground(MatrixStack matrices, int mouseX, int mouseY) {
        RenderSystem.disableBlend();
        super.drawForeground(matrices, mouseX, mouseY);
    }
}
