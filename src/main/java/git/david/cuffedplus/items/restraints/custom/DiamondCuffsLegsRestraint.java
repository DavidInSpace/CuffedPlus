package git.david.cuffedplus.items.restraints.custom;

import java.util.Random;

import javax.annotation.Nonnull;

import com.lazrproductions.cuffed.CuffedMod;
import com.lazrproductions.cuffed.api.CuffedAPI;
import com.lazrproductions.cuffed.cap.base.IRestrainableCapability;
import com.lazrproductions.cuffed.entity.animation.ArmRestraintAnimationFlags;
import com.lazrproductions.cuffed.entity.animation.LegRestraintAnimationFlags;
import com.lazrproductions.cuffed.init.ModStatistics;
import com.lazrproductions.cuffed.restraints.base.AbstractLegRestraint;
import com.lazrproductions.cuffed.restraints.base.IBreakableRestraint;
import com.lazrproductions.cuffed.restraints.base.IEnchantableRestraint;
import com.lazrproductions.cuffed.restraints.base.RestraintType;
import com.lazrproductions.cuffed.restraints.client.RestraintModelInterface;

import com.lazrproductions.lazrslib.client.screen.ScreenUtilities;
import com.lazrproductions.lazrslib.client.screen.base.BlitCoordinates;
import com.lazrproductions.lazrslib.client.screen.base.ScreenTexture;
import git.david.cuffedplus.CuffedPlusMain;
import git.david.cuffedplus.init.ModItems;
import git.david.cuffedplus.init.ModModelLayers;
import git.david.cuffedplus.init.ModRestraints;
import git.david.cuffedplus.init.ModSounds;
import git.david.cuffedplus.items.restraints.client.model.DiamondCuffsLegsModel;
import com.mojang.blaze3d.platform.Window;

import net.minecraft.client.Options;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import static git.david.cuffedplus.misc.Icons.DIAMOND_CHAIN_ICON;

