package io.itch.potAuJeu.widget;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import slickXtension.game.entity.AbstractEntity;

public class GameMessage extends AbstractEntity
{
	private String message;
	
	public GameMessage()
	{
		this("");
	}
	
	public GameMessage(String defaultMessage)
	{
		this.message = defaultMessage;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		this.x = 0;
		this.y = 10;
		this.width = 0;
		this.height = 0;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
	{
		this.width = g.getFont().getWidth(this.message);
		this.height = g.getFont().getHeight(this.message);
		
		this.x = (container.getWidth() / 2) - (this.width / 2);
		
		g.setColor(Color.black);
		g.drawString(this.message, this.x, this.y);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
	{
		
	}
	
	public void changeMessage(String message)
	{
		this.message = message;
	}
	
	

	@Override
	public void onMouseClick(GameContainer container, StateBasedGame game, int button, int x, int y, int clickCount)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMouseDragged(GameContainer container, StateBasedGame game, int oldx, int oldy, int newx, int newy)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMouseMoved(GameContainer container, StateBasedGame game, int oldx, int oldy, int newx, int newy)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMousePressed(GameContainer container, StateBasedGame game, int button, int x, int y)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMouseReleased(GameContainer container, StateBasedGame game, int button, int x, int y)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMouseWheelMoved(GameContainer container, StateBasedGame game, int change) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onKeyPressed(GameContainer container, StateBasedGame game, int key, char c) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onKeyReleased(GameContainer container, StateBasedGame game, int key, char c) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onControllerButtonPressed(GameContainer container, StateBasedGame game, int controller, int button)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onControllerButtonReleased(GameContainer container, StateBasedGame game, int controller, int button)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onControllerDownPressed(GameContainer container, StateBasedGame game, int controller)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onControllerDownReleased(GameContainer container, StateBasedGame game, int controller)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onControllerLeftPressed(GameContainer container, StateBasedGame game, int controller)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onControllerLeftReleased(GameContainer container, StateBasedGame game, int controller)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onControllerRightPressed(GameContainer container, StateBasedGame game, int controller)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onControllerRightReleased(GameContainer container, StateBasedGame game, int controller)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onControllerUpPressed(GameContainer container, StateBasedGame game, int controller)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onControllerUpReleased(GameContainer container, StateBasedGame game, int controller)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

}
