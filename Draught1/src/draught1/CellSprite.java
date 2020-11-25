package draught1;

import java.awt.Graphics2D;

public class CellSprite extends AbsSprite2D{

	private int cellNum;
	private boolean filled;
	private boolean validCell;
	
	public CellSprite()
	{
		super();
		initSprite();
	}
	
	@Override
	public void initSprite()
	{
		setWidth(MyConsts.CELL_W);
		setHeight(MyConsts.CELL_H);
		setActive(false);
		setVisible(true);
		setCellNum(getCellNum());
		setFilled(false);
		setColor(getColor());
		setValidCell(true);
	}
	
	@Override
	public void updateSprite()
	{
		
	}
	
	@Override
	public void paintSprite(Graphics2D g2d)
	{
		g2d.setColor(getColor());
		g2d.fillRect(getX(), getY(), getWidth(), getHeight());
	}

	public int getCellNum() {
		return cellNum;
	}

	public void setCellNum(int cellNum) {
		this.cellNum = cellNum;
	}

	public boolean isFilled() {
		return filled;
	}

	public void setFilled(boolean filled) {
		this.filled = filled;
	}

	public boolean isValidCell() {
		return validCell;
	}

	public void setValidCell(boolean validCell) {
		this.validCell = validCell;
	}
}
