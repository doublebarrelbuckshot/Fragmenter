import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;


public class Main {


	private static final int MTU = 1500;
	private static final int headerSize = 20;
	
	public static void main(String[] args){
		
		
		String line;
		BufferedReader in;

		String strVersion = "4";
		String strIHL = "5";
		String strDSCPECN = "00";
		int iIdentification = 51739;
		int iFlagReserved  = 0;
		int iFlagDF = 1;
		int iFlagMF = 1;
		int iFragmentOffset = 0;
		String strTTL = "40";
		String strProtocol = "01";
		String strChecksum = "0de9";
		int iTotalLength = MTU;
	

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		InetAddress sourceAddress = getUserInputIpAddress(br, "Source", "132.204.24.40");
		String strSourceIP =  parseIp(sourceAddress.getHostAddress());

		InetAddress destinationAddress = getUserInputIpAddress(br, "Destination", "132.204.26.162");
		String strDestinationIP =  parseIp(destinationAddress.getHostAddress());

		int maxCapacity = (MTU-headerSize) * 2;

		try {


			
			in = new BufferedReader(new FileReader("data.txt"));
			line = in.readLine();

			while(line != null)
			{
				iIdentification++;
				
				HeaderParams hp = new HeaderParams(strVersion, strIHL, strDSCPECN, iTotalLength, iIdentification, iFlagReserved, iFlagDF, iFlagMF, iFragmentOffset, strTTL, strProtocol, strChecksum, strSourceIP, strDestinationIP);

				
				//System.out.println("LineLength: " + line.length());
				int lineLength = line.length();
				int numberFrames = (int)Math.floor(lineLength * 1.0 / maxCapacity);
				//System.out.println(numberFrames);
				int remainder = lineLength % maxCapacity;
				//System.out.println(remainder);
				
				

				int start = 0;
				int end = maxCapacity;
				for (int i = 0; i<numberFrames; i++)
				{
					String data = line.substring(start, end); 
					
					String toPrint = packageFrame(data, hp);
					
					System.out.println("Frame: " + toPrint);
					if((remainder == 0 ) && (i == numberFrames -1))
					{
						hp.setiFlagMF(0);
					}
					
					hp.setiFragmentOffset(hp.getiFragmentOffset() + maxCapacity /16);
					
					//System.out.println( "FRAGMENT OFFSET" + hp.getiFragmentOffset());

					start = end;
					end = end + maxCapacity;
				}
				
			
				if(remainder > 0)
				{
					hp.setiFlagMF(0);
					hp.setiTotalLength(remainder/2 + 20);
					String data = line.substring(start, start + remainder);
					String toPrint = packageFrame(data, hp);
					System.out.println("Frame: " + toPrint);
				}
				//REMOVE ALL SPACES FOR PROCESSING
				line = in.readLine();

			}
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	private static String packageFrame(String data, HeaderParams hp)
	{
		String result = "";
		StringBuilder sb = new StringBuilder();
		/*
		System.out.println( "FRAGMENT OFFSET: " + hp.getiFragmentOffset());
		System.out.println("MF: " + hp.getiFlagMF());
		System.out.println("Identification: " + hp.getiIdentification());
		System.out.println("TotalLength: " + hp.getiTotalLength());
		*/
		
		
		/*
		 * PROCESS FLAG VALUE AND FRAGMENT OFFSET
		 */
		String flagValues = Integer.toString(hp.getiFlagReserved()) + Integer.toString(hp.getiFlagDF()) + Integer.toString(hp.getiFlagMF());
		String paddedBinaryOffset = convertToPaddedBinary(hp.getiFragmentOffset(), 13 );

		int flagsAndOffset = Integer.parseInt(flagValues + paddedBinaryOffset, 2);
		String flagAndOffset = convertToPaddedHex(flagsAndOffset, 4);
		
		//System.out.println("FLAG AND OFFSET: " + flagAndOffset);
		
		
		/*
		 * Process TOTAL LENGTH
		 */
		String strTotalLength = convertToPaddedHex(hp.getiTotalLength(), 4);
		//System.out.println("strTotalLength: " + strTotalLength);
		
		String strIdentification = convertToPaddedHex(hp.getiIdentification(), 4);
		//System.out.println("strIdentification: " + strIdentification);
		
		

		
		sb.append(hp.getStrVersion());
		sb.append(hp.getStrIHL());
		sb.append(hp.getStrDSCPECN());
		
		sb.append(strTotalLength);
		sb.append(strIdentification);
		sb.append(flagAndOffset);
		sb.append(hp.getStrTTL());
		sb.append(hp.getStrProtocol());
		sb.append(hp.getStrChecksum());
		sb.append(hp.getStrSourceIP());
		sb.append(hp.getStrDestinationIP());
		sb.append(data);
		
		
		return sb.toString();
	}

	
	private static String convertToPaddedBinary(int value, int totalSize)
	{
		return String.format("%" + totalSize + "s", Integer.toBinaryString(value)).replace(' ', '0');

	}
	
	private static String convertToPaddedHex(int value, int totalSize)
	{
		return String.format("%" + totalSize + "s", Integer.toHexString(value)).replace(' ', '0');

	}
	private static String parseIp(String fullData){
		String[] parts = fullData.split("\\.");

		String result = "";
		for (int i = 0; i<parts.length; i++)
		{
			result += Integer.toHexString(Integer.parseInt(parts[i]));
		}

		return result;
	}




	/*
	 * INPUT IP ADDRESS
	 */
	private static InetAddress getUserInputIpAddress(BufferedReader br, String sourceDest, String strDefault) 
	{
		boolean successIP = false;
		String strIP = "";
		InetAddress adresse = null;
		System.out.println("Please enter a " + sourceDest + " IP address, or press 'Enter' to use the default IP address " + strDefault);// (127.0.0.1)");
		while(!successIP)
		{
			try
			{
				strIP = br.readLine();
				System.out.println(strIP);
				if(strIP.equals(""))
				{
					adresse = InetAddress.getByName(strDefault);
					successIP = true;
				}
				else
				{
					adresse = InetAddress.getByName(strIP);
					successIP = true;
				}
			}
			catch (Exception e)
			{
				System.out.println("Error, please enter a " + sourceDest + " IP address or press 'Enter' to use default IP address " + strDefault);//(127.0.0.1)");
			}
		}
		return adresse;
	}


}
