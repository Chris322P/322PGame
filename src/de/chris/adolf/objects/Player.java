package de.chris.adolf.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;

import de.chris.adolf.libs.Images;
import de.chris.adolf.screens.Handler;
import de.chris.adolf.utils.GameObject;
import de.chris.adolf.utils.ObjectId;

public class Player extends GameObject
{
	private float width = 48, height = 64;
	private float gravity = 0.4f;
	private final float MAX_SPEED = 10;
	private Handler handler;
	private int i = 0;
	private Animation ani;	
		
	public Player(float x, float y, Handler handler, ObjectId id) 
	{
		super(x, y, id);
		this.handler = handler;

		// SET LEFT BINDINGS
//		jump_L = new ImageLoader().rotateImageVert(Images.mage_jump);
//		attack_L = new ImageLoader().rotateImageVert(Images.mage_atk);
		
		ArrayList<BufferedImage> imgs = new ArrayList<BufferedImage>();
		imgs.add(Images.mage_main);
		imgs.add(Images.mage_walk_1);
		imgs.add(Images.mage_walk_2);
		imgs.add(Images.mage_walk_1);
		imgs.add(Images.mage_main);
		imgs.add(Images.mage_walk_3);
		
		// Get PlayerID	
		int PlayerId = 0;
		for(int i = 0; i < handler.object.size(); i++)
		{
			GameObject tempObject = handler.object.get(i);		
			if(tempObject.getId() == ObjectId.Player)
			{
				PlayerId = i;
			}
		}
		ani = new Animation((int)x, (int)y, this.handler, null, PlayerId, imgs);
	
	}

	public void tick(LinkedList<GameObject> object) 
	{
		x += velX;
		y += velY;
		
		if(falling || jumping)
		{
			velY += gravity;
			
			if(velY > MAX_SPEED)
				velY = MAX_SPEED;
		}
		
		Collision(object);
	}
	
	private void Collision(LinkedList<GameObject> object)
	{
		for(int i = 0; i < handler.object.size(); i++)
		{
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ObjectId.Block)
			{
				//BOTTOM
				if(getBounds().intersects(tempObject.getBounds()))
				{
					//CAUSE OF COLLSIION PROBLEM
					y = tempObject.getY() - height;
					velY = 0;
					falling = false;
					jumping = false;
				} else {
					falling = true;
				}
				//TOP
				if(getBoundsTop().intersects(tempObject.getBounds()))
				{
					y = tempObject.getY() + 32;
					velY = 0;
				}
				//RECHTS
				if(getBoundsRight().intersects(tempObject.getBounds()))
				{
					x = tempObject.getX() - width;
				}
				//LINKS
				if(getBoundsLeft().intersects(tempObject.getBounds()))
				{
					x = tempObject.getX() + width-16;
				}
			}
		}
	}

	public void render(Graphics g) 
	{	
		BufferedImage img = null;
		if(jumping == true) {
			img = Images.mage_jump;
		} else if (attacking == true) {
			img = Images.mage_atk;
			if(i >= 500)
			{
				attacking = false;
			} else {
				i++;
			}
			
		} else if (running == true)	{
			ani.render(g);
		} else {
			i = 0;
			img = Images.mage_main;
		}			
		
		if(lookRight == false)
		{
			if(img == Images.mage_main)
			{
				img = Images.mage_main;
			} else if(img == Images.mage_jump)
			{
				img = Images.mage_jump;
			}
		}
		
		if(running == false){
			g.drawImage(img, (int)x, (int)y, null);
		}
			
		// BOUNDING BOXES
//		Graphics2D g2d = (Graphics2D) g;
//		g.setColor(Color.red);
//		g2d.draw(getBounds());
//		g2d.draw(getBoundsRight());
//		g2d.draw(getBoundsLeft());
//		g2d.draw(getBoundsTop());
	}

	public Rectangle getBounds() 
	{
		return new Rectangle((int)x+4, (int) ((int)y+(height/2)), (int)width-8, (int)height/2);
	}
	
	public Rectangle getBoundsTop() 
	{
		return new Rectangle((int)x+4, (int) ((int)y), (int)width-8, (int)height/2);
	}
	
	public Rectangle getBoundsRight() 
	{
		return new Rectangle((int) ((int)x+width-4), (int)y+2, (int)3, (int)height-4);
	}
	
	public Rectangle getBoundsLeft() 
	{
		return new Rectangle((int)x, (int)y+2, (int)3, (int)height-4);
	}

}
