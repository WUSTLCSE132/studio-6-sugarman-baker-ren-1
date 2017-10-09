package studio6;

import jssc.*;

public class SerialComm {

	SerialPort port;

	private boolean debug;  // Indicator of "debugging mode"

	// This function can be called to enable or disable "debugging mode"
	void setDebug(boolean mode) {
		debug = mode;
	}	
	// Constructor for the SerialComm class
	public SerialComm(String name){
		try {

			port = new SerialPort(name);		
			port.openPort();
			port.setParams(SerialPort.BAUDRATE_9600,
					SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);

			debug = false; // Default is to NOT be in debug mode
		} catch (SerialPortException e) {
			System.out.println("Error opeining port. Please restart");
			System.exit(0);
		}
	}


	public void writeByte(byte b)
	{
		try {
			port.writeByte(b);
		} catch(SerialPortException e) {
			System.out.print("Error writing");
		}
		if(debug)
		{
			System.out.println("<0x"+b+">");
		}

	}

	
	// TODO: Add available() method
	public boolean available() {
		try {
			//System.out.println(port.getInputBufferBytesCount());
			return port.getInputBufferBytesCount() >0;
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			System.out.println("Error checking avaliable");
		}
		return false;
	}
	
	
	// TODO: Add readByte() method	
	
	public byte readByte() {
		byte b = 0;
		try {
			b = port.readBytes(1)[0];
		} catch (SerialPortException e) {
			// TODO Auto-generated catch block
			System.out.print("Error reading");
		}
		if(debug)
		{
			System.out.println("<x"+String.format("%02x", b)+">");
		}
		return b;
	}
	// TODO: Add a main() method
	
	public static void main(String[] args) {
		SerialComm s = new SerialComm("COM6");
		s.setDebug(true);
		while(true) {
			if(s.available()) {
				System.out.println((char)s.readByte());
				
			}
		}
	}
}
