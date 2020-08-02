package idv.ironnoobseventhree.tofurtherworld;

import idv.ironnoobseventhree.tofurtherworld.block.forging.ForgingTableL1Inventory;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.minecraft.client.render.RenderLayer;


@Environment(EnvType.CLIENT)
public class CliF implements net.fabricmc.api.ClientModInitializer{
    @Override
    public void onInitializeClient() {
        //Transparent
        BlockRenderLayerMap.INSTANCE.putBlock(Core.PureObsidianBlock, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(Core.WhiteSeamlessGlass, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(Core.OrangeSeamlessGlass, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(Core.MagentaSeamlessGlass, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(Core.LightBlueSeamlessGlass, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(Core.YellowSeamlessGlass, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(Core.LimeSeamlessGlass, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(Core.PinkSeamlessGlass, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(Core.GraySeamlessGlass, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(Core.LightGraySeamlessGlass, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(Core.CyanSeamlessGlass, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(Core.PurpleSeamlessGlass, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(Core.BlueSeamlessGlass, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(Core.BrownSeamlessGlass, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(Core.GreenSeamlessGlass, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(Core.RedSeamlessGlass, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(Core.BlackSeamlessGlass, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(Core.WhiteGlowingGlass, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(Core.OrangeGlowingGlass, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(Core.MagentaGlowingGlass, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(Core.LightBlueGlowingGlass, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(Core.YellowGlowingGlass, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(Core.LimeGlowingGlass, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(Core.PinkGlowingGlass, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(Core.GrayGlowingGlass, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(Core.LightGrayGlowingGlass, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(Core.CyanGlowingGlass, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(Core.PurpleGlowingGlass, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(Core.BlueGlowingGlass, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(Core.BrownGlowingGlass, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(Core.GreenGlowingGlass, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(Core.RedGlowingGlass, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(Core.BlackGlowingGlass, RenderLayer.getTranslucent());
        //Transparent with alpha
        BlockRenderLayerMap.INSTANCE.putBlock(Core.IceBirchSapling, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Core.FrozenBush, RenderLayer.getCutout());
        //Gui
        ScreenRegistry.register(Core.ForgingTableL1Screen, ForgingTableL1Inventory::new);
    }
}
