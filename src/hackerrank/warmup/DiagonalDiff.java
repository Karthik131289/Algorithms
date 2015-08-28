package hackerrank.warmup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by DV21 on 21-08-2015.
 */
public class DiagonalDiff {

    public static void main(String[] args) throws IOException {
        System.out.println( "**** Diagonal Difference ****" );
        System.out.println( "Enter Matrix Size( No of Row & Col Should be equal ) : ");
        BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) );
        String data = br.readLine();

        if( data == null || data == "" ) {
            System.out.println( "Invalid Input...Exiting." );
            System.exit(-1);
        }
        Integer size = Integer.parseInt( data );
        Integer[][] matrix = new Integer[size][size];

        System.out.println("Enter Values...");
        for (int i=0; i<size; i++ )
            for ( int j=0; j<size; j++ ) {
                data = br.readLine();
                if( data==null || data=="" ) {
                    System.out.println( "Invalid Input...Exiting." );
                    System.exit(-1);
                }
                else  {
                    Integer input = Integer.parseInt( data );
                    matrix[i][j] = input;
                }
            }
        dispMatrixValues( matrix , size );
        int sum1 = getDiagonalSum( matrix , size , false );
        int sum2 = getDiagonalSum( matrix , size , true );
        System.out.println( "\n\n sum1 : " + sum1 + " sum2 : " + sum2 );
        int diff = sum1 - sum2;
        System.out.println( " Diff : " + diff );
    }

    private static void dispMatrixValues( Integer[][] matrix , int size ) {
        System.out.println("Matrix Values are ....");
        for( int i=0; i<size; i++ ) {
            System.out.println();
            for (int j = 0; j < size; j++)
                System.out.print("\t" + matrix[i][j]);
        }
    }

    private static Integer getDiagonalSum( Integer[][] matrix , int size , boolean reverse ) {
        Integer sum = 0;
        for (int i = (reverse?size-1:0); reverse?(i>=0):(i < size); i=reverse?i-1:i+1 ) {
            for (int j = (reverse?size-1:0); reverse?(j>=0):(j < size); j=reverse?j-1:j+1 ) {
                if (i == j)
                    sum += matrix[i][j];
            }
        }
        return sum;
    }
}
