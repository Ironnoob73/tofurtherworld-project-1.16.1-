package idv.ironnoobseventhree.tofurtherworld.recipe;

import idv.ironnoobseventhree.tofurtherworld.Core;
import idv.ironnoobseventhree.tofurtherworld.block.forging.ForgingTableL1Inventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


import java.util.Optional;

public class Recipes/*<T extends Recipe<?>> extends RecipeType*/{
    //Recipes<ForgingL1Recipe> ForgingL1 = (Recipes<ForgingL1Recipe>) RecipeType.register("forgingl1");

    public static ForgingL1Recipe.Serializer ForgingL1;

    public static void init() {
        ForgingL1 = Registry.register(Registry.RECIPE_SERIALIZER, ForgingL1Recipe.ID, ForgingL1Recipe.Serializer.INSTANCE);
        Registry.register(Registry.RECIPE_TYPE, new Identifier(Core.MODID, "forgingtable_l1"), ForgingL1Recipe.TYPE);
    }
}
