package model;

import controller.ClickController;
import view.ChessboardPoint;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class BishopChessComponent extends ChessComponent {

    private static Image BISHOP_WHITE;
    private static Image BISHOP_BLACK;

    private Image bishopImage;

    public void loadResource() throws IOException {
        if (BISHOP_WHITE == null) {
            BISHOP_WHITE = ImageIO.read(new File("./images/bishop-white.png"));
        }

        if (BISHOP_BLACK == null) {
            BISHOP_BLACK = ImageIO.read(new File("./images/bishop-black.png"));
        }
    }

    private void initiatePawnImage(ChessColor color) {
        try {
            loadResource();
            if (color == ChessColor.WHITE) {
                bishopImage = BISHOP_WHITE;
            } else if (color == ChessColor.BLACK) {
                bishopImage = BISHOP_BLACK;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BishopChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size) {
        super(chessboardPoint, location, color, listener, size);
        initiatePawnImage(color);
    }

    @Override
    public boolean canMoveTo(ChessComponent[][] chessComponents, ChessboardPoint destination) {
        ChessboardPoint source = getChessboardPoint();
        if(source.getX()-destination.getX()==source.getY()-destination.getY()){//如果二者相减相等
            int rowInitial=Math.min(source.getX(),destination.getX());
            int rowFinal=Math.max(source.getX(),destination.getX());
            int colInitial=Math.min(source.getY(),destination.getY());
            int colFinal=Math.max(source.getY(),destination.getY());
            for(int i=rowInitial+1;i<rowFinal;i++){
                if (!(chessComponents[i][i-rowInitial+colInitial] instanceof EmptySlotComponent)) {
                    return false;
                }
            }
        }else if(source.getX()-destination.getX()==destination.getY()-source.getY()){
            int rowInitial=Math.min(source.getX(),destination.getX());
            int rowFinal=Math.max(source.getX(),destination.getX());
            int colInitial=Math.max(source.getY(),destination.getY());
            int colFinal=Math.min(source.getY(),destination.getY());
            for(int i=rowInitial+1;i<rowFinal;i++){
                if (!(chessComponents[i][rowInitial+colInitial-i] instanceof EmptySlotComponent)) {
                    return false;
                }
            }
        } else { // Not on the same row or the same column.
            return false;
        }

        return true;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        g.drawImage(rookImage, 0, 0, getWidth() - 13, getHeight() - 20, this);
        g.drawImage(bishopImage, 0, 0, getWidth() , getHeight(), this);
        g.setColor(Color.BLACK);
        if (isSelected()) { // Highlights the model if selected.
            g.setColor(Color.RED);
            g.drawOval(0, 0, getWidth() , getHeight());
        }
    }
}
