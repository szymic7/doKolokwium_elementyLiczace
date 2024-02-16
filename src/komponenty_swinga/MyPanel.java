package komponenty_swinga;

import figury.Circle;
import figury.Shape;
import figury.Square;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class MyPanel extends JPanel {

    private Shape[] shapes = new Shape[15];

    public MyPanel() {
        initialize();
    }

    public void initialize() {
        this.setSize(new Dimension(800, 600));
        this.setBackground(new Color(229, 221, 195));
        this.setBorder(new LineBorder(Color.BLACK, 3, true));
        this.setLayout(null);
        this.setFocusable(false);
    }

    public Shape[] getShapes() {
        return shapes;
    }

    public void addShape(int i, Shape s) {
        shapes[i] = s;
    }

    public void deleteShape(Shape s) {

        for(int i = 0; i < shapes.length; i++) {

            if(shapes[i] != null) {

                if (shapes[i].equals(s)) {
                    shapes[i] = null;
                    break;
                }

            }

        }

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        try {
            for(Shape s: shapes) {
                if(s != null) {

                    if(!s.isDone()) g2d.setColor(Color.RED);
                    else g2d.setColor(Color.GREEN);

                    if(s instanceof Circle) {

                        g2d.fillOval(s.getX(), s.getY(), s.getD(), s.getD());

                    } else if(s instanceof Square) {

                        g2d.fillRect(s.getX(), s.getY(), s.getD(), s.getD());

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
