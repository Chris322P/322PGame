package de.chris.game.screens;

import java.awt.Graphics;
import java.util.LinkedList;

import de.chris.game.main.Game;
import de.chris.game.objects.Block;
import de.chris.game.utils.GameObject;
import de.chris.game.utils.ObjectId;

public class Handler 
{

	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	private GameObject tempObject;
	
	public void tick()
	{
		for(int i = 0; i < object.size(); i++)
		{
			tempObject = object.get(i);
			
			tempObject.tick(object);
		}
	}
	
	public void render(Graphics g)
	{
		for(int i = 0; i < object.size(); i++)
		{
			tempObject = object.get(i);
			
			tempObject.render(g);
		}
	}
	
	public void addObject(GameObject object)
	{
		this.object.add(object);
	}
	
	public void removeObject(GameObject object)
	{
		this.object.remove(object);
	}
	
	public void createLevel()
	{
		for(int xx = 0; xx < Game.WIDTH+32; xx += 32)
		{
			addObject(new Block(xx, Game.HEIGHT-32, ObjectId.Block));
		}
		
		for(int xx = 0; xx < Game.HEIGHT-64; xx += 32)
		{
			addObject(new Block(0, xx, ObjectId.Block));
		}
		
		for(int xx = 0; xx < Game.HEIGHT-64; xx += 32)
		{
			addObject(new Block(Game.WIDTH-32, xx, ObjectId.Block));
		}
		
		for(int xx = 0; xx < 256; xx += 32)
		{
			addObject(new Block(xx, Game.HEIGHT-192, ObjectId.Block));
		}
	}
}
