package draught1;

import java.awt.Color;
import java.util.ArrayList;


public class FirstPlayer extends AbsPlayer{

	public FirstPlayer(Board board)
	{
		super(board);
		setIncX(0);
		setIncY(6);
		setName("FirstPlayer");
		setPieceMoveDirection(MyConsts.UP);
		setColor(MyConsts.FIRSTPLAYERC);
		initPlayerPiece();
	}
}
