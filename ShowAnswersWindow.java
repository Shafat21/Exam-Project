import java.awt.*;
import javax.swing.*;
import java.io.*;

public class ShowAnswersWindow extends JFrame 
{
    private JTextArea answerArea;

    public ShowAnswersWindow() 
    {
        setTitle("Show Answers");
        setLayout(new FlowLayout());

        answerArea = new JTextArea(10, 30);
        answerArea.setEditable(false);

        try {
        BufferedReader reader = new BufferedReader(new FileReader("answers.txt"));
        String line = reader.readLine();
        while (line != null) {
            answerArea.append(line + "\n");
            line = reader.readLine();
        }
        reader.close();
        } catch (IOException ex) {
        ex.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(answerArea);
        add(scrollPane);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setVisible(true);
    }
}
