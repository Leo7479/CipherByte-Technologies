import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.*;

public class Reception extends JFrame implements ActionListener, WindowListener, KeyListener {
    JButton proceed;
    int score=-1;
    Label text;
    JLabel l;
    Reception(){
        // Label for the image
        l = new JLabel();
        ImageIcon image = new ImageIcon("./images/picture.png");
        l.setIcon(image);
        l.setBounds(10, 35, 300, 300);
        add(l);

        // Add the vertical Separator
        JSeparator sp = new JSeparator();
        sp.setOrientation(JSeparator.VERTICAL);
        sp.setBounds(320, 25, 10, 320);
        add(sp);

        // Label to welcome or to display the final score
        text = new Label("Guess The Number");
        text.setFont(new Font("Unispace",Font.BOLD,20));
        text.setForeground(Color.BLUE);
        text.setBounds(350, 80, 400, 40);
        add(text);

        // Setting the Start Button
        proceed = new JButton("Start Game");
        proceed.addActionListener(this);
        proceed.setBounds(350, 130, 180, 40);
        proceed.setBackground(Color.BLUE);
        proceed.setForeground(Color.WHITE);
        proceed.addKeyListener(this);
        add(proceed);

        setLayout(null);
        setLocation(400,200);
        getContentPane().setBackground(Color.white);
        setSize(600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        addKeyListener(this);
        addWindowListener(this);
        setTitle("Guess The Number");
        setIconImage(image.getImage());
    }
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == proceed){
            setVisible(false);
            MyApp app = new MyApp(this);
        }
    }
    public static void main(String[] args) {
        new Reception();
    }

    @Override
    public void windowOpened(WindowEvent e) {    }
    @Override
    public void windowClosing(WindowEvent e) {    }
    @Override
    public void windowClosed(WindowEvent e) {    }
    @Override
    public void windowIconified(WindowEvent e) {    }
    @Override
    public void windowDeiconified(WindowEvent e) {    }
    @Override
    public void windowActivated(WindowEvent e) {
        if(score != -1){
            l.setIcon(new ImageIcon("./images/congrats.jpeg"));
            text.setText("Your Score: "+score);
            proceed.setText("Restart Game");
        }
    }
    @Override
    public void windowDeactivated(WindowEvent e) {    }
    @Override
    public void keyTyped(KeyEvent e) {   }
    @Override
    public void keyPressed(KeyEvent e) {
        // System.out.println(e.getKeyCode());
        if(e.getKeyCode() == 10){
            proceed.doClick();
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {   }
}
