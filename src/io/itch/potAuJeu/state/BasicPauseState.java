package io.itch.potAuJeu.state;

import org.lwjgl.input.Keyboard;

import slickXtension.game.entity.AbstractEntity;
import slickXtension.game.entity.widget.Background;
import slickXtension.game.state.BasicState;

public class BasicPauseState extends BasicState
{
	public static int ID = 1;

	/**
	 * The ID of the state to return
	 */
	private static int goBackStateID;
	
	/**
	 * Create a Pause State with a simple content and a default background
	 * @param content : the content of the state
	 * @param backgroundPath : the path to the background file
	 */
	public BasicPauseState(AbstractEntity content, String backgroundPath)
	{
		this(content);
		this.setBackground(new Background(backgroundPath));
	}
	
	/**
	 * Create a Pause State with a simple content
	 * @param content : the content of the state
	 */
	public BasicPauseState(AbstractEntity content)
	{
		this.entities.add(content);
	}
	
	/**
	 * Change the go back state id used by the pause state
	 * @param id : the new id where to go back
	 */
	public static void changeGoBackStateID(int id)
	{
		goBackStateID = id;
	}
	
	/**
	 * Retrieve the curent state to go back
	 * @return The id of the go back state
	 */
	public static int getGoBackStateID()
	{
		return BasicPauseState.goBackStateID;
	}
	
	@Override
	public void keyPressed(int key, char c)
	{
		if(key == Keyboard.KEY_ESCAPE)
		{
			this.game.enterState(BasicPauseState.goBackStateID);
		}
		else
		{
			super.keyPressed(key, c);
		}
	}
	
	@Override
	public int getID()
	{
		return ID;
	}

}
