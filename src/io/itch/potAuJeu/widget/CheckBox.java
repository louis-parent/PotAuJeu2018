package io.itch.potAuJeu.widget;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import slickXtension.game.entity.widget.AbstractButton;

public class CheckBox extends AbstractButton
{
	private boolean checked = false;
	
	private String checkedImagePath;
	private Image checkedImage;
	
	private String uncheckedImagePath;
	private Image uncheckedImage;
	
	private String label;
	
	public CheckBox(String label, String uncheckedImagePath, String checkedImagePath, boolean checked, float x, float y)
	{
		super(label, uncheckedImagePath, x, y);
		
		this.checked = checked;
		this.checkedImagePath = checkedImagePath;
		this.uncheckedImagePath = uncheckedImagePath;
		this.label = label;
	}
	
	public CheckBox(String label, String uncheckedImagePath, String checkedImagePath, boolean checked)
	{
		super(label, uncheckedImagePath);
		
		this.checked = checked;
		this.checkedImagePath = checkedImagePath;
		this.uncheckedImagePath = uncheckedImagePath;
		this.label = label;
	}
	
	public CheckBox(String label, String uncheckedImagePath, String checkedImagePath)
	{
		super(label, uncheckedImagePath);
		this.checkedImagePath = checkedImagePath;
		this.uncheckedImagePath = uncheckedImagePath;
		this.label = label;
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		super.init(container, game);
		
		this.checkedImage = new Image(this.checkedImagePath);
		this.uncheckedImage = new Image(this.uncheckedImagePath);
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
	{
		if(this.checked)
		{
			this.checkedImage.draw(this.getX(), this.getY(), this.getWidth(), this.getHeight());
			
			int textHeight = g.getFont().getHeight(this.label);
			g.drawString(this.label, this.getX() + this.checkedImage.getWidth() + 10, this.getY() + (this.height / 2) - (textHeight / 2));
		}
		else
		{
			this.uncheckedImage.draw(this.getX(), this.getY(), this.getWidth(), this.getHeight());
			
			int textHeight = g.getFont().getHeight(this.label);
			g.drawString(this.label, this.getX() + this.uncheckedImage.getWidth() + 10, this.getY() + (this.height / 2) - (textHeight / 2));
		}
	}

	@Override
	@Deprecated
	public void action(GameContainer container, StateBasedGame game)
	{
		this.checked = !this.checked;
		this.action(container, game, this.checked);
	}

	public void action(GameContainer container, StateBasedGame game, boolean checked)
	{
		
	}
	
	public boolean isChecked()
	{
		return this.checked;
	}
	
	public void setUnchecked()
	{
		this.checked = false;
	}
	
	public void setChecked()
	{
		this.checked = true;
	}
}
