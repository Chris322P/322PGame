package de.chris.adolf.screens;

import java.awt.Color;
import java.awt.Graphics;

import de.chris.adolf.main.Game;
import de.chris.adolf.objects.Animation;

public class Renderer {
	public void renderBackground(Graphics g){
		switch(Game.state){
		case GAME:
			Game.getInstance().handler.render(g);
			break;
		case MENU:
			Game.getInstance().menu.render(g);
		case OPTIONS:
			break;
		case PAUSE:
			break;
		default:
			g.setColor(Color.RED);
			g.drawString("UNKOWN GAME STATE", 0, 0);
		}
	}
	public void renderForeground(Graphics g){
		
	}
	
}
