package idv.ironnoobseventhree.tofurtherworld.entity;

import idv.ironnoobseventhree.tofurtherworld.Core;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class MissingNumberRender extends MobEntityRenderer<MissingNumber, MissingNumberModel> {

    public MissingNumberRender(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher, new MissingNumberModel(), 0.5f);
    }

    @Override
    public Identifier getTexture(MissingNumber entity) {
        return new Identifier(Core.MODID, "textures/entity/missing_no.png");
    }
}
