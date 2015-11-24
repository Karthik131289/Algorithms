package javalib.nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by DV21 on 09-10-2015.
 */
public class FileHandling {

    public static void main(String[] args) {

    }

    private static void read( String path ) {
        try {
            RandomAccessFile file = new RandomAccessFile( path , "r" );
            FileChannel channel = file.getChannel();
            ByteBuffer buffer = ByteBuffer.allocateDirect( 1024 );
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }
}
