import javax.swing.*;
import java.awt.*;

public class imagen {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Imagen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        
        // Crear un panel para dibujar la imagen
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Dibujar un rectángulo rojo
                g.setColor(Color.RED);
                g.fillRect(50, 50, 300, 300);
            }
        };
        
        frame.add(panel);
        frame.setVisible(true);
    }
}
