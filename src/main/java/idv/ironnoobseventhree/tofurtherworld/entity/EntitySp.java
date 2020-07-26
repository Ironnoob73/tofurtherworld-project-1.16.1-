package idv.ironnoobseventhree.tofurtherworld.entity;

import idv.ironnoobseventhree.tofurtherworld.Core;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EntitySp implements ModInitializer {
    public static final EntityType<MissingNumber> MISSING_NUMBER = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier(Core.MODID, "missing_no"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, MissingNumber::new).dimensions(EntityDimensions.fixed(0.75f, 1.75f)).build()
    );

    @Override
    public void onInitialize() {
        FabricDefaultAttributeRegistry.register(MISSING_NUMBER, MissingNumber.createMobAttributes());
        EntityRendererRegistry.INSTANCE.register(MISSING_NUMBER, (dispatcher, context) -> { return new MissingNumberRender(dispatcher); });
    }
}
