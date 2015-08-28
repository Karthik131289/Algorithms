package volante.soundex;

import java.util.ArrayList;
import java.util.Vector;

public class Soundex {

	public static void main(String[] args) throws Exception {
		
		if( validateInput( args ) )
			new Soundex( args[0] );
		else
			System.out.println( "Invalid input.Please check your input" );
	}

	public Soundex( String inputStr ) throws Exception {
		init();
		
		String inputSoundex = getSoundex( inputStr );
		
		ArrayList<String> matchedWords = getMatchedWords( inputSoundex );
		if( matchedWords.contains( inputStr.toLowerCase() ) )
			matchedWords.remove( inputStr.toLowerCase() );
		
		System.out.println( inputStr + " : " + inputSoundex );
		System.out.print( "Similar Words are :" );
		for( String str : matchedWords ) 
			System.out.print( str + " " );
	}
	
	private ArrayList<String> getMatchedWords( String soundexCode ) throws Exception {
		ArrayList<String> toRet = new ArrayList<String>();
		
		Dictionary dic = new Dictionary();
		Vector<String> dicWords = dic.getWords();
		for( String dicWord : dicWords )
			if( getSoundex( dicWord ).equalsIgnoreCase( soundexCode ) )
				toRet.add( dicWord );
		
		return toRet;
	}
	
	private String getSoundex( String data ) {
		String toRet = "";
		
		StringBuffer temp = new StringBuffer( data.toLowerCase() ) ;
		
		toRet = toRet.concat( data.substring(0,1) );	// Append First letter to toRet
		
		// Ignore First Char
		temp.deleteCharAt( 0 );
		
		// Check and Remove drop chars
		if( isContainDropChars( temp.toString() ) ) {
			removeDropChars( temp );
		}
		
		//System.out.println( "temp : " + temp.toString() );
		
		String grpCode = getGroupCode( temp.toString() );
		//System.out.println( "grpCode : " + grpCode );
		
		String validGrpCode = getValidGroupCode( grpCode , data.toLowerCase() );
		//System.out.println( "Valid grpCode : " + validGrpCode );
		
		toRet = toRet.concat( validGrpCode );
		toRet = toRet.toUpperCase();
		//System.out.println( "toRet : " + toRet );

		toRet = adjustSoundex( toRet );
		//System.out.println( data + " : " + toRet );
		
		return toRet;
	}
	
	private String getValidGroupCode( String data , String actualData ) {
		StringBuffer toRet = new StringBuffer( data );
		
		for( int i=0; i<toRet.length()-1; i++ ) {
			String curr = toRet.substring( i , i+1 );
			String next = toRet.substring( i+1 , i+2 );
			if( curr.equalsIgnoreCase( next ) )
				toRet = toRet.deleteCharAt(i+1);
		}
		
		return toRet.toString();
	}
	
	private String getGroupCode( String data ) {
		String toRet = "";
		
		for( int i=0; i<data.length(); i++ )
		{
			String singleChar = data.substring( i , i+1 );
			int grpCode = 1;
			for( String[] grpChar : consonantGrp )
			{
				for( String str : grpChar ) 
					if( str.equalsIgnoreCase( singleChar ) )
						toRet = toRet.concat( grpCode+"" );
				grpCode++;
			}
		}
		
		return toRet;
	}
	
	private boolean isContainDropChars( String data ) {
		boolean toRet = false;

		for( int i=0; i<data.length(); i++ ) {
			String singleChar = data.substring( i, i+1 );
			if( dropCharList.contains( singleChar ) )
			{
				toRet = true;
				break;
			}
		}	
		
		return toRet;
	}
	
	private void removeDropChars( StringBuffer data ) {
		
		for( int i=0; i<data.length(); i++ ) {
			String singleChar = data.substring( i, i+1 );
			if( dropCharList.contains( singleChar ) )
			{
				data.deleteCharAt( i );
			}
		}	
	}
	
	private String adjustSoundex( String soundex ) {
		
		if( soundex.length() > 4 )
			soundex = soundex.substring( 0 , 4 );
		else if( soundex.length() < 4 ) {
			while( soundex.length() < 4 )
				soundex = soundex.concat("0");
		}
		
		return soundex;
	}
	
	private void init() {
		for( String str : dropChars )
			dropCharList.add( str );
	}
	
	private static boolean validateInput( String[] input ) {
		
		if( input.length > 1 || input.length == 0 )
			return false;
		
		if( input[0] == null || input[0].length()<2 )
			return false;
		
		return true;
	}
		
	private ArrayList<String> dropCharList = new ArrayList<String>();

	private static final String[] dropChars = { "a" , "e" , "i" , "o" , "u" , "y" , "h" , "w" };
	private static final String[] grp1 = { "b" , "f" , "p" , "v" };
	private static final String[] grp2 = { "c" , "g" , "j" , "k" , "q" , "s" , "x" , "z" };
	private static final String[] grp3 = { "d" , "t" };
	private static final String[] grp4 = { "l" };
	private static final String[] grp5 = { "m" , "n" };
	private static final String[] grp6 = { "r" };
	private static final String[][] consonantGrp = { grp1 , grp2 , grp3 , grp4 , grp5 , grp6 };
}
