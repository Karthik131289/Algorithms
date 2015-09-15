package api.jaxb.simple;

import api.jaxb.Book;
import api.jaxb.BookStore;

import javax.xml.bind.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * Created by DV21 on 14-09-2015.
 */
public class BookExample {
    public static void main(String[] args) throws JAXBException {
        if ( assertInputs( args ) ) {
            initContext();
            initJAXBMarshaller();
            initJAXBUnMarshaller();

            if( args[0].equalsIgnoreCase("-init") )
                initBookStore();
            else if( args[0].equalsIgnoreCase("-write") ) {
                initBookStore();
                marshaller.setProperty("jaxb.formatted.output", true);
                marshaller.marshal(store, new File(FILE_PATH));
            }
            else if( args[0].equalsIgnoreCase("-read") ) {
                store = (BookStore) unmarshaller.unmarshal(new File(FILE_PATH));
                printBookStore();
            }
            else if( args[0].equalsIgnoreCase("-readAdv") ) {
                JAXBElement<BookStore> jaxbElm = (JAXBElement<BookStore>) unmarshaller.unmarshal( new File(FILE_PATH));
                store = jaxbElm.getValue();
                printBookStore();
            }
        }
    }

    private static void initContext() throws JAXBException {
        context = JAXBContext.newInstance(BookStore.class);
    }

    private static void initJAXBMarshaller() throws JAXBException {
        marshaller = context.createMarshaller();
    }

    private static void initJAXBUnMarshaller() throws JAXBException {
        unmarshaller = context.createUnmarshaller();
    }

    private static void initBookStore() {

            store = new BookStore();
            store.setId(50000);
            store.setName("Store-1");

            Book book;
            List<Book> bookList = new ArrayList<Book>();

            book = new Book();
            book.setId(1000);
            book.setName("Book-1");
            book.setPrice(1000.00f);
            bookList.add(book);

            book = new Book();
            book.setId(2000);
            book.setName("Book-2");
            book.setPrice(2000.00f);
            bookList.add(book);

            store.setBookList(bookList);
    }

    private static void printBookStore() {
        System.out.print("<store ");
        System.out.print("id=" + store.getId() + " >\n");
        System.out.println("\t<name>"+store.getName()+"</name>");
        if( !store.getBookList().isEmpty() || store.getBookList()!=null ) {
            System.out.println("\t<bookList>");
            List<Book> bookList = store.getBookList();
            Iterator<Book> it = bookList.iterator();
            while ( it.hasNext() )
            {
                Book book = it.next();
                System.out.print("\t\t<book ");
                System.out.print("id=" + book.getId() + " >\n");
                System.out.println("\t\t\t<name>" + book.getName() + "</name>");
                System.out.println("\t\t\t<price>" + book.getPrice() + "</price>");
                System.out.println("\t\t</book>");
            }

            System.out.println("\t</bookList>");
        }
        System.out.println("</store>");
    }

    private static boolean assertInputs( String[] args ) {
        if( args == null )
            return false;
        if( args.length != 1 )
            return false;
        if( !args[0].startsWith("-") )
            return false;

        System.out.println( "input : " + args[0] );
        String regex = "-init|-write|-read|-readAdv";
        if( !args[0].matches(regex) )
            return false;

        return true;
    }

    private static JAXBContext context;
    private static Marshaller marshaller;
    private static Unmarshaller unmarshaller;
    private static BookStore store;
    public static final String FILE_PATH = "E:/JAXB-Example.xml";
}
