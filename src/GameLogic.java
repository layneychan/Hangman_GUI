import javax.swing.*;
import java.util.Objects;
import java.awt.*;

public class GameLogic
{
    private final MainUI ui;
    private final JFrame frame;
    private int errorCounter = 0;
    private String word;
    char[] hiddenWord;
    public boolean gameOver = false;
    public boolean hasWon = false;

    public GameLogic(MainUI ui, JFrame frame)
    {
        this.ui = ui;
        this.frame = frame;
    }

    public void CheckLetter()
    {
        if(gameOver)
        {
            JOptionPane.showMessageDialog(frame, "you already lost! start a new game!", "error", JOptionPane.ERROR_MESSAGE);
        }
        if(hasWon)
        {
            JOptionPane.showMessageDialog(frame, "you already won! start a new game!", "error", JOptionPane.ERROR_MESSAGE);
        }

        String selectedLetter = Objects.requireNonNull(ui.getLettersCb().getSelectedItem()).toString();
        if (selectedLetter.equals("-"))
        {
            JOptionPane.showMessageDialog(frame, "oops! you forgot to select a letter", "error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        char selectedL = selectedLetter.charAt(0);
        boolean letterFound = false;

        if (word.contains(selectedLetter))
        {
            for (int i = 0; i < word.length(); i++)
            {
                if (word.charAt(i) == selectedL)
                {
                    hiddenWord[i] =  selectedL;
                    letterFound = true;
                }
            }
        }

        if(!letterFound)
        {
            errorCounter++;
            UpdateStageImage(errorCounter);

            if (errorCounter == 11)
            {
                gameOver = true;
                UpdateStageImage(12);
                GameOver();
            }
        }
        ui.getLettersCb().removeItem(selectedLetter);
        ui.getLettersCb().setSelectedIndex(0);
        UpdateWord();
        WinCheck();
    }

    public void UpdateStageImage(int stage)
    {
        ImageIcon img = new ImageIcon("C:/Users/z00502xz/IdeaProjects/Hangman_GUI/images/man/stage" + stage + ".png"); //fix later
        //ImageIcon img = new ImageIcon("../../images/man/stage" + stage + ".png");
        Image scaledImg = img.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        img = new ImageIcon(scaledImg);
        ui.getStageImage().setIcon(img);
    }

    public void UpdateWord()
    {
        ui.getWordLbl().setText(getHiddenWordAsString());
    }

    public void GameOver()
    {
        JOptionPane.showMessageDialog(frame, "game over!\n the word was: " + word, "game over", JOptionPane.INFORMATION_MESSAGE);
    }

    public void WinCheck()
    {
        if(!getHiddenWordAsString().contains("_"))
        {
            hasWon = true;
            JOptionPane.showMessageDialog(frame, "you won!\n the word was: " + word, "winner", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void setWord(String word)
    {
        this.word = word.toUpperCase();
        //openWord = this.word.toCharArray();

        hiddenWord = new char[word.length()];
        for (int i = 0; i < word.length(); i++)
        {
            hiddenWord[i] = '_';
        }
    }

    public void setErrorCounter(int newValue)
    {
        errorCounter = newValue;
    }

    public String getHiddenWordAsString()
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hiddenWord.length; i++)
        {
            sb.append(hiddenWord[i]);
            if (i < hiddenWord.length - 1)
            {
                sb.append(' ');
            }
        }
        return sb.toString();
    }


    /*public char[] getClosedWord()
    {
        return closedWord;
    }

    public void setClosedWord(char[] closedWord)
    {
        this.closedWord = closedWord;
    }*/
}
