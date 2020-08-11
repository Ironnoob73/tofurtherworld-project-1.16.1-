package idv.ironnoobseventhree.tofurtherworld.block.forging;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.block.BlockState;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerListener;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;

public class ForgingTableL1Screen<T extends ForgingTableL1B> extends HandledScreen<T> implements ScreenHandlerListener {
    private Identifier texture;

    public ForgingTableL1Screen(T handler, PlayerInventory playerInventory, Text title, Identifier texture) {
        super(handler, playerInventory, title);
        this.texture = texture;
    }

    protected void setup() {
    }

    protected void init() {
        super.init();
        this.setup();
        ((ForgingTableL1B)this.handler).addListener(this);
    }

    public void removed() {
        super.removed();
        ((ForgingTableL1B)this.handler).removeListener(this);
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
        this.drawTexture(matrices, i + 59, j + 20, 0, this.backgroundHeight + (((ForgingTableL1B)this.handler).getSlot(0).hasStack() ? 0 : 16), 110, 16);
        if ((((ForgingTableL1B)this.handler).getSlot(0).hasStack() || ((ForgingTableL1B)this.handler).getSlot(1).hasStack() || ((ForgingTableL1B)this.handler).getSlot(2).hasStack() || ((ForgingTableL1B)this.handler).getSlot(3).hasStack()|| ((ForgingTableL1B)this.handler).getSlot(4).hasStack()|| ((ForgingTableL1B)this.handler).getSlot(5).hasStack()|| ((ForgingTableL1B)this.handler).getSlot(6).hasStack()|| ((ForgingTableL1B)this.handler).getSlot(7).hasStack()|| ((ForgingTableL1B)this.handler).getSlot(8).hasStack()) && !((ForgingTableL1B)this.handler).getSlot(9).hasStack()) {
            this.drawTexture(matrices, i + 92, j + 36, this.backgroundWidth, 0, 28, 21);
        }

    }

    public void onHandlerRegistered(ScreenHandler handler, DefaultedList<ItemStack> stacks) {
        this.onSlotUpdate(handler, 0, handler.getSlot(0).getStack());
    }

    public void onPropertyUpdate(ScreenHandler handler, int property, int value) {
    }

    public void onSlotUpdate(ScreenHandler handler, int slotId, ItemStack stack) {
    }
}
abstract class ForgingTableL1B extends ScreenHandler {
    protected final Inventory output = new CraftingResultInventory();
    protected final Inventory input = new SimpleInventory(10) {//这里还存在上限问题，预计会合并ForgingTableL1ScreenHandler
        public void markDirty() {
            super.markDirty();
            ForgingTableL1B.this.onContentChanged(this);
        }
    };
    protected final ScreenHandlerContext context;
    protected final PlayerEntity player;

    protected abstract boolean canTakeOutput(PlayerEntity player, boolean present);

    protected abstract ItemStack onTakeOutput(PlayerEntity player, ItemStack stack);

    protected abstract boolean canUse(BlockState state);

    public ForgingTableL1B(ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(type, syncId);
        this.context = context;
        this.player = playerInventory.player;
    }

    public abstract void updateResult();

    public void onContentChanged(Inventory inventory) {
        super.onContentChanged(inventory);
        if (inventory == this.input) {
            this.updateResult();
        }

    }

    public void close(PlayerEntity player) {
        super.close(player);
        this.context.run((world, blockPos) -> {
            this.dropInventory(player, world, this.input);
        });
    }

    public boolean canUse(PlayerEntity player) {
        return (Boolean)this.context.run((world, blockPos) -> {
            return !this.canUse(world.getBlockState(blockPos)) ? false : player.squaredDistanceTo((double)blockPos.getX() + 0.5D, (double)blockPos.getY() + 0.5D, (double)blockPos.getZ() + 0.5D) <= 64.0D;
        }, true);
    }

    protected boolean method_30025(ItemStack itemStack) {
        return false;
    }
}

