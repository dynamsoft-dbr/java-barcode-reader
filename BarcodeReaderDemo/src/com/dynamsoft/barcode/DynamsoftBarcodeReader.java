package com.dynamsoft.barcode;

import com.dynamsoft.utils.BaseReader;

public class DynamsoftBarcodeReader extends BaseReader {
	
	public void testDBR(String fileName) {
		tagBarcodeResultArray paryResults = new tagBarcodeResultArray();

		long start = System.nanoTime();
		int iret = JBarcode.DBR_DecodeFile(fileName, Integer.MAX_VALUE, -1,
				paryResults);
		if (iret != 0) {
			System.out.println(TABLE + DBR + FAILUTRE + ERROR
					+ JBarcode.DBR_GetErrorString(iret));
			return;
		} else {
			System.out.println(DBR + TIME_COST
					+ ((System.nanoTime() - start) / 1000000) + MS);
			for (int iIndex = 0; iIndex < paryResults.iBarcodeCount; iIndex++) {
				tagBarcodeResult result = paryResults.ppBarcodes[iIndex];
				int barcodeDataLen = result.iBarcodeDataLength;

				byte[] barcodeData = new byte[barcodeDataLen];
				for (int x = 0; x < barcodeDataLen; x++) {
					barcodeData[x] = result.pBarcodeData[x];
				}

				System.out.println(DBR + TYPE
						+ DBRCommon.GetFormatStr(result.lFormat) + VALUE
						+ new String(barcodeData));
			}

			if (paryResults.iBarcodeCount == 0) {
				System.out.println(TABLE + DBR + FAILUTRE);
				return;
			}
		}

		mCount += 1;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return super.getCount();
	}
}
