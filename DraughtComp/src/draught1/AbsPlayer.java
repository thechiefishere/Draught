package draught1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public abstract class AbsPlayer {

	private ArrayList<AbsPieceSprite> pieces;
	private int pieceLeft;
	private Board board;
	private Color color;
	
	private ArrayList<AbsPieceSprite> killMovers;
	private ArrayList<AbsPieceSprite> coolMovers;
	private int x, y;
	private int incX;
	private int incY;
	private String name;
	
	private String pieceMoveDirection;
	private AbsPieceSprite pieceToMove;
	private GlowBox glowBox;
	private ArrayList<GlowBox> allGlowBoxes;
	private Graphics2D g2d;
	private GameCanvas gameCanvas;
	
	private String multiKillKingLastMove;
	
	public AbsPlayer(Board board)
	{
		pieceLeft = MyConsts.NUM_PIECE;
		this.board = board;
		killMovers = new ArrayList<AbsPieceSprite>();
		coolMovers = new ArrayList<AbsPieceSprite>();
		allGlowBoxes = new ArrayList<GlowBox>();
	}
	
	public void initPlayerPiece()
	{
		pieces = new ArrayList<AbsPieceSprite>();
		AbsPieceSprite piece;
	
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
			
			piece = new RegularPiece(board);
			piece.setColor(getColor());
			piece.setX(x);
			piece.setY(y);
			piece.setBoxNum(initPieceBoardNum(x, y));
			piece.setMoveDirection(getPieceMoveDirection());
			pieces.add(piece);
		}
	}
	
	public int fillKillMovers()
	{
		int toReturn = 0;
		killMovers.clear();
		for(AbsPieceSprite element : pieces)
			if(element.killMoveToLeft() || element.killMoveToRight())
				killMovers.add(element);
		
		if(killMovers.size() > 0)
			toReturn = 1;
		
		return toReturn;
	}
	
	public int fillCoolMovers()
	{
		int toReturn = 0;
		coolMovers.clear();
		for(AbsPieceSprite element : pieces)
			if(element.coolMoveToLeft() || element.coolMoveToRight())
				coolMovers.add(element);
		
		if(coolMovers.size() > 0)
			toReturn = 1;
		
		return toReturn;
	}
	
	private int initPieceBoardNum(int x, int y)
	{
		int boxnum = 0;
		CellSprite cell;
		for(int i = 0; i < MyConsts.NUM_BOXES; i++)
		{
			cell = board.getCellFromCellNum(i);
			if(cell.getX() == (x - 10) && cell.getY() == (y - 10))
			{
				cell.setFilled(true);
				return i;
			}
		}
		
		return boxnum;
	}
	
	public boolean samePieceMultipleKill(AbsPieceSprite pieceToMove)
	{
		boolean yesItIs = false;
		fillKillMovers();
		
		for(AbsPieceSprite element : killMovers)
		{
			if(element.equals(pieceToMove))
			{
				if(pieceToMove instanceof RegularPiece)
					return true;
				else if(pieceToMove instanceof KingPiece)
				{
					int size = ((KingPiece) pieceToMove).getKingKillDirection().size();
					String newMove = ((KingPiece) pieceToMove).getKingKillDirection().get(size - 1);
					String pastMove = getMultiKillKingLastMove();
					
					if(((newMove.equals("DR")) && (pastMove.equals("UL"))) || ((newMove.equals("UL")) && (pastMove.equals("DR"))))
						return false;
					else if(((newMove.equals("UR")) && (pastMove.equals("DL"))) || ((newMove.equals("DL")) && (pastMove.equals("UR"))))
						return false;
					else
						return true;
				}
			}
		}
		
		return yesItIs;
	}
	
	public void paintPlayerPiece(Graphics2D g2d)
	{
		g2d.setColor(getColor());
		for(int i = 0; i < pieces.size(); i++)
			pieces.get(i).paintSprite(g2d);
	}
	
	public void initGlowBoxes(CellSprite aCell)
	{
		glowBox = new GlowBox();
		glowBox.setX(aCell.getX());
		glowBox.setY(aCell.getY());
		glowBox.setVisible(true);
		allGlowBoxes.add(glowBox);
	}
	
	public void glowPieceThatCanMoveOrKill()
	{
		if(fillKillMovers() == 1)
		{
			allGlowBoxes.clear();
			for(int i = 0; i < killMovers.size(); i++)
			{
				int piecePos = killMovers.get(i).getBoxNum();
				CellSprite aCell = board.getCellFromCellNum(piecePos);
				initGlowBoxes(aCell);
			}
			for(GlowBox element : allGlowBoxes)
				element.paintSprite(g2d);
		}
		else if(fillKillMovers() == 0 && fillCoolMovers() == 1)
		{
			allGlowBoxes.clear();
			for(int i = 0; i < coolMovers.size(); i++)
			{
				int piecePos = coolMovers.get(i).getBoxNum();
				CellSprite aCell = board.getCellFromCellNum(piecePos);
				initGlowBoxes(aCell);
			}
			for(GlowBox element : allGlowBoxes)
				element.paintSprite(g2d);
		}
	}
	
	public boolean checkCoolMove(int x, int y)
	{
		boolean success = false;
		int cellNum = board.getCellNumFromXY(x, y);
		CellSprite aCell = board.getCellFromCellNum(cellNum);
		int presentBoxNum = pieceToMove.getBoxNum();
		
		if(pieceToMove instanceof RegularPiece)
		{
			if(!aCell.isFilled() && pieceToMove.getMoveDirection() == MyConsts.UP)
			{
				if((presentBoxNum - 9 == cellNum) || (presentBoxNum - 11 == cellNum))
				{
					success = true;
					pieceToMove.setBoxNum(cellNum);
					board.getCellFromCellNum(presentBoxNum).setFilled(false);
					aCell.setFilled(true);
				}
			}
			else if(!aCell.isFilled() && pieceToMove.getMoveDirection() == MyConsts.DOWN)
			{
				if((presentBoxNum + 9 == cellNum) || (presentBoxNum + 11 == cellNum))
				{
					success = true;
					pieceToMove.setBoxNum(cellNum);
					board.getCellFromCellNum(presentBoxNum).setFilled(false);
					aCell.setFilled(true);
				}
			}
		}
		else if(pieceToMove instanceof KingPiece)
		{
			int breakOrContinue = 0;
			if(aCell.isValidCell())
			{
				if(presentBoxNum > cellNum)
				{
					int dec;
					if(presentBoxNum % 10 < cellNum % 10)
						dec = 9;
					else
						dec = 11;
					
					int reducer = presentBoxNum - dec;
					while(reducer >= cellNum)
					{
						aCell = board.getCellFromCellNum(reducer);
						if(!aCell.isFilled())
						{
							breakOrContinue = 1;
							reducer -= dec;
							continue;
						}
						else
						{
							breakOrContinue = 0;
							break;
						}
					}
					if(breakOrContinue == 1)
					{
						success = true;
						pieceToMove.setBoxNum(cellNum);
						board.getCellFromCellNum(presentBoxNum).setFilled(false);
						aCell.setFilled(true);
					}
				}
				else if(presentBoxNum < cellNum)
				{
					int inc;
					if(presentBoxNum % 10 > cellNum % 10)
						inc = 9;
					else
						inc = 11;
					int increase = presentBoxNum + inc;
					while(increase <= cellNum)
					{
						aCell = board.getCellFromCellNum(increase);
						if(!aCell.isFilled())
						{
							breakOrContinue = 1;
							increase += inc;
							continue;
						}
						else
						{
							breakOrContinue = 0;
							break;
						}
					}
					if(breakOrContinue == 1)
					{
						success = true;
						pieceToMove.setBoxNum(cellNum);
						board.getCellFromCellNum(presentBoxNum).setFilled(false);
						aCell.setFilled(true);
					}
				}
			}
		}
		
		return success;
	}
	
	public boolean checkKillMove(int x, int y)
	{
		boolean success = false;
		int cellNum = board.getCellNumFromXY(x, y);
		CellSprite aCell = board.getCellFromCellNum(cellNum);
		int presentBoxNum = pieceToMove.getBoxNum();
		
		if(pieceToMove instanceof RegularPiece)
		{
			if(!aCell.isFilled())
			{
				if((presentBoxNum - 18 == cellNum) || (presentBoxNum - 22 == cellNum))
				{
					int dec = 0;
					if(presentBoxNum - 18 == cellNum)
						dec = 9;
					else if(presentBoxNum - 22 == cellNum)
						dec = 11;
					
					int midPieceBoxNum = presentBoxNum - dec;
					CellSprite midCell = board.getCellFromCellNum(midPieceBoxNum);
					if(midCell.isFilled())
					{
						AbsPieceSprite piece = board.getPieceOnCell(midCell);
						if(!(piece.getColor().equals(pieceToMove.getColor())))
						{
							success = true;
							pieceToMove.setBoxNum(cellNum);
							board.getCellFromCellNum(presentBoxNum).setFilled(false);
							aCell.setFilled(true);
							midCell.setFilled(false);
							
							gameCanvas.deleteKilledPiece(piece);
						}
					}
				}
				else if((presentBoxNum + 18 == cellNum) || (presentBoxNum + 22 == cellNum))
				{
					int inc = 0;
					if(presentBoxNum + 18 == cellNum)
						inc = 9;
					else if(presentBoxNum + 22 == cellNum)
						inc = 11;
					
					int midPieceBoxNum = presentBoxNum + inc;
					CellSprite midCell = board.getCellFromCellNum(midPieceBoxNum);
					if(midCell.isFilled())
					{
						AbsPieceSprite piece = board.getPieceOnCell(midCell);
						if(!(piece.getColor().equals(pieceToMove.getColor())))
						{
							success = true;
							pieceToMove.setBoxNum(cellNum);
							board.getCellFromCellNum(presentBoxNum).setFilled(false);
							aCell.setFilled(true);
							midCell.setFilled(false);
							
							gameCanvas.deleteKilledPiece(piece);
						}
					}
				}
			}
		}
		else if(pieceToMove instanceof KingPiece)
		{
			int numOfPieceInLine = 0;
			String dir;
			if(!aCell.isFilled() && aCell.isValidCell())
			{
				if(presentBoxNum > cellNum)
				{
					int dec;
					AbsPieceSprite piece = null;
					CellSprite midCell = null;
					
					if(presentBoxNum % 10 > cellNum % 10)
					{
						dec = 11;
						dir = "L";
					}
					else
					{
						dec = 9;
						dir = "R";
					}
					
					int reducer = presentBoxNum - dec;
					while(reducer >= cellNum)
					{
						CellSprite temporalMidCell = board.getCellFromCellNum(reducer);
						AbsPieceSprite temporalPiece = board.getPieceOnCell(temporalMidCell);
						if(temporalMidCell.isFilled() && !(pieceToMove.getColor().equals(temporalPiece.getColor())))
						{
							numOfPieceInLine++;
							piece = temporalPiece;
							midCell = temporalMidCell;
						}
						
						reducer -= dec;
					}
					
					if(numOfPieceInLine == 1)
					{
						success = true;
						pieceToMove.setBoxNum(cellNum);
						board.getCellFromCellNum(presentBoxNum).setFilled(false);
						aCell.setFilled(true);
						midCell.setFilled(false);
						setMultiKillKingLastMove("U" + dir);
						
						gameCanvas.deleteKilledPiece(piece);
					}
				}
				else if(presentBoxNum < cellNum)
				{
					int inc;
					AbsPieceSprite piece = null;
					CellSprite midCell = null;
					
					if(presentBoxNum % 10 > cellNum % 10)
					{
						inc = 9;
						dir = "L";
					}
					else
					{
						inc = 11;
						dir = "R";
					}
					
					int adder = presentBoxNum + inc;
					while(adder <= cellNum)
					{
						CellSprite temporalMidCell = board.getCellFromCellNum(adder);
						AbsPieceSprite temporalPiece = board.getPieceOnCell(temporalMidCell);
						if(temporalMidCell.isFilled() && !(pieceToMove.getColor().equals(temporalPiece.getColor())))
						{
							numOfPieceInLine++;
							piece = temporalPiece;
							midCell = temporalMidCell;
						}
						
						adder += inc;
					}
					
					if(numOfPieceInLine == 1)
					{
						success = true;
						pieceToMove.setBoxNum(cellNum);
						board.getCellFromCellNum(presentBoxNum).setFilled(false);
						aCell.setFilled(true);
						midCell.setFilled(false);
						setMultiKillKingLastMove("D" + dir);
						
						gameCanvas.deleteKilledPiece(piece);
					}
				}
			}
		}
		
		return success;
	}
	
	public void resizePiecesOnBoard()
	{
		for(AbsPieceSprite element : pieces)
		{
			int pieceBoxNum = element.getBoxNum();
			CellSprite aCell = board.getCellFromCellNum(pieceBoxNum);
			element.setX(aCell.getX() + 10);
			element.setY(aCell.getY() + 10);
		}
	}
	
	public void returnPiece(AbsPieceSprite piece, int x, int y)
	{
		piece.setX(x);
		piece.setY(y);
	}

	public ArrayList<AbsPieceSprite> getPieces() {
		return pieces;
	}

	public void setPieces(ArrayList<AbsPieceSprite> pieces) {
		this.pieces = pieces;
	}

	public int getPieceLeft() {
		return pieceLeft;
	}

	public void setPieceLeft(int pieceLeft) {
		this.pieceLeft = pieceLeft;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getIncX() {
		return incX;
	}

	public void setIncX(int incX) {
		this.incX = incX;
	}

	public int getIncY() {
		return incY;
	}

	public void setIncY(int incY) {
		this.incY = incY;
	}

	public String getPieceMoveDirection() {
		return pieceMoveDirection;
	}

	public void setPieceMoveDirection(String pieceMoveDirection) {
		this.pieceMoveDirection = pieceMoveDirection;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public ArrayList<AbsPieceSprite> getCoolMovers() {
		return coolMovers;
	}

	public void setCoolMovers(ArrayList<AbsPieceSprite> coolMovers) {
		this.coolMovers = coolMovers;
	}

	public void setKillMovers(ArrayList<AbsPieceSprite> killMovers) {
		this.killMovers = killMovers;
	}

	public ArrayList<AbsPieceSprite> getKillMovers() {
		return killMovers;
	}

	public void setPieceToMove(AbsPieceSprite pieceToMove) {
		this.pieceToMove = pieceToMove;
	}

	public void setG2d(Graphics2D g2d) {
		this.g2d = g2d;
	}

	public void setGameCanvas(GameCanvas gameCanvas) {
		this.gameCanvas = gameCanvas;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMultiKillKingLastMove() {
		return multiKillKingLastMove;
	}

	public void setMultiKillKingLastMove(String multiKillKingLastMove) {
		this.multiKillKingLastMove = multiKillKingLastMove;
	}
}