/*

    This is the Restraint class for the Diamond Handcuffs when they're applied to the legs.

    Restraint classes may be big and intimidating, but they... well they are.

    First off, leg restraints must extend the AbstractLegRestraint class, which itself extends the AbstractRestraint class.
    I recommend taking a look at these classes to see what they do, most of the heavy lifting comes from the AbstractRestraint class.

    1. CONSTRUCTORS
        First in the class are the constructors, there are two different constructors that MUST be implemented in every restraint class:

            public MyLegsRestraint()
            public MyLegsRestraint(ItemStack stack, ServerPlayer player, ServerPlayer captor)

        The first constructor is only used to register the restraint type.
        The second constructor is used to create instances of the restraint.

    2. RESTRAINT PROPERTIES
        After the constructors comes the many restraint properties getters.

            public ResourceLocation getId() // Get the ID of this restraint, should just return the ResourceLocation of the restraint in your restraint registry.

            public String getActionBarLabel() // The translatable path of the text to be shown.
            public String getName() // The translatable path of the name of this restraint.

            public Item getItem() // Get the item that is used to equip this restraint.
            public Item getKeyItem() // Get the item that is used to unequip this restraint.

            public ArmRestraintAnimationFlags getArmAnimationFlags() // Get how to animate the player's arms with this restraint on.
            public LegRestraintAnimationFlags getLegAnimationFlags() // Get how to animate the player's legs with this restraint on.

            public SoundEvent getEquipSound() // The sound to play when this restraint is equipped.
            public SoundEvent getUnequipSound() // The sound to play when this restraint is unequipped.

            public boolean AllowBreakingBlocks() // Get whether or not to allow mining blocks with this restraint equipped.
            public boolean AllowItemUse() // Get whether or not to allow item use, block interactions, and inventory access with this restraint equipped.
            public boolean AllowMovement() // Get whether or not to allow movement with this restraint equipped.
            public boolean AllowJumping() // Get whether or not to allow jumping with this restraint equipped.

            public boolean canBeBrokenOutOf() // Get whether or not this restraint can be broken out of. NOTE: Breakable restraints require some more functions
            public boolean getLockpickable() // Get whether or not this restraint is lockpickable.
            public int getLockpickingProgressPerPick() // Get the progress gained per pick when lockpicking this restraint.
            public int getLockpickingSpeedIncreasePerPick() // Get the speed increase per pick when lockpicking this restraint.

        These functions are used to define the behaviors of the restraint.

    3. EVENTS
        There are many event functions in the AbstractRestraint class that allows the restraint to do things when events happen with the restraint equipped:

            public void onTickServer(ServerPlayer player) // Called each tick server-side.

            public void onTickClient(Player player) // Called each tick client-side.

            public void onEquippedServer(ServerPlayer player, ServerPlayer captor) // Called when the restraint is equipped server-side.

            public void onEquippedClient(Player player, Player captor) // Called when the restraint is equipped client-side.

            public void onUnequippedServer(ServerPlayer player) // Called when the restraint is unequipped server-side.

            public void onUnequippedClient(Player player) // Called when the restraint is unequipped client-side.

            public abstract void onLoginServer(ServerPlayer player) // Called when a player restrained with this restraint logs into the server, only server-side.

            public abstract void onLoginClient(Player player) // Called when a player restrained with this restraint logs into the server, only client-side.

            public abstract void onLogoutServer(ServerPlayer player) // Called when a player restrained with this restraint logs out of the server, only server-side.

            public abstract void onLogoutClient(Player player) // Called when a player restrained with this restraint logs out of the server, only client-side.

            public abstract void onDeathServer(ServerPlayer player) // Called server-side when a player restraint with this restraint dies.

            public abstract void onDeathClient(Player player) // Called client-side when a player restraint with this restraint dies.

            public abstract void onJumpServer(ServerPlayer player) // Called when a player restraint with this restraint jumps.

            public abstract void onJumpClient(Player player) // Called client-side when a player restraint with this restraint jumps.

            public abstract float onLandServer(ServerPlayer player, float distance, float damageMultiplier) // Called server-side when a player restraint with this restraint lands after falling.

            public abstract void onLandClient(Player player, float distance, float damageMultiplier) // Called client-side when a player restraint with this restraint lands after falling.

        As you can see, every even has a client-side and server-side method. Client-side functions are only called on the client's instance of Minecraft when that event happens
        while the server-side functions are called on just the server's instance of Minecraft. If you have knowledge of networking this should come be easy to understand, but I've
        tried to also make it easy for inexperienced devs as well. It's important to remember which side you're working on as it matters which side most of Minecraft's functions take
        place on. (for example, almost none of the functions in Minecraft's Level class can be used client-side.)

    4. CLIENT-SIDE METHODS
        The following methods are only to be used client-side.

            public void renderOverlay(Player player, GuiGraphics graphics, float partialTick, Window window) // Called every frame to draw overlays on the screen when restrained.

            public void onKeyInput(Player player, int keyCode, int action) // Called when the client presses a key while restrained.

            public void onMouseInput(Player player, int keyCode, int action) // Called when the client presses a mouse button while restrained.

            @Nonnull
            @OnlyIn(Dist.CLIENT)
            @Override
            public RestraintModelInterface getModelInterface() // Get the RestraintModelInterface for this restraint to render a model on the player when this restraint is equipped.

        @OnlyIn(Dist.CLIENT) should always be used for getModelInterface() to avoid errors with client-side classes loading on servers.

    5. MODEL INTERFACE
        To render a model for your restraint on the player, you must add a child class to the your restraint's class that extends RestraintModelInterface.

            public Class<? extends HumanoidModel<? extends LivingEntity>> getRenderedModel() // Get the model to render on the player

            public ModelLayerLocation getRenderedModelLayer() // Get the model layer to render

            public ResourceLocation getRenderedModelTexture() // Get the location of the texture to render on the model

        Use these getters to get the various client-side only properties needed to render the model, which will be done automatically if these getters are setup correctly.

    6. IMPLEMENTABLE INTERFACES
        There are a couple built-in interfaces that your restraint can implement.

            IBreakableRestraint
            IEnchantableRestraint

        It's not enough to just implement either one of these interfaces to make the restraints enchantable or breakable, we must do some extra work to make them function properly.

        -> IBreakableRestraint
            To make your restraint breakable, your restraint needs to implement IBreakableRestraint which has the following functions:

                public SoundEvent getBreakSound() // Get the SoundEvent to play when the restraint is broken.

                public int getMaxDurability() // Get the maximum amount of durability of this restraint.

                public boolean isKeyToAttemptBreak(int keyCode, Options options) // Get whether or not the given keycode is valid for attempting to break out.

                public boolean requireAlternateKeysToAttemptBreak() // Get whether or not to require different keys to be pressed to make progress breaking out.

                public boolean dropItemOnBroken() // Whether or not to drop the restraint item (with the proper remaining durability) when broken out of.

                public boolean canBeBrokenOutOf() // Whether or not the restraint can currently be broken of.

                public int getDurability() // Get the current durability of this restraint.

                public void attemptToBreak(Player player, int keyCode, int action, Options options) // Called when an attempt to break out is made.

                public void setDurability(ServerPlayer player, int value) // Set the current durability of this restraint.

                public void incrementDurability(ServerPlayer player, int value) // Increment the durability of this restraint.

                public void onBrokenServer(ServerPlayer player) // Called on server-side when this restraint is broken.

                public void onBrokenClient(Player player) // Called client-side when this restraint is broken.

            It is important when making a breakable restraint, to include all the necessary functions.
            Please examine this class's "Breakable Restraint Management" to see how breakable restraints are implemented,
            also see onTickClient() to see how the break cooldown is ticked down.

            Lastly, for breakable restraints there is a progress bar and the chain icon is different in the overlays. You can paste the following snippet into
            your renderOverlay() function to display the chain and progress bar.

                static final ResourceLocation CUFFED_WIDGETS = new ResourceLocation(CuffedMod.MODID, "textures/gui/widgets.png");
                static final ScreenTexture CHAIN_ICON = new ScreenTexture(CUFFED_WIDGETS, 44, 24, 16, 16, 192, 192);
                public void renderOverlay(Player player, GuiGraphics graphics, float partialTick, Window window) {
                    super.renderOverlay(player, graphics, partialTick, window);

                    // Display Icon and chain overlay
                    float f = (Mth.clamp(breakCooldown / 10, 0, 1)+1);
                    graphics.setColor(f, f, f, 1);

                    int iconWidth = (int) (16 * 1.75f);
                    int iconHeight = (int) (16 * 1.75f);
                    int x = (window.getGuiScaledWidth() / 2) - (iconWidth / 2);
                    int y = (window.getGuiScaledHeight() / 2) - (iconHeight) - 65; // 65 pixels is where the arm icon is rendered, it's 30 for legs and 100 for the head.

                    ScreenUtilities.drawTexture(graphics, new BlitCoordinates(x, y, iconWidth, iconHeight), CHAIN_ICON);
                    graphics.setColor(1, 1, 1, 1);

                    // Display break progress bar
                    float p = Mth.clamp((float)clientSidedDurability / (float)getMaxDurability(), 0, 1);
                    ScreenUtilities.drawGenericProgressBar(graphics, new BlitCoordinates(x, y+iconHeight-2, iconWidth, iconHeight), p);
                }

            NOTE: Lazr's Lib is required for the way Cuffed renders overlays, so consider your own solution, or implementing Lazr's Lib.

        -> IEnchantableRestraint
            To make your restraint enchantable, your restraint needs to implement IEnchantableRestraint which has the following functions:

                public ListTag getEnchantments(); // Get all of the enchantments on this restraint.

                public void setEnchantments(ListTag tag); // Set the enchantments on this restraint.

                public boolean hasEnchantment(Enchantment enchantment) // Get whether or not this restraint has the given enchantment.

                public int getEnchantmentLevel(Enchantment enchantment) // Get the amplifier of the given enchantment.

                public void enchant(Enchantment enchantment, int value) // Apply an enchantment to this restraint.

            The IEnchantableRestraint interface is not as difficult to implement as the IBreakableRestraint, it only requires the following snippet and a line
            inserted into the first constructor:

                ListTag enchantments;
                public ListTag getEnchantments() {
                    return enchantments;
                }
                public void setEnchantments(ListTag tag) {
                    enchantments = tag;
                }

                public boolean hasEnchantment(Enchantment enchantment) {
                    ResourceLocation resourcelocation = EnchantmentHelper.getEnchantmentId(enchantment);
                    for (int i = 0; i < enchantments.size(); ++i) {
                        CompoundTag compoundtag = enchantments.getCompound(i);
                        ResourceLocation resourcelocation1 = EnchantmentHelper.getEnchantmentId(compoundtag);
                        if (resourcelocation1 != null && resourcelocation1.equals(resourcelocation)) {
                            return true;
                        }
                    }
                    return false;
                }
                public int getEnchantmentLevel(Enchantment enchantment) {
                    ResourceLocation resourcelocation = EnchantmentHelper.getEnchantmentId(enchantment);
                    for (int i = 0; i < enchantments.size(); ++i) {
                        CompoundTag compoundtag = enchantments.getCompound(i);
                        ResourceLocation resourcelocation1 = EnchantmentHelper.getEnchantmentId(compoundtag);
                        if (resourcelocation1 != null && resourcelocation1.equals(resourcelocation)) {
                            return EnchantmentHelper.getEnchantmentLevel(compoundtag);
                        }
                    }
                    return 0;
                }
                public void enchant(Enchantment enchantment, int value) {
                    ResourceLocation l = EnchantmentHelper.getEnchantmentId(enchantment);
                    enchantments.add(EnchantmentHelper.storeEnchantment(l, value));
                }

                public MyRestraint() {
                    enchantments = new ListTag();
                }

            The IEnchantableRestraint interface's functions must be implement exactly like this to make your restraint enchantable.

        That is all of the interfaces that can be implemented for restraints. I may look into making these interfaces more automatic
        in the future so that you wont have to rewrite all these functions each time.

    Lastly after this class is complete you need to register the restraint and the restraint items in your respective registries (see ModRestraints)
    and it should work in the game.

*/

public class DiamondCuffsLegsRestraint extends AbstractLegRestraint implements IBreakableRestraint, IEnchantableRestraint {

    private final ItemStack sourceStack;

    public DiamondCuffsLegsRestraint() {
        enchantments = new ListTag();
        this.sourceStack = ItemStack.EMPTY;
    }
    public DiamondCuffsLegsRestraint(ItemStack stack, ServerPlayer player, ServerPlayer captor) {
        super(stack, player, captor);
        this.durability = getMaxDurability() - stack.getDamageValue();
        this.sourceStack = stack;

    }

    // #region Restraint Properties

    public static final ResourceLocation ID = ModRestraints.DIAMOND_CUFFS_LEGS.getId();
    public ResourceLocation getId() {
        return ID;
    }

    public String getActionBarLabel() {
        return "info.cuffed.restraints.legcuffs.action_bar";
    }
    public String getName() {
        return "info.cuffed.restraints.legcuffs.name";
    }

    public static final Item ITEM =  ModItems.DIAMOND_CUFFS.get();
    public Item getItem() {
        return ITEM;
    }
    public static final Item KEY = ModItems.DIAMOND_CUFFS_KEY.get();
    public Item getKeyItem() {
        return KEY;
    }

    public static final LegRestraintAnimationFlags LEG_ANIMATION_FLAGS = LegRestraintAnimationFlags.NONE;
    public ArmRestraintAnimationFlags getArmAnimationFlags() {
        return ArmRestraintAnimationFlags.NONE;
    }
    public LegRestraintAnimationFlags getLegAnimationFlags() {
        return LEG_ANIMATION_FLAGS;
    }

