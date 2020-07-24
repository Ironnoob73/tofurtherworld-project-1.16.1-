package idv.ironnoobseventhree.tofurtherworld.recipes;

import net.minecraft.recipe.CraftingRecipe;
import net.minecraft.recipe.RecipeType;

public interface Forgingl1 extends CraftingRecipe {
    default RecipeType<Forgingl1> getType() {
        return Type.Forgingl1;
    }
}
