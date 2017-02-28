#!/usr/bin/env python

#
# python sample for RS-485 for flipdots
# --> install pyserial before with 'pip install pyserial'

import serial
import time

# Parity
#
# serial.PARITY_NONE
# serial.PARITY_EVEN
# serial.PARITY_ODD
# serial.PARITY_MARK
# serial.PARITY_SPACE
# Stop bits
#
# serial.STOPBITS_ONE
# serial.STOPBITS_ONE_POINT_FIVE
# serial.STOPBITS_TWO
# Note that 1.5 stop bits are not supported on POSIX. It will fall back to 2 stop bits.
#
# Byte size
#
# serial.FIVEBITS
# serial.SIXBITS
# serial.SEVENBITS
# serial.EIGHTBITS
# Others
#
# Default control characters (instances of bytes for Python 3.0+) for software flow control:
#
# serial.XON
# serial.XOFF
# Module version:
#
# serial.VERSION
# A string indicating the pySerial version, such as 3.0.
#
# New in version 2.3.

print '#1 Opening serial'
ser = serial.Serial(
    port='/dev/cu.usbserial-AI03650Y',
    baudrate=9600,
    parity=serial.PARITY_NONE,
    stopbits=serial.STOPBITS_ONE,
    bytesize=serial.EIGHTBITS
)
time.sleep(2)

print '#2 Write all BRIGHT'
# 0x80 Beginnging (128)
#   0x83 Mode (2C with refresh) (131)
#   0x00 adress
#   N Data Bytes (2C = 28 Bytes)
# 0x8F Ending (143)
valuesAllBright = bytearray(
    [128, 131, 1, 127, 127, 127, 127, 127, 127, 127, 127, 127, 127, 127, 127, 127, 127, 127, 127, 127, 127, 127, 127, 127, 127, 127, 127, 127, 127, 127, 127, 143])
ser.write(valuesAllBright)

time.sleep(2)

print '#3 Write all NULL'
valuesAllNull = bytearray([128, 131, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 143])
ser.write(valuesAllNull)

time.sleep(1)

print '#4 Write SOMETHING'
# ser.write(b'\x80\x83\x01\x7F\x6F\x7F\x7F\x7F\x7F\x7F\x7F\x7F\x7F\x7F\x7F\x7F\x00\x7F\x7F\x7F\x7F\x7F\x7F\x7F\x7F\x7F\x7F\x7F\x7F\x7F\x7F\x8F')

valuesSomething = bytearray([128, 131, 1, 0, 66, 77, 66, 77, 66, 77, 66, 77, 99, 102, 99, 102, 99, 102, 99, 102, 99, 102, 0, 0, 0, 0, 0, 0, 0, 0, 0, 143])
ser.write(valuesSomething)

time.sleep(0.5)

ser.write(bytearray([128, 131, 1, 0, 66, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 143]))
time.sleep(0.1)
ser.write(bytearray([128, 131, 1, 0, 0, 66, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 143]))
time.sleep(0.1)
ser.write(bytearray([128, 131, 1, 0, 0, 0, 66, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 143]))
time.sleep(0.1)
ser.write(bytearray([128, 131, 1, 0, 0, 0, 66, 66, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 143]))
time.sleep(0.1)
ser.write(bytearray([128, 131, 1, 0, 0, 0, 0, 66, 66, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 143]))
time.sleep(0.1)
ser.write(bytearray([128, 131, 1, 0, 0, 0, 0, 66, 66, 66, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 143]))
time.sleep(0.1)
ser.write(bytearray([128, 131, 1, 0, 0, 0, 0, 0, 66, 66, 66, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 143]))
time.sleep(0.1)
ser.write(bytearray([128, 131, 1, 0, 0, 0, 0, 0, 66, 66, 66, 66, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 143]))
time.sleep(0.1)
ser.write(bytearray([128, 131, 1, 0, 0, 0, 0, 0, 66, 66, 66, 66, 66, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 143]))
time.sleep(0.1)
ser.write(bytearray([128, 131, 1, 0, 0, 0, 0, 0, 0, 66, 66, 66, 66, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 143]))
time.sleep(0.1)
ser.write(bytearray([128, 131, 1, 0, 0, 0, 0, 0, 0, 0, 66, 66, 66, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 143]))
time.sleep(0.1)
ser.write(bytearray([128, 131, 1, 0, 0, 0, 0, 0, 0, 0, 0, 66, 66, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 143]))
time.sleep(0.1)
ser.write(bytearray([128, 131, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 66, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 143]))
time.sleep(0.1)
ser.write(bytearray([128, 131, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 143]))

time.sleep(2)

print '#5 FIN'
