package io.itch.potAuJeu.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import io.itch.potAuJeu.widget.RelativeToBackgroundWidget;

public abstract class CellEntity extends RelativeToBackgroundWidget
{
	protected int cellX;
	protected int cellY;
	protected GridEntity parent;

	public CellEntity(int cellX, int cellY, GridEntity parent, String imagePath, int x, int y, int width, int height, int backgroundOriginalWidth, int backgroundOriginalHeight)
	{
		super(imagePath, x, y, backgroundOriginalWidth, backgroundOriginalHeight);
		
		this.width = width;
		this.height = height;
		
		this.cellX = cellX;
		this.cellY = cellY;
		this.parent = parent;
	}
	
	public int getCellX()
	{
		return this.cellX;
	}
	
	public int getCellY()
	{
		return this.cellY;
	}
	
	@Override
	public void onMouseClick(GameContainer container, StateBasedGame game, int button, int x, int y, int clickCount) throws SlickException
	{
		if(isIn(x, y))
		{
			this.action(container, game, button, x, y, clickCount);
		}
	}
	
	protected abstract void action(GameContainer container, StateBasedGame game, int button, int x, int y, int clickCount) throws SlickException;

	@Override
	public void onMouseDragged(GameContainer container, StateBasedGame game, int oldx, int oldy, int newx, int newy) throws SlickException
	{}

	@Override
	public void onMouseMoved(GameContainer container, StateBasedGame game, int oldx, int oldy, int newx, int newy) throws SlickException
	{}

	@Override
	public void onMousePressed(GameContainer container, StateBasedGame game, int button, int x, int y) throws SlickException 
	{}

	@Override
	public void onMouseReleased(GameContainer container, StateBasedGame game, int button, int x, int y) throws SlickException
	{}

	@Override
	public void onMouseWheelMoved(GameContainer container, StateBasedGame game, int change) throws SlickException {}

	@Override
	public void onKeyPressed(GameContainer container, StateBasedGame game, int key, char c) throws SlickException {}

	@Override
	public void onKeyReleased(GameContainer container, StateBasedGame game, int key, char c) throws SlickException {}

	@Override
	public void onControllerButtonPressed(GameContainer container, StateBasedGame game, int controller, int button)
			throws SlickException {
	}

	@Override
	public void onControllerButtonReleased(GameContainer container, StateBasedGame game, int controller, int button)
			throws SlickException {
		
	}

	@Override
	public void onControllerDownPressed(GameContainer container, StateBasedGame game, int controller)
			throws SlickException {
		
	}

	@Override
	public void onControllerDownReleased(GameContainer container, StateBasedGame game, int controller)
			throws SlickException {
		
	}

	@Override
	public void onControllerLeftPressed(GameContainer container, StateBasedGame game, int controller)
			throws SlickException {
		
	}

	@Override
	public void onControllerLeftReleased(GameContainer container, StateBasedGame game, int controller)
			throws SlickException {
		
	}

	@Override
	public void onControllerRightPressed(GameContainer container, StateBasedGame game, int controller)
			throws SlickException {
		
	}

	@Override
	public void onControllerRightReleased(GameContainer container, StateBasedGame game, int controller)
			throws SlickException {
		
	}

	@Override
	public void onControllerUpPressed(GameContainer container, StateBasedGame game, int controller)
			throws SlickException {
		
	}

	@Override
	public void onControllerUpReleased(GameContainer container, StateBasedGame game, int controller)
			throws SlickException {
		
	}

}
