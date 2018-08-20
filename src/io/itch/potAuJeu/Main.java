package io.itch.potAuJeu;

import java.util.ArrayList;

import org.newdawn.slick.state.BasicGameState;

import io.itch.potAuJeu.state.BasicPauseState;
import io.itch.potAuJeu.state.EndState;
import io.itch.potAuJeu.state.GridState;
import io.itch.potAuJeu.state.MainMenuState;
import io.itch.potAuJeu.widget.ContinueButton;
import io.itch.potAuJeu.widget.GoToMenuButton;
import io.itch.potAuJeu.widget.MusicCheckBox;
import io.itch.potAuJeu.widget.SoundCheckBox;
import slickXtension.game.GameStarter;
import slickXtension.game.entity.widget.Frame;
import slickXtension.game.entity.widget.layout.VerticalLayout;
import slickXtension.managers.AssetsManager;

public class Main 
{
	public static boolean isDebug = false;
	
	public static boolean musicMuted = false;
	public static boolean soundMuted = false;
	
	public static void main(String[] args)
	{		
		ArrayList<BasicGameState> states = new ArrayList<BasicGameState>();
		states.add(new MainMenuState());
		
		VerticalLayout layout = new VerticalLayout();
		
		Frame frame = new Frame(AssetsManager.getImage("logo"));
		SoundCheckBox sound = new SoundCheckBox("", AssetsManager.getImage("sound_unmuted_button"), AssetsManager.getImage("sound_muted_button"));
		sound.changeSoundName("click");
		MusicCheckBox music = new MusicCheckBox("", AssetsManager.getImage("music_unmuted_button"), AssetsManager.getImage("music_muted_button"));
		music.changeSoundName("click");
		ContinueButton continueButton = new ContinueButton("", AssetsManager.getImage("continue_button"));
		continueButton.changeSoundName("click");
		GoToMenuButton goToMenu = new GoToMenuButton("", AssetsManager.getImage("go_to_menu_button"));
		goToMenu.changeSoundName("click");

		layout.addToLayout(frame);
		layout.addToLayout(sound);
		layout.addToLayout(music);
		layout.addToLayout(continueButton);
		layout.addToLayout(goToMenu);

		states.add(new BasicPauseState(layout, AssetsManager.getImage("empty_background")));
		
		states.add(new GridState(7, 4));
		states.add(new GridState(2, 6));
		states.add(new GridState(3, 8));
		states.add(new EndState(4, EndState.J1_VICTORY));
		states.add(new EndState(5, EndState.J2_VICTORY));
		states.add(new EndState(6, EndState.NULL_VICTORY));

		GameStarter.startGame("Tic Tac Pow'", states);
	}

}
