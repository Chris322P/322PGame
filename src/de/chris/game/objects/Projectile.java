package de.chris.game.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import de.chris.game.main.Game;
import de.chris.game.screens.Handler;
import de.chris.game.utils.GameObject;
import de.chris.game.utils.ObjectId;

public class Projectile extends GameObject 
{
	Handler handler;	
	private float width = 16, height = 16;
	private int PlayerId = 0;
	private float i = 0;
	BufferedImage img = null;	
	private BufferedImage ball0 = null,ball1 = null,ball2 = null,ball3 = null,ball4 = null,ball5 = null;
	float x = 0,y = 0;
	public Projectile(float xx, float yy, Handler handler, ObjectId id) {
		super(xx, yy, id);
		this.handler = handler;
		// LOAD IMAGES
		try {
			ball0 = ImageIO.read(new File(Game.path + "mage\\ball0.png"));
			ball1 = ImageIO.read(new File(Game.path + "mage\\ball1.png"));
			ball2 = ImageIO.read(new File(Game.path + "mage\\ball2.png"));
			ball3 = ImageIO.read(new File(Game.path + "mage\\ball3.png"));
			ball4 = ImageIO.read(new File(Game.path + "mage\\ball4.png"));
			ball5 = ImageIO.read(new File(Game.path + "mage\\ball5.png"));
		} catch (IOException e) {
			System.out.println("Error mit BIld digga");
		}
		// PLAYER ID GETTING
		for(int i = 0; i < handler.object.size(); i++)
		{
			GameObject tempObject = handler.object.get(i);		
			if(tempObject.getId() == ObjectId.Player)
			{
				PlayerId = i;
			}
		}
		GameObject tempObject = handler.object.get(PlayerId);
		x = tempObject.getX()+65;
		y = tempObject.getY();
	}
	@Override
	public void tick(LinkedList<GameObject> object) {
		
	}
	@Override
	public void render(Graphics g) {
		float speedadd = 0.3f;
		if(i >= 0 && i <= 800)// 800 wg weg machen
		{
			i+=speedadd;
		}
		if(i < 350)
		{
			img = ball0;		
		} else if(i >= 350 && i <= 450) {
			img = ball1;
		} else if(i <= 450 && i <= 550) {
			img = ball2;
		} else if(i <= 550 && i <= 650) {
			img = ball3;
		} else if(i <= 650 && i <= 750) {
			img = ball4;
		} else if(i <= 750 && i <= 850) {
			img = ball4;	
		}
		if(i >= 750){		
			handler.removeObject(this);
		}
		
		g.drawImage(img, (int)x+(int)i, (int)y, null);	
		
	}
	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

}
