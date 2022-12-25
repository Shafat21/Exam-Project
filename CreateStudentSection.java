import java.awt.*;
// import java.awt.event.*;
import javax.swing.*;
// import javax.swing.event.*;

public class CreateStudentSection extends JPanel {
    private JButton submit;
    private JButton clear;
    private JLabel question1;
    private JTextArea answer1;
    private JLabel question2;
    private JTextArea answer2;
    private JLabel question3;
    private JCheckBox option1;
    private JCheckBox option2;
    private JCheckBox option3;
    private JCheckBox option4;

    public CreateStudentSection() {
        //construct components
        submit = new JButton ("Submit");
        clear = new JButton ("Clear");
        question1 = new JLabel ("First Question");
        answer1 = new JTextArea (5, 5);
        question2 = new JLabel ("Second Question");
        answer2 = new JTextArea (5, 5);
        question3 = new JLabel ("Third Question");
        option1 = new JCheckBox ("Option1");
        option2 = new JCheckBox ("Option2");
        option3 = new JCheckBox ("Option3");
        option4 = new JCheckBox ("Option4");

        //adjust size and set layout
        setPreferredSize (new Dimension (468, 346));
        setLayout (null);

        //add components
        add (submit);
        add (clear);
        add (question1);
        add (answer1);
        add (question2);
        add (answer2);
        add (question3);
        add (option1);
        add (option2);
        add (option3);
        add (option4);

        //set component bounds (only needed by Absolute Positioning)
        submit.setBounds (85, 285, 125, 35);
        clear.setBounds (260, 285, 125, 35);
        question1.setBounds (25, 20, 415, 25);
        answer1.setBounds (25, 45, 415, 50);
        question2.setBounds (25, 105, 415, 20);
        answer2.setBounds (25, 130, 415, 50);
        question3.setBounds (25, 200, 410, 20);
        option1.setBounds (25, 230, 100, 25);
        option2.setBounds (130, 230, 100, 25);
        option3.setBounds (235, 230, 100, 25);
        option4.setBounds (345, 230, 100, 25);
    }


    public static void main (String[] args) {
        JFrame frame = new JFrame ("CreateStudentSection");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new CreateStudentSection());
        frame.pack();
        frame.setVisible (true);
    }
}
