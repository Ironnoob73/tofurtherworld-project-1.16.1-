package idv.ironnoobseventhree.tofurtherworld;

import idv.ironnoobseventhree.tofurtherworld.block.DrawerBlock;
import idv.ironnoobseventhree.tofurtherworld.block.ForgingTableL1;
import idv.ironnoobseventhree.tofurtherworld.block.GlassLike;
import idv.ironnoobseventhree.tofurtherworld.biome.BiomeMain;
import idv.ironnoobseventhree.tofurtherworld.block.Facing;
import idv.ironnoobseventhree.tofurtherworld.block.furniture.Chair;
import idv.ironnoobseventhree.tofurtherworld.block.sapling.FrozenBushBlock;
import idv.ironnoobseventhree.tofurtherworld.block.sapling.IceBirch;
import idv.ironnoobseventhree.tofurtherworld.block.sapling.SaplingMain;
import idv.ironnoobseventhree.tofurtherworld.tool.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biomes.v1.OverworldBiomes;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

public class Core implements ModInitializer {
    //Base Item
    public static final Item CopperIngot = new Item(new Item.Settings());
    public static final Item CopperPowder = new Item(new Item.Settings());
    public static final Item CopperPlate = new Item(new Item.Settings());
    public static final Item CopperCube = new Item(new Item.Settings());
    public static final Item AluminumIngot = new Item(new Item.Settings());
    public static final Item AluminumPowder = new Item(new Item.Settings());
    public static final Item AluminumPlate = new Item(new Item.Settings());
    public static final Item AluminumCube = new Item(new Item.Settings());
    public static final Item IronPowder = new Item(new Item.Settings());
    public static final Item IronPlate = new Item(new Item.Settings());
    public static final Item IronCube = new Item(new Item.Settings());
    public static final Item GoldPowder = new Item(new Item.Settings());
    public static final Item GoldCube = new Item(new Item.Settings());
    public static final Item CarbonFibre = new Item(new Item.Settings());
    public static final Item CarbonPowder = new Item(new Item.Settings());
    public static final Item CarbonPlate = new Item(new Item.Settings());
    public static final Item CarbonStick = new Item(new Item.Settings());
    public static final Item TinIngot = new Item(new Item.Settings());
    public static final Item TinPowder = new Item(new Item.Settings());
    public static final Item TinPlate = new Item(new Item.Settings());
    public static final Item TinCube = new Item(new Item.Settings());
    public static final Item LeadIngot = new Item(new Item.Settings());
    public static final Item LeadPowder = new Item(new Item.Settings());
    public static final Item LeadPlate = new Item(new Item.Settings());
    public static final Item LeadCube = new Item(new Item.Settings());
    public static final Item SilverIngot = new Item(new Item.Settings());
    public static final Item SilverPowder = new Item(new Item.Settings());
    public static final Item SilverPlate = new Item(new Item.Settings());
    public static final Item SilverCube = new Item(new Item.Settings());
    public static final Item StoneIngot = new Item(new Item.Settings());
    public static final Item StonePlate = new Item(new Item.Settings());
    public static final Item Rust = new Item(new Item.Settings());
    public static final Item RustyIronIngot = new Item(new Item.Settings());
    public static final Item RawSteelIngot = new Item(new Item.Settings());
    public static final Item SteelIngot = new Item(new Item.Settings());
    //Nature Item
    public static final Item TinyRock = new Item(new Item.Settings());
    public static final Item StoneChippings = new Item(new Item.Settings());
    public static final Item RichOreStoneChippings = new Item(new Item.Settings());
    public static final Item TinyFlint = new Item(new Item.Settings());
    public static final Item BoneFragments = new Item(new Item.Settings());
    public static final Item GrassStem = new Item(new Item.Settings());
    public static final Item Rattan = new Item(new Item.Settings());
    public static final Item SoilHeaps = new Item(new Item.Settings());
    public static final Item WoodenStaff = new Item(new Item.Settings());
    public static final Item TurbidFragmentOfObsidian = new Item(new Item.Settings());
    public static final Item PureFragmentOfObsidian = new Item(new Item.Settings());
    public static final Item TurbidObsidian = new Item(new Item.Settings());
    public static final Item PureObsidian = new Item(new Item.Settings());
    //Food
    public static final Item ApplePiece = new Item((new Item.Settings()).food(FoodComponents.APPLE));
    //Magic Item
    public static final Item Ruby = new Item(new Item.Settings());
    public static final Item Sapphire = new Item(new Item.Settings());
    public static final Item Topaz = new Item(new Item.Settings());
    public static final Item NetherWartSlurries = new Item(new Item.Settings());
    public static final Item WarpedWartSlurries = new Item(new Item.Settings());
    public static final Item BlackgoldIngot = new Item(new Item.Settings());
    public static final Item EndPearl = new Item(new Item.Settings());
    public static final Item EndPearlPowder = new Item(new Item.Settings());
    public static final Item EndPearlStick = new Item(new Item.Settings());
    //Special
    public static final idv.ironnoobseventhree.tofurtherworld.tool.PDA PDA = new PDA(new Item.Settings());
    public static final Item CopperWrench = new Item(new Item.Settings());
    public static final Item Blueprint = new Item(new Item.Settings());
    public static final Item EmptyBlueprint = new Item(new Item.Settings());
    //Tool
    public static ToolItem RoughHatchet = new AxeModel(ToolSetting.Rough,0.5f,1,new Item.Settings());
    public static ToolItem RoughKnife = new SwordItem(ToolSetting.Rough,0,1,new Item.Settings());
    public static final Item CopperAxePart = new Item(new Item.Settings());
    public static final Item CopperSwordPart = new Item(new Item.Settings());
    public static final Item CopperPickaxePart = new Item(new Item.Settings());
    public static final Item CopperShovelPart = new Item(new Item.Settings());
    public static final Item CopperHoePart = new Item(new Item.Settings());
    public static ToolItem CopperAxe = new AxeModel(ToolSetting.Copper,5.5f,-3,new Item.Settings());
    public static ToolItem CopperSword = new SwordItem(ToolSetting.Copper,4,-1,new Item.Settings());
    public static ToolItem CopperPickaxe = new PickaxeModel(ToolSetting.Copper,2,-2,new Item.Settings());
    public static ToolItem CopperShovel = new ShovelItem(ToolSetting.Copper,1.5f,-2,new Item.Settings());
    public static ToolItem CopperHoe = new HoeModel(ToolSetting.Copper,-3,0,new Item.Settings());
    public static ToolItem AluminumAxe = new AxeModel(ToolSetting.Aluminum,5.5f,-2,new Item.Settings());
    public static ToolItem AluminumSword = new SwordItem(ToolSetting.Aluminum,3,0,new Item.Settings());
    public static ToolItem AluminumPickaxe = new PickaxeModel(ToolSetting.Aluminum,1,-1,new Item.Settings());
    public static ToolItem AluminumShovel = new ShovelItem(ToolSetting.Aluminum,1,-1,new Item.Settings());
    public static ToolItem AluminumHoe = new HoeModel(ToolSetting.Aluminum,-3,0,new Item.Settings());
    public static ToolItem OnlyIronAxe = new AxeModel(ToolSetting.OnlyIron,5.5f,-3.1f,new Item.Settings());
    public static ToolItem OnlyIronSword = new SwordItem(ToolSetting.OnlyIron,3,-2.4f,new Item.Settings());
    public static ToolItem OnlyIronPickaxe = new PickaxeModel(ToolSetting.OnlyIron,1,-2.8f,new Item.Settings());
    public static ToolItem OnlyIronShovel = new ShovelItem(ToolSetting.OnlyIron,1,-3,new Item.Settings());
    public static ToolItem OnlyIronHoe = new HoeModel(ToolSetting.OnlyIron,-3,-1,new Item.Settings());
    public static ToolItem RuggedizedAluminumAxe = new AxeModel(ToolSetting.RuggedizedAluminum,5.5f,-1,new Item.Settings());
    public static ToolItem RuggedizedAluminumSword = new SwordItem(ToolSetting.RuggedizedAluminum,3,1,new Item.Settings());
    public static ToolItem RuggedizedAluminumPickaxe = new PickaxeModel(ToolSetting.RuggedizedAluminum,1,0,new Item.Settings());
    public static ToolItem RuggedizedAluminumShovel = new ShovelItem(ToolSetting.RuggedizedAluminum,1,0,new Item.Settings());
    public static ToolItem RuggedizedAluminumHoe = new HoeModel(ToolSetting.RuggedizedAluminum,-3,1,new Item.Settings());
    public static ToolItem StoneBrickAxe = new AxeModel(ToolSetting.StoneBrick,7.0f,-3.2f,new Item.Settings());
    public static ToolItem StoneBrickSword = new SwordItem(ToolSetting.StoneBrick,3,-2.4f,new Item.Settings());
    public static ToolItem StoneBrickPickaxe = new PickaxeModel(ToolSetting.StoneBrick,1,-2.8f,new Item.Settings());
    public static ToolItem StoneBrickShovel = new ShovelItem(ToolSetting.StoneBrick,1.5f,-3,new Item.Settings());
    public static ToolItem StoneBrickHoe = new HoeModel(ToolSetting.StoneBrick,-1,-2,new Item.Settings());
    public static ToolItem SteelAxe = new AxeModel(ToolSetting.Steel,5.5f,-3,new Item.Settings());
    public static ToolItem SteelSword = new SwordItem(ToolSetting.Steel,3,-2.5f,new Item.Settings());
    public static ToolItem SteelPickaxe = new PickaxeModel(ToolSetting.Steel,1,-2.5f,new Item.Settings());
    public static ToolItem SteelShovel = new ShovelItem(ToolSetting.Steel,1,-3,new Item.Settings());
    public static ToolItem SteelHoe = new HoeModel(ToolSetting.Steel,-3,-1,new Item.Settings());
    public static ToolItem ObsidianAxe = new AxeModel(ToolSetting.Obsidian,5.5f,-3,new Item.Settings());
    public static ToolItem ObsidianSword = new SwordItem(ToolSetting.Steel,3,-2.5f,new Item.Settings());
    public static ToolItem ObsidianPickaxe = new PickaxeModel(ToolSetting.Steel,1,-2.5f,new Item.Settings());
    public static ToolItem ObsidianShovel = new ShovelItem(ToolSetting.Steel,1,-3,new Item.Settings());
    public static ToolItem ObsidianHoe = new HoeModel(ToolSetting.Steel,-3,-1,new Item.Settings());
    //Material Block
    public static final Block TestOre = new Block(FabricBlockSettings.of(Material.STONE).hardness(6.0f).breakByTool(FabricToolTags.PICKAXES, 0));
    public static final Block ForgottenIronOre = new OreBlock(AbstractBlock.Settings.of(Material.STONE).requiresTool().strength(3.0F, 3.0F));
    public static final Block ChiseledIronBlock = new Block(AbstractBlock.Settings.of(Material.METAL, MaterialColor.IRON).requiresTool().strength(10.0F, 6.0F).sounds(BlockSoundGroup.NETHERITE));
    public static final Block CopperOre = new OreBlock(FabricBlockSettings.of(Material.STONE).hardness(6.0f).breakByTool(FabricToolTags.PICKAXES, 0).requiresTool());
    public static final Block CopperBlock = new Block(AbstractBlock.Settings.of(Material.METAL, MaterialColor.IRON).requiresTool().strength(5.0F, 6.0F).sounds(BlockSoundGroup.METAL));
    public static final Block AluminumOre = new OreBlock(FabricBlockSettings.of(Material.STONE).hardness(6.0f).breakByTool(FabricToolTags.PICKAXES, 3).requiresTool());
    public static final Block AluminumBlock = new Block(AbstractBlock.Settings.of(Material.METAL, MaterialColor.IRON).requiresTool().strength(5.0F, 6.0F).sounds(BlockSoundGroup.METAL));
    public static final Block TinOre = new OreBlock(AbstractBlock.Settings.of(Material.STONE).requiresTool().strength(3.0F, 3.0F));
    public static final Block TinBlock = new Block(AbstractBlock.Settings.of(Material.METAL, MaterialColor.IRON).requiresTool().strength(5.0F, 6.0F).sounds(BlockSoundGroup.METAL));
    public static final Block LeadOre = new OreBlock(AbstractBlock.Settings.of(Material.STONE).requiresTool().strength(6.0F, 6.0F));
    public static final Block LeadBlock = new Block(AbstractBlock.Settings.of(Material.METAL, MaterialColor.IRON).requiresTool().strength(5.0F, 6.0F).sounds(BlockSoundGroup.METAL));
    public static final Block SilverOre = new OreBlock(AbstractBlock.Settings.of(Material.STONE).requiresTool().strength(6.0F, 6.0F));
    public static final Block SilverBlock = new Block(AbstractBlock.Settings.of(Material.METAL, MaterialColor.IRON).requiresTool().strength(5.0F, 6.0F).sounds(BlockSoundGroup.METAL));
    public static final Block OldCommandBlock = new Block(AbstractBlock.Settings.of(Material.STONE).requiresTool().strength(120.0F, 999.0F));
    public static final Block OldReactor = new Block(AbstractBlock.Settings.of(Material.STONE).requiresTool().strength(20.0F, 80.0F));
    public static final Block RubyOre = new OreBlock(AbstractBlock.Settings.of(Material.STONE).requiresTool().strength(6.0F, 6.0F));
    public static final Block RubyBlock = new Block(AbstractBlock.Settings.of(Material.METAL, MaterialColor.IRON).requiresTool().strength(5.0F, 6.0F).sounds(BlockSoundGroup.METAL));
    public static final Block SapphireOre = new OreBlock(AbstractBlock.Settings.of(Material.STONE).requiresTool().strength(6.0F, 6.0F));
    public static final Block SapphireBlock = new Block(AbstractBlock.Settings.of(Material.METAL, MaterialColor.IRON).requiresTool().strength(5.0F, 6.0F).sounds(BlockSoundGroup.METAL));
    public static final Block TopazOre = new OreBlock(AbstractBlock.Settings.of(Material.STONE).requiresTool().strength(6.0F, 6.0F));
    public static final Block TopazBlock = new Block(AbstractBlock.Settings.of(Material.METAL, MaterialColor.IRON).requiresTool().strength(5.0F, 6.0F).sounds(BlockSoundGroup.METAL));
    public static final Block SteelBlock = new Block(AbstractBlock.Settings.of(Material.METAL, MaterialColor.IRON).requiresTool().strength(10.0F, 6.0F).sounds(BlockSoundGroup.NETHERITE));
    public static final Block TurbidObsidianBlock = new Block(AbstractBlock.Settings.of(Material.STONE, MaterialColor.BLACK).requiresTool().strength(50.0F, 999.0F).sounds(BlockSoundGroup.STONE));
    public static final Block PureObsidianBlock = new GlassLike(AbstractBlock.Settings.of(Material.STONE, MaterialColor.BLACK).requiresTool().strength(50.0F, 999.0F).sounds(BlockSoundGroup.STONE).nonOpaque());
    //Colour Block
    public static final Block WhiteLego = new Block(FabricBlockSettings.of(Material.STONE).hardness(6.0f).breakByTool(FabricToolTags.PICKAXES, 0));
    public static final Block OrangeLego = new Block(FabricBlockSettings.of(Material.STONE).hardness(6.0f).breakByTool(FabricToolTags.PICKAXES, 0));
    public static final Block MagentaLego = new Block(FabricBlockSettings.of(Material.STONE).hardness(6.0f).breakByTool(FabricToolTags.PICKAXES, 0));
    public static final Block LightBlueLego = new Block(FabricBlockSettings.of(Material.STONE).hardness(6.0f).breakByTool(FabricToolTags.PICKAXES, 0));
    public static final Block YellowLego = new Block(FabricBlockSettings.of(Material.STONE).hardness(6.0f).breakByTool(FabricToolTags.PICKAXES, 0));
    public static final Block LimeLego = new Block(FabricBlockSettings.of(Material.STONE).hardness(6.0f).breakByTool(FabricToolTags.PICKAXES, 0));
    public static final Block PinkLego = new Block(FabricBlockSettings.of(Material.STONE).hardness(6.0f).breakByTool(FabricToolTags.PICKAXES, 0));
    public static final Block GrayLego = new Block(FabricBlockSettings.of(Material.STONE).hardness(6.0f).breakByTool(FabricToolTags.PICKAXES, 0));
    public static final Block LightGrayLego = new Block(FabricBlockSettings.of(Material.STONE).hardness(6.0f).breakByTool(FabricToolTags.PICKAXES, 0));
    public static final Block CyanLego = new Block(FabricBlockSettings.of(Material.STONE).hardness(6.0f).breakByTool(FabricToolTags.PICKAXES, 0));
    public static final Block PurpleLego = new Block(FabricBlockSettings.of(Material.STONE).hardness(6.0f).breakByTool(FabricToolTags.PICKAXES, 0));
    public static final Block BlueLego = new Block(FabricBlockSettings.of(Material.STONE).hardness(6.0f).breakByTool(FabricToolTags.PICKAXES, 0));
    public static final Block BrownLego = new Block(FabricBlockSettings.of(Material.STONE).hardness(6.0f).breakByTool(FabricToolTags.PICKAXES, 0));
    public static final Block GreenLego = new Block(FabricBlockSettings.of(Material.STONE).hardness(6.0f).breakByTool(FabricToolTags.PICKAXES, 0));
    public static final Block RedLego = new Block(FabricBlockSettings.of(Material.STONE).hardness(6.0f).breakByTool(FabricToolTags.PICKAXES, 0));
    public static final Block BlackLego = new Block(FabricBlockSettings.of(Material.STONE).hardness(6.0f).breakByTool(FabricToolTags.PICKAXES, 0));
    public static final Block WhiteSeamlessGlass = new GlassLike(AbstractBlock.Settings.of(Material.GLASS, MaterialColor.WHITE).requiresTool().strength(0.3F, 1.0F).sounds(BlockSoundGroup.GLASS).nonOpaque());
    public static final Block OrangeSeamlessGlass = new GlassLike(AbstractBlock.Settings.of(Material.GLASS, MaterialColor.ORANGE).requiresTool().strength(0.3F, 1.0F).sounds(BlockSoundGroup.GLASS).nonOpaque());
    public static final Block MagentaSeamlessGlass = new GlassLike(AbstractBlock.Settings.of(Material.GLASS, MaterialColor.MAGENTA).requiresTool().strength(0.3F, 1.0F).sounds(BlockSoundGroup.GLASS).nonOpaque());
    public static final Block LightBlueSeamlessGlass = new GlassLike(AbstractBlock.Settings.of(Material.GLASS, MaterialColor.LIGHT_BLUE).requiresTool().strength(0.3F, 1.0F).sounds(BlockSoundGroup.GLASS).nonOpaque());
    public static final Block YellowSeamlessGlass = new GlassLike(AbstractBlock.Settings.of(Material.GLASS, MaterialColor.YELLOW).requiresTool().strength(0.3F, 1.0F).sounds(BlockSoundGroup.GLASS).nonOpaque());
    public static final Block LimeSeamlessGlass = new GlassLike(AbstractBlock.Settings.of(Material.GLASS, MaterialColor.LIME).requiresTool().strength(0.3F, 1.0F).sounds(BlockSoundGroup.GLASS).nonOpaque());
    public static final Block PinkSeamlessGlass = new GlassLike(AbstractBlock.Settings.of(Material.GLASS, MaterialColor.PINK).requiresTool().strength(0.3F, 1.0F).sounds(BlockSoundGroup.GLASS).nonOpaque());
    public static final Block GraySeamlessGlass = new GlassLike(AbstractBlock.Settings.of(Material.GLASS, MaterialColor.GRAY).requiresTool().strength(0.3F, 1.0F).sounds(BlockSoundGroup.GLASS).nonOpaque());
    public static final Block LightGraySeamlessGlass = new GlassLike(AbstractBlock.Settings.of(Material.GLASS, MaterialColor.LIGHT_GRAY).requiresTool().strength(0.3F, 1.0F).sounds(BlockSoundGroup.GLASS).nonOpaque());
    public static final Block CyanSeamlessGlass = new GlassLike(AbstractBlock.Settings.of(Material.GLASS, MaterialColor.CYAN).requiresTool().strength(0.3F, 1.0F).sounds(BlockSoundGroup.GLASS).nonOpaque());
    public static final Block PurpleSeamlessGlass = new GlassLike(AbstractBlock.Settings.of(Material.GLASS, MaterialColor.PURPLE).requiresTool().strength(0.3F, 1.0F).sounds(BlockSoundGroup.GLASS).nonOpaque());
    public static final Block BlueSeamlessGlass = new GlassLike(AbstractBlock.Settings.of(Material.GLASS, MaterialColor.BLUE).requiresTool().strength(0.3F, 1.0F).sounds(BlockSoundGroup.GLASS).nonOpaque());
    public static final Block BrownSeamlessGlass = new GlassLike(AbstractBlock.Settings.of(Material.GLASS, MaterialColor.BROWN).requiresTool().strength(0.3F, 1.0F).sounds(BlockSoundGroup.GLASS).nonOpaque());
    public static final Block GreenSeamlessGlass = new GlassLike(AbstractBlock.Settings.of(Material.GLASS, MaterialColor.GREEN).requiresTool().strength(0.3F, 1.0F).sounds(BlockSoundGroup.GLASS).nonOpaque());
    public static final Block RedSeamlessGlass = new GlassLike(AbstractBlock.Settings.of(Material.GLASS, MaterialColor.RED).requiresTool().strength(0.3F, 1.0F).sounds(BlockSoundGroup.GLASS).nonOpaque());
    public static final Block BlackSeamlessGlass = new GlassLike(AbstractBlock.Settings.of(Material.GLASS, MaterialColor.BLACK).requiresTool().strength(0.3F, 1.0F).sounds(BlockSoundGroup.GLASS).nonOpaque());
    public static final Block WhiteGlowingGlass = new GlassLike(AbstractBlock.Settings.of(Material.GLASS, MaterialColor.WHITE).requiresTool().strength(0.3F, 1.0F).sounds(BlockSoundGroup.GLASS).nonOpaque().lightLevel((state)-> 16));
    public static final Block OrangeGlowingGlass = new GlassLike(AbstractBlock.Settings.of(Material.GLASS, MaterialColor.ORANGE).requiresTool().strength(0.3F, 1.0F).sounds(BlockSoundGroup.GLASS).nonOpaque().lightLevel((state)-> 16));
    public static final Block MagentaGlowingGlass = new GlassLike(AbstractBlock.Settings.of(Material.GLASS, MaterialColor.MAGENTA).requiresTool().strength(0.3F, 1.0F).sounds(BlockSoundGroup.GLASS).nonOpaque().lightLevel((state)-> 16));
    public static final Block LightBlueGlowingGlass = new GlassLike(AbstractBlock.Settings.of(Material.GLASS, MaterialColor.LIGHT_BLUE).requiresTool().strength(0.3F, 1.0F).sounds(BlockSoundGroup.GLASS).nonOpaque().lightLevel((state)-> 16));
    public static final Block YellowGlowingGlass = new GlassLike(AbstractBlock.Settings.of(Material.GLASS, MaterialColor.YELLOW).requiresTool().strength(0.3F, 1.0F).sounds(BlockSoundGroup.GLASS).nonOpaque().lightLevel((state)-> 16));
    public static final Block LimeGlowingGlass = new GlassLike(AbstractBlock.Settings.of(Material.GLASS, MaterialColor.LIME).requiresTool().strength(0.3F, 1.0F).sounds(BlockSoundGroup.GLASS).nonOpaque().lightLevel((state)-> 16));
    public static final Block PinkGlowingGlass = new GlassLike(AbstractBlock.Settings.of(Material.GLASS, MaterialColor.PINK).requiresTool().strength(0.3F, 1.0F).sounds(BlockSoundGroup.GLASS).nonOpaque().lightLevel((state)-> 16));
    public static final Block GrayGlowingGlass = new GlassLike(AbstractBlock.Settings.of(Material.GLASS, MaterialColor.GRAY).requiresTool().strength(0.3F, 1.0F).sounds(BlockSoundGroup.GLASS).nonOpaque().lightLevel((state)-> 16));
    public static final Block LightGrayGlowingGlass = new GlassLike(AbstractBlock.Settings.of(Material.GLASS, MaterialColor.LIGHT_GRAY).requiresTool().strength(0.3F, 1.0F).sounds(BlockSoundGroup.GLASS).nonOpaque().lightLevel((state)-> 16));
    public static final Block CyanGlowingGlass = new GlassLike(AbstractBlock.Settings.of(Material.GLASS, MaterialColor.CYAN).requiresTool().strength(0.3F, 1.0F).sounds(BlockSoundGroup.GLASS).nonOpaque().lightLevel((state)-> 16));
    public static final Block PurpleGlowingGlass = new GlassLike(AbstractBlock.Settings.of(Material.GLASS, MaterialColor.PURPLE).requiresTool().strength(0.3F, 1.0F).sounds(BlockSoundGroup.GLASS).nonOpaque().lightLevel((state)-> 16));
    public static final Block BlueGlowingGlass = new GlassLike(AbstractBlock.Settings.of(Material.GLASS, MaterialColor.BLUE).requiresTool().strength(0.3F, 1.0F).sounds(BlockSoundGroup.GLASS).nonOpaque().lightLevel((state)-> 16));
    public static final Block BrownGlowingGlass = new GlassLike(AbstractBlock.Settings.of(Material.GLASS, MaterialColor.BROWN).requiresTool().strength(0.3F, 1.0F).sounds(BlockSoundGroup.GLASS).nonOpaque().lightLevel((state)-> 16));
    public static final Block GreenGlowingGlass = new GlassLike(AbstractBlock.Settings.of(Material.GLASS, MaterialColor.GREEN).requiresTool().strength(0.3F, 1.0F).sounds(BlockSoundGroup.GLASS).nonOpaque().lightLevel((state)-> 16));
    public static final Block RedGlowingGlass = new GlassLike(AbstractBlock.Settings.of(Material.GLASS, MaterialColor.RED).requiresTool().strength(0.3F, 1.0F).sounds(BlockSoundGroup.GLASS).nonOpaque().lightLevel((state)-> 16));
    public static final Block BlackGlowingGlass = new GlassLike(AbstractBlock.Settings.of(Material.GLASS, MaterialColor.BLACK).requiresTool().strength(0.3F, 1.0F).sounds(BlockSoundGroup.GLASS).nonOpaque().lightLevel((state)-> 16));
    //Ore Gen
    private void CopperGen(Biome biome) {if(biome.getCategory() != Biome.Category.NETHER && biome.getCategory() != Biome.Category.THEEND) { biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES,
            Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Target.NATURAL_STONE, CopperOre.getDefaultState(), 10)).createDecoratedFeature(
                    Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(8, 0, 1, 63)))); } }
    private void AluminumGen(Biome biome) {if(biome.getCategory() != Biome.Category.NETHER && biome.getCategory() != Biome.Category.THEEND) { biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES,
            Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Target.NATURAL_STONE, AluminumOre.getDefaultState(), 7)).createDecoratedFeature(
                    Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(15, 0, 1, 63)))); } }
    private void TinGen(Biome biome) {if(biome.getCategory() != Biome.Category.NETHER && biome.getCategory() != Biome.Category.THEEND) { biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES,
            Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Target.NATURAL_STONE, TinOre.getDefaultState(), 7)).createDecoratedFeature(
                    Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(10, 0, 1, 63)))); } }
    private void LeadGen(Biome biome) {if(biome.getCategory() != Biome.Category.NETHER && biome.getCategory() != Biome.Category.THEEND) { biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES,
            Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Target.NATURAL_STONE, LeadOre.getDefaultState(), 5)).createDecoratedFeature(
                    Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(5, 0, 1, 40)))); } }
    private void SilverGen(Biome biome) {if(biome.getCategory() != Biome.Category.NETHER && biome.getCategory() != Biome.Category.THEEND) { biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES,
            Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Target.NATURAL_STONE, SilverOre.getDefaultState(), 4)).createDecoratedFeature(
                    Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(6, 0, 2, 29)))); } }
    private void ForgottenIronGen(Biome biome) {if(biome.getCategory() != Biome.Category.NETHER && biome.getCategory() != Biome.Category.THEEND) { biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES,
            Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Target.NATURAL_STONE, ForgottenIronOre.getDefaultState(), 2)).createDecoratedFeature(
                    Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(1, 0, 0, 60)))); } }
    private void RubyGen(Biome biome) {if(biome.getCategory() == Biome.Category.JUNGLE) { biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES,
            Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Target.NATURAL_STONE, RubyOre.getDefaultState(), 5)).createDecoratedFeature(
                    Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(3, 0, 4, 30)))); } }
    private void SapphireGen(Biome biome) {if(biome.getCategory() == Biome.Category.OCEAN) { biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES,
            Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Target.NATURAL_STONE, SapphireOre.getDefaultState(), 5)).createDecoratedFeature(
                    Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(3, 0, 4, 30)))); } }
    private void TopazGen(Biome biome) {if(biome.getCategory() == Biome.Category.DESERT) { biome.addFeature(GenerationStep.Feature.UNDERGROUND_ORES,
            Feature.ORE.configure(new OreFeatureConfig(OreFeatureConfig.Target.NATURAL_STONE, TopazOre.getDefaultState(), 5)).createDecoratedFeature(
                    Decorator.COUNT_RANGE.configure(new RangeDecoratorConfig(3, 0, 4, 30)))); } }
    //Entity Block
    public static final DrawerBlock Drawer1 = new DrawerBlock(AbstractBlock.Settings.of(Material.STONE).requiresTool().strength(6.0F, 6.0F));
    public static final DrawerBlock Drawer2 = new DrawerBlock(AbstractBlock.Settings.of(Material.STONE).requiresTool().strength(6.0F, 6.0F));
    public static final Block ForgingTableL1 = new ForgingTableL1(AbstractBlock.Settings.of(Material.STONE).requiresTool().strength(6.0F, 6.0F).sounds(BlockSoundGroup.METAL));
    //Furniture
    public static final Block WoodenChair = new Chair(AbstractBlock.Settings.of(Material.WOOD, MaterialColor.BROWN).strength(2.0F).sounds(BlockSoundGroup.WOOD).nonOpaque());
    //Block Setting
    private static PillarBlock createLogBlock(MaterialColor topMaterialColor, MaterialColor sideMaterialColor) {
        return new PillarBlock(AbstractBlock.Settings.of(Material.WOOD, (blockState) -> {
            return blockState.get(PillarBlock.AXIS) == Direction.Axis.Y ? topMaterialColor : sideMaterialColor;
        }).strength(2.0F).sounds(BlockSoundGroup.WOOD));
    }
    private static LeavesBlock createLeavesBlock() {
        return new LeavesBlock(AbstractBlock.Settings.of(Material.LEAVES).strength(0.2F).ticksRandomly().sounds(BlockSoundGroup.GRASS).nonOpaque());
    }
    //Nature Block
    public static final Block AppleBlock = new Facing(AbstractBlock.Settings.of(Material.GOURD, MaterialColor.RED).strength(1.0F).sounds(BlockSoundGroup.WART_BLOCK));
    public static final Block IceBirchSapling = new SaplingMain(new IceBirch(), AbstractBlock.Settings.of(Material.PLANT).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS));
    public static final Block IceBirchLog = createLogBlock(MaterialColor.ICE, MaterialColor.QUARTZ);
    public static final Block IceBirchLeaves = createLeavesBlock();
    public static final Block IceBirchPlanks = new Block(AbstractBlock.Settings.of(Material.WOOD, MaterialColor.ICE).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD));
    public static final Block StrippedIceBirchLog = new PillarBlock(AbstractBlock.Settings.of(Material.WOOD, MaterialColor.WOOD).strength(2.0F).sounds(BlockSoundGroup.WOOD));
    public static final FrozenBushBlock FrozenBush =new FrozenBushBlock(AbstractBlock.Settings.of(Material.REPLACEABLE_PLANT, MaterialColor.WOOD).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS));
    
    public static final String MODID = "tofurtherworld";
    
    @Override
    public void onInitialize() {
        //Base Item
        Registry.register(Registry.ITEM, new Identifier(MODID, "copper_ingot"), CopperIngot);
        Registry.register(Registry.ITEM, new Identifier(MODID, "copper_powder"), CopperPowder);
        Registry.register(Registry.ITEM, new Identifier(MODID, "copper_plate"), CopperPlate);
        Registry.register(Registry.ITEM, new Identifier(MODID, "copper_cube"), CopperCube);
        Registry.register(Registry.ITEM, new Identifier(MODID, "aluminum_ingot"), AluminumIngot);
        Registry.register(Registry.ITEM, new Identifier(MODID, "aluminum_powder"), AluminumPowder);
        Registry.register(Registry.ITEM, new Identifier(MODID, "aluminum_plate"), AluminumPlate);
        Registry.register(Registry.ITEM, new Identifier(MODID, "aluminum_cube"), AluminumCube);
        Registry.register(Registry.ITEM, new Identifier(MODID, "iron_powder"), IronPowder);
        Registry.register(Registry.ITEM, new Identifier(MODID, "iron_plate"), IronPlate);
        Registry.register(Registry.ITEM, new Identifier(MODID, "iron_cube"), IronCube);
        Registry.register(Registry.ITEM, new Identifier(MODID, "gold_powder"), GoldPowder);
        Registry.register(Registry.ITEM, new Identifier(MODID, "gold_cube"), GoldCube);
        Registry.register(Registry.ITEM, new Identifier(MODID, "carbon_fibre"), CarbonFibre);
        Registry.register(Registry.ITEM, new Identifier(MODID, "carbon_powder"), CarbonPowder);
        Registry.register(Registry.ITEM, new Identifier(MODID, "carbon_plate"), CarbonPlate);
        Registry.register(Registry.ITEM, new Identifier(MODID, "carbon_stick"), CarbonStick);
        Registry.register(Registry.ITEM, new Identifier(MODID, "tin_ingot"), TinIngot);
        Registry.register(Registry.ITEM, new Identifier(MODID, "tin_powder"), TinPowder);
        Registry.register(Registry.ITEM, new Identifier(MODID, "tin_plate"), TinPlate);
        Registry.register(Registry.ITEM, new Identifier(MODID, "tin_cube"), TinCube);
        Registry.register(Registry.ITEM, new Identifier(MODID, "lead_ingot"), LeadIngot);
        Registry.register(Registry.ITEM, new Identifier(MODID, "lead_powder"), LeadPowder);
        Registry.register(Registry.ITEM, new Identifier(MODID, "lead_plate"), LeadPlate);
        Registry.register(Registry.ITEM, new Identifier(MODID, "lead_cube"), LeadCube);
        Registry.register(Registry.ITEM, new Identifier(MODID, "silver_ingot"), SilverIngot);
        Registry.register(Registry.ITEM, new Identifier(MODID, "silver_powder"), SilverPowder);
        Registry.register(Registry.ITEM, new Identifier(MODID, "silver_plate"), SilverPlate);
        Registry.register(Registry.ITEM, new Identifier(MODID, "silver_cube"), SilverCube);
        Registry.register(Registry.ITEM, new Identifier(MODID, "stone_ingot"), StoneIngot);
        Registry.register(Registry.ITEM, new Identifier(MODID, "stone_plate"), StonePlate);
        Registry.register(Registry.ITEM, new Identifier(MODID, "rust"), Rust);
        Registry.register(Registry.ITEM, new Identifier(MODID, "rusty_iron_ingot"), RustyIronIngot);
        Registry.register(Registry.ITEM, new Identifier(MODID, "steel_ingot"), SteelIngot);
        Registry.register(Registry.ITEM, new Identifier(MODID, "raw_steel_ingot"), RawSteelIngot);
        //Nature Item
        Registry.register(Registry.ITEM, new Identifier(MODID, "tiny_rock"), TinyRock);
        Registry.register(Registry.ITEM, new Identifier(MODID, "stone_chippings"), StoneChippings);
        Registry.register(Registry.ITEM, new Identifier(MODID, "rich_ore_stone_chippings"), RichOreStoneChippings);
        Registry.register(Registry.ITEM, new Identifier(MODID, "tiny_flint"), TinyFlint);
        Registry.register(Registry.ITEM, new Identifier(MODID, "bone_fragments"), BoneFragments);
        Registry.register(Registry.ITEM, new Identifier(MODID, "grass_stem"), GrassStem);
        Registry.register(Registry.ITEM, new Identifier(MODID, "rattan"), Rattan);
        Registry.register(Registry.ITEM, new Identifier(MODID, "soil_heaps"), SoilHeaps);
        Registry.register(Registry.ITEM, new Identifier(MODID, "wooden_staff"), WoodenStaff);
        Registry.register(Registry.ITEM, new Identifier(MODID, "turbid_fragment_of_obsidian"), TurbidFragmentOfObsidian);
        Registry.register(Registry.ITEM, new Identifier(MODID, "pure_fragment_of_obsidian"), PureFragmentOfObsidian);
        Registry.register(Registry.ITEM, new Identifier(MODID, "turbid_obsidian"), TurbidObsidian);
        Registry.register(Registry.ITEM, new Identifier(MODID, "pure_obsidian"), PureObsidian);
        //Food
        Registry.register(Registry.ITEM, new Identifier(MODID, "apple_piece"), ApplePiece);
        //Magic Item
        Registry.register(Registry.ITEM, new Identifier(MODID, "ruby"), Ruby);
        Registry.register(Registry.ITEM, new Identifier(MODID, "sapphire"), Sapphire);
        Registry.register(Registry.ITEM, new Identifier(MODID, "topaz"), Topaz);
        Registry.register(Registry.ITEM, new Identifier(MODID, "nether_wart_slurries"), NetherWartSlurries);
        Registry.register(Registry.ITEM, new Identifier(MODID, "warped_wart_slurries"), WarpedWartSlurries);
        Registry.register(Registry.ITEM, new Identifier(MODID, "blackgold_ingot"), BlackgoldIngot);
        Registry.register(Registry.ITEM, new Identifier(MODID, "end_pearl"), EndPearl);
        Registry.register(Registry.ITEM, new Identifier(MODID, "end_pearl_powder"), EndPearlPowder);
        Registry.register(Registry.ITEM, new Identifier(MODID, "end_pearl_stick"), EndPearlStick);
        //Special
        Registry.register(Registry.ITEM, new Identifier(MODID, "pda"), PDA);
        Registry.register(Registry.ITEM, new Identifier(MODID, "copper_wrench"), CopperWrench);
        Registry.register(Registry.ITEM, new Identifier(MODID, "blueprint"), Blueprint);
        Registry.register(Registry.ITEM, new Identifier(MODID, "empty_blueprint"), EmptyBlueprint);
        //Tool
        Registry.register(Registry.ITEM, new Identifier(MODID, "rough_hatchet"), RoughHatchet);
        Registry.register(Registry.ITEM, new Identifier(MODID, "rough_knife"), RoughKnife);
        Registry.register(Registry.ITEM, new Identifier(MODID, "copper_axe_part"), CopperAxePart);
        Registry.register(Registry.ITEM, new Identifier(MODID, "copper_sword_part"), CopperSwordPart);
        Registry.register(Registry.ITEM, new Identifier(MODID, "copper_pickaxe_part"), CopperPickaxePart);
        Registry.register(Registry.ITEM, new Identifier(MODID, "copper_shovel_part"), CopperShovelPart);
        Registry.register(Registry.ITEM, new Identifier(MODID, "copper_hoe_part"), CopperHoePart);
        Registry.register(Registry.ITEM, new Identifier(MODID, "copper_axe"), CopperAxe);
        Registry.register(Registry.ITEM, new Identifier(MODID, "copper_sword"), CopperSword);
        Registry.register(Registry.ITEM, new Identifier(MODID, "copper_pickaxe"), CopperPickaxe);
        Registry.register(Registry.ITEM, new Identifier(MODID, "copper_shovel"), CopperShovel);
        Registry.register(Registry.ITEM, new Identifier(MODID, "copper_hoe"), CopperHoe);
        Registry.register(Registry.ITEM, new Identifier(MODID, "aluminum_axe"), AluminumAxe);
        Registry.register(Registry.ITEM, new Identifier(MODID, "aluminum_sword"), AluminumSword);
        Registry.register(Registry.ITEM, new Identifier(MODID, "aluminum_pickaxe"), AluminumPickaxe);
        Registry.register(Registry.ITEM, new Identifier(MODID, "aluminum_shovel"), AluminumShovel);
        Registry.register(Registry.ITEM, new Identifier(MODID, "aluminum_hoe"), AluminumHoe);
        Registry.register(Registry.ITEM, new Identifier(MODID, "only_iron_axe"), OnlyIronAxe);
        Registry.register(Registry.ITEM, new Identifier(MODID, "only_iron_sword"), OnlyIronSword);
        Registry.register(Registry.ITEM, new Identifier(MODID, "only_iron_pickaxe"), OnlyIronPickaxe);
        Registry.register(Registry.ITEM, new Identifier(MODID, "only_iron_shovel"), OnlyIronShovel);
        Registry.register(Registry.ITEM, new Identifier(MODID, "only_iron_hoe"), OnlyIronHoe);
        Registry.register(Registry.ITEM, new Identifier(MODID, "ruggedized_aluminum_axe"), RuggedizedAluminumAxe);
        Registry.register(Registry.ITEM, new Identifier(MODID, "ruggedized_aluminum_sword"), RuggedizedAluminumSword);
        Registry.register(Registry.ITEM, new Identifier(MODID, "ruggedized_aluminum_pickaxe"), RuggedizedAluminumPickaxe);
        Registry.register(Registry.ITEM, new Identifier(MODID, "ruggedized_aluminum_shovel"), RuggedizedAluminumShovel);
        Registry.register(Registry.ITEM, new Identifier(MODID, "ruggedized_aluminum_hoe"), RuggedizedAluminumHoe);
        Registry.register(Registry.ITEM, new Identifier(MODID, "stonebrick_axe"), StoneBrickAxe);
        Registry.register(Registry.ITEM, new Identifier(MODID, "stonebrick_sword"), StoneBrickSword);
        Registry.register(Registry.ITEM, new Identifier(MODID, "stonebrick_pickaxe"), StoneBrickPickaxe);
        Registry.register(Registry.ITEM, new Identifier(MODID, "stonebrick_shovel"), StoneBrickShovel);
        Registry.register(Registry.ITEM, new Identifier(MODID, "stonebrick_hoe"), StoneBrickHoe);
        Registry.register(Registry.ITEM, new Identifier(MODID, "steel_axe"), SteelAxe);
        Registry.register(Registry.ITEM, new Identifier(MODID, "steel_sword"), SteelSword);
        Registry.register(Registry.ITEM, new Identifier(MODID, "steel_pickaxe"), SteelPickaxe);
        Registry.register(Registry.ITEM, new Identifier(MODID, "steel_shovel"), SteelShovel);
        Registry.register(Registry.ITEM, new Identifier(MODID, "steel_hoe"), SteelHoe);
        Registry.register(Registry.ITEM, new Identifier(MODID, "obsidian_axe"), ObsidianAxe);
        Registry.register(Registry.ITEM, new Identifier(MODID, "obsidian_sword"), ObsidianSword);
        Registry.register(Registry.ITEM, new Identifier(MODID, "obsidian_pickaxe"), ObsidianPickaxe);
        Registry.register(Registry.ITEM, new Identifier(MODID, "obsidian_shovel"), ObsidianShovel);
        Registry.register(Registry.ITEM, new Identifier(MODID, "obsidian_hoe"), ObsidianHoe);
        //Material Block
        Registry.register(Registry.BLOCK, new Identifier(MODID, "test_ore"),TestOre);
        Registry.register(Registry.ITEM, new Identifier(MODID, "test_ore"), new BlockItem(TestOre, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier(MODID, "forgotten_iron_ore"), ForgottenIronOre);
        Registry.register(Registry.ITEM, new Identifier(MODID, "forgotten_iron_ore"), new BlockItem(ForgottenIronOre, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier(MODID, "chiseled_iron_block"), ChiseledIronBlock);
        Registry.register(Registry.ITEM, new Identifier(MODID, "chiseled_iron_block"), new BlockItem(ChiseledIronBlock, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier(MODID, "copper_ore"), CopperOre);
        Registry.register(Registry.ITEM, new Identifier(MODID, "copper_ore"), new BlockItem(CopperOre, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier(MODID, "copper_block"), CopperBlock);
        Registry.register(Registry.ITEM, new Identifier(MODID, "copper_block"), new BlockItem(CopperBlock, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier(MODID, "aluminum_ore"), AluminumOre);
        Registry.register(Registry.ITEM, new Identifier(MODID, "aluminum_ore"), new BlockItem(AluminumOre, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier(MODID, "aluminum_block"), AluminumBlock);
        Registry.register(Registry.ITEM, new Identifier(MODID, "aluminum_block"), new BlockItem(AluminumBlock, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier(MODID, "tin_ore"), TinOre);
        Registry.register(Registry.ITEM, new Identifier(MODID, "tin_ore"), new BlockItem(TinOre, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier(MODID, "tin_block"), TinBlock);
        Registry.register(Registry.ITEM, new Identifier(MODID, "tin_block"), new BlockItem(TinBlock, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier(MODID, "lead_ore"), LeadOre);
        Registry.register(Registry.ITEM, new Identifier(MODID, "lead_ore"), new BlockItem(LeadOre, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier(MODID, "lead_block"), LeadBlock);
        Registry.register(Registry.ITEM, new Identifier(MODID, "lead_block"), new BlockItem(LeadBlock, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier(MODID, "silver_ore"), SilverOre);
        Registry.register(Registry.ITEM, new Identifier(MODID, "silver_ore"), new BlockItem(SilverOre, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier(MODID, "silver_block"), SilverBlock);
        Registry.register(Registry.ITEM, new Identifier(MODID, "silver_block"), new BlockItem(SilverBlock, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier(MODID, "old_command_block"), OldCommandBlock);
        Registry.register(Registry.ITEM, new Identifier(MODID, "old_command_block"), new BlockItem(OldCommandBlock, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier(MODID, "old_reactor"), OldReactor);
        Registry.register(Registry.ITEM, new Identifier(MODID, "old_reactor"), new BlockItem(OldReactor, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier(MODID, "ruby_ore"), RubyOre);
        Registry.register(Registry.ITEM, new Identifier(MODID, "ruby_ore"), new BlockItem(RubyOre, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier(MODID, "ruby_block"), RubyBlock);
        Registry.register(Registry.ITEM, new Identifier(MODID, "ruby_block"), new BlockItem(RubyBlock, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier(MODID, "sapphire_ore"), SapphireOre);
        Registry.register(Registry.ITEM, new Identifier(MODID, "sapphire_ore"), new BlockItem(SapphireOre, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier(MODID, "sapphire_block"), SapphireBlock);
        Registry.register(Registry.ITEM, new Identifier(MODID, "sapphire_block"), new BlockItem(SapphireBlock, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier(MODID, "topaz_ore"), TopazOre);
        Registry.register(Registry.ITEM, new Identifier(MODID, "topaz_ore"), new BlockItem(TopazOre, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier(MODID, "topaz_block"), TopazBlock);
        Registry.register(Registry.ITEM, new Identifier(MODID, "topaz_block"), new BlockItem(TopazBlock, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier(MODID, "steel_block"), SteelBlock);
        Registry.register(Registry.ITEM, new Identifier(MODID, "steel_block"), new BlockItem(SteelBlock, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier(MODID, "turbid_obsidian_block"), TurbidObsidianBlock);
        Registry.register(Registry.ITEM, new Identifier(MODID, "turbid_obsidian_block"), new BlockItem(TurbidObsidianBlock, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier(MODID, "pure_obsidian_block"), PureObsidianBlock);
        Registry.register(Registry.ITEM, new Identifier(MODID, "pure_obsidian_block"), new BlockItem(PureObsidianBlock, new Item.Settings()));
        //Colour Block
        Registry.register(Registry.BLOCK, new Identifier("tofurtherworld", "white_lego"),WhiteLego);
        Registry.register(Registry.ITEM, new Identifier("tofurtherworld", "white_lego"), new BlockItem(WhiteLego, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier("tofurtherworld", "orange_lego"),OrangeLego);
        Registry.register(Registry.ITEM, new Identifier("tofurtherworld", "orange_lego"), new BlockItem(OrangeLego, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier("tofurtherworld", "magenta_lego"),MagentaLego);
        Registry.register(Registry.ITEM, new Identifier("tofurtherworld", "magenta_lego"), new BlockItem(MagentaLego, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier("tofurtherworld", "lightblue_lego"),LightBlueLego);
        Registry.register(Registry.ITEM, new Identifier("tofurtherworld", "lightblue_lego"), new BlockItem(LightBlueLego, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier("tofurtherworld", "yellow_lego"),YellowLego);
        Registry.register(Registry.ITEM, new Identifier("tofurtherworld", "yellow_lego"), new BlockItem(YellowLego, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier("tofurtherworld", "lime_lego"),LimeLego);
        Registry.register(Registry.ITEM, new Identifier("tofurtherworld", "lime_lego"), new BlockItem(LimeLego, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier("tofurtherworld", "pink_lego"),PinkLego);
        Registry.register(Registry.ITEM, new Identifier("tofurtherworld", "pink_lego"), new BlockItem(PinkLego, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier("tofurtherworld", "gray_lego"),GrayLego);
        Registry.register(Registry.ITEM, new Identifier("tofurtherworld", "gray_lego"), new BlockItem(GrayLego, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier("tofurtherworld", "lightgray_lego"),LightGrayLego);
        Registry.register(Registry.ITEM, new Identifier("tofurtherworld", "lightgray_lego"), new BlockItem(LightGrayLego, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier("tofurtherworld", "cyan_lego"),CyanLego);
        Registry.register(Registry.ITEM, new Identifier("tofurtherworld", "cyan_lego"), new BlockItem(CyanLego, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier("tofurtherworld", "purple_lego"),PurpleLego);
        Registry.register(Registry.ITEM, new Identifier("tofurtherworld", "purple_lego"), new BlockItem(PurpleLego, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier("tofurtherworld", "blue_lego"),BlueLego);
        Registry.register(Registry.ITEM, new Identifier("tofurtherworld", "blue_lego"), new BlockItem(BlueLego, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier("tofurtherworld", "brown_lego"),BrownLego);
        Registry.register(Registry.ITEM, new Identifier("tofurtherworld", "brown_lego"), new BlockItem(BrownLego, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier("tofurtherworld", "green_lego"),GreenLego);
        Registry.register(Registry.ITEM, new Identifier("tofurtherworld", "green_lego"), new BlockItem(GreenLego, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier("tofurtherworld", "red_lego"),RedLego);
        Registry.register(Registry.ITEM, new Identifier("tofurtherworld", "red_lego"), new BlockItem(RedLego, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier("tofurtherworld", "black_lego"),BlackLego);
        Registry.register(Registry.ITEM, new Identifier("tofurtherworld", "black_lego"), new BlockItem(BlackLego, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier("tofurtherworld", "white_seamless_glass"),WhiteSeamlessGlass);
        Registry.register(Registry.ITEM, new Identifier("tofurtherworld", "white_seamless_glass"), new BlockItem(WhiteSeamlessGlass, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier("tofurtherworld", "orange_seamless_glass"),OrangeSeamlessGlass);
        Registry.register(Registry.ITEM, new Identifier("tofurtherworld", "orange_seamless_glass"), new BlockItem(OrangeSeamlessGlass, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier("tofurtherworld", "magenta_seamless_glass"),MagentaSeamlessGlass);
        Registry.register(Registry.ITEM, new Identifier("tofurtherworld", "magenta_seamless_glass"), new BlockItem(MagentaSeamlessGlass, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier("tofurtherworld", "lightblue_seamless_glass"),LightBlueSeamlessGlass);
        Registry.register(Registry.ITEM, new Identifier("tofurtherworld", "lightblue_seamless_glass"), new BlockItem(LightBlueSeamlessGlass, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier("tofurtherworld", "yellow_seamless_glass"),YellowSeamlessGlass);
        Registry.register(Registry.ITEM, new Identifier("tofurtherworld", "yellow_seamless_glass"), new BlockItem(YellowSeamlessGlass, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier("tofurtherworld", "lime_seamless_glass"),LimeSeamlessGlass);
        Registry.register(Registry.ITEM, new Identifier("tofurtherworld", "lime_seamless_glass"), new BlockItem(LimeSeamlessGlass, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier("tofurtherworld", "pink_seamless_glass"),PinkSeamlessGlass);
        Registry.register(Registry.ITEM, new Identifier("tofurtherworld", "pink_seamless_glass"), new BlockItem(PinkSeamlessGlass, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier("tofurtherworld", "gray_seamless_glass"),GraySeamlessGlass);
        Registry.register(Registry.ITEM, new Identifier("tofurtherworld", "gray_seamless_glass"), new BlockItem(GraySeamlessGlass, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier("tofurtherworld", "lightgray_seamless_glass"),LightGraySeamlessGlass);
        Registry.register(Registry.ITEM, new Identifier("tofurtherworld", "lightgray_seamless_glass"), new BlockItem(LightGraySeamlessGlass, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier("tofurtherworld", "cyan_seamless_glass"),CyanSeamlessGlass);
        Registry.register(Registry.ITEM, new Identifier("tofurtherworld", "cyan_seamless_glass"), new BlockItem(CyanSeamlessGlass, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier("tofurtherworld", "purple_seamless_glass"),PurpleSeamlessGlass);
        Registry.register(Registry.ITEM, new Identifier("tofurtherworld", "purple_seamless_glass"), new BlockItem(PurpleSeamlessGlass, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier("tofurtherworld", "blue_seamless_glass"),BlueSeamlessGlass);
        Registry.register(Registry.ITEM, new Identifier("tofurtherworld", "blue_seamless_glass"), new BlockItem(BlueSeamlessGlass, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier("tofurtherworld", "brown_seamless_glass"),BrownSeamlessGlass);
        Registry.register(Registry.ITEM, new Identifier("tofurtherworld", "brown_seamless_glass"), new BlockItem(BrownSeamlessGlass, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier("tofurtherworld", "green_seamless_glass"),GreenSeamlessGlass);
        Registry.register(Registry.ITEM, new Identifier("tofurtherworld", "green_seamless_glass"), new BlockItem(GreenSeamlessGlass, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier("tofurtherworld", "red_seamless_glass"),RedSeamlessGlass);
        Registry.register(Registry.ITEM, new Identifier("tofurtherworld", "red_seamless_glass"), new BlockItem(RedSeamlessGlass, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier("tofurtherworld", "black_seamless_glass"),BlackSeamlessGlass);
        Registry.register(Registry.ITEM, new Identifier("tofurtherworld", "black_seamless_glass"), new BlockItem(BlackSeamlessGlass, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier("tofurtherworld", "white_glowing_glass"),WhiteGlowingGlass);
        Registry.register(Registry.ITEM, new Identifier("tofurtherworld", "white_glowing_glass"), new BlockItem(WhiteGlowingGlass, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier("tofurtherworld", "orange_glowing_glass"),OrangeGlowingGlass);
        Registry.register(Registry.ITEM, new Identifier("tofurtherworld", "orange_glowing_glass"), new BlockItem(OrangeGlowingGlass, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier("tofurtherworld", "magenta_glowing_glass"),MagentaGlowingGlass);
        Registry.register(Registry.ITEM, new Identifier("tofurtherworld", "magenta_glowing_glass"), new BlockItem(MagentaGlowingGlass, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier("tofurtherworld", "lightblue_glowing_glass"),LightBlueGlowingGlass);
        Registry.register(Registry.ITEM, new Identifier("tofurtherworld", "lightblue_glowing_glass"), new BlockItem(LightBlueGlowingGlass, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier("tofurtherworld", "yellow_glowing_glass"),YellowGlowingGlass);
        Registry.register(Registry.ITEM, new Identifier("tofurtherworld", "yellow_glowing_glass"), new BlockItem(YellowGlowingGlass, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier("tofurtherworld", "lime_glowing_glass"),LimeGlowingGlass);
        Registry.register(Registry.ITEM, new Identifier("tofurtherworld", "lime_glowing_glass"), new BlockItem(LimeGlowingGlass, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier("tofurtherworld", "pink_glowing_glass"),PinkGlowingGlass);
        Registry.register(Registry.ITEM, new Identifier("tofurtherworld", "pink_glowing_glass"), new BlockItem(PinkGlowingGlass, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier("tofurtherworld", "gray_glowing_glass"),GrayGlowingGlass);
        Registry.register(Registry.ITEM, new Identifier("tofurtherworld", "gray_glowing_glass"), new BlockItem(GrayGlowingGlass, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier("tofurtherworld", "lightgray_glowing_glass"),LightGrayGlowingGlass);
        Registry.register(Registry.ITEM, new Identifier("tofurtherworld", "lightgray_glowing_glass"), new BlockItem(LightGrayGlowingGlass, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier("tofurtherworld", "cyan_glowing_glass"),CyanGlowingGlass);
        Registry.register(Registry.ITEM, new Identifier("tofurtherworld", "cyan_glowing_glass"), new BlockItem(CyanGlowingGlass, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier("tofurtherworld", "purple_glowing_glass"),PurpleGlowingGlass);
        Registry.register(Registry.ITEM, new Identifier("tofurtherworld", "purple_glowing_glass"), new BlockItem(PurpleGlowingGlass, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier("tofurtherworld", "blue_glowing_glass"),BlueGlowingGlass);
        Registry.register(Registry.ITEM, new Identifier("tofurtherworld", "blue_glowing_glass"), new BlockItem(BlueGlowingGlass, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier("tofurtherworld", "brown_glowing_glass"),BrownGlowingGlass);
        Registry.register(Registry.ITEM, new Identifier("tofurtherworld", "brown_glowing_glass"), new BlockItem(BrownGlowingGlass, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier("tofurtherworld", "green_glowing_glass"),GreenGlowingGlass);
        Registry.register(Registry.ITEM, new Identifier("tofurtherworld", "green_glowing_glass"), new BlockItem(GreenGlowingGlass, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier("tofurtherworld", "red_glowing_glass"),RedGlowingGlass);
        Registry.register(Registry.ITEM, new Identifier("tofurtherworld", "red_glowing_glass"), new BlockItem(RedGlowingGlass, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier("tofurtherworld", "black_glowing_glass"),BlackGlowingGlass);
        Registry.register(Registry.ITEM, new Identifier("tofurtherworld", "black_glowing_glass"), new BlockItem(BlackGlowingGlass, new Item.Settings()));
        //Ore gen
        Registry.BIOME.forEach(this::CopperGen);RegistryEntryAddedCallback.event(Registry.BIOME).register((i, identifier, biome) -> CopperGen(biome));
        Registry.BIOME.forEach(this::AluminumGen);RegistryEntryAddedCallback.event(Registry.BIOME).register((i, identifier, biome) -> AluminumGen(biome));
        Registry.BIOME.forEach(this::TinGen);RegistryEntryAddedCallback.event(Registry.BIOME).register((i, identifier, biome) -> TinGen(biome));
        Registry.BIOME.forEach(this::LeadGen);RegistryEntryAddedCallback.event(Registry.BIOME).register((i, identifier, biome) -> LeadGen(biome));
        Registry.BIOME.forEach(this::SilverGen);RegistryEntryAddedCallback.event(Registry.BIOME).register((i, identifier, biome) -> SilverGen(biome));
        Registry.BIOME.forEach(this::ForgottenIronGen);RegistryEntryAddedCallback.event(Registry.BIOME).register((i, identifier, biome) -> ForgottenIronGen(biome));
        Registry.BIOME.forEach(this::RubyGen);RegistryEntryAddedCallback.event(Registry.BIOME).register((i, identifier, biome) -> RubyGen(biome));
        Registry.BIOME.forEach(this::SapphireGen);RegistryEntryAddedCallback.event(Registry.BIOME).register((i, identifier, biome) -> SapphireGen(biome));
        Registry.BIOME.forEach(this::TopazGen);RegistryEntryAddedCallback.event(Registry.BIOME).register((i, identifier, biome) -> TopazGen(biome));
        //Entity Block
        Registry.register(Registry.BLOCK, new Identifier("tofurtherworld", "drawer_1"), Drawer1);
        Registry.register(Registry.ITEM, new Identifier("tofurtherworld", "drawer_1"), new BlockItem(Drawer1, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier("tofurtherworld", "drawer_2"), Drawer2);
        Registry.register(Registry.ITEM, new Identifier("tofurtherworld", "drawer_2"), new BlockItem(Drawer2, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier("tofurtherworld", "forging_table_l1"), ForgingTableL1);
        Registry.register(Registry.ITEM, new Identifier("tofurtherworld", "forging_table_l1"), new BlockItem(ForgingTableL1, new Item.Settings()));
        //Furniture
        Registry.register(Registry.BLOCK, new Identifier("tofurtherworld", "wooden_chair"), WoodenChair);
        Registry.register(Registry.ITEM, new Identifier("tofurtherworld", "wooden_chair"), new BlockItem(WoodenChair, new Item.Settings()));
        //Nature block
        Registry.register(Registry.BLOCK, new Identifier(MODID, "apple_block"), AppleBlock);
        Registry.register(Registry.ITEM, new Identifier(MODID, "apple_block"), new BlockItem(AppleBlock, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier(MODID, "icebirch_sapling"), IceBirchSapling);
        Registry.register(Registry.ITEM, new Identifier(MODID, "icebirch_sapling"), new BlockItem(IceBirchSapling, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier(MODID, "icebirch_log"), IceBirchLog);
        Registry.register(Registry.ITEM, new Identifier(MODID, "icebirch_log"), new BlockItem(IceBirchLog, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier(MODID, "icebirch_leaves"), IceBirchLeaves);
        Registry.register(Registry.ITEM, new Identifier(MODID, "icebirch_leaves"), new BlockItem(IceBirchLeaves, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier(MODID, "icebirch_planks"), IceBirchPlanks);
        Registry.register(Registry.ITEM, new Identifier(MODID, "icebirch_planks"), new BlockItem(IceBirchPlanks, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier(MODID, "stripped_icebirch_log"), StrippedIceBirchLog);
        Registry.register(Registry.ITEM, new Identifier(MODID, "stripped_icebirch_log"), new BlockItem(StrippedIceBirchLog, new Item.Settings()));
        Registry.register(Registry.BLOCK, new Identifier(MODID, "frozen_bush"), FrozenBush);
        Registry.register(Registry.ITEM, new Identifier(MODID, "frozen_bush"), new BlockItem(FrozenBush, new Item.Settings()));
        //Biome
        OverworldBiomes.addBiomeVariant(Biomes.DESERT,BiomeMain.PoorDesert,0.1);
    }
    public static final ItemGroup MATERIAL = FabricItemGroupBuilder.create(
            new Identifier(MODID, "material"))
            .icon(() -> new ItemStack(AluminumIngot))
            .appendItems(stacks ->{
                stacks.add(new ItemStack(CopperIngot));
                stacks.add(new ItemStack(CopperPowder));
                stacks.add(new ItemStack(CopperPlate));
                stacks.add(new ItemStack(CopperCube));
                stacks.add(new ItemStack(AluminumIngot));
                stacks.add(new ItemStack(AluminumPowder));
                stacks.add(new ItemStack(AluminumPlate));
                stacks.add(new ItemStack(AluminumCube));
                stacks.add(new ItemStack(IronPowder));
                stacks.add(new ItemStack(IronPlate));
                stacks.add(new ItemStack(IronCube));
                stacks.add(new ItemStack(GoldPowder));
                stacks.add(new ItemStack(GoldCube));
                stacks.add(new ItemStack(CarbonFibre));
                stacks.add(new ItemStack(CarbonPowder));
                stacks.add(new ItemStack(CarbonPlate));
                stacks.add(new ItemStack(CarbonStick));
                stacks.add(new ItemStack(TinIngot));
                stacks.add(new ItemStack(TinPowder));
                stacks.add(new ItemStack(TinPlate));
                stacks.add(new ItemStack(TinCube));
                stacks.add(new ItemStack(LeadIngot));
                stacks.add(new ItemStack(LeadPowder));
                stacks.add(new ItemStack(LeadPlate));
                stacks.add(new ItemStack(LeadCube));
                stacks.add(new ItemStack(SilverIngot));
                stacks.add(new ItemStack(SilverPowder));
                stacks.add(new ItemStack(SilverPlate));
                stacks.add(new ItemStack(SilverCube));
                stacks.add(new ItemStack(StoneIngot));
                stacks.add(new ItemStack(StonePlate));
                stacks.add(new ItemStack(Rust));
                stacks.add(new ItemStack(RustyIronIngot));
                stacks.add(new ItemStack(RawSteelIngot));
                stacks.add(new ItemStack(SteelIngot));
                stacks.add(new ItemStack(TinyRock));
                stacks.add(new ItemStack(StoneChippings));
                stacks.add(new ItemStack(RichOreStoneChippings));
                stacks.add(new ItemStack(TinyFlint));
                stacks.add(new ItemStack(BoneFragments));
                stacks.add(new ItemStack(GrassStem));
                stacks.add(new ItemStack(Rattan));
                stacks.add(new ItemStack(SoilHeaps));
                stacks.add(new ItemStack(WoodenStaff));
                stacks.add(new ItemStack(TurbidFragmentOfObsidian));
                stacks.add(new ItemStack(PureFragmentOfObsidian));
                stacks.add(new ItemStack(TurbidObsidian));
                stacks.add(new ItemStack(PureObsidian));
                stacks.add(new ItemStack(Ruby));
                stacks.add(new ItemStack(Sapphire));
                stacks.add(new ItemStack(Topaz));
                stacks.add(new ItemStack(NetherWartSlurries));
                stacks.add(new ItemStack(WarpedWartSlurries));
                stacks.add(new ItemStack(BlackgoldIngot));
                stacks.add(new ItemStack(EndPearl));
                stacks.add(new ItemStack(EndPearlPowder));
                stacks.add(new ItemStack(EndPearlStick));
                stacks.add(new ItemStack(ApplePiece));
                    }
            ).build();
    public static final ItemGroup BLOCK = FabricItemGroupBuilder.create(
            new Identifier(MODID, "block"))
            .icon(() -> new ItemStack(TestOre))
            .appendItems(stacks ->{
                stacks.add(new ItemStack(TestOre));
                stacks.add(new ItemStack(ForgottenIronOre));
                stacks.add(new ItemStack(ChiseledIronBlock));
                stacks.add(new ItemStack(CopperOre));
                stacks.add(new ItemStack(CopperBlock));
                stacks.add(new ItemStack(AluminumOre));
                stacks.add(new ItemStack(AluminumBlock));
                stacks.add(new ItemStack(TinOre));
                stacks.add(new ItemStack(TinBlock));
                stacks.add(new ItemStack(LeadOre));
                stacks.add(new ItemStack(LeadBlock));
                stacks.add(new ItemStack(SilverOre));
                stacks.add(new ItemStack(SilverBlock));
                stacks.add(new ItemStack(SteelBlock));
                stacks.add(new ItemStack(RubyOre));
                stacks.add(new ItemStack(RubyBlock));
                stacks.add(new ItemStack(SapphireOre));
                stacks.add(new ItemStack(SapphireBlock));
                stacks.add(new ItemStack(TopazOre));
                stacks.add(new ItemStack(TopazBlock));
                stacks.add(new ItemStack(TurbidObsidianBlock));
                stacks.add(new ItemStack(PureObsidianBlock));
                stacks.add(new ItemStack(OldCommandBlock));
                stacks.add(new ItemStack(OldReactor));
                stacks.add(new ItemStack(WhiteLego));
                stacks.add(new ItemStack(OrangeLego));
                stacks.add(new ItemStack(MagentaLego));
                stacks.add(new ItemStack(LightBlueLego));
                stacks.add(new ItemStack(YellowLego));
                stacks.add(new ItemStack(LimeLego));
                stacks.add(new ItemStack(PinkLego));
                stacks.add(new ItemStack(GrayLego));
                stacks.add(new ItemStack(LightGrayLego));
                stacks.add(new ItemStack(CyanLego));
                stacks.add(new ItemStack(PurpleLego));
                stacks.add(new ItemStack(BlueLego));
                stacks.add(new ItemStack(BrownLego));
                stacks.add(new ItemStack(GreenLego));
                stacks.add(new ItemStack(RedLego));
                stacks.add(new ItemStack(BlackLego));
                stacks.add(new ItemStack(WhiteSeamlessGlass));
                stacks.add(new ItemStack(OrangeSeamlessGlass));
                stacks.add(new ItemStack(MagentaSeamlessGlass));
                stacks.add(new ItemStack(LightBlueSeamlessGlass));
                stacks.add(new ItemStack(YellowSeamlessGlass));
                stacks.add(new ItemStack(LimeSeamlessGlass));
                stacks.add(new ItemStack(PinkSeamlessGlass));
                stacks.add(new ItemStack(GraySeamlessGlass));
                stacks.add(new ItemStack(LightGraySeamlessGlass));
                stacks.add(new ItemStack(CyanSeamlessGlass));
                stacks.add(new ItemStack(PurpleSeamlessGlass));
                stacks.add(new ItemStack(BlueSeamlessGlass));
                stacks.add(new ItemStack(BrownSeamlessGlass));
                stacks.add(new ItemStack(GreenSeamlessGlass));
                stacks.add(new ItemStack(RedSeamlessGlass));
                stacks.add(new ItemStack(BlackSeamlessGlass));
                stacks.add(new ItemStack(WhiteGlowingGlass));
                stacks.add(new ItemStack(OrangeGlowingGlass));
                stacks.add(new ItemStack(MagentaGlowingGlass));
                stacks.add(new ItemStack(LightBlueGlowingGlass));
                stacks.add(new ItemStack(YellowGlowingGlass));
                stacks.add(new ItemStack(LimeGlowingGlass));
                stacks.add(new ItemStack(PinkGlowingGlass));
                stacks.add(new ItemStack(GrayGlowingGlass));
                stacks.add(new ItemStack(LightGrayGlowingGlass));
                stacks.add(new ItemStack(CyanGlowingGlass));
                stacks.add(new ItemStack(PurpleGlowingGlass));
                stacks.add(new ItemStack(BlueGlowingGlass));
                stacks.add(new ItemStack(BrownGlowingGlass));
                stacks.add(new ItemStack(GreenGlowingGlass));
                stacks.add(new ItemStack(RedGlowingGlass));
                stacks.add(new ItemStack(BlackGlowingGlass));
                stacks.add(new ItemStack(Drawer1));
                stacks.add(new ItemStack(Drawer2));
                stacks.add(new ItemStack(ForgingTableL1));
                stacks.add(new ItemStack(AppleBlock));
                stacks.add(new ItemStack(IceBirchSapling));
                stacks.add(new ItemStack(IceBirchLog));
                stacks.add(new ItemStack(IceBirchLeaves));
                stacks.add(new ItemStack(IceBirchPlanks));
                stacks.add(new ItemStack(StrippedIceBirchLog));
                stacks.add(new ItemStack(FrozenBush));
                    }
            ).build();
    public static final ItemGroup ITEM = FabricItemGroupBuilder.create(
            new Identifier(MODID, "item"))
            .icon(() -> new ItemStack(PDA))
            .appendItems(stacks ->{
                stacks.add(new ItemStack(PDA));
                stacks.add(new ItemStack(CopperWrench));
                stacks.add(new ItemStack(Blueprint));
                stacks.add(new ItemStack(EmptyBlueprint));
                    }
            ).build();
    public static final ItemGroup TOOL = FabricItemGroupBuilder.create(
            new Identifier(MODID, "tool"))
            .icon(() -> new ItemStack(RoughHatchet))
            .appendItems(stacks ->{
                stacks.add(new ItemStack(RoughHatchet));
                stacks.add(new ItemStack(RoughKnife));
                stacks.add(new ItemStack(CopperAxePart));
                stacks.add(new ItemStack(CopperSwordPart));
                stacks.add(new ItemStack(CopperPickaxePart));
                stacks.add(new ItemStack(CopperShovelPart));
                stacks.add(new ItemStack(CopperHoePart));
                stacks.add(new ItemStack(CopperAxe));
                stacks.add(new ItemStack(CopperSword));
                stacks.add(new ItemStack(CopperPickaxe));
                stacks.add(new ItemStack(CopperShovel));
                stacks.add(new ItemStack(CopperHoe));
                stacks.add(new ItemStack(AluminumAxe));
                stacks.add(new ItemStack(AluminumSword));
                stacks.add(new ItemStack(AluminumPickaxe));
                stacks.add(new ItemStack(AluminumShovel));
                stacks.add(new ItemStack(AluminumHoe));
                stacks.add(new ItemStack(RuggedizedAluminumAxe));
                stacks.add(new ItemStack(RuggedizedAluminumSword));
                stacks.add(new ItemStack(RuggedizedAluminumPickaxe));
                stacks.add(new ItemStack(RuggedizedAluminumShovel));
                stacks.add(new ItemStack(RuggedizedAluminumHoe));
                stacks.add(new ItemStack(OnlyIronAxe));
                stacks.add(new ItemStack(OnlyIronSword));
                stacks.add(new ItemStack(OnlyIronPickaxe));
                stacks.add(new ItemStack(OnlyIronShovel));
                stacks.add(new ItemStack(OnlyIronHoe));
                stacks.add(new ItemStack(StoneBrickAxe));
                stacks.add(new ItemStack(StoneBrickSword));
                stacks.add(new ItemStack(StoneBrickPickaxe));
                stacks.add(new ItemStack(StoneBrickShovel));
                stacks.add(new ItemStack(StoneBrickHoe));
                stacks.add(new ItemStack(SteelAxe));
                stacks.add(new ItemStack(SteelSword));
                stacks.add(new ItemStack(SteelPickaxe));
                stacks.add(new ItemStack(SteelShovel));
                stacks.add(new ItemStack(SteelHoe));
                stacks.add(new ItemStack(ObsidianAxe));
                stacks.add(new ItemStack(ObsidianSword));
                stacks.add(new ItemStack(ObsidianPickaxe));
                stacks.add(new ItemStack(ObsidianShovel));
                stacks.add(new ItemStack(ObsidianHoe));
                    }
            ).build();

}