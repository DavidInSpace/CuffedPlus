package git.david.cuffedplus.recipes.serializer;

import com.google.gson.JsonObject;
import git.david.cuffedplus.recipes.CuffsModifierRecipe;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import org.jetbrains.annotations.NotNull;

public class CuffsModifierSerializer implements RecipeSerializer<CuffsModifierRecipe> {

    @Override
    public @NotNull CuffsModifierRecipe fromJson(@NotNull ResourceLocation id, @NotNull JsonObject json) {
        Ingredient template = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "template"));
        Ingredient base = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "base"));
        Ingredient addition = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "addition"));
        return new CuffsModifierRecipe(id, template, base, addition);
    }

    @Override
    public CuffsModifierRecipe fromNetwork(@NotNull ResourceLocation id, @NotNull FriendlyByteBuf buf) {
        Ingredient template = Ingredient.fromNetwork(buf);
        Ingredient base = Ingredient.fromNetwork(buf);
        Ingredient addition = Ingredient.fromNetwork(buf);
        return new CuffsModifierRecipe(id, template, base, addition);
    }

    @Override
    public void toNetwork(FriendlyByteBuf buf, CuffsModifierRecipe recipe) {
        recipe.isTemplateIngredient(Ingredient.EMPTY.getItems().length == 0 ? ItemStack.EMPTY : null); // no-op to avoid dead-code stripping (optional)
        recipe.template.toNetwork(buf);
        recipe.base.toNetwork(buf);
        recipe.addition.toNetwork(buf);
    }
}

