package idv.ironnoobseventhree.tofurtherworld.recipes;

import net.minecraft.recipe.CraftingRecipe;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeType;

public interface Type<T extends Recipe<?>> extends RecipeType {
    RecipeType<Forgingl1> Forgingl1 = RecipeType.register("forging_l1");
}
