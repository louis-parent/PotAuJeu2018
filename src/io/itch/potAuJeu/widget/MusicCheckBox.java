package io.itch.potAuJeu.widget;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import io.itch.potAuJeu.Main;
import slickXtension.managers.SoundManager;

public class MusicCheckBox extends CheckBox
{
	public MusicCheckBox(String label, String uncheckedImagePath, String checkedImagePath, boolean checked, float x, float y)
	{
		super(label, uncheckedImagePath, checkedImagePath, checked, x, y);
	}
	
	public MusicCheckBox(String label, String uncheckedImagePath, String checkedImagePath, boolean checked)
	{
		super(label, uncheckedImagePath, checkedImagePath, checked);
	}
	
	public MusicCheckBox(String label, String uncheckedImagePath, String checkedImagePath)
	{
		super(label, uncheckedImagePath, checkedImagePath);
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
	{
		super.update(container, game, delta);
		
		if(Main.musicMuted)
		{
			this.setChecked();
		}
		else
		{
			this.setUnchecked();
		}
	}
	
	@Override
	public void action(GameContainer container, StateBasedGame game, boolean checked)
	{
		Main.musicMuted = checked;

		if(checked)
		{
			SoundManager.stopMusic();
		}
		else
		{
			SoundManager.loopMusic("theme", 1.0f, 0.1f);
		}
	}

}
