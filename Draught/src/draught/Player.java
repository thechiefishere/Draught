package draught;

public class Player extends Templatepc{

	private Computer computer;
	private Board board;
	
	public Player(Board board)
	{
		super(board);
		setIncX(0);
		setIncY(6);
		initPlayerPiece();
	}
	
	public boolean validMove(int evtX, int evtY, int draggedPieceBoardNum)
	{
		if(boxIsEmpty(evtX, evtY))
		{
			if(possiblePieceMove(evtX, evtY, draggedPieceBoardNum))
				return true;
		}
		
		return false;
	}
	
	private boolean possiblePieceMove(int evtX, int evtY, int pieceBoardNum)
	{
		boolean possible = false;
		Cell aCell = boxToCheck(evtX, evtY);
		int newPosBoardNum = aCell.getCellNum();
		
		if((pieceBoardNum % 10 == 0) || (pieceBoardNum % 10 == 9))
		{
			if(pieceBoardNum % 10 == 0)
			{
				if((pieceBoardNum - 10) == newPosBoardNum - 1)
				{
					possible = true;
					return possible;
				}
					
			}
			else if(pieceBoardNum % 10 == 9)
			{
				if((pieceBoardNum - 10) == newPosBoardNum + 1)
				{
					possible = true;
					return possible;
				}
			}
				
		}
		else
			if(((pieceBoardNum - 10) == newPosBoardNum + 1) || ((pieceBoardNum - 10) == newPosBoardNum - 1))
			{
				possible = true;
				return possible;
			}
		
		return possible;
	}
	
	private boolean boxIsEmpty(int evtX, int evtY)
	{
		boolean isEmpty = true;
		int count = 0;
		Cell aCell = boxToCheck(evtX, evtY);
		Piece piece = null;
		
		for(int i = 0; i < this.getPieces().size(); i++)
		{
			piece = this.getPieces().get(i);
			if((piece.getX() >= aCell.getX()) && (piece.getX() + MyConsts.PIECE_W <= aCell.getX() + MyConsts.CELL_W) 
					&& (piece.getY() >= aCell.getY()) && (piece.getY() + MyConsts.PIECE_H <= aCell.getY() + MyConsts.CELL_H))
				count++;
		}
		if(count > 1)
		{
			isEmpty = false;
			return isEmpty;
		}
		
		return isEmpty;
	}
	
	public Cell boxToCheck(int evtX, int evtY)
	{
		Cell cell = null;
		for(int i = 0; i < 100; i++)
		{
			cell = board.getCellDimension(i);
			if((evtX >= cell.getX()) && (evtX <= cell.getX() + MyConsts.CELL_W)
					&& (evtY >= cell.getY()) && (evtY <= cell.getY() + MyConsts.CELL_H))
				break;
		}
		return cell;
	}
	
	public void setBoard(Board board)
	{
		this.board = board;
	}
}
