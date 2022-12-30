import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TeacherPasswordWindow extends JFrame 
{
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton submitButton;
    private String password;

    public TeacherPasswordWindow() 
    {
      setTitle("Teacher Password");
      setLayout(new FlowLayout());

      passwordLabel = new JLabel("Enter password: ");
      passwordField = new JPasswordField(10);
      submitButton = new JButton("Submit");
      submitButton.addActionListener(new SubmitButtonListener());

      add(passwordLabel);
      add(passwordField);
      add(submitButton);

      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      setSize(350, 100);
      setVisible(true);
    }

    private class SubmitButtonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            char[] enteredPassword = passwordField.getPassword();
            password = new String(enteredPassword);

            if (password.equals("admin")) {
              new QuestionCreationWindow();
            } else {
              JOptionPane.showMessageDialog(null, "Incorrect password. Please try again.");
            }
        }
    }
}
