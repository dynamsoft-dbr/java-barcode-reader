Making Java Barcode Reader with Dynamsoft Barcode SDK
=======================================================================

The sample shows how to call native C/C++ methods of [Dynamsoft Barcode Reader][1] (DBR) SDK via JNI to create a Java Barcode Reader on Windows.

Download & Installation
-----------------------
* [Dynamsoft Barcode Reader SDK][2]
![image](http://www.codepool.biz/wp-content/uploads/2015/05/dbr_folder.png)

How to Run
-----------
1. configure directories of DBR header files and libraries in Visual Studio.
![image](http://www.codepool.biz/wp-content/uploads/2015/05/dbr_include-1024x462.png)
![image](http://www.codepool.biz/wp-content/uploads/2015/05/dbr_lib-1024x462.png)
2. build the JNI project.
3. copy generated DLL and DBR DLL to Java Barcode Reader Project.
![image](http://www.codepool.biz/wp-content/uploads/2015/05/dbr_java.png)
4. import the Java project into Eclipse.
5. specify an image file path as argument, and then run the Java project.
![image](http://www.codepool.biz/wp-content/uploads/2015/05/dbr_args.png)
![image](http://www.codepool.biz/wp-content/uploads/2015/05/dbr_results1.png)

How to Build and Run ZBar JNI Project
-----------------------------
1. Download and install ImageMagick-6.9.2-0-Q16-x86-dll.exe: http://www.imagemagick.org/download/binaries/ImageMagick-6.9.2-0-Q16-x86-dll.exe
2. Download and install ZBar 0.1: http://sourceforge.net/projects/zbar/files/zbar/0.10/
3. Import the JNI project to Visual Studio and add relevant include directories and library directories.
4. Build the project with win32.
5. Copy the generated **zbarjni.dll** to Java project.
6. Run **Benchmark.java** to read barcodes with ZBar.

Blog
-----
* [How to Make Java Barcode Reader with Dynamsoft Barcode SDK][3]
* [How to Benchmark Barcode SDK Performance – ZXing vs ZBar][4]

[1]:http://www.dynamsoft.com/Products/Dynamic-Barcode-Reader.aspx
[2]:http://www.dynamsoft.com/Downloads/Dynamic-Barcode-Reader-Download.aspx
[3]:http://www.codepool.biz/barcode/how-to-make-java-barcode-reader-with-dynamsoft-barcode-sdk.html
[4]:http://www.codepool.biz/barcode-sdk-performance-zxing-zbar.html
