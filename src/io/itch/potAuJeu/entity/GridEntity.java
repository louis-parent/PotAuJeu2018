package io.itch.potAuJeu.entity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import io.itch.potAuJeu.Main;
import io.itch.potAuJeu.state.GridState;
import io.itch.potAuJeu.widget.SkipButton;
import io.itch.potAuJeu.widget.SplashWidget;
import slickXtension.game.entity.AbstractEntity;
import slickXtension.managers.AssetsManager;
import slickXtension.managers.SoundManager;

public class GridEntity extends AbstractEntity
{
	private int gridSize;
	
	private CellEntity[][] cells;
	private int cellWidth;
	private int cellHeight;
	
	private int gridBackgroundWidth;
	private int gridBackgroundHeight;
	
	private GameContainer container;
	private StateBasedGame game;
	private GridState state;
	
	private int turnCount = 0;
	private boolean addForTurn = false;
	
	public GridEntity(int gridSize, GridState state) 
	{
		this.gridSize = gridSize;
		this.state = state;
		
		this.createGrid();
	}
	
	public void createGrid()
	{
		this.cells = new CellEntity[this.gridSize][this.gridSize];
		
		int[] widths = null;
		int[] heights = null;
		
		int buttonX = 0;
		int buttonY = 0;
		
		BufferedReader br;
		
		//Lecture du fichier GRID
		try 
		{
			br = new BufferedReader(new FileReader(new File("assets/grid/" + this.gridSize + "x" + this.gridSize + ".grid")));
			
			String line;
			int count = 0;
			System.out.println("GRID " + this.gridSize + "x" + this.gridSize + " loaded with parameters");
			
			while ((line = br.readLine()) != null)
			{
				System.out.println(line);
				
				if(count == 0)
				{
					String[] size = line.split("x");
					this.gridBackgroundWidth = Integer.parseInt(size[0]);
					this.gridBackgroundHeight = Integer.parseInt(size[1]);
				}
				else if(count == 1)
			   {
					String[] size = line.split("x");
					   this.cellWidth = Integer.parseInt(size[0]);
					   this.cellHeight = Integer.parseInt(size[1]);
			   }
			   else if(count == 2)
			   {
				   String[] numbers = line.split(":");
				   
				   if(numbers.length != this.gridSize)
				   {
					   System.err.println("Incorrect file parsing");
				   }
				   else
				   {
					   widths = new int[numbers.length];
						for(int i = 0; i < numbers.length; i++)
						{
							widths[i] = Integer.parseInt(numbers[i]);
						}
				   }
			   }
			   else if (count == 3)
			   {
				   String[] numbers = line.split(":");
				   
				   if(numbers.length != this.gridSize)
				   {
					   System.err.println("Incorrect file parsing");
				   }
				   else
				   {
					   heights = new int[numbers.length];
						for(int i = 0; i < numbers.length; i++)
						{
							heights[i] = Integer.parseInt(numbers[i]);
						}
				   }
			   }
			   else if (count == 4)
			   {
				   String[] size = line.split(":");
				   buttonX = Integer.parseInt(size[0]);
				   buttonY = Integer.parseInt(size[1]);
			   }
			   else if(count == 5)
			   {
				   String[] size = line.split("x");
				   int buttonWidth = Integer.parseInt(size[0]);
				   int buttonHeight = Integer.parseInt(size[1]);
				   
				   this.state.addEntity(new SkipButton(this.state, buttonX, buttonY, buttonWidth, buttonHeight, this.gridBackgroundWidth, this.gridBackgroundHeight));
			   }
				count++;
			}
			
			System.out.println("\n");
			br.close();
			
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
		//Placement des cases vides
		if(widths != null && heights != null)
		{
			for(int y = 0; y < heights.length; y++)
			{
				for(int x = 0; x < widths.length; x++)
				{		
					this.cells[y][x] = new BlankCell(x, y, this, widths[x], heights[y], cellWidth, cellHeight, this.gridBackgroundWidth, this.gridBackgroundHeight);
				}
			}
		}
		
		//Placement des cases spéciales
		int cellQuantity = this.gridSize * this.gridSize;
		int specialQuantity = cellQuantity / 3;
		Random rand = new Random();
		
		int eraseQuantity = specialQuantity / 4;
		int permanentQuantity = specialQuantity / 3;
		int extramoveQuantity = specialQuantity / 6;
		int stampQuantity = specialQuantity / 4;
		int pingQuantity = specialQuantity / 5;
		int randomQuantity = specialQuantity / 3;
		
		for(int i = 0; i < eraseQuantity; i++)
		{
			boolean valid = false;
			
			while(!valid)
			{
				int cellX = rand.nextInt(this.gridSize);
				int cellY = rand.nextInt(this.gridSize);
				
				if(this.getCell(cellX, cellY) instanceof BlankCell)
				{
					this.cells[cellY][cellX] = new EraseCell(cellX, cellY, this, widths[cellX], heights[cellY], this.cellWidth, this.cellHeight, this.gridBackgroundWidth, this.gridBackgroundHeight);
					valid = true;
				}
			}
		}
		
		for(int i = 0; i < permanentQuantity; i++)
		{
			boolean valid = false;
			
			while(!valid)
			{
				int cellX = rand.nextInt(this.gridSize);
				int cellY = rand.nextInt(this.gridSize);
				
				if(this.getCell(cellX, cellY) instanceof BlankCell)
				{
					this.cells[cellY][cellX] = new PermanentCell(cellX, cellY, this, widths[cellX], heights[cellY], this.cellWidth, this.cellHeight, this.gridBackgroundWidth, this.gridBackgroundHeight);
					valid = true;
				}
			}
		}
		
		for(int i = 0; i < extramoveQuantity; i++)
		{
			boolean valid = false;
			
			while(!valid)
			{
				int cellX = rand.nextInt(this.gridSize);
				int cellY = rand.nextInt(this.gridSize);
				
				if(this.getCell(cellX, cellY) instanceof BlankCell)
				{
					this.cells[cellY][cellX] = new ExtramoveCell(cellX, cellY, this, widths[cellX], heights[cellY], this.cellWidth, this.cellHeight, this.gridBackgroundWidth, this.gridBackgroundHeight);
					valid = true;
				}
			}
		}
		
		for(int i = 0; i < stampQuantity; i++)
		{
			boolean valid = false;
			
			while(!valid)
			{
				int cellX = rand.nextInt(this.gridSize);
				int cellY = rand.nextInt(this.gridSize);
				
				if(this.getCell(cellX, cellY) instanceof BlankCell)
				{
					this.cells[cellY][cellX] = new StampCell(cellX, cellY, this, widths[cellX], heights[cellY], this.cellWidth, this.cellHeight, this.gridBackgroundWidth, this.gridBackgroundHeight);
					valid = true;
				}
			}
		}
		
		for(int i = 0; i < pingQuantity; i++)
		{
			boolean valid = false;
			
			while(!valid)
			{
				int cellX = rand.nextInt(this.gridSize);
				int cellY = rand.nextInt(this.gridSize);
				
				if(this.getCell(cellX, cellY) instanceof BlankCell)
				{
					this.cells[cellY][cellX] = new PingCell(cellX, cellY, this, widths[cellX], heights[cellY], this.cellWidth, this.cellHeight, this.gridBackgroundWidth, this.gridBackgroundHeight);
					valid = true;
				}
			}
		}
		
		for(int i = 0; i < randomQuantity; i++)
		{
			boolean valid = false;
			
			while(!valid)
			{
				int cellX = rand.nextInt(this.gridSize);
				int cellY = rand.nextInt(this.gridSize);
				
				if(this.getCell(cellX, cellY) instanceof BlankCell)
				{
					this.cells[cellY][cellX] = new RandomCell(cellX, cellY, this, widths[cellX], heights[cellY], this.cellWidth, this.cellHeight, this.gridBackgroundWidth, this.gridBackgroundHeight);
					valid = true;
				}
			}
		}
	}
	
	public void changeCell(int x, int y, CellEntity newCell) throws SlickException
	{
		this.cells[y][x] = newCell;
		newCell.init(container, game);
	}
	
	private boolean isSame(int[] tab)
	{
		int prev = tab[0];
		boolean same = tab[0] > 0 ? true : false;
		int i = 1;
		
		while(same && i < tab.length)
		{
			same = tab[i] == prev;
			i++;
		}
		
		return same;
	}
	
	private boolean checkRows()
	{
		boolean victory = false;
		int i = 0;

		while(!victory && i < this.gridSize)
		{
			int[] players = new int[this.gridSize];
			
			for(int x = 0; x < this.gridSize; x++)
			{
				if(this.cells[i][x] instanceof PlayerCell)
				{
					players[x] =  ((PlayerCell) this.cells[i][x]).getPlayer();
				}
				else if(this.cells[i][x] instanceof PermanentPlayerCell)
				{
					players[x] =  ((PermanentPlayerCell) this.cells[i][x]).getPlayer();
				}
				else
				{
					players[x] = -1;
				}
			}
			
			if(isSame(players))
			{
				victory = true;
			}
			
			i++;
		}
		return victory;
	}
	
	private boolean checkColumns()
	{
		boolean victory = false;
		int i = 0;
		
		while(!victory && i < this.gridSize)
		{
			int[] players = new int[this.gridSize];
			
			for(int y = 0; y < this.gridSize; y++)
			{
				if(this.cells[y][i] instanceof PlayerCell)
				{
					players[y] =  ((PlayerCell) this.cells[y][i]).getPlayer();
				}
				else if(this.cells[y][i] instanceof PermanentPlayerCell)
				{
					players[y] =  ((PermanentPlayerCell) this.cells[y][i]).getPlayer();
				}
				else
				{
					players[y] = -1;
				}
			}
			
			if(isSame(players))
			{
				victory = true;
			}
			
			i++;
		}
		return victory;
	}
	
	public boolean checkDiag()
	{
		int[] playersLeftDiag = new int[this.gridSize];
		int[] playersRightDiag = new int[this.gridSize];
		
		for(int i = 0; i < this.gridSize; i++)
		{
			if(this.cells[i][i] instanceof PlayerCell)
			{
				playersLeftDiag[i] =  ((PlayerCell) this.cells[i][i]).getPlayer();
			}
			else if(this.cells[i][i] instanceof PermanentPlayerCell)
			{
				playersLeftDiag[i] =  ((PermanentPlayerCell) this.cells[i][i]).getPlayer();
			}
			else
			{
				playersLeftDiag[i] = -1;
			}
		}
		
		for(int i = 0; i < this.gridSize; i++)
		{
			int x = this.gridSize - 1 - i;
			
			if(this.cells[i][x] instanceof PlayerCell)
			{
				playersRightDiag[i] =  ((PlayerCell) this.cells[i][x]).getPlayer();
			}
			else if(this.cells[i][x] instanceof PermanentPlayerCell)
			{
				playersRightDiag[i] =  ((PermanentPlayerCell) this.cells[i][x]).getPlayer();
			}
			else
			{
				playersRightDiag[x] = -1;
			}
		}
		
		return isSame(playersRightDiag) || isSame(playersLeftDiag);
	}
	
	public boolean hasVictory()
	{
		return checkRows() || checkColumns() || checkDiag();
	}
	
	public boolean isFull()
	{
		boolean empty = false;
		int y = 0;
		
		while(!empty && y < this.cells.length)
		{
			int x = 0;
			while(!empty && x < this.cells[y].length)
			{
				empty = !(this.cells[y][x] instanceof PlayerCell || this.cells[y][x] instanceof PermanentPlayerCell);
				x++;
			}
			y++;
		}
		return !empty;
	}
	
	public void passTurn()
	{
		this.state.passTurn();
		this.turnCount++;
		this.addForTurn = false;
	}
	
	public boolean verifyVictory()
	{
		return this.state.verifyVictory();
	}

	public CellEntity getCell(int x, int y)
	{
		return this.cells[y][x];
	}
	
	public int getGridSize()
	{
		return this.gridSize;
	}
	
	public void changeCurrentMode(int mode)
	{
		this.state.changeCurrentMode(mode);
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		this.container = container;
		this.game = game;
		
		for(int y = 0; y < this.cells.length; y++)
		{
			for(int x = 0; x < this.cells[y].length; x++)
			{
				this.cells[y][x].init(container, game);
			}
		}
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
	{
		for(int y = 0; y < this.cells.length; y++)
		{
			for(int x = 0; x < this.cells[y].length; x++)
			{
				this.cells[y][x].render(container, game, g);;
			}
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException 
	{		
		for(int y = 0; y < this.cells.length; y++)
		{
			for(int x = 0; x < this.cells[y].length; x++)
			{
				this.cells[y][x].update(container, game, delta);;
			}
		}
		
		this.addNewPower();
	}
	
	public void addNewPower() throws SlickException
	{
		if(this.turnCount != 0 && this.turnCount % this.gridSize == 0 && !this.addForTurn)
		{
			boolean notBlankCell = true;
			while(notBlankCell)
			{
				Random rand = new Random();
				
				int randX = rand.nextInt(this.getGridSize());
				int randY = rand.nextInt(this.getGridSize());

				if(this.getCell(randX, randY) instanceof BlankCell)
				{
					notBlankCell = false;
					this.addForTurn = true;
					
					System.err.println("OLD : " + randX + ", " + randY + " at " + (int) this.getCell(randX, randY).getX() + ", " + (int) this.getCell(randX, randY).getY());
					
					RandomCell randomCell = new RandomCell(randX, randY, this, (int) this.getCell(randX, randY).getX(), (int) this.getCell(randX, randY).getY(), this.getCell(randX, randY).getWidth(), this.getCell(randX, randY).getHeight(), this.getCell(randX, randY).getBackgroundOriginalWidth(), this.getCell(randX, randY).getBackgroundOriginalHeight());
					randomCell.setToExactPosition();
					System.err.println("NEW : " + randX + ", " + randY + " at " + (int) randomCell.getX() + ", " + (int) randomCell.getY());
					this.changeCell(randX, randY, randomCell);
				}
			}
		}
	}
	
	public void activeBlank(int cellX, int cellY, int x, int y, int width, int height, int backgroundOriginalWidth, int backgroundOriginalHeight) throws SlickException
	{		
		if(!Main.soundMuted)
			SoundManager.playSound("write", 1.0f, 0.3f);

		PlayerCell cell = new PlayerCell(GridState.getCurrentTurnPlayerID(), cellX, cellY, this, x, y, width, height, backgroundOriginalWidth, backgroundOriginalHeight);
		cell.setToExactPosition();
		
		this.changeCell(cellX, cellY, cell);
		this.passTurn();
	}
	
	public void activeErase(int cellX, int cellY, int x, int y, int width, int height, int backgroundOriginalWidth, int backgroundOriginalHeight) throws SlickException
	{
		if(!Main.soundMuted)
			SoundManager.playSound("powerSound", 1.0f, 0.5f);
		
		SplashWidget splash = new SplashWidget(AssetsManager.getImage("erase_splash"), 2, this.state);
		splash.init(container, game);
		this.state.addEntity(splash);

		CellEntity cell = new PlayerCell(GridState.getCurrentTurnPlayerID(), cellX, cellY, this, x, y, width, height, backgroundOriginalWidth, backgroundOriginalHeight);
		cell.setToExactPosition();
		
		this.changeCell(cellX, cellY, cell);
		
		this.verifyVictory();
		this.changeCurrentMode(GridState.ERASE_MODE);
	}
	
	public void activeExtraMove(int cellX, int cellY, int x, int y, int width, int height, int backgroundOriginalWidth, int backgroundOriginalHeight) throws SlickException
	{
		if(!Main.soundMuted)
			SoundManager.playSound("powerSound", 1.0f, 0.5f);
		
		SplashWidget splash = new SplashWidget(AssetsManager.getImage("extramove_splash"), 2, this.state);
		splash.init(container, game);
		this.state.addEntity(splash);
		
		PlayerCell cell = new PlayerCell(GridState.getCurrentTurnPlayerID(), cellX, cellY, this, x, y, width, height, backgroundOriginalWidth, backgroundOriginalHeight);
		cell.setToExactPosition();
		
		this.changeCell(cellX, cellY, cell);
		
		this.verifyVictory();
		this.changeCurrentMode(GridState.EXTRA_MOVE_MODE);
	}
	
	public void activePermanent(int cellX, int cellY, int x, int y, int width, int height, int backgroundOriginalWidth, int backgroundOriginalHeight) throws SlickException
	{
		if(!Main.soundMuted)
			SoundManager.playSound("powerSound", 1.0f, 0.5f);
		
		SplashWidget splash = new SplashWidget(AssetsManager.getImage("permanent_splash"), 2, this.state);
		splash.init(container, game);
		this.state.addEntity(splash);
		
		PermanentPlayerCell cell = new PermanentPlayerCell(GridState.getCurrentTurnPlayerID(), cellX, cellY, this, x, y, width, height, backgroundOriginalWidth, backgroundOriginalHeight);
		cell.setToExactPosition();
		
		this.changeCell(cellX, cellY, cell);
		this.passTurn();
	}
	
	public void activePing(int cellX, int cellY, int x, int y, int width, int height, int backgroundOriginalWidth, int backgroundOriginalHeight, int button, int clickCount) throws SlickException
	{
		if(!Main.soundMuted)
			SoundManager.playSound("powerSound", 1.0f, 0.5f);
		
		SplashWidget splash = new SplashWidget(AssetsManager.getImage("ping_splash"), 2, this.state);
		splash.init(container, game);
		this.state.addEntity(splash);
		
		PlayerCell cell = new PlayerCell(GridState.getCurrentTurnPlayerID(), cellX, cellY, this, x, y, width, height, backgroundOriginalWidth, backgroundOriginalHeight);
		cell.setToExactPosition();
		
		this.changeCell(cellX, cellY, cell);
		
		this.verifyVictory();
		
		boolean playerCell = true;
		while(playerCell)
		{
			Random rand = new Random();
			
			int randX = rand.nextInt(this.getGridSize());
			int randY = rand.nextInt(this.getGridSize());

			if(!(this.getCell(randX, randY) instanceof PlayerCell) || !(this.getCell(randX, randY) instanceof PermanentPlayerCell))
			{
				playerCell = false;
				this.getCell(randX, randY).onMouseClick(container, game, button, (int) this.getCell(randX, randY).getX() + 2, (int) this.getCell(randX, randY).getY() + 2, clickCount);
			}
		}
	}
	
	public void activeStamp(int cellX, int cellY, int x, int y, int width, int height, int backgroundOriginalWidth, int backgroundOriginalHeight) throws SlickException
	{
		if(!Main.soundMuted)
			SoundManager.playSound("powerSound", 1.0f, 0.5f);
		
		SplashWidget splash = new SplashWidget(AssetsManager.getImage("stamp_splash"), 2, this.state);
		splash.init(container, game);
		this.state.addEntity(splash);
		
		PlayerCell cell = new PlayerCell(GridState.getCurrentTurnPlayerID(), cellX, cellY, this, x, y, width, height, backgroundOriginalWidth, backgroundOriginalHeight);
		cell.setToExactPosition();
		
		this.changeCell(cellX, cellY, cell);
		
		this.verifyVictory();
		this.changeCurrentMode(GridState.STAMP_MODE);
	}
	
	public GridState getState()
	{
		return this.state;
	}
	
	public void resetTurnCount()
	{
		this.turnCount = 0;
	}

	@Override
	public void onMouseClick(GameContainer container, StateBasedGame game, int button, int x, int y, int clickCount) throws SlickException
	{
		if(!GridState.isVictory())
			for(int i = 0; i < this.cells.length; i++)
			{
				for(int j = 0; j < this.cells[i].length; j++)
				{
					this.cells[i][j].onMouseClick(container, game, button, x, y, clickCount);;
				}
			}		
	}

	@Override
	public void onMouseDragged(GameContainer container, StateBasedGame game, int oldx, int oldy, int newx, int newy) throws SlickException 
	{
		if(!GridState.isVictory())
		for(int y = 0; y < this.cells.length; y++)
		{
			for(int x = 0; x < this.cells[y].length; x++)
			{
				this.cells[y][x].onMouseDragged(container, game, oldx, oldy, newx, newy);;
			}
		}
	}

	@Override
	public void onMouseMoved(GameContainer container, StateBasedGame game, int oldx, int oldy, int newx, int newy) throws SlickException 
	{
		if(!GridState.isVictory())
		for(int y = 0; y < this.cells.length; y++)
		{
			for(int x = 0; x < this.cells[y].length; x++)
			{
				this.cells[y][x].onMouseMoved(container, game, oldx, oldy, newx, newy);;
			}
		}
	}

	@Override
	public void onMousePressed(GameContainer container, StateBasedGame game, int button, int x, int y) throws SlickException 
	{
		if(!GridState.isVictory())
		for(int i = 0; i < this.cells.length; i++)
		{
			for(int j = 0; j < this.cells[i].length; j++)
			{
				this.cells[i][j].onMousePressed(container, game, button, x, y);;
			}
		}
	}

	@Override
	public void onMouseReleased(GameContainer container, StateBasedGame game, int button, int x, int y) throws SlickException 
	{
		if(!GridState.isVictory())
		for(int i = 0; i < this.cells.length; i++)
		{
			for(int j = 0; j < this.cells[i].length; j++)
			{
				this.cells[i][j].onMouseReleased(container, game, button, x, y);;
			}
		}		
	}

	@Override
	public void onMouseWheelMoved(GameContainer container, StateBasedGame game, int change) throws SlickException
	{
		if(!GridState.isVictory())
		for(int y = 0; y < this.cells.length; y++)
		{
			for(int x = 0; x < this.cells[y].length; x++)
			{
				this.cells[y][x].onMouseWheelMoved(container, game, change);;
			}
		}		
	}

	@Override
	public void onKeyPressed(GameContainer container, StateBasedGame game, int key, char c) throws SlickException
	{
		if(!GridState.isVictory())
		for(int y = 0; y < this.cells.length; y++)
		{
			for(int x = 0; x < this.cells[y].length; x++)
			{
				this.cells[y][x].onKeyPressed(container, game, key, c);;
			}
		}		
	}

	@Override
	public void onKeyReleased(GameContainer container, StateBasedGame game, int key, char c) throws SlickException
	{
		if(!GridState.isVictory())
		for(int y = 0; y < this.cells.length; y++)
		{
			for(int x = 0; x < this.cells[y].length; x++)
			{
				this.cells[y][x].onKeyReleased(container, game, key, c);;
			}
		}		
	}

	@Override
	public void onControllerButtonPressed(GameContainer container, StateBasedGame game, int controller, int button) throws SlickException
	{
		if(!GridState.isVictory())
		for(int y = 0; y < this.cells.length; y++)
		{
			for(int x = 0; x < this.cells[y].length; x++)
			{
				this.cells[y][x].onControllerButtonPressed(container, game, controller, button);;
			}
		}		
	}

	@Override
	public void onControllerButtonReleased(GameContainer container, StateBasedGame game, int controller, int button) throws SlickException
	{
		if(!GridState.isVictory())
		for(int y = 0; y < this.cells.length; y++)
		{
			for(int x = 0; x < this.cells[y].length; x++)
			{
				this.cells[y][x].onControllerButtonReleased(container, game, controller, button);;
			}
		}		
	}

	@Override
	public void onControllerDownPressed(GameContainer container, StateBasedGame game, int controller) throws SlickException
	{
		if(!GridState.isVictory())
		for(int y = 0; y < this.cells.length; y++)
		{
			for(int x = 0; x < this.cells[y].length; x++)
			{
				this.cells[y][x].onControllerDownPressed(container, game, controller);;
			}
		}		
	}

	@Override
	public void onControllerDownReleased(GameContainer container, StateBasedGame game, int controller) throws SlickException
	{
		if(!GridState.isVictory())
		for(int y = 0; y < this.cells.length; y++)
		{
			for(int x = 0; x < this.cells[y].length; x++)
			{
				this.cells[y][x].onControllerDownReleased(container, game, controller);;
			}
		}		
	}

	@Override
	public void onControllerLeftPressed(GameContainer container, StateBasedGame game, int controller) throws SlickException
	{
		if(!GridState.isVictory())
		for(int y = 0; y < this.cells.length; y++)
		{
			for(int x = 0; x < this.cells[y].length; x++)
			{
				this.cells[y][x].onControllerLeftPressed(container, game, controller);;
			}
		}		
	}

	@Override
	public void onControllerLeftReleased(GameContainer container, StateBasedGame game, int controller) throws SlickException 
	{
		if(!GridState.isVictory())
		for(int y = 0; y < this.cells.length; y++)
		{
			for(int x = 0; x < this.cells[y].length; x++)
			{
				this.cells[y][x].onControllerLeftReleased(container, game, controller);;
			}
		}
		
	}

	@Override
	public void onControllerRightPressed(GameContainer container, StateBasedGame game, int controller) throws SlickException
	{
		if(!GridState.isVictory())
		for(int y = 0; y < this.cells.length; y++)
		{
			for(int x = 0; x < this.cells[y].length; x++)
			{
				this.cells[y][x].onControllerRightPressed(container, game, controller);;
			}
		}		
	}

	@Override
	public void onControllerRightReleased(GameContainer container, StateBasedGame game, int controller) throws SlickException
	{
		if(!GridState.isVictory())
		for(int y = 0; y < this.cells.length; y++)
		{
			for(int x = 0; x < this.cells[y].length; x++)
			{
				this.cells[y][x].onControllerRightReleased(container, game, controller);;
			}
		}		
	}

	@Override
	public void onControllerUpPressed(GameContainer container, StateBasedGame game, int controller) throws SlickException 
	{
		if(!GridState.isVictory())
		for(int y = 0; y < this.cells.length; y++)
		{
			for(int x = 0; x < this.cells[y].length; x++)
			{
				this.cells[y][x].onControllerUpPressed(container, game, controller);;
			}
		}		
	}

	@Override
	public void onControllerUpReleased(GameContainer container, StateBasedGame game, int controller) throws SlickException 
	{
		if(!GridState.isVictory())
		for(int y = 0; y < this.cells.length; y++)
		{
			for(int x = 0; x < this.cells[y].length; x++)
			{
				this.cells[y][x].onControllerUpReleased(container, game, controller);;
			}
		}		
	}
}
