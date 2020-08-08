package idv.ironnoobseventhree.tofurtherworld.recipe;

import com.google.gson.JsonObject;
import idv.ironnoobseventhree.tofurtherworld.Core;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.world.World;

//在此非常感谢qq群友@Saltwater Sea提供的巨大帮助！
//以及来自b站的@因为所以的教程！没有你们我什么都写不出来......
public class ForgingL1Recipe implements Recipe<Inventory> {
    private final Ingredient s1;
    private final Ingredient s2;
    private final Ingredient s3;
    private final Ingredient s4;
    private final Ingredient s5;
    private final Ingredient s6;
    private final Ingredient s7;
    private final Ingredient s8;
    private final Ingredient s9;
    private final ItemStack result;
    private final Identifier id;

    public ForgingL1Recipe(Identifier id, Ingredient s1, Ingredient s2, Ingredient s3, Ingredient s4, Ingredient s5, Ingredient s6, Ingredient s7, Ingredient s8, Ingredient s9, ItemStack result) {
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        this.s4 = s4;
        this.s5 = s5;
        this.s6 = s6;
        this.s7 = s7;
        this.s8 = s8;
        this.s9 = s9;
        this.result = result;
        this.id = id;
    }

    //@Override
    public boolean matches(/*@Nonnull*/ Inventory inv, World world) {
        return this.s1.test(inv.getStack(0))
                &&this.s2.test(inv.getStack(1))
                &&this.s3.test(inv.getStack(2))
                &&this.s4.test(inv.getStack(3))
                &&this.s5.test(inv.getStack(4))
                &&this.s6.test(inv.getStack(5))
                &&this.s7.test(inv.getStack(6))
                &&this.s8.test(inv.getStack(7))
                &&this.s9.test(inv.getStack(8));
    }

    @Override
    public ItemStack craft(Inventory inv) {
        ItemStack itemStack = this.result.copy();
        return itemStack;
    }

    @Environment(EnvType.CLIENT)//@Override
    public boolean fits(int width, int height) {
        return width * height >= 9;
    }

    //@Override
    public ItemStack getOutput() {
        return this.result;
    }

    @Environment(EnvType.CLIENT)
    public ItemStack getRecipeKindIcon() {
        return new ItemStack(Core.SteelIngot);
    }

    //@Override
    public Identifier getId() {
        return this.id;
    }

    //@Override
    public RecipeSerializer<?> getSerializer() {
        return Core.ForgingL1Serializer;
    }

    //@Override
    public RecipeType<?> getType() {
        return Core.ForgingL1Type;
    }

    /*public boolean method_30029(ItemStack itemStack) {
        return this.s1.test(itemStack);
    }*/

    public static class Serializer implements RecipeSerializer<ForgingL1Recipe> {

        //@Override
        public ForgingL1Recipe read(Identifier identifier, JsonObject jsonObject) {
            Ingredient ingredient1 = Ingredient.fromJson(JsonHelper.getObject(jsonObject, "s1"));
            Ingredient ingredient2 = Ingredient.fromJson(JsonHelper.getObject(jsonObject, "s2"));
            Ingredient ingredient3 = Ingredient.fromJson(JsonHelper.getObject(jsonObject, "s3"));
            Ingredient ingredient4 = Ingredient.fromJson(JsonHelper.getObject(jsonObject, "s4"));
            Ingredient ingredient5 = Ingredient.fromJson(JsonHelper.getObject(jsonObject, "s5"));
            Ingredient ingredient6 = Ingredient.fromJson(JsonHelper.getObject(jsonObject, "s6"));
            Ingredient ingredient7 = Ingredient.fromJson(JsonHelper.getObject(jsonObject, "s7"));
            Ingredient ingredient8 = Ingredient.fromJson(JsonHelper.getObject(jsonObject, "s8"));
            Ingredient ingredient9 = Ingredient.fromJson(JsonHelper.getObject(jsonObject, "s9"));
            ItemStack itemStack = ShapedRecipe.getItemStack(JsonHelper.getObject(jsonObject, "result"));
            return new ForgingL1Recipe(identifier, ingredient1, ingredient2, ingredient3, ingredient4, ingredient5, ingredient6, ingredient7, ingredient8, ingredient9, itemStack);
        }

        //@Override
        public ForgingL1Recipe read(Identifier identifier, PacketByteBuf packetByteBuf) {
            Ingredient ingredient1 = Ingredient.fromPacket(packetByteBuf);
            Ingredient ingredient2 = Ingredient.fromPacket(packetByteBuf);
            Ingredient ingredient3 = Ingredient.fromPacket(packetByteBuf);
            Ingredient ingredient4 = Ingredient.fromPacket(packetByteBuf);
            Ingredient ingredient5 = Ingredient.fromPacket(packetByteBuf);
            Ingredient ingredient6 = Ingredient.fromPacket(packetByteBuf);
            Ingredient ingredient7 = Ingredient.fromPacket(packetByteBuf);
            Ingredient ingredient8 = Ingredient.fromPacket(packetByteBuf);
            Ingredient ingredient9 = Ingredient.fromPacket(packetByteBuf);
            ItemStack itemStack = packetByteBuf.readItemStack();
            return new ForgingL1Recipe(identifier, ingredient1, ingredient2,ingredient3,ingredient4,ingredient5,ingredient6,ingredient7,ingredient8,ingredient9, itemStack);
        }

        //@Override
        public void write(PacketByteBuf buf, /*@Nonnull*/ ForgingL1Recipe recipe) {
            recipe.s1.write(buf);
            recipe.s2.write(buf);
            recipe.s3.write(buf);
            recipe.s4.write(buf);
            recipe.s5.write(buf);
            recipe.s6.write(buf);
            recipe.s7.write(buf);
            recipe.s8.write(buf);
            recipe.s9.write(buf);
            buf.writeItemStack(recipe.result);
        }
    }
}
