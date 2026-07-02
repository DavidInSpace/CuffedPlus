package git.david.cuffedplus.items.restraints.client.model;

import javax.annotation.Nonnull;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.entity.LivingEntity;

/*

	This class is the model for the Diamond Handcuffs, when they're applied to the player's legs.
	This is the same exact model as the Legcuffs in Cuffed

	When making the models for the restraints, the different cubes for the restraint model can be bound to the player's body parts by referencing them first.
	That's why I include:

		partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);
		partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.ZERO);
		partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.ZERO);
		partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.ZERO);
		partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.ZERO);
		partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.ZERO);
		partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.ZERO);

	in createArmorLayer(), so that I can later reference these parts if I need to like this:

		PartDefinition left_leg = partdefinition.getChild("left_leg");

	then by adding children to this part, those children will be bound to the parent part.
	
	When I make my restraint models, I use blockbench use their Minecraft Skin model and convert it to Modded Entity format.
	Then I export it as a java class and remove everything by the createModelLayer() function and put the layer definition in ModModelLayers
	
	If you have experience with creating entity models, or custom armor models this class should look familiar.

 */ 


public class DiamondCuffsLegsModel<T extends LivingEntity> extends HumanoidModel<T> {
	private final ModelPart _root;

	public DiamondCuffsLegsModel(ModelPart root) {
		super(root);
		this._root = root;
	}

	public static LayerDefinition createArmorLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		
		// Create definitions for the player model's body parts so we can create child cubes for them.
		partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);
		partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.ZERO);
		partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.ZERO);
		partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.ZERO);
		partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.ZERO);
		partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.ZERO);
		partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.ZERO);

		// Get the right leg from the player and add the right legcuff model cubes to it as children.
		PartDefinition right_leg = partdefinition.getChild("right_leg");

		PartDefinition right_cuff = right_leg.addOrReplaceChild("right_cuff", CubeListBuilder.create(), PartPose.offset(0.0F, 8.0F, 0.0F));
		PartDefinition cube_r1 = right_cuff.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 8).addBox(1.0F, -2.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(8, 8).addBox(-3.0F, -2.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 4).addBox(-2.0F, 1.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-2.0F, -3.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 0.0F));

		// Get the left leg from the player and add the left legcuff model cubes to it as children.
		PartDefinition left_leg = partdefinition.getChild("left_leg");

		PartDefinition left_cuff = left_leg.addOrReplaceChild("left_cuff", CubeListBuilder.create(), PartPose.offset(0.1F, 8.0F, 0.0F));
		PartDefinition cube_r2 = left_cuff.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 14).addBox(-5.25F, -2.0358F, -0.0307F, 5.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.0F, 3.0F, -2.4436F, -0.0045F, 0.019F));
		PartDefinition cube_r3 = left_cuff.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(8, 8).addBox(-3.1F, -2.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 8).addBox(0.9F, -2.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 4).addBox(-2.1F, 1.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-2.1F, -3.0F, -1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 16, 16);
	}
	
	// This function is used to render the model by calling render() on _root.
	@Override
	public void renderToBuffer(@Nonnull PoseStack stack, @Nonnull VertexConsumer buffer, int packedLight, int blockLight,
			float partialTick, float r, float g, float b) {	
		_root.render(stack, buffer, packedLight, blockLight);

		super.renderToBuffer(stack, buffer, packedLight, blockLight, partialTick, r, g, b);
	}
}