package nyancat_adventure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;

public class Server extends Thread{
	Socket soc = null;
	ResultObject obj;
	
	InputStream is = null;
	ObjectInputStream os = null;

	String path=System.getProperty("user.dir")+"\\scoreboard_data.csv";
	public void run() {
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(5000);
			System.out.println("Server Start");
			soc = ss.accept();
			is = soc.getInputStream();
			os = new ObjectInputStream(is);
			obj = (ResultObject)os.readObject();
			recordWriter(obj);
			this.soc.close();
			
			
			
			
			System.out.println("Server Stop");
			ss.close();
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void recordWriter(ResultObject score) {
		try {
			ArrayList<ResultObject> ResultList = new ArrayList<ResultObject>();
			InputStream fis=new FileInputStream(path);
			Reader isr=new InputStreamReader(fis);
	        BufferedReader br=new BufferedReader(isr);
	      
	        String line;
	        int i=1;
	        while((line=br.readLine())!=null) {
	        	String[] tmp=line.split(",");
	            String name=tmp[0];
	            ResultList.add(new ResultObject(name,Integer.parseInt(tmp[1])));
	        }
	        ResultList.add(score);
	        Collections.sort(ResultList);
	        ResultList.remove(ResultList.size()-1);
	        
	        OutputStream fos = new FileOutputStream(path);
	        Writer osw=new OutputStreamWriter(fos);
	        BufferedWriter bw=new BufferedWriter(osw);
	         
	        ResultObject data;
	        for(i=0;i<ResultList.size();i++) {
	            line="";
	            data=ResultList.get(i);
	            line=data.name+","+Integer.toString(data.score)+"\n";
	            bw.write(line);
	         }
	        
	        bw.flush();
	        bw.close();
	        osw.close();
	        fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
