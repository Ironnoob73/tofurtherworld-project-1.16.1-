package idv.ironnoobseventhree.tofurtherworld.item;

import idv.ironnoobseventhree.tofurtherworld.Core;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Lazy;

import java.util.function.Supplier;

public enum ArmorSetting implements ArmorMaterial {
   Copper("copper", 7, new int[]{1, 4, 5, 2}, 6, SoundEvents.ITEM_ARMOR_EQUIP_GOLD, 0.0F, 0.0F, () -> { return Ingredient.ofItems(Core.CopperIngot); }),
   Aluminum("aluminum", 11, new int[]{2, 4, 6, 3}, 17, SoundEvents.ITEM_ARMOR_EQUIP_TURTLE, 0.0F, 0.0F, () -> { return Ingredient.ofItems(Core.AluminumIngot); }),
   StoneBrick("stonebrick", 13, new int[]{1, 3, 4, 1}, 5, SoundEvents.ITEM_ARMOR_EQUIP_TURTLE, 0.0F, 0.5F, () -> { return Ingredient.ofItems(Core.StoneIngot); });

   private static final int[] BASE_DURABILITY = new int[]{13, 15, 16, 11};
   private final String name;
   private final int durabilityMultiplier;
   private final int[] protectionAmounts;
   private final int enchantability;
   private final SoundEvent equipSound;
   private final float toughness;
   private final float knockbackResistance;
   private final Lazy<Ingredient> repairIngredientSupplier;

   ArmorSetting(String name, int durabilityMultiplier, int[] protectionAmounts, int enchantability, SoundEvent equipSound, float toughness, float knockbackResistance, Supplier<Ingredient> supplier) {
      this.name = name;
      this.durabilityMultiplier = durabilityMultiplier;
      this.protectionAmounts = protectionAmounts;
      this.enchantability = enchantability;
      this.equipSound = equipSound;
      this.toughness = toughness;
      this.knockbackResistance = knockbackResistance;
      this.repairIngredientSupplier = new Lazy(supplier);
   }

   public int getDurability(EquipmentSlot slot) {
      return BASE_DURABILITY[slot.getEntitySlotId()] * this.durabilityMultiplier;
   }

   public int getProtectionAmount(EquipmentSlot slot) {
      return this.protectionAmounts[slot.getEntitySlotId()];
   }

   public int getEnchantability() {
      return this.enchantability;
   }

   public SoundEvent getEquipSound() {
      return this.equipSound;
   }

   public Ingredient getRepairIngredient() {
      return (Ingredient)this.repairIngredientSupplier.get();
   }

   @Environment(EnvType.CLIENT)
   public String getName() {
      return this.name;
   }

   public float getToughness() {
      return this.toughness;
   }

   public float getKnockbackResistance() {
      return this.knockbackResistance;
   }
}
