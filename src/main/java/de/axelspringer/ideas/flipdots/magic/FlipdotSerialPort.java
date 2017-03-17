package de.axelspringer.ideas.flipdots.magic;

import de.axelspringer.ideas.flipdots.magic.frames.FlipdotBigFrame;
import de.axelspringer.ideas.flipdots.magic.frames.FlipdotFrame;
import de.axelspringer.ideas.flipdots.magic.frames.FlipdotFull2CFrame;
import de.axelspringer.ideas.flipdots.magic.frames.FlipdotSingleFrame;
import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlipdotSerialPort {

    private static final Logger LOG = LoggerFactory.getLogger(FlipdotSerialPort.class);
    private SerialPort serialPort;

    public FlipdotSerialPort() {
        for (String portName : SerialPortList.getPortNames()) {
            LOG.info("Found serial port: " + portName);
        }

        new Thread(() -> {
            write(FlipdotSingleFrame.forAddress(0x01));
            write(FlipdotSingleFrame.forAddress(0x02));
            write(FlipdotSingleFrame.forAddress(0x03));
            write(FlipdotSingleFrame.forAddress(0x04));
        }).start();
    }

    private SerialPort getSerialPort() {
        if (serialPort == null || !serialPort.isOpened()) {
            serialPort = new SerialPort(portName());
            try {
                serialPort.openPort();//Open serial port
                //Set params. Also you can set params by this string: serialPort.setParams(9600, 8, 1, 0);
                serialPort.setParams(SerialPort.BAUDRATE_19200,
                        SerialPort.DATABITS_8,
                        SerialPort.STOPBITS_1,
                        SerialPort.PARITY_NONE);
                Thread.sleep(200);
            } catch (SerialPortException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        return serialPort;
    }

    public void write(FlipdotFull2CFrame flipdotFrame) {
        write(flipdotFrame.upperLeftFrame);
        write(flipdotFrame.lowerLeftFrame);
        write(flipdotFrame.upperRightFrame);
        write(flipdotFrame.lowerRightFrame);

    }

    public void write(FlipdotBigFrame flipdotFrame) {
        write(flipdotFrame.upperFrame);
        write(flipdotFrame.lowerFrame);
    }

    public void write(FlipdotSingleFrame flipdotFrame) {
        write(flipdotFrame.getFrame());
    }

    private void write(int[] flipdotFrame) {
        try {
            boolean result = getSerialPort().writeIntArray(flipdotFrame);
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

    private String portName() {
        String[] portNames = SerialPortList.getPortNames();
        if (portNames == null || portNames.length == 0) {
            throw new RuntimeException("No SERIAL PORT was found!");
        }
        if (portNames != null && portNames.length == 1) {
            return portNames[0];
        }


        String result = portNames[0];
        for (String portName : portNames) {
            if (portName.contains("usbserial")) {
                result = portName;
            }
        }
        return result;
    }

}
