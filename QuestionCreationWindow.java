import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class QuestionCreationWindow extends JFrame 
{
    private JLabel questionLabel;
    private JTextField questionField;
    private JTextArea questionArea;
    private JButton saveButton;
    private JButton showAnswersButton;

    public QuestionCreationWindow() 
    {
        setTitle("Create Questions");
        setLayout(new FlowLayout());

        questionLabel = new JLabel("Enter question: ");
        questionField = new JTextField(30);
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

        saveButton = new JButton("Save");
        saveButton.addActionListener(new SaveButtonListener());
        showAnswersButton = new JButton("Show Answers");
        showAnswersButton.addActionListener(new ShowAnswersButtonListener());

        add(questionLabel);
        add(questionField);
        add(saveButton);
        add(showAnswersButton);

        JScrollPane scrollPane = new JScrollPane(questionArea);
        add(scrollPane);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setVisible(true);
    }

    private class SaveButtonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            String question = questionField.getText();

            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("questions.txt", true));
                writer.append(question + "\n");
                writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            questionField.setText("");
            questionArea.append(question + "\n");
        }
    }

    private class ShowAnswersButtonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            // Open the show answers window
            new ShowAnswersWindow();
        }
    }
}
