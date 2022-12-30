import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ExamSystemGui extends JFrame 
{
    public enum UserType 
    {
        TEACHER,
        STUDENT;
    }
    
    private JLabel timeLabel;
    
    public ExamSystemGui() 
    {
        setTitle("Exam System");
        setLayout(new FlowLayout());
        ImageIcon icon = new ImageIcon("favicon.ico");
    
        JButton teacherButton = new JButton("Teacher");
        teacherButton.addActionListener(new UserTypeButtonListener(UserType.TEACHER));
        JButton studentButton = new JButton("Student");
        studentButton.addActionListener(new UserTypeButtonListener(UserType.STUDENT));
        
        timeLabel = new JLabel();
        updateTimeLabel();
        
        add(timeLabel);
        add(teacherButton);
        add(studentButton);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 100);
        setVisible(true);
        setIconImage(icon.getImage());

        Timer timer = new Timer(1000, new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
                updateTimeLabel();
            }
        });
        timer.start();
    }
    
    private void updateTimeLabel() 
    {
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
        String formattedTime = currentTime.format(formatter);
        timeLabel.setText(formattedTime);
    }

    private class UserTypeButtonListener implements ActionListener 
    {
        private UserType userType;

        public UserTypeButtonListener(UserType userType) 
        {
            this.userType = userType;
        }

        public void actionPerformed(ActionEvent e) 
        {
            switch (userType) {
                case TEACHER:
                    new TeacherPasswordWindow();
                    break;
                case STUDENT:
                    new StudentExamWindow();
                    break;
            }
        }
    }
}
