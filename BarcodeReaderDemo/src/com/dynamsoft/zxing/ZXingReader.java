package com.dynamsoft.zxing;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;

import com.dynamsoft.utils.BaseReader;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.multi.GenericMultipleBarcodeReader;

public class ZXingReader extends BaseReader {
	
	public void testZXing(String fileName) {
		long start = System.nanoTime();

		File file = new File(fileName);
		BufferedImage image = null;
		BinaryBitmap bitmap = null;
//		Result result = null;

		if (fileName.endsWith(TIFF_SUFFIX)) {
			RenderedImage tiff = JAI.create("fileload", fileName);
			image = PlanarImage.wrapRenderedImage(tiff).getAsBufferedImage();
		} else {
			try {
				image = ImageIO.read(file);
				if (image == null) {
					System.out.println("ImageIO do not support: " + file);
					return;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		int[] pixels = image.getRGB(0, 0, image.getWidth(), image.getHeight(),
				null, 0, image.getWidth());
		RGBLuminanceSource source = new RGBLuminanceSource(image.getWidth(),
				image.getHeight(), pixels);
		bitmap = new BinaryBitmap(new HybridBinarizer(source));

		Map<DecodeHintType, Object> hints = new HashMap<DecodeHintType, Object>();
		hints.put(DecodeHintType.TRY_HARDER, null);
		Collection<BarcodeFormat> formats = new ArrayList<>();
		formats.add(BarcodeFormat.QR_CODE);
		formats.add(BarcodeFormat.CODABAR);
		formats.add(BarcodeFormat.CODE_39);
		formats.add(BarcodeFormat.CODE_93);
		formats.add(BarcodeFormat.CODE_128);
		formats.add(BarcodeFormat.EAN_8);
		formats.add(BarcodeFormat.EAN_13);
		formats.add(BarcodeFormat.ITF);
		formats.add(BarcodeFormat.UPC_A);
		formats.add(BarcodeFormat.UPC_E);
		formats.add(BarcodeFormat.UPC_EAN_EXTENSION);

		hints.put(DecodeHintType.POSSIBLE_FORMATS, formats);
		MultiFormatReader reader = new MultiFormatReader(); 

		// read one barcode
		/*try {
			result = reader.decode(bitmap, hints);
			reader.reset();
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}

		if (result != null && !result.equals("")) {
			System.out.println(ZXING + TIME_COST
					+ ((System.nanoTime() - start) / 1000000) + MS);
			System.out.println(ZXING + " Type: " + result.getBarcodeFormat() + ", value: " + result.getText());
		} else {
			System.out.println(ZXING + FAILUTRE);
			return;
		}*/

		// read multi barcodes
		GenericMultipleBarcodeReader multiReader = new GenericMultipleBarcodeReader(
				reader);
		try {
			Result[] results = multiReader.decodeMultiple(bitmap, hints);
			System.out.println(ZXING + TIME_COST
					+ ((System.nanoTime() - start) / 1000000) + MS);
			if (results != null) {
				for (Result result : results) {
					System.out.println(ZXING + TYPE + result.getBarcodeFormat() + VALUE + result.getText());
				}
			}
		} catch (NotFoundException e) {
			e.printStackTrace();
			return;
		}

		mCount += 1;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return super.getCount();
	}
}
