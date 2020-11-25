package draught1;

public class Computer extends AbsPlayer{

	private Board board;
	private GameCanvas gameCanvas;
	private FirstPlayer firstPlayer;
	
	private boolean multipleKill;
	private String lastMove;
	private int counter;
	private AbsPieceSprite pieceInAction;
	
	public Computer(Board board)
	{
		super(board);
		setIncX(0);
		setIncY(0);
		setName("Computer");
		setPieceMoveDirection(MyConsts.DOWN);
		setColor(MyConsts.SECONDPLAYERC);
		setMultipleKill(false);
		initPlayerPiece();
	}
	
	public void makeMove()
	{
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(fillKillMovers() == 1)
		{
			makeKillMove();
			setLastMove("KILL");
		}
		else if(fillKillMovers() == 0 && fillCoolMovers() == 1)
		{
			makeCoolMove();
			setLastMove("COOL");
		}
	}
	
	public void makeKillMove()
	{
		if(fillKillMovers() == 1)
		{
			if(isMultipleKill())
				pieceInAction = getPieceInAction();
			else
			{
				int size = getKillMovers().size();
				int toMove = (int) (Math.random() * size);
				pieceInAction = getKillMovers().get(toMove);
			}
			setPieceInAction(pieceInAction);
			String killDirection = pieceInAction.getComputerKillDirection();
			
			if(pieceInAction instanceof RegularPiece)
			{
				if(pieceInAction.killMoveToLeft() && pieceInAction.killMoveToRight())
				{
					int rand = (int) (Math.random() * 2);
					if(rand == 0)
						regularPieceKillRight(pieceInAction, killDirection);
					else if(rand == 1)
						regularPieceKillLeft(pieceInAction, killDirection);
				}
				else if(pieceInAction.killMoveToLeft())
					regularPieceKillLeft(pieceInAction, killDirection);
				else if(pieceInAction.killMoveToRight())
					regularPieceKillRight(pieceInAction, killDirection);
			}
			else if(pieceInAction instanceof KingPiece)
			{
				if(pieceInAction.killMoveToLeft() && pieceInAction.killMoveToRight())
				{
					int rand = (int) (Math.random() * 2);
					if(rand == 0)
						kingPieceKillRight(pieceInAction, killDirection);
					else if(rand == 1)
						kingPieceKillLeft(pieceInAction, killDirection);
				}
				else if(pieceInAction.killMoveToLeft())
					kingPieceKillLeft(pieceInAction, killDirection);
				else if(pieceInAction.killMoveToRight())
					kingPieceKillRight(pieceInAction, killDirection);
			}
		}
	}
	
	public void kingPieceKillLeft(AbsPieceSprite pieceInAction, String killDirection)
	{
		int presentPosition = pieceInAction.getBoxNum();
		int counter = 0;
		
		if(killDirection.equals("UP"))
		{
			int posOfPieceToKill = presentPosition - 11;
			CellSprite midCell;
			while(!(midCell = board.getCellFromCellNum(posOfPieceToKill)).isFilled())
			{
				counter++;
				posOfPieceToKill -= 11;
			}
			
			posOfPieceToKill = presentPosition - ((counter + 1) * 11);
			midCell = board.getCellFromCellNum(posOfPieceToKill);
			AbsPieceSprite midPiece = board.getPieceOnCell(midCell);
			int newPosition = posOfPieceToKill - 11;
			CellSprite newCell = board.getCellFromCellNum(newPosition);
			
			changePiecePosition(pieceInAction, newCell);
			pieceInAction.setBoxNum(newPosition);
			board.getCellFromCellNum(presentPosition).setFilled(false);
			midCell.setFilled(false);
			newCell.setFilled(true);
			
			gameCanvas.deleteKilledPiece(midPiece);
		}
		else if(killDirection.equals("DOWN"))
		{
			int posOfPieceToKill = presentPosition + 9;
			CellSprite midCell;
			while(!(midCell = board.getCellFromCellNum(posOfPieceToKill)).isFilled())
			{
				counter++;
				posOfPieceToKill += 9;
			}
			
			posOfPieceToKill = presentPosition - ((counter + 1) * 9);
			midCell = board.getCellFromCellNum(posOfPieceToKill);
			AbsPieceSprite midPiece = board.getPieceOnCell(midCell);
			int newPosition = posOfPieceToKill + 9;
			CellSprite newCell = board.getCellFromCellNum(newPosition);
			
			changePiecePosition(pieceInAction, newCell);
			pieceInAction.setBoxNum(newPosition);
			board.getCellFromCellNum(presentPosition).setFilled(false);
			midCell.setFilled(false);
			newCell.setFilled(true);
			
			gameCanvas.deleteKilledPiece(midPiece);
		}
	}
	
