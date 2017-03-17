package de.axelspringer.ideas.flipdots.magic.frames;

public class FlipdotOneLineFrame extends FlipdotFrame {

    public final int[] leftFrame = initWithAddress(0x01);
    public final int[] rightFrame = initWithAddress(0x03);

    public FlipdotOneLineFrame(FramePosition framePosition) {
//        public final int[] leftFrame = initWithAddress(0x01);
//        public final int[] rightFrame = initWithAddress(0x03);
    }

    public void shiftLeft() {
        leftFrame[30] = rightFrame[3];
        for (int i = 3; i <= 29; i++) {
            leftFrame[i] = leftFrame[i + 1];
        }
        for (int i = 3; i <= 29; i++) {
            rightFrame[i] = rightFrame[i + 1];
        }
    }

    public void appendOnLastColumn(FlipdotBigByte oneCol) {
        rightFrame[30] = oneCol.upper;
    }

}
