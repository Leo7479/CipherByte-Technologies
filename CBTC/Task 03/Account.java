import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class Account extends JFrame implements ActionListener{
    JButton startBtn = null, settingsBtn = null, logoutBtn = null;
    Login login = null;
    String name = null;
    Account(Login l, String s){
        login = l;
        name = s;
        // Title Bar
        TitleBar title = new TitleBar();
        title.setBounds(0, 0, 700, 50);
        add(title);

        // Account Information
        JLabel temp = new JLabel();
        temp.setIcon(new ImageIcon("images/student2.png"));
        temp.setBounds(10, 60, 40, 40);
        add(temp);

        temp = new JLabel();
        showName(temp);
        temp.setBounds(60, 60, 500, 40);
        temp.setFont(new Font("SansSerif", Font.PLAIN, 30));
        temp.setForeground(Color.white);
        add(temp);

        // Button for Starting the Exam
        startBtn = new JButton("Start the Exam", new ImageIcon("images/exam.png"));
        startBtn.setBounds(200, 120, 300, 60);
        startBtn.setForeground(Color.white);
        startBtn.setBackground(new Color(5,26,45));
        startBtn.setFont(new Font("Monospace", Font.BOLD, 30));
        startBtn.addActionListener(this);
        add(startBtn);

        // Button for Settings
        settingsBtn = new JButton("Change Password", new ImageIcon("images/settings.png"));
        settingsBtn.setBounds(375, 240, 250, 50);
        settingsBtn.setFont(new Font("Monospace", Font.PLAIN, 15));
        settingsBtn.setBackground(new Color(5,26,45));
        settingsBtn.setForeground(Color.white);
        settingsBtn.addActionListener(this);
        add(settingsBtn);
        // TODO 4: Create the Log Out button

        logoutBtn = new JButton("Logout", new ImageIcon("images/logout.png"));
        logoutBtn.setBounds(30, 240, 200, 50);
        logoutBtn.setFont(new Font("sanserif", Font.BOLD, 15));
        logoutBtn.addActionListener(this);
        add(logoutBtn);

        // Frame Properties
        setUndecorated(true);
        setSize(700, 300);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(67,139,211));
        setIconImage(new ImageIcon("./images/student.png").getImage());
        setLayout(null);
        setVisible(true);
    }
    public void showName(JLabel c){
        c.setText(name);
    }
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == startBtn){
            new Exam(this);
        }else if(ae.getSource() == settingsBtn){
            if(JOptionPane.showConfirmDialog(this,"Do you want to change your password?") == 0){
                String newpass = JOptionPane.showInputDialog(this,"Enter new password", "Change Value", JOptionPane.QUESTION_MESSAGE);
                String confirmpass = JOptionPane.showInputDialog(this,"Confirm new password", "Change Value", JOptionPane.QUESTION_MESSAGE);
                if(newpass.equals(confirmpass)) {
                    int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to change the password?");
                    if (confirm == 0 && login.db.updatePassword(login.studentId.getText(), newpass))
                        JOptionPane.showMessageDialog(this, "Password has been successfully changed");
                    else
                        JOptionPane.showMessageDialog(this, "Password was not changed");
                }
            }

        }else if(ae.getSource() == logoutBtn){
            name = "";
            login.setVisible(true);
            login.studentId.setText("");
            login.password.setText("");
        }
    }
}
