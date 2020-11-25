package draught1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class GameCanvas extends AbsGameCanvas{

	private Board board;
	private FirstPlayer firstPlayer;
	private SecondPlayer secondPlayer;
	private AbsPlayer currPlayer;
	private AbsPieceSprite pieceToMove;
	
	private int pieceToMoveInitX, pieceToMoveInitY;
	private boolean multipleKill;
	private AbsPieceSprite pieceToCheckForMultipleKill;
	
	public GameCanvas()
	{
		super();
		initComponent();
		currPlayer = firstPlayer;
		pieceToMove = null;
		multipleKill = false;
		addMouseMotionListener(new PieceDragged());
		addMouseListener(new PieceReleased());
		startGame();
	}
	
	private void initComponent()
	{
		board = new Board();
		firstPlayer = new FirstPlayer(board);
		secondPlayer = new SecondPlayer(board);
		
		initBoard();
		initFirstPlayer();
		initSecondPlayer();
	}
	
	private void initBoard()
	{
		board.setFirstPlayer(firstPlayer);
		board.setSecondPlayer(secondPlayer);
		getSpriteAry().add(board);
	}
	
	private void initFirstPlayer()
	{
		firstPlayer.setGameCanvas(this);
	}
	
	private void initSecondPlayer()
	{
		secondPlayer.setGameCanvas(this);
	}
	
	public void switchCurrPlayer(int x, int y)
	{
		if(multipleKill && currPlayer.fillKillMovers() == 1 
				&& currPlayer.samePieceMultipleKill(pieceToCheckForMultipleKill))
			retainCurrPlayer();
		else
		{
			if(getCurrPlayer().equals(firstPlayer))
				setCurrPlayer(secondPlayer);
			else if(getCurrPlayer().equals(secondPlayer))
				setCurrPlayer(firstPlayer);
			
			multipleKill = false;
		}
	}
	
	public void retainCurrPlayer()
	{
		if(getCurrPlayer().equals(firstPlayer))
			setCurrPlayer(firstPlayer);
		else if(getCurrPlayer().equals(secondPlayer))
			setCurrPlayer(secondPlayer);
	}
	
	@Override
	public void paintPieces(Graphics2D g2d)
	{	
		firstPlayer.paintPlayerPiece(g2d);
		secondPlayer.paintPlayerPiece(g2d);
	}
	
	public void deleteKilledPiece(AbsPieceSprite piece)
	{
		if(currPlayer.equals(firstPlayer))
		{
			for(AbsPieceSprite element : secondPlayer.getPieces())
			{
				if(element.equals(piece))
				{
					piece.setVisible(false);
					secondPlayer.getPieces().remove(element);
					break;
				}
			}
			if(secondPlayer.getPieces().size() == 0)
			{
				setGameOver(true);
				announceGameResult((Graphics2D) getGraphics(),firstPlayer);
			}
		}
		else if(currPlayer.equals(secondPlayer))
		{
			for(AbsPieceSprite element : firstPlayer.getPieces())
			{
				if(element.equals(piece))
				{
					piece.setVisible(false);
					firstPlayer.getPieces().remove(element);
					break;
				}
			}
			if(firstPlayer.getPieces().size() == 0)
			{
				setGameOver(true);
				announceGameResult((Graphics2D) getGraphics(),secondPlayer);
			}
		}
	}
	
	public boolean regularPieceIsInKingPosition(AbsPieceSprite piece)
	{
		boolean sure = false;
		int pieceBoxNum = piece.getBoxNum();
		
		if(piece instanceof RegularPiece)
		{
			if(currPlayer.equals(firstPlayer))
			{
				if(pieceBoxNum == 0 || pieceBoxNum == 2 || pieceBoxNum == 4
						|| pieceBoxNum == 6 || pieceBoxNum == 8)
					sure = true;
			}
			else if(currPlayer.equals(secondPlayer))
			{
				if(pieceBoxNum == 91 || pieceBoxNum == 93 || pieceBoxNum == 95
						|| pieceBoxNum == 97 || pieceBoxNum == 99)
					sure = true;
			}
		}
		
		return sure;
	}
	
	public void changeRegularPieceToKingPiece(AbsPieceSprite piece)
	{
		if(!currPlayer.samePieceMultipleKill(pieceToCheckForMultipleKill))
		{
			int pieceBoxNum = piece.getBoxNum();
			int x = piece.getX();
			int y = piece.getY();
			KingPiece kingP = new KingPiece(board);
			kingP.setX(x);
			kingP.setY(y);
			kingP.setBoxNum(pieceBoxNum);
			kingP.setColor(piece.getColor());
			kingP.setMoveDirection(currPlayer.getPieceMoveDirection());
			currPlayer.getPieces().remove(piece);
			currPlayer.getPieces().add(kingP);
		}
	}
	
	public void announceGameResult(Graphics2D g2d, AbsPlayer winner)
	{
		if(isGameOver())
		{
			stopGame();
			
			g2d.setFont(new Font("Times", Font.ITALIC, 25));
			g2d.setColor(Color.YELLOW);
			g2d.fillRect(40, 200, 420, 150);
			
			String toShow = winner.getName() + " has won the game";
			g2d.setColor(Color.BLUE);
			g2d.drawString(toShow, 80, 280);
		}
	}
	
	
	class PieceDragged extends MouseMotionAdapter
	{
		@Override
		public void mouseMoved(MouseEvent evt)
		{
			currPlayer.setG2d((Graphics2D) getGraphics());
			currPlayer.glowPieceThatCanMoveOrKill();
		}
		
		@Override
		public void mouseDragged(MouseEvent evt)
		{
			if(currPlayer.fillKillMovers() == 1)
			{
				AbsPieceSprite piece;
				for(int i = 0; i < currPlayer.getKillMovers().size(); i++)
				{
					piece = currPlayer.getKillMovers().get(i);
					if(evt.getX() >= piece.getX() && evt.getX() <= piece.getX() + piece.getWidth()
					             && evt.getY() >= piece.getY() && evt.getY() <= piece.getY() + piece.getHeight())
					{
						pieceToMove = piece;
						pieceToMoveInitX = piece.getX();
						pieceToMoveInitY = piece.getY();
						currPlayer.setPieceToMove(piece);
						if(pieceToMove instanceof KingPiece)
						{
							((KingPiece) pieceToMove).getKingKillDirection().clear();
							currPlayer.setMultiKillKingLastMove("");
						}
						break;
					}
				}
				
				if(pieceToMove != null)
				{
					pieceToMove.setX(evt.getX());
					pieceToMove.setY(evt.getY());
				}
				
				multipleKill = true;
				pieceToCheckForMultipleKill = pieceToMove;
			}
			else if(currPlayer.fillKillMovers() == 0 && currPlayer.fillCoolMovers() == 1)
			{
				AbsPieceSprite piece;
				for(int i = 0; i < currPlayer.getCoolMovers().size(); i++)
				{
					piece = currPlayer.getCoolMovers().get(i);
					if(evt.getX() >= piece.getX() && evt.getX() <= piece.getX() + piece.getWidth()
					             && evt.getY() >= piece.getY() && evt.getY() <= piece.getY() + piece.getHeight())
					{
						pieceToMove = piece;
						currPlayer.setPieceToMove(piece);
						break;
					}
				}
				
				if(pieceToMove != null)
				{
					pieceToMove.setX(evt.getX());
					pieceToMove.setY(evt.getY());
				}
			}
		}
	}
	
	
	class PieceReleased extends MouseAdapter
	{
		@Override
		public void mouseReleased(MouseEvent evt)
		{
			if(pieceToMove != null)
			{
				if(currPlayer.fillKillMovers() == 1)
				{
					if(currPlayer.checkKillMove(evt.getX(), evt.getY()))
					{
						currPlayer.resizePiecesOnBoard();
						if(regularPieceIsInKingPosition(pieceToMove))
							changeRegularPieceToKingPiece(pieceToMove);
						pieceToMove = null;
						switchCurrPlayer(evt.getX(), evt.getY());
					}
					else
					{
						currPlayer.returnPiece(pieceToMove, pieceToMoveInitX, pieceToMoveInitY);
						currPlayer.resizePiecesOnBoard();
					}
				}
				else if(currPlayer.fillKillMovers() == 0 && currPlayer.fillCoolMovers() == 1)
				{
					if(currPlayer.checkCoolMove(evt.getX(), evt.getY()))
					{
						currPlayer.resizePiecesOnBoard();
						if(regularPieceIsInKingPosition(pieceToMove))
							changeRegularPieceToKingPiece(pieceToMove);
						pieceToMove = null;
						switchCurrPlayer(evt.getX(), evt.getY());
					}
					else
					{
						currPlayer.returnPiece(pieceToMove, pieceToMoveInitX, pieceToMoveInitY);
						currPlayer.resizePiecesOnBoard();
					}
				}
			}
		}
	}

	public AbsPlayer getCurrPlayer() {
		return currPlayer;
	}

	public void setCurrPlayer(AbsPlayer currPlayer) {
		this.currPlayer = currPlayer;
	}
}
