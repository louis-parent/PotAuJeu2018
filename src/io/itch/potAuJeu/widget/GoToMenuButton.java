package io.itch.potAuJeu.widget;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import io.itch.potAuJeu.state.BasicPauseState;
import io.itch.potAuJeu.state.GridState;
import slickXtension.game.entity.widget.AbstractButton;

public class GoToMenuButton extends AbstractButton
{

	public GoToMenuButton(String text, String backgroundPath, float x, float y)
	{
		super(text, backgroundPath, x, y);
	}
	
	public GoToMenuButton(String text, String backgroundPath)
	{
		super(text, backgroundPath);
	}

	@Override
	public void action(GameContainer container, StateBasedGame game)
	{
		GridState.forceReset();
		
		game.enterState(BasicPauseState.getGoBackStateID());
	}

}