	public void kingPieceKillRight(AbsPieceSprite pieceInAction, String killDirection)
	{
		int presentPosition = pieceInAction.getBoxNum();
		int counter = 0;
		
		if(killDirection.equals("UP"))
		{
			int posOfPieceToKill = presentPosition - 9;
			CellSprite midCell;
			while(!(midCell = board.getCellFromCellNum(posOfPieceToKill)).isFilled())
			{
				counter++;
				posOfPieceToKill -= 9;
			}
			
			posOfPieceToKill = presentPosition - ((counter + 1) * 9);
			midCell = board.getCellFromCellNum(posOfPieceToKill);
			AbsPieceSprite midPiece = board.getPieceOnCell(midCell);
			int newPosition = posOfPieceToKill - 9;
			CellSprite newCell = board.getCellFromCellNum(newPosition);
			
			changePiecePosition(pieceInAction, newCell);
			pieceInAction.setBoxNum(newPosition);
			board.getCellFromCellNum(presentPosition).setFilled(false);
			midCell.setFilled(false);
			newCell.setFilled(true);
			
			gameCanvas.deleteKilledPiece(midPiece);
		}
		else if(killDirection.equals("DOWN"))
		{
			int posOfPieceToKill = presentPosition + 11;
			CellSprite midCell;
			while(!(midCell = board.getCellFromCellNum(posOfPieceToKill)).isFilled())
			{
				counter++;
				posOfPieceToKill += 11;
			}
			
			posOfPieceToKill = presentPosition - ((counter + 1) * 11);
			midCell = board.getCellFromCellNum(posOfPieceToKill);
			AbsPieceSprite midPiece = board.getPieceOnCell(midCell);
			int newPosition = posOfPieceToKill + 11;
			CellSprite newCell = board.getCellFromCellNum(newPosition);
			
			changePiecePosition(pieceInAction, newCell);
			pieceInAction.setBoxNum(newPosition);
			board.getCellFromCellNum(presentPosition).setFilled(false);
			midCell.setFilled(false);
			newCell.setFilled(true);
			
			gameCanvas.deleteKilledPiece(midPiece);
		}
	}
	
	public void regularPieceKillLeft(AbsPieceSprite pieceInAction, String killDirection)
	{
		int presentPosition = pieceInAction.getBoxNum();
		int posOfPieceToKill;
		int newPosition;
		CellSprite midCell;
		if(pieceInAction.killMoveToLeft())
		{
			if(killDirection == "DOWN")
			{
				posOfPieceToKill = presentPosition + 9;
				newPosition = presentPosition + 18;
				midCell = board.getCellFromCellNum(posOfPieceToKill);
				if(midCell.isFilled())
				{
					AbsPieceSprite midPiece = board.getPieceOnCell(midCell);
					if(!midPiece.getColor().equals(pieceInAction))
					{
						CellSprite newCell = board.getCellFromCellNum(newPosition);
						if(!newCell.isFilled())
						{
							changePiecePosition(pieceInAction, newCell);
							pieceInAction.setBoxNum(newPosition);
							board.getCellFromCellNum(presentPosition).setFilled(false);
							midCell.setFilled(false);
							newCell.setFilled(true);
							
							gameCanvas.deleteKilledPiece(midPiece);
						}
					}
				}
			}
			else if(killDirection == "UP")
			{
				posOfPieceToKill = presentPosition - 11;
				newPosition = presentPosition - 22;
				midCell = board.getCellFromCellNum(posOfPieceToKill);
				if(midCell.isFilled())
				{
					AbsPieceSprite midPiece = board.getPieceOnCell(midCell);
					if(!midPiece.getColor().equals(pieceInAction))
					{
						CellSprite newCell = board.getCellFromCellNum(newPosition);
						if(!newCell.isFilled())
						{
							changePiecePosition(pieceInAction, newCell);
							pieceInAction.setBoxNum(newPosition);
							board.getCellFromCellNum(presentPosition).setFilled(false);
							midCell.setFilled(false);
							newCell.setFilled(true);
							
							gameCanvas.deleteKilledPiece(midPiece);
						}
					}
				}
			}
		}
	}
	
