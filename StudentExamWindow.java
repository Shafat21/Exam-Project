import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class StudentExamWindow extends JFrame 
{
    private JLabel questionLabel;
    private JTextArea questionArea;
    private JLabel answerLabel;
    private JTextArea answerField;
    private JLabel nameLabel;
    private JTextField nameField;
    private JLabel idLabel;
    private JTextField idField;
    private JButton submitButton;

    public StudentExamWindow() 
    {
        setTitle("Take Exam");
        setLayout(new FlowLayout());

        questionLabel = new JLabel("Question: ");
        questionArea = new JTextArea(10, 40);
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
        answerField = new JTextArea(10, 40);
        nameLabel = new JLabel("Name: ");
        nameField = new JTextField(20);
        idLabel = new JLabel("ID: ");
        idField = new JTextField(20);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(new SubmitButtonListener());

        add(nameLabel);
        add(nameField);
        add(idLabel);
        add(idField);
        add(questionLabel);
        add(questionArea);
        add(answerLabel);
        add(answerField);

        add(submitButton);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(570, 470);
        setVisible(true);
    }

    private class SubmitButtonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            String answer = answerField.getText();
            String name = nameField.getText();
            String id = idField.getText();

            File answersFolder = new File("answers");
            if (!answersFolder.exists()) {
                answersFolder.mkdir();
            }

            File answersFile = new File(answersFolder, id + ".txt");
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(answersFile, true));
                writer.append(name + "\n\n" + id + "\n\n" + answer + "\n");
                writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            answerField.setText("");
            nameField.setText("");
            idField.setText("");
        }
    }
}
