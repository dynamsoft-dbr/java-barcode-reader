package com.dynamsoft.zbar;

import com.dynamsoft.utils.BaseReader;

public class ZBarReader extends BaseReader {

	static {
		System.loadLibrary("zbarjni");
	}
	
	public void testZBar(String fileName) {
		long start = System.nanoTime();
		ZBarResult[] results = (ZBarResult[])decode(fileName);
		System.out.println(ZBAR + TIME_COST
				+ ((System.nanoTime() - start) / 1000000) + MS);
		
		if (results != null && results.length > 0) {
			mCount += 1;
			for (ZBarResult result : results) {
				System.out.println(ZBAR + TYPE + result.mType + VALUE + result.mValue);
			}
		}
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return super.getCount();
	}
	
	public native Object[] decode(String fileName);
}
