package src;

import java.util.ArrayList;
import java.util.Arrays;

import jpcap.packet.*;
import src.javafx.MyItem;

		/**
		 * This class is responsible for analyzing captured network packets
		 * and keeping track of various packet statistics.
		 */
public class Analysis {
		    /**
		     * The captured packet to be analyzed.
		     */
	private Packet capturedPacket;

		    /**
		     * Constructs a new PacketAnalyzer with the specified packet.
		     *
		     * @param packet The packet to analyze.
		     */
	public Analysis(Packet packet) {
		this.capturedPacket = packet;
	}

		    /**
		     * Counters for various types of packets and packet lengths.
		     */
	public static int totalPackets = 0;
		    
	public static int unknownPackets = 0;
	public static int ipPackets = 0;
	public static int tcpPackets = 0;
	public static int udpPackets = 0;
	public static int icmpPackets = 0;
	public static int arpPackets = 0;
	public static int elsePackets = 0;
	public static int totalTcpLength = 0;
	public static int totalUdpLength = 0;

		    /**
		     * Analyzes the captured packet and updates the statistics.
		     */
	public void analyze() {
		 // Analyze the data link layer, which is present in every packet.
		 // Assuming EthernetPacket is a subclass of DataPacket.
		
		if(capturedPacket!=null) {
		totalPackets++;
			EthernetPacket dataLinkLayer = (EthernetPacket) capturedPacket.datalink;
			System.out.println("Packet No." + String.valueOf(totalPackets));
			System.out.println("Ethernet Header: " + dataLinkLayer.toString());
			System.out.println("Frame Type: " + dataLinkLayer.frametype);
			System.out.println("Src MAC Address: " + dataLinkLayer.getSourceAddress());
			System.out.println("Dst MAC Address: " + dataLinkLayer.getDestinationAddress());
			System.out.println("vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv");
			System.out.println("");
			System.out.println("------------------------------------------------------------------------------------------------------");
			if (capturedPacket instanceof jpcap.packet.Packet) {
				// Handle packets of unknown protocol type with custom identification.
				if (capturedPacket.getClass().equals(Packet.class)) {
			         unknownPackets++;
			    } else if (capturedPacket.getClass().equals(ARPPacket.class)) {
			         arpPackets++;
			         ARPPacket arpPacket = (ARPPacket) capturedPacket;
			         printARPPacketDetails(arpPacket);
			    } else if (capturedPacket.getClass().equals(IPPacket.class)) {
			         ipPackets++;
			         IPPacket ipPacket = (IPPacket) capturedPacket;
			         printIPPacketDetails(ipPacket);
			    } else if (capturedPacket.getClass().equals(TCPPacket.class)) {
			         tcpPackets++;
			         TCPPacket tcpPacket = (TCPPacket) capturedPacket;
			         totalTcpLength += tcpPacket.len;
			         printTCPPacketDetails(tcpPacket);
			    } else if (capturedPacket.getClass().equals(UDPPacket.class)) {
			         udpPackets++;
			         UDPPacket udpPacket = (UDPPacket) capturedPacket;
			         totalUdpLength += udpPacket.len;
			         printUDPPacketDetails(udpPacket);
			    } else if (capturedPacket.getClass().equals(ICMPPacket.class)) {
			         icmpPackets++;
			         ICMPPacket icmpPacket = (ICMPPacket) capturedPacket;
			         printICMPPacketDetails(icmpPacket);
			    } else {
			         // Increment the counter for other unidentified packet types.
			         elsePackets++;
			         System.out.println("Unknown Protocol.");
			    }
			} else {
			    System.out.println("No packet captured.");
			}			
				
			
			// Output the type of the packet and the summary of captured packets.
			System.out.println("Data Packet Type: " + (capturedPacket.getClass()).toString().substring(19));
			System.out.println("------------------------------------------------------------------------------------------------------");
			
			System.out.println("");
			System.out.println("");
		}
		
	}
	private static String bytesToHex(byte[] bytes) {
		StringBuilder hexString = new StringBuilder();
	    for (byte b : bytes) {
	        String hex = Integer.toHexString(0xff & b);
	        if (hex.length() == 1) {
	            hexString.append('0');
	        }
	        hexString.append(hex);
	        hexString.append(' ');
	    }
	    return hexString.toString().trim();
	}

