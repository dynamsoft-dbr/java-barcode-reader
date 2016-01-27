package com.dynamsoft.test;

import java.io.File;
import com.dynamsoft.barcode.DynamsoftBarcodeReader;
import com.dynamsoft.utils.BaseReader;
import com.dynamsoft.zbar.ZBarReader;
import com.dynamsoft.zxing.ZXingReader;

public class Benchmark extends BaseReader {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Folder path
		String folder = "F:\\Program Files (x86)\\Dynamsoft\\Barcode Reader 3.0 Trial\\Images";
//		folder = "F:\\resources\\benchmark\\android-1";
//		folder = "F:\\resources\\blackbox\\qrcode-1";
//		folder = "F:\\resources\\blackbox\\falsepositives-2";
		File path = new File(folder);
		DynamsoftBarcodeReader dbr = new DynamsoftBarcodeReader();
		ZXingReader zxr = new ZXingReader();
		ZBarReader zbr = new ZBarReader();
		
		if (path.isDirectory()) {
			File[] list = path.listFiles();
			for (File file : list) {
				if (file.isDirectory())
					continue;
				
				String fileName = file.getAbsolutePath();
				if (fileName.endsWith(TEXT_SUFFIX))
					continue;
				
				System.out.println(TABLE + fileName);
				
				dbr.testDBR(fileName);
				zxr.testZXing(fileName);
				zbr.testZBar(fileName);
				System.out.println(SEPARATOR);
				// return;
			}
		}
		
		System.out.println(DBR + PASS + dbr.getCount());
		System.out.println(ZXING + PASS + zxr.getCount());
		System.out.println(ZBAR + PASS + zbr.getCount());
	}
}
