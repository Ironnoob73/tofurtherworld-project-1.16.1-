package idv.ironnoobseventhree.tofurtherworld.recipe;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

public abstract class RefinerRB implements Recipe<Inventory> {
    protected final RecipeType<?> type;
    protected final Identifier id;
    protected final String group;
    protected final Ingredient input1;
    protected final Ingredient input2;
    protected final ItemStack output;
    protected final float experience;
    protected final int cookTime;

    public RefinerRB(RecipeType<?> type, Identifier id, String group, Ingredient input1, Ingredient input2, ItemStack output, float experience, int cookTime) {
        this.type = type;
        this.id = id;
        this.group = group;
        this.input1 = input1;
        this.input2 = input2;
        this.output = output;
        this.experience = experience;
        this.cookTime = cookTime;
    }

    public boolean matches(Inventory inv, World world) {
        return this.input1.test(inv.getStack(0));
    }
    public boolean matches1(Inventory inv, World world) {
        return this.input1.test(inv.getStack(0));
    }
    public boolean matches2(Inventory inv, World world) {
        return this.input2.test(inv.getStack(3));
    }

    public ItemStack craft(Inventory inv) {
        return this.output.copy();
    }

    @Environment(EnvType.CLIENT)
    public boolean fits(int width, int height) {
        return true;
    }

    public DefaultedList<Ingredient> getPreviewInputs() {
        DefaultedList<Ingredient> defaultedList = DefaultedList.of();
        defaultedList.add(this.input1);
        return defaultedList;
    }

    public float getExperience() {
        return this.experience;
    }

    public ItemStack getOutput() {
        return this.output;
    }

    @Environment(EnvType.CLIENT)
    public String getGroup() {
        return this.group;
    }

    public int getCookTime() {
        return this.cookTime;
    }

    public Identifier getId() {
        return this.id;
    }

    public RecipeType<?> getType() {
        return this.type;
    }
}

