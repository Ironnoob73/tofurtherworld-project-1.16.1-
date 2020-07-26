package idv.ironnoobseventhree.tofurtherworld.entity;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;

public class MissingNumberModel extends EntityModel<MissingNumber> {

    private final ModelPart base;

    public MissingNumberModel() {
        base = new ModelPart(64,64,0,0);
        base.addCuboid("base",-4.0F, -30.0F, -2.0F, 16, 55, 4,0,0,0);
        base.addCuboid("base",-12.0F, -7.0F, -2.0F, 8, 32, 4,0,40,0);
        this.textureHeight = 64;
        this.textureWidth = 64;
    }

    @Override
    public void setAngles(MissingNumber entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {

    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        matrices.translate(0, 0, 0);
        base.render(matrices, vertices, light, overlay);
    }
}