	private static String bytesToAscii(byte[] bytes) {
		StringBuilder asciiString = new StringBuilder();
		for (byte b : bytes) {
	        if (b >= 32 && b < 127) {
	            asciiString.append((char) b);
	        } else {
	            asciiString.append("");
	        }
	    }
		return asciiString.toString();
	}
	private void printARPPacketDetails(ARPPacket arpPacket) {
		System.out.println("Hardware Type : " + arpPacket.hardtype);
		System.out.println("Prototype : " + arpPacket.prototype);
		System.out.println("Operation : " + arpPacket.operation);
		System.out.println("IP Header : " + arpPacket.toString());
		System.out.println("Sender MAC Add : " + arpPacket.getSenderHardwareAddress());
 		System.out.println("Target MAC Add : " + arpPacket.getTargetHardwareAddress());
 		System.out.println("Sender IP Add : " + arpPacket.getSenderProtocolAddress());
 		System.out.println("Target IP Add : " + arpPacket.getTargetProtocolAddress());
 		ArrayList<String> newstring = new ArrayList<>(Arrays.asList(new String[16]));
 		newstring.set(0, "<ARP Hardtype>"+String.valueOf(arpPacket.hardtype));
 		newstring.set(1, "<ARP Prototype>"+arpPacket.prototype);
 		newstring.set(2, "<ARP OperaId>"+arpPacket.operation);
 		newstring.set(3, "<IP Header>" + arpPacket.toString());
 		newstring.set(4, "<源MAC>" + arpPacket.getSenderHardwareAddress());
 		newstring.set(5, "<目的MAC>" + arpPacket.getTargetHardwareAddress());
 		newstring.set(6, "<源IP>" + arpPacket.getSenderProtocolAddress());
 		newstring.set(7, "<目的IP>" + arpPacket.getTargetProtocolAddress());
 		newstring.set(8, "");
 		newstring.set(9, "");
 		newstring.set(10, "");
 		newstring.set(11, "");
 		newstring.set(12, "");
 		newstring.set(13, "");
 		newstring.set(14, "");
 		newstring.set(15, "");
 		netcap.listOfArrays.add(newstring);
	}
	
