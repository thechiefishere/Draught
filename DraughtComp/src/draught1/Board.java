package draught1;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Board extends AbsSprite{

	private CellSprite[][] board;
	private FirstPlayer firstPlayer;
	private Computer secondPlayer;
	
	private AbsPlayer currPlayer;
	
	public Board()
	{
		super();
		initSprite();
	}
	
	@Override
	public void initSprite()
	{
		board = new CellSprite[10][10];
		int x = MyConsts.INITX;
		int y = MyConsts.INITY;
		int cellNum = 0;
		
		for(int row = 0; row < MyConsts.MAXCELL; row++)
		{
			y = row * MyConsts.CELL_H;
			for(int col = 0; col < MyConsts.MAXCELL; col++)
			{
				x = col * MyConsts.CELL_W;
				board[row][col] = new CellSprite();
				board[row][col].setX(x);
				board[row][col].setY(y);
				board[row][col].setFilled(false);
				board[row][col].setCellNum(cellNum);
				cellNum++;
				
				if(row == col)
					board[row][col].setColor(Color.RED);
				else if(row % 2 == 0)
				{
					if(col % 2 == 0)
						board[row][col].setColor(Color.BLACK);
					else
					{
						board[row][col].setColor(Color.YELLOW);
						board[row][col].setValidCell(false);
					}
				}
				else if(row % 2 != 0)
				{
					if(col % 2 == 0)
					{
						board[row][col].setColor(Color.YELLOW);
						board[row][col].setValidCell(false);
					}
					else
						board[row][col].setColor(Color.BLACK);
				}
			}
		}
		
		setVisible(true);
		setActive(false);
	}
	
	@Override
	public void updateSprite()
	{
		
	}
	
	@Override
	public void paintSprite(Graphics2D g2d)
	{
		for(int i = 0; i < MyConsts.MAXCELL; i++)
			for(int j = 0; j < MyConsts.MAXCELL; j++)
				board[i][j].paintSprite(g2d);
	}
	
	public CellSprite getCellFromCellNum(int cellNum)
	{
		CellSprite cell;
		int row = cellNum / 10;
		int col = cellNum % 10;
		cell = board[row][col];
		
		return cell;
	}
	
	public int getCellColFromX(int x)
	{
		int col = 0;
		col = x / MyConsts.CELL_W;
		
		return col;
	}
	
	public int getCellRowFromY(int y)
	{
		int row = 0;
		row = y / MyConsts.CELL_H;
		
		return row;
	}
	
	public int getCellNumFromXY(int x, int y)
	{
		int cellNum = 0;
		cellNum = board[getCellRowFromY(y)][getCellColFromX(x)].getCellNum();
		
		return cellNum;
	}
	
	public AbsPieceSprite getPieceOnCell(CellSprite aCell)
	{
		ArrayList<AbsPieceSprite> pieces;
		AbsPieceSprite piece = null;
		
		if(aCell.isFilled())
		{
			int cellNum = aCell.getCellNum();
			
			pieces = firstPlayer.getPieces();
			for(AbsPieceSprite element : pieces)
				if(element.getBoxNum() == cellNum)
					return element;
			
			pieces = secondPlayer.getPieces();
			for(AbsPieceSprite element : pieces)
				if(element.getBoxNum() == cellNum)
					return element;
		}
		
		return piece;
	}

	public CellSprite[][] getBoard() {
		return board;
	}

	public void setBoard(CellSprite[][] board) {
		this.board = board;
	}

	public void setFirstPlayer(FirstPlayer firstPlayer) {
		this.firstPlayer = firstPlayer;
	}

	public void setComputer(Computer secondPlayer) {
		this.secondPlayer = secondPlayer;
	}

	public void setCurrPlayer(AbsPlayer currPlayer) {
		this.currPlayer = currPlayer;
	}
}
