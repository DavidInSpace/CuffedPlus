package git.david.cuffedplus.recipes;

import git.david.cuffedplus.init.ModRecipes;
import git.david.cuffedplus.items.item.base.RestraintItem;
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

public class CuffsModifierRecipe implements SmithingRecipe {
    public final ResourceLocation id;
    public final Ingredient template;
    public final Ingredient base;
    public final Ingredient addition;

    public CuffsModifierRecipe(ResourceLocation id, Ingredient template, Ingredient base, Ingredient addition) {
        this.id = id;
        this.template = template;
        this.base = base;
        this.addition = addition;
    }

    @Override
    public boolean matches(Container container, @NotNull Level level) {
        return template.test(container.getItem(0)) &&
                base.test(container.getItem(1)) &&
                addition.test(container.getItem(2));
    }

    @Override
    public @NotNull ItemStack assemble(Container container, @NotNull RegistryAccess access) {
        ItemStack modifierItem = container.getItem(0);
        ResourceLocation modKey = ForgeRegistries.ITEMS.getKey(modifierItem.getItem());

        ItemStack baseItem = container.getItem(1).copy();
        baseItem.setCount(1);

        ItemStack additionItem = container.getItem(2);

        if (additionItem.is(Items.DIAMOND)) {

            switch (Objects.requireNonNull(modKey).toString()) {
                case "cuffedplus:timer_modifier":
                    RestraintItem.enableTimer(baseItem, true);
                    break;
                case "cuffedplus:saturation_modifier":
                    RestraintItem.setSaturationModifier(baseItem, true);
                    break;
                case "cuffedplus:hunger_modifier":
                    RestraintItem.setHungerModifier(baseItem, RestraintItem.getHungerModifier(baseItem) + 1);
                    break;
                case "cuffedplus:anti_god_modifier":
                    if (RestraintItem.getAntiGodModifier(baseItem) < 3) {
                        RestraintItem.setAntiGodModifier(baseItem, RestraintItem.getAntiGodModifier(baseItem) + 1);
                    }
                    break;
                case "cuffedplus:jump_modifier":
                    RestraintItem.setJumpModifier(baseItem, true);
                    break;

                case "cuffedplus:can_be_broken_out_of":
                    RestraintItem.setCanBeBrokenOutOf(baseItem, true);
                    break;

                case "cuffedplus:is_lockpickable":
                    RestraintItem.setLockpickable(baseItem, true);
                    break;
                default:
            }

        } else if (additionItem.is(Items.NETHERITE_SCRAP)) {

            switch (modKey.toString()) {
                case "cuffedplus:saturation_modifier":
                    RestraintItem.setSaturationModifier(baseItem, false);
                    break;
                case "cuffedplus:hunger_modifier":
                    if (RestraintItem.getHungerModifier(baseItem) > 0) {
                        RestraintItem.setHungerModifier(baseItem, RestraintItem.getHungerModifier(baseItem) - 1);
                    }
                    break;
                case "cuffedplus:anti_god_modifier":
                    if (RestraintItem.getAntiGodModifier(baseItem) > 0) {
                        RestraintItem.setAntiGodModifier(baseItem, RestraintItem.getAntiGodModifier(baseItem) - 1);
                    }
                    break;
                case "cuffedplus:jump_modifier":
                    RestraintItem.setJumpModifier(baseItem, false);
                    break;
                case "cuffedplus:can_be_broken_out_of":
                    RestraintItem.setCanBeBrokenOutOf(baseItem, false);
                    break;
                case "cuffedplus:is_lockpickable":
                    RestraintItem.setLockpickable(baseItem, false);
                    break;
                default:
                    break;
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
    public ItemStack getResultItem(RegistryAccess access) {
        return ItemStack.EMPTY;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.CUFFS_MODIFIER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return RecipeType.SMITHING;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }
}