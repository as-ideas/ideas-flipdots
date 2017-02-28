package de.axelspringer.ideas.flipdots.magic;


import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;
import org.junit.Test;

public class FlipdotsTest {

    @Test
    public void listSerialPorts() throws Exception {
        String[] portNames = SerialPortList.getPortNames();
        for (int i = 0; i < portNames.length; i++) {
            System.out.println(portNames[i]);
        }

    }

    @Test
    public void name() throws Exception {
        SerialPort serialPort = new SerialPort("COM1");
        try {
            serialPort.openPort();//Open serial port
            serialPort.setParams(SerialPort.BAUDRATE_9600,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);//Set params. Also you can set params by this string: serialPort.setParams(9600, 8, 1, 0);
            serialPort.writeBytes("This is a test string".getBytes());//Write data to port
            serialPort.closePort();//Close serial port
        } catch (SerialPortException ex) {
            System.out.println(ex);
        }

    }
}