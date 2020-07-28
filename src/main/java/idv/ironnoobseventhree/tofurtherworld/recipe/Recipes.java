package idv.ironnoobseventhree.tofurtherworld.recipe;

import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeType;

public interface Recipes<F extends Recipe<ForgingTableL1Inventory>> extends RecipeType {
    Recipes<ForgingL1Recipe> ForgingL1 = (Recipes<ForgingL1Recipe>) RecipeType.register("forgingl1");
}