	public void regularPieceKillRight(AbsPieceSprite pieceInAction, String killDirection)
	{
		int presentPosition = pieceInAction.getBoxNum();
		int posOfPieceToKill;
		int newPosition;
		CellSprite midCell;
		if(pieceInAction.killMoveToRight())
		{
			if(killDirection == "DOWN")
			{
				posOfPieceToKill = presentPosition + 11;
				newPosition = presentPosition + 22;
				midCell = board.getCellFromCellNum(posOfPieceToKill);
				if(midCell.isFilled())
				{
					AbsPieceSprite midPiece = board.getPieceOnCell(midCell);
					if(!midPiece.getColor().equals(pieceInAction))
					{
						CellSprite newCell = board.getCellFromCellNum(newPosition);
						if(!newCell.isFilled())
						{
							changePiecePosition(pieceInAction, newCell);
							pieceInAction.setBoxNum(newPosition);
							board.getCellFromCellNum(presentPosition).setFilled(false);
							midCell.setFilled(false);
							newCell.setFilled(true);
							
							gameCanvas.deleteKilledPiece(midPiece);
						}
					}
				}
			}
			else if(killDirection == "UP")
			{
				posOfPieceToKill = presentPosition - 9;
				newPosition = presentPosition - 18;
				midCell = board.getCellFromCellNum(posOfPieceToKill);
				if(midCell.isFilled())
				{
					AbsPieceSprite midPiece = board.getPieceOnCell(midCell);
					if(!midPiece.getColor().equals(pieceInAction))
					{
						CellSprite newCell = board.getCellFromCellNum(newPosition);
						if(!newCell.isFilled())
						{
							changePiecePosition(pieceInAction, newCell);
							pieceInAction.setBoxNum(newPosition);
							board.getCellFromCellNum(presentPosition).setFilled(false);
							midCell.setFilled(false);
							newCell.setFilled(true);
							
							gameCanvas.deleteKilledPiece(midPiece);
						}
					}
				}
			}
		}
	}
	
	public void makeCoolMove()
	{
		//System.out.println("Debugging am in makeCoolMove");
		if(getKillMovers().size() == 0 && getCoolMovers().size() > 0)
		{
			int size = getCoolMovers().size();
			int toMove = (int) (Math.random() * size);
			pieceInAction = getCoolMovers().get(toMove);
			while(checkIfPieceIsKillable(pieceInAction) && getCounter() <= 1000)
            {
                setCounter(getCounter() + 1);
                //System.out.println("Debugging i entered when counter= " + getCounter());
                toMove = (int) (Math.random() * size);
    			pieceInAction = getCoolMovers().get(toMove);
            }
            setCounter(0);
            System.out.println("am with a sure piece");
			if(pieceInAction instanceof RegularPiece)
			{
				//System.out.println("i entered if");
				if(pieceInAction.coolMoveToLeft() && pieceInAction.coolMoveToRight())
				{
					int rand = (int) (Math.random() * 2);
					if(rand == 0)
					{
						//System.out.println("i entered if of if");
						regularPieceCoolRight(pieceInAction);
					}
					else if(rand == 1)
					{
						//System.out.println("i entered else of if");
						regularPieceCoolLeft(pieceInAction);
					}
				}
				else if(pieceInAction.coolMoveToLeft())
				{
					//System.out.println("i entered first else if");
					regularPieceCoolLeft(pieceInAction);
					
				}
				else if(pieceInAction.coolMoveToRight())
				{
					//System.out.println("i entered second else if");
					regularPieceCoolRight(pieceInAction);
				}
			}
			else if(pieceInAction instanceof KingPiece)
			{
				if(pieceInAction.coolMoveToLeft() && pieceInAction.coolMoveToRight())
				{
					int rand = (int) (Math.random() * 2);
					if(rand == 0)
						kingPieceCoolRight(pieceInAction);
					else if(rand == 1)
						kingPieceCoolLeft(pieceInAction);
				}
				else if(pieceInAction.coolMoveToLeft())
					kingPieceCoolLeft(pieceInAction);
				else if(pieceInAction.killMoveToRight())
					kingPieceCoolRight(pieceInAction);
			}
		}
		System.out.println("am out of makeCoolMove");
	}
	
