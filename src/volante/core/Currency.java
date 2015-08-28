package volante.core;

/**
 * Created by DV21 on 21-08-2015.
 */
public class Currency {
    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public Short getFractionalDigit() {
        return fractionalDigit;
    }

    public void setFractionalDigit(Short fractionalDigit) {
        this.fractionalDigit = fractionalDigit;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    private String currencyCode;
    private String currencyName;
    private Short  fractionalDigit;
    private String countryCode;
}
