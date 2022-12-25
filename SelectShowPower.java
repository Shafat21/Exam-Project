import java.awt.*;
// import java.awt.event.*;
import javax.swing.*;
// import javax.swing.event.*;

public class SelectShowPower extends JPanel {
    private JToggleButton teacher;
    private JButton student;
    private JPasswordField password;

    public SelectShowPower() {
        //construct components
        teacher = new JToggleButton ("Teacher");
        student = new JButton ("Student");
        password = new JPasswordField (5);

        //adjust size and set layout
        setPreferredSize (new Dimension (312, 143));
        setLayout (null);

        //add components
        add (teacher);
        add (student);
        add (password);

        //set component bounds (only needed by Absolute Positioning)
        teacher.setBounds (25, 35, 115, 40);
        student.setBounds (165, 35, 115, 40);
        password.setBounds (25, 80, 255, 25);

        // String passText = new String(password.getPassword());


        // teacher.addActionListener(new ActionListener() 
        // {
        //     @Override
        //     public void actionPerformed(ActionEvent teacher) {
        //         if(teacher.getModel().isPressed())
        //         {
        //             add (password);
        //             if (passText == "admin")
        //                 {
        //                     password.setVisible (true);
        //                 }
        //         }
        //     }
        // });
    }

    public static void main (String[] args) {
        JFrame frame = new JFrame ("Select Show Power");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new SelectShowPower());
        frame.pack();
        frame.setVisible (true);
        
    }
}
