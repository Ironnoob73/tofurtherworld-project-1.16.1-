package idv.ironnoobseventhree.tofurtherworld.block.furniture;

import net.minecraft.state.property.EnumProperty;

public class FurniturePart{
    public static final EnumProperty<LongTableEnum> TablePart;
    static {
       TablePart = EnumProperty.of("part", LongTableEnum.class);
    }
}
