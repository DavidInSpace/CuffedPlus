package git.david.cuffedplus;

import git.david.cuffedplus.utils.GeneralUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import org.jetbrains.annotations.NotNull;
import git.david.cuffedplus.init.ModItems;

import static net.minecraft.client.model.geom.ModelLayers.PLAYER;
import static net.minecraft.client.model.geom.ModelLayers.PLAYER_SLIM;

public class JumpsuitLayer extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {





    private static final ResourceLocation DCLASS_CLASSIC_TEXTURE = new ResourceLocation(CuffedPlusMain.MODID, "textures/entity/uniforms/dclass_jumpsuit_classic.png");
    private static final ResourceLocation DCLASS_SLIM_TEXTURE = new ResourceLocation(CuffedPlusMain.MODID, "textures/entity/uniforms/dclass_jumpsuit_slim.png");
    private static final ResourceLocation JUMPSUIT1_CLASSIC_TEXTURE = new ResourceLocation(CuffedPlusMain.MODID, "textures/entity/uniforms/prison_jumpsuit1_classic.png");
    private static final ResourceLocation JUMPSUIT1_SLIM_TEXTURE = new ResourceLocation(CuffedPlusMain.MODID, "textures/entity/uniforms/prison_jumpsuit1_slim.png");
    private static final ResourceLocation JUMPSUIT2_CLASSIC_TEXTURE = new ResourceLocation(CuffedPlusMain.MODID, "textures/entity/uniforms/prison_jumpsuit2_classic.png");
    private static final ResourceLocation JUMPSUIT2_SLIM_TEXTURE = new ResourceLocation(CuffedPlusMain.MODID, "textures/entity/uniforms/prison_jumpsuit2_slim.png");
    private static final ResourceLocation JUMPSUIT3_CLASSIC_TEXTURE = new ResourceLocation(CuffedPlusMain.MODID, "textures/entity/uniforms/prison_jumpsuit3_classic.png");
    private static final ResourceLocation JUMPSUIT3_SLIM_TEXTURE = new ResourceLocation(CuffedPlusMain.MODID, "textures/entity/uniforms/prison_jumpsuit3_slim.png");

    // Example overlay texture
    private static final ResourceLocation TARGET = new ResourceLocation(CuffedPlusMain.MODID, "textures/entity/uniforms/backs/target.png");
    private static final ResourceLocation LETTER_A = new ResourceLocation(CuffedPlusMain.MODID, "textures/entity/uniforms/backs/a.png");
    private static final ResourceLocation LETTER_B = new ResourceLocation(CuffedPlusMain.MODID, "textures/entity/uniforms/backs/b.png");
    private static final ResourceLocation LETTER_C = new ResourceLocation(CuffedPlusMain.MODID, "textures/entity/uniforms/backs/c.png");
    private static final ResourceLocation LETTER_D = new ResourceLocation(CuffedPlusMain.MODID, "textures/entity/uniforms/backs/d.png");
    private static final ResourceLocation LETTER_E = new ResourceLocation(CuffedPlusMain.MODID, "textures/entity/uniforms/backs/e.png");
    private static final ResourceLocation LETTER_F = new ResourceLocation(CuffedPlusMain.MODID, "textures/entity/uniforms/backs/f.png");
    private static final ResourceLocation LETTER_G = new ResourceLocation(CuffedPlusMain.MODID, "textures/entity/uniforms/backs/g.png");
    private static final ResourceLocation LETTER_H = new ResourceLocation(CuffedPlusMain.MODID, "textures/entity/uniforms/backs/h.png");
    private static final ResourceLocation TEST_SUBJECT_BACK = new ResourceLocation(CuffedPlusMain.MODID, "textures/entity/uniforms/backs/test_subject_text.png");
    private static final ResourceLocation INMATE_BACK = new ResourceLocation(CuffedPlusMain.MODID, "textures/entity/uniforms/backs/inmate_text.png");
    private static final ResourceLocation BARS_BACK = new ResourceLocation(CuffedPlusMain.MODID, "textures/entity/uniforms/backs/bars_back.png");
    private static final ResourceLocation DCLASS_BACK = new ResourceLocation(CuffedPlusMain.MODID, "textures/entity/uniforms/backs/dclass_back.png");
    private static final ResourceLocation JUMPSUIT1_BACK = new ResourceLocation(CuffedPlusMain.MODID, "textures/entity/uniforms/backs/jumpsuit1_back.png");
    private static final ResourceLocation JUMPSUIT2_BACK = new ResourceLocation(CuffedPlusMain.MODID, "textures/entity/uniforms/backs/jumpsuit2_back.png");
    private static final ResourceLocation JUMPSUIT3_BACK = new ResourceLocation(CuffedPlusMain.MODID, "textures/entity/uniforms/backs/jumpsuit3_back.png");

