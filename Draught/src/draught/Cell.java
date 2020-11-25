package draught;

import java.awt.Color;
import java.awt.Graphics2D;

public class Cell {

	private int x;
	private int y;
	private int width;
	private int height;
	private Color color;
	private int cellNum;
	
	public Cell()
	{
		width = MyConsts.CELL_W;
		height = MyConsts.CELL_H;
	}
	
	public void paintCell(Graphics2D g2d)
	{
		g2d.setColor(color);
		g2d.fillRect(x, y, width, height);
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

	public int getCellNum() {
		return cellNum;
	}

	public void setCellNum(int cellNum) {
		this.cellNum = cellNum;
	}
}
