package draught;

import java.awt.Color;
import java.awt.Graphics2D;

public class Piece {

	private int x;
	private int y;
	private int width;
	private int height;
	private Color color;
	private int boxNum;
	
	public Piece()
	{
		width = MyConsts.PIECE_W;
		height = MyConsts.PIECE_H;
	}
	
	public void paintPiece(Graphics2D g2d)
	{
		g2d.setColor(Color.GREEN);
		g2d.fillOval(x, y, width, height);
	}

	public int getX() 
	{
		return x;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public int getY()
	{
		return y;
	}

	public void setY(int y) 
	{
		this.y = y;
	}

	public Color getColor() 
	{
		return color;
	}

	public void setColor(Color color) 
	{
		this.color = color;
	}

	public int getBoxNum() 
	{
		return boxNum;
	}

	public void setBoxNum(int boxNum) 
	{
		this.boxNum = boxNum;
	}
}
