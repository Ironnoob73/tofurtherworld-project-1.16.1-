package idv.ironnoobseventhree.tofurtherworld.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;
import java.util.Objects;

public class PDA extends Item {
    public PDA(Settings settings)
    {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(new TranslatableText("item.tfw.pda.text"));
    }
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand)
    {
        user.playSound(SoundEvents.UI_TOAST_IN, 1.0F, 1.0F);
        user.playSound(SoundEvents.UI_TOAST_OUT, 1.0F, 1.0F);
        if(!world.isClient) Objects.requireNonNull(user.getServer()).getCommandManager().execute(user.getCommandSource(),"/function tofurtherworld:pda/homepage");
        return TypedActionResult.consume(user.getStackInHand(hand));
    }
}
