package draught1;

import java.awt.Color;
import java.awt.Graphics2D;

public abstract class AbsPieceSprite extends AbsSprite2D{

	private int boxNum;
	private String moveDirection;
	
	private Board board;
	private CellSprite aCell;
	
	public AbsPieceSprite(Board board)
	{
		super();
		this.board = board;
		initSprite();
	}
	
	@Override
	public void initSprite()
	{
		setX(getX());
		setY(getY());
		setWidth(MyConsts.PIECEW);
		setHeight(MyConsts.PIECEH);
		setColor(getColor());
		setActive(false);
		setVisible(true);
		setBoxNum(getBoxNum());
		setMoveDirection(getMoveDirection());
	}
	
	@Override
	public void updateSprite()
	{
		
	}
	
	@Override
	public void paintSprite(Graphics2D g2d)
	{
		g2d.setColor(getColor());
		g2d.fillOval(getX(), getY(), getWidth(), getHeight());
	}
	
	public boolean coolMoveToLeft()
	{
		boolean coolMovePossible = false;
		
		int presentPosition = getBoxNum();
		if(getMoveDirection() == MyConsts.UP)
		{
			if(presentPosition % 10 == 0 || presentPosition < 10)
				coolMovePossible = false;
			else
			{
				int leftPossiblePosition = presentPosition - (MyConsts.MAXCELL + 1);
				aCell = board.getCellFromCellNum(leftPossiblePosition);
				if(!aCell.isFilled())
					coolMovePossible = true;
			}
		}
		else if(getMoveDirection() == MyConsts.DOWN)
		{
			if(presentPosition % 10 == 0 || presentPosition >= 90)
				coolMovePossible = false;
			else
			{
				int leftPossiblePosition = presentPosition + (MyConsts.MAXCELL - 1);
				aCell = board.getCellFromCellNum(leftPossiblePosition);
				if(!aCell.isFilled())
					coolMovePossible = true;
			}
		}
		
		return coolMovePossible;
	}
	
	public boolean coolMoveToRight()
	{
        boolean coolMovePossible = false;
		
		int presentPosition = getBoxNum();
		if(getMoveDirection() == MyConsts.UP)
		{
			if(presentPosition % 10 == 9 || presentPosition < 10)
				coolMovePossible = false;
			else
			{
				int rightPossiblePosition = presentPosition - (MyConsts.MAXCELL - 1);
				aCell = board.getCellFromCellNum(rightPossiblePosition);
				if(!aCell.isFilled())
					coolMovePossible = true;
			}
		}
		else if(getMoveDirection() == MyConsts.DOWN)
		{
			if(presentPosition % 10 == 9 || presentPosition >= 90)
				coolMovePossible = false;
			else
			{
				int rightPossiblePosition = presentPosition + (MyConsts.MAXCELL + 1);
				aCell = board.getCellFromCellNum(rightPossiblePosition);
				if(!aCell.isFilled())
					coolMovePossible = true;
			}
		}
		
		return coolMovePossible;
	}
	
	public boolean killMoveToLeft()
	{
		boolean killMovePossible = false;
		
		int presentPosition = getBoxNum();
		if(presentPosition % 10 == 0 || presentPosition % 10 == 1 || presentPosition < 20)
			killMovePossible = false;
		else
		{
			int posOfPieceToKill = presentPosition - (MyConsts.MAXCELL + 1);
			int newPosition = presentPosition - (MyConsts.MAXCELL * 2 + 2);
			aCell = board.getCellFromCellNum(posOfPieceToKill);
			if(aCell.isFilled())
			{
				if(!(this.getColor().equals(board.getPieceOnCell(aCell).getColor())))
				{
					aCell = board.getCellFromCellNum(newPosition);
					if(!aCell.isFilled())
						return true;
				}
			}
		}
		
		if(presentPosition % 10 == 0 || presentPosition % 10 == 1 || presentPosition >= 80)
			killMovePossible = false;
		else
		{
			int posOfPieceToKill = presentPosition + (MyConsts.MAXCELL - 1);
			int newPosition = presentPosition + (MyConsts.MAXCELL * 2 - 2);
			aCell = board.getCellFromCellNum(posOfPieceToKill);
			if(aCell.isFilled())
			{
				if(!(this.getColor().equals(board.getPieceOnCell(aCell).getColor())))
				{
					aCell = board.getCellFromCellNum(newPosition);
					if(!aCell.isFilled())
						return true;
				}
			}
		}
		
		return killMovePossible;
	}
	
	public boolean killMoveToRight()
	{
		boolean killMovePossible = false;
		
		int presentPosition = getBoxNum();
		if(presentPosition % 10 == 9 || presentPosition % 10 == 8 || presentPosition < 20)
			killMovePossible = false;
		else
		{
			int posOfPieceToKill = presentPosition - (MyConsts.MAXCELL - 1);
			int newPosition = presentPosition - (MyConsts.MAXCELL * 2 - 2);
			aCell = board.getCellFromCellNum(posOfPieceToKill);
			if(aCell.isFilled())
			{
				if(!(this.getColor().equals(board.getPieceOnCell(aCell).getColor())))
				{
					aCell = board.getCellFromCellNum(newPosition);
					if(!aCell.isFilled())
						return true;
				}
			}
		}
		
		if(presentPosition % 10 == 9 || presentPosition % 10 == 8 || presentPosition >= 80)
			killMovePossible = false;
		else
		{
			int posOfPieceToKill = presentPosition + (MyConsts.MAXCELL + 1);
			int newPosition = presentPosition + (MyConsts.MAXCELL * 2 + 2);
			aCell = board.getCellFromCellNum(posOfPieceToKill);
			if(aCell.isFilled())
			{
				if(!(this.getColor().equals(board.getPieceOnCell(aCell).getColor())))
				{
					aCell = board.getCellFromCellNum(newPosition);
					if(!aCell.isFilled())
						return true;
				}
			}
		}
		
		return killMovePossible;
	}

	public int getBoxNum() {
		return boxNum;
	}

	public void setBoxNum(int boxNum) {
		this.boxNum = boxNum;
	}

	public String getMoveDirection() {
		return moveDirection;
	}

	public void setMoveDirection(String moveDirection) {
		this.moveDirection = moveDirection;
	}
}
