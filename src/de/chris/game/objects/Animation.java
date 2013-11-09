package de.chris.game.objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;

import de.chris.game.screens.Handler;
import de.chris.game.utils.GameObject;
import de.chris.game.utils.ImageLoader;
import de.chris.game.utils.ObjectId;

public class Animation  extends GameObject {
	int tickes = 0;
	BufferedImage img;
	ArrayList<BufferedImage> imgaes_R = new ArrayList<BufferedImage>();
	ArrayList<BufferedImage> imgaes_L = new ArrayList<BufferedImage>();
	private Handler handler;
	private int parentId;
	
	public Animation(float x, float y, Handler handler, ObjectId id, int parentId, ArrayList<BufferedImage> imgs) {
		super(x, y, id);
		this.handler = handler;
		this.imgaes_R = imgs;
		this.parentId = parentId;
		
		for (int i = 0; i < imgaes_R.size(); i++){
			BufferedImage tempImg = new ImageLoader().rotateImageVert(imgs.get(i));
			imgaes_L.add(tempImg);
		}
	}

	
	@Override
	public void tick(LinkedList<GameObject> object) {
	}

	@Override
	public void render(Graphics g) {
		tickes++;
		
		int AnimSpeed = 200;
		GameObject tempObject = handler.object.get(parentId);
		
		if (tempObject.isRunning() == true)	{
					
			for (int i = 0; i < imgaes_R.size(); i++){
				ArrayList<BufferedImage> sided_img;
				if (tempObject.lookRight() == true)	{
					sided_img = imgaes_R;
				} else {
					sided_img = imgaes_L;
				}
				//PRÜFEN OB ENDE
				if(tickes == AnimSpeed*sided_img.size()){
					img = sided_img.get(0);
					tickes = 0;
				} else if (tickes < AnimSpeed ){
					img = sided_img.get(i);
				} else if(tickes >= AnimSpeed*i-1 && tickes <= AnimSpeed*i){
					img = sided_img.get(i);
				}
			}	
				g.drawImage(img, (int)tempObject.getX(), (int)tempObject.getY(), null);		
		}

			
	}	
	@Override
	public Rectangle getBounds() {
		return null;
	}
}
