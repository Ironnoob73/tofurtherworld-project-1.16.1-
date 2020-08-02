package idv.ironnoobseventhree.tofurtherworld.recipe;

import com.google.gson.JsonObject;
import idv.ironnoobseventhree.tofurtherworld.Core;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Blocks;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import com.google.gson.JsonObject;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;


public class ForgingL1Recipe implements Recipe<Inventory> {
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
    /*private final Ingredient base;
    private final Ingredient addition;
    private final ItemStack result;
    private final Identifier id;
    public static final Identifier ID = new Identifier(Core.MODID, "dungeons_transformer");
    public static final Type TYPE = new Type();


    public ForgingL1Recipe(Identifier id, Ingredient base, Ingredient addition, ItemStack result) {
        this.id = id;
        this.base = base;
        this.addition = addition;
        this.result = result;
    }

    public boolean matches(Inventory inv, World world) {
        return this.base.test(inv.getStack(0)) && this.addition.test(inv.getStack(1));
    }

    public ItemStack craft(Inventory inv) {
        ItemStack itemStack = this.result.copy();
        CompoundTag compoundTag = inv.getStack(0).getTag();
        if (compoundTag != null) {
            itemStack.setTag(compoundTag.copy());
        }

        return itemStack;
    }

    @Environment(EnvType.CLIENT)
    public boolean fits(int width, int height) {
        return width * height >= 2;
    }

    public ItemStack getOutput() {
        return this.result;
    }

    public boolean method_30029(ItemStack itemStack) {
        return this.addition.test(itemStack);
    }

    @Environment(EnvType.CLIENT)
    public ItemStack getRecipeKindIcon() {
        return new ItemStack(Blocks.SMITHING_TABLE);
    }

    public Identifier getId() {
        return this.id;
    }

    public RecipeSerializer<?> getSerializer() {
        return RecipeSerializer.SMITHING;
    }

    public RecipeType<?> getType() {
        return RecipeType.SMITHING;
    }

    public static class Serializer implements RecipeSerializer<ForgingL1Recipe> {
        public ForgingL1Recipe read(Identifier identifier, JsonObject jsonObject) {
            Ingredient ingredient = Ingredient.fromJson(JsonHelper.getObject(jsonObject, "base"));
            Ingredient ingredient2 = Ingredient.fromJson(JsonHelper.getObject(jsonObject, "addition"));
            ItemStack itemStack = ShapedRecipe.getItemStack(JsonHelper.getObject(jsonObject, "result"));
            return new ForgingL1Recipe(identifier, ingredient, ingredient2, itemStack);
        }

        public ForgingL1Recipe read(Identifier identifier, PacketByteBuf packetByteBuf) {
            Ingredient ingredient = Ingredient.fromPacket(packetByteBuf);
            Ingredient ingredient2 = Ingredient.fromPacket(packetByteBuf);
            ItemStack itemStack = packetByteBuf.readItemStack();
            return new ForgingL1Recipe(identifier, ingredient, ingredient2, itemStack);
        }

        public void write(PacketByteBuf packetByteBuf, ForgingL1Recipe smithingRecipe) {
            smithingRecipe.base.write(packetByteBuf);
            smithingRecipe.addition.write(packetByteBuf);
            packetByteBuf.writeItemStack(smithingRecipe.result);
        }
    }*/

    public static final Identifier ID = new Identifier(Core.MODID, "forgingtable_l1");
    public static final Type TYPE = new Type();

    private Identifier id;
    private Ingredient input;
    private ItemStack output;

    private ForgingL1Recipe(Identifier id, Ingredient input, ItemStack output) {
        this.id = id;
        this.input = input;
        this.output = output;
    }

    @Override
    public boolean matches(Inventory inv, World world) {
        return input.test(inv.getStack(0)) && inv.getStack(1).isEmpty();
    }

    @Override
    public ItemStack craft(Inventory inv) {
        return output.copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getOutput() {
        return output;
    }

    @Override
    public Identifier getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Recipes.ForgingL1;
    }

    @Override
    public RecipeType<?> getType() {
        return TYPE;
    }

    @Override
    public DefaultedList<Ingredient> getPreviewInputs() {
        return DefaultedList.copyOf(Ingredient.EMPTY, this.input);
    }

    public static class Type implements RecipeType<ForgingL1Recipe> {
        @Override
        public String toString() {
            return ID.toString();
        }
    }

    public static class Serializer implements RecipeSerializer<ForgingL1Recipe> {

        public static final Serializer INSTANCE = new Serializer();

        @Override
        public ForgingL1Recipe read(Identifier id, JsonObject jsonObject) {
            Ingredient ingredient2;
            if (JsonHelper.hasArray(jsonObject, "ingredient")) {
                ingredient2 = Ingredient.fromJson(JsonHelper.getArray(jsonObject, "ingredient"));
            } else {
                ingredient2 = Ingredient.fromJson(JsonHelper.getObject(jsonObject, "ingredient"));
            }

            String string2 = JsonHelper.getString(jsonObject, "result");
            int i = JsonHelper.getInt(jsonObject, "count");
            ItemStack itemStack = new ItemStack(Registry.ITEM.get(new Identifier(string2)), i);
            return new ForgingL1Recipe(id, ingredient2, itemStack);
        }

        @Override
        public ForgingL1Recipe read(Identifier id, PacketByteBuf packetByteBuf) {
            Ingredient ingredient = Ingredient.fromPacket(packetByteBuf);
            ItemStack itemStack = packetByteBuf.readItemStack();
            return new ForgingL1Recipe(id, ingredient, itemStack);
        }

        @Override
        public void write(PacketByteBuf packetByteBuf, ForgingL1Recipe recipe) {
            recipe.input.write(packetByteBuf);
            packetByteBuf.writeItemStack(recipe.output);
        }
    }
}
