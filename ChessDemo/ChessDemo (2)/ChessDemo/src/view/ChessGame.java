package view;

public class ChessGame {

    static ChessGameFrame mainFrame;
    public static void runGame(){
        mainFrame = new ChessGameFrame(1000, 760);
        mainFrame.setVisible(true);
    }
}
