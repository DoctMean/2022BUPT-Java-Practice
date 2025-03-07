package src;

import jpcap.*;
import java.util.*;
import java.io.*;
import jpcap.packet.*;

public class App {

    public static int CapTime=1;

	public static void netcap(final String[] args) {

        NetworkInterface[] networkDevices = JpcapCaptor.getDeviceList();
        
        int deviceIndex = 0;
        
        for (NetworkInterface device : networkDevices) {
            deviceIndex++;
            System.out.printf("Index %d: %s | %s\n", deviceIndex, device.name, device.description);
            
            System.out.println("—————————————————————————————————————————————————————————————————————————————————————————————————————");
        }
        
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the INDEX of the network card you wish to monitor:");
        System.out.print(">>>");
        int selectedIndex = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Please enter the TIME(S) of the Capture you wish to maintain:");
        System.out.println("Option:'1'(default) or '<integer>' or '-1'(means infinite time)");
        System.out.print(">>>");
        CapTime = (int)(scanner.nextDouble()*1000);
        scanner.nextLine();
        System.out.println("Please enter the PROTOCOL TYPE of the Capture:");
        System.out.println("Option:'arp','ip','tcp','udp','icmp' or 'all'(default)");
        System.out.print(">>>");
        String CapMode = scanner.nextLine();
        System.out.println("Please enter the IP Add Request of the Capture:");
        System.out.println("Example:'host 127.0.0.1' or 'all'(default)");
        System.out.print(">>>");
        String IPrequest = scanner.nextLine();
        // Initialize JpcapCaptor instance
        JpcapCaptor Capt = null;
        if(CapTime > 0) {
        	try {
            	if(CapMode.contentEquals("arp")) {
            		Capt = JpcapCaptor.openDevice(networkDevices[selectedIndex-1], 65535, false, CapTime);
            		Capt.setFilter("arp", true);
            	} else if(CapMode.contentEquals("ip")) {
            		Capt = JpcapCaptor.openDevice(networkDevices[selectedIndex-1], 65535, false, CapTime);
            		Capt.setFilter("ip", true);
            	} else if(CapMode.contentEquals("tcp")) {
            		Capt = JpcapCaptor.openDevice(networkDevices[selectedIndex-1], 65535, false, CapTime);
            		Capt.setFilter("ip and tcp", true);
            	} else if(CapMode.contentEquals("udp")) {
            		Capt = JpcapCaptor.openDevice(networkDevices[selectedIndex-1], 65535, false, CapTime);
            		Capt.setFilter("ip and udp", true);
            	} else if(CapMode.contentEquals("icmp")) {
            		Capt = JpcapCaptor.openDevice(networkDevices[selectedIndex-1], 65535, false, CapTime);
            		Capt.setFilter("icmp", true);
            	} else {
            		Capt = JpcapCaptor.openDevice(networkDevices[selectedIndex-1], 65535, true, CapTime); 
        		}
            	if(!IPrequest.contentEquals("all")) {
            		Capt.setFilter(IPrequest, true);
            	}
            	System.out.println("");
            	System.out.printf("Please Wait for at MOST %d S.\n",CapTime/1000+1);
            	System.out.println("");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("An exception occurred while attempting to capture packets!");
            }
            
             
            MultiThread packetCapturingThread = new MultiThread(Capt);
            PrintBoard packetPrintThread = new PrintBoard();
            Thread packetCapturingThreadHandle = new Thread(packetCapturingThread);
            Thread packetPrintThreadHandle = new Thread(packetPrintThread);
            
            packetCapturingThreadHandle.start();
            packetPrintThreadHandle.start();
            try {
                Thread.sleep(CapTime); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            packetCapturingThread.run = false;
            try {
                Thread.sleep(100); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            packetPrintThread.run = false;
        }else {
        	while(true) {
        		try {
                	if(CapMode.contentEquals("arp")) {
                		Capt = JpcapCaptor.openDevice(networkDevices[selectedIndex-1], 65535, false, 1000);
                		Capt.setFilter("arp", true);
                	} else if(CapMode.contentEquals("ip")) {
                		Capt = JpcapCaptor.openDevice(networkDevices[selectedIndex-1], 65535, false, 1000);
                		Capt.setFilter("ip", true);
                	} else if(CapMode.contentEquals("tcp")) {
                		Capt = JpcapCaptor.openDevice(networkDevices[selectedIndex-1], 65535, false, 1000);
                		Capt.setFilter("ip and tcp", true);
                	} else if(CapMode.contentEquals("udp")) {
                		Capt = JpcapCaptor.openDevice(networkDevices[selectedIndex-1], 65535, false, 1000);
                		Capt.setFilter("ip and udp", true);
                	} else if(CapMode.contentEquals("icmp")) {
                		Capt = JpcapCaptor.openDevice(networkDevices[selectedIndex-1], 65535, false, 1000);
                		Capt.setFilter("icmp", true);
                	} else {
                		Capt = JpcapCaptor.openDevice(networkDevices[selectedIndex-1], 65535, true, 1000); 
            		}
                	if(!IPrequest.contentEquals("all")) {
                		Capt.setFilter(IPrequest, true);
                	}
                	System.out.println("");
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("An exception occurred while attempting to capture packets!");
                }
                
                 
                MultiThread packetCapturingThread = new MultiThread(Capt);
                PrintBoard packetPrintThread = new PrintBoard();
                Thread packetCapturingThreadHandle = new Thread(packetCapturingThread);
                Thread packetPrintThreadHandle = new Thread(packetPrintThread);
                
                packetCapturingThreadHandle.start();
                packetPrintThreadHandle.start();
                try {
                    Thread.sleep(2000); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                packetCapturingThread.run = false;
//                try {
//                    Thread.sleep(100); 
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                packetPrintThread.run = false;
        	}
        	
        }
        
    }
}
