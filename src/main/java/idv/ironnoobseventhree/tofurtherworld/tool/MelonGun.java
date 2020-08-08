package idv.ironnoobseventhree.tofurtherworld.tool;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.ItemTags;
import net.minecraft.world.World;

import java.util.function.Predicate;

public class MelonGun extends RangedWeaponItem {
    public MelonGun(Item.Settings settings) {
        super(settings);
    }

    @Override
    public Predicate<ItemStack> getProjectiles() {
        return (stack) -> stack.getItem() == Items.SNOWBALL;
    }

    @Override
    public int getRange() {
        /*From: teddyxlandlee
         * To:   低耐久的铁砧
         * 我也不知道这个方法是什么，大概是蓄力时间吧
         * 你回去调试一下，把值改成15会发生什么
         */
        return 0;
    }

    private static ItemStack getSnowballType(PlayerEntity playerEntity, ItemStack stack) {
        ItemStack fake = playerEntity.getArrowType(stack);
        return fake.getItem().isIn(ItemTags.ARROWS) ? new ItemStack(Items.MELON_SEEDS) : fake;
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (user instanceof PlayerEntity) {
            PlayerEntity playerEntity = (PlayerEntity)user;
            boolean creative = playerEntity.abilities.creativeMode;
            ItemStack itemStack = MelonGun.getSnowballType(playerEntity, stack);
            if (!itemStack.isEmpty() || creative) {
                if (itemStack.isEmpty())
                    itemStack = new ItemStack(Items.MELON_SEEDS);

                boolean bl = creative && itemStack.getItem() == Items.MELON_SEEDS;
                if (!world.isClient) {
                    //assert Items.SNOWBALL instanceof SnowballItem;  // Here, please test it. Can Items.SNOWBALL instanceof SnowballItem? -- teddyxlandlee
                    //Item item = (itemStack.getItem() instanceof SnowballItem ? itemStack.getItem() : Items.MELON_SEEDS);
                    SnowballEntity snowballEntity = new SnowballEntity(world, user);
                    snowballEntity.setProperties(user, user.pitch, user.yaw, 0.0F, 1.5F, 1.0F);
                    stack.damage(1, playerEntity, (p) -> p.sendToolBreakStatus(playerEntity.getActiveHand()));
                    world.spawnEntity(snowballEntity);
                }
                world.playSound(null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.PLAYERS, 1.0F, 1.0F / (RANDOM.nextFloat() * 0.4F + 1.4F));
                if (!bl && !creative) {
                    itemStack.decrement(1);
                    if (itemStack.isEmpty())
                        playerEntity.inventory.removeOne(itemStack);
                }

                //playerEntity.increaseStat(Stats.USED.getOrCreateStat(this));
            }
        }
    }

   /*public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
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
    }*/
}

/*public class MelonGun extends Item {
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
}*/
