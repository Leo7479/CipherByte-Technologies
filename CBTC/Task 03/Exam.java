import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class Exam extends JFrame implements ActionListener, Runnable{
    private int[] choiceList = null;
    private int numberOfQuestions = 10;
    private int linesRead = 0;
    private JPanel[] questionsList = null;
    private JRadioButton[][] options = null;
    private ButtonGroup[] buttonGroup = null;
    private int[] answers = null;
    private JTabbedPane tbp = null;
    private Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
    private int height = (int)dm.getHeight(), width = (int)dm.getWidth();
    JButton prevBtn, nextBtn, submitBtn;
    private JLabel timer = null;
    Account account = null;
    int timeForExam = 300; // time in seconds

    Exam(Account a){
        account = a;
        // Timer section
        JLabel temp;
        temp = new JLabel();
        temp.setIcon(new ImageIcon("images/clock.png"));
        temp.setBounds(20, 10, 40, 40);
        add(temp);

        timer = new JLabel("00:00:00");
        timer.setBounds(70, 10, 150, 40);
        timer.setFont(new Font("sanserif", Font.BOLD, 25));
        Thread t = new Thread(this);
        t.start();
        add(timer);
        // Question List
        questionsList = new JPanel[numberOfQuestions];
        buttonGroup = new ButtonGroup[numberOfQuestions];
        options = new JRadioButton[numberOfQuestions][4];
        answers = new int[numberOfQuestions];

        for(int i=0;i<numberOfQuestions;i++){
            questionsList[i] = new JPanel();
            temp = new JLabel();
            temp.setFont(new Font("SansSeris", Font.BOLD, 30));
            questionsList[i].add(temp);
            buttonGroup[i] = new ButtonGroup();
            for(int j=0;j<4;j++) {
                options[i][j] = new JRadioButton();
                buttonGroup[i].add(options[i][j]);
                questionsList[i].add(options[i][j]);
                options[i][j].setFont(new Font("SansSeris", Font.BOLD, 25));
            }
            nextQuestion(temp, options[i]);
            setUpStyle(questionsList[i],temp, options[i]);
        }
        tbp = new JTabbedPane(JTabbedPane.RIGHT);
        for(int i=0;i<numberOfQuestions;i++)
            tbp.add(String.valueOf(i+1),questionsList[i]);
        tbp.setBounds(50, 50, width-100, height-200);
        add(tbp);

        // Button
        prevBtn = new JButton("Previous Question");
        prevBtn.setBounds(50, height-80, 200, 40);
        prevBtn.setFont(new Font("Monospace", Font.PLAIN, 20));
        prevBtn.setBackground(Color.blue);
        prevBtn.setForeground(Color.white);
        prevBtn.addActionListener(this);
        prevBtn.setEnabled(false);
        add(prevBtn);

        nextBtn = new JButton("Next Question");
        nextBtn.setBounds(280, height-80, 200, 40);
        nextBtn.setFont(new Font("Monospace", Font.PLAIN, 20));
        nextBtn.setBackground(Color.blue);
        nextBtn.setForeground(Color.white);
        nextBtn.addActionListener(this);
        add(nextBtn);

        // Submit Button
        submitBtn = new JButton("Submit");
        submitBtn.setBounds(width-230, height-80, 180, 40);
        submitBtn.setFont(new Font("Monospace", Font.PLAIN, 20));
        submitBtn.setBackground(new Color(47, 119, 47));
        submitBtn.setForeground(Color.white);
        submitBtn.addActionListener(this);
        add(submitBtn);

        // Frame Properties
        setUndecorated(true);
        //setSize(width, height);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.white);
        setIconImage(new ImageIcon("./images/student.png").getImage());
        setLayout(null);
        setVisible(true);
    }
    public void nextQuestion(JLabel c, JRadioButton[] options)  {
        try {
            BufferedReader br = new BufferedReader(new FileReader("./questions.txt"));
            int i=0;
            String lines = "";
            while(i<=linesRead) {
                lines = br.readLine();
                i++;
            }
            String[] temp = lines.split(",");
            c.setText(temp[0]);
            options[0].setText(temp[1]);
            options[1].setText(temp[2]);
            if(temp.length == 6) {
                options[2].setText(temp[3]);
                options[3].setText(temp[4]);
            }else{
                options[2].setVisible(false);
                options[3].setVisible(false);
            }
            answers[i-1] = Integer.parseInt(temp[temp.length - 1]);
            linesRead++;
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public void setUpStyle(JPanel p, JLabel l, JRadioButton[] options){
        l.setBounds(10, 10, width-100, 100);
        p.add(l);
        JPanel temp = new JPanel();
        temp.setBounds(10, 150, width-100, 150);
        temp.setLayout(new GridLayout(4,1, 0, 10));
        for(int i=0;i<4;i++)
            temp.add(options[i]);
        p.add(temp);
        p.setLayout(null);
        p.setSize(width-100, height-100);
    }
    public void getChoices(){
        choiceList = new int[numberOfQuestions];
        int i,j;
        for(i=0;i<numberOfQuestions;i++){
            ButtonModel b = buttonGroup[i].getSelection();
            for(j=0;j<4;j++)
                if(options[i][j].getModel() == b)
                    break;
            choiceList[i] = j == 4 ? -1 : j+1;
        }
    }
    public int checkAnswers(){
        int score = 0;
        for(int i=0;i<numberOfQuestions;i++) {
            if (choiceList[i] == answers[i])
                score = score + 4;
            else if(choiceList[i]!=-1 && choiceList[i] != answers[i])
                score = score - 1;
        }
        return score;
    }
    @Override
    public void actionPerformed(ActionEvent ae){
        int current = tbp.getSelectedIndex();
        if(ae.getSource() == prevBtn){
            if(current == 1)
                prevBtn.setEnabled(false);
            else
                prevBtn.setEnabled(true);
            if(current>0)
                tbp.setSelectedIndex((current-1));
        }
        else if(ae.getSource() == nextBtn){
            tbp.setSelectedIndex((current+1)%numberOfQuestions);
            if(current != numberOfQuestions - 1)
                prevBtn.setEnabled(true);
            else
                prevBtn.setEnabled(false);
        }
        else if(ae.getSource() == submitBtn){
            System.out.println("Getting answers");
            getChoices();
            System.out.println("Got answers");
            int score = checkAnswers();
            setVisible(false);
            account.setVisible(true);
            JOptionPane.showMessageDialog(account, "Your Score is "+score);
        }
    }
    @Override
    public void run(){
        int value = timeForExam; // time in seconds
        int minutes=0, hours=0, seconds=0;
        String text = "";
        hours = value / 3600;
        minutes = (value % 3600) / 60;
        seconds = value % 60;
        text = String.format("%02d",hours) +":"+ String.format("%02d",minutes) +":"+ String.format("%02d",seconds);
        timer.setText(text);
        while(value>0){
            try{
                value--;
                Thread.sleep(1000);
            }catch(Exception e){}
            text = "";
            hours = value / 3600;
            minutes = (value % 3600) / 60;
            seconds = value % 60;
            text = String.format("%02d",hours) +":"+ String.format("%02d",minutes) +":"+ String.format("%02d",seconds);
            timer.setText(text);
        }
        submitBtn.doClick();
    }


}
