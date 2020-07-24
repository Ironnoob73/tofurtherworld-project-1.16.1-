package idv.ironnoobseventhree.tofurtherworld.recipes;

import com.google.gson.JsonObject;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.ShapedRecipe.Serializer;
import net.minecraft.util.Identifier;

public interface Main extends RecipeSerializer{
    RecipeSerializer<L1S> FORGINGL1 = RecipeSerializer.register("forging_l1", new RecipeSerializer<L1S>()
    {
        @Override
        public L1S read(Identifier id, JsonObject json) {
            return null;
        }

        @Override
        public L1S read(Identifier id, PacketByteBuf buf) {
            return null;
        }

        @Override
        public void write(PacketByteBuf buf, L1S recipe) {

        }
    });
    //Serializer Forgingl1= RecipeSerializer.register("forging_l1", new Serializer());
}
