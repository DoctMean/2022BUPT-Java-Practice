package src;

import java.util.ArrayList;
import java.util.Arrays;

import jpcap.*;
import jpcap.packet.*;

public class PrintBoard implements Runnable {
    private JpcapCaptor jpcap;
    boolean run = true;

    @Override
    public void run() {

        while (run) {

            try {
            	Thread.sleep(App.CapTime>0 ? App.CapTime+100:5000);
            	System.out.println("<---------------------------------------------------------------------------------------------------->");
            	System.out.println("Current Capture Summary:");
        		System.out.printf("1.Total packets captured: %d\n", Analysis.totalPackets);
        		System.out.printf("2.ARP packets captured: %d\n", Analysis.arpPackets);
        		System.out.printf("3.IP(Neither TCP Nor UDP) packets captured: %d\n", Analysis.ipPackets);
        		System.out.printf("4.TCP packets captured: %d (Total Length: %d bytes)\n", Analysis.tcpPackets, Analysis.totalTcpLength);
        		System.out.printf("5.UDP packets captured: %d (Total Length: %d bytes)\n", Analysis.udpPackets, Analysis.totalUdpLength);
        		System.out.printf("6.ICMP packets captured: %d\n", Analysis.icmpPackets);
        		System.out.printf("7.Packet protocol packets captured: %d\n", Analysis.unknownPackets);
        		System.out.printf("8.Other packets captured: %d\n", Analysis.elsePackets);
        		System.out.println("<---------------------------------------------------------------------------------------------------->");
        		ArrayList<String> newstring = new ArrayList<>(Arrays.asList(new String[16]));
        		newstring.set(0, "===S U M M A R Y===");
         		newstring.set(1, "<Total packets captured>"+Analysis.totalPackets);
         		newstring.set(2, "<ARP packets captured>" + Analysis.arpPackets);
         		newstring.set(3, "<IP(Neither TCP Nor UDP) packets captured>" + Analysis.ipPackets);
         		newstring.set(4, "<TCP packets captured>" + Analysis.tcpPackets);
         		newstring.set(5, "<TCP packets Length>" + Analysis.totalTcpLength);
         		newstring.set(6, "<UDP packets captured>" + Analysis.udpPackets);
         		newstring.set(7, "<UDP packets Length>" + Analysis.totalUdpLength);
         		newstring.set(8, "<ICMP packets captured>" + Analysis.icmpPackets);
         		newstring.set(9, "<Packet protocol packets captured>" + Analysis.unknownPackets);
         		newstring.set(10, "<Other packets captured>" + Analysis.elsePackets);
         		newstring.set(11, "");
         		newstring.set(12, "");
         		newstring.set(13, "");
         		newstring.set(14, "");
         		newstring.set(15, "");
         		netcap.listOfArrays.add(newstring);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
