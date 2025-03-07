package src;

import jpcap.*;
import jpcap.packet.*;
import src.javafx.MyItem;

public class MultiThread implements Runnable {
	    private JpcapCaptor jpcap;
	    boolean run = true;
	    public MultiThread(JpcapCaptor Capt) {
	        this.jpcap = Capt;
	    }
	    @Override
	    public void run() {

	        while (run) {

	            try {
	            	
	                Packet packet = jpcap.getPacket();
	                if(packet!=null) {
	                	System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		    	        System.out.println("Current Thread Name : " + Thread.currentThread().getName());
		                Analysis ProtoList = new Analysis(packet);
		                
		                ProtoList.analyze();
	                }
	                

	                Thread.sleep(100);
	                
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }

	        }
	    }
}

