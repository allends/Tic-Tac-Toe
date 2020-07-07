public class Computer {

    //by convention, X is maximizing
    private Grid grid;

    public Computer(Grid input){
        this.grid = input;
    }

    public int[] makeMove(Grid input) {
        int score;
        int bestScore = Integer.MAX_VALUE;
        int[] moveLocation = new int[2];
        Grid util = new Grid();
        util.setTiles(input.getTiles());
        int[][] spaces = util.getTiles();
        for(int i= 0; i < 3; i ++){
            for(int j= 0; j < 3; j ++){
                if(spaces[i][j] == 0){
                    util.setSpace(j,i,-1);
                    score = miniMax(util, true);
                    util.clear(j,i);
                    if(score < bestScore){
                        bestScore = score;
                        moveLocation = new int[] {j,i};
                    }
                }
            }
        }
        return moveLocation;
    }

    private int miniMax(Grid input, boolean maximizing){
        int winner = input.winner();
        if(winner != 0 && winner!= 99){
            return winner*10;
        }else if(winner == 99){
            return 1;
        }
        Grid util = new Grid();
        util.setTiles(input.getTiles());
        if(maximizing){
            int bestScore = Integer.MIN_VALUE;
            int score;
            int[][] spaces = util.getTiles();
            for(int i= 0; i < 3; i ++){
                for(int j= 0; j < 3; j ++){
                    if(spaces[i][j] == 0){
                        util.setSpace(j,i,1);
                        score = miniMax(util, false);
                        util.clear(j,i);
                        bestScore = Math.max(score, bestScore);
                    }
                }
            }
            return bestScore;
        }else{
            int bestScore = Integer.MAX_VALUE;
            int score;
            int[][] spaces = util.getTiles();
            for(int i= 0; i < 3; i ++){
                for(int j= 0; j < 3; j ++){
                    if(spaces[i][j] == 0){
                        util.setSpace(j,i,-1);
                        score = miniMax(util, true);
                        util.clear(j,i);
                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
            return bestScore;
        }
    }
}
