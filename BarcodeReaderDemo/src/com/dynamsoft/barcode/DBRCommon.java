package com.dynamsoft.barcode;

public class DBRCommon {
	public static int compareCmd(String cmd, String dest) {
		return (cmd.compareTo(dest));
	}
	
	public static long GetFormat(String formats) {
		long lFormat = 0;
		String[] aryFormats = formats.split("\\|");
		
		for(int i=0; i<aryFormats.length; i++){
			String cmd = aryFormats[i].toUpperCase();
			
			if (compareCmd(cmd, "CODE_39") == 0)
				lFormat |= EnumBarCode.CODE_39;
			else if (compareCmd(cmd, "CODE_128") == 0)
				lFormat |= EnumBarCode.CODE_128;
			else if (compareCmd(cmd, "CODE_93") == 0)
				lFormat |= EnumBarCode.CODE_93;
			else if (compareCmd(cmd, "CODABAR") == 0)
				lFormat |= EnumBarCode.CODABAR;
			else if (compareCmd(cmd, "ITF") == 0)
				lFormat |= EnumBarCode.ITF;
			else if (compareCmd(cmd, "UPC_A") == 0)
				lFormat |= EnumBarCode.UPC_A;
			else if (compareCmd(cmd, "UPC_E") == 0)
				lFormat |= EnumBarCode.UPC_E;
			else if (compareCmd(cmd, "EAN_13") == 0)
				lFormat |= EnumBarCode.EAN_13;
			else if (compareCmd(cmd, "EAN_8") == 0)
				lFormat |= EnumBarCode.EAN_8;
			else if (compareCmd(cmd, "ONED") == 0)
				lFormat = EnumBarCode.OneD;
			else if (compareCmd(cmd, "INDUSTRIAL_25") == 0)
				lFormat = EnumBarCode.INDUSTRIAL_25;
			else if(compareCmd(cmd, "QR_CODE") == 0)
				lFormat = EnumBarCode.QR_CODE;
			
		}
		return lFormat;
	}


	public static String GetFormatStr(long format)
	{
		if (format == EnumBarCode.OneD)	
			return "OneD";
		if (format == EnumBarCode.CODE_39)
			return "CODE_39";
		if (format == EnumBarCode.CODE_128)
			return "CODE_128";
		if (format == EnumBarCode.CODE_93)
			return "CODE_93";
		if (format == EnumBarCode.CODABAR)	
			return "CODABAR";
		if (format == EnumBarCode.ITF)	
			return "ITF";
		if (format == EnumBarCode.UPC_A)	
			return "UPC_A";
		if (format == EnumBarCode.UPC_E)	
			return "UPC_E";
		if (format == EnumBarCode.EAN_13)	
			return "EAN_13";
		if (format == EnumBarCode.EAN_8)	
			return "EAN_8";
		if (format == EnumBarCode.INDUSTRIAL_25)
			return "INDUSTRIAL_25";
		if (format == EnumBarCode.QR_CODE)
			return "QR_CODE";

		return "Unknown";
	}
}
