package idv.ironnoobseventhree.tofurtherworld.recipe;

import idv.ironnoobseventhree.tofurtherworld.block.forging.ForgingTableL1Inventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.util.Optional;

public interface Recipes<T extends Recipe<?>> extends RecipeType{
    Recipes<ForgingL1Recipe> ForgingL1 = RecipeType.register("forgingl1");
}
