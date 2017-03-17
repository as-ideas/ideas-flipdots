package de.axelspringer.ideas.flipdots.magic;

import de.axelspringer.ideas.flipdots.magic.font.FlipdotsBigFont;
import de.axelspringer.ideas.flipdots.magic.font.FlipdotsSmallFont;
import de.axelspringer.ideas.flipdots.magic.font.TextMode;
import de.axelspringer.ideas.flipdots.magic.frames.FlipdotBigByte;
import de.axelspringer.ideas.flipdots.magic.frames.FlipdotFull2CFrame;
import de.axelspringer.ideas.flipdots.magic.frames.FlipdotSingleFrame;
import de.axelspringer.ideas.flipdots.magic.frames.FramePosition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;


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

        FlipdotSingleFrame flipdotFrame = new FlipdotSingleFrame();
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
        writeText(params, FramePosition.ALL, TextMode.FLOATING_RIGHT_TO_LEFT);
    }

    public void writeText(String text, FramePosition framePosition) {
        writeText(text, framePosition, TextMode.FLOATING_RIGHT_TO_LEFT);
    }

    public void writeText(String text, FramePosition framePosition, TextMode textMode) {
        LOG.info("Flipdots.writeText:  " + text + " on " + framePosition);

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

        FlipdotSingleFrame flipdotFrame = new FlipdotSingleFrame(framePosition);
//        flipdotSerialPort.write(flipdotFrame);
//        FlipdotFrame flipdotFrame = FlipdotFrame.createFrameForPosition(framePosition);

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


    public void clear(FramePosition framePosition) {
        if (framePosition == FramePosition.ALL) {
            clearAll();
        }
        flipdotSerialPort.write(new FlipdotSingleFrame(framePosition));
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

    public void write(FlipdotFull2CFrame flipdotFull2CFrame) {
        flipdotSerialPort.write(flipdotFull2CFrame);
    }
}
