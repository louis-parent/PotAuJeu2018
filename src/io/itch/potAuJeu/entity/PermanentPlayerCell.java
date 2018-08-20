package io.itch.potAuJeu.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import slickXtension.managers.AssetsManager;

public class PermanentPlayerCell extends CellEntity
{
	protected int playerId;

	public PermanentPlayerCell(int playerId, int cellX, int cellY, GridEntity parent, int x, int y, int width, int height, int backgroundOriginalWidth, int backgroundOriginalHeight) {
		super(cellX, cellY, parent, (playerId == 1 ? AssetsManager.getImage("circle_perm_" + parent.getGridSize() + "x" + parent.getGridSize()) : AssetsManager.getImage("cross_perm_" + parent.getGridSize() + "x" + parent.getGridSize())), x, y, width, height, backgroundOriginalWidth, backgroundOriginalHeight);
		
		this.playerId = playerId;
	}
	
	@Override
	protected void action(GameContainer container, StateBasedGame game, int button, int x, int y, int clickCount) throws SlickException 
	{
	}
	
	public int getPlayer()
	{
		return this.playerId;
	}
}
