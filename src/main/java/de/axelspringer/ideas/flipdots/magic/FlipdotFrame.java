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

    public static FlipdotFrame forAddress(int a) {
        FlipdotFrame flipdotFrame = new FlipdotFrame();
        flipdotFrame.setAddress(a);
        return flipdotFrame;
    }

    public void setAddress(int a) {
        frame[2] = a;
    }


    public int[] getFrame() {
        return frame;
    }

    public void shiftLeft() {
        for (int i = 3; i <= 29; i++) {
            frame[i] = frame[i + 1];
        }
//        frame[30] = 0;
    }

    public void appendOnLastColumn(Integer oneCol) {
        frame[30] = oneCol;
    }

    public void appendSimple(Integer[] values) {
        for (int i = 0; i <= 27; i++) {
            frame[3 + i] = values[i];
        }
    }
}
