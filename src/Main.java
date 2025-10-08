import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main
{
    private static String word;
    private static final String[] letters =
            {"-", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L",
                    "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "Ä", "Ö", "Ü"};

    public static void main(String[] args)
    {
        try
        {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e)
        {
            System.out.println("Could not load nimbus look and feel" + e.getMessage());
        }

        MainUI ui = new MainUI();
        JFrame frame = new JFrame();

        frame.setContentPane(ui.getMainPnl());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setVisible(true);

        GameLogic game = new GameLogic(ui, frame);

        JMenuBar menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("new game");
        JMenuItem mI = new JMenuItem("+");
        gameMenu.add(mI);
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(gameMenu);
        frame.setJMenuBar(menuBar);

        ui.getOkBtn().addActionListener(e -> game.CheckLetter());

        mI.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String input = JOptionPane.showInputDialog(frame, "enter your word", "new game", JOptionPane.PLAIN_MESSAGE);
                if (input != null)
                {
                    word = input;
                }

                game.setErrorCounter(0);

                JComboBox<String> lettersCb = ui.getLettersCb();
                lettersCb.removeAllItems();
                for (String letter : letters)
                {
                    lettersCb.addItem(letter);
                }
                lettersCb.setSelectedIndex(0);

                game.UpdateStageImage(0);
                game.setWord(word);
                game.UpdateWord();
                game.gameOver = false;
                game.hasWon = false;
            }
        });
    }
}
