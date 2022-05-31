package model;
import view.ChessboardPoint;
import controller.ClickController;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class PawnChessComponent extends ChessComponent  {

    private static Image PAWN_WHITE;
    private static Image PAWN_BLACK;


    private Image pawnImage;

    public void loadResource() throws IOException {
        if (PAWN_WHITE == null) {
            PAWN_WHITE = ImageIO.read(new File("./images/pawn-white.png"));
        }

        if (PAWN_BLACK == null) {
            PAWN_BLACK = ImageIO.read(new File("./images/pawn-black.png"));
        }
    }

    private void initiatePawnImage(ChessColor color) {
        try {
            loadResource();
            if (color == ChessColor.WHITE) {
               pawnImage = PAWN_WHITE;
            } else if (color == ChessColor.BLACK) {
                pawnImage = PAWN_BLACK;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        sta= 0;
    }

    public PawnChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size) {
        super(chessboardPoint, location, color, listener, size);
        name= 1;
        initiatePawnImage(color);
    }

    @Override
    public boolean canMoveTo(ChessComponent[][] chessComponents, ChessboardPoint destination) {//前提：颜色已经是不相等了
        ChessboardPoint source = getChessboardPoint();//起始点
        ChessColor destinationColor=chessComponents[destination.getX()][destination.getY()].getChessColor();
        System.out.println(count);
        //ChessComponent des= chessComponents[destination.getX()][destination.getY()];
        if(count==0&&chessColor==ChessColor.BLACK){
            if(destinationColor==ChessColor.NONE){
                if(destination.getY()!=source.getY())return false;
                if(destination.getX()==source.getX()+1){
                   // GuoLuBing=false;
                   // canGuoLuBing=null;
                    return  true;
                }
                if(destination.getX()==source.getX()+2&&chessComponents[source.getX()+1][source.getY()].getChessColor()==ChessColor.NONE){
                    //if(destination.getX()==source.getX()+2){

                       // GuoLuBing=true;
                    //canGuoLuBing=this;
                    return true;
                }
                return false;
            }else{
                if(Math.abs(source.getY()-destination.getY())==1&&destination.getX()-source.getX()==1){
//                    GuoLuBing=false;
//                    canGuoLuBing=null;
                    return true;
                }
                return false;
            }


        }else if(count==0&&chessColor==ChessColor.WHITE){
            if(destinationColor==ChessColor.NONE){
                if(destination.getY()!=source.getY())return false;
                if(destination.getX()==source.getX()-1){
//                    GuoLuBing=false;
//                    canGuoLuBing=null;
                    return  true;
                }
                if(destination.getX()==source.getX()-2&&chessComponents[source.getX()-1][source.getY()].getChessColor()==ChessColor.NONE){
//                    GuoLuBing=true;
//                    canGuoLuBing=this;
                    return true;
                }
                return false;
            }else{
                if(Math.abs(source.getY()-destination.getY())==1&&destination.getX()-source.getX()==-1){
//                    GuoLuBing=false;
//                    canGuoLuBing=null;
                    return true;
                }
                return false;
            }


        }else if(count!=0&&chessColor==ChessColor.BLACK){
            if(destinationColor==ChessColor.NONE){
                if(destination.getY()!=source.getY())return false;
                if(destination.getX()==source.getX()+1){
//                    GuoLuBing=false;
//                    canGuoLuBing=null;
                    return  true;
                }
                return false;
            }else{
                if(Math.abs(source.getY()-destination.getY())==1&&destination.getX()-source.getX()==1){
//                    GuoLuBing=false;
//                    canGuoLuBing=null;
                    return true;
                }
                return false;
            }
        } else if(count!=0&&chessColor==ChessColor.WHITE){
            if(destinationColor==ChessColor.NONE){
                if(destination.getY()!=source.getY())return false;
                if(destination.getX()==source.getX()-1){
//                    GuoLuBing=false;
//                    canGuoLuBing=null;
                    return  true;
                }
                return false;
            }else{
                if(Math.abs(source.getY()-destination.getY())==1&&destination.getX()-source.getX()==-1){
//                    GuoLuBing=false;
//                    canGuoLuBing=null;
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        g.drawImage(rookImage, 0, 0, getWidth() - 13, getHeight() - 20, this);
        g.drawImage(pawnImage, 0, 0, getWidth() , getHeight(), this);
        g.setColor(Color.BLACK);
        if (isSelected()) { // Highlights the model if selected.
            g.setColor(Color.RED);
            g.drawOval(0, 0, getWidth() , getHeight());
        }
    }
}
