package volante.wildcard;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Vector;

public class Dictionary {
	
	public static final String DIC_FILE_NAME = "dictionary.txt";
	
	public Dictionary() throws Exception {
		readDictionary();
	}
	
	private void readDictionary() throws Exception {
		File dictionary = new File( this.getClass().getResource( DIC_FILE_NAME ).toURI() );
		FileReader fileReader = new FileReader( dictionary );
		BufferedReader reader = new BufferedReader( fileReader );
		String data = "";
		while( (data=reader.readLine()) != null ) {
			words.add( data.trim() );
		}
		fileReader.close();
		reader.close();
	}
	
	public Vector<String> getWords() {
		return new Vector<String>( words );
	}
	
	private Vector<String> words = new Vector<String>();
}
