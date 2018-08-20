package io.itch.potAuJeu.widget;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import io.itch.potAuJeu.Main;
import slickXtension.managers.SoundManager;

public class SoundCheckBox extends CheckBox
{
	public SoundCheckBox(String label, String uncheckedImagePath, String checkedImagePath, boolean checked, float x, float y)
	{
		super(label, uncheckedImagePath, checkedImagePath, checked, x, y);
	}
	
	public SoundCheckBox(String label, String uncheckedImagePath, String checkedImagePath, boolean checked)
	{
		super(label, uncheckedImagePath, checkedImagePath, checked);
	}
	
	public SoundCheckBox(String label, String uncheckedImagePath, String checkedImagePath)
	{
		super(label, uncheckedImagePath, checkedImagePath);
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
	{
		super.update(container, game, delta);
		
		if(Main.soundMuted)
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
		Main.soundMuted = checked;

		if(checked)
		{
			SoundManager.stopAllSound();
		}
	}

}
