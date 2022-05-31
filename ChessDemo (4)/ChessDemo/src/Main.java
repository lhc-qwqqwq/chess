import view.ChessGame;
import view.ChessGameFrame;

import javax.swing.*;

public class Main {


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChessGame.runGame();
        });
    }
}
