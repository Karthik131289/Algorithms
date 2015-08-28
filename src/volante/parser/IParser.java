package volante.parser;

/**
 * Created by DV21 on 21-08-2015.
 */
public interface IParser {

    public void parse() throws Exception;
    public Object getAsObject( String data , Class cls ) throws Exception;
}
