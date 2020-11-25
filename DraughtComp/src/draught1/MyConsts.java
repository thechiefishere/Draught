package draught1;

import java.awt.Color;

public class MyConsts {

	public MyConsts()
	{
		
	}
	
	public static final int TOP_BAR_HEIGHT = 40;
	public static final int EDGE_WIDTH = 17;
	public static final int CV_WIDTH = 517;
	public static final int CV_HEIGHT = 540;
	
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
	public static final int NEXTLINE = 30;
	
	public static final int SLEEPTIME = 100;
	public static final int CELL_W = 50;
	public static final int CELL_H = 50;
	public static final int INITX = 0;
	public static final int INITY = 0;
	public static final int MAXCELL = 10;
	public static final int PIECEW = 30;
	public static final int PIECEH = 30;
	public static final Color WHITEP = Color.WHITE;
	public static final Color BLUEP = Color.BLUE;
	public static final int NUM_PIECE = 20;
	public static final int NUM_BOXES = 100;
	public static final String UP = "UP";
	public static final String DOWN = "DOWN";
	public static final Color PIECEMOVEC = Color.WHITE;
	public static final Color FIRSTPLAYERC = Color.GREEN;
	public static final Color SECONDPLAYERC = Color.WHITE;
	
}