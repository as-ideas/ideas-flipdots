package de.axelspringer.ideas.flipdots.magic;


import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;
import org.junit.Test;

public class FlipdotsManualStuff {


    @Test
    public void listSerialPorts() throws Exception {
        String[] portNames = SerialPortList.getPortNames();
        for (int i = 0; i < portNames.length; i++) {
            System.out.println(portNames[i]);
        }

    }

    @Test
    public void test_bin_parsing() throws Exception {
        String bin = "0_0_62_0_8_8_8_8_8_8_20_34_65_0_0_0_0_0_0_0_0_0_0_0_0_0_0_0";

        Integer[] result = new Flipdots().parseBinaryPatternString(bin);


    }

    @Test
    public void name_write_stuff() throws Exception {
        SerialPort serialPort = new SerialPort("/dev/tty.usbserial-AI03650Y");
        try {
            serialPort.openPort();//Open serial port
            serialPort.setParams(SerialPort.BAUDRATE_9600,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);//Set params. Also you can set params by this string: serialPort.setParams(9600, 8, 1, 0);

            Thread.sleep(1000);

            FlipdotFrame flipdotFrame = new FlipdotFrame();
            flipdotFrame.appendOnLastColumn(66);

            serialPort.writeIntArray(flipdotFrame.getFrame());//Write data to port

            Thread.sleep(1000);

            serialPort.closePort();//Close serial port
        } catch (SerialPortException ex) {
            System.out.println(ex);
        }
    }


    @Test
    public void name_write_stuff_2() throws Exception {
        Flipdots flipdots = new Flipdots();

        for (int i = 0; i < 200; i++) {
            flipdots.writeText("Hallo bei AS ideas engineering der besten firma der welt und viel spass heute                     ");
        }
    }

//    @Test
//    public void name_writeImage() throws Exception {
//        Flipdots flipdots = new Flipdots();
//        Thread.sleep(3000);
//
//        // FIXME Remove System.out
//        System.out.println("FlipdotsManualStuff.name_writeImage");
//        flipdots.writeFullFrame(ImageReader.readImage("example.png"));
//        Thread.sleep(3000);
//
//    }


}