	public void kingPieceCoolLeft(AbsPieceSprite pieceInAction)
	{
		int presentPosition = pieceInAction.getBoxNum();
		int coolMoveLeftUp = presentPosition - 11;
		int coolMoveLeftDown = presentPosition + 9;
		CellSprite upCell = board.getCellFromCellNum(coolMoveLeftUp);
		CellSprite downCell = board.getCellFromCellNum(coolMoveLeftDown);
		String moveDirection = null;
		if(!upCell.isFilled() && !downCell.isFilled())
		{
			int rand = (int) (Math.random() * 2);
			if(rand == 0)
				moveDirection = "UP";
			else if(rand == 1)
				moveDirection = "DOWN";
		}
		else if(!upCell.isFilled())
			moveDirection = "UP";
		else if(!downCell.isFilled())
			moveDirection = "DOWN";
		
		int counter = 0;
		int newPosition = presentPosition;
		if(moveDirection == "DOWN")
		{
			while(((newPosition += 9) % 10 < 9) && (newPosition < 100))
			{
				CellSprite aCell = board.getCellFromCellNum(newPosition);
				if(!aCell.isFilled())
				{
					counter++;
					continue;
				}
				else
					break;
			}
			
			int random = (int) (Math.random() * counter) + 1;
			newPosition = presentPosition + (random * 9);
			CellSprite newCell = board.getCellFromCellNum(newPosition);
			
			changePiecePosition(pieceInAction, newCell);
			pieceInAction.setBoxNum(newPosition);
			board.getCellFromCellNum(presentPosition).setFilled(false);
			newCell.setFilled(true);
		}
		else if(moveDirection == "UP")
		{
			while(((newPosition -= 11) % 10 < 9) && (newPosition >= 0))
			{
				CellSprite aCell = board.getCellFromCellNum(newPosition);
				if(!aCell.isFilled())
				{
					counter++;
					continue;
				}
				else
					break;
			}
			
			int random = (int) (Math.random() * counter) + 1;
			newPosition = presentPosition - (random * 11);
			CellSprite newCell = board.getCellFromCellNum(newPosition);
			
			changePiecePosition(pieceInAction, newCell);
			pieceInAction.setBoxNum(newPosition);
			board.getCellFromCellNum(presentPosition).setFilled(false);
			newCell.setFilled(true);
		}
	}
	
	public void kingPieceCoolRight(AbsPieceSprite pieceInAction)
	{
		int presentPosition = pieceInAction.getBoxNum();
		int coolMoveLeftUp = presentPosition - 11;
		int coolMoveLeftDown = presentPosition + 9;
		CellSprite upCell = board.getCellFromCellNum(coolMoveLeftUp);
		CellSprite downCell = board.getCellFromCellNum(coolMoveLeftDown);
		String moveDirection = null;
		if(!upCell.isFilled() && !downCell.isFilled())
		{
			int rand = (int) (Math.random() * 2);
			if(rand == 0)
				moveDirection = "UP";
			else if(rand == 1)
				moveDirection = "DOWN";
		}
		else if(!upCell.isFilled())
			moveDirection = "UP";
		else if(!downCell.isFilled())
			moveDirection = "DOWN";
		
		int counter = 0;
		int newPosition = presentPosition;
		if(moveDirection == "DOWN")
		{
			while(((newPosition += 11) % 10 > 0) && (newPosition < 100))
			{
				CellSprite aCell = board.getCellFromCellNum(newPosition);
				if(!aCell.isFilled())
				{
					counter++;
					continue;
				}
				else
					break;
			}
			
			int random = (int) (Math.random() * counter) + 1;
			newPosition = presentPosition + (random * 11);
			CellSprite newCell = board.getCellFromCellNum(newPosition);
			
			changePiecePosition(pieceInAction, newCell);
			pieceInAction.setBoxNum(newPosition);
			board.getCellFromCellNum(presentPosition).setFilled(false);
			newCell.setFilled(true);
		}
		else if(moveDirection == "UP")
		{
			while(((newPosition -= 9) % 10 < 9) && (newPosition >= 0))
			{
				CellSprite aCell = board.getCellFromCellNum(newPosition);
				if(!aCell.isFilled())
				{
					counter++;
					continue;
				}
				else
					break;
			}
			
			int random = (int) (Math.random() * counter) + 1;
			newPosition = presentPosition - (random * 9);
			CellSprite newCell = board.getCellFromCellNum(newPosition);
			
			changePiecePosition(pieceInAction, newCell);
			pieceInAction.setBoxNum(newPosition);
			board.getCellFromCellNum(presentPosition).setFilled(false);
			newCell.setFilled(true);
		}
	}
	
