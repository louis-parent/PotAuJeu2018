package io.itch.potAuJeu.entity;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import io.itch.potAuJeu.Main;
import io.itch.potAuJeu.state.GridState;
import slickXtension.managers.AssetsManager;
import slickXtension.managers.SoundManager;

public class PlayerCell  extends CellEntity
{
	protected int playerId;
	
	public PlayerCell(int playerId, int cellX, int cellY, GridEntity parent, int x, int y, int width, int height, int backgroundOriginalWidth, int backgroundOriginalHeight) {
		super(cellX, cellY, parent, playerId == 1 ? AssetsManager.getImage("circle_" + parent.getGridSize() + "x" + parent.getGridSize()) : AssetsManager.getImage("cross_" + parent.getGridSize() + "x" + parent.getGridSize()), x, y, width, height, backgroundOriginalWidth, backgroundOriginalHeight);
		this.playerId = playerId;
	}
	
	public void changePlayer(int playerId) throws SlickException
	{
		this.playerId = playerId;
		this.image = new Image(playerId == 1 ? AssetsManager.getImage("circle_" + parent.getGridSize() + "x" + parent.getGridSize()) : AssetsManager.getImage("cross_" + parent.getGridSize() + "x" + parent.getGridSize()));
	}
	
	public int getPlayer()
	{
		return this.playerId;
	}

	@Override
	protected void action(GameContainer container, StateBasedGame game, int button, int x, int y, int clickCount) throws SlickException
	{
		if(GridState.getCurrentMode() == GridState.ERASE_MODE)
		{
			if(!Main.soundMuted)
				SoundManager.playSound("write", 1.0f, 0.3f);

			BlankCell cell = new BlankCell(this.cellX, this.cellY, this.parent, (int) this.x, (int) this.y, this.width, this.height, this.backgroundOriginalWidth, this.backgroundOriginalHeight);
			cell.setToExactPosition();
			
			this.parent.changeCell(this.cellX, this.cellY, cell);
			this.parent.passTurn();
		}
		else if(GridState.getCurrentMode() == GridState.STAMP_MODE && this.playerId != GridState.getCurrentTurnPlayerID())
		{
			if(!Main.soundMuted)
				SoundManager.playSound("write", 1.0f, 0.3f);

			this.changePlayer(GridState.getCurrentTurnPlayerID());
			this.parent.passTurn();
		}
	}
}