    public SoundEvent getEquipSound() {
        return ModSounds.DIAMOND_CUFFS_EQUIP;
    }
    public SoundEvent getUnequipSound() {
        return SoundEvents.ARMOR_EQUIP_CHAIN;
    }

    private boolean getBooleanTag(String key, boolean defaultValue) {
        if (sourceStack == null || sourceStack.isEmpty()) return defaultValue;
        CompoundTag tag = sourceStack.getTag();
        return tag != null && tag.contains(key) ? tag.getBoolean(key) : defaultValue;
    }

    @Override
    public boolean AllowBreakingBlocks() {
        return getBooleanTag("AllowBreakingBlocks", true);
    }

    @Override
    public boolean AllowItemUse() {
        return getBooleanTag("AllowItemUse", true);
    }

    @Override
    public boolean AllowMovement() {
        return getBooleanTag("AllowMovement", false);
    }

    @Override
    public boolean AllowJumping() {
        return getBooleanTag("AllowJumping", false);
    }

    @Override
    public boolean canBeBrokenOutOf() {
        return getBooleanTag("CanBeBrokenOutOf", true);
    }

    @Override
    public boolean getLockpickable() {
        return getBooleanTag("Lockpickable", true);
    }

    public int getLockpickingProgressPerPick() {
        return 3;
    }
    public int getLockpickingSpeedIncreasePerPick() {
        return 2;
    }
    // #endregion

    // #region Events

    public void onTickServer(ServerPlayer player) {
        super.onTickServer(player);
    }

    public void onTickClient(Player player) {
        super.onTickClient(player);

        if(breakCooldown>0)
            breakCooldown--;
    }

    public void onEquippedServer(ServerPlayer player, ServerPlayer captor) {
        super.onEquippedServer(player, captor);
    }

    public void onEquippedClient(Player player, Player captor) {
        super.onEquippedClient(player, captor);
    }

    public void onUnequippedServer(ServerPlayer player) {
        super.onUnequippedServer(player);
    }

    public void onUnequippedClient(Player player) {
        super.onUnequippedClient(player);
    }