	public void regularPieceCoolLeft(AbsPieceSprite pieceInAction)
	{
		int presentPosition = pieceInAction.getBoxNum();
		int newPosition = presentPosition + 9;
		CellSprite newCell = board.getCellFromCellNum(newPosition);
		
		if(!newCell.isFilled())
		{
			changePiecePosition(pieceInAction, newCell);
			pieceInAction.setBoxNum(newPosition);
			board.getCellFromCellNum(presentPosition).setFilled(false);
			newCell.setFilled(true);
		}
	}
	
	public void regularPieceCoolRight(AbsPieceSprite pieceInAction)
	{
		int presentPosition = pieceInAction.getBoxNum();
		int newPosition = presentPosition + 11;
		CellSprite newCell = board.getCellFromCellNum(newPosition);
		
		if(!newCell.isFilled())
		{
			changePiecePosition(pieceInAction, newCell);
			pieceInAction.setBoxNum(newPosition);
			board.getCellFromCellNum(presentPosition).setFilled(false);
			newCell.setFilled(true);
		}
	}
	
	public void changePiecePosition(AbsPieceSprite pieceThatMove, CellSprite newCell)
	{
		int x = newCell.getX() + 10;
		int y = newCell.getY() + 10;
		pieceThatMove.setX(x);
		pieceThatMove.setY(y);
	}
	
	public boolean checkIfPieceIsKillable(AbsPieceSprite pieceInAction)
    {
        //System.out.println("Debugging am in checkIfPieceIsKillable");
        boolean killable = false;

        int pieceInActionPosition = pieceInAction.getBoxNum();
        //System.out.println("Debugging computerPiecePosition " + pieceInActionPosition);
        int possibleKillZoneA = pieceInActionPosition + 18;
        int possibleKillZoneB = pieceInActionPosition + 22;
        int possibleKillZoneC = pieceInActionPosition + 20;
        if((pieceInActionPosition % 10 > 0) && (pieceInActionPosition % 10 < 9))
        {
            //System.out.println("Debugging am in if");
            for(int i = 0; i < firstPlayer.getPieces().size(); i++)
            {
                //Log.d("Debugging", "firstPlayer piece position= " + firstPlayer.getPieces().get(i).getBoxNum());
                if((firstPlayer.getPieces().get(i).getBoxNum() == possibleKillZoneA) || (firstPlayer.getPieces().get(i).getBoxNum() == possibleKillZoneB))
                {
                    killable = true;
                    break;
                }
            }
        }
        else if(pieceInActionPosition % 10 == 0)
        {
            for(int i = 0; i < firstPlayer.getPieces().size(); i++)
            {
                if((firstPlayer.getPieces().get(i).getBoxNum() == possibleKillZoneB) || (firstPlayer.getPieces().get(i).getBoxNum() == possibleKillZoneC))
                {
                    killable = true;
                    break;
                }
            }
        }
        else if(pieceInActionPosition % 10 == 9)
        {
            for(int i = 0; i < firstPlayer.getPieces().size(); i++)
            {
                if((firstPlayer.getPieces().get(i).getBoxNum() == possibleKillZoneA) || (firstPlayer.getPieces().get(i).getBoxNum() == possibleKillZoneC))
                {
                    killable = true;
                    break;
                }
            }
        }

        //System.out.println("Debugging boolean killable= " + killable);
        return killable;
    }

	public void setBoard(Board board) {
		this.board = board;
	}

	public void setGameCanvas(GameCanvas gameCanvas) {
		this.gameCanvas = gameCanvas;
	}

	public boolean isMultipleKill() {
		return multipleKill;
	}

	public void setMultipleKill(boolean multipleKill) {
		this.multipleKill = multipleKill;
	}

	public AbsPieceSprite getPieceInAction() {
		return pieceInAction;
	}

	public void setPieceInAction(AbsPieceSprite pieceInAction) {
		this.pieceInAction = pieceInAction;
	}

	public String getLastMove() {
		return lastMove;
	}

	public void setLastMove(String lastMove) {
		this.lastMove = lastMove;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public void setFirstPlayer(FirstPlayer firstPlayer) {
		this.firstPlayer = firstPlayer;
	}
}
