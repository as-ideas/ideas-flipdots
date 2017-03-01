package de.axelspringer.ideas.flipdots.magic;

import java.util.HashMap;
import java.util.Map;

public class Flipdots {

    Map<Character, byte[]> font = new HashMap<>();

    public Flipdots() {
        font.put(' ', new byte[]{0});
        font.put('+', new byte[]{24, 126, 126, 24, 0});
        font.put('-', new byte[]{24, 24, 24, 24, 0});
        font.put('0', new byte[]{62, 65, 65, 62, 0});
        font.put('1', new byte[]{0, 66, 127, 64, 0});
        font.put('2', new byte[]{98, 81, 73, 70, 0});
        font.put('3', new byte[]{34, 65, 73, 54, 0});
        font.put('4', new byte[]{56, 36, 34, 127, 32});
        font.put('5', new byte[]{79, 73, 73, 49, 0});
        font.put('6', new byte[]{62, 73, 73, 50, 0});
        font.put('7', new byte[]{3, 1, 1, 127, 0});
        font.put('8', new byte[]{54, 73, 73, 54, 0});
        font.put('9', new byte[]{38, 73, 73, 62, 0});
        font.put('A', new byte[]{0x3C, 0x0A, 0x0A, 0x3C});
        font.put('B', new byte[]{0x3E, 0x2A, 0x2A, 0x14});
        font.put('C', new byte[]{0x1C, 0x22, 0x22, 0x14});
        font.put('D', new byte[]{0x3E, 0x22, 0x22, 0x1C});
        font.put('E', new byte[]{0x3E, 0x2A, 0x2A});
        font.put('F', new byte[]{0x3E, 0x0A, 0x0A});
        font.put('G', new byte[]{0x1C, 0x22, 0x2A, 0x2A});
        font.put('H', new byte[]{0x3E, 0x08, 0x08, 0x3E});
        font.put('I', new byte[]{0x3E});
        font.put('J', new byte[]{0x10, 0x20, 0x20, 0x1E});
        font.put('K', new byte[]{0x3E, 0x08, 0x14, 0x22});
        font.put('L', new byte[]{0x3E, 0x20, 0x20});
        font.put('M', new byte[]{0x3E, 0x04, 0x08, 0x04});
        font.put('N', new byte[]{0x3E, 0x04, 0x08, 0x3E});
        font.put('O', new byte[]{0x1C, 0x22, 0x22, 0x1C});
        font.put('P', new byte[]{0x3E, 0x0A, 0x0A, 0x1C, 0x04});
        font.put('Q', new byte[]{0x1C, 0x22, 0x12, 0x2C});
        font.put('R', new byte[]{0x3E, 0x0A, 0x1A, 0x24});
        font.put('S', new byte[]{0x24, 0x2A, 0x2A, 0x12});
        font.put('T', new byte[]{0x02, 0x02, 0x3E, 0x02, 0x02});
        font.put('U', new byte[]{0x1E, 0x20, 0x20, 0x1E});
        font.put('V', new byte[]{0x06, 0x18, 0x20, 0x18, 0x6});
        font.put('W', new byte[]{0x1E, 0x20, 0x1E, 0x20, 0x1E});
        font.put('X', new byte[]{0x36, 0x08, 0x08, 0x36});
        font.put('Y', new byte[]{0x2E, 0x28, 0x28, 0x1E});
        font.put('Z', new byte[]{0x32, 0x2A, 0x2A, 0x26});
    }

}
