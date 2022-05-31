package controller;


import model.ChessColor;
import model.ChessComponent;
import view.ChessGameFrame;
import view.Chessboard;
import view.ChessboardPoint;

import javax.swing.*;

public class ClickController {
    private final Chessboard chessboard;
    private ChessComponent first;

    public ClickController(Chessboard chessboard) {
        this.chessboard = chessboard;
    }

    public void onClick(ChessComponent chessComponent) {
        if (first == null) {
            if (handleFirst(chessComponent)) {
                chessComponent.setSelected(true);
                first = chessComponent;
                first.repaint();
            }
        } else {
            if (first == chessComponent) { // 再次点击取消选取
                chessComponent.setSelected(false);
                ChessComponent recordFirst = first;
                first = null;
                recordFirst.repaint();
            } else if (handleSecond(chessComponent)) {
                //repaint in swap chess method.
                chessboard.swapChessComponents(first, chessComponent);
                chessboard.swapColor();
                if(chessComponent.name== 2){
                    chessboard.gameover(chessComponent.getChessColor() );
                }
                first.setSelected(false);
                first = null;
            }
        }
    }

    /**
     * @param chessComponent 目标选取的棋子
     * @return 目标选取的棋子是否与棋盘记录的当前行棋方颜色相同
     */

    private boolean handleFirst(ChessComponent chessComponent) {
        return chessComponent.getChessColor() == chessboard.getCurrentColor();
    }

    /**
     * @param chessComponent first棋子目标移动到的棋子second
     * @return first棋子是否能够移动到second棋子位置
     */

    private boolean handleSecond(ChessComponent chessComponent) {
        if(chessComponent.getChessColor() == chessboard.getCurrentColor() ){
            return false;
        }
        if(first.name== 1){//如果是兵
            ChessboardPoint fp= first.getChessboardPoint();
            ChessboardPoint sp= chessComponent.getChessboardPoint();
            int fx= fp.y, fy= fp.x;
            int sx= sp.y, sy= sp.x;
            if(first.count== 0){
                if(first.getChessColor()== ChessColor.BLACK) {
                    if(fy!=sy-1&&fy!=sy-2){
                        return false;
                    }
                    if(chessComponent.getChessColor()== ChessColor.WHITE){
                        if(fx== sx){
                            return false;
                        }
                        if(fx+1==sx||fx-1==sx){
                            first.count++;
                            return true;
                        }
                    }
                    else if(fx==sx){
                        first.count++;
                        return true;
                    }
                    else {
                        return false;
                    }


                }
                if(first.getChessColor()== ChessColor.WHITE) {
                    if(fy!=sy+1&&fy!=sy+2){
                        return false;
                    }
                    if(chessComponent.getChessColor()== ChessColor.BLACK){
                        if(fx== sx){
                            return false;
                        }
                        if(fx+1==sx||fx-1==sx){
                            first.count++;
                            return true;
                        }
                    }
                    else if(fx==sx){
                        first.count++;
                        return true;
                    }
                    else return false;
                }
            }
            if(first.getChessColor()== ChessColor.BLACK){
                if(fy!= sy- 1){
                    return false;
                }
                if(chessComponent.getChessColor()== ChessColor.WHITE){
                    if(fx== sx){
                        return false;
                    }
                    if(fx+1==sx||fx-1==sx){
                        first.count++;

                        return true;
                    }
                }
                if(fx== sx){
                    first.count++;
                    return true;
                }

            }
            if(first.getChessColor()== ChessColor.WHITE){
                if(fy!= sy+ 1){
                    return false;
                }
                if(chessComponent.getChessColor()== ChessColor.BLACK){
                    if(fx== sx){
                        return false;
                    }
                    if(fx+1==sx||fx-1==sx){
                        first.count++;
                        return true;
                    }
                }
                if(fx== sx){
                    first.count++;
                    return true;
                }
            }
            return false;
        }
        if (chessComponent.getChessColor() != chessboard.getCurrentColor() &&
                first.canMoveTo(chessboard.getChessComponents(), chessComponent.getChessboardPoint())
        ) {
            first.count++;
            return true;

        }
        else return false;

    }
}
