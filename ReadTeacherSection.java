import java.awt.*;
// import java.awt.event.*;
import javax.swing.*;
// import javax.swing.event.*;

public class ReadTeacherSection extends JPanel {
    private JLabel ques1;
    private JTextArea ans1;
    private JLabel ques2;
    private JTextArea ans2;
    private JLabel ques3;
    private JTextField jcomp6;
    private JTextField jcomp7;
    private JTextField jcomp8;
    private JTextField jcomp9;
    private JButton save;
    private JButton clear;
    private JMenuBar menu;

    public ReadTeacherSection() {
        //construct preComponents
        JMenu fileMenu = new JMenu ("File");
        JMenuItem printItem = new JMenuItem ("Print");
        fileMenu.add (printItem);
        JMenuItem exitItem = new JMenuItem ("Exit");
        fileMenu.add (exitItem);
        JMenu helpMenu = new JMenu ("Help");
        JMenuItem contentsItem = new JMenuItem ("Contents");
        helpMenu.add (contentsItem);
        JMenuItem aboutItem = new JMenuItem ("About");
        helpMenu.add (aboutItem);

        //construct components
        ques1 = new JLabel ("First Question?");
        ans1 = new JTextArea (5, 5);
        ques2 = new JLabel ("Second Question?");
        ans2 = new JTextArea (5, 5);
        ques3 = new JLabel ("Third Question?");
        jcomp6 = new JTextField (5);
        jcomp7 = new JTextField (5);
        jcomp8 = new JTextField (5);
        jcomp9 = new JTextField (5);
        save = new JButton ("Save");
        clear = new JButton ("Clear");
        menu = new JMenuBar();
        menu.add (fileMenu);
        menu.add (helpMenu);

        //adjust size and set layout
        setPreferredSize (new Dimension (552, 480));
        setLayout (null);

        //add components
        add (ques1);
        add (ans1);
        add (ques2);
        add (ans2);
        add (ques3);
        add (jcomp6);
        add (jcomp7);
        add (jcomp8);
        add (jcomp9);
        add (save);
        add (clear);
        add (menu);

        //set component bounds (only needed by Absolute Positioning)
        ques1.setBounds (15, 25, 505, 25);
        ans1.setBounds (15, 55, 510, 65);
        ques2.setBounds (15, 135, 510, 25);
        ans2.setBounds (15, 170, 510, 65);
        ques3.setBounds (15, 245, 510, 25);
        jcomp6.setBounds (15, 290, 120, 25);
        jcomp7.setBounds (145, 290, 120, 25);
        jcomp8.setBounds (275, 290, 120, 25);
        jcomp9.setBounds (410, 290, 120, 25);
        save.setBounds (155, 375, 100, 25);
        clear.setBounds (300, 375, 100, 25);
        menu.setBounds (0, 0, 550, 25);
    }


    public static void main (String[] args) {
        JFrame frame = new JFrame ("ReadTeacherSection");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new ReadTeacherSection());
        frame.pack();
        frame.setVisible (true);
    }
}
