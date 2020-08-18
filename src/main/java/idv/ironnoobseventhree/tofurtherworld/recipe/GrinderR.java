package idv.ironnoobseventhree.tofurtherworld.recipe;

import idv.ironnoobseventhree.tofurtherworld.Core;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;

public class GrinderR extends GrinderRB {
    public GrinderR(Identifier id, String group, Ingredient input1, Ingredient input2, ItemStack output1, float experience, int cookTime) {
        super(Core.GrinderR, id, group, input1,input2, output1, experience, cookTime);
    }

    @Environment(EnvType.CLIENT)
    public ItemStack getRecipeKindIcon() {
        return new ItemStack(Blocks.SAND);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Core.grinder_type;
    }

    @Override
    public String getGroup() {
        return super.getGroup();
    }

    @Override
    public ItemStack getOutput() {
        return super.getOutput();
    }

    @Override
    public float getExperience() {
        return super.getExperience();
    }

    @Override
    public int getCookTime() {
        return super.getCookTime();
    }
    public void writeInput(PacketByteBuf packetByteBuf){
        input1.write(packetByteBuf);

    }
}