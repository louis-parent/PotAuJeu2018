package io.itch.potAuJeu.state;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import io.itch.potAuJeu.Loader;
import slickXtension.game.entity.AbstractEntity;
import slickXtension.game.entity.widget.Background;
import slickXtension.game.entity.widget.Frame;
import slickXtension.game.entity.widget.layout.VerticalLayout;
import slickXtension.game.state.BasicState;

public class BasicMainMenuState extends BasicState
{
	public static int ID = 0;
	
	private Loader loader = null;
	
	/**
	 * Build a Main Menu with the logo of the game at the top, a list of content (e.g. buttons for game mode) of the menu and a bakcground
	 * Allow to use a loader on the init of the state to preload assets for the game like music
	 * @param logoPath : the path to the logo file
	 * @param menuContent : an array of entity corresponding to the menu items
	 * @param backgroundPath : the path to the background file
	 * @param loader : the loader who will be used in the init of the state
	 */
	public BasicMainMenuState(String logoPath, ArrayList<AbstractEntity> menuContent, String backgroundPath, Loader loader)
	{
		this(logoPath, menuContent, backgroundPath);
		
		this.loader = loader;
	}
	
	/**
	 * Build a Main Menu with the logo of the game at the top, a list of content (e.g. buttons for game mode) of the menu and a bakcground
	 * @param logoPath : the path to the logo file
	 * @param menuContent : an array of entity corresponding to the menu items
	 * @param backgroundPath : the path to the background file
	 */
	public BasicMainMenuState(String logoPath, ArrayList<AbstractEntity> menuContent, String backgroundPath)
	{
		this(logoPath, menuContent);
		
		this.setBackground(new Background(backgroundPath));
	}
	
	/**
	 * Build a Main Menu with the logo of the game at the top and a list of content (e.g. buttons for game mode) of the menu
	 * @param logoPath : the path to the logo file
	 * @param menuContent : an array of entity corresponding to the menu items
	 */
	public BasicMainMenuState(String logoPath, ArrayList<AbstractEntity> menuContent)
	{
		Frame frame = new Frame(logoPath);
		
		VerticalLayout layout = new VerticalLayout();
		
		layout.addToLayout(frame);
		
		for(int i = 0; i < menuContent.size(); i++)
		{
			layout.addToLayout(menuContent.get(i));
		}

		this.addEntity(layout);
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		super.init(container, game);
		
		if(this.loader != null)
		{
			this.loader.load(container, game);
		}
	}
	
	@Override
	public int getID()
	{
		return ID;
	}

}