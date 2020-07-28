package idv.ironnoobseventhree.tofurtherworld.block.furniture;

import net.minecraft.util.StringIdentifiable;

public enum LongTableEnum implements StringIdentifiable {
    MIDDLE("middle"),
    LEFT("left"),
    RIGHT("right");

    private final String name;

    private LongTableEnum(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

    public String asString() {
        return this.name;
    }
}
