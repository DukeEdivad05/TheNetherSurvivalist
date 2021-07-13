package gnotterosdecorations.blocks;

import net.minecraft.util.StringIdentifiable;

public enum VerticalSlabType implements StringIdentifiable {
    DOUBLE("double"),
    SINGLE("single");

    private final String name;

    VerticalSlabType(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

    public String asString() {
        return this.name;
    }
}
