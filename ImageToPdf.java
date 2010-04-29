import java.io.*;
import java.util.Arrays;
import java.util.regex.Pattern;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

/**
 * This class does the actual work.
 * 
 * @author Dennis Heidsiek
 */
 //wrap("image.jp2");
 //wrap("C:\\User\\John Doe\\Pictures\\MyPicture.jp2");
public class ImageToPdf {
  
  /** @see #wrap(File) */
  public static void wrap(String imageName) throws Exception {
    wrap(new File(imageName));
  }
  
  /** @see #wrap(File, File[]) */
  public static void wrap(String pdfName, String... imageName) throws Exception {
    File[] imageFile = new File[imageName.length];
    for(int i=0; i<imageName.length; i++) {
      imageFile[i] = new File(imageName[i]);
    }
    wrap(new File(pdfName), imageFile);
  }
  
  /**
   * Embeds {@code imagefile.whatever} as {@code imagefile.pdf}.
   * @see #wrap(File, File[])
   */
  public static void wrap(File image) throws Exception {
    String name = image.getName();
    wrap(new File(image.getParent(), name.substring(0, name.lastIndexOf("."))+".pdf"), image);
  }
  
  /*
   * Creates a new pdf file, embedding all given images on their own fitting pages.
   */
  public static void wrap(File pdfFile, File... imageFile) throws Exception {
    System.out.format("Converting %s -> %s%n",
      (imageFile.length==1) ? imageFile[0] : Arrays.toString(imageFile), pdfFile);
    
    Document doc = new Document();
    PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(pdfFile));
    
    doc.open();
    for(File image : imageFile) {
      addEmbedImage(doc, image);
    }
    doc.close();
    
  }
  
  /**
   * Appends an image on a new fitting site to a existing document.
   */
  protected static void addEmbedImage(Document doc, File imageFile) throws Exception {    
    Image image = Image.getInstance (imageFile.toURI().toURL());
    doc.setPageSize(new Rectangle(image.getPlainWidth(), image.getPlainHeight()));
    doc.setMargins(0, 0, 0, 0);
    doc.newPage();
    doc.add(image);
  }
  
}