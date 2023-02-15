import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import javax.swing.table.*;

public class QuestionCreationWindow extends JFrame 
{
    private JLabel teacherNameLabel;
    private JLabel questionLabel;
    private JTextField teacherNameField;
    private JTextField questionField;
    private JTable questionTable;
    private DefaultTableModel tableModel;
    private JScrollPane tableScrollPane;
    private JButton saveButton;
    private JButton showAnswersButton;
    private ArrayList<String> questions;
    private String teacherName;
    
    public QuestionCreationWindow() 
    {
        setTitle("Create Questions");
        setLayout(new GridLayout(0, 1));

        ImageIcon icon = new ImageIcon("logo.png");
        setIconImage(icon.getImage());

        teacherNameLabel = new JLabel("Teacher Name: ");
        teacherNameLabel.setFont(new Font("Serif", Font.BOLD, 24));
        teacherNameLabel.setHorizontalAlignment(JLabel.CENTER);
        teacherNameField = new JTextField(30);
        teacherNameField.addActionListener(new TeacherNameFieldListener());
        JPanel teacherNamePanel = new JPanel(new GridLayout(0, 1));
        teacherNamePanel.add(teacherNameLabel);
        teacherNamePanel.add(teacherNameField);
        add(teacherNamePanel);
        
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

        JPanel buttonPanel = new JPanel(new GridLayout(0, 1));
        buttonPanel.add(questionLabel);
        buttonPanel.add(questionField);
        buttonPanel.add(saveButton);
        buttonPanel.add(showAnswersButton);

        add(buttonPanel);
        add(tableScrollPane);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setVisible(true);
    }

    private class TeacherNameFieldListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            teacherName = teacherNameField.getText();
            teacherNameLabel.setText("Teacher Name: " + teacherName);
        }
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