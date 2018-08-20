package io.itch.potAuJeu.entity;

import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import io.itch.potAuJeu.state.GridState;
import slickXtension.managers.AssetsManager;

public class RandomCell extends CellEntity
{

	public RandomCell(int cellX, int cellY, GridEntity parent, int x, int y, int width, int height, int backgroundOriginalWidth, int backgroundOriginalHeight)
	{
		super(cellX, cellY, parent, AssetsManager.getImage("random_" + parent.getGridSize() + "x" + parent.getGridSize()), x, y, width, height, backgroundOriginalWidth, backgroundOriginalHeight);
	}

	@Override
	protected void action(GameContainer container, StateBasedGame game, int button, int x, int y, int clickCount) throws SlickException
	{
		if(GridState.getCurrentMode() == GridState.PLACE_MODE)
		{
			/*SplashWidget splash = new SplashWidget(AssetsManager.getImage("random_splash"), 3, this.parent.getState());
			splash.init(container, game);
			this.parent.getState().addEntity(splash);*/
			
			Random rand = new Random();
			int transform = rand.nextInt(6);
			
			switch(transform)
			{
				case 1:
					this.parent.activeErase(this.cellX, this.cellY, (int) this.x, (int) this.y, this.width, this.height, this.backgroundOriginalWidth, this.backgroundOriginalHeight);
					break;
					
				case 2:
					this.parent.activeExtraMove(this.cellX, this.cellY, (int) this.x, (int) this.y, this.width, this.height, this.backgroundOriginalWidth, this.backgroundOriginalHeight);
					break;
					
				case 3:
					this.parent.activePermanent(this.cellX, this.cellY, (int) this.x, (int) this.y, this.width, this.height, this.backgroundOriginalWidth, this.backgroundOriginalHeight);
					break;
					
				case 4:
					this.parent.activePing(this.cellX, this.cellY, (int) this.x, (int) this.y, this.width, this.height, this.backgroundOriginalWidth, this.backgroundOriginalHeight, button, clickCount);
					break;
					
				case 5:
					this.parent.activeStamp(this.cellX, this.cellY, (int) this.x, (int) this.y, this.width, this.height, this.backgroundOriginalWidth, this.backgroundOriginalHeight);
					break;
					
				default:
				case 0:
					this.parent.activeBlank(this.cellX, this.cellY, (int) this.x, (int) this.y, this.width, this.height, this.backgroundOriginalWidth, this.backgroundOriginalHeight);
			}
		}
	}

}
