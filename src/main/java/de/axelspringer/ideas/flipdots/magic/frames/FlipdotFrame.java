package de.axelspringer.ideas.flipdots.magic.frames;

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
public class FlipdotFrame {

    public static FlipdotFrame createFrameForPosition(FramePosition framePosition) {
        switch (framePosition) {

            case ALL:
                return new FlipdotBigFrame();
            case UPPER:
            case LOWER:
                return new FlipdotOneLineFrame(framePosition);
            case UPPER_LEFT:
            case LOWER_LEFT:
            case UPPER_RIGHT:
            case LOWER_RIGHT:
                return new FlipdotSingleFrame(framePosition);
            default:
                throw new IllegalStateException();
        }
    }

//    public void setAddress(int a) {
//        frame[2] = a;
//    }
//
//
//    public int[] getFrame() {
//        return frame;
//    }
//
//    public void shiftLeft() {
//        for (int i = 3; i <= 29; i++) {
//            frame[i] = frame[i + 1];
//        }
//        frame[30] = 0;
//    }
//
//    public void shiftRight() {
//        System.arraycopy(frame, 3, frame, 4, 27);
//        frame[3] = 0;
//    }
//
//    public void appendOnLastColumn(Integer oneCol) {
//        frame[30] = oneCol;
//    }
//
//
//    public void appendOnFirstColumn(Integer oneCol) {
//        frame[3] = oneCol;
//    }
//
//    public void appendSimple(Integer[] values) {
//        for (int i = 0; i <= 27; i++) {
//            frame[3 + i] = values[i];
//        }
//    }

//    public void append(List<Integer> data);

    protected int[] initWithAddress(int a) {
        int[] result = new int[32];
        result[0] = 0x80; // Beginning
        result[1] = 0x83; // 2C with Refresh
        result[2] = a; // Address
        // 28 Bytes of Data
        result[31] = 0x8F; // END
        return result;
    }
}
