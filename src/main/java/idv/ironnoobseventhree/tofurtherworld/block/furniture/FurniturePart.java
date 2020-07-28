package idv.ironnoobseventhree.tofurtherworld.block.furniture;

import net.minecraft.block.enums.BedPart;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;

public class FurniturePart{
    public static final EnumProperty<LongTableEnum> TablePart;
    static {
       TablePart = EnumProperty.of("part", LongTableEnum.class);
    }
}
