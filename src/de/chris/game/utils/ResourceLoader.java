package de.chris.game.utils;

import java.io.IOException;

import de.chris.game.libs.Images;

public class ResourceLoader {
	
	private static ImageLoader imageLoader = new ImageLoader();
	
	public static void loadImages(){
		
		try{
			Images.logo = imageLoader.loadImage("logo.png");
			
			//MAGE
			Images.mage_main = imageLoader.loadImage("mage/mage_main.png");
			Images.mage_walk_1 = imageLoader.loadImage("mage/mage_walk_1.png");
			Images.mage_walk_2 = imageLoader.loadImage("mage/mage_walk_2.png");
			Images.mage_walk_3 = imageLoader.loadImage("mage/mage_walk_3.png");
			Images.mage_jump = imageLoader.loadImage("mage/mage_jump.png");
			Images.mage_atk = imageLoader.loadImage("mage/mage_atk.png");

		} catch(IOException e){
			e.printStackTrace();
		}
		
	}
}
