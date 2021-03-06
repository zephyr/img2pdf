package dh.img2pdf;

import java.io.*;
import java.util.Arrays;
import java.util.regex.Pattern;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

/**
 * @author Dennis Heidsiek
 */
public class Cli {
  
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
  
  @SuppressWarnings("fallthrough") // Dear Compiler, i do know what i do ;)
  public static void main(String... arg) throws Exception {
    switch(arg.length) {
      case 0:
        System.out.format(
            "img2pdf is written by Dennis Heidsiek (in 2010) and available under the%n" +
            "    GNU General Public License version 3 (or any later version):%n" +
            "    http://www.gnu.org/licenses/gpl-3.0.html%n" +
            "%n" +
            "You can get the source code at GitHub:%n" +
            "    http://github.com/zephyr/img2pdf%n" +
            "%n" +
            "If you prefer a simple graphical use interface instead, type%n" +
            "    img2pdf -g%n" +
            "%n" +
            "Command Line Usage Examples (for producing a single pdf file -- if you%n" +
            "    want to produce several pdf files (one per image) instead, just drop%n" +
            "     the final output.pdf)%n" +
            "%n" +
            "    img2pdf image.jp2%n" +
            "or: img2pdf image.jp2 output.pdf%n" +
            "or: img2pdf image1.jp2 image2.jpg image3.png [...] output.pdf%n" +
            "or: img2pdf -r regular-expression-for-the-desired-filenames output.pdf%n" +
            "as: img2pdf -r '^image.*' output.pdf (all filenames who start with \"image\")%n" +
            "as: img2pdf -r '.*\\.jpg$' output.pdf (all filenames who end with \".jpg\")%n" +
            "as: img2pdf -r 'image \\d+\\.(jpg|jp2)' output.pdf%n" + 
            "        (matches 'image 1.jpg', ..., 'image 42.jp2', ...)%n"
            );
        break;
      case 1:
        if(arg[0].equals("-g") || arg[0].equals("--gui")) {
          Gui.main(arg);
          break;
        }
        // fall through!
      case 2:
        if(arg[0].equals("-r")) {
          File[] files = matchingFiles(arg[1]);
          for(File f : files) {
            Lib.wrap(f);
          }
          break;
        }
        // fall through!
      case 3:
        if(arg[0].equals("-r")) {
          File[] files = matchingFiles(arg[1]);
          Lib.wrap(new File(arg[2]), files);
          break;
        }
        // fall through!
      default:
        if(arg[arg.length-1].toLowerCase().endsWith(".pdf")) {
          String[] images = new String[arg.length-1];
          for(int i=0; i<images.length; i++) {
            images[i] = arg[i];
          }
          Lib.wrap(arg[arg.length-1], images);
        } else {
          for(String f : arg) {
            Lib.wrap(f);
          }
        }
        break;
    }
  }
  
}
