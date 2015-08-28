package volante.parser;

import volante.core.Currency;

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

    private ArrayList<Currency> currencyList = new ArrayList<Currency>();
    public static final String FILE_NAME = "CurrencyCode.txt";
}
