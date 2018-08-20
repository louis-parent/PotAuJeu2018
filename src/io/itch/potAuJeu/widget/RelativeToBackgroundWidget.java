package io.itch.potAuJeu.widget;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import slickXtension.game.entity.AbstractEntity;

public abstract class RelativeToBackgroundWidget extends AbstractEntity
{
	protected int backgroundOriginalWidth;
	protected int backgroundOriginalHeight;
	
	private String imagePath;
	protected Image image;
	
	private boolean exactPosition = false;

	public RelativeToBackgroundWidget(String imagePath, int x, int y, int backgroundOriginalWidth, int backgroundOriginalHeight)
	{		
		this.imagePath = imagePath;
		
		this.x = x;
		this.y = y;
		
		this.backgroundOriginalWidth = backgroundOriginalWidth;
		this.backgroundOriginalHeight = backgroundOriginalHeight;
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		if(this.imagePath != null)
			this.image = new Image(this.imagePath);
		
		if(!this.exactPosition)
		{
			this.x = (x / this.backgroundOriginalWidth) * container.getWidth();
			this.y = (y / this.backgroundOriginalHeight) * container.getHeight();
	
			if(this.image != null)
			{
				this.width = (int) (((float) this.width / (float) this.backgroundOriginalWidth) * (float) container.getWidth());
				this.height = (int) (((float) this.height / (float) this.backgroundOriginalHeight) * (float) container.getHeight());
			}
			else
			{
				this.width = (int) (((float) this.width / (float) this.backgroundOriginalWidth) * (float) container.getWidth());
				this.height = (int) (((float) this.height / (float) this.backgroundOriginalHeight) * (float) container.getHeight());
			}
		}
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
	{	
		if(this.image != null)
		{
			this.image.draw(this.x, this.y, this.width, this.height);
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
	{
		
	}
	
	public void setToExactPosition()
	{
		this.exactPosition = true;
	}

	public int getBackgroundOriginalWidth() 
	{
		return backgroundOriginalWidth;
	}

	public int getBackgroundOriginalHeight()
	{
		return backgroundOriginalHeight;
	}
}
