package io.itch.potAuJeu.state;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import io.itch.potAuJeu.Main;
import slickXtension.game.entity.widget.Background;
import slickXtension.game.state.BasicState;
import slickXtension.managers.AssetsManager;
import slickXtension.managers.CursorManager;
import slickXtension.managers.SoundManager;

public class EndState extends BasicState
{
	public static final int J1_VICTORY = 1;
	public static final int J2_VICTORY = 2;
	public static final int NULL_VICTORY = 0;
	
	public int ID = 1;
	
	private StateBasedGame game;
	private int stayTime = 0;
	private boolean timePassed = false;
	
	public EndState(int id, int endType)
	{
		this(endType);
		
		ID = id;
	}
	
	public EndState(int endType)
	{
		this.setBackground(new Background(AssetsManager.getImage(endType == 1 ? "j1_victory" : (endType == 2 ? "j2_victory" : "null_victory"))));
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException 
	{
		super.init(container, game);
		
		this.game = game;
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
	{
		super.update(container, game, delta);
		
		if(this.stayTime >= 10 * container.getFPS() && !this.timePassed)
		{
			this.timePassed = true;
			if(!Main.musicMuted)
				SoundManager.loopMusic("theme", 1.0f, 0.1f);
		}
		else
		{
			this.stayTime += delta;
		}
	}
	
	@Override
	public void mouseClicked(int button, int x, int y, int clickCount)
	{
		game.enterState(MainMenuState.ID);
	}
	
	@Override
	public void keyPressed(int key, char c) 
	{
		game.enterState(MainMenuState.ID);
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException 
	{
		super.enter(container, game);
		CursorManager.applyCursor(CursorManager.CLICKABLE_CURSOR, container);
		if(!Main.musicMuted)
			SoundManager.playMusic("end", 1.0f, 0.5f);
	}
	
	@Override
	public void leave(GameContainer container, StateBasedGame game) throws SlickException
	{
		super.leave(container, game);
		
		this.stayTime = 0;
		this.timePassed = false;
	}
	
	@Override
	public int getID() 
	{
		return ID;
	}

}
