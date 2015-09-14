package volante.spellcheck;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpellCheck {

	public static final String DATA_FILE_NAME = "data.txt";
	public static final String COMMA = ""+',';
	public static final String DOUBLE_QOUTE = ""+'"';
	public static final String[] REMOVABLE_CHARS = { COMMA , "(" , ")" , ":" , ";" , DOUBLE_QOUTE };
	public static final String[] SPLIT_CHARS = { "-" , "." };

	
	public static void main(String[] args) throws Exception {
		new SpellCheck();
	}

	public SpellCheck() throws Exception {
		readDataFile();
		findWorngWords();
	}
	
	private void findWorngWords() throws Exception {
		Dictionary dic = new Dictionary();
		Vector<String> dicWords = dic.getWords();
		
		for( int i=0; i<allWords.size(); i++ ) {
			Vector<String> line = allWords.get(i);
			for( String str : line )
			{
				if( !dicWords.contains( str.toLowerCase() ) )
					printWord( str , i+1 );
			}
		}
	}
	
	private void readDataFile() throws IOException, URISyntaxException {
		File dataFile = new File( this.getClass().getResource( DATA_FILE_NAME ).toURI() );
		FileReader fileReader = new FileReader( dataFile );
		BufferedReader reader = new BufferedReader( fileReader );
		
		String line = "";
		while( ( line = reader.readLine() ) != null ) {
			allWords.add ( getWords(line) );
		}
		
		fileReader.close();
		reader.close();
	}
	
	private Vector<String> getWords( String lineData ) {
		Vector<String> toRet = new Vector<String>();
		
		String[] data = lineData.split( " " );
		for( String temp : data ) {
			temp = temp.trim();
			if( temp.length()>1 ) { 
				temp = removeSpecialChars( temp );
				if( isContainSpecialChar( temp ) )
				{
					String[] splitData = splitBySpecialChars( temp );
					for( String str : splitData ) {
						if( str.trim().length() > 1 )
							toRet.add( str.trim() );
					}
				}
				else 
					toRet.add( temp.trim() );
			}
		}
		return toRet;
	}
	
	private String removeSpecialChars( String data ) {
		for( String sChar : REMOVABLE_CHARS ) {
			if( data.contains( sChar ) )
				data = data.replace( sChar , "" );
		}

		return data;
	}
	
	private String[] splitBySpecialChars( String data ) {
		String[] toRet = null;
		for( String sChar : SPLIT_CHARS ) {
			if( data.contains(sChar) ) {
				toRet = data.split( sChar );
				break;
			}
		}
		return toRet;
	}
	
	private boolean isContainSpecialChar( String data ) {
		boolean toRet = false;
		for( String sChar : SPLIT_CHARS ) 
			if( data.contains(sChar) )
			{
				toRet = true;
				break;
			}
		return toRet;
	}
	
	private void printWord( String word , int lineNo ) {
		System.out.println( "Line " + lineNo + " : " + word );
	}
	
	private Vector<Vector<String>> allWords = new Vector<Vector<String>>();
}
