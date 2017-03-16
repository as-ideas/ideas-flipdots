package de.axelspringer.ideas.flipdots.magic;

public class FlipdotBigFrame {

    public FlipdotFrame upperFrame = new FlipdotFrame();
    public FlipdotFrame lowerFrame = new FlipdotFrame();

    public FlipdotBigFrame() {
        upperFrame.setAddress(0x01);
        lowerFrame.setAddress(0x02);
    }


    public void shiftLeft() {
        upperFrame.shiftLeft();
        lowerFrame.shiftLeft();
    }

    public void appendOnLastColumn(FlipdotBigByte oneCol) {
        upperFrame.appendOnLastColumn(oneCol.upper);
        lowerFrame.appendOnLastColumn(oneCol.lower);
    }
//
//    public void appendSimple(Integer[] values) {
//        for (int i = 0; i <= 28; i++) {
//            frame[2 + i] = values[0];
//        }
//    }
}
