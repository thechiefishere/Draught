package draught;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Templatepc {

	private Board board;
	private ArrayList<Piece> pieces;
	private int x, y;
	private Color color;
	private int incX;
	private int incY;
	
	public Templatepc(Board board)
	{
		this.board = board;
		initPlayerPiece();
		color = Color.GREEN;
	}
	
	public void initPlayerPiece()
	{
		pieces = new ArrayList<>();
		Piece piece;
	
		for(int i = 0; i < MyConsts.NUM_PIECE; i++)
		{
			if(i < 5)
			{
				y = 10 + (incY * MyConsts.CELL_H);
				x = 10 + (incX * MyConsts.CELL_W * 2);
				
				incX++;
				if(incX == 5)
				{
					incX = 0;
					incY++;
				}
			}
			else if(i < 10)
			{
				y = 10 + (incY * MyConsts.CELL_H);
				x = MyConsts.CELL_W + (10 + (incX * MyConsts.CELL_W * 2));
				
				incX++;
				if(incX == 5)
				{
					incX = 0;
					incY++;
				}
			}
			else if(i < 15)
			{
				y = 10 + (incY * MyConsts.CELL_H);
				x = 10 + (incX * MyConsts.CELL_W * 2);
				
				incX++;
				if(incX == 5)
				{
					incX = 0;
					incY++;
				}
			}
			else if(i < 20)
			{
				y = 10 + (incY * MyConsts.CELL_H);
				x = MyConsts.CELL_W + (10 + (incX * MyConsts.CELL_W * 2));
				
				incX++;
				if(incX == 5)
				{
					incX = 0;
					incY++;
				}
			}
			
			piece = new Piece();
			piece.setColor(Color.GREEN);
			piece.setX(x);
			piece.setY(y);
			piece.setBoxNum(initPieceBoardNum(x, y));
			pieces.add(piece);
		}
	}
	
	private int initPieceBoardNum(int x, int y)
	{
		int boxnum = 0;
		Cell cell;
		for(int i = 0; i < MyConsts.NUM_BOXES; i++)
		{
			cell = board.getCellDimension(i);
			if(cell.getX() == (x - 10) && cell.getY() == (y - 10))
				return i;
		}
		
		return boxnum;
	}
	
	public void paintPlayerPiece(Graphics2D g2d)
	{
		for(int i = 0; i < MyConsts.NUM_PIECE; i++)
			g2d.fillOval(pieces.get(i).getX(), pieces.get(i).getY(), 
					MyConsts.PIECE_W, MyConsts.PIECE_H);
	}
	
	public int getIncX() 
	{
		return incX;
	}

	public void setIncX(int incX)
	{
		this.incX = incX;
	}

	public int getIncY() 
	{
		return incY;
	}

	public void setIncY(int incY) 
	{
		this.incY = incY;
	}

	public ArrayList<Piece> getPieces() {
		return pieces;
	}

	public void setPieces(ArrayList<Piece> pieces) {
		this.pieces = pieces;
	}
}
