package git.david.cuffedplus.items.restraints.custom;


import com.lazrproductions.cuffed.entity.animation.ArmRestraintAnimationFlags;
import com.lazrproductions.cuffed.entity.animation.LegRestraintAnimationFlags;
import com.lazrproductions.cuffed.restraints.base.AbstractHeadRestraint;
import com.lazrproductions.cuffed.restraints.client.RestraintModelInterface;
import git.david.cuffedplus.CuffedPlusMain;
import git.david.cuffedplus.init.ModItems;
import git.david.cuffedplus.init.ModModelLayers;
import git.david.cuffedplus.items.restraints.client.model.AnkleMonitorModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

public class AnkleMonitorDisabled extends AbstractHeadRestraint {

    //static final ResourceLocation CUFFED_WIDGETS = new ResourceLocation(CuffedPlusMain.MODID, "textures/gui/widgets.png");
    //static final ResourceLocation BUNDLE_TEXTURE = new ResourceLocation(CuffedMod.MODID, "textures/gui/bundle_overlay.png");

    // static final ScreenTexture CHAIN_ICON = new ScreenTexture(CUFFED_WIDGETS, 44, 24, 16, 16, 192, 192);
    //    static final ScreenTexture BUNDLE_OVERLAY = new ScreenTexture(BUNDLE_TEXTURE, 0, 0, 32, 18, 32, 18);


    public AnkleMonitorDisabled() {

    }

    public AnkleMonitorDisabled(ItemStack stack, ServerPlayer player, ServerPlayer captor) {
        super(stack, player, captor);
    }

    @Override
    public ResourceLocation getId() {
        return null;
    }

    @Override
    public boolean canEquipRestraintItem(ItemStack stack, ServerPlayer player, ServerPlayer captor) {
     /*   if (BundleItem.getFullnessDisplay(stack) <= 0) {
            ModStatistics.awardRestraintItemUsed(captor, stack);
            return true;
        }
        return super.canEquipRestraintItem(stack, player, captor); */
        return true;
    }

    // #region Restraint Properties

 //   public static final ResourceLocation ID = ModRestraints.ANKLE_MONITOR.getId();
   // public ResourceLocation getId() {
     //   return ID;
    // }

    public String getActionBarLabel() {
        return "";
    }
    public String getName() {
        return "";
    }

//    public static final Item ITEM =  ModItems.ANKLE_MONITOR.get();
    public Item getItem() {
        //        return ITEM;
        return Items.AIR;
    }
    public static final Item KEY = null;
    public Item getKeyItem() {
        return KEY;
    }

    public SoundEvent getEquipSound() {
        return SoundEvents.CHAIN_BREAK;
    }
    public SoundEvent getUnequipSound() {
        return SoundEvents.CHAIN_STEP;
    }

    public boolean AllowBreakingBlocks() {
        return true;
    }
    public boolean AllowItemUse() {
        return true;
    }
    public boolean AllowMovement() {
        return true;
    }
    public boolean AllowJumping() {
        return true;
    }

    public boolean canBeBrokenOutOf() {
        return false;
    }
    public boolean getLockpickable() {
        return false;
    }
    public int getLockpickingProgressPerPick() {
        return 5;
    }
    public int getLockpickingSpeedIncreasePerPick() {
        return 0;
    }


    public ArmRestraintAnimationFlags getArmAnimationFlags() {
        return ArmRestraintAnimationFlags.NONE;
    }
    public LegRestraintAnimationFlags getLegAnimationFlags() {
        return LegRestraintAnimationFlags.NONE;
    }
    // #endregion

    // #region Events

    public void onTickServer(ServerPlayer player) {
        super.onTickServer(player);
    }

    public void onTickClient(Player player) {
        super.onTickClient(player);
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

    @Override
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


    public void onKeyInput(Player player, int keyCode, int action) {
    //    super.onKeyInput(player, keyCode, action);
    }

    public void onMouseInput(Player player, int keyCode, int action) {
    //    super.onMouseInput(player, keyCode, action);
    }







    @Nonnull
    @OnlyIn(Dist.CLIENT)
    public RestraintModelInterface getModelInterface() {
        return new AnkleMonitorModelInterface();
    }

    // #endregion

    @OnlyIn(Dist.CLIENT)
    public static class AnkleMonitorModelInterface extends RestraintModelInterface {


        static final Class<? extends HumanoidModel<? extends LivingEntity>> MODEL_CLASS = (Class<? extends HumanoidModel<? extends LivingEntity>>)(Class<?>) AnkleMonitorModel.class;
        static final ModelLayerLocation MODEL_LAYER = ModModelLayers.ANKLE_MONITOR_LAYER;
        static final ResourceLocation MODEL_TEXTURE = new ResourceLocation(CuffedPlusMain.MODID, "textures/entity/ankle_monitor_dirty.png");

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
