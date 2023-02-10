import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
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
    private ArrayList<String> questions;

    public QuestionCreationWindow() 
    {
        setTitle("Create Questions");
        setLayout(new FlowLayout());

        questionLabel = new JLabel("Enter question: ");
        questionField = new JTextField(30);

        tableModel = new DefaultTableModel(new String[] {"Serial", "Question"}, 0);
        questionTable = new JTable(tableModel);
        tableScrollPane = new JScrollPane(questionTable);

        questions = new ArrayList<>();

        try {
            FileInputStream fileIn = new FileInputStream("questions.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            questions = (ArrayList<String>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        

        int serial = 1;
        for (String question : questions) 
        {
            tableModel.addRow(new String[] {String.valueOf(serial), question});
            serial++;
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
            questions.add(question);

            try {
                FileOutputStream fileOut = new FileOutputStream("questions.ser");
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(questions);
                out.close();
                fileOut.close();
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