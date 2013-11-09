package de.chris.adolf.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

import de.chris.adolf.libs.Images;
import de.chris.adolf.libs.Reference;
import de.chris.adolf.main.Game;

public class Menu {
	public static Rectangle play, options, quit;
	
	public Menu(){
		int fillerY = 150;
		play = new Rectangle(Reference.CENTER_X-100, fillerY, 200, 50);
		options = new Rectangle(Reference.CENTER_X-100, fillerY+=60, 200, 50);
		quit = new Rectangle(Reference.CENTER_X-100, fillerY+=60, 200, 50);
	}
	
	public void drawButon(Graphics g, Rectangle rect, String txt, int offsetX){
		Font tempFont = new Font("Arial", Font.BOLD, 45);
		g.setFont(tempFont);
		g.setColor(Color.BLACK);
		g.drawRect(rect.x, rect.y, rect.width, rect.height);
		g.drawString(txt, rect.x + offsetX, rect.y + 38);
	}
	
	public void render(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		g.drawImage(Images.logo, Reference.CENTER_X-150, 20, null);
		drawButon(g, play, "Start", 45);
		drawButon(g, options, "Options", 15);
		drawButon(g, quit, "Quit", 45);
	}
}
