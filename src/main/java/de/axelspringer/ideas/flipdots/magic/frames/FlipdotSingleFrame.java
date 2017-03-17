package de.axelspringer.ideas.flipdots.magic.frames;

import java.util.List;

/**
 * flipdot display
 * <p>
 * 0:1200
 * 1:2400
 * 2:4800
 * 3:9600<---this should be set,means 1-ON 2-ON 3-OFF
 * 4:19200
 * 5:38200<--do not use,most probably wrong speed programmed
 * 6:9600
 * 7:9600
 * 8:9600
 * <p>
 * 0x80beginning
 * ___________________
 * 0x81 - 112Integers/no refresh/C+3E
 * 0x82 - refresh
 * 0x83 - 28Integers of data/refresh/2C
 * 0x84 - 28Integers of data/no refresh/2C
 * 0x85 - 56Integers of data/refresh/C+E
 * 0x86 - 56Integers of data/no refresh/C+E
 * ---------------------------------------
 * address or 0xFF for all
 * data...1to number of data buytes
 * 0x8Fend
 */
public class FlipdotSingleFrame extends FlipdotFrame {

    private int[] frame = new int[32];

    public FlipdotSingleFrame(FramePosition framePosition) {
        frame[0] = 0x80; // Beginning
        frame[1] = 0x83; // 2C with Refresh
        frame[2] = framePosition.address; // Address
        // 28 Bytes of Data
        frame[31] = 0x8F; // END
    }

    public FlipdotSingleFrame() {
        this(FramePosition.ALL);
    }


    public static FlipdotSingleFrame forAddress(int a) {
        FlipdotSingleFrame flipdotFrame = new FlipdotSingleFrame();
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
        frame[30] = 0;
    }

    public void shiftRight() {
        System.arraycopy(frame, 3, frame, 4, 27);
        frame[3] = 0;
    }

    public void appendOnLastColumn(Integer oneCol) {
        frame[30] = oneCol;
    }


    public void appendOnFirstColumn(Integer oneCol) {
        frame[3] = oneCol;
    }

    public void appendSimple(Integer[] values) {
        for (int i = 0; i <= 27; i++) {
            frame[3 + i] = values[i];
        }
    }

    public void append(List<Integer> data) {
        for (int i = 0; i < Math.min(data.size(), 28); i++) {
            frame[3 + i] = data.get(i);
        }
    }
}
