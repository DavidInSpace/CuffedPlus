package git.david.cuffedplus.recipes;

import git.david.cuffedplus.init.ModRecipes;
import git.david.cuffedplus.items.item.JumpsuitItem;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmithingRecipe;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public record JumpsuitModifierRecipe(ResourceLocation id, Ingredient template, Ingredient base,
                                     Ingredient addition) implements SmithingRecipe {

    @Override
    public boolean matches(Container container, @NotNull Level level) {
        return template.test(container.getItem(0)) &&
                base.test(container.getItem(1)) &&
                addition.test(container.getItem(2));
    }

    @Override
    public @NotNull ItemStack assemble(Container container, @NotNull RegistryAccess access) {
        ItemStack jumpsuitModifierItem = container.getItem(0);
        ResourceLocation modKey = ForgeRegistries.ITEMS.getKey(jumpsuitModifierItem.getItem());

        ItemStack baseItem = container.getItem(1).copy();
        baseItem.setCount(1);

        ItemStack additionItem = container.getItem(2);

        if (additionItem.is(Items.DIAMOND)) {

            switch (Objects.requireNonNull(modKey).toString()) {
                case "cuffedplus:lock_modifier":
                    JumpsuitItem.setCanBeLocked(baseItem, true);
                    JumpsuitItem.setHighVisibility(baseItem, false);
                case "cuffedplus:high_visibility_modifier":
                    JumpsuitItem.setHighVisibility(baseItem, true);
                default:
                    // throw new IllegalStateException("Oh no! Unexpected value: " + modKey);
            }

        } else if (additionItem.is(Items.NETHERITE_SCRAP)) {
            switch (Objects.requireNonNull(modKey).toString()) {
                case "cuffedplus:lock_modifier":
                    JumpsuitItem.setCanBeLocked(baseItem, false);
                    JumpsuitItem.setHighVisibility(baseItem, false);
                case "cuffedplus:high_visibility_modifier":
                    JumpsuitItem.setHighVisibility(baseItem, false);
                default:
            }
        } else {
            return ItemStack.EMPTY;
        }

        return baseItem;

    }

    @Override
    public boolean isTemplateIngredient(@NotNull ItemStack stack) {
        return template.test(stack);
    }

    @Override
    public boolean isBaseIngredient(@NotNull ItemStack stack) {
        return base.test(stack);
    }

    @Override
    public boolean isAdditionIngredient(@NotNull ItemStack stack) {
        return addition.test(stack);
    }

    @Override
    public boolean canCraftInDimensions(int w, int h) {
        return true;
    }

    @Override
    public @NotNull ItemStack getResultItem(@NotNull RegistryAccess access) {
        return ItemStack.EMPTY;
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