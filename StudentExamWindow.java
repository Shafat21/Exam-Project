import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class StudentExamWindow extends JFrame 
{
    private JLabel questionLabel;
    private JTextArea questionArea;
    private JLabel answerLabel;
    private JTextField answerField;
    private JButton submitButton;

    public StudentExamWindow() 
    {
    setTitle("Take Exam");
    setLayout(new FlowLayout());

    questionLabel = new JLabel("Question: ");
    questionArea = new JTextArea(10, 30);
    questionArea.setEditable(false);

        try {
            BufferedReader reader = new BufferedReader(new FileReader("questions.txt"));
            String line = reader.readLine();
            while (line != null) {
                questionArea.append(line + "\n");
                line = reader.readLine();
            }
                reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        answerLabel = new JLabel("Answer: ");
        answerField = new JTextField(30);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(new SubmitButtonListener());

        add(questionLabel);
        add(questionArea);
        add(answerLabel);
        add(answerField);
        add(submitButton);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setVisible(true);
    }

    private class SubmitButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) 
        {
            String answer = answerField.getText();

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("answers.txt", true));
            writer.append(answer + "\n");
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        answerField.setText("");
        }
    }
}
