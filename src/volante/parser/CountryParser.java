package volante.parser;

import volante.core.Country;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Created by DV21 on 21-08-2015.
 */
public class CountryParser {

    public ArrayList<Country> getCountryList() {
        return new ArrayList<Country>(countryList);
    }

    public Country getCountry( int index ) {
        return this.countryList.get( index );
    }

    public Country getCountryByName( String countryName ) {
        Country country = null;
        for ( Country cntry : this.countryList ) {
            if( cntry.getCountryName().equals( countryName ) )
                country = cntry;
        }
        return country;
    }

    public Country getCountryByCode( String countryCode ) {
        Country country = null;
        for ( Country cntry : this.countryList ) {
            if( cntry.getCountryCode().equals( countryCode ) )
                country = cntry;
        }
        return country;
    }

    public void addCountry( Country country ) {
        this.countryList.add( country );
    }

    public void addCountry( int index , Country country ) {
        this.countryList.add( index , country );
    }

    public void parseCountryFile() throws Exception {
        FileReader fr = null;
        BufferedReader reader = null;
        try {
            fr = new FileReader( new File( this.getClass().getResource( FILE_NAME ).getPath() ) );
            reader = new BufferedReader( fr );
            String line = "";
            while ( (line = reader.readLine()) != null ) {
                this.addCountry( getAsCountry( line ) );
            }
        } finally {
            if( fr != null )
                fr.close();
            if( reader != null )
                reader.close();
        }
    }

    private Country getAsCountry( String data ) throws Exception {
        Country country = null;
        String[] splitStr = data.split( "\t" );

        if( splitStr.length != 2 )
            throw new Exception( "Invalid Country Code Entry... Entry : " + data  );

        country = new Country();
        country.setCountryCode( splitStr[0] );
        country.setCountryName( splitStr[1] );

        return country;
    }

    public static void main(String[] args) throws Exception {
        CountryParser parser = new CountryParser();
        parser.parseCountryFile();
        System.out.println( "Size : " + countryList.size() );
    }

    private static ArrayList<Country> countryList = new ArrayList<Country>();
    public static final String FILE_NAME = "CountryCode.txt";
}
