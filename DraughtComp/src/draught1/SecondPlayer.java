package draught1;

import java.awt.Color;

public class SecondPlayer extends AbsPlayer {

	public SecondPlayer(Board board)
	{
		super(board);
		setIncX(0);
		setIncY(0);
		setName("SecondPlayer");
		setPieceMoveDirection(MyConsts.DOWN);
		setColor(MyConsts.SECONDPLAYERC);
		initPlayerPiece();
	}
}
