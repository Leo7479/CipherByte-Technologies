import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenAccount extends JFrame implements ActionListener, ChangeListener {
    JButton backBtn, proceedBtn;
    JTabbedPane tbp;
    JPanel personalDetailsPanel = null, accountDetailsPanel = null, servicesPanel = null;
    Login l = null;
    JTextField firstName, lastName, email, DOB, MOB, YOB, panNo, aadhaarNo, homeBranch, initialBalance, monthlyIncome;
    JComboBox gender, accountType;
    JRadioButton taxyes, taxno, atmyes, atmno, passyes, passno, onlineyes, onlineno;
    ButtonGroup taxSaver, atmNeeded, passbookNeeded, onlineBankingNeeded;

    String[] personal , account;

    OpenAccount(Login l){
        this.l = l;
        TitleBar titleBar = new TitleBar();
        titleBar.setBounds(0,0,700, 35);
        add(titleBar);

        personalDetailsPanel = createPersonalPanel();
        accountDetailsPanel = createAccountPanel();
        servicesPanel = createServicesPanel();

        tbp = new JTabbedPane();
        tbp.add("Personal Details", personalDetailsPanel);
        tbp.add("Account Details", accountDetailsPanel);
        tbp.add("Services Details", servicesPanel);
        tbp.setBounds(10,50,680,480);
        add(tbp);
        tbp.addChangeListener(this);

        backBtn = new JButton("Back to Main Menu");
        backBtn.setBounds(20, 550, 200, 40);
        add(backBtn);
        backBtn.addActionListener(this);

        proceedBtn = new JButton("Proceed");
        proceedBtn.setBounds(480, 550, 200, 40);
        add(proceedBtn);
        proceedBtn.addActionListener(this);


        // Frame configurations:
        setUndecorated(true);
        setLayout(null);
        setSize(700, 600);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.white);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon("./images/login.png").getImage());
    }
    private JPanel createPersonalPanel(){
        JPanel temp = new JPanel(null);

        JLabel text = new JLabel("First Name");
        text.setBounds(10, 10, 150, 40);
        temp.add(text);

        firstName = new JTextField();
        firstName.setBounds(200, 10, 250, 40);
        temp.add(firstName);

        text = new JLabel("Last Name");
        text.setBounds(10, 60, 150, 40);
        temp.add(text);

        lastName = new JTextField();
        lastName.setBounds(200, 60, 250, 40);
        temp.add(lastName);

        text = new JLabel("Gender");
        text.setBounds(10, 110, 150, 40);
        temp.add(text);

        gender = new JComboBox(new String[] {"MALE","FEMALE","THIRD GENDER","PREFER NOT TO SAY"});
        gender.setBounds(200, 110, 250, 40);
        temp.add(gender);

        text = new JLabel("Email Address");
        text.setBounds(10, 160, 150, 40);
        temp.add(text);

        email = new JTextField();
        email.setBounds(200, 160, 250, 40);
        temp.add(email);

        text = new JLabel("Date of Birth");
        text.setBounds(10, 210, 150, 40);
        temp.add(text);

        DOB = new JTextField();
        DOB.setBounds(200, 210, 50, 40);
        temp.add(DOB);
        text = new JLabel("/");
        text.setBounds(265, 210, 10, 40);
        temp.add(text);
        MOB = new JTextField();
        MOB.setBounds(280, 210, 50, 40);
        temp.add(MOB);
        text = new JLabel("/");
        text.setBounds(335, 210, 10, 40);
        temp.add(text);
        YOB = new JTextField();
        YOB.setBounds(350, 210, 100, 40);
        temp.add(YOB);

        text = new JLabel("Pan Number");
        text.setBounds(10, 260, 150, 40);
        temp.add(text);

        panNo = new JTextField();
        panNo.setBounds(200, 260, 250, 40);
        temp.add(panNo);

        text = new JLabel("Aadhaar Number");
        text.setBounds(10, 310, 150, 40);
        temp.add(text);

        aadhaarNo = new JTextField();
        aadhaarNo.setBounds(200, 310, 250, 40);
        temp.add(aadhaarNo);

        temp.setSize(600, 500);
        return temp;
    }
    private JPanel createAccountPanel(){
        JPanel temp = new JPanel(null);

        JLabel text = new JLabel("Account Type");
        text.setBounds(10, 10, 150, 40);
        temp.add(text);

        accountType = new JComboBox(new String[] {"SAVING","CURRENT"});
        accountType.setBounds(200, 10, 250, 40);
        temp.add(accountType);

        text = new JLabel("Opening Balance");
        text.setBounds(10, 60, 150, 40);
        temp.add(text);

        initialBalance = new JTextField();
        initialBalance.setBounds(200, 60, 250, 40);
        temp.add(initialBalance);

        text = new JLabel("Tax Saver");
        text.setBounds(10, 110, 150, 40);
        temp.add(text);

        taxSaver = new ButtonGroup();
        this.taxyes = new JRadioButton("YES");
        this.taxno = new JRadioButton("NO");
        taxSaver.add(taxyes);
        taxSaver.add(taxno);
        taxyes.setBounds(200, 110, 100, 40);
        taxno.setBounds( 320, 110, 100, 40);
        temp.add(taxyes);
        temp.add(taxno);

        text = new JLabel("Home Branch");
        text.setBounds(10, 160, 150, 40);
        temp.add(text);

        homeBranch = new JTextField();
        homeBranch.setBounds(200, 160, 250, 40);
        temp.add(homeBranch);

        text = new JLabel("Monthly Income");
        text.setBounds(10, 210, 150, 40);
        temp.add(text);

        monthlyIncome = new JTextField();
        monthlyIncome.setBounds(200, 210, 250, 40);
        temp.add(monthlyIncome);

        temp.setSize(600, 500);
        return temp;
    }
    private JPanel createServicesPanel(){
        JPanel temp = new JPanel(null);

        this.atmyes = new JRadioButton("YES", true);
        this.passyes = new JRadioButton("YES");
        this.onlineyes = new JRadioButton("YES",true);
        this.atmno = new JRadioButton("NO");
        this.passno = new JRadioButton("NO",true);
        this.onlineno = new JRadioButton("NO");

        JLabel text = new JLabel("ATM Card Needed");
        text.setBounds(10, 10, 150, 40);
        temp.add(text);

        atmNeeded = new ButtonGroup();
        atmNeeded.add(atmyes);
        atmNeeded.add(atmno);
        atmyes.setBounds(200, 10, 100, 40);
        atmno.setBounds( 320, 10, 100, 40);
        temp.add(atmyes);
        temp.add(atmno);

        text = new JLabel("PassBook Needed");
        text.setBounds(10, 60, 150, 40);
        temp.add(text);

        passbookNeeded = new ButtonGroup();
        passbookNeeded.add(passyes);
        passbookNeeded.add(passno);
        passyes.setBounds(200, 60, 100, 40);
        passno.setBounds( 320, 60, 100, 40);
        temp.add(passyes);
        temp.add(passno);

        text = new JLabel("Online Banking Needed");
        text.setBounds(10, 110, 150, 40);
        temp.add(text);

        onlineBankingNeeded = new ButtonGroup();
        onlineBankingNeeded.add(onlineyes);
        onlineBankingNeeded.add(onlineno);
        onlineyes.setBounds(200, 110, 100, 40);
        onlineno.setBounds( 320, 110, 100, 40);
        temp.add(onlineyes);
        temp.add(onlineno);

        temp.setSize(600, 500);
        return temp;
    }

    private boolean allSet(){
        try{
            if(firstName.getText().isEmpty()) throw new Exception();
            if(lastName.getText().isEmpty()) throw new Exception();
            if(DOB.getText().isEmpty()) throw new Exception();
            if(MOB.getText().isEmpty()) throw new Exception();
            if(YOB.getText().isEmpty()) throw new Exception();
            if(email.getText().isEmpty()) throw new Exception();
            if(panNo.getText().isEmpty()) throw new Exception();
            if(aadhaarNo.getText().isEmpty()) throw new Exception();
            if(initialBalance.getText().isEmpty()) throw new Exception();
            if(homeBranch.getText().isEmpty()) throw new Exception();
            if(monthlyIncome.getText().isEmpty()) throw new Exception();

            return true;
        }catch(Exception e){}
        return false;
    }

    private void getDetails(){
        personal = new String[6];
        account = new String[4];
        try {
            personal[0] = firstName.getText() + " " + lastName.getText();
            personal[1] = String.valueOf(gender.getSelectedItem()).substring(0, 1);
            personal[2] = email.getText();
            personal[3] = DOB.getText() + "/" + MOB.getText() + "/" + YOB.getText();
            personal[4] = panNo.getText();
            personal[5] = aadhaarNo.getText();

            account[0] = String.valueOf(accountType.getSelectedItem()).toLowerCase();
            account[1] = initialBalance.getText();
            account[2] = homeBranch.getText();
            account[3] = monthlyIncome.getText();

            Boolean atm = atmNeeded.isSelected(atmyes.getModel());
            String accountNumber = l.db.insertData(personal, account, atm);
            JOptionPane.showMessageDialog(this,"Account Successfully Created.\nAccount Number: "+ accountNumber);
        }
        catch(Exception e) {
            System.out.println("Error" + e);
            JOptionPane.showMessageDialog(this, "Account couldn't be created");
        }
    }
    @Override
    public void stateChanged(ChangeEvent e) {
        if(tbp.getSelectedComponent() == servicesPanel){
            proceedBtn.setText("Sign Up");
        }else{
            proceedBtn.setText("Proceed");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backBtn){
            int selected = tbp.getSelectedIndex();
            if(selected != 1 && selected != 0){
                backBtn.setText("Back");
                proceedBtn.setText("Proceed");
                tbp.setSelectedIndex(selected - 1);
            }
            else if(selected == 1){
                backBtn.setText("Back To Main Menu");
                proceedBtn.setText("Proceed");
                tbp.setSelectedIndex(0);
            }
            else {
                this.setVisible(false);
                proceedBtn.setText("Proceed");
                l.setVisible(true);
            }
        }
        else if(e.getSource() == proceedBtn){
            int selected = tbp.getSelectedIndex();
            if(selected != 1 && selected != 2){
                backBtn.setText("Back");
                proceedBtn.setText("Proceed");
                tbp.setSelectedIndex(selected +1);
            }
            else if(selected == 1){
                backBtn.setText("Back");
                proceedBtn.setText("Submit");
                tbp.setSelectedIndex(2);
            }
            else {
                if(allSet()) {
                    this.setVisible(false);
                    proceedBtn.setText("Submit");
                    l.setVisible(true);
                    this.getDetails();
                }else{
                    JOptionPane.showMessageDialog(this,"Please fill all the fields");
                }
            }
        }
    }
}
