package idv.ironnoobseventhree.tofurtherworld.tool;

import com.google.common.collect.Lists;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.CrossbowUser;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireworkRocketEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.Quaternion;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.function.Predicate;

public class MelonGun extends Item {
    public MelonGun(Item.Settings settings) {
        super(settings);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if(user.inventory.contains(new ItemStack(Items.MELON_SEEDS))){
            ItemStack itemStack = new ItemStack(Items.MELON_SEEDS);
            world.playSound((PlayerEntity)null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (RANDOM.nextFloat() * 0.4F + 0.8F));
            user.getItemCooldownManager().set(this, 5);
            if (!world.isClient) {
                //itemStack = new ItemStack(Items.ARROW);
                SnowballEntity snowballEntity = new SnowballEntity(world, user);
                //snowballEntity.setItem(itemStack);
                snowballEntity.setProperties(user, user.pitch, user.yaw, 0.0F, 1.5F, 1.0F);
                world.spawnEntity(snowballEntity);
                itemStack.increment(1);
                //itemStack.removeStack();
            }
        }else{
            user.sendMessage(new TranslatableText("item.tfw.mg.text"), true);
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this));
        if (!user.abilities.creativeMode) {
            //itemStack.decrement(1);
        }

        return TypedActionResult.fail(user.getStackInHand(hand));
    }
}
