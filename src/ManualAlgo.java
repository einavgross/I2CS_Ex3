
import exe.ex3.game.PacManAlgo;
import exe.ex3.game.PacmanGame;

public class ManualAlgo implements PacManAlgo{
    public ManualAlgo() {;}
    @Override
    public String getInfo() {
        return "This is a manual algorithm for manual controlling the PacMan using w,a,x,d (up,left,down,right).";
    }

    @Override
    public int move(PacmanGame game) {
        int ans = PacmanGame.ERR;
        Character cmd = Ex3Main.getCMD();
            if (cmd != null) {
                if (cmd == 'w' || cmd == '\'') {ans = PacmanGame.UP;}
                if (cmd == 's' || cmd == 'ד') {ans = PacmanGame.DOWN;}
                if (cmd == 'a' || cmd == 'ש') {ans = PacmanGame.LEFT;}
                if (cmd == 'd' || cmd == 'ג') {ans = PacmanGame.RIGHT;}
            }
            return  ans;
    }
}
