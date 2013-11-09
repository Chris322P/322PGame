package de.chris.game.input;

import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import de.chris.game.enums.GameState;
import de.chris.game.main.Game;
import de.chris.game.screens.Handler;

public class MouseInput extends MouseAdapter{
	
Handler handler;
	
	public MouseInput(Handler handler)
	{
		this.handler = handler;
	}
	
	@Override
	public void mouseClicked(MouseEvent e){
		int mouse = e.getButton();
		Rectangle rect = new Rectangle(e.getX(), e.getY(), 1, 1);
		
		if(mouse == MouseEvent.BUTTON1){
			switch(Game.state){
			case GAME:
				break;
			case MENU:
				if(rect.intersects(Game.getInstance().menu.play))
					Game.state = GameState.GAME;
				break;
			case OPTIONS:
				break;
			case PAUSE:
				break;
			default:
				break;	
			}
		if(rect.intersects(Game.getInstance().menu.quit))
			System.exit(1);

		}
	}

}
