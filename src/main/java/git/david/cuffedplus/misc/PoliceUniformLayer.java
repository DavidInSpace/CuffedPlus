package git.david.cuffedplus.misc;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import git.david.cuffedplus.CuffedPlusMain;
import git.david.cuffedplus.init.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import static net.minecraft.client.model.geom.ModelLayers.PLAYER;
import static net.minecraft.client.model.geom.ModelLayers.PLAYER_SLIM;

public class PoliceUniformLayer extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {

    private static final ResourceLocation POLICE_UNIFORM1_CLASSIC_TEXTURE = ResourceLocation.fromNamespaceAndPath(CuffedPlusMain.MODID, "textures/entity/uniforms/police_uniform1_classic.png");
    private static final ResourceLocation POLICE_UNIFORM1_SLIM_TEXTURE = ResourceLocation.fromNamespaceAndPath(CuffedPlusMain.MODID, "textures/entity/uniforms/police_uniform1_slim.png");
    //private static final ResourceLocation POLICE_HAT1_TEXTURE = ResourceLocation.fromNamespaceAndPath(CuffedPlusMain.MODID, "textures/entity/uniforms/police_hat1.png");

    public PoliceUniformLayer(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> parent) {
        super(parent);
    }

    @Override
    public void render(@NotNull PoseStack poseStack, @NotNull MultiBufferSource buffer, int packedLight,
                       @NotNull AbstractClientPlayer player, float limbSwing, float limbSwingAmount,
                       float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {

        ItemStack chest = player.getItemBySlot(EquipmentSlot.CHEST);
        boolean slim = "slim".equals(player.getModelName());

        ResourceLocation baseTexture = null;
        ResourceLocation overlayTexture = null;

        if (chest.is(ModItems.POLICE_UNIFORM_1.get())) {
            baseTexture = slim ? POLICE_UNIFORM1_SLIM_TEXTURE : POLICE_UNIFORM1_CLASSIC_TEXTURE;
        }

        if (baseTexture == null) return;

        PlayerModel<AbstractClientPlayer> model = new PlayerModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(slim ? PLAYER_SLIM : PLAYER), slim);

        getParentModel().copyPropertiesTo(model);
        model.setupAnim(player, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

        VertexConsumer baseVertex = buffer.getBuffer(RenderType.entityCutoutNoCull(baseTexture));
        model.renderToBuffer(poseStack, baseVertex, packedLight, OverlayTexture.NO_OVERLAY, 1F, 1F, 1F, 1F);

        if (overlayTexture != null) {
            VertexConsumer overlayVertex = buffer.getBuffer(RenderType.entityTranslucent(overlayTexture));
            model.renderToBuffer(poseStack, overlayVertex, packedLight, OverlayTexture.NO_OVERLAY, 1F, 1F, 1F, 1F);
        }
    }
}