	private void printIPPacketDetails(IPPacket Packet) {
		System.out.println("IP Header : " + Packet.toString());
		System.out.println("Version : " + Packet.version);
		System.out.println("Don't Frag : " + Packet.dont_frag);
		System.out.println("More Frag : " + Packet.more_frag);
		System.out.println("Timer Sec : " + Packet.sec);
		System.out.println("Timer μsec : " + Packet.usec);
		System.out.println("Src IP Add: " + Packet.src_ip.getHostAddress());
		System.out.println("Dst IP Add: " + Packet.dst_ip.getHostAddress());
		System.out.println("Protocol : " + Packet.protocol);
	 	System.out.println("Priority : " + Packet.priority);
	 	System.out.println("Sur Time : " + Packet.hop_limit);	
		System.out.println("Rsv Frag : " + Packet.rsv_frag);
	 	System.out.println("Offset : " + Packet.offset);
	 	System.out.println("Ident : " + Packet.ident);
	 	IPPacket ippacket = (IPPacket) Packet;
	 	byte[] payload = new byte[ippacket.data.length];
	    if (ippacket.data.length > 0) {
	    	
	        System.arraycopy(ippacket.data, 0, payload, 0, ippacket.data.length);
	        
	        // 显示原始的十六进制流
	        System.out.println("Raw Hex Stream: " + bytesToHex(payload));
	        
	        // 解析成ASCII码格式
	        String asciiString = bytesToAscii(payload);
	        if (asciiString != null) {
	            System.out.println("ASCII String: " + asciiString);
	        }
	    }
	 	ArrayList<String> newstring = new ArrayList<>(Arrays.asList(new String[16]));
	 	newstring.set(0, "<IP Header>"+Packet.toString());
	 	newstring.set(1, "<Version>"+Packet.version);
	 	newstring.set(2, "<DF>"+Packet.dont_frag);
	 	newstring.set(3, "<MF>" + Packet.more_frag);
	 	newstring.set(4, "<Timer Sec>" + Packet.sec);
 		newstring.set(5, "<Timer μsec>" + Packet.usec);
 		newstring.set(6, "<源IP>" + Packet.src_ip.getHostAddress());
 		newstring.set(7, "<目的IP>" + Packet.dst_ip.getHostAddress());
 		newstring.set(8, "<Protocol>" + Packet.protocol);
 		newstring.set(9, "<Priority>" + Packet.priority);
 		newstring.set(10, "<Survive Time>" + Packet.hop_limit);
 		newstring.set(11, "<RF>" + Packet.rsv_frag);
 		newstring.set(12, "<Offset>" + Packet.offset);
 		newstring.set(13, "<Ident>" + Packet.ident);
 		newstring.set(14, "<Hex Stream>"+bytesToHex(payload));
 		newstring.set(15, "<Ascii>"+bytesToAscii(payload));
 		netcap.listOfArrays.add(newstring);
	}
	

	
	private void printTCPPacketDetails(TCPPacket tcpPacket) {
		
		System.out.println("TCP Header : " + tcpPacket.toString());
		System.out.println("Don't Frag : " + tcpPacket.dont_frag);
		System.out.println("More Frag : " + tcpPacket.more_frag);
		System.out.println("Src Port : " + tcpPacket.src_port);
		System.out.println("Dst Port : " + tcpPacket.dst_port);
		System.out.println("ACK Flag : " + tcpPacket.ack);
		System.out.println("ACK Num : " + tcpPacket.ack_num);
		System.out.println("FIN Flag : " + tcpPacket.fin);
		System.out.println("SYN Flag : " + tcpPacket.syn);
		System.out.println("Seq : " + tcpPacket.sequence);
		System.out.println("Window Size : " + tcpPacket.window);
		System.out.println("TCP Length : " + tcpPacket.length);
		System.out.println("Offset : " + tcpPacket.offset);
		System.out.println("Ident : " + tcpPacket.ident);
		TCPPacket tcppacket = (TCPPacket) tcpPacket;
		byte[] payload = new byte[tcppacket.data.length];
	    if (tcppacket.data.length > 0) {
	    	
	        System.arraycopy(tcppacket.data, 0, payload, 0, tcppacket.data.length);
	        
	        // 显示原始的十六进制流
	        System.out.println("Raw Hex Stream: " + bytesToHex(payload));
	        
	        // 解析成ASCII码格式
	        String asciiString = bytesToAscii(payload);
	        if (asciiString != null) {
	            System.out.println("ASCII String: " + asciiString);
	        }
	    }
		ArrayList<String> newstring = new ArrayList<>(Arrays.asList(new String[16]));
		newstring.set(0, "<TCP Header>"+tcpPacket.toString());
 		newstring.set(1, "<DF>"+tcpPacket.dont_frag);
 		newstring.set(2, "<MF>" + tcpPacket.more_frag);
 		newstring.set(3, "<源Port>" + tcpPacket.src_port);
 		newstring.set(4, "<目的Port>" + tcpPacket.dst_port);
 		newstring.set(5, "<ACK Flag>" + tcpPacket.ack);
 		newstring.set(6, "<ACK Num>" + tcpPacket.ack_num);
 		newstring.set(7, "<FIN Flag>" + tcpPacket.fin);
 		newstring.set(8, "<SYN Flag>" + tcpPacket.syn);
 		newstring.set(9, "<Seq>" + tcpPacket.sequence);
 		newstring.set(10, "<Window Size>" + tcpPacket.window);
 		newstring.set(11, "<TCP Length>" + tcpPacket.length);
 		newstring.set(12, "<Offset>" + tcpPacket.offset);
 		newstring.set(13, "<Ident>" + tcpPacket.ident);
 		newstring.set(14, "<Hex Stream>"+bytesToHex(payload));
 		newstring.set(15, "<Ascii>"+bytesToAscii(payload));
 		netcap.listOfArrays.add(newstring);
	}
	
