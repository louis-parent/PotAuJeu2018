package io.itch.potAuJeu.state;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import io.itch.potAuJeu.Main;
import io.itch.potAuJeu.entity.GridEntity;
import io.itch.potAuJeu.widget.CornerMessage;
import io.itch.potAuJeu.widget.GameMessage;
import io.itch.potAuJeu.widget.SplashWidget;
import slickXtension.game.entity.AbstractEntity;
import slickXtension.game.entity.widget.Background;
import slickXtension.game.state.BasicState;
import slickXtension.managers.AssetsManager;
import slickXtension.managers.CursorManager;

public class GridState extends BasicState
{
	public int ID = 0;
	
	public static final int PLAYER_ONE_ID = 1;
	public static final int PLAYER_TWO_ID = 2;
	
	public static final int PLACE_MODE = 0;
	public static final int ERASE_MODE = 1;
	public static final int STAMP_MODE = 2;
	public static final int EXTRA_MOVE_MODE = 3;
	
	private static int currentTurnPlayerId = 1;
	private static int currentMode = 0;
	private static boolean isVictory = false;
	private static boolean forceReset = false;
	
	private int gridSize;
	private GridEntity grid = null;
	private GameMessage message;
	private CornerMessage powerMessage;
	
	public GridState(int id, int gridSize)
	{
		this(gridSize);
		
		ID = id;
	}
	
	public GridState(int gridSize)
	{
		this.gridSize = gridSize;

		this.setBackground(new Background(AssetsManager.getImage("grid_" + String.valueOf(this.gridSize) + "x" + String.valueOf(this.gridSize))));
		
		this.grid = new GridEntity(this.gridSize, this);
		this.addEntity(this.grid);
		
		if(Main.isDebug)
		{
			this.message = new GameMessage("Au tour de joueur 1");
			this.powerMessage = new CornerMessage("En Placement");
			
			this.addEntity(this.message);
			this.addEntity(this.powerMessage);
		}
	}
	
	@Override
	public int getID()
	{
		return ID;
	}
	
	public boolean verifyVictory()
	{
		if(this.grid.hasVictory())
		{
			isVictory = true;
			this.game.enterState(currentTurnPlayerId == 1 ? 4 : 5);
			
			if(Main.isDebug)
			{
				this.message.changeMessage("Victoire de Joueur " + currentTurnPlayerId);
			}
			
			return true;
		}
		else if(this.grid.isFull())
		{
			isVictory = true;
			this.game.enterState(6);
			
			if(Main.isDebug)
			{
				this.message.changeMessage("Match Null");
			}
			
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void passTurn()
	{
		boolean victory = verifyVictory();
		
		if(!victory)
		{
			currentTurnPlayerId = currentTurnPlayerId == 1 ? 2 : 1;
			this.changeCurrentMode(PLACE_MODE);
			
			if(Main.isDebug)
			{
				this.message.changeMessage("Au tour de Joueur " + currentTurnPlayerId);
			}
		}
	}
	
	public static int getCurrentTurnPlayerID()
	{
		return currentTurnPlayerId;
	}
	
	public static int getCurrentMode()
	{
		return currentMode;
	}
	
	public void changeCurrentMode(int mode)
	{
		currentMode = mode;
		if(Main.isDebug)
		{
			this.powerMessage.changeMessage(currentMode == PLACE_MODE ? "En Placement" : currentMode == ERASE_MODE ? "Effacement" : currentMode == STAMP_MODE ? "Tamponement" : currentMode == EXTRA_MOVE_MODE ? "Mouvement Supplémentaire" : "");
		}
	}
	
	public static boolean isVictory()
	{
		return isVictory;
	}
	
	public static void forceReset()
	{
		GridState.forceReset = true;
	}
	
	public int getGridSize()
	{
		return this.gridSize;
	}
	
	public void removeEntity(AbstractEntity entity)
	{
		this.entities.remove(entity);
	}
	
	public void pauseInput()
	{
		this.controller.setDeaf();
	}
	
	public void restartInput()
	{
		this.controller.setListening();
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		super.enter(container, game);
		CursorManager.applyCursor(container);
		
		if(GridState.forceReset)
		{
			game.enterState(MainMenuState.ID);
		}
	}
	
	
	@Override
	public void leave(GameContainer container, StateBasedGame game) throws SlickException 
	{
		super.leave(container, game);
		if(GridState.isVictory || GridState.forceReset)
		{
			this.removeEntity(this.grid);
			this.grid = new GridEntity(this.gridSize, this);
			this.addEntity(this.grid);
			
			this.grid.init(container, game);
			GridState.isVictory = false;
			GridState.forceReset = false;
			
			for(int i = 0; i < this.entities.size(); i++)
			{
				if(this.entities.get(i) instanceof SplashWidget)
				{
					this.entities.remove(this.entities.get(i));
				}
			}
		}
	}
	
	@Override
	public void keyPressed(int key, char c) 
	{
		if(key == Keyboard.KEY_ESCAPE)
		{
			BasicPauseState.changeGoBackStateID(this.ID);
			this.game.enterState(BasicPauseState.ID);
		}
		else
		{
			super.keyPressed(key, c);
		}
	}
}
