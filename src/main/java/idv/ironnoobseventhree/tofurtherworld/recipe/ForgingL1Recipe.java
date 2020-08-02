package idv.ironnoobseventhree.tofurtherworld.recipe;

import idv.ironnoobseventhree.tofurtherworld.block.forging.ForgingTableL1Inventory;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Blocks;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

public interface ForgingL1Recipe<C extends ForgingTableL1Inventory> extends Recipe<Inventory> {
    /*boolean matches(C inv, World world);

    ItemStack craft(C inv);

    @Environment(EnvType.CLIENT)
    boolean fits(int width, int height);

    ItemStack getOutput();

    default DefaultedList<ItemStack> getRemainingStacks(C inventory) {
        DefaultedList<ItemStack> defaultedList = DefaultedList.ofSize(inventory.size(), ItemStack.EMPTY);

        for(int i = 0; i < defaultedList.size(); ++i) {
            Item item = inventory.getStack(i).getItem();
            if (item.hasRecipeRemainder()) {
                defaultedList.set(i, new ItemStack(item.getRecipeRemainder()));
            }
        }

        return defaultedList;
    }

    default DefaultedList<Ingredient> getPreviewInputs() {
        return DefaultedList.of();
    }

    default boolean isIgnoredInRecipeBook() {
        return false;
    }

    @Environment(EnvType.CLIENT)
    default String getGroup() {
        return "";
    }

    @Environment(EnvType.CLIENT)
    default ItemStack getRecipeKindIcon() {
        return new ItemStack(Blocks.CRAFTING_TABLE);
    }

    Identifier getId();

    RecipeSerializer<?> getSerializer();

    RecipeType<?> getType();*/
}
