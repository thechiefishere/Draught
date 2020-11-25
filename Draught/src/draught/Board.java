package draught;

import java.awt.Color;
import java.awt.Graphics2D;

public class Board {

	private Cell[][] board;
	private int x, y;
	
	public Board()
	{
		initBoard();
	}
	
	private void initBoard()
	{
		board = new Cell[10][10];
		x = 0;
		y = 0;
		int cellNum = 0;
		
		for(int i = 0; i < MyConsts.BOARD_RC; i++)
		{
			y = i * MyConsts.CELL_H;
			for(int j = 0; j < MyConsts.BOARD_RC; j++)
			{
				x = j * MyConsts.CELL_W;
				board[i][j] = new Cell();
				board[i][j].setX(x);
				board[i][j].setY(y);
				
				if(i == j)
					board[i][j].setColor(Color.RED);
				else if(i % 2 == 0)
				{
					if(j % 2 == 0)
						board[i][j].setColor(Color.BLACK);
					else
						board[i][j].setColor(Color.YELLOW);
				}
				else if(i % 2 != 0)
				{
					if(j % 2 == 0)
						board[i][j].setColor(Color.YELLOW);
					else
						board[i][j].setColor(Color.BLACK);
				}
				
				board[i][j].setCellNum(cellNum);
				cellNum++;
			}
		}
	}
	
	public Cell getCellDimension(int cellNum)
	{
		Cell cell;
		int row = cellNum / 10;
		int col = cellNum % 10;
		cell = board[row][col];
		
		return cell;
	}
	
	public void paintBoard(Graphics2D g2d)
	{
		for(int i = 0; i < MyConsts.BOARD_RC; i++)
			for(int j = 0; j < MyConsts.BOARD_RC; j++)
				board[i][j].paintCell(g2d);
	}

	public Cell[][] getBoard() {
		return board;
	}

	public void setBoard(Cell[][] board) {
		this.board = board;
	}
}
