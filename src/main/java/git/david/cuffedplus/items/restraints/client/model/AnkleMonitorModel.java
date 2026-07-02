package git.david.cuffedplus.items.restraints.client.model;// Made with Blockbench 5.0.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

import javax.annotation.Nonnull;

public class AnkleMonitorModel<T extends LivingEntity> extends HumanoidModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER =
            new ModelLayerLocation(new ResourceLocation("cuffedplus", "ankle_monitor"), "main");
    private final ModelPart _root;

    public AnkleMonitorModel(ModelPart root) {
        super(root);
        _root = root;
    }


	public static LayerDefinition createArmorLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();


        partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);
        partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.ZERO);
        partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.ZERO);
        partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.ZERO);
        partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.ZERO);
        partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.ZERO);
        partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.ZERO);

        PartDefinition right_leg = partdefinition.getChild("right_leg");

        PartDefinition right_cuff = right_leg.addOrReplaceChild("right_cuff", CubeListBuilder.create(), PartPose.offset(0.0F, -8.0F, 0.0F));
		PartDefinition bb_main = right_cuff.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(0, 0).addBox(-2.3F, -8.3F, -2.3F, 4.6F, 1.6F, 4.6F, new CubeDeformation(0.0F))
		.texOffs(0, 19).addBox(-1.5F, -8.5F, -2.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 13).addBox(-2.15F, -8.15F, -2.45F, 4.3F, 1.3F, 0.3F, new CubeDeformation(0.0F))
		.texOffs(15, 7).addBox(-0.5F, -8.0F, -2.7F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube_r1 = bb_main.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 16).addBox(-2.15F, -0.65F, -2.425F, 4.3F, 1.3F, 0.3F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.5F, -0.025F, 0.0F, 3.1416F, 0.0F));

		PartDefinition cube_r2 = bb_main.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(10, 10).addBox(-1.025F, -0.5F, -2.425F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 10).addBox(-2.175F, -0.65F, -2.4F, 4.3F, 1.3F, 0.3F, new CubeDeformation(0.0F)),                                                                                                   PartPose.offsetAndRotation(0.0F, -7.5F, -0.025F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r3 = bb_main.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 7).addBox(-2.125F, -0.65F, -2.4F, 4.3F, 1.3F, 0.3F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.5F, -0.025F, 0.0F, -1.5708F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

    @Override
    public void renderToBuffer(@Nonnull PoseStack stack, @Nonnull VertexConsumer buffer, int packedLight, int blockLight,
                               float partialTick, float r, float g, float b) {
        _root.render(stack, buffer, packedLight, blockLight);
        super.renderToBuffer(stack, buffer, packedLight, blockLight, partialTick, r, g, b);
    }
}