	private void printUDPPacketDetails(UDPPacket udpPacket) {
		System.out.println("UDP Header : " + udpPacket.toString());
		System.out.println("Don't Frag : " + udpPacket.dont_frag);
		System.out.println("More Frag : " + udpPacket.more_frag);
		System.out.println("Src Port : " + udpPacket.src_port);
		System.out.println("Dst Port : " + udpPacket.dst_port);
		System.out.println("UDP Length : " + udpPacket.length);
		System.out.println("Offset : " + udpPacket.offset);
		System.out.println("Ident : " + udpPacket.ident);
		UDPPacket udppacket = (UDPPacket) udpPacket;
		byte[] payload = new byte[udppacket.data.length];
	    if (udppacket.data.length > 0) {
	    	
	        System.arraycopy(udppacket.data, 0, payload, 0, udppacket.data.length);
	        
	        // 显示原始的十六进制流
	        System.out.println("Raw Hex Stream: " + bytesToHex(payload));
	        
	        // 解析成ASCII码格式
	        String asciiString = bytesToAscii(payload);
	        if (asciiString != null) {
	            System.out.println("ASCII String: " + asciiString);
	        }
	    }
		ArrayList<String> newstring = new ArrayList<>(Arrays.asList(new String[16]));
		newstring.set(0, "<UDP Header>"+udpPacket.toString());
 		newstring.set(1, "<DF>"+udpPacket.dont_frag);
 		newstring.set(2, "<MF>" + udpPacket.more_frag);
 		newstring.set(3, "<源Port>" + udpPacket.src_port);
 		newstring.set(4, "<目的Port>" + udpPacket.dst_port);
 		newstring.set(5, "<UDP Length>" + udpPacket.length);
 		newstring.set(6, "<Offset>" + udpPacket.offset);
 		newstring.set(7, "<Ident>" + udpPacket.ident);
 		newstring.set(8, "<Hex Stream>"+bytesToHex(payload));
 		newstring.set(9, "<Ascii>"+bytesToAscii(payload));
 		newstring.set(10, "");
 		newstring.set(11, "");
 		newstring.set(12, "");
 		newstring.set(13, "");
 		newstring.set(14, "");
 		newstring.set(15, "");
 		netcap.listOfArrays.add(newstring);
	}
	
	private void printICMPPacketDetails(ICMPPacket icmpPacket) {
		System.out.println("ICMP Header : " + icmpPacket.toString());
		System.out.println("Don't Frag : " + icmpPacket.dont_frag);
		System.out.println("More Frag : " + icmpPacket.more_frag);
		System.out.println("ICMP Type : " + icmpPacket.type);
		System.out.println("ICMP Code : " + icmpPacket.code);
		System.out.println("Offset : " + icmpPacket.offset);
		System.out.println("Ident : " + icmpPacket.ident);
		ArrayList<String> newstring = new ArrayList<>(Arrays.asList(new String[16]));
		newstring.set(0, "<ICMP Header>"+icmpPacket.toString());
 		newstring.set(1, "<DF>"+icmpPacket.dont_frag);
 		newstring.set(2, "<MF>" + icmpPacket.more_frag);
 		newstring.set(3, "<ICMP Type>" + icmpPacket.type);
 		newstring.set(4, "<ICMP Code>" + icmpPacket.code);
 		newstring.set(5, "<UDP Length>" + icmpPacket.length);
 		newstring.set(6, "<Offset>" + icmpPacket.offset);
 		newstring.set(7, "<Ident>" + icmpPacket.ident);
 		newstring.set(8, "");
 		newstring.set(9, "");
 		newstring.set(10, "");
 		newstring.set(11, "");
 		newstring.set(12, "");
 		newstring.set(13, "");
 		newstring.set(14, "");
 		newstring.set(15, "");
 		netcap.listOfArrays.add(newstring);
	}

}