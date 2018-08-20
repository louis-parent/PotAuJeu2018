package io.itch.potAuJeu.widget;

import org.newdawn.slick.GameContainer;

import slickXtension.game.entity.widget.layout.AbstractLayout;

public class UpperLeftCorner extends AbstractLayout
{

	@Override
	protected void positionedEntities(GameContainer container)
	{
		this.entities.get(0).setX(5);
		this.entities.get(0).setY(5);
		this.entities.get(0).setWidth(container.getWidth() / 11);
		this.entities.get(0).setHeight(container.getHeight() / 8);

		this.entities.get(1).setX(this.entities.get(0).getWidth() + 10);
		this.entities.get(1).setY(5);
		this.entities.get(1).setWidth(container.getWidth() / 11);
		this.entities.get(1).setHeight(container.getHeight() / 8);
	}

	@Override
	protected int getContentWidth()
	{
		return this.width / (this.entities.size() + 5);
	}

	@Override
	protected int getContentHeight()
	{
		return this.height / (this.entities.size() + 5);
	}

}
