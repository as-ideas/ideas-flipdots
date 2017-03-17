package de.axelspringer.ideas.flipdots.magic.font;

import java.util.HashMap;

public class FlipdotsSmallFont extends HashMap<Character, Integer[]> {

    public FlipdotsSmallFont() {
        this.put(' ', new Integer[]{0});
        this.put('+', new Integer[]{24, 126, 126, 24, 0});
        this.put('-', new Integer[]{24, 24, 24, 24, 0});
        this.put('0', new Integer[]{62, 34, 62});//62, 65, 65, 62, 0});
        this.put('1', new Integer[]{62});//0, 66, 127, 64, 0});
        this.put('2', new Integer[]{58, 42, 46});//98, 81, 73, 70, 0});
        this.put('3', new Integer[]{34, 42, 62});//34, 65, 73, 54, 0});
        this.put('4', new Integer[]{14, 8, 62});//56, 36, 34, 127, 32});
        this.put('5', new Integer[]{46, 42, 58});//79, 73, 73, 49, 0});
        this.put('6', new Integer[]{62, 42, 58});//62, 73, 73, 50, 0});
        this.put('7', new Integer[]{ 2,  2, 62}); //3, 1, 1, 127, 0});
        this.put('8', new Integer[]{62, 42, 62});//54, 73, 73, 54, 0});
        this.put('9', new Integer[]{46, 42, 62});//38, 73, 73, 62, 0});
        this.put('A', new Integer[]{0x3C, 0x0A, 0x0A, 0x3C});
        this.put('B', new Integer[]{0x3E, 0x2A, 0x2A, 0x14});
        this.put('C', new Integer[]{0x1C, 0x22, 0x22, 0x14});
        this.put('D', new Integer[]{0x3E, 0x22, 0x22, 0x1C});
        this.put('E', new Integer[]{0x3E, 0x2A, 0x2A});
        this.put('F', new Integer[]{0x3E, 0x0A, 0x0A});
        this.put('G', new Integer[]{0x1C, 0x22, 0x2A, 0x3A});
        this.put('H', new Integer[]{0x3E, 0x08, 0x08, 0x3E});
        this.put('I', new Integer[]{0x3E});
        this.put('J', new Integer[]{0x10, 0x20, 0x20, 0x1E});
        this.put('K', new Integer[]{0x3E, 0x08, 0x14, 0x22});
        this.put('L', new Integer[]{0x3E, 0x20, 0x20});
        this.put('M', new Integer[]{0x3E, 0x04, 0x08, 0x04, 0x3E});
        this.put('N', new Integer[]{0x3E, 0x04, 0x08, 0x3E});
        this.put('O', new Integer[]{0x1C, 0x22, 0x22, 0x1C});
        this.put('P', new Integer[]{0x3E, 0x0A, 0x0A, 0x04});
        this.put('Q', new Integer[]{0x1C, 0x22, 0x12, 0x2C});
        this.put('R', new Integer[]{0x3E, 0x0A, 0x1A, 0x24});
        this.put('S', new Integer[]{0x24, 0x2A, 0x2A, 0x12});
        this.put('T', new Integer[]{0x02, 0x02, 0x3E, 0x02, 0x02});
        this.put('U', new Integer[]{0x1E, 0x20, 0x20, 0x1E});
        this.put('V', new Integer[]{0x06, 0x18, 0x20, 0x18, 0x6});
        this.put('W', new Integer[]{0x1E, 0x20, 0x1E, 0x20, 0x1E});
        this.put('X', new Integer[]{0x36, 0x08, 0x08, 0x36});
        this.put('Y', new Integer[]{0x2E, 0x28, 0x28, 0x1E});
        this.put('Z', new Integer[]{0x32, 0x2A, 0x2A, 0x26});
    }

}
