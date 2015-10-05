package javalib.utill;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Scanner;

/**
 * Created by DV21 on 15-09-2015.
 */
public class Base64Demo {

    public static void main(String[] args) throws IOException {
        System.out.println( " 1.Encoding-String \n 2.Decoding-String \n 3.Encoding-File \n 4.Decoding-File" );
        System.out.print( " Enter your choice... ");
        Integer choice = Integer.parseInt( scanner.next( INPUT_PATTERN ) );
        Base64Demo demo = new Base64Demo( choice );
    }

    public Base64Demo( Integer choice ) throws IOException {
        switch (choice) {
            case 1:
                System.out.print(" Please enter String to Encode : ");
                String data = scanner.next();
                System.out.println( " Encoded String : " + getEncodedString( data ) );
                break;
            case 2:
                System.out.print(" Please enter Encoded String to Decode : ");
                data = scanner.next();
                System.out.println( " Decoded String : " + getDecodedString(data) );
                break;
            case 3:
                System.out.print(" Please Enter Source File Path to Encode : ");
                String srcPath = scanner.next();
                System.out.print(" Please Enter Destination File Path to Write: ");
                String destPath = scanner.next();
                generateEncodedFile( srcPath , destPath );
                System.out.println( " Done...!");
                break;
            case 4:
                System.out.print(" Please Enter Encoded File Path to Decode : ");
                srcPath = scanner.next();
                System.out.print(" Please Enter Destination File Path to Write: ");
                destPath = scanner.next();
                generateEncodedFile( srcPath , destPath );
                System.out.println( " Done...!");
                break;
        }
    }
    
    private static String getEncodedString( String data ) {
        String toRet = null ;
        Base64.Encoder encoder = Base64.getEncoder();
        toRet = encoder.encodeToString( data.getBytes( StandardCharsets.UTF_8 ) );
        return toRet;
    }

    private static String getDecodedString( String encodedStr ) {
        String toRet = null;
        Base64.Decoder decoder = Base64.getDecoder();
        toRet = new String( decoder.decode( encodedStr ) );
        return toRet;
    }

    private static void generateEncodedFile( String srcPath , String destPath ) throws IOException {
        Base64.Encoder mimeEncoder = Base64.getMimeEncoder();
        FileInputStream inputStream = new FileInputStream( srcPath );
        FileOutputStream outStream = new FileOutputStream( destPath );
        OutputStream encodedStream = mimeEncoder.wrap( outStream );

        byte[] buff;
        int size =0;
        while ( ( size=inputStream.available()) > 0) {
            buff = new byte[ size ];
            inputStream.read( buff );
            encodedStream.write( buff );
        }

        encodedStream.close();
        inputStream.close();
        outStream.close();
    }

    private static void generateDecodedFile( String srcPath , String destPath ) throws IOException {
        Base64.Decoder mimeDecoder = Base64.getMimeDecoder();
        FileInputStream inputStream = new FileInputStream( srcPath );
        FileOutputStream outStream = new FileOutputStream( destPath );
        InputStream encodedStream = mimeDecoder.wrap( inputStream );

        byte[] buff;
        int size =0;
        while ( ( size=encodedStream.available()) > 0) {
            buff = new byte[ size ];
            encodedStream.read( buff );
            outStream.write( buff );
        }

        encodedStream.close();
        inputStream.close();
        outStream.close();
    }

    private static final Scanner scanner = new Scanner( System.in );
    public static final String INPUT_PATTERN = "[1-4]{1}";
}
