import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Login extends JFrame implements ActionListener, KeyListener{
    JButton loginBtn, signupBtn;
    JTextField userName;
    JPasswordField password;
    DatabaseTools db;
    JLabel error;
    Login(){
        TitleBar titleBar = new TitleBar();
        titleBar.setBounds(0,0,700, 35);
        add(titleBar);
        // Image on one side
        JLabel imgHolder = new JLabel();
        ImageIcon image = new ImageIcon("./images/bank_logo.png");
        imgHolder.setIcon(image);
        imgHolder.setBounds(0, 35, 300, 380);
        add(imgHolder);

        // User Details
        JLabel temp = new JLabel("Login / Sign Up");
        temp.setBounds(380, 50, 300, 40);
        temp.setFont(new Font("Unispace",Font.BOLD, 30));
        temp.setForeground(Color.blue);
        add(temp);

        // User Login Details
        JLabel userIcon = new JLabel();
        userIcon.setIcon(new ImageIcon("./images/username.png"));
        userIcon.setBounds(350, 120, 40, 40);
        add(userIcon);

        userName = new JTextField();
        userName.setBounds(400, 120, 250, 40);
        userName.setFont(new Font("Unispace",Font.BOLD,20));
        userName.addKeyListener(this);
        add(userName);

        JLabel passwordIcon = new JLabel();
        passwordIcon.setIcon(new ImageIcon("./images/password.png"));
        passwordIcon.setBounds(350, 170, 40, 40);
        add(passwordIcon);

        password = new JPasswordField();
        password.setBounds(400, 170, 250, 40);
        password.setFont(new Font("Unispace",Font.BOLD,20));
        password.addKeyListener(this);
        add(password);

        // Buttons:
        loginBtn = new JButton("Login",new ImageIcon("./images/loginBtn.png"));
        loginBtn.setBackground(Color.GREEN);
        loginBtn.setBounds(370, 250, 280, 35);
        add(loginBtn);
        loginBtn.addActionListener(this);

        // Sign Up:
        JLabel signup = new JLabel("Create an account ");
        signupBtn = new JButton("Create an Account");
        signup.setBounds(370, 295, 110, 40);
        signupBtn.setBounds(490, 295, 150, 40);
        add(signup);
        add(signupBtn);
        signupBtn.addActionListener(this);

        // Error Message
        error = new JLabel();
        error.setForeground(Color.red);
        error.setBounds(410, 350, 250, 40);
        add(error);

        // Frame configurations:
        setUndecorated(true);
        setLayout(null);
        setSize(700, 415);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.white);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setIconImage(new ImageIcon("./images/login.png").getImage());
        userName.grabFocus();
        // Connecting with the DB
        db = new DatabaseTools();
    }
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == loginBtn){
            String  userNameVal= "";
            String passwordString= "";
            char[] passwordChars;
            boolean loginSuccess=false;
            try{
                userNameVal = this.userName.getText();
                passwordChars = this.password.getPassword();
                for (char passwordChar : passwordChars) passwordString += passwordChar;
                loginSuccess = db.checkCredentials(userNameVal, passwordString);
                System.out.println(loginSuccess);
                error.setFont(new Font("Unispace", Font.BOLD, 15));
                if(!loginSuccess){
                    error.setText("");
                    error.setText("Credentials mismatch.");
                    error.setForeground(Color.red);
                }
            }
            catch(UserNotFoundException e){
                error.setFont(new Font("Unispace", Font.BOLD, 20));
                error.setText("User Not Found");
                error.setForeground(Color.red);
            }
            catch(Exception e){
            }
            finally {
                password.setText("");
            }
            if(loginSuccess){
                new Account(db, userNameVal);
                this.setVisible(false);
            }
        } else if (ae.getSource() == signupBtn) {
            new OpenAccount(this).setVisible(true);
            setVisible(false);
        }
    }
    public static void main(String[] args) {
        new Login();
    }

    @Override
    public void keyTyped(KeyEvent e) {    }
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == 10){
            loginBtn.doClick();
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {    }
}
