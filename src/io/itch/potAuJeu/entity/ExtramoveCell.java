package io.itch.potAuJeu.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import io.itch.potAuJeu.state.GridState;
import slickXtension.managers.AssetsManager;

public class ExtramoveCell  extends CellEntity
{

	public ExtramoveCell(int cellX, int cellY, GridEntity parent, int x, int y, int width, int height, int backgroundOriginalWidth, int backgroundOriginalHeight)
	{
		super(cellX, cellY, parent, AssetsManager.getImage("extra_moves_" + parent.getGridSize() + "x" + parent.getGridSize()), x, y, width, height, backgroundOriginalWidth, backgroundOriginalHeight);
	}

	@Override
	protected void action(GameContainer container, StateBasedGame game, int button, int x, int y, int clickCount) throws SlickException
	{
		if(GridState.getCurrentMode() == GridState.PLACE_MODE)
		{
			this.parent.activeExtraMove(this.cellX, this.cellY, (int) this.x, (int) this.y, this.width, this.height, this.backgroundOriginalWidth, this.backgroundOriginalHeight);
		}
	}

}
