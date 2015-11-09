
public class HeaderParams {
	
	private String strVersion;
	private String strIHL;
	private String strDSCPECN;
	private int iTotalLength;
	private int iIdentification;
	private int iFlagReserved;
	private int iFlagDF;
	private int iFlagMF;
	private int iFragmentOffset;
	private String strTTL;
	private String strProtocol;
	private String strChecksum;
	private String strSourceIP;
	private String strDestinationIP;
	
	public HeaderParams(String strVersion, String strIHL, String strDSCPECN, int iTotalLength,
			int iIdentification, int iFlagReserved, int iFlagDF,
			int iFlagMF, int iFragmentOffset, String strTTL,
			String strProtocol, String strChecksum, String strSourceIP, String strDestinationIP) {
		this.strVersion = strVersion;
		this.strIHL = strIHL;
		this.strDSCPECN = strDSCPECN;
		this.iTotalLength = iTotalLength;
		this.iIdentification = iIdentification;
		this.iFlagReserved = iFlagReserved;
		this.iFlagDF = iFlagDF;
		this.iFlagMF = iFlagMF;
		this.iFragmentOffset = iFragmentOffset;
		this.strTTL = strTTL;
		this.strProtocol = strProtocol;
		this.strChecksum = strChecksum;
		this.strSourceIP = strSourceIP;
		this.strDestinationIP = strDestinationIP;
		
		// TODO Auto-generated constructor stub
	}


	public String getStrVersion() {
		return strVersion;
	}

	public void setStrVersion(String strVersion) {
		this.strVersion = strVersion;
	}

	public String getStrIHL() {
		return strIHL;
	}

	public void setStrIHL(String strIHL) {
		this.strIHL = strIHL;
	}

	public String getStrDSCPECN() {
		return strDSCPECN;
	}

	public void setStrDSCPECN(String strDSCPECN) {
		this.strDSCPECN = strDSCPECN;
	}
	
	public int getiTotalLength() {
		return iTotalLength;
	}

	public void setiTotalLength(int iTotalLength) {
		this.iTotalLength = iTotalLength;
	}

	public String getStrSourceIP() {
		return strSourceIP;
	}

	public void setStrSourceIP(String strSourceIP) {
		this.strSourceIP = strSourceIP;
	}

	public String getStrDestinationIP() {
		return strDestinationIP;
	}

	public void setStrDestinationIP(String strDestinationIP) {
		this.strDestinationIP = strDestinationIP;
	}

	public int getiIdentification() {
		return iIdentification;
	}

	public void setiIdentification(int iIdentification) {
		this.iIdentification = iIdentification;
	}

	public int getiFlagReserved() {
		return iFlagReserved;
	}

	public void setiFlagReserved(int iFlagReserved) {
		this.iFlagReserved = iFlagReserved;
	}

	public int getiFlagDF() {
		return iFlagDF;
	}

	public void setiFlagDF(int iFlagDF) {
		this.iFlagDF = iFlagDF;
	}

	public int getiFlagMF() {
		return iFlagMF;
	}

	public void setiFlagMF(int iFlagMF) {
		this.iFlagMF = iFlagMF;
	}

	public int getiFragmentOffset() {
		return iFragmentOffset;
	}

	public void setiFragmentOffset(int iFragmentOffset) {
		this.iFragmentOffset = iFragmentOffset;
	}

	public String getStrTTL() {
		return strTTL;
	}

	public void setStrTTL(String strTTL) {
		this.strTTL = strTTL;
	}

	public String getStrProtocol() {
		return strProtocol;
	}

	public void setStrProtocol(String strProtocol) {
		this.strProtocol = strProtocol;
	}

	public String getStrChecksum() {
		return strChecksum;
	}

	public void setStrChecksum(String strChecksum) {
		this.strChecksum = strChecksum;
	}
	

}
