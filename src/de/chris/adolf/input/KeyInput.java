package de.chris.adolf.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import de.chris.adolf.objects.Projectile;
import de.chris.adolf.screens.Handler;
import de.chris.adolf.utils.GameObject;
import de.chris.adolf.utils.ObjectId;

public class KeyInput extends KeyAdapter
{

	Handler handler;
	
	public KeyInput(Handler handler)
	{
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++)
		{
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ObjectId.Player)
			{
				if(key == KeyEvent.VK_D)
				{
					tempObject.setVelX(4f);
					tempObject.setlookRight(true);
					tempObject.setRunning(true);
				}
				if(key == KeyEvent.VK_A)
				{
					tempObject.setVelX(-4f);
					tempObject.setlookRight(false);
					tempObject.setRunning(true);
				}
				if(key == KeyEvent.VK_SHIFT)
				{
					handler.addObject(new Projectile(100, 100, handler, ObjectId.Projectile));
					tempObject.setAttacking(true);
				}
				if(key == KeyEvent.VK_SPACE && !tempObject.isJumping())
				{
					tempObject.setJumping(true);
					tempObject.setVelY(-10);
				}
			}
		}
		
		if(key == KeyEvent.VK_ESCAPE)
		{
			System.exit(1);
		}
	}
	
	public void keyReleased(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++)
		{
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ObjectId.Player)
			{
				if(key == KeyEvent.VK_D) 
				{
					tempObject.setVelX(0);
					tempObject.setRunning(false);
				}
				if(key == KeyEvent.VK_A)
				{
					tempObject.setVelX(0);
					tempObject.setRunning(false);
				}
			}
		}
	}
	
}
