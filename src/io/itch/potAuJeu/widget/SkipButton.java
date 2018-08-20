package io.itch.potAuJeu.widget;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import io.itch.potAuJeu.state.GridState;
import slickXtension.managers.AssetsManager;
import slickXtension.managers.CursorManager;
import slickXtension.managers.SoundManager;

public class SkipButton extends RelativeToBackgroundWidget
{
	private GridState state;

	public SkipButton(GridState state, int x, int y, int width, int height, int backgroundOriginalWidth, int backgroundOriginalHeight) 
	{
		super(AssetsManager.getImage("skip_" + state.getGridSize() + "x" + state.getGridSize()), x, y, backgroundOriginalWidth, backgroundOriginalHeight);
		this.state = state;

		this.width = width;
		this.height = height;
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		super.init(container, game);
	}

	@Override
	public void onMouseClick(GameContainer container, StateBasedGame game, int button, int x, int y, int clickCount)
	{
		if(isIn(x, y))
		{
			SoundManager.playSound("click", SoundManager.getPitch(), SoundManager.getVolume());
			this.state.passTurn();
		}
	}
	
	@Override
	public void onMouseMoved(GameContainer container, StateBasedGame game, int oldx, int oldy, int newx, int newy) throws SlickException 
	{
		if(!isIn(oldx, oldy) && isIn(newx, newy))
		{
			CursorManager.applyCursor(CursorManager.CLICKABLE_CURSOR, container);
		}
		else if(isIn(oldx, oldy) && ! isIn(newx, newy))
		{
			CursorManager.applyCursor(container);
		}
	}
	
	@Override
	public void onMouseDragged(GameContainer container, StateBasedGame game, int oldx, int oldy, int newx, int newy) {}
	@Override
	public void onMousePressed(GameContainer container, StateBasedGame game, int button, int x, int y) {}
	@Override
	public void onMouseReleased(GameContainer container, StateBasedGame game, int button, int x, int y) {}
	@Override
	public void onMouseWheelMoved(GameContainer container, StateBasedGame game, int change) {}
	
	@Override
	public void onKeyPressed(GameContainer container, StateBasedGame game, int key, char c) {}
	@Override
	public void onKeyReleased(GameContainer container, StateBasedGame game, int key, char c) {}
	
	@Override
	public void onControllerButtonPressed(GameContainer container, StateBasedGame game, int controller, int button) {}
	@Override
	public void onControllerButtonReleased(GameContainer container, StateBasedGame game, int controller, int button) {}
	@Override
	public void onControllerDownPressed(GameContainer container, StateBasedGame game, int controller) {}
	@Override
	public void onControllerDownReleased(GameContainer container, StateBasedGame game, int controller) {}
	@Override
	public void onControllerLeftPressed(GameContainer container, StateBasedGame game, int controller) {}
	@Override
	public void onControllerLeftReleased(GameContainer container, StateBasedGame game, int controller) {}
	@Override
	public void onControllerRightPressed(GameContainer container, StateBasedGame game, int controller) {}
	@Override
	public void onControllerRightReleased(GameContainer container, StateBasedGame game, int controller) {}
	@Override
	public void onControllerUpPressed(GameContainer container, StateBasedGame game, int controller) {}
	@Override
	public void onControllerUpReleased(GameContainer container, StateBasedGame game, int controller) {}
}
