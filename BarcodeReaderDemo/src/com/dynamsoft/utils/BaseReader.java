package com.dynamsoft.utils;

public abstract class BaseReader {
	protected int mCount = 0;
	
	protected static final String TIME_COST = " Time cost: ";
	protected static final String FAILUTRE = " No barcode detected.";
	protected static final String ERROR = " Error: ";
	protected static final String TYPE = " Type: ";
	protected static final String VALUE = ", value: ";
	protected static final String TABLE = "\t";
	protected static final String MS = "ms";
	protected static final String DBR = "DBR";
	protected static final String ZXING = "ZXI";
	protected static final String ZBAR = "ZBA";
	protected static final String PASS = " passed: ";
	protected static final String TEXT_SUFFIX = ".txt";
	protected static final String TIFF_SUFFIX = ".tif";
	protected static final String SEPARATOR = "---------------------------------------------------------------------------------------------";

	public int getCount() {
		return mCount;
	}
}
