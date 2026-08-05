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

public class JumpsuitLayer extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {

    private static final ResourceLocation DCLASS_CLASSIC_TEXTURE = ResourceLocation.fromNamespaceAndPath(CuffedPlusMain.MODID, "textures/entity/uniforms/dclass_jumpsuit_classic.png");
    private static final ResourceLocation DCLASS_SLIM_TEXTURE = ResourceLocation.fromNamespaceAndPath(CuffedPlusMain.MODID, "textures/entity/uniforms/dclass_jumpsuit_slim.png");
    private static final ResourceLocation JUMPSUIT1_CLASSIC_TEXTURE = ResourceLocation.fromNamespaceAndPath(CuffedPlusMain.MODID, "textures/entity/uniforms/prison_jumpsuit1_classic.png");
    private static final ResourceLocation JUMPSUIT1_SLIM_TEXTURE = ResourceLocation.fromNamespaceAndPath(CuffedPlusMain.MODID, "textures/entity/uniforms/prison_jumpsuit1_slim.png");
    private static final ResourceLocation JUMPSUIT2_CLASSIC_TEXTURE = ResourceLocation.fromNamespaceAndPath(CuffedPlusMain.MODID, "textures/entity/uniforms/prison_jumpsuit2_classic.png");
    private static final ResourceLocation JUMPSUIT2_SLIM_TEXTURE = ResourceLocation.fromNamespaceAndPath(CuffedPlusMain.MODID, "textures/entity/uniforms/prison_jumpsuit2_slim.png");
    private static final ResourceLocation JUMPSUIT3_CLASSIC_TEXTURE = ResourceLocation.fromNamespaceAndPath(CuffedPlusMain.MODID, "textures/entity/uniforms/prison_jumpsuit3_classic.png");
    private static final ResourceLocation JUMPSUIT3_SLIM_TEXTURE = ResourceLocation.fromNamespaceAndPath(CuffedPlusMain.MODID, "textures/entity/uniforms/prison_jumpsuit3_slim.png");

    // Example overlay texture
    private static final ResourceLocation TARGET = ResourceLocation.fromNamespaceAndPath(CuffedPlusMain.MODID, "textures/entity/uniforms/backs/target.png");
    private static final ResourceLocation LETTER_A = ResourceLocation.fromNamespaceAndPath(CuffedPlusMain.MODID, "textures/entity/uniforms/backs/a.png");
    private static final ResourceLocation LETTER_B = ResourceLocation.fromNamespaceAndPath(CuffedPlusMain.MODID, "textures/entity/uniforms/backs/b.png");
    private static final ResourceLocation LETTER_C = ResourceLocation.fromNamespaceAndPath(CuffedPlusMain.MODID, "textures/entity/uniforms/backs/c.png");
    private static final ResourceLocation LETTER_D = ResourceLocation.fromNamespaceAndPath(CuffedPlusMain.MODID, "textures/entity/uniforms/backs/d.png");
    private static final ResourceLocation LETTER_E = ResourceLocation.fromNamespaceAndPath(CuffedPlusMain.MODID, "textures/entity/uniforms/backs/e.png");
    private static final ResourceLocation LETTER_F = ResourceLocation.fromNamespaceAndPath(CuffedPlusMain.MODID, "textures/entity/uniforms/backs/f.png");
    private static final ResourceLocation LETTER_G = ResourceLocation.fromNamespaceAndPath(CuffedPlusMain.MODID, "textures/entity/uniforms/backs/g.png");
    private static final ResourceLocation LETTER_H = ResourceLocation.fromNamespaceAndPath(CuffedPlusMain.MODID, "textures/entity/uniforms/backs/h.png");
    private static final ResourceLocation TEST_SUBJECT_BACK = ResourceLocation.fromNamespaceAndPath(CuffedPlusMain.MODID, "textures/entity/uniforms/backs/test_subject_text.png");
    private static final ResourceLocation INMATE_BACK = ResourceLocation.fromNamespaceAndPath(CuffedPlusMain.MODID, "textures/entity/uniforms/backs/inmate_text.png");
    private static final ResourceLocation BARS_BACK = ResourceLocation.fromNamespaceAndPath(CuffedPlusMain.MODID, "textures/entity/uniforms/backs/bars_back.png");
    private static final ResourceLocation DCLASS_BACK = ResourceLocation.fromNamespaceAndPath(CuffedPlusMain.MODID, "textures/entity/uniforms/backs/dclass_back.png");
    private static final ResourceLocation JUMPSUIT1_BACK = ResourceLocation.fromNamespaceAndPath(CuffedPlusMain.MODID, "textures/entity/uniforms/backs/jumpsuit1_back.png");
    private static final ResourceLocation JUMPSUIT2_BACK = ResourceLocation.fromNamespaceAndPath(CuffedPlusMain.MODID, "textures/entity/uniforms/backs/jumpsuit2_back.png");
    private static final ResourceLocation JUMPSUIT3_BACK = ResourceLocation.fromNamespaceAndPath(CuffedPlusMain.MODID, "textures/entity/uniforms/backs/jumpsuit3_back.png");

    private final ResourceLocation[] textures = {TARGET, BARS_BACK, INMATE_BACK, TEST_SUBJECT_BACK, LETTER_A, LETTER_B, LETTER_C, LETTER_D, LETTER_E, LETTER_F, LETTER_G, LETTER_H};

    public JumpsuitLayer(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> parent) {
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

        if (chest.is(ModItems.DCLASS_JUMPSUIT.get())) {
            baseTexture = slim ? DCLASS_SLIM_TEXTURE : DCLASS_CLASSIC_TEXTURE;
            /* if (chest.hasTag() && chest.getOrCreateTag().getByte("JumpsuitNumber") == 0) {
                overlayTexture = DCLASS_BACK;
            } */
        } else if (chest.is(ModItems.PRISON_JUMPSUIT_1.get())) {
            baseTexture = slim ? JUMPSUIT1_SLIM_TEXTURE : JUMPSUIT1_CLASSIC_TEXTURE;
            /* if (chest.hasTag() && chest.getOrCreateTag().getByte("JumpsuitNumber") == 0) {
                overlayTexture = JUMPSUIT1_BACK;
            }*/
        } else if (chest.is(ModItems.PRISON_JUMPSUIT_2.get())) {
            baseTexture = slim ? JUMPSUIT2_SLIM_TEXTURE : JUMPSUIT2_CLASSIC_TEXTURE;
            /*  if (chest.hasTag() && chest.getOrCreateTag().getByte("JumpsuitNumber") == 0) {
                overlayTexture = JUMPSUIT2_BACK;
            }*/
        } else if (chest.is(ModItems.PRISON_JUMPSUIT_3.get())) {
            baseTexture = slim ? JUMPSUIT3_SLIM_TEXTURE : JUMPSUIT3_CLASSIC_TEXTURE;
            /*  if (chest.hasTag() && chest.getOrCreateTag().getByte("JumpsuitNumber") == 0) {
                overlayTexture = JUMPSUIT3_BACK;
            }*/
        }

        if (baseTexture == null) return;

        if (chest.hasTag() && chest.getOrCreateTag().getByte("JumpsuitNumber") != 0 && chest.getOrCreateTag().getByte("JumpsuitNumber") < 11) {
            overlayTexture = textures[chest.getOrCreateTag().getByte("JumpsuitNumber") - 1];
        }

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
