import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ExamSystemGui extends JFrame 
{
    private JButton teacherButton;
    private JButton studentButton;

    public ExamSystemGui() {
        setTitle("Exam System");
        setLayout(new FlowLayout());
        ImageIcon icon = new ImageIcon("favicon.ico");
    
        teacherButton = new JButton("Teacher");
        teacherButton.addActionListener(new TeacherButtonListener());
        studentButton = new JButton("Student");
        studentButton.addActionListener(new StudentButtonListener());

        add(teacherButton);
        add(studentButton);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 100);
        setVisible(true);
        setIconImage(icon.getImage());
    }

    private class TeacherButtonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
        new TeacherPasswordWindow();
        }
    }

    private class StudentButtonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
        // Open the student exam window
        new StudentExamWindow();
        }
    }
}
