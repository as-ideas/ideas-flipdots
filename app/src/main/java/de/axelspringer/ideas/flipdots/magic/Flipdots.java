package de.axelspringer.ideas.flipdots.magic;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private static final long DEFAULT_FRAME_DURATION_IN_MS = TimeUnit.MILLISECONDS.toMillis(750);

    private Map<Character, Integer[]> font = new HashMap<>();
    private SerialPort serialPort;

    public Flipdots() {
        font.put(' ', new Integer[]{0});
        font.put('+', new Integer[]{24, 126, 126, 24, 0});
        font.put('-', new Integer[]{24, 24, 24, 24, 0});
        font.put('0', new Integer[]{62, 65, 65, 62, 0});
        font.put('1', new Integer[]{0, 66, 127, 64, 0});
        font.put('2', new Integer[]{98, 81, 73, 70, 0});
        font.put('3', new Integer[]{34, 65, 73, 54, 0});
        font.put('4', new Integer[]{56, 36, 34, 127, 32});
        font.put('5', new Integer[]{79, 73, 73, 49, 0});
        font.put('6', new Integer[]{62, 73, 73, 50, 0});
        font.put('7', new Integer[]{3, 1, 1, 127, 0});
        font.put('8', new Integer[]{54, 73, 73, 54, 0});
        font.put('9', new Integer[]{38, 73, 73, 62, 0});
        font.put('A', new Integer[]{0x3C, 0x0A, 0x0A, 0x3C});
        font.put('B', new Integer[]{0x3E, 0x2A, 0x2A, 0x14});
        font.put('C', new Integer[]{0x1C, 0x22, 0x22, 0x14});
        font.put('D', new Integer[]{0x3E, 0x22, 0x22, 0x1C});
        font.put('E', new Integer[]{0x3E, 0x2A, 0x2A});
        font.put('F', new Integer[]{0x3E, 0x0A, 0x0A});
        font.put('G', new Integer[]{0x1C, 0x22, 0x2A, 0x2A});
        font.put('H', new Integer[]{0x3E, 0x08, 0x08, 0x3E});
        font.put('I', new Integer[]{0x3E});
        font.put('J', new Integer[]{0x10, 0x20, 0x20, 0x1E});
        font.put('K', new Integer[]{0x3E, 0x08, 0x14, 0x22});
        font.put('L', new Integer[]{0x3E, 0x20, 0x20});
        font.put('M', new Integer[]{0x3E, 0x04, 0x08, 0x04});
        font.put('N', new Integer[]{0x3E, 0x04, 0x08, 0x3E});
        font.put('O', new Integer[]{0x1C, 0x22, 0x22, 0x1C});
        font.put('P', new Integer[]{0x3E, 0x0A, 0x0A, 0x1C, 0x04});
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
    }


    public void writeBin(String params) {
        LOG.info("Flipdots.writeBin " + params);
    }

    public void writeText(String params) {
        LOG.info("Flipdots.writeText " + params);

        List<Integer> data = new ArrayList<>();
        for (char c : params.toCharArray()) {
            data.addAll(Arrays.asList(font.get(c)));
        }

        FlipdotFrame flipdotFrame = new FlipdotFrame();

        for (Integer oneCol : data) {
            flipdotFrame.shiftLeft();
            flipdotFrame.appendOnLastColumn(oneCol);
            write(flipdotFrame);
            sleep();
        }

    }

    private void write(FlipdotFrame flipdotFrame) {
        try {
            boolean result = getSerialPort().writeIntArray(flipdotFrame.getFrame());
            if (!result) {
                LOG.warn("Error on writing frame!");
            }
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }


    public void close() {
        if (serialPort != null) {
            try {
                serialPort.closePort();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private SerialPort getSerialPort() {
        if (serialPort == null) {
            serialPort = new SerialPort(portName());
            try {
                serialPort.openPort();//Open serial port
                //Set params. Also you can set params by this string: serialPort.setParams(9600, 8, 1, 0);
                serialPort.setParams(SerialPort.BAUDRATE_9600,
                        SerialPort.DATABITS_8,
                        SerialPort.STOPBITS_1,
                        SerialPort.PARITY_NONE);
                sleep();
            } catch (SerialPortException e) {
                e.printStackTrace();
            }
        }
        return serialPort;
    }

    private void sleep() {
        try {
            Thread.sleep(DEFAULT_FRAME_DURATION_IN_MS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String portName() {
        String[] portNames = SerialPortList.getPortNames();
        for (String portName : portNames) {
            return portName;
        }
        return null;
    }


}
