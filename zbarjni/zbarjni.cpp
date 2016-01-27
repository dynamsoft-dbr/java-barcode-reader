#include <iostream>
#include <Magick++.h>
#include <zbar.h>
#include <jni.h>
#define STR(s) #s

using namespace std;
using namespace zbar;

#ifndef DEBUG
#define DEBUG(...) printf(__VA_ARGS__)
#endif


extern "C" {
	JNIEXPORT jobjectArray JNICALL Java_com_dynamsoft_zbar_ZBarReader_decode(JNIEnv *env, jobject obj, jstring fileName);
}

JNIEXPORT jobjectArray JNICALL Java_com_dynamsoft_zbar_ZBarReader_decode(JNIEnv *env, jobject obj, jstring fileName)
{
	const char *pszFileName = env->GetStringUTFChars(fileName, 0);

#ifdef MAGICK_HOME
	// http://www.imagemagick.org/Magick++/
	//    under Windows it is necessary to initialize the ImageMagick
	//    library prior to using the Magick++ library
	Magick::InitializeMagick(MAGICK_HOME);
#endif

	// create a reader
	ImageScanner scanner;

	// configure the reader
	scanner.set_config(ZBAR_NONE, ZBAR_CFG_ENABLE, 1);

	// obtain image data
	Magick::Image magick(pszFileName);  // read an image file
	int width = magick.columns();   // extract dimensions
	int height = magick.rows();
	Magick::Blob blob;              // extract the raw data
	magick.modifyImage();
	magick.write(&blob, "GRAY", 8);
	const void *raw = blob.data();

	// wrap image data
	Image image(width, height, "Y800", raw, width * height);

	// scan the image for barcodes
	int n = scanner.scan(image);

	// find java class
	jclass clsZBarResult = env->FindClass("com/dynamsoft/zbar/ZBarResult");
	// create java array
	int data_length = 0;
	for (Image::SymbolIterator symbol = image.symbol_begin();
		symbol != image.symbol_end();
		++symbol) {
		++data_length;
	}

	jobjectArray clsZBarResultArray = env->NewObjectArray(data_length, clsZBarResult, 0);
	int iIndex = 0;

	// extract results
	for (Image::SymbolIterator symbol = image.symbol_begin();
		symbol != image.symbol_end();
		++symbol) {
		// do something useful with results
		//cout << "ZBR Type: " << symbol->get_type_name()
		//	<< ", Value \"" << symbol->get_data() << '"' << endl;

		// save result to java array
		jmethodID init = env->GetMethodID(clsZBarResult, "<init>", "()V");
		jobject clsZBarResultObj = env->NewObject(clsZBarResult, init);
		jfieldID jType = env->GetFieldID(clsZBarResult, "mType", "Ljava/lang/String;");
		jfieldID jValue = env->GetFieldID(clsZBarResult, "mValue", "Ljava/lang/String;");
		env->SetObjectField(clsZBarResultObj, jType, env->NewStringUTF(symbol->get_type_name().c_str()));
		env->SetObjectField(clsZBarResultObj, jValue, env->NewStringUTF(symbol->get_data().c_str()));
		env->SetObjectArrayElement(clsZBarResultArray, iIndex, clsZBarResultObj);
		++iIndex;		
	}

	// clean up
	image.set_data(NULL, 0);

	// release string
	env->ReleaseStringUTFChars(fileName, pszFileName);

	return clsZBarResultArray;
}
