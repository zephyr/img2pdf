package dh.img2pdf;

import java.io.*;
import java.net.URI;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

/**
 * @author Dennis Heidsiek
 */
public class Gui {

  protected static JLabel createLabel(String title, FileDrop.Listener listener) {
    JLabel label = new JLabel(String.format(
        "<html><centering><h1>%s</h1>Just drag and drop<br/>your files at this text!</centering></html>",
        title));
      
    label.setFont(new Font("Dialog", Font.PLAIN, 24));
    new FileDrop(System.out, label, BorderFactory.createRaisedBevelBorder(), listener);
    return label;
  }
  
  public static void main(String... arg) throws Exception {
    JPanel center = new JPanel();
    center.setLayout(new GridLayout(1, 2));
    
    center.add(createLabel("Create several PDF files",
      new FileDrop.Listener() {
        public void filesDropped(File[] files) {
          for(File f : files) {
            try {
              Lib.wrap(f);
            } catch(Exception e) {
              System.err.println(e);
            }
          }
        }
      }
    ));
    
    center.add(createLabel("Create only one PDF file",
      new FileDrop.Listener() {
        public void filesDropped(File[] files) {
          try {
            File out = new File(files[0].getParent(), "output.pdf");
            Lib.wrap(out, files);
          } catch(Exception e) {
            System.err.println(e);
          }
        }
      }
    ));
    
    @SuppressWarnings("serial")
    Action link = new AbstractAction("Source code (GPL3+) at GitHub! – Written By Dennis Heidsiek using iText and FileDrop.") {
        public void actionPerformed(ActionEvent e) {
          try {
            Desktop.getDesktop().browse(new URI("http://github.com/zephyr/img2pdf")); 
          } catch(Exception ex) {}
        }
    };
    
    JFrame frame = new JFrame("img2pdf (supports jp2, jpg, jpc, png, …)");
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.setLayout(new BorderLayout());
    
    frame.add(new JButton(link), BorderLayout.NORTH);
    frame.add(center, BorderLayout.CENTER);
    
    frame.setSize(600,220);
    frame.setVisible(true);
  }
  
}
