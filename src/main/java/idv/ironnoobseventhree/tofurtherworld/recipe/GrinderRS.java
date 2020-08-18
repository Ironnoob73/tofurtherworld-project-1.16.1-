package idv.ironnoobseventhree.tofurtherworld.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.registry.Registry;

public class GrinderRS implements RecipeSerializer<GrinderR> {
    private final int cookingTime;
    private final RecipeFactory<GrinderR> recipeFactory;

    public GrinderRS(RecipeFactory<GrinderR> recipeFactory, int cookingTime) {
        this.cookingTime = cookingTime;
        this.recipeFactory = recipeFactory;
    }

    public GrinderR read(Identifier identifier, JsonObject jsonObject) {
        String string = JsonHelper.getString(jsonObject, "group", "");
        JsonElement jsonElementInput1 = JsonHelper.hasArray(jsonObject, "ingredient") ? JsonHelper.getArray(jsonObject, "ingredient") : JsonHelper.getObject(jsonObject, "ingredient");
        JsonElement jsonElementInput2 = JsonHelper.hasArray(jsonObject, "accessories") ? JsonHelper.getArray(jsonObject, "accessories") : JsonHelper.getObject(jsonObject, "accessories");
        Ingredient ingredient = Ingredient.fromJson(jsonElementInput1);
        Ingredient accessories = Ingredient.fromJson(jsonElementInput2);
        String string2 = JsonHelper.getString(jsonObject, "result");
        Identifier identifier2 = new Identifier(string2);
        ItemStack itemStack = new ItemStack(Registry.ITEM.getOrEmpty(identifier2).orElseThrow(() -> new IllegalStateException("Item: " + string2 + " does not exist")));
        float f = JsonHelper.getFloat(jsonObject, "experience", 0.0F);
        int i = JsonHelper.getInt(jsonObject, "cookingtime", this.cookingTime);
        return this.recipeFactory.create(identifier, string, ingredient,accessories, itemStack, f, i);
    }

    public GrinderR read(Identifier identifier, PacketByteBuf packetByteBuf) {
        String string = packetByteBuf.readString(32767);
        Ingredient ingredient = Ingredient.fromPacket(packetByteBuf);
        Ingredient accessories = Ingredient.fromPacket(packetByteBuf);
        ItemStack itemStack = packetByteBuf.readItemStack();
        float f = packetByteBuf.readFloat();
        int i = packetByteBuf.readVarInt();
        return this.recipeFactory.create(identifier, string, ingredient,accessories, itemStack, f, i);
    }

    public void write(PacketByteBuf packetByteBuf, GrinderR abstractCookingRecipe) {
        packetByteBuf.writeString(abstractCookingRecipe.getGroup());
        abstractCookingRecipe.writeInput(packetByteBuf);
        packetByteBuf.writeItemStack(abstractCookingRecipe.getOutput());
        packetByteBuf.writeFloat(abstractCookingRecipe.getExperience());
        packetByteBuf.writeVarInt(abstractCookingRecipe.getCookTime());
    }

    public interface RecipeFactory<T extends GrinderRB> {
        T create(Identifier id, String group, Ingredient input1,Ingredient input2, ItemStack output, float experience, int cookTime);
    }
}