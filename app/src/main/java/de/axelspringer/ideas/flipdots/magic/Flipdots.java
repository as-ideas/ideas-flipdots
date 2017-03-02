package de.axelspringer.ideas.flipdots.magic;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * 0x81 - 112bytes/no refresh/C+3E
 * 0x82 - refresh
 * 0x83 - 28bytes of data/refresh/2C
 * 0x84 - 28bytes of data/no refresh/2C
 * 0x85 - 56bytes of data/refresh/C+E
 * 0x86 - 56bytes of data/no refresh/C+E
 * ---------------------------------------
 * address or 0xFF for all
 * data...1to number of data buytes
 * 0x8Fend
 */
public class Flipdots {

    Map<Character, Byte[]> font = new HashMap<>();

    private SerialPort serialPort;

    public Flipdots() {
        font.put(' ', new Byte[]{0});
        font.put('+', new Byte[]{24, 126, 126, 24, 0});
        font.put('-', new Byte[]{24, 24, 24, 24, 0});
        font.put('0', new Byte[]{62, 65, 65, 62, 0});
        font.put('1', new Byte[]{0, 66, 127, 64, 0});
        font.put('2', new Byte[]{98, 81, 73, 70, 0});
        font.put('3', new Byte[]{34, 65, 73, 54, 0});
        font.put('4', new Byte[]{56, 36, 34, 127, 32});
        font.put('5', new Byte[]{79, 73, 73, 49, 0});
        font.put('6', new Byte[]{62, 73, 73, 50, 0});
        font.put('7', new Byte[]{3, 1, 1, 127, 0});
        font.put('8', new Byte[]{54, 73, 73, 54, 0});
        font.put('9', new Byte[]{38, 73, 73, 62, 0});
        font.put('A', new Byte[]{0x3C, 0x0A, 0x0A, 0x3C});
        font.put('B', new Byte[]{0x3E, 0x2A, 0x2A, 0x14});
        font.put('C', new Byte[]{0x1C, 0x22, 0x22, 0x14});
        font.put('D', new Byte[]{0x3E, 0x22, 0x22, 0x1C});
        font.put('E', new Byte[]{0x3E, 0x2A, 0x2A});
        font.put('F', new Byte[]{0x3E, 0x0A, 0x0A});
        font.put('G', new Byte[]{0x1C, 0x22, 0x2A, 0x2A});
        font.put('H', new Byte[]{0x3E, 0x08, 0x08, 0x3E});
        font.put('I', new Byte[]{0x3E});
        font.put('J', new Byte[]{0x10, 0x20, 0x20, 0x1E});
        font.put('K', new Byte[]{0x3E, 0x08, 0x14, 0x22});
        font.put('L', new Byte[]{0x3E, 0x20, 0x20});
        font.put('M', new Byte[]{0x3E, 0x04, 0x08, 0x04});
        font.put('N', new Byte[]{0x3E, 0x04, 0x08, 0x3E});
        font.put('O', new Byte[]{0x1C, 0x22, 0x22, 0x1C});
        font.put('P', new Byte[]{0x3E, 0x0A, 0x0A, 0x1C, 0x04});
        font.put('Q', new Byte[]{0x1C, 0x22, 0x12, 0x2C});
        font.put('R', new Byte[]{0x3E, 0x0A, 0x1A, 0x24});
        font.put('S', new Byte[]{0x24, 0x2A, 0x2A, 0x12});
        font.put('T', new Byte[]{0x02, 0x02, 0x3E, 0x02, 0x02});
        font.put('U', new Byte[]{0x1E, 0x20, 0x20, 0x1E});
        font.put('V', new Byte[]{0x06, 0x18, 0x20, 0x18, 0x6});
        font.put('W', new Byte[]{0x1E, 0x20, 0x1E, 0x20, 0x1E});
        font.put('X', new Byte[]{0x36, 0x08, 0x08, 0x36});
        font.put('Y', new Byte[]{0x2E, 0x28, 0x28, 0x1E});
        font.put('Z', new Byte[]{0x32, 0x2A, 0x2A, 0x26});
    }


    public void writeBin(String params) {
        // FIXME Remove System.out
        System.out.println("Flipdots.writeBin " + params);
    }

    public void writeText(String params) {
        // FIXME Remove System.out
        System.out.println("Flipdots.writeText " + params);
        List<Byte> bytes = new ArrayList<>();
        for (char c : params.toCharArray()) {
            bytes.addAll(Arrays.asList(font.get(c)));
        }
    }

    private void openConnection() {
        SerialPort serialPort = new SerialPort(portName());
        try {
            serialPort.openPort();//Open serial port
            serialPort.setParams(SerialPort.BAUDRATE_9600,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);//Set params. Also you can set params by this string: serialPort.setParams(9600, 8, 1, 0);
            serialPort.writeBytes("This is a test string".getBytes());
            serialPort.closePort();
        } catch (SerialPortException ex) {
            System.out.println(ex);
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
