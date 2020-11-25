package draught;

public class Computer extends Templatepc{

	public Computer(Board board)
	{
		super(board);
		setIncX(0);
		setIncY(0);
		initPlayerPiece();
	}
}
