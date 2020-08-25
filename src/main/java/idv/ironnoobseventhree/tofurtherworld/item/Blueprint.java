package idv.ironnoobseventhree.tofurtherworld.item;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

public enum Blueprint {
    Common("common", 1),
    Normal("normal", 2),
    Uncommon("uncommon", 3),
    Rare("rare", 4),
    Htm("htm", 5),
    Epic("epic", 6),
    Mystical("mystical", 7),
    Gl("gl", 8),
    Ssr("ssr", 9),
    Junk("junk", 0);

    private final String name;
    private final int id;

    Blueprint(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Environment(EnvType.CLIENT)
    public String getName() {
        return this.name;
    }

    public int getId() {
        return this.id;
    }

}
