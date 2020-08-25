package idv.ironnoobseventhree.tofurtherworld.item;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class BlueprintItemR extends Item {

    public BlueprintItemR(Blueprint rarity, Settings settings) {
        super(settings);
    }

    @Environment(EnvType.CLIENT)
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(this.getDescription().formatted(Formatting.GRAY));
    }

    @Environment(EnvType.CLIENT)
    public MutableText getDescription() {
        return new TranslatableText(this.getTranslationKey() + ".f");
    }
    public boolean hasGlint(ItemStack stack) {
        return true;
    }
}
