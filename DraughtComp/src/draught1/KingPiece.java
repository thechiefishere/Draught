package draught1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class KingPiece extends AbsPieceSprite{

	private CellSprite aCell;
	private Board board;
	
	private ArrayList<String> kingKillDirection;
	
	public KingPiece(Board board)
	{
		super(board);
		this.board = board;
		initSprite();
		setColor(getColor());
		kingKillDirection = new ArrayList<String>();
	}
	
	@Override
	public boolean coolMoveToLeft()
	{
		boolean coolMovePossible = false;
		
		int presentPosition = getBoxNum();
		
		if(presentPosition % 10 == 0 || presentPosition < 10)
			coolMovePossible = false;
		else
		{
			int leftPossiblePosition = presentPosition - (MyConsts.MAXCELL + 1);
			aCell = board.getCellFromCellNum(leftPossiblePosition);
			if(!aCell.isFilled())
				return true;
		}
		
		if(presentPosition % 10 == 0 || presentPosition >= 90)
			coolMovePossible = false;
		else
		{
			int leftPossiblePosition = presentPosition + (MyConsts.MAXCELL - 1);
			aCell = board.getCellFromCellNum(leftPossiblePosition);
			if(!aCell.isFilled())
				return true;
		}
		
		return coolMovePossible;
	}
	
	@Override
	public boolean coolMoveToRight()
	{
		boolean coolMovePossible = false;
		
		int presentPosition = getBoxNum();
		
		if(presentPosition % 10 == 9 || presentPosition < 10)
			coolMovePossible = false;
		else
		{
			int rightPossiblePosition = presentPosition - (MyConsts.MAXCELL - 1);
			aCell = board.getCellFromCellNum(rightPossiblePosition);
			if(!aCell.isFilled())
				return true;
		}
		
		if(presentPosition % 10 == 9 || presentPosition >= 90)
			coolMovePossible = false;
		else
		{
			int rightPossiblePosition = presentPosition + (MyConsts.MAXCELL + 1);
			aCell = board.getCellFromCellNum(rightPossiblePosition);
			if(!aCell.isFilled())
				return true;
		}
		
		return coolMovePossible;
	}
	
	@Override
	public boolean killMoveToLeft()
	{
		boolean killMovePossible = false;
		
		int presentPosition = getBoxNum();
		
		if(presentPosition % 10 == 0 || presentPosition % 10 == 1 || presentPosition < 20)
			killMovePossible = false;
		else
		{
			int i = 1;
			int j = 2;
			int posOfPieceToKill;
			int newPosition;
			while((posOfPieceToKill = presentPosition - ((i * MyConsts.MAXCELL) + i)) % 10 > 0 && 
					(newPosition = presentPosition - ((j * MyConsts.MAXCELL) + j)) >= 0)
			{
				aCell = board.getCellFromCellNum(posOfPieceToKill);
				i++;
				j++;
				if(aCell.isFilled())
				{
					if(!(this.getColor().equals(board.getPieceOnCell(aCell).getColor())))
					{
						aCell = board.getCellFromCellNum(newPosition);
						if(aCell.isFilled())
						{
							killMovePossible = false;
							break;
						}
						else if(!aCell.isFilled())
						{
							setComputerKillDirection("UP");
							getKingKillDirection().add("UL");
							return true;
						}
					}
				}
			}
		}
		
		if(presentPosition % 10 == 0 || presentPosition % 10 == 1 || presentPosition >= 80)
			killMovePossible = false;
		else
		{
			int i = 1;
			int j = 2;
			int posOfPieceToKill;
			int newPosition;
			while((posOfPieceToKill = presentPosition + ((i * MyConsts.MAXCELL) - i)) % 10 > 0 && 
					(newPosition = presentPosition + ((j * MyConsts.MAXCELL) - j)) <= 99)
			{
				posOfPieceToKill = presentPosition + ((i * MyConsts.MAXCELL) - i);
				newPosition = presentPosition + ((j * MyConsts.MAXCELL) - j);
				aCell = board.getCellFromCellNum(posOfPieceToKill);
				i++;
				j++;
				if(aCell.isFilled())
				{
					if(!(this.getColor().equals(board.getPieceOnCell(aCell).getColor())))
					{
						aCell = board.getCellFromCellNum(newPosition);
						if(aCell.isFilled())
						{
							killMovePossible = false;
							break;
						}
						else if(!aCell.isFilled())
						{
							setComputerKillDirection("DOWN");
							getKingKillDirection().add("DL");
							return true;
						}
					}
				}
			}
		}
		
		return killMovePossible;
	}
	
	@Override
	public boolean killMoveToRight()
	{
		boolean killMovePossible = false;
		
		int presentPosition = getBoxNum();
		
		if(presentPosition % 10 == 9 || presentPosition % 10 == 8 || presentPosition < 20)
			killMovePossible = false;
		else
		{
			int i = 1;
			int j = 2;
			int posOfPieceToKill;
			int newPosition;
			while((posOfPieceToKill = presentPosition - ((i * MyConsts.MAXCELL) - i)) % 10 < 9 && 
					(newPosition = presentPosition - ((j * MyConsts.MAXCELL) - j)) >= 0)
			{
				posOfPieceToKill = presentPosition - ((i * MyConsts.MAXCELL) - i);
				newPosition = presentPosition - ((j * MyConsts.MAXCELL) - j);
				aCell = board.getCellFromCellNum(posOfPieceToKill);
				i++;
				j++;
				if(aCell.isFilled())
				{
					if(!(this.getColor().equals(board.getPieceOnCell(aCell).getColor())))
					{
						aCell = board.getCellFromCellNum(newPosition);
						if(aCell.isFilled())
						{
							killMovePossible = false;
							break;
						}
						else if(!aCell.isFilled())
						{
							setComputerKillDirection("UP");
							getKingKillDirection().add("UR");
							return true;
						}
					}
				}
			}
		}
		
		if(presentPosition % 10 == 8 || presentPosition % 10 == 9 || presentPosition >= 80)
			killMovePossible = false;
		else
		{
			int i = 1;
			int j = 2;
			int posOfPieceToKill;
			int newPosition;
			while((posOfPieceToKill = presentPosition + ((i * MyConsts.MAXCELL) + i)) % 10 < 9 && 
					(newPosition = presentPosition + ((j * MyConsts.MAXCELL) + j)) <= 99)
			{
				aCell = board.getCellFromCellNum(posOfPieceToKill);
				i++;
				j++;
				if(aCell.isFilled())
				{
					if(!(this.getColor().equals(board.getPieceOnCell(aCell).getColor())))
					{
						aCell = board.getCellFromCellNum(newPosition);
						if(aCell.isFilled())
						{
							killMovePossible = false;
							break;
						}
						else if(!aCell.isFilled())
						{
							setComputerKillDirection("DOWN");
							getKingKillDirection().add("DR");
							return true;
						}
					}
				}
			}
		}
		
		return killMovePossible;
	}
	
	@Override
	public void paintSprite(Graphics2D g2d)
	{
		super.paintSprite(g2d);
		
		g2d.setColor(Color.RED);
		
		g2d.setFont(new Font("Times", Font.BOLD, 20));
		g2d.drawString("K", getX() + 9, getY() + 23);
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public ArrayList<String> getKingKillDirection() {
		return kingKillDirection;
	}

	public void setKingKillDirection(ArrayList<String> kingKillDirection) {
		this.kingKillDirection = kingKillDirection;
	}
}
