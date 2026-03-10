import javax.swing.*;
import java.awt.*;

public class MostrarImagen {
    ImageIcon imageIcon = new ImageIcon("https://oechsle.vteximg.com.br/arquivos/ids/18022678-1000-1000/imageUrl_1.jpg?v=638514346876130000");
      
    JFrame frame = new JFrame("Visor de Imagen");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
    JLabel label = new JLabel(imageIcon);
    frame.getContentPane().add(label, BorderLayout.CENTER);
        
    frame.pack();
    frame.setVisible(true);
}
