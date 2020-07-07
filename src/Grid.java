import javax.swing.*;
import java.awt.*;

public class Grid extends JPanel implements Cloneable{

    private int[][] tiles;
    int boardSize = 3;
    private boolean running = true;
    private Font f = new Font("Monospaced", Font.BOLD, 48);

    // 0 is empty
    // 10 is an X
    // -10 is an O

    public Grid(){
        this.setSize(600,600);
        this.setOpaque(true);
        this.setBackground(Color.white);
        this.tiles = new int[3][3];
    }

    @Override
    public void paintComponent(Graphics gOld){
        final int offset = 25;
        //super.paintComponent(gOld);
        Graphics2D g = (Graphics2D) gOld;
        g.setBackground(Color.white);
        g.setColor(Color.black);
        int boxWidth = getWidth() / boardSize;
        int boxHeight = getHeight() / boardSize;
        BasicStroke gridStroke = new BasicStroke(1);
        BasicStroke playerStroke = new BasicStroke(5);
        for(int i = 0; i < boardSize; i++){
            for(int j = 0; j < boardSize; j++){
                g.setStroke(gridStroke);
                g.drawRect(i*boxWidth, j * boxHeight, boxWidth, boxHeight);
                if(this.tiles[i][j] == -1){
                    g.setStroke(playerStroke);
                    g.drawOval(i*boxWidth + offset, j * boxHeight + offset, boxWidth - offset * 2, boxHeight - offset * 2);
                }else if (this.tiles[i][j] == 1){
                    g.setStroke(playerStroke);
                    g.drawLine(i*boxWidth + offset, j * boxHeight + offset, (i + 1 )*boxWidth - offset, (j + 1)* boxHeight - offset);
                    g.drawLine((i + 1 )*boxWidth - offset, j * boxHeight + offset, i*boxWidth + offset, (j + 1)* boxHeight - offset);
                }
            }
        }
        if(!running){
            Dimension d = this.getSize();
            g.setFont(f);
            g.setColor(Color.red);
            int outcome = this.winner();
            if(outcome == -1){
                drawCenteredString("O Won!",d.width,d.height,g);
            }else if( outcome == 1){
                drawCenteredString("X Won!",d.width,d.height,g);
            }else{
                drawCenteredString("Tie!",d.width,d.height,g);
            }
        }
    }

    private void drawCenteredString(String s, int w, int h, Graphics g) {
        FontMetrics fm = g.getFontMetrics();
        int x = (w - fm.stringWidth(s)) / 2;
        int y = (fm.getAscent() + (h - (fm.getAscent() + fm.getDescent())) / 2);
        g.drawString(s, x, y);
    }

    public void setSpace(int col, int row, int player){
        if(tiles[row][col] == 0) {
            tiles[row][col] = player;
        }else{
            System.out.println("trying to overwrite");
        }
    }

    public void clear(int col, int row){
        tiles[row][col] = 0;
    }

    public int winner(){
        //1 for x, -1 for o, 99 for tie, 0 for running
        //across
        for(int i= 0; i < 3; i ++){
            if(tiles[i][0] != 0 &&  tiles[i][0] == tiles[i][1] && tiles[i][1] == tiles[i][2]){
                return(tiles[i][0]);
            }
        }
        //updown
        for(int i = 0; i < 3; i ++){
            if(tiles[0][i] != 0 &&  tiles[0][i] == tiles[1][i] && tiles[1][i] == tiles[2][i]){
                return(tiles[0][i]);
            }
        }
        //diagonals
        if(tiles[0][0] != 0 &&  tiles[1][1] == tiles[0][0] && tiles[1][1] == tiles[2][2]){
            return(tiles[0][0]);
        }else if(tiles[0][2] != 0 &&  tiles[1][1] == tiles[0][2] && tiles[1][1] == tiles[2][0]){
            return(tiles[0][2]);
        }
        //checking to see if the game is in progress
        for(int i= 0; i < 3; i ++){
            for(int j= 0; j < 3; j ++){
                if(tiles[i][j] == 0){
                    return 0;
                }
            }
        }
        return 99;
    }

    public int[][] getTiles(){
        return tiles;
    }

    public Grid clone(){
        try {
            return (Grid) super.clone();
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
            return null;
        }
    }

    public void setTiles(int[][] input){
        int size = input.length;
        for(int i = 0; i < size; i ++){
            for(int j = 0; j < size; j ++){
                tiles[i][j]  = input[i][j];
            }
        }
    }

    public void print(){
        for(int i= 0; i < 3; i ++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(tiles[j][i] + " ");
            }
            System.out.println();
        }
        System.out.println("---------------------------");
    }

    public void setRunning(boolean input){
        running = input;
    }

    public boolean getRunning(){
        return running;
    }

}
