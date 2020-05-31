import java.util.*;
import java.lang.*;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Sudoku {
	static boolean isFullSolution(int[][] board) {
		//if we should not reject the board, then if there are any 0's it is not the full solution
		if(reject(board))
			return false;
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				if(board[i][j] == 0)
					return false;
			}
		}
		return true;
	}

	static boolean reject(int[][] board) {
		//if the board passes the column and row checks then we SHOULD NOT reject the board.
		if(checkRows(board) && checkColumns(board) && checkCube(board))
			return false;
		return true;
	}

	static boolean checkRows(int[][] board){
		//returns false if the row does not pass the check
		int[] checkingArray = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				if(j == 0 && i != 0){
					System.arraycopy(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, 0, checkingArray, 0, checkingArray.length);
				}
				for(int k = 0; k < 9; k++){
					if(board[i][j] == checkingArray[k]){//if there is more than one instance of the same number
						checkingArray[k] = -1;
						break;
					}
					if(k == 8 && board[i][j] != 0){return false;}
				}
			}
		}
		return true;
	}

	static boolean checkColumns(int[][] board){
		//returns false if the column does not pass the check
		int[] checkingArray = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		for(int j = 0; j < 9; j++){
			for(int i = 0; i < 9; i++){
				if(i == 0 && j != 0){
					System.arraycopy(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, 0, checkingArray, 0, checkingArray.length);
				}
				for(int k = 0; k < 9; k++){
					if(board[i][j] == checkingArray[k]){//if there is more than one instance of the same number
						checkingArray[k] = -1;
						break;
					}
					if(k == 8 && board[i][j] != 0){return false;}
				}
			}
		}
		return true;
	}

	static boolean checkCube(int[][] board){
		int[] checkingArray1 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		int[] checkingArray2 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		int[] checkingArray3 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		int[] checkingArray4 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		int[] checkingArray5 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		int[] checkingArray6 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		int[] checkingArray7 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		int[] checkingArray8 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		int[] checkingArray9 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		for(int j = 0; j < 9; j++){
			for(int i = 0; i < 9; i++){
				if(i < 3){
					if(j < 3){
						for(int k = 0; k < 9; k++){
							if(board[i][j] == checkingArray1[k]){//if there is more than one instance of the same number
								checkingArray1[k] = -1;
								break;
							}
							if(k == 8 && board[i][j] != 0){return false;}
						}
					}
					if(j < 6 && j > 2){
						for(int k = 0; k < 9; k++){
							if(board[i][j] == checkingArray4[k]){//if there is more than one instance of the same number
								checkingArray4[k] = -1;
								break;
							}
							if(k == 8 && board[i][j] != 0){return false;}
						}
					}
					if(j < 9 && j > 5){
						for(int k = 0; k < 9; k++){
							if(board[i][j] == checkingArray7[k]){//if there is more than one instance of the same number
								checkingArray7[k] = -1;
								break;
							}
							if(k == 8 && board[i][j] != 0){return false;}
						}
					}
				}
				if(i < 6 && i > 2){
					if(j < 3){
						for(int k = 0; k < 9; k++){
							if(board[i][j] == checkingArray2[k]){//if there is more than one instance of the same number
								checkingArray2[k] = -1;
								break;
							}
							if(k == 8 && board[i][j] != 0){return false;}
						}
					}
					if(j < 6 && j > 2){
						for(int k = 0; k < 9; k++){
							if(board[i][j] == checkingArray5[k]){//if there is more than one instance of the same number
								checkingArray5[k] = -1;
								break;
							}
							if(k == 8 && board[i][j] != 0){return false;}
						}
					}
					if(j < 9 && j > 5){
						for(int k = 0; k < 9; k++){
							if(board[i][j] == checkingArray8[k]){//if there is more than one instance of the same number
								checkingArray8[k] = -1;
								break;
							}
							if(k == 8 && board[i][j] != 0){return false;}
						}
					}
				}
				if(i < 9 && i > 5){
					if(j < 3){
						for(int k = 0; k < 9; k++){
							if(board[i][j] == checkingArray3[k]){//if there is more than one instance of the same number
								checkingArray3[k] = -1;
								break;
							}
							if(k == 8 && board[i][j] != 0){return false;}
						}
					}
					if(j < 6 && j > 2){
						for(int k = 0; k < 9; k++){
							if(board[i][j] == checkingArray6[k]){//if there is more than one instance of the same number
								checkingArray6[k] = -1;
								break;
							}
							if(k == 8 && board[i][j] != 0){return false;}
						}
					}
					if(j < 9 && j > 5){
						for(int k = 0; k < 9; k++){
							if(board[i][j] == checkingArray9[k]){//if there is more than one instance of the same number
								checkingArray9[k] = -1;
								break;
							}
							if(k == 8 && board[i][j] != 0){return false;}
						}
					}
				}
			}
		}
		return true;
	}

	static Group extend(int[][] board) {
		int[][] newBoard = new int[9][9];
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				newBoard[i][j] = board[i][j];
			}
		}
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				if(newBoard[i][j] == 0){
						newBoard[i][j] = 1;
						Group collect = new Group(newBoard, i, j);
						return collect;
				}
			}
		}
		return null;
	}

	static int[][] next(Group attempt) {
		int[][] board = attempt.getBoard();
		int i = attempt.getI();
		int j = attempt.getJ();
		int temp = board[i][j];
		if(temp + 1 == 10){
			return null;
		}
		board[i][j] = temp+1;
		return board;
	}

	static void testIsFullSolution() {
		System.out.println("TESTING FULL SOLTUION:");
		System.out.println("#############################################");
		for(int i = 0; i < 5; i++){
			System.out.println("testing: testSolution"+i);
			int[][] board = readBoard("testSolution"+i);
				printBoard(board);
			boolean result = isFullSolution(board);
			System.out.println(result+"\n\n");
		}
	}

	static void testReject() {
		System.out.println("TESTING REJECT:");
		System.out.println("#############################################");
		for(int i = 0; i < 5; i++){
			System.out.println("testing: testSolution"+i);
			int[][] board = readBoard("testSolution"+i);
			printBoard(board);
			boolean result = reject(board);
			System.out.println(result+"\n\n");
		}
	}

	static void testExtend() {
		System.out.println("TESTING EXTEND:");
		System.out.println("#############################################");
		for(int i = 0; i < 4; i++){
			System.out.println("testing: testExtend"+i);
			int[][] board = readBoard("testExtend"+i);
			printBoard(board);
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>\n\n");
			Group result = extend(board);
			if(result != null)
				printBoard(result.getBoard());
			else
				printBoard(null);
			System.out.println("\n\n");
		}
	}

	static void testNext() {
		System.out.println("TESTING NEXT:");
		System.out.println("#############################################");
		for(int i = 0; i < 3; i++){
			System.out.println("testing: testNext"+i);
			int[][] board = readBoard("testNext"+i);
			printBoard(board);
			Group test = new Group(board, i, i+1);
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>\n\n");
			int[][] result = next(test);
			printBoard(result);
			System.out.println("\n\n");
		}
	}

	static void printBoard(int[][] board) {
		if(board == null) {
			System.out.println("No assignment");
			return;
		}
		for(int i = 0; i < 9; i++) {
			if(i == 3 || i == 6) {
				System.out.println("----+-----+----");
			}
			for(int j = 0; j < 9; j++) {
				if(j == 2 || j == 5) {
					System.out.print(board[i][j] + " | ");
				} else {
					System.out.print(board[i][j]);
				}
			}
			System.out.print("\n");
		}
	}

	static int[][] readBoard(String filename) {
		List<String> lines = null;
		try {
			lines = Files.readAllLines(Paths.get(filename), Charset.defaultCharset());
		} catch (IOException e) {
			return null;
		}
		int[][] board = new int[9][9];
		int val = 0;
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				try {
					val = Integer.parseInt(Character.toString(lines.get(i).charAt(j)));
				} catch (Exception e) {
					val = 0;
				}
				board[i][j] = val;
			}
		}
		return board;
	}

	static int[][] solve(int[][] board) {
		if(reject(board)) return null;
		if(isFullSolution(board)) return board;
		Group attempt = extend(board);
		while (attempt != null) {
			int[][] solution = solve(attempt.getBoard());
			if(solution != null) return solution;
			int[][] temp = next(attempt);
			if(temp == null){
				attempt = null;
			}
			else{
				attempt.setBoard(temp);
			}
		}
		return null;
	}

	public static void main(String[] args) {
		if(args[0].equals("-t")) {
			testIsFullSolution();
			testReject();
			testExtend();
			testNext();
		} else {
			int[][] board = readBoard(args[0]);
			printBoard(board);
			System.out.println("\nSolution:");
			printBoard(solve(board));
		}
	}
}
