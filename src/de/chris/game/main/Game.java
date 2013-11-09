package de.chris.game.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import de.chris.game.enums.GameState;
import de.chris.game.input.KeyInput;
import de.chris.game.input.MouseInput;
import de.chris.game.libs.Images;
import de.chris.game.libs.Reference;
import de.chris.game.objects.Player;
import de.chris.game.screens.Handler;
import de.chris.game.screens.Menu;
import de.chris.game.screens.Renderer;
import de.chris.game.utils.FileManager;
import de.chris.game.utils.ObjectId;
import de.chris.game.utils.ResourceLoader;


public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	public static String path = System.getProperty("user.home") + "\\Desktop\\Adolf\\";
	private static JFrame frame =  new JFrame();
	public static final String TITLE = "NIKKKES";
	public static final int WIDTH = 1280;
	public static final int	HEIGHT = 720;
	public static Game game = new Game();
	public static GameState state = GameState.MENU;

	public Handler handler;	
	private boolean running = false;
	private Renderer gfx;
	private Thread thread;
	public Menu menu;
	
	public static Game getInstance(){
		return game;
	}
	
	private void init(){
		System.out.println(Reference.SPTIE_LOCATION);
		
		ResourceLoader.loadImages();
		menu = new Menu();
		gfx = new Renderer();
		
		handler = new Handler();
		handler.addObject(new Player(100, 100, handler, ObjectId.Player));		
		handler.createLevel();
		
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(new MouseInput(handler));
//		new FileManager().createFolder(path);
	}
	
	public synchronized void start()
	{
		if(running)
			return;
		else
			running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop(){
		if(!running)
			return;
		else
			running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
	}
	
	public void run(){
		init();
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
					
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
	}
	
	private void tick()	{
		handler.tick();
	}
	
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null)
		{
			this.createBufferStrategy(3);//3
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		////////////////////////////////
		//Draw HERE
		g.setColor(Color.black);
		g.fillRect(0,0, getWidth(), getHeight());
		
//		handler.render(g);
		
		gfx.renderBackground(g);
		gfx.renderForeground(g);
		
		////////////////////////////////
		g.dispose();
		bs.show();
		
	}
	
	public static void main(String args[]){
		Image icon = Toolkit.getDefaultToolkit().getImage(Reference.RESOURCE_LOACTION + "icon.png");
		game.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		game.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		game.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		frame.add(game);
		frame.setTitle(TITLE);
		frame.setIconImage(icon);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setFocusable(true);
//		frame.setLocationRelativeTo(null);
		frame.setResizable(true);	
		frame.setVisible(true);		
		frame.pack();
		game.start();
	}
}
