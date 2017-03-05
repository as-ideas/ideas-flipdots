package de.axelspringer.ideas.flipdots.magic;

public class FlipdotFrame {

    private int[] frame = new int[32];

    public FlipdotFrame() {
        frame[0] = 0x80; // Beginning
        frame[1] = 0x83; // 2C with Refresh
        frame[2] = 0x01; // Address
        // 28 Bytes of Data
        frame[31] = 0x8F; // END
    }

    public int[] getFrame() {
        return frame;
    }

    public void shiftLeft() {
        frame[30] = 0;
        for (int i = 29; i > 2; i--) {
            frame[i] = frame[i + 1];
        }
    }

    public void appendOnLastColumn(Integer oneCol) {
        frame[30] = oneCol;
    }

    public void appendSimple(Integer[] values) {
        for (int i = 0; i <= 28; i++) {
            frame[2 + i] = values[0];
        }
    }
}
