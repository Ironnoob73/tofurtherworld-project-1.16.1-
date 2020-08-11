package idv.ironnoobseventhree.tofurtherworld.recipe;

import idv.ironnoobseventhree.tofurtherworld.Core;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;

public class RefinerR extends RefinerRB {
    public RefinerR(Identifier id, String group, Ingredient input, ItemStack output, float experience, int cookTime) {
        super(RecipeType.SMELTING, id, group, input, output, experience, cookTime);
    }

    @Environment(EnvType.CLIENT)
    public ItemStack getRecipeKindIcon() {
        return new ItemStack(Core.Refiner);
    }

    public RecipeSerializer<?> getSerializer() {
        return Core.RefinerR;
    }
}