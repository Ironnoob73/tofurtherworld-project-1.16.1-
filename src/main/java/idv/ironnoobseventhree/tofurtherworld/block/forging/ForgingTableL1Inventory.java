package idv.ironnoobseventhree.tofurtherworld.block.forging;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ForgingTableL1Inventory extends ForgingScreen<ForgingTableL1ScreenHandler> {
    private static final Identifier TEXTURE = new Identifier("tofurtherworld:textures/gui/forgingtablel1.png");

    public ForgingTableL1Inventory(ForgingTableL1ScreenHandler handler, PlayerInventory playerInventory, Text title) {
        super(handler, playerInventory, title, TEXTURE);
        this.titleX = 30;
        this.titleY = 5;
    }

    protected void drawForeground(MatrixStack matrices, int mouseX, int mouseY) {
        RenderSystem.disableBlend();
        super.drawForeground(matrices, mouseX, mouseY);
    }
}
