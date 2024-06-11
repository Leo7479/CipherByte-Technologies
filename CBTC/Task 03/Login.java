import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class Login extends JFrame implements ActionListener{
    JTextField studentId = null;
    JPasswordField password = null;
    JButton loginBtn = null;
    DatabaseTools db = null;
    Login(){
        // Database connections
        db = new DatabaseTools();

        // Title Bar
        TitleBar title = new TitleBar();
        title.setBounds(0, 0, 700, 50);
        add(title);

        // Image
        JLabel temp = new JLabel();
        temp.setIcon(new ImageIcon("./images/student.png"));
        temp.setBounds(237, 60, 225, 225);
        add(temp);


        temp = new JLabel("LCS Exam Portal");
        temp.setBounds(225, 290, 300, 40);
        temp.setFont(new Font("Dialog",Font.BOLD, 30 ));
        temp.setForeground(Color.orange);
        add(temp);

        // Form
        studentId = new JTextField();
        studentId.setFont(new Font("Monospace", Font.BOLD,20));
        studentId.setBackground(Color.white);
        studentId.setBounds(40, 360, 300, 40);
        add(studentId);

        temp = new JLabel();
        temp.setIcon(new ImageIcon("images/student2.png"));
        temp.setBounds(350, 360, 40, 40);
        add(temp);

        password = new JPasswordField();
        password.setFont(new Font("Monospace", Font.BOLD,20));
        password.setBackground(Color.white);
        password.setBounds(40, 430, 300, 40);
        add(password);

        temp = new JLabel();
        temp.setIcon(new ImageIcon("images/password.png"));
        temp.setBounds(350, 430, 40, 40);
        add(temp);

        loginBtn = new JButton("Login",new ImageIcon("./images/login.png"));
        loginBtn.setBounds(450, 395, 200, 60);
        loginBtn.setFont(new Font("Monospace", Font.BOLD,20));
        loginBtn.setForeground(Color.white);
        loginBtn.setBackground(new Color(5,26,45));
        loginBtn.addActionListener(this);
        add(loginBtn);

        // Frame Properties
        setUndecorated(true);
        setSize(700, 500);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(67,139,211));
        setIconImage(new ImageIcon("./images/student.png").getImage());
        setLayout(null);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == loginBtn){
            String username = studentId.getText();
            char[] passkey = password.getPassword();
            String passString = "";
            for(char c: passkey)
                passString += Character.toString(c);
            if(db.studentExists(username) && db.passwordMatches(passString, username)) {
                setVisible(false);
                new Account(this, db.getName(username));
            }
        }
    }
    public static void main(String[] args){
        new Login();
    }

}
