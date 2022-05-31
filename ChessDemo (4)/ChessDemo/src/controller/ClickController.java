package controller;


import model.ChessColor;
import model.ChessComponent;
import model.KingChessComponent;
import model.PawnChessComponent;
import view.ChessGameFrame;
import view.Chessboard;
import view.ChessboardPoint;

import javax.swing.*;

public class ClickController {
    private final Chessboard chessboard;
    private ChessComponent first;
   // private ChessComponent canGuoLuBing=null;


    public ClickController(Chessboard chessboard) {
        this.chessboard = chessboard;
    }

    public void onClick(ChessComponent chessComponent) {//点击选定区域，如果
        if (first == null) {//如果当前没有选定区域
            if (handleFirst(chessComponent)) {
                for(int i=0;i<8;i++){//遍历一边棋子
                    for(int j=0;j<8;j++){
                        chessboard.getChessComponents()[i][j].canBeMoveTo=false;
                        chessboard.getChessComponents()[i][j].repaint();
                    }
                }
                chessComponent.setSelected(true);//
                first = chessComponent;
                first.repaint();
                for(int i=0;i<8;i++){//遍历一边棋子
                    for(int j=0;j<8;j++){
                        if (chessboard.getChessComponents()[i][j].getChessColor() != chessboard.getCurrentColor() &&
                                first.canMoveTo(chessboard.getChessComponents(), chessboard.getChessComponents()[i][j].getChessboardPoint())
                        ){
                            chessboard.getChessComponents()[i][j].canBeMoveTo=true;
                            chessboard.getChessComponents()[i][j].repaint();
                        }
                        else {
                            chessboard.getChessComponents()[i][j].canBeMoveTo=false;
                            chessboard.getChessComponents()[i][j].repaint();
                        }
                    }
                }

            }
        } else {
            if (first == chessComponent) { // 再次点击取消选取，当前已经选定这个棋子
                chessComponent.setSelected(false);
                ChessComponent recordFirst = first;
                for (int i = 0; i < 8; i++) {//遍历一边棋子
                    for (int j = 0; j < 8; j++) {
                        chessboard.getChessComponents()[i][j].canBeMoveTo = false;
                        chessboard.getChessComponents()[i][j].repaint();
                    }
                }
                first = null;
                recordFirst.repaint();
            } else if (handleFirst(chessComponent)) {//之后加的,目的在于同颜色换棋
                first.setSelected(false);
                first.repaint();
                for (int i = 0; i < 8; i++) {//遍历一边棋子
                    for (int j = 0; j < 8; j++) {
                        chessboard.getChessComponents()[i][j].canBeMoveTo = false;
                        chessboard.getChessComponents()[i][j].repaint();
                    }
                }
                first = chessComponent;
                first.setSelected(true);
                first.repaint();
                for (int i = 0; i < 8; i++) {//遍历一边棋子
                    for (int j = 0; j < 8; j++) {
                        if (chessboard.getChessComponents()[i][j].getChessColor() != chessboard.getCurrentColor() &&
                                first.canMoveTo(chessboard.getChessComponents(), chessboard.getChessComponents()[i][j].getChessboardPoint())
                        ) {
                            chessboard.getChessComponents()[i][j].canBeMoveTo = true;
                            chessboard.getChessComponents()[i][j].repaint();
                        } else {
                            chessboard.getChessComponents()[i][j].canBeMoveTo = false;
                            chessboard.getChessComponents()[i][j].repaint();
                        }
                    }
                }
            }//颜色不相等在进行以下操作
            else
                /*if (handleSecond(chessComponent)) {//若行棋方不是兵

                    //repaint in swap chess method.
                    chessboard.swapChessComponents(first, chessComponent);
                    chessboard.swapColor();
                    //chessColor = chessboard.getCurrentColor();
                    first.setSelected(false);
                    first = null;

                }*/
                /*else if (first instanceof PawnChessComponent&&ChessComponent.canGuoLuBing == null && handleSecond(chessComponent) ) {//若行棋方是个兵而且当前无过路兵
                    chessboard.swapChessComponents(first, chessComponent);
                    chessboard.swapColor();
                    if (chessComponent.name == 2) {
                        chessboard.gameover(chessComponent.getChessColor());
                    }
                    //ChessComponent.canGuoLuBing=null;
                    first.setSelected(false);
                    first = null;
                }*/

               /* else if (ChessComponent.canGuoLuBing != null && handleSecond(chessComponent) && first.name != 1) {//若一开始有过路兵,而且另一个选定的不是兵
                    chessboard.swapChessComponents(first, chessComponent);
                    chessboard.swapColor();
                    if (chessComponent.name == 2) {
                        chessboard.gameover(chessComponent.getChessColor());
                    }
                    ChessComponent.canGuoLuBing = null;
                    first.GuoLuBing = false;

                    first.setSelected(false);
                    first = null;

                } */
                /*else{
                    if(first.count==2&&chessComponent==ChessComponent.canGuoLuBing&&chessComponent.getX()==first.getX()&&
                            Math.abs(chessComponent.chessboardPoint.getY() - first.chessboardPoint.getY()) == 1){
                        //如果说可以吃 当前棋子步数为2，且目标棋子可以被吃过路兵而且目标棋子位置对
                        int x = chessComponent.getX();
                        int y = chessComponent.getY();
                        chessboard.swapChessComponents(first, chessComponent);
                        first = chessboard.getChessComponents()[x][y];
                        first.count++;
                        if (first.getChessColor() == ChessColor.BLACK) {
                            chessboard.swapChessComponents(first, chessboard.getChessComponents()[first.getX() + 1][first.getY()]);
                        }
                        if (first.getChessColor() == ChessColor.WHITE) {
                            chessboard.swapChessComponents(first, chessboard.getChessComponents()[first.getX() - 1][first.getY()]);
                        }
                        chessboard.swapColor();
                        ChessComponent.canGuoLuBing = null;
                        //chessComponent.GuoLuBing=false;
                        first.setSelected(false);
                       // first.count++;
                        first = null;
                    }

                }*/
                if (handleSecond(chessComponent)) {//
                    chessboard.swapChessComponents(first, chessComponent);
                    chessboard.swapColor();
                    if (chessComponent.name == 2) {
                        chessboard.gameover(chessComponent.getChessColor());
                    }
                    //ChessComponent.canGuoLuBing=null;
                    first.setSelected(false);
                    first = null;

                   /* if (ChessComponent.canGuoLuBing != null && first.name == 1 && first.count == 0 && handleSecond(chessComponent)) {//若一开始有过路兵且走子为首发兵
                    chessboard.swapChessComponents(first, chessComponent);//备注：需要再handle前面把他修改成可以被吃的过路兵
                    chessboard.swapColor();
                    if (chessComponent.name == 2) {
                        chessboard.gameover(chessComponent.getChessColor());
                    }
                    first.setSelected(false);
                    first = null;
                } else if (ChessComponent.canGuoLuBing != null && first.name == 1 && first.count != 0) {//行棋方是一个不是首发兵的兵
                    if (chessComponent.count == 1 && chessComponent.GuoLuBing == true && chessComponent.chessboardPoint.getX() == first.chessboardPoint.getX() && Math.abs(chessComponent.chessboardPoint.getY() - first.chessboardPoint.getY()) == 1) {
                        //如果说可以吃
                        int x = chessComponent.getX();
                        int y = chessComponent.getY();
                        chessboard.swapChessComponents(first, chessComponent);
                        first = chessboard.getChessComponents()[x][y];
                        first.count++;
                        if (first.getChessColor() == ChessColor.BLACK) {
                            chessboard.swapChessComponents(first, chessboard.getChessComponents()[first.getX() + 1][first.getY()]);
                        }
                        if (first.getChessColor() == ChessColor.WHITE) {
                            chessboard.swapChessComponents(first, chessboard.getChessComponents()[first.getX() - 1][first.getY()]);
                        }
                        chessboard.swapColor();
                        ChessComponent.canGuoLuBing = null;
                        chessComponent.GuoLuBing=false;
                        first.setSelected(false);
                        first = null;
                    }

                }*/
            for (int i = 0; i < 8; i++) {//遍历一边棋子
                for (int j = 0; j < 8; j++) {
                    chessboard.getChessComponents()[i][j].canBeMoveTo = false;
                    chessboard.getChessComponents()[i][j].repaint();
                }
            }
            //遍历一遍，检测王有没有攻击
            int countCanMoveTo = 0;
            int countking = 0;
            ChessColor antiColor = ChessColor.NONE;
            if (chessboard.getCurrentColor() == ChessColor.BLACK) antiColor = ChessColor.WHITE;
            if (chessboard.getCurrentColor() == ChessColor.WHITE) antiColor = ChessColor.BLACK;

            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {//外层遍历每个棋子
                    if (chessboard.getChessComponents()[i][j].getChessColor() == antiColor) {//如果和交换前的一样
                        for (int k = 0; k < 8; k++) {
                            for (int l = 0; i < 8; l++) {
                                if (chessboard.getChessComponents()[k][l].getChessColor() != antiColor &&
                                        chessboard.getChessComponents()[i][j].canMoveTo(chessboard.getChessComponents(), chessboard.getChessComponents()[k][l].getChessboardPoint())) {//目标棋子颜色不相同而且能够移动到

                                    if (chessboard.getChessComponents()[k][l] instanceof KingChessComponent) {
                                        countking = 1;
                                        break;
                                    }
                                    //if(countking==1)break;
                                }
                                if(countking==1)break;
                            }
                            if(countking==1)break;
                        }
                        if(countking==1)break;
                    }if(countking==1)break;
                }if(countking==1)break;
            }
            if (countking == 1) chessboard.warning();
            countking=0;
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {//外层遍历每个棋子
                    if (chessboard.getChessComponents()[i][j].getChessColor() == chessboard.getCurrentColor()) {//如果和交换前的一样
                        for (int k = 0; k < 8; k++) {
                            for (int l = 0; i < 8; l++) {
                                if (
                                        chessboard.getChessComponents()[k][l].getChessColor() != chessboard.getCurrentColor() &&
                                        chessboard.getChessComponents()[i][j].canMoveTo(chessboard.getChessComponents(), chessboard.getChessComponents()[k][l].getChessboardPoint())) {//目标棋子颜色不相同而且能够移动到
                                    countCanMoveTo++;
                                    if (chessboard.getChessComponents()[k][l] instanceof KingChessComponent) {
                                        //countking=1;
                                    }
                                }
                            }
                        }
                    }


                }
            }

            if (countCanMoveTo == 0) {
                chessboard.Draw();
            }
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
        /*if(first.name== 1){//如果是兵
            ChessboardPoint fp= first.getChessboardPoint();
            ChessboardPoint sp= chessComponent.getChessboardPoint();
            int fx= fp.y, fy= fp.x;
            int sx= sp.y, sy= sp.x;
            if(first.count== 0){
                if(first.getChessColor()== ChessColor.BLACK) {
                    if(fy!=sy-1&&fy!=sy-2){
                        return false;
                    }
                    if(chessComponent.getChessColor()== ChessColor.WHITE){//对面是白子而且在指定范围内部
                        if(fx== sx){
                            return false;
                        }
                        if(fx+1==sx||fx-1==sx){
                            if(fy==sy-1){
                                first.count++;
                                first.GuoLuBing=false;
                                canGuoLuBing=null;
                                return true;
                            }
                            else return  false;
                        }
                    }
                    else if(fx==sx){
                        if(fy==sy-2){
                            canGuoLuBing=first;//他可以被过路兵吃子
                           first.GuoLuBing=true;
                           first.count++;
                           return true;
                        }
                        else {
                            first.GuoLuBing=false;
                            canGuoLuBing=null;
                        }
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
                            if(fy==sy+1){
                                first.count++;
                                return true;
                            }
                            else return false;
                        }
                    }
                    else if(fx==sx){
                        if(fy==sy+2){
                            canGuoLuBing=first;
                            first.GuoLuBing=true;
                            first.count++;
                            return true;
                        }
                        else {
                            first.GuoLuBing=false;
                            canGuoLuBing=null;
                            first.count++;
                            return true;
                        }
                        //first.count++;
                       // return true;
                    }
                    else return false;
                }
            }
            else if(first.getChessColor()== ChessColor.BLACK){
                if(fy!= sy- 1){
                    return false;
                }
                if(chessComponent.getChessColor()== ChessColor.WHITE){
                    if(fx== sx){
                        return false;
                    }
                    if(fx+1==sx||fx-1==sx){
                        first.GuoLuBing=false;
                        canGuoLuBing=null;
                        first.count++;

                        return true;
                    }
                }
                if(fx== sx){
                    first.count++;
                    first.GuoLuBing=false;
                    canGuoLuBing=null;
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
                        first.GuoLuBing=false;
                       canGuoLuBing=null;
                        return true;
                    }
                }
                if(fx== sx){
                    first.count++;
                    first.GuoLuBing=false;
                    canGuoLuBing=null;
                    return true;
                }
            }
            return false;
        }*/
        if (chessComponent.getChessColor() != chessboard.getCurrentColor() &&
                first.canMoveTo(chessboard.getChessComponents(), chessComponent.getChessboardPoint())
        ) {
            first.count++;
            //System.out.println(first.count);
            return true;
        }
        else return false;

    }
    public void mouseMovein(ChessComponent chessComponent) {
        chessboard.changeChessComponent(chessComponent);//改变棋子状态颜色，具体点chessboard
    }
    public void mouseMoveout(ChessComponent chessComponent) {
        chessboard.returnChessComponent(chessComponent);//改变棋子状态颜色，具体点chessboard
    }

}
