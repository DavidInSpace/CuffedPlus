package git.david.cuffedplus.recipes;

import git.david.cuffedplus.init.ModRecipes;
import git.david.cuffedplus.items.item.JumpsuitItem;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmithingRecipe;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class JumpsuitModifierRecipe implements SmithingRecipe {
    public final ResourceLocation id;
    public final Ingredient template;
    public final Ingredient base;
    public final Ingredient addition;

    public JumpsuitModifierRecipe(ResourceLocation id, Ingredient template, Ingredient base, Ingredient addition) {
        this.id = id;
        this.template = template;
        this.base = base;
        this.addition = addition;
    }

    @Override
    public boolean matches(Container container, Level level) {
        return template.test(container.getItem(0)) &&
                base.test(container.getItem(1)) &&
                addition.test(container.getItem(2));
    }

    @Override
    public @NotNull ItemStack assemble(Container container, @NotNull RegistryAccess access) {
        ItemStack jumpsuitModifierItem = container.getItem(0);
        ResourceLocation modKey = BuiltInRegistries.ITEM.getKey(jumpsuitModifierItem.getItem());



        ItemStack baseItem = container.getItem(1).copy();
        baseItem.setCount(1);

        ItemStack additionItem = container.getItem(2);


        if (additionItem.is(Items.DIAMOND)) {

            switch (modKey.toString()) {
                case "cuffedplus:locked":
                    JumpsuitItem.setLocked(baseItem, true);
                case "cuffedplus:high_visiblity":
                    JumpsuitItem.setHighVisibility(baseItem, true);
                default:

                    // throw new IllegalStateException("Oh no! Unexpected value: " + modKey);
            }


        } else if (additionItem.is(Items.NETHERITE_SCRAP)) {

            switch (modKey.toString()) {
                case "cuffedplus:locked":
                    JumpsuitItem.setLocked(baseItem, false);
                case "cuffedplus:high_visiblity":
                    JumpsuitItem.setHighVisibility(baseItem, false);
                default:
            }

        } else {

            return ItemStack.EMPTY;

        }


        return baseItem;
    }

    @Override
    public boolean isTemplateIngredient(ItemStack stack) {
        return template.test(stack);
    }

    @Override
    public boolean isBaseIngredient(ItemStack stack) {
        return base.test(stack);
    }

    @Override
    public boolean isAdditionIngredient(ItemStack stack) {
        return addition.test(stack);
    }

    @Override
    public boolean canCraftInDimensions(int w, int h) {
        return true;
    }

    @Override
    public @NotNull ItemStack getResultItem(RegistryAccess access) {
        return ItemStack.EMPTY; // dynamic result
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return ModRecipes.JUMPSUIT_MODIFIER.get();
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return RecipeType.SMITHING;
    }

    @Override
    public @NotNull ResourceLocation getId() {
        return id;
    }
}