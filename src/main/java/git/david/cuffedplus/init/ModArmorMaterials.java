package git.david.cuffedplus.init;

import net.minecraft.Util;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;
import java.util.function.Supplier;

import static git.david.cuffedplus.CuffedPlusMain.MODID;

public enum ModArmorMaterials implements ArmorMaterial {

    ANKLE_MONITOR("ankle_monitor", 24, Util.make(new EnumMap<>(ArmorItem.Type.class), (ty) -> {
        ty.put(ArmorItem.Type.BOOTS, 4);
    }), 5, 15, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0F, 0.0F, () -> Ingredient.of(ModItems.ANKLE_MONITOR.get()));

    public static final StringRepresentable.EnumCodec<ArmorMaterials> CODEC = StringRepresentable.fromEnum(ArmorMaterials::values);
    private static final EnumMap<ArmorItem.Type, Integer> HEALTH_FUNCTION_FOR_TYPE = Util.make(new EnumMap<>(ArmorItem.Type.class), (p_266653_) -> {
        p_266653_.put(ArmorItem.Type.BOOTS, 13);
    });
    private final String name;
    private final int durability;
    private final int protectionFunction;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredient;

    ModArmorMaterials(String name, int durability, EnumMap<ArmorItem.Type, Integer> protectionTypeMap, int protectionFunction,
                      int enchantmentValue, SoundEvent soundEvent, float toughness, float knockbackRes, Supplier<Ingredient> repairMaterial) {
        this.name = name;
        this.durability = durability;
        this.protectionFunction = protectionFunction;
        this.enchantmentValue = enchantmentValue;
        this.sound = soundEvent;
        this.toughness = toughness;
        this.knockbackResistance = knockbackRes;
        this.repairIngredient = repairMaterial;
    }

    @Override
    public int getDurabilityForType(ArmorItem.Type pType) {
        return this.durability;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type pType) {
        return this.protectionFunction;
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public @NotNull SoundEvent getEquipSound() {
        return this.sound;
    }

    public @NotNull Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    public @NotNull String getName() {
        return MODID + ":" + this.name;
    }

    public float getToughness() {
        return this.toughness;
    }

    /**
     * Gets the percentage of knockback resistance provided by armor of the material.
     */
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }

    public String getSerializedName() {
        return MODID + ":" + this.name;
    }
}