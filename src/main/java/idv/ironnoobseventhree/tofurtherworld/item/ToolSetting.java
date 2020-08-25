package idv.ironnoobseventhree.tofurtherworld.item;

import idv.ironnoobseventhree.tofurtherworld.Core;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Lazy;

import java.util.function.Supplier;

public enum ToolSetting implements ToolMaterial {
    Rough(0, 29, 0.0F, 1.0F, 0, () ->
            Ingredient.ofItems((ItemConvertible) null)),
    Copper(0, 140, 3.0F, 1.0F, 6, () ->
            Ingredient.ofItems(Core.CopperIngot)),
    Aluminum(1, 200, 5.0F, 2.0F, 17, () ->
            Ingredient.ofItems(Core.AluminumIngot)),
    OnlyIron(2, 850, 5.7F, 2.7F, 16, () ->
            Ingredient.ofItems(Items.IRON_INGOT)),
    RuggedizedAluminum(1, 400, 4.0F, 2.0F, 20, () ->
            Ingredient.ofItems(Core.AluminumIngot)),
    StoneBrick(1, 262, 4.0F, 1.0F, 5, () ->
            Ingredient.ofItems(Core.StoneIngot)),
    Steel(2, 900, 6.0F, 3.0F, 15, () ->
            Ingredient.ofItems(Core.SteelIngot)),
    Obsidian(3, 1500, 9.0F, 3.5F, 20, () ->
            Ingredient.ofItems(Core.PureObsidian));

    private final int miningLevel;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Lazy<Ingredient> repairIngredient;

    private ToolSetting(int miningLevel, int itemDurability, float miningSpeed, float attackDamage, int enchantability, Supplier<Ingredient> repairIngredient) {
        this.miningLevel = miningLevel;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairIngredient = new Lazy(repairIngredient);
    }

    public int getDurability() {
        return this.itemDurability;
    }

    public float getMiningSpeedMultiplier() {
        return this.miningSpeed;
    }

    public float getAttackDamage() {
        return this.attackDamage;
    }

    public int getMiningLevel() {
        return this.miningLevel;
    }

    public int getEnchantability() {
        return this.enchantability;
    }

    public Ingredient getRepairIngredient() {
        return (Ingredient)this.repairIngredient.get();
    }
}
