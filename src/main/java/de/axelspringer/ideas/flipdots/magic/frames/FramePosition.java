package de.axelspringer.ideas.flipdots.magic.frames;

public enum FramePosition {

    ALL(0xFF),
    UPPER(0x00),
    LOWER(0x00),
    UPPER_LEFT(0x01),
    LOWER_LEFT(0x02),
    UPPER_RIGHT(0x03),
    LOWER_RIGHT(0x04);

    public final int address;

    FramePosition(int address) {
        this.address = address;
    }
}
