package draught1;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class GlowBox extends AbsSprite2D{

	public GlowBox()
	{
		super();
		initSprite();
	}
	
	@Override
	public void initSprite()
	{
		setX(getX());
		setY(getY());
		setWidth(MyConsts.CELL_W);
		setHeight(MyConsts.CELL_H);
		setColor(MyConsts.PIECEMOVEC);
		setVisible(false);
		setActive(false);
	}
	
	@Override
	public void updateSprite()
	{
		
	}
	
	@Override
	public void paintSprite(Graphics2D g2d)
	{
		Stroke solid = new BasicStroke(10.f);
		g2d.setStroke(solid);
		if(isVisible())
			g2d.drawRect(getX(), getY(), getWidth(), getHeight());
	}
}
