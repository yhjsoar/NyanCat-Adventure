package nyancat_adventure;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client extends Thread{
	ResultObject obj;
	Socket ssoc = null;
	OutputStream sos = null;
	ObjectOutputStream soos = null;
	String type;
	
	Client(ResultObject obj){
		this.obj = obj;
		type ="store";
	}
	public void run() {
		try {
			Socket ssoc = new Socket("localhost",5000);
			
			sos = ssoc.getOutputStream();
			soos = new ObjectOutputStream(sos);
			soos.writeObject(obj);
			soos.flush();
			soos.close();
			sos.close();
			ssoc.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