    public void onLoginServer(ServerPlayer player) {
    }

    public void onLoginClient(Player player) {
    }

    public void onLogoutServer(ServerPlayer player) {
    }

    public void onLogoutClient(Player player) {
    }

    public void onDeathServer(ServerPlayer player) {
    }

    public void onDeathClient(Player player) {
    }

    public void onJumpServer(ServerPlayer player) {
    }

    public void onJumpClient(Player player) {
    }

    public float onLandServer(ServerPlayer player, float distance, float damageMultiplier) {
        return 1;
    }

    public void onLandClient(Player player, float distance, float damageMultiplier) {
    }

    // #endregion

    // #region Client-Side operations

    int lastBarIndex = 0;
    public void renderOverlay(Player player, GuiGraphics graphics, float partialTick, Window window) {
        super.renderOverlay(player, graphics, partialTick, window);

        // Display Icon and chain overlay
        float f = (Mth.clamp(breakCooldown / 10, 0, 1)+1);
        graphics.setColor(f, f, f, 1);

        int iconWidth = (int) (16 * 1.75f);
        int iconHeight = (int) (16 * 1.75f);
        int x = (window.getGuiScaledWidth() / 2) - (iconWidth / 2);
        int y = (window.getGuiScaledHeight() / 2) - (iconHeight) - 65; // 65 pixels is where the arm icon is rendered, it's 30 for legs and 100 for the head.

        ScreenUtilities.drawTexture(graphics, new BlitCoordinates(x, y, iconWidth, iconHeight), DIAMOND_CHAIN_ICON);
        graphics.setColor(1, 1, 1, 1);

        // Display break progress bar
        float p = Mth.clamp((float)clientSidedDurability / (float)getMaxDurability(), 0, 1);
        ScreenUtilities.drawGenericProgressBar(graphics, new BlitCoordinates(x, y+iconHeight-2, iconWidth, iconHeight), p);
    }


    public void onKeyInput(Player player, int keyCode, int action) {
        super.onKeyInput(player, keyCode, action);
    }

    public void onMouseInput(Player player, int keyCode, int action) {
        super.onMouseInput(player, keyCode, action);
    }

    @Nonnull
    @OnlyIn(Dist.CLIENT)
    @Override
    public RestraintModelInterface getModelInterface() {
        return new DiamondCuffsLegsRestraintModelInterface();
    }

    // #endregion

    // #region Breakable Restraint Management

    public SoundEvent getBreakSound() {
        return SoundEvents.ITEM_BREAK;
    }

    public boolean isKeyToAttemptBreak(int keyCode, Options options) {
        return keyCode == options.keyLeft.getKey().getValue() || keyCode == options.keyRight.getKey().getValue();
    }

    public boolean requireAlternateKeysToAttemptBreak() {
        return true;
    }

    public int getMaxDurability() {
        return ModItems.DIAMOND_CUFFS.get().getMaxDamage(ModItems.DIAMOND_CUFFS.get().getDefaultInstance());
    }

    public boolean dropItemOnBroken() {
        return true;
    }

    /** Changed only server-side. changes are synced to client. */
    private int durability = 100;

    public int getDurability() {
        return durability;
    }

    float breakCooldown = 4;
    int lastKeyPressed = -1;

    public void attemptToBreak(Player player, int keyCode, int action, Options options) {
        if (breakCooldown <= 0) {
            if (isKeyToAttemptBreak(keyCode, options)) {
                if (!requireAlternateKeysToAttemptBreak() || keyCode != lastKeyPressed) {
                    Random r = new Random();
                    double chance = 0.5f;
                    double cooldownMultiplier = 1;
                    if(this instanceof IEnchantableRestraint && hasEnchantment(Enchantments.UNBREAKING)) {
                        double d = getEnchantmentLevel(Enchantments.UNBREAKING) / 3d;
                        chance = 0.5f;//((MathUtilities.invert01(d / 3d) * 0.7d) + 0.3d)  * 0.5f;
                        cooldownMultiplier = 1 + d;
                    }
                    if (r.nextDouble() < chance) {
                        lastKeyPressed = keyCode;
                        player.playNotifySound(SoundEvents.CHAIN_STEP, SoundSource.PLAYERS, 1f,
                                Mth.nextFloat(player.getRandom(), 0.9f, 1.1f));

                        CuffedAPI.Networking.sendRestraintUtilityPacketToServer(getType(), 102, -1, false, 0D, "");

                        breakCooldown = r.nextInt(20) + Mth.floor(20 * cooldownMultiplier);
                    }
                }
            }
        }
    }

