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

	This class is the model for the Diamond Handcuffs, when they're applied to the player's arms.
	This is the same exact model as the Handcuffs in Cuffed

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

		PartDefinition leftarm = partdefinition.getChild("left_arm");

	then by adding children to this part, those children will be bound to the parent part.

	When I make my restraint models, I use blockbench use their Minecraft Skin model and convert it to Modded Entity format.
	Then I export it as a java class and remove everything by the createModelLayer() function and put the layer definition in ModModelLayers

	If you have experience with creating entity models, or custom armor models this class should look familiar.

 */


public class DiamondCuffsArmsModel<T extends LivingEntity> extends HumanoidModel<T> {
    private final ModelPart _root;

    public DiamondCuffsArmsModel(ModelPart root) {
        super(root);
        _root = root;
    }

    // The same model as the Handcuffs model
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

        // Get the left arm from the player and add the left handcuff model cubes to it as children.
        PartDefinition leftarm = partdefinition.getChild("left_arm");
        PartDefinition leftcuff = leftarm.addOrReplaceChild("leftcuff", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition LeftCuff_r1 = leftcuff.addOrReplaceChild("LeftCuff_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -3.0F, 1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 4).addBox(-2.0F, 1.0F, 1.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(8, 8).addBox(-3.0F, -2.0F, 1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 8).addBox(1.0F, -2.0F, 1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 5.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

        PartDefinition Chain_r1 = leftcuff.addOrReplaceChild("Chain_r1", CubeListBuilder.create().texOffs(0, 14).addBox(-5.75F, -0.75F, -0.25F, 5.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 6.0F, 3.0F, -0.8309F, 0.5148F, -0.4946F));


        // Get the right arm from the player and add the left handcuff model cubes to it as children.
        PartDefinition rightarm = partdefinition.getChild("right_arm");
        PartDefinition rightcuff = rightarm.addOrReplaceChild("rightcuff", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition RightCuff_r1 = rightcuff.addOrReplaceChild("RightCuff_r1", CubeListBuilder.create().texOffs(8, 8).addBox(-4.0F, -2.0F, 6.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 8).addBox(0.0F, -2.0F, 6.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-3.0F, -3.0F, 6.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 4).addBox(-3.0F, 1.0F, 6.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

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