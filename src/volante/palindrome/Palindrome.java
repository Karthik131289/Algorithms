package volante.palindrome;

import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

/**
 * Created by DV21 on 24-11-2015.
 */
public class Palindrome {

    public static void main(String[] args) throws URISyntaxException {
        readFile( Palindrome.class.getResource( "dictionary.txt" ).toURI().getPath() );
        doSort( getPalindromeWords() );
        System.out.println( getTop3Words() );
    }

    private static void readFile( String path ) {
        try {
            FileReader fileReader = new FileReader( path );
            BufferedReader reader = new BufferedReader( fileReader );

            String word = null;
            while ( (word = reader.readLine()) != null ) {
                boolean res = isPalindrome( word );
                if( res )
                    palindromeWords.add( word );
            }

            fileReader.close();
            reader.close();
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    private static boolean isPalindrome( String word ) {
        boolean res = true;

        word = word.toLowerCase();

        int len = word.length()/2;
        for( int i=0,j=word.length()-1; i<len; i++,j-- )
            if( word.charAt( i ) != word.charAt( j ) )
            {
                res = false;
                break;
            }
        return res;
    }

    private static void doSort( Vector<String> wordList ) {
        Collections.sort( wordList , new SortWords() );
    }

    private static List<String> getTop3Words() {
        List<String> toRet = new Vector<String>();
        for( int i=0; i<palindromeWords.size(); i++ )
            if( toRet.size() >=3 )
                break;
            else
                toRet.add( palindromeWords.get(i) );
        return toRet;
    }

    public static Vector<String> getPalindromeWords() {
        return palindromeWords;
    }

    private static Vector<String> palindromeWords = new Vector<String>();

    static class SortWords implements Comparator {

        @Override
        public int compare(Object o1, Object o2) {

            String str1 = (String) o1;
            String str2 = (String) o2;

            if( str1.length() > str2.length() ) {
                if( str1.compareTo(str2) <0 )
                    return -3;
                else if( str1.compareTo(str2) >0 )
                    return -2;
                else
                    return -1;
            }
            else if( str1.length() < str2.length() ) {
                if( str1.compareTo(str2) <0 )
                    return 5;
                else if( str1.compareTo(str2) >0 )
                    return 4;
                else
                    return 3;
            } else {
                if( str1.compareTo(str2) <0 )
                    return 2;
                else if( str1.compareTo(str2) >0 )
                    return 1;
                else
                    return 0;
            }
        }
    }
}
