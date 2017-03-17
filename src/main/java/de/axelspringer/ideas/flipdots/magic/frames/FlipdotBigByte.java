package de.axelspringer.ideas.flipdots.magic.frames;

public class FlipdotBigByte {

    public static final FlipdotBigByte SPACE = new FlipdotBigByte(0, 0);
    public int upper;
    public int lower;

    public FlipdotBigByte(int upper, int lower) {
        this.upper = upper;
        this.lower = lower;
    }
}
