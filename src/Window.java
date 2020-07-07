import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Window extends JFrame {

    final int width = 600;
    final int height = 600;
    Grid grid;
    private Computer cpu;


    public Window() {
        grid = new Grid();
        cpu = new Computer(grid);
        panelSetup();
        design();
        setVisible(true);
    }

    private void design(){
        this.setSize(new Dimension(width,height));
        this.setTitle("Tic-Tac-Toe");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setBackground(Color.white);
    }

    private void panelSetup(){
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                setPoint(e.getX(), e.getY());
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        this.add(grid);
    }

    public void setPoint(int x, int y) {
        if(grid.getRunning()) {
            int row = 3 * x / width;
            int col = 3 * y / height;
            grid.setSpace(col, row, 1);
            int[] move = cpu.makeMove(grid);
            grid.setSpace(move[0], move[1], -1);
            grid.repaint();
            if (grid.winner() != 0) {
                grid.setRunning(false);
            }
        }
    }

}
