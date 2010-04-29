import java.io.*;
import java.awt.*;
import javax.swing.*;

/**
 * @author Dennis Heidsiek
 */
public class ImageToPdfGui {
  
  public static void main(String... arg) throws Exception {
    Font font = new Font("Dialog", Font.PLAIN, 24);
    
    JLabel left = new JLabel("<html><h1>Create single PDF files</h1>Just drag and drop<br/>your files at this text!</html>");
    left.setFont(font);
    new FileDrop( System.out, left, /*dragBorder,*/ new FileDrop.Listener(){
        public void filesDropped(File[] files) {
          for(File f : files) {
            try {
              System.out.format("%s%n", f.getCanonicalPath());
            } catch(IOException e) {}
          }
        }
    });
    JLabel right = new JLabel("<html><h1>Create only one PDF file</h1>Just drag and drop<br/>your files at this text!</html>");
    right.setFont(font);
    new FileDrop( System.out, right, /*dragBorder,*/ new FileDrop.Listener(){
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
    frame.setLayout(new GridLayout(1, 2));
    frame.add(left);
    frame.add(right);
    frame.pack();
    frame.setVisible(true);
  }
  
}