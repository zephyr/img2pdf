package dh.img2pdf;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
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
public class Lib {
  
  /** @see #wrap(File) */
  public static void wrap(String imageName) throws Exception {
    wrap(new File(imageName));
  }
  
  /** @see #wrap(File, File[]) */
  public static void wrap(String pdfName, String... imageNames) throws Exception {
    File[] imageFiles = new File[imageNames.length];
    for(int i=0; i<imageNames.length; i++) {
      imageFiles[i] = new File(imageNames[i]);
    }
    wrap(new File(pdfName), imageFiles);
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
  public static void wrap(File pdfFile, File... imageFiles) throws Exception {
    if(imageFiles.length>1) {
      // Sort the files alphabetical by name
      Arrays.sort(imageFiles, new Comparator<File>() {
        public int compare(File a, File b) {
          if (a==b) return 0;
          if(a.isDirectory() && b.isFile()) return -1;
          if(a.isFile() && b.isDirectory()) return +1;
          return a.getName().compareTo(b.getName());
        }
      });
    }
    
    System.out.format("Converting %s -> %s%n",
      (imageFiles.length==1) ? imageFiles[0] : Arrays.toString(imageFiles), pdfFile);
    
    Document doc = new Document();
    PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(pdfFile));
    
    doc.open();
    for(File image : imageFiles) {
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
  
  /* Will execute ether the gui or cli interface, depending of the environment. */
  public static void main(String... arg) throws Exception {
    if(System.console() == null) {
      Gui.main(arg);
    } else {
      Cli.main(arg);
    }
  }
  
}
