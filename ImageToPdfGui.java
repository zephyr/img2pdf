import java.io.*;
import java.awt.*;
import javax.swing.*;

/**
 * @author Dennis Heidsiek
 */
public class ImageToPdfGui {
  
  public static void main(String... arg) throws Exception {
    JLabel left = new JLabel("Left!");
    
    new FileDrop( System.out, left, /*dragBorder,*/ new FileDrop.Listener(){
        public void filesDropped(File[] files) {
          for(File f : files) {
            try {
              System.out.format("%s%n", f.getCanonicalPath());
            } catch(IOException e) {}
          }
        }
    });

    
    JFrame frame = new JFrame("img2pdf");
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.setLayout(new GridLayout(2,1));
    frame.add(left);
    frame.add(left);
    
    frame.setVisible(true);
  }
  
}