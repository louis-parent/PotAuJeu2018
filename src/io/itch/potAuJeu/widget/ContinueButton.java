package io.itch.potAuJeu.widget;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import io.itch.potAuJeu.state.BasicPauseState;
import slickXtension.game.entity.widget.AbstractButton;

/**
 * A button to continue the game from the BasicPauseState
 * @author Appixroid
 *
 */
public class ContinueButton extends AbstractButton
{	
	/**
	 * Build a AbstractButton Object with a text inside and a background image
	 * @param text : the label text of the button
	 * @param backgroundPath : the path to the background image file
	 * @param stateToGo : the id of the state to go on click
	 */
	public ContinueButton(String text, String backgroundPath)
	{
		super(text, backgroundPath);
	}
	
	/**
	 * 
	 * Build a AbstractButton Object with a text inside and a background image
	 * @param text : the label text of the button
	 * @param backgroundPath : the path to the background image file
	 * @param stateToGo : the id of the state to go on click
	 * @param x : the x postion of the button
	 * @param y : the y position of the button
	 */
	public ContinueButton(String text, String backgroundPath, float x, float y)
	{
		super(text, backgroundPath, x, y);
	}

	@Override
	public void action(GameContainer container, StateBasedGame game)
	{
		game.enterState(BasicPauseState.getGoBackStateID());
	}

}
