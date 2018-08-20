package io.itch.potAuJeu.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import io.itch.potAuJeu.state.GridState;
import slickXtension.managers.AssetsManager;

public class PingCell extends CellEntity
{

	public PingCell(int cellX, int cellY, GridEntity parent, int x, int y, int width, int height, int backgroundOriginalWidth, int backgroundOriginalHeight)
	{
		super(cellX, cellY, parent, AssetsManager.getImage("ping_" + parent.getGridSize() + "x" + parent.getGridSize()), x, y, width, height, backgroundOriginalWidth, backgroundOriginalHeight);
	}

	@Override
	protected void action(GameContainer container, StateBasedGame game, int button, int x, int y, int clickCount) throws SlickException
	{
		if(GridState.getCurrentMode() == GridState.PLACE_MODE)
		{		
			this.parent.activePing(this.cellX, this.cellY, (int) this.x, (int) this.y, this.width, this.height, this.backgroundOriginalWidth, this.backgroundOriginalHeight, button, clickCount);
		}
	}

}