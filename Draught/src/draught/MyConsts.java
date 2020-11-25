package draught;

public class MyConsts {

	public MyConsts()
	{
		
	}
	
	public static final int TOP_BAR_HEIGHT = 40;
	public static final int EDGE_WIDTH = 17;
	public static final int CV_WIDTH = 717;
	public static final int CV_HEIGHT = 740;
	
	public static final int MINX = 0;
	public static final int MAXX = CV_WIDTH -  EDGE_WIDTH;
	public static final int MINY = 0;
	public static final int MAXY = CV_HEIGHT - TOP_BAR_HEIGHT;
	public static final int CV_WIDTH_MID = MAXX / 2;
	
	public static final int MARGIN = 50;
	public static final int MINX_DIS = MINX + MARGIN;
	public static final int MAXX_DIS = MAXX - MARGIN;
	public static final int MINY_DIS = MINY + MARGIN;
	public static final int MAXY_DIS = MAXY - MARGIN;
	public static final int NEXTLINE = 50;
	
	public static final int CELL_W = 70;
	public static final int CELL_H = 70;
	public static final int PIECE_W = 50;
	public static final int PIECE_H = 50;
	public static final int NUM_PIECE = 20;
	public static final int BOARD_RC = 10;
	public static final int NUM_BOXES = 100;
}
