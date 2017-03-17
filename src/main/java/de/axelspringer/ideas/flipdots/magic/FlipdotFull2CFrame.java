package de.axelspringer.ideas.flipdots.magic;

public class FlipdotFull2CFrame {

    public int[] upperLeftFrame = initWithAddress(0x01);
    public int[] lowerLeftFrame = initWithAddress(0x02);
    public int[] upperRightFrame = initWithAddress(0x03);
    public int[] lowerRightFrame = initWithAddress(0x04);

    public FlipdotFull2CFrame() {
//        lowerRightFrame[3] = 64;
//        lowerRightFrame[4] = 64;
    }

    private int[] initWithAddress(int a) {
        int[] result = new int[32];
        result[0] = 0x80; // Beginning
        result[1] = 0x83; // 2C with Refresh
        result[2] = a; // Address
        // 28 Bytes of Data
        result[31] = 0x8F; // END
        return result;
    }

    public void shiftLeft() {
        upperLeftFrame[30] = upperRightFrame[3];
        lowerLeftFrame[30] = lowerRightFrame[3];
        for (int i = 3; i <= 29; i++) {
            upperLeftFrame[i] = upperLeftFrame[i + 1];
        }
        for (int i = 3; i <= 29; i++) {
            lowerLeftFrame[i] = lowerLeftFrame[i + 1];
        }
        for (int i = 3; i <= 29; i++) {
            upperRightFrame[i] = upperRightFrame[i + 1];
        }
        for (int i = 3; i <= 29; i++) {
            lowerRightFrame[i] = lowerRightFrame[i + 1];
        }

//        lowerRightFrame[3] ^= ~(1 << 7);
//        lowerRightFrame[4] ^= ~(1 << 7);

        lowerRightFrame[3] ^= (-(1) ^ lowerRightFrame[3]) & (1 << 7);
        lowerRightFrame[4] ^= (-(1) ^ lowerRightFrame[4]) & (1 << 7);
    }

    public void appendOnLastColumn(FlipdotBigByte oneCol) {
        upperRightFrame[30] = oneCol.upper;
        lowerRightFrame[30] = oneCol.lower;
    }

}
