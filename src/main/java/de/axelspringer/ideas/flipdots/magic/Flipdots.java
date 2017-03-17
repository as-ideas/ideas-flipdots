package de.axelspringer.ideas.flipdots.magic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    private Map<Character, Integer[]> font = new HashMap<>();
    private Map<Character, FlipdotBigByte[]> bigFont = new HashMap<>();
    private Long timePerFrame = DEFAULT_FRAME_DURATION_IN_MS;
    private FlipdotSerialPort flipdotSerialPort;

    public Flipdots() {
        flipdotSerialPort = new FlipdotSerialPort();

        font.put(' ', new Integer[]{0});
        font.put('+', new Integer[]{24, 126, 126, 24, 0});
        font.put('-', new Integer[]{24, 24, 24, 24, 0});
        font.put('0', new Integer[]{62, 34, 62});//62, 65, 65, 62, 0});
        font.put('1', new Integer[]{62});//0, 66, 127, 64, 0});
        font.put('2', new Integer[]{58, 42, 46});//98, 81, 73, 70, 0});
        font.put('3', new Integer[]{34, 42, 62});//34, 65, 73, 54, 0});
        font.put('4', new Integer[]{14, 8, 62});//56, 36, 34, 127, 32});
        font.put('5', new Integer[]{46, 42, 58});//79, 73, 73, 49, 0});
        font.put('6', new Integer[]{62, 42, 58});//62, 73, 73, 50, 0});
        font.put('7', new Integer[]{ 2,  2, 62}); //3, 1, 1, 127, 0});
        font.put('8', new Integer[]{62, 42, 62});//54, 73, 73, 54, 0});
        font.put('9', new Integer[]{46, 42, 62});//38, 73, 73, 62, 0});
        font.put('A', new Integer[]{0x3C, 0x0A, 0x0A, 0x3C});
        font.put('B', new Integer[]{0x3E, 0x2A, 0x2A, 0x14});
        font.put('C', new Integer[]{0x1C, 0x22, 0x22, 0x14});
        font.put('D', new Integer[]{0x3E, 0x22, 0x22, 0x1C});
        font.put('E', new Integer[]{0x3E, 0x2A, 0x2A});
        font.put('F', new Integer[]{0x3E, 0x0A, 0x0A});
        font.put('G', new Integer[]{0x1C, 0x22, 0x2A, 0x3A});
        font.put('H', new Integer[]{0x3E, 0x08, 0x08, 0x3E});
        font.put('I', new Integer[]{0x3E});
        font.put('J', new Integer[]{0x10, 0x20, 0x20, 0x1E});
        font.put('K', new Integer[]{0x3E, 0x08, 0x14, 0x22});
        font.put('L', new Integer[]{0x3E, 0x20, 0x20});
        font.put('M', new Integer[]{0x3E, 0x04, 0x08, 0x04, 0x3E});
        font.put('N', new Integer[]{0x3E, 0x04, 0x08, 0x3E});
        font.put('O', new Integer[]{0x1C, 0x22, 0x22, 0x1C});
        font.put('P', new Integer[]{0x3E, 0x0A, 0x0A, 0x04});
        font.put('Q', new Integer[]{0x1C, 0x22, 0x12, 0x2C});
        font.put('R', new Integer[]{0x3E, 0x0A, 0x1A, 0x24});
        font.put('S', new Integer[]{0x24, 0x2A, 0x2A, 0x12});
        font.put('T', new Integer[]{0x02, 0x02, 0x3E, 0x02, 0x02});
        font.put('U', new Integer[]{0x1E, 0x20, 0x20, 0x1E});
        font.put('V', new Integer[]{0x06, 0x18, 0x20, 0x18, 0x6});
        font.put('W', new Integer[]{0x1E, 0x20, 0x1E, 0x20, 0x1E});
        font.put('X', new Integer[]{0x36, 0x08, 0x08, 0x36});
        font.put('Y', new Integer[]{0x2E, 0x28, 0x28, 0x1E});
        font.put('Z', new Integer[]{0x32, 0x2A, 0x2A, 0x26});

        bigFont.put('A', bigFontArray(intA(0, 112, 120, 28, 14, 7, 7, 14, 28, 120, 112, 0), intA(0, 127, 127, 6, 6, 6, 6, 6, 6, 127, 127, 0)));
        bigFont.put('B', bigFontArray(intA(0, 127, 127, 67, 67, 67, 67, 67, 103, 126, 60, 0), intA(0, 127, 127, 97, 97, 97, 97, 97, 115, 63, 30, 0)));
        bigFont.put('C', bigFontArray(intA(0, 124, 126, 7, 3, 3, 3, 3, 7, 14, 12, 0), intA(0, 31, 63, 112, 96, 96, 96, 96, 112, 56, 24, 0)));
        bigFont.put('D', bigFontArray(intA(0, 127, 127, 3, 3, 3, 3, 3, 7, 126, 124, 0), intA(0, 127, 127, 96, 96, 96, 96, 96, 112, 63, 31, 0)));
        bigFont.put('E', bigFontArray(intA(0, 127, 127, 67, 67, 67, 67, 67, 67, 3, 3, 0), intA(0, 127, 127, 97, 97, 97, 97, 97, 97, 96, 96, 0)));
        bigFont.put('F', bigFontArray(intA(0, 127, 127, 67, 67, 67, 67, 67, 67, 3, 3, 0), intA(0, 127, 127, 1, 1, 1, 1, 1, 1, 0, 0, 0)));
        bigFont.put('G', bigFontArray(intA(0, 124, 126, 7, 3, 3, 67, 67, 71, 78, 76, 0), intA(0, 31, 63, 112, 96, 96, 97, 97, 113, 63, 31, 0)));
        bigFont.put('H', bigFontArray(intA(0, 127, 127, 64, 64, 64, 64, 64, 64, 127, 127, 0), intA(0, 127, 127, 1, 1, 1, 1, 1, 1, 127, 127, 0)));
        bigFont.put('I', bigFontArray(intA(0, 0, 0, 3, 3, 127, 127, 3, 3, 0, 0, 0), intA(0, 0, 0, 96, 96, 127, 127, 96, 96, 0, 0, 0)));
        bigFont.put('J', bigFontArray(intA(0, 0, 0, 0, 0, 3, 3, 127, 127, 3, 3, 0), intA(0, 24, 56, 112, 96, 96, 112, 63, 31, 0, 0, 0)));
        bigFont.put('K', bigFontArray(intA(0, 127, 127, 64, 96, 112, 56, 28, 14, 7, 3, 0), intA(0, 127, 127, 1, 3, 7, 14, 28, 56, 112, 96, 0)));
        bigFont.put('L', bigFontArray(intA(0, 127, 127, 0, 0, 0, 0, 0, 0, 0, 0, 0), intA(0, 127, 127, 96, 96, 96, 96, 96, 96, 96, 96, 0)));
        bigFont.put('M', bigFontArray(intA(0, 127, 127, 28, 56, 112, 112, 56, 28, 127, 127, 0), intA(0, 127, 127, 0, 0, 1, 1, 0, 0, 127, 127, 0)));
        bigFont.put('N', bigFontArray(intA(0, 127, 127, 56, 112, 96, 64, 0, 0, 127, 127, 0), intA(0, 127, 127, 0, 0, 1, 3, 7, 14, 127, 127, 0)));
        bigFont.put('O', bigFontArray(intA(0, 124, 126, 7, 3, 3, 3, 3, 7, 126, 124, 0), intA(0, 31, 63, 112, 96, 96, 96, 96, 112, 63, 31, 0)));
        bigFont.put('P', bigFontArray(intA(0, 127, 127, 67, 67, 67, 67, 67, 103, 126, 60, 0), intA(0, 127, 127, 1, 1, 1, 1, 1, 1, 0, 0, 0)));
        bigFont.put('Q', bigFontArray(intA(0, 124, 126, 7, 3, 3, 3, 3, 7, 126, 124, 0), intA(0, 31, 63, 112, 96, 102, 110, 60, 56, 127, 111, 0)));
        bigFont.put('R', bigFontArray(intA(0, 127, 127, 67, 67, 67, 67, 67, 103, 126, 60, 0), intA(0, 127, 127, 1, 3, 7, 15, 29, 57, 112, 96, 0)));
        bigFont.put('S', bigFontArray(intA(0, 60, 126, 103, 67, 67, 67, 67, 71, 14, 12, 0), intA(0, 24, 56, 113, 97, 97, 97, 97, 115, 63, 30, 0)));
        bigFont.put('T', bigFontArray(intA(0, 3, 3, 3, 3, 127, 127, 3, 3, 3, 3, 0), intA(0, 0, 0, 0, 0, 127, 127, 0, 0, 0, 0, 0)));
        bigFont.put('U', bigFontArray(intA(0, 127, 127, 0, 0, 0, 0, 0, 0, 127, 127, 0), intA(0, 31, 63, 112, 96, 96, 96, 96, 112, 63, 31, 0)));
        bigFont.put('V', bigFontArray(intA(0, 127, 127, 0, 0, 0, 0, 0, 0, 127, 127, 0), intA(0, 7, 15, 28, 56, 112, 112, 56, 28, 15, 7, 0)));
        bigFont.put('W', bigFontArray(intA(0, 127, 127, 0, 0, 64, 64, 0, 0, 127, 127, 0), intA(0, 31, 63, 112, 112, 63, 63, 112, 112, 63, 31, 0)));
        bigFont.put('X', bigFontArray(intA(0, 15, 31, 56, 112, 96, 96, 112, 56, 31, 15, 0), intA(0, 120, 124, 14, 7, 3, 3, 7, 14, 124, 120, 0)));
        bigFont.put('Y', bigFontArray(intA(0, 63, 127, 96, 64, 0, 0, 64, 96, 127, 63, 0), intA(0, 0, 0, 1, 3, 127, 127, 3, 1, 0, 0, 0)));
        bigFont.put('Z', bigFontArray(intA(0, 3, 3, 3, 3, 67, 99, 115, 59, 31, 15, 0), intA(0, 120, 124, 110, 103, 99, 97, 96, 96, 96, 96, 0)));
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

    public synchronized void writeText(String params) {
        try {
            params = URLDecoder.decode(params, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
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
        try {
            params = URLDecoder.decode(params, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
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


    public void writeBigTextOnlyLeft(String params) {
        try {
            params = URLDecoder.decode(params, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
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

        FlipdotBigFrame flipdotFrame = new FlipdotBigFrame();
        flipdotSerialPort.write(flipdotFrame);

        for (FlipdotBigByte oneCol : data) {
            flipdotFrame.shiftLeft();
            flipdotFrame.appendOnLastColumn(oneCol);
            flipdotSerialPort.write(flipdotFrame);
            sleep();
        }

    }


    private void sleep() {
        try {
            Thread.sleep(timePerFrame);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void setTimePerFrame(long i) {
        LOG.info("Setting time to ms: " + i);
        this.timePerFrame = i;
    }

    private FlipdotBigByte[] bigFontArray(int[] upper, int[] lower) {
        if (upper.length != lower.length) {
            throw new IllegalStateException("Length of upper and lower must be the same! Upper:" + upper + ", lower:" + lower);
        }

        FlipdotBigByte[] result = new FlipdotBigByte[upper.length];
        for (int i = 0; i < upper.length; i++) {
            result[i] = new FlipdotBigByte(upper[i], lower[i]);
        }

        return result;
    }

    private int[] intA(int... values) {
        return values;
    }


    public void close() {
        flipdotSerialPort.close();
    }

    public void clearAll() {
        flipdotSerialPort.write(new FlipdotFull2CFrame());
    }
}
