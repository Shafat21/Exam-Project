import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.swing.table.*;

public class QuestionCreationWindow extends JFrame 
{
    private JLabel questionLabel;
    private JTextField questionField;
    private JTable questionTable;
    private DefaultTableModel tableModel;
    private JScrollPane tableScrollPane;
    private JButton saveButton;
    private JButton showAnswersButton;

    public QuestionCreationWindow() 
    {
        setTitle("Create Questions");
        setLayout(new FlowLayout());

        questionLabel = new JLabel("Enter question: ");
        questionField = new JTextField(30);

        tableModel = new DefaultTableModel(new String[] {"Serial", "Question"}, 0);
        questionTable = new JTable(tableModel);
        tableScrollPane = new JScrollPane(questionTable);

        try {
            BufferedReader reader = new BufferedReader(new FileReader("questions.txt"));
            String line = reader.readLine();
            int serial = 1;
            while (line != null) 
            {
                tableModel.addRow(new String[] {String.valueOf(serial), line});
                line = reader.readLine();
                serial++;
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
        add(tableScrollPane);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
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
            int serial = tableModel.getRowCount() + 1;
            tableModel.addRow(new String[] {String.valueOf(serial), question});
        }
    }

    private class ShowAnswersButtonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            new ShowAnswersWindow();
        }
    }
}
