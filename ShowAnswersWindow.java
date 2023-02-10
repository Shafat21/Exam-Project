import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class ShowAnswersWindow extends JFrame 
{
    private JTextArea answerArea;
    private JButton previousButton;
    private JButton nextButton;
    private File[] answerFiles;
    private int currentIndex;

    public ShowAnswersWindow() 
    {
        setTitle("Show Answers");
        setLayout(new BorderLayout());

        ImageIcon icon = new ImageIcon("logo.png");
        setIconImage(icon.getImage());
        
        answerArea = new JTextArea(10, 30);
        answerArea.setEditable(false);
        add(new JScrollPane(answerArea), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        previousButton = new JButton("Previous");
        previousButton.addActionListener(new PreviousButtonListener());
        nextButton = new JButton("Next");
        nextButton.addActionListener(new NextButtonListener());
        bottomPanel.add(previousButton);
        bottomPanel.add(nextButton);
        add(bottomPanel, BorderLayout.SOUTH);

        File answersFolder = new File("answers");
        answerFiles = answersFolder.listFiles(new FilenameFilter() 
        {
            @Override
            public boolean accept(File dir, String name) 
            {
                return name.endsWith(".ser");
            }
        });

        if (answerFiles.length > 0) 
        {
            currentIndex = 0;
            loadAnswerFile(answerFiles[currentIndex]);
        }

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setVisible(true);
    }

    private void loadAnswerFile(File answerFile) 
    {
        try 
        {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(answerFile));
            String answer = (String) input.readObject();
            answerArea.setText(answer);
            input.close();
            setTitle("Show Answers - " + answerFile.getName());
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    private class PreviousButtonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            if (currentIndex > 0) 
            {
                currentIndex--;
                answerArea.setText("");
                loadAnswerFile(answerFiles[currentIndex]);
            }
        }
    }

    private class NextButtonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            if (currentIndex < answerFiles.length - 1) 
            {
                currentIndex++;
                answerArea.setText("");
                loadAnswerFile(answerFiles[currentIndex]);
            }
        }
    }
}
