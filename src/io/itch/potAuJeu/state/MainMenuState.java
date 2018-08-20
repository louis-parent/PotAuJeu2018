package io.itch.potAuJeu.state;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import io.itch.potAuJeu.Main;
import io.itch.potAuJeu.widget.MusicCheckBox;
import io.itch.potAuJeu.widget.SoundCheckBox;
import io.itch.potAuJeu.widget.UpperLeftCorner;
import slickXtension.game.entity.widget.Background;
import slickXtension.game.entity.widget.Frame;
import slickXtension.game.entity.widget.buttons.QuitButton;
import slickXtension.game.entity.widget.buttons.StateChangeButton;
import slickXtension.game.entity.widget.layout.VerticalLayout;
import slickXtension.game.state.BasicState;
import slickXtension.managers.AssetsManager;
import slickXtension.managers.CursorManager;
import slickXtension.managers.SoundManager;

public class MainMenuState extends BasicState
{
	public static int ID = 0;
	
	public MainMenuState()
	{
		Frame frame = new Frame(AssetsManager.getImage("logo"));
		
		StateChangeButton button44 = new StateChangeButton("", AssetsManager.getImage("4x4_button"), 7);
		button44.changeSoundName("click");
		StateChangeButton button66 = new StateChangeButton("", AssetsManager.getImage("6x6_button"), 2);
		button66.changeSoundName("click");
		StateChangeButton button88 = new StateChangeButton("", AssetsManager.getImage("8x8_button"), 3);
		button88.changeSoundName("click");
		QuitButton quit = new QuitButton("", AssetsManager.getImage("quit_button"));
		quit.changeSoundName("click");
		
		VerticalLayout layout = new VerticalLayout();
		
		layout.addToLayout(frame);
		layout.addToLayout(button44);
		layout.addToLayout(button66);
		layout.addToLayout(button88);
		layout.addToLayout(quit);

		this.addEntity(layout);
		
		SoundCheckBox sound = new SoundCheckBox("", AssetsManager.getImage("sound_unmuted_button"), AssetsManager.getImage("sound_muted_button"));
		sound.changeSoundName("click");
		MusicCheckBox music = new MusicCheckBox("", AssetsManager.getImage("music_unmuted_button"), AssetsManager.getImage("music_muted_button"));
		music.changeSoundName("click");
		
		UpperLeftCorner corner = new UpperLeftCorner();
		
		corner.addToLayout(music);
		corner.addToLayout(sound);
		
		this.addEntity(corner);

		this.setBackground(new Background(AssetsManager.getImage("empty_background")));
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		super.init(container, game);
		
		CursorManager.setCursor(CursorManager.DEFAULT_CURSOR, CursorManager.getCursorImage("mouse_pencil"));
		CursorManager.setCursor(CursorManager.CLICKABLE_CURSOR, CursorManager.getCursorImage("mouse_click"));
		CursorManager.changeCursorHotspot(CursorManager.CLICKABLE_CURSOR, 5, 0);
		CursorManager.applyCursor(container);
		
		SoundManager.loadSound("click", SoundManager.findSound("click"));
		SoundManager.loadSound("powerSound", SoundManager.findSound("power"));
		SoundManager.loadSound("write", SoundManager.findSound("write"));
		
		SoundManager.loadMusic("theme", SoundManager.findMusic("music"));
		SoundManager.loadMusic("end", SoundManager.findMusic("victory"));
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		super.enter(container, game);
		
		CursorManager.applyCursor(container);
		if(!Main.musicMuted)
			SoundManager.loopMusic("theme", 1.0f, 0.1f);
	}
	
	@Override
	public int getID()
	{
		return ID;
	}

}
