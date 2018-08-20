package io.itch.potAuJeu.widget;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import io.itch.potAuJeu.state.GridState;
import slickXtension.game.entity.AbstractEntity;

public class SplashWidget extends AbstractEntity
{
	private GridState parent;
	
	private String imagePath;
	private Image image;
	
	private int duration;
	private float lifeTime = 0;
	
	public SplashWidget(String imagePath, int duration, GridState parent) 
	{
		this.imagePath = imagePath;
		this.duration = duration;
		this.parent = parent;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		this.x = 0;
		this.y = 0;
		
		this.width = container.getWidth();
		this.height = container.getHeight();
		
		this.image = new Image(this.imagePath);
				
		this.parent.pauseInput();
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
	{
		this.image.draw(this.x, this.y, this.width, this.height);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
	{
		this.lifeTime += delta / 1000.0f;
		
		if(this.lifeTime >= this.duration)
		{
			this.parent.removeEntity(this);
			this.parent.restartInput();
		}
	}

	@Override
	public void onMouseClick(GameContainer container, StateBasedGame game, int button, int x, int y, int clickCount)
			throws SlickException {}

	@Override
	public void onMouseDragged(GameContainer container, StateBasedGame game, int oldx, int oldy, int newx, int newy)
			throws SlickException {}

	@Override
	public void onMouseMoved(GameContainer container, StateBasedGame game, int oldx, int oldy, int newx, int newy)
			throws SlickException {}

	@Override
	public void onMousePressed(GameContainer container, StateBasedGame game, int button, int x, int y)
			throws SlickException {}

	@Override
	public void onMouseReleased(GameContainer container, StateBasedGame game, int button, int x, int y)
			throws SlickException {}

	@Override
	public void onMouseWheelMoved(GameContainer container, StateBasedGame game, int change) throws SlickException {}

	@Override
	public void onKeyPressed(GameContainer container, StateBasedGame game, int key, char c) throws SlickException {}

	@Override
	public void onKeyReleased(GameContainer container, StateBasedGame game, int key, char c) throws SlickException {}

	@Override
	public void onControllerButtonPressed(GameContainer container, StateBasedGame game, int controller, int button)
			throws SlickException {}

	@Override
	public void onControllerButtonReleased(GameContainer container, StateBasedGame game, int controller, int button)
			throws SlickException {}

	@Override
	public void onControllerDownPressed(GameContainer container, StateBasedGame game, int controller)
			throws SlickException {}

	@Override
	public void onControllerDownReleased(GameContainer container, StateBasedGame game, int controller)
			throws SlickException {}

	@Override
	public void onControllerLeftPressed(GameContainer container, StateBasedGame game, int controller)
			throws SlickException {}

	@Override
	public void onControllerLeftReleased(GameContainer container, StateBasedGame game, int controller)
			throws SlickException {}

	@Override
	public void onControllerRightPressed(GameContainer container, StateBasedGame game, int controller)
			throws SlickException {}

	@Override
	public void onControllerRightReleased(GameContainer container, StateBasedGame game, int controller)
			throws SlickException {}

	@Override
	public void onControllerUpPressed(GameContainer container, StateBasedGame game, int controller)
			throws SlickException {}

	@Override
	public void onControllerUpReleased(GameContainer container, StateBasedGame game, int controller)
			throws SlickException {}

}
