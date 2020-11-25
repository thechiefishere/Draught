package draught;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

	private Templatepc temp;
	private Board board;
	private Player player;
	private Computer computer;
	private boolean firstTime;
	private Piece draggedPiece;
	private int dx, dy;
	private int draggedPieceBoardNum;
	private int initX, initY;
	
	public GamePanel()
	{
		initComponent();
		repaint();
		addMouseMotionListener(new MyMouseAdapter());
		addMouseListener(new MyMouseAdapter());
	}
	
	private void initComponent()
	{
		firstTime = true;
		board = new Board();
		player = new Player(board);
		computer = new Computer(board);
		
		initBoard();
		initPlayer();
		initComputer();
	}
	
	private void initBoard()
	{
		
	}
	
	private void initPlayer()
	{
		player.setBoard(board);
	}
	
	private void initComputer()
	{
		
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d  = (Graphics2D) g;
		
		board.paintBoard(g2d);
		
		g2d.setColor(Color.WHITE);
		player.paintPlayerPiece(g2d);
		
		g2d.setColor(Color.GREEN);
		computer.paintPlayerPiece(g2d);
	}
	
	class MyMouseAdapter extends MouseAdapter
	{
		@Override
		public void mouseDragged(MouseEvent evt)
		{
			if(firstTime)
			{
				int idx = playerPieceDragged(evt.getX(), evt.getY());
				if(idx >= 0)
					draggedPiece = player.getPieces().get(idx);
				
				initX = draggedPiece.getX();
				initY = draggedPiece.getY();
				
				dx = evt.getX() - draggedPiece.getX();
				dy = evt.getY() - draggedPiece.getY();
				
				firstTime = false;
			}
			if(draggedPiece != null)
			{
				draggedPiece.setX(evt.getX() - dx);
				draggedPiece.setY(evt.getY() - dy);
			}
			
			draggedPieceBoardNum = draggedPiece.getBoxNum();
			repaint();
		}
		
		private int playerPieceDragged(int evtX, int evtY)
		{
			int idx = -1;
			for(int i = 0; i < player.getPieces().size(); i++)
			{
				if((evtX >= player.getPieces().get(i).getX()) && (evtX <= player.getPieces().get(i).getX() + MyConsts.PIECE_W)
						&& (evtY >= player.getPieces().get(i).getY()) && (evtY <= player.getPieces().get(i).getY() + MyConsts.PIECE_H))
					return i;
			}
			return idx;
		}
		
		@Override
		public void mouseReleased(MouseEvent evt)
		{
			if(player.validMove(evt.getX(), evt.getY(), draggedPieceBoardNum))
			{
				draggedPiece.setBoxNum(player.boxToCheck(evt.getX(), evt.getY()).getCellNum());
			}
			else
			{
				returnPiece(initX, initY);
			}
			
			draggedPiece = null;
			firstTime = true;
			repaint();
		}
		
		private void returnPiece(int x, int y)
		{
			draggedPiece.setX(x);
			draggedPiece.setY(y);
		}
	}
}
