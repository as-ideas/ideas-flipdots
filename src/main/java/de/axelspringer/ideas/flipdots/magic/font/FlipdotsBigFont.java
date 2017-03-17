package de.axelspringer.ideas.flipdots.magic.font;

import de.axelspringer.ideas.flipdots.magic.frames.FlipdotBigByte;

import java.util.HashMap;

public class FlipdotsBigFont extends HashMap<Character, FlipdotBigByte[]> {


    public FlipdotsBigFont() {
        this.put(' ', bigFontArray(intA(0, 0), intA(0, 0)));
        this.put('A', bigFontArray(intA(0, 112, 120, 28, 14, 7, 7, 14, 28, 120, 112, 0), intA(0, 127, 127, 6, 6, 6, 6, 6, 6, 127, 127, 0)));
        this.put('B', bigFontArray(intA(0, 127, 127, 67, 67, 67, 67, 67, 103, 126, 60, 0), intA(0, 127, 127, 97, 97, 97, 97, 97, 115, 63, 30, 0)));
        this.put('C', bigFontArray(intA(0, 124, 126, 7, 3, 3, 3, 3, 7, 14, 12, 0), intA(0, 31, 63, 112, 96, 96, 96, 96, 112, 56, 24, 0)));
        this.put('D', bigFontArray(intA(0, 127, 127, 3, 3, 3, 3, 3, 7, 126, 124, 0), intA(0, 127, 127, 96, 96, 96, 96, 96, 112, 63, 31, 0)));
        this.put('E', bigFontArray(intA(0, 127, 127, 67, 67, 67, 67, 67, 67, 3, 3, 0), intA(0, 127, 127, 97, 97, 97, 97, 97, 97, 96, 96, 0)));
        this.put('F', bigFontArray(intA(0, 127, 127, 67, 67, 67, 67, 67, 67, 3, 3, 0), intA(0, 127, 127, 1, 1, 1, 1, 1, 1, 0, 0, 0)));
        this.put('G', bigFontArray(intA(0, 124, 126, 7, 3, 3, 67, 67, 71, 78, 76, 0), intA(0, 31, 63, 112, 96, 96, 97, 97, 113, 63, 31, 0)));
        this.put('H', bigFontArray(intA(0, 127, 127, 64, 64, 64, 64, 64, 64, 127, 127, 0), intA(0, 127, 127, 1, 1, 1, 1, 1, 1, 127, 127, 0)));
        this.put('I', bigFontArray(intA(0, 0, 0, 3, 3, 127, 127, 3, 3, 0, 0, 0), intA(0, 0, 0, 96, 96, 127, 127, 96, 96, 0, 0, 0)));
        this.put('J', bigFontArray(intA(0, 0, 0, 0, 0, 3, 3, 127, 127, 3, 3, 0), intA(0, 24, 56, 112, 96, 96, 112, 63, 31, 0, 0, 0)));
        this.put('K', bigFontArray(intA(0, 127, 127, 64, 96, 112, 56, 28, 14, 7, 3, 0), intA(0, 127, 127, 1, 3, 7, 14, 28, 56, 112, 96, 0)));
        this.put('L', bigFontArray(intA(0, 127, 127, 0, 0, 0, 0, 0, 0, 0, 0, 0), intA(0, 127, 127, 96, 96, 96, 96, 96, 96, 96, 96, 0)));
        this.put('M', bigFontArray(intA(0, 127, 127, 28, 56, 112, 112, 56, 28, 127, 127, 0), intA(0, 127, 127, 0, 0, 1, 1, 0, 0, 127, 127, 0)));
        this.put('N', bigFontArray(intA(0, 127, 127, 56, 112, 96, 64, 0, 0, 127, 127, 0), intA(0, 127, 127, 0, 0, 1, 3, 7, 14, 127, 127, 0)));
        this.put('O', bigFontArray(intA(0, 124, 126, 7, 3, 3, 3, 3, 7, 126, 124, 0), intA(0, 31, 63, 112, 96, 96, 96, 96, 112, 63, 31, 0)));
        this.put('P', bigFontArray(intA(0, 127, 127, 67, 67, 67, 67, 67, 103, 126, 60, 0), intA(0, 127, 127, 1, 1, 1, 1, 1, 1, 0, 0, 0)));
        this.put('Q', bigFontArray(intA(0, 124, 126, 7, 3, 3, 3, 3, 7, 126, 124, 0), intA(0, 31, 63, 112, 96, 102, 110, 60, 56, 127, 111, 0)));
        this.put('R', bigFontArray(intA(0, 127, 127, 67, 67, 67, 67, 67, 103, 126, 60, 0), intA(0, 127, 127, 1, 3, 7, 15, 29, 57, 112, 96, 0)));
        this.put('S', bigFontArray(intA(0, 60, 126, 103, 67, 67, 67, 67, 71, 14, 12, 0), intA(0, 24, 56, 113, 97, 97, 97, 97, 115, 63, 30, 0)));
        this.put('T', bigFontArray(intA(0, 3, 3, 3, 3, 127, 127, 3, 3, 3, 3, 0), intA(0, 0, 0, 0, 0, 127, 127, 0, 0, 0, 0, 0)));
        this.put('U', bigFontArray(intA(0, 127, 127, 0, 0, 0, 0, 0, 0, 127, 127, 0), intA(0, 31, 63, 112, 96, 96, 96, 96, 112, 63, 31, 0)));
        this.put('V', bigFontArray(intA(0, 127, 127, 0, 0, 0, 0, 0, 0, 127, 127, 0), intA(0, 7, 15, 28, 56, 112, 112, 56, 28, 15, 7, 0)));
        this.put('W', bigFontArray(intA(0, 127, 127, 0, 0, 64, 64, 0, 0, 127, 127, 0), intA(0, 31, 63, 112, 112, 63, 63, 112, 112, 63, 31, 0)));
        this.put('X', bigFontArray(intA(0, 15, 31, 56, 112, 96, 96, 112, 56, 31, 15, 0), intA(0, 120, 124, 14, 7, 3, 3, 7, 14, 124, 120, 0)));
        this.put('Y', bigFontArray(intA(0, 63, 127, 96, 64, 0, 0, 64, 96, 127, 63, 0), intA(0, 0, 0, 1, 3, 127, 127, 3, 1, 0, 0, 0)));
        this.put('Z', bigFontArray(intA(0, 3, 3, 3, 3, 67, 99, 115, 59, 31, 15, 0), intA(0, 120, 124, 110, 103, 99, 97, 96, 96, 96, 96, 0)));
    }


    private FlipdotBigByte[] bigFontArray(int[] upper, int[] lower) {
        if (upper.length != lower.length) {
            throw new IllegalStateException("Length of upper and lower must be the same! Upper:" + upper + ", lower:" + lower);
        }

        FlipdotBigByte[] result = new FlipdotBigByte[upper.length];
        for (int i = 0; i < upper.length; i++) {
            result[i] = new FlipdotBigByte(upper[i], lower[i]);
        }

        return result;
    }

    private int[] intA(int... values) {
        return values;
    }


}
