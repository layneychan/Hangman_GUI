import javax.swing.*;

public class MainUI
{
    private JPanel mainPnl;
    private JLabel stageImage;
    private JComboBox<String> lettersCb;
    private JButton okBtn;
    private JLabel wordLbl;

    public JPanel getMainPnl()
    {
        return mainPnl;
    }

    public JComboBox<String> getLettersCb()
    {
        return lettersCb;
    }

    public JLabel getWordLbl()
    {
        return wordLbl;
    }

    public JLabel getStageImage()
    {
        return stageImage;
    }

    public JButton getOkBtn()
    {
        return okBtn;
    }
}