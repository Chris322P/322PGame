package de.chris.game.screens;

import java.awt.Color;
import java.awt.Graphics;

import de.chris.game.main.Game;
import de.chris.game.objects.Animation;

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