    private final ResourceLocation[] textures = {TARGET, BARS_BACK, INMATE_BACK, TEST_SUBJECT_BACK, LETTER_A, LETTER_B, LETTER_C, LETTER_D, LETTER_E, LETTER_F, LETTER_G, LETTER_H};

    public JumpsuitLayer(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> parent) {
        super(parent);
    }

    @Override
    public void render(@NotNull PoseStack poseStack, @NotNull MultiBufferSource buffer, int packedLight,
                       @NotNull AbstractClientPlayer player, float limbSwing, float limbSwingAmount,
                       float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        GeneralUtils.displayClientMessage(player, "Rendering Jumpsuit", ChatFormatting.BLUE);

        ItemStack chest = player.getItemBySlot(EquipmentSlot.CHEST);
        boolean slim = "slim".equals(player.getModelName());

        ResourceLocation baseTexture = null;
        ResourceLocation overlayTexture = null;

        // Choose base texture depending on item
        if (chest.is(ModItems.DCLASS_JUMPSUIT.get())) {
            baseTexture = slim ? DCLASS_SLIM_TEXTURE : DCLASS_CLASSIC_TEXTURE;
            if (chest.hasTag() && chest.getOrCreateTag().getByte("JumpsuitNumber") == 0) {
                overlayTexture = DCLASS_BACK;
            }
        } else if (chest.is(ModItems.PRISON_JUMPSUIT_1.get())) {
            baseTexture = slim ? JUMPSUIT1_SLIM_TEXTURE : JUMPSUIT1_CLASSIC_TEXTURE;
            if (chest.hasTag() && chest.getOrCreateTag().getByte("JumpsuitNumber") == 0) {
                overlayTexture = JUMPSUIT1_BACK;
            }
        } else if (chest.is(ModItems.PRISON_JUMPSUIT_2.get())) {
            baseTexture = slim ? JUMPSUIT2_SLIM_TEXTURE : JUMPSUIT2_CLASSIC_TEXTURE;
            if (chest.hasTag() && chest.getOrCreateTag().getByte("JumpsuitNumber") == 0) {
                overlayTexture = JUMPSUIT2_BACK;
            }
        } else if (chest.is(ModItems.PRISON_JUMPSUIT_3.get())) {
            baseTexture = slim ? JUMPSUIT3_SLIM_TEXTURE : JUMPSUIT3_CLASSIC_TEXTURE;
            if (chest.hasTag() && chest.getOrCreateTag().getByte("JumpsuitNumber") == 0) {
                overlayTexture = JUMPSUIT3_BACK;
            }
        }

        if (baseTexture == null) return;


        // Optional: add overlay texture if item NBT has a certain flag

        if (chest.hasTag() && chest.getOrCreateTag().getByte("JumpsuitNumber") != 0 && chest.getOrCreateTag().getByte("JumpsuitNumber") < 11) {
            overlayTexture = textures[chest.getOrCreateTag().getByte("JumpsuitNumber") - 1];
        }

        // Setup player model for rendering
        PlayerModel<AbstractClientPlayer> model = new PlayerModel<>(
                Minecraft.getInstance().getEntityModels().bakeLayer(
                        slim ? PLAYER_SLIM : PLAYER
                ),
                slim
        );

        getParentModel().copyPropertiesTo(model);
        model.setupAnim(player, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

        // --- Render base texture ---
        VertexConsumer baseVertex = buffer.getBuffer(RenderType.entityCutoutNoCull(baseTexture));
        model.renderToBuffer(poseStack, baseVertex, packedLight, OverlayTexture.NO_OVERLAY, 1F, 1F, 1F, 1F);

        // --- Render overlay texture (on top) ---
        if (overlayTexture != null) {
            VertexConsumer overlayVertex = buffer.getBuffer(RenderType.entityTranslucent(overlayTexture));
            model.renderToBuffer(poseStack, overlayVertex, packedLight, OverlayTexture.NO_OVERLAY, 1F, 1F, 1F, 1F);
        }
    }
}
