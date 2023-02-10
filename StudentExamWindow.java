import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

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

        ImageIcon icon = new ImageIcon("logo.png");
        setIconImage(icon.getImage());
        
        questionLabel = new JLabel("Question: ");
        questionArea = new JTextArea(10, 40);
        questionArea.setEditable(false);

        try {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("questions.ser"));
            ArrayList<String> questions = (ArrayList<String>) input.readObject();
            input.close();

            StringBuilder questionsString = new StringBuilder();
            for (String question : questions) {
                questionsString.append(question).append("\n");
            }

            questionArea.setText(questionsString.toString());
        } catch (IOException | ClassNotFoundException ex) {
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

            File answersFile = new File(answersFolder, id + ".ser");
            try {
                ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(answersFile, true));
                output.writeObject(name + "\n\n" + id + "\n\n" + answer + "\n");
                output.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            answerField.setText("");
            nameField.setText("");
            idField.setText("");
        }
    }
}
