package de.chris.game.utils;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import de.chris.game.libs.Reference;

public class ImageLoader {
	public BufferedImage rotateImageVert(BufferedImage old)
	{
		BufferedImage neu = null;
		AffineTransform  tx = AffineTransform.getScaleInstance(-1, 1);
		tx.translate(-old.getWidth(null), 0);
		AffineTransformOp  op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		neu = op.filter(old, null);
		return neu;
	}
	
	public BufferedImage loadImage(String imagePath) throws IOException{
		BufferedImage img = ImageIO.read(new File(Reference.SPTIE_LOCATION + imagePath));
		return img;
	}

}
