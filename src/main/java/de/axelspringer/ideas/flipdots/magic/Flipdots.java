package de.axelspringer.ideas.flipdots.magic;

import de.axelspringer.ideas.flipdots.magic.font.FlipdotsBigFont;
import de.axelspringer.ideas.flipdots.magic.font.FlipdotsSmallFont;
import de.axelspringer.ideas.flipdots.magic.font.TextMode;
import de.axelspringer.ideas.flipdots.magic.frames.FlipdotBigByte;
import de.axelspringer.ideas.flipdots.magic.frames.FlipdotFrame;
import de.axelspringer.ideas.flipdots.magic.frames.FlipdotFull2CFrame;
import de.axelspringer.ideas.flipdots.magic.frames.FramePosition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
public class Flipdots {

    private static final Logger LOG = LoggerFactory.getLogger(Flipdots.class);
    private static final long DEFAULT_FRAME_DURATION_IN_MS = TimeUnit.MILLISECONDS.toMillis(280);

    private FlipdotsSmallFont font = new FlipdotsSmallFont();
    private FlipdotsBigFont bigFont = new FlipdotsBigFont();
    private Long timePerFrame = DEFAULT_FRAME_DURATION_IN_MS;
    private FlipdotSerialPort flipdotSerialPort;

    private boolean isRunning = false;

    public Flipdots() {
        flipdotSerialPort = new FlipdotSerialPort();
    }


    public void writeBin(String params) {
        LOG.info("Flipdots.writeBin " + params);
        Integer[] values = parseBinaryPatternString(params);

        FlipdotFrame flipdotFrame = new FlipdotFrame();
        for (int i = 0; i < values.length; i++) {
            flipdotFrame.appendSimple(values);
        }
        flipdotSerialPort.write(flipdotFrame);
    }

    Integer[] parseBinaryPatternString(String params) {
        String[] split = params.split("_");
        Integer[] values = new Integer[split.length];
        for (int i = 0; i < values.length; i++) {
            values[i] = Integer.valueOf(split[i]);
        }
        return values;
    }

    public void writeText(String params) {
        LOG.info("Flipdots.writeText:  " + params);

        List<Integer> data = new ArrayList<>();
        for (char c : params.toUpperCase().toCharArray()) {
            Integer[] a = font.get(c);
            if (a != null) {
                data.addAll(Arrays.asList(a));
            } else {
                LOG.warn("Char not found: " + c);
            }
            data.add(0);
        }

        FlipdotFrame flipdotFrame = new FlipdotFrame();
        flipdotSerialPort.write(flipdotFrame);

        for (Integer oneCol : data) {
            flipdotFrame.shiftLeft();
            flipdotFrame.appendOnLastColumn(oneCol);
            flipdotSerialPort.write(flipdotFrame);
            sleep();
        }

    }

    public void writeBigText(String params) {
        LOG.info("Flipdots.writeBigText " + params);

        List<FlipdotBigByte> data = new ArrayList<>();
        for (char c : params.toUpperCase().toCharArray()) {
            FlipdotBigByte[] a = bigFont.get(c);
            if (a != null) {
                data.addAll(Arrays.asList(a));
            } else {
                LOG.warn("Char not found: " + c);
            }

            data.add(FlipdotBigByte.SPACE);
        }

        FlipdotFull2CFrame flipdotFrame = new FlipdotFull2CFrame();
        flipdotSerialPort.write(flipdotFrame);

        for (FlipdotBigByte oneCol : data) {
            flipdotFrame.shiftLeft();
            flipdotFrame.appendOnLastColumn(oneCol);
            flipdotSerialPort.write(flipdotFrame);
            sleep();
        }

    }

    public void setTimePerFrame(long i) {
        LOG.info("Setting time to ms: " + i);
        this.timePerFrame = i;
    }


    public void close() {
        flipdotSerialPort.close();
    }

    public void clearAll() {
        flipdotSerialPort.write(new FlipdotFull2CFrame());
    }

    public void demoStart() {
        FlipdotDemo flipdotDemo = new FlipdotDemo();
        while (isRunning) {


            sleepOneSecond();
        }
    }

    private void sleepOneSecond() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void writeTextToPosition(String text, FramePosition framePosition) {
        LOG.info("Flipdots.writeTextToPosition:  " + text + " on " + framePosition);

        List<Integer> data = new ArrayList<>();
        for (char c : text.toUpperCase().toCharArray()) {
            Integer[] a = font.get(c);
            if (a != null) {
                data.addAll(Arrays.asList(a));
            } else {
                LOG.warn("Char not found: " + c);
            }
            data.add(0);
        }

        FlipdotFrame flipdotFrame = new FlipdotFrame(framePosition);
        flipdotSerialPort.write(flipdotFrame);

        for (Integer oneCol : data) {
            flipdotFrame.shiftLeft();
            flipdotFrame.appendOnLastColumn(oneCol);
            flipdotSerialPort.write(flipdotFrame);
            sleep();
        }
    }


    public void writeTextToPosition(String text, FramePosition framePosition, TextMode textMode) {
        LOG.info("Flipdots.writeTextToPosition:  " + text + " on " + framePosition);

        List<Integer> data = new ArrayList<>();
        for (char c : text.toUpperCase().toCharArray()) {
            Integer[] a = font.get(c);
            if (a != null) {
                data.addAll(Arrays.asList(a));
            } else {
                LOG.warn("Char not found: " + c);
            }
            data.add(0);
        }

        FlipdotFrame flipdotFrame = new FlipdotFrame(framePosition);
        flipdotSerialPort.write(flipdotFrame);

        switch (textMode) {

            case FLOATING_RIGHT_TO_LEFT:
                for (Integer oneCol : data) {
                    flipdotFrame.shiftLeft();
                    flipdotFrame.appendOnLastColumn(oneCol);
                    flipdotSerialPort.write(flipdotFrame);
                    sleep();
                }
                break;
            case LEFT_ALIGN:
                flipdotFrame.append(data);
                flipdotSerialPort.write(flipdotFrame);
                sleep();
                flipdotSerialPort.write(flipdotFrame);
                break;
            case RIGHT_ALIGN:
                for (Integer oneCol : data) {
                    flipdotFrame.shiftLeft();
                    flipdotFrame.appendOnLastColumn(oneCol);
                }
                flipdotSerialPort.write(flipdotFrame);
                sleep();
                flipdotSerialPort.write(flipdotFrame);
                break;
            default:
                throw new IllegalStateException("Mode not supported: " + textMode);
        }


    }

    public void demoStop() {
        isRunning = false;
    }

    private void sleep() {
        try {
            Thread.sleep(timePerFrame);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
