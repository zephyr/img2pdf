import java.io.*;
import java.util.Arrays;
import java.util.regex.Pattern;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

/**
 * @author Dennis Heidsiek
 */
public class ImageToPdfCli {
  
  /*
   * Returns all images in the current directory whose filenames
   * match the given regular expression (and are not ending with {@code .pdf}).
   */
  protected static File[] matchingFiles(String pattern) throws Exception {
    final Pattern p = Pattern.compile(pattern);
    return (new File(".")).listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
              return p.matcher(name).matches() && (name.toLowerCase().endsWith(".pdf")==false);
            }
          });
  }
  
  public static void main(String... arg) throws Exception {
    switch(arg.length) {
      case 0:
        System.out.format(
            "img2pdf is written by Dennis Heidsiek (in 2010) and available under the%n" +
            "    GNU Affero General Public License version 3 (or any later version):%n" +
            "    http://www.gnu.org/licenses/agpl-3.0.html%n" +
            "%n" +
            "You can get the source code at GitHub:%n" +
            "    ?http://github.com/zephyr/img2pdf%n" +
            "%n" +
            "Usage Examples (for producing a single pdf file -- if you want to produce%n" +
            "    several pdf files (one per image) instead, just drop the output.pdf)%n" +
            "%n" +
            "    img2pdf image.jp2%n" +
            "or: img2pdf image.jp2 output.pdf%n" +
            "or: img2pdf image1.jp2 image2.jpg image3.png [...] output.pdf%n" +
            "or: img2pdf -r regular-expression-for-the-desired-filenames output.pdf%n" +
            "as: img2pdf -r ^image.* output.pdf (all filenames who start with \"image\")%n" +
            "as: img2pdf -r .*\\.jpg$ output.pdf (all filenames who end with \".jpg\")%n" +
            "as: img2pdf -r \"image \\d+\\.(jpg|jp2)\" output.pdf%n" + 
            "        (matches \"image 1.jpg\", ..., \"image 42.jp2\", ...)%n"
            );
        break;
      case 2:
        if(arg[0].equals("-r")) {
          File[] files = matchingFiles(arg[1]);
          for(File f : files) {
            ImageToPdf.wrap(f);
          }
          break;
        }
        // fall through!
      case 3:
        if(arg[0].equals("-r")) {
          File[] files = matchingFiles(arg[1]);
          ImageToPdf.wrap(new File(arg[2]), files);
          break;
        }
        // fall through!
      default:
        if(arg[arg.length-1].toLowerCase().endsWith(".pdf")) {
          String[] images = new String[arg.length-1];
          for(int i=0; i<images.length; i++) {
            images[i] = arg[i];
          }
          ImageToPdf.wrap(arg[arg.length-1], images);
        } else {
          for(String f : arg) {
            ImageToPdf.wrap(f);
          }
        }
        break;
    }
  }
  
}