    public void setDurability(ServerPlayer player, int value) {
        if (value > getMaxDurability())
            value = getMaxDurability();
        if (value < 0)
            value = 0;

        durability = value;
        CuffedAPI.Networking.sendRestraintUtilityPacketToClient(player, getType(), 101, durability, false, 0, "");

        if (durability <= 0) {
            onBrokenServer(player);
        }
    }

    public void incrementDurability(ServerPlayer player, int value) {
        int newValue = getDurability() + value;
        setDurability(player, newValue);
    }

    public void onBrokenServer(ServerPlayer player) {
        CuffedAPI.Networking.sendRestraintUtilityPacketToClient(player, getType(), 103, 0, false, 0, "");

        Random random = new Random();
        player.level().playSound(null, player.blockPosition(), getBreakSound(), SoundSource.PLAYERS, 0.8f,
                (random.nextFloat() * 0.2f) + 0.9f);

        ModStatistics.awardRestraintBroken(player, this);

        if (dropItemOnBroken()) {
            ItemStack stack = this.saveToItemStack();
            stack.setDamageValue(stack.getMaxDamage() - 1); // instead of 0 durability
            ItemEntity e = new ItemEntity(player.level(), player.getX(), player.getY() + 0.6D, player.getZ(), stack);
            e.setDefaultPickUpDelay();
            player.level().addFreshEntity(e);
        }

        IRestrainableCapability cap = CuffedAPI.Capabilities.getRestrainableCapability(player);
        if (getType() == RestraintType.Arm)
            cap.setArmRestraintWithoutWarning(player, null);
        else
            cap.setLegRestraintWithoutWarning(player, null);

    }

    public void onBrokenClient(Player player) {
    }

    // #endregion

    // #region Enchantable Restraint Management

    ListTag enchantments;

    public ListTag getEnchantments() {
        return enchantments;
    }
    public void setEnchantments(ListTag tag) {
        enchantments = tag;
    }

    public boolean hasEnchantment(Enchantment enchantment) {
        ResourceLocation resourcelocation = EnchantmentHelper.getEnchantmentId(enchantment);
        for (int i = 0; i < enchantments.size(); ++i) {
            CompoundTag compoundtag = enchantments.getCompound(i);
            ResourceLocation resourcelocation1 = EnchantmentHelper.getEnchantmentId(compoundtag);
            if (resourcelocation1 != null && resourcelocation1.equals(resourcelocation)) {
                return true;
            }
        }
        return false;
    }
    public int getEnchantmentLevel(Enchantment enchantment) {
        ResourceLocation resourcelocation = EnchantmentHelper.getEnchantmentId(enchantment);
        for (int i = 0; i < enchantments.size(); ++i) {
            CompoundTag compoundtag = enchantments.getCompound(i);
            ResourceLocation resourcelocation1 = EnchantmentHelper.getEnchantmentId(compoundtag);
            if (resourcelocation1 != null && resourcelocation1.equals(resourcelocation)) {
                return EnchantmentHelper.getEnchantmentLevel(compoundtag);
            }
        }
        return 0;
    }
    public void enchant(Enchantment enchantment, int value) {
        ResourceLocation l = EnchantmentHelper.getEnchantmentId(enchantment);
        enchantments.add(EnchantmentHelper.storeEnchantment(l, value));
    }
    // #endregion

    @OnlyIn(Dist.CLIENT)
    public static class DiamondCuffsLegsRestraintModelInterface extends RestraintModelInterface {
        @SuppressWarnings("unchecked")
        static final Class<? extends HumanoidModel<? extends LivingEntity>> MODEL_CLASS = (Class<? extends HumanoidModel<? extends LivingEntity>>)(Class<?>) DiamondCuffsLegsModel.class;
        static final ModelLayerLocation MODEL_LAYER = ModModelLayers.DIAMOND_CUFFS_LEGS_LAYER;
        static final ResourceLocation MODEL_TEXTURE = new ResourceLocation(CuffedPlusMain.MODID, "textures/entity/diamond_cuffs.png");

        @Override
        public Class<? extends HumanoidModel<? extends LivingEntity>> getRenderedModel() {
            return MODEL_CLASS;
        }
        @Override
        public ModelLayerLocation getRenderedModelLayer() {
            return MODEL_LAYER;
        }
        @Override
        public ResourceLocation getRenderedModelTexture() {
            return MODEL_TEXTURE;
        }
    }
}