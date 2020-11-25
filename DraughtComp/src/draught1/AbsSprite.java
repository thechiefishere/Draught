package draught1;

import java.awt.Graphics2D;

public abstract class AbsSprite {

	private boolean visible;
	private boolean active;
	private int priority;
	
	public abstract void initSprite();
	
	public abstract void updateSprite();
	
	public abstract void paintSprite(Graphics2D g2d);

	public boolean isVisible()
	{
		return visible;
	}

	public void setVisible(boolean visible) 
	{
		this.visible = visible;
	}

	public boolean isActive()
	{
		return active;
	}

	public void setActive(boolean active)
	{
		this.active = active;
	}

	public int getPriority()
	{
		return priority;
	}

	public void setPriority(int priority)
	{
		this.priority = priority;
	}
	
	
}
