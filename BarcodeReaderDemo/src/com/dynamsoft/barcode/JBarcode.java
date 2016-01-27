package com.dynamsoft.barcode;

public class JBarcode {

	static {

		try {
			System.loadLibrary("DynamsoftBarcodeJNIx86");
		} catch (Exception e) {
			System.err.println("load jni error!");
		}
    	
}
  
    public native static int DBR_InitLicense(
		String pLicense	//License Key
	);

    public native static int DBR_DecodeFile(
		String pFileName,
		
		int option_iMaxBarcodesNumPerPage,
		long option_lBarcodeFormat,
		
		tagBarcodeResultArray ppResults	//Barcode Results
	);	

    public native static int DBR_DecodeFileRect(
		String pFileName,

		int option_iMaxBarcodesNumPerPage,
		long option_lBarcodeFormat,
		
		int iRectLeft,			//Rectangle Left
		int iRectTop,			//Rectangle Top
		int iRectWidth,			//Rectangle 
		int iRectHeight,		//Rectangle 
		tagBarcodeResultArray ppResults	// Barcode Results
	);

    public native static int DBR_DecodeBuffer(
		byte[] pDIBBuffer,	//Buffer
        int iDIBSize,
        
		int option_iMaxBarcodesNumPerPage,
		long option_lBarcodeFormat,
		
        tagBarcodeResultArray ppResults	//Barcode Results
	);

    public native static int DBR_DecodeBufferRect(
		byte[] pDIBBuffer,	//Buffer
        int iDIBSize,	
        
		int option_iMaxBarcodesNumPerPage,
		long option_lBarcodeFormat,
		
        int iRectLeft,			//Rectangle Left
        int iRectTop,			//Rectangle Top
        int iRectWidth,			//Rectangle 
        int iRectHeight,		//Rectangle 
        tagBarcodeResultArray ppResults	//Barcode Results
	);


    public native static String DBR_GetErrorString(int iErrorCode);

}