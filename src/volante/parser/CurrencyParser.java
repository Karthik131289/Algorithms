package volante.parser;

import volante.core.Country;
import volante.core.Currency;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Created by DV21 on 21-08-2015.
 */
public class CurrencyParser {
    public ArrayList<Currency> getCurrencyList() {
        return new ArrayList<Currency>(currencyList);
    }

    public Currency getCurrency( int index ) {
        return this.currencyList.get( index );
    }

    public Currency getCurrencyByCode( String currencyCode ) {
        Currency cncy = null;
        for ( Currency currency : this.currencyList )
            if( currency.getCurrencyCode().equals( currencyCode ) )
                cncy = currency;
        return cncy;
    }

    public Currency getCurrencyByName( String currencyName ) {
        Currency cncy = null;
        for ( Currency currency : this.currencyList )
            if( currency.getCurrencyName().equals( currencyName ) )
                cncy = currency;
        return cncy;
    }

    public void addCurrency( Currency currency ) {
        this.currencyList.add( currency );
    }

    public void addCurrency( int index , Currency currency ) {
        this.currencyList.add( index , currency );
    }

    public void parseCurrencyFile() throws Exception {
        FileReader fr = null;
        BufferedReader reader = null;
        try {
            fr = new FileReader( new File( this.getClass().getResource( FILE_NAME ).getPath() ) );
            reader = new BufferedReader( fr );
            String line = "";
            boolean isFirstLine = true;
            while ( (line = reader.readLine()) != null ) {
                if( isFirstLine )
                    isFirstLine = false;
                else
                    this.addCurrency( getAsCurrency(line) );
            }
        } finally {
            if( fr != null )
                fr.close();
            if( reader != null )
                reader.close();
        }
    }

    private Currency getAsCurrency( String data ) throws Exception {
        Currency currency = null;
        String[] splitStr = data.split( "\t" );

        if( splitStr.length == 4 )
        {

            currency = new Currency();
            currency.setCurrencyCode( splitStr[0] );
            currency.setCurrencyName( splitStr[1] );
            currency.setFractionalDigit( Short.parseShort(splitStr[2]) );
            currency.setCountryCode(splitStr[3]);

            return currency;
        } else {
            //throw new Exception("Invalid Country Code Entry... " + data);
        }

        return currency;
    }

    public static void main(String[] args) throws Exception {
        CurrencyParser parser = new CurrencyParser();
        parser.parseCurrencyFile();
        System.out.println( "Size : " + currencyList.size() );
    }
    private static ArrayList<Currency> currencyList = new ArrayList<Currency>();
    public static final String FILE_NAME = "CurrencyCode.txt";
}
