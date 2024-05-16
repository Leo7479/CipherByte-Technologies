// Here is the import section of the modules needed for the project
import java.util.Random;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class MyApp extends JFrame implements ActionListener, KeyListener{
    JLabel roundNo, text, result;
    JTextField inputText;
    JButton next,check;
    final Random rand = new Random();
    int actualNumber, guessNumber, numberOfAttempts=0, maxAttempts=5, score=0,round = 1;
    int upperLimit = 100;
    Reception reception=null;
    MyApp(Reception r){
        reception = r;
        // Insert the Image on the left
        JLabel l = new JLabel();
        ImageIcon photo =new ImageIcon("./images/picture.png");
        l.setIcon(photo);
        l.setBackground(Color.black);
        l.setBounds(10,35,242,242);
        add(l);

        // Add a seperator line in the middle
        JSeparator sep = new JSeparator();
        sep.setOrientation(JSeparator.VERTICAL);
        sep.setBounds(300,15,10,325);
        add(sep);

        // To know all the fonts available
        /*
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontNames = ge.getAvailableFontFamilyNames();
        for(String s: fontNames)
            System.out.println(s);
         */
        // Set the Round No
        roundNo = new JLabel("Round No : 1/3");
        roundNo.setBounds(320, 15, 350, 37);
        roundNo.setFont(new Font("Unispace",Font.BOLD,40));
        roundNo.setForeground(Color.BLUE);
        add(roundNo);
        actualNumber = nextRandom();
        // Set the Text
        text = new JLabel("Enter your guess: ");
        text.setFont(new Font("Unispace",Font.BOLD,30));
        text.setBounds(320, 60, 400, 80);
        add(text);

        // Set the TextField
        inputText = new JTextField();
        inputText.grabFocus();
        inputText.setBounds(320, 130, 300, 80);
        inputText.setFont(new Font("Unispace",Font.BOLD,20));
        inputText.addKeyListener(this);
        add(inputText);

        // Set the Buttons
        check = new JButton("Check");
        check.setBounds(320, 220, 100, 40);
        check.setBackground(Color.BLUE);
        check.setForeground(Color.WHITE);
        check.setFont(new Font("Unispace",Font.BOLD,20));
        check.addActionListener(this);
        add(check);
        next = new JButton("Proceed");
        next.setBounds(430, 220, 150, 40);
        next.setBackground(new Color(42, 171, 22));
        next.setForeground(Color.WHITE);
        next.setFont(new Font("Unispace",Font.BOLD,20));
        next.addActionListener(this);
        add(next);
        next.setEnabled(false);

        // Set the Result:
        result = new JLabel("Attempts Left: "+maxAttempts);
        result.setFont(new Font("Unispace",Font.BOLD,27));
        result.setForeground(Color.MAGENTA);
        result.setBounds(320, 280, 500, 80);
        add(result);

        setLayout(null);
        setLocation(400,200);
        getContentPane().setBackground(Color.white);
        setSize(800,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public int nextRandom(){
        float f = rand.nextFloat();
        int a = (int)(f * upperLimit);
        return a;
    }
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == next){
            if(round == 3){
                setVisible(false);
                reception.score=score;
                reception.setVisible(true);
            }
            round++;
            roundNo.setText("Round No: "+round+"/3");
            actualNumber = nextRandom();
            inputText.setText("");
            inputText.grabFocus();
            next.setEnabled(false);
            check.setEnabled(true);
            numberOfAttempts=0;
        }else if(ae.getSource() == check){
            try {
                guessNumber = Integer.parseInt(inputText.getText());
            }catch(Exception e){
                result.setText("Please give a valid number");
                inputText.setText("");
                inputText.grabFocus();
                return;
            }
            numberOfAttempts++;
            inputText.setText("");
            String str="";
            if(guessNumber == actualNumber){
                check.setEnabled(false);
                next.setEnabled(true);
                str="You got it right!!!";
                score += (maxAttempts - numberOfAttempts) * 10;
                result.setText(str);
                return;
            }else{
                if(guessNumber > actualNumber)
                    str="Lesser";
                else
                    str="Greater";
            }
            str+=". Attempts Left: "+(maxAttempts - numberOfAttempts);
            System.out.println(actualNumber);
            if(numberOfAttempts == maxAttempts){
                result.setText("No more Attempts left...");
                setVisible(false);
                reception.score=score;
                reception.setVisible(true);
            }else {
                result.setText(str);
                inputText.grabFocus();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {   }
    @Override
    public void keyPressed(KeyEvent e) {
        // System.out.println(e.getKeyCode());
        if(e.getKeyCode() == 10){
            if(check.isEnabled())
                check.doClick();
            else if(next.isEnabled())
                next.doClick();
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {   }
}
