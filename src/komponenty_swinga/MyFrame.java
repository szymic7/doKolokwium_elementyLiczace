package komponenty_swinga;

import figury.Circle;
import figury.Shape;
import figury.Square;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class MyFrame extends JFrame implements ActionListener, MouseListener, MouseMotionListener {

    // Komponenty JFrame'a
    private JPanel panel;
    private MyPanel myPanel;
    private JLabel komunikatyLabel;
    private JTextField komunikaty;
    private ButtonGroup group;
    private JRadioButton kolo, kwadrat;

    // Zmienne pomocnicze
    private int wybranaFigura = 0;
    private boolean wykrytoFigure;
    public static final Font font = new Font("Arial", Font.PLAIN, 16);

    public MyFrame() {
        initialize();
    }

    public void initialize() {

        // Frame
        this.setTitle("Tytuł jakiś");
        this.setSize(1000, 800);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // panel
        panel = new JPanel();
        panel.setLayout(null);
        panel.setFocusable(false);
        panel.setBounds(0, 0, this.getWidth(), this.getHeight());
        this.add(panel);

        // MyPanel - płótno
        myPanel = new MyPanel();
        myPanel.setBounds(20, 20, myPanel.getWidth(), myPanel.getHeight());
        myPanel.addMouseListener(this);
        myPanel.addMouseMotionListener(this);
        panel.add(myPanel);

        // komunikatyLabel
        komunikatyLabel = new JLabel("Komunikaty:");
        komunikatyLabel.setBounds(40, 640, 100, 40);
        komunikatyLabel.setFont(font);
        panel.add(komunikatyLabel);

        // komunikaty - TextField
        komunikaty = new JTextField();
        komunikaty.setBounds(40, 680, 600, 60);
        komunikaty.setEditable(false);
        komunikaty.setFocusable(false);
        komunikaty.setBorder(new LineBorder(Color.BLACK, 3, true));
        komunikaty.setFont(font);
        panel.add(komunikaty);

        // ButtonGroup
        group = new ButtonGroup();

        // kolo - JRadioButton
        kolo = new JRadioButton("Koło");
        kolo.setFont(font);
        kolo.setBounds(840, 40, 130, 30);
        kolo.addActionListener(this);
        group.add(kolo);
        panel.add(kolo);

        // kwadrat - JRadioButton
        kwadrat = new JRadioButton("Kwadrat");
        kwadrat.setFont(font);
        kwadrat.setBounds(840, 80, 130, 30);
        kwadrat.addActionListener(this);
        group.add(kwadrat);
        panel.add(kwadrat);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MyFrame().setVisible(true);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == kolo) {
            wybranaFigura = 1;
        } else if(e.getSource() == kwadrat) {
            wybranaFigura = 2;
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

        int x = e.getX();
        int y = e.getY();

        // Kliknięcie w celu usunięcia figury
        for(Shape s: myPanel.getShapes()) {
            if(s != null) {
                if ((x >= s.getX() && x <= s.getX() + s.getD()) && (y >= s.getY() && y <= s.getY() + s.getD())) {
                    if(s.isDone()) {
                        myPanel.deleteShape(s);
                        myPanel.repaint();
                        wykrytoFigure = true;
                        break;
                    } else {
                        wykrytoFigure = true;
                        break;
                    }
                }
            }
        }

        // Kliknięcie w celu narysowania nowej figury - gdy kursor nie jest na żadnej istniejącej figurze
        if(!wykrytoFigure) {

            if (wybranaFigura == 1) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        Circle circle = new Circle(x - 30, y - 30);
                        for (int i = 0; i < myPanel.getShapes().length; i++) {
                            if (myPanel.getShapes()[i] == null) {
                                myPanel.addShape(i, circle);
                                myPanel.repaint();
                                break;
                            }
                        }

                        try {
                            Thread.sleep(5000); // koło - 5 sekund
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        circle.setDone();
                        myPanel.repaint();

                    }

                }).start();

            } else if (wybranaFigura == 2) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        Square square = new Square(x - 30, y - 30);
                        for (int i = 0; i < myPanel.getShapes().length; i++) {
                            if (myPanel.getShapes()[i] == null) {
                                myPanel.addShape(i, square);
                                myPanel.repaint();
                                break;
                            }
                        }

                        try {
                            Thread.sleep(7000); // kwadrat - 7 sekund
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        square.setDone();
                        myPanel.repaint();
                    }

                }).start();

            } else {
                komunikaty.setText("Figura nie została wybrana");
            }

        }

        wykrytoFigure = false;

    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {

        int x = e.getX();
        int y = e.getY();

        for(Shape s: myPanel.getShapes()) {
            if(s != null) {
                if ((x >= s.getX() && x <= s.getX() + s.getD()) && (y >= s.getY() && y <= s.getY() + s.getD())) {
                    if(s.isDone()) {
                        komunikaty.setText("Zakończono obliczenia.");
                    } else {
                        komunikaty.setText("Obliczenia nadal trwają.");
                    }
                }
            }
        }
    }
}
