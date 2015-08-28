package volante.wildcard;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.regex.Pattern;

public class WildCardMatcher {

	public static void main(String[] args) throws Exception {
		
		if( assertInput( args ) )
			new WildCardMatcher( args[0] );
		else
			System.out.println( "Invalid Input." );
	}

	public WildCardMatcher( String wildCard ) throws Exception {
		Dictionary dic = new Dictionary();
		Vector<String> dicWords = dic.getWords();						// Get Word list from dictionary.
		
		List<String> res = getMatchingWords( wildCard , dicWords );		// Get Matching words based on given pattern
		
		System.out.println( "WildCard : " + wildCard );
		System.out.print( "Matching Words : " );
		
		Iterator<String> it = res.iterator();
		while ( it.hasNext() ) 											// iterate and print matched words
			System.out.print( it.next() + (it.hasNext() ? " , " : "." ) );
	}
	
	private Vector<String> getMatchingWords( String wildCard , List<String> dicWords ) throws Exception {
		Vector<String> matchedWords = new Vector<String>();
		
		String regex = wildCard.replace( "?", "." );					// prepare regex for given pattern
		
		Iterator<String> it = dicWords.iterator();
		while (it.hasNext()) 											// Iterate dic words
		{
			String word = it.next();
			boolean res = Pattern.matches( regex , word );				
			if( res )													// Check whether each word matches given pattern
				matchedWords.add( word );
		}
		
		return matchedWords;
	}

	private static boolean assertInput( String[] args )
	{
		boolean toRet = true;
		
		if( args.length != 1 )
			toRet = false;
		else
		{
			if( args[0].length() < 2 )
				toRet = false;
		}
		
		return toRet;
	}
}
