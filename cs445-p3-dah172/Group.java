//David James Hinton (dah172)
import java.util.Arrays;

public class Group{
  private int[][] _board = new int[9][9];
  private int _i;
  private int _j;

  public Group(int[][] board, int i, int j){
    setBoard(board);
    _i = i;
    _j = j;
  }

  public int getI(){
    return _i;
  }

  public int getJ(){
    return _j;
  }

  public int[][] getBoard(){
    return _board;
  }

  public boolean setBoard(int[][] board){
    for(int k = 0; k < 9; k++){
      for(int m = 0; m < 9; m++){
        _board[k][m] = board[k][m];
      }
    }
    return true;
  }
}
