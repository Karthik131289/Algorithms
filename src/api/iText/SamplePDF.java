package api.iText;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by DV21 on 15-09-2015.
 */
public class SamplePDF {

    public static void main(String[] args) throws FileNotFoundException, DocumentException {
        initDoc();
        addDocProperties( "Author-iText" , "Creator-iText" , "Title-iText" , "Subject-iText" );
        addParagraph( "Paragraph Test...." );
        closeDoc();
    }

    private static void initDoc() throws FileNotFoundException, DocumentException {
        document = new Document();
        writer = PdfWriter.getInstance( document , new FileOutputStream( FILE_PATH ) );
        document.open();
    }

    private static void closeDoc() {
        document.close();
        writer.close();
    }

    private static void addDocProperties(String author, String creator, String title, String subject) {
        document.addAuthor( author );
        document.addCreationDate();
        document.addCreator( creator );
        document.addTitle( title );
        document.addSubject( subject );
    }

    private static void addParagraph( String content ) throws DocumentException {
        document.add(new Paragraph(content));
    }

    private static void addParagraph( float leadingSpace , String content ) throws DocumentException {
        document.add( new Paragraph( leadingSpace , content ) );
    }

    private static void addParagraph( float leadingSpace , String content , Font font) throws DocumentException {
        document.add( new Paragraph( leadingSpace , content , font ) );
    }

    private static Document document;
    private static PdfWriter writer;
    public static final String FILE_PATH = "E:/samplePDF.pdf";
}
