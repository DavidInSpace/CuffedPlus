package git.david.cuffedplus.init;

import git.david.cuffedplus.recipes.CuffsModifierRecipe;
import git.david.cuffedplus.recipes.serializer.CuffsModifierSerializer;
import git.david.cuffedplus.recipes.JumpsuitModifierRecipe;
import git.david.cuffedplus.recipes.serializer.JumpsuitModifierSerializer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, "cuffedplus");

    public static final RegistryObject<RecipeSerializer<CuffsModifierRecipe>> CUFFS_MODIFIER =
            SERIALIZERS.register("cuffs_modifier", CuffsModifierSerializer::new);

    public static final RegistryObject<RecipeSerializer<JumpsuitModifierRecipe>> JUMPSUIT_MODIFIER =
            SERIALIZERS.register("jumpsuit_modifier", JumpsuitModifierSerializer::new);
}



