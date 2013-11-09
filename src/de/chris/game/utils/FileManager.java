package de.chris.game.utils;

import java.io.File;

public class FileManager {
	public void createFolder(String name)
	{
		System.out.println("Created Folder: " + name);
		File dir = new File(name); 
	    dir.mkdirs();
	}
}
