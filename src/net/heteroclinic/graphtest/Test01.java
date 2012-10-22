package net.heteroclinic.graphtest;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;


public class Test01 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		int width=150;
//		int height=40;
		int width=4096;
		int height=4096;

		String mcap = "testcapcha";
		Color background = new Color(204,204,204);

		Color fbl = new Color(0,100,0);

		Font fnt=new Font("SansSerif",1,14);

		BufferedImage cpimg =new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

		Graphics g = cpimg.createGraphics();

		g.setColor(background);

		g.fillRect(0,0,width,height);

		g.setColor(fbl);

		g.setFont(fnt);

		g.drawString(mcap,30,25);

		g.setColor(background);

		g.drawLine(25,20,120,20);

		//g.drawLine(25,22,75,22);

		//response.setContentType("image/jpeg");

		//OutputStream strm = response.getOutputStream();
		File f = new File("C:\\Users\\Graphics\\Desktop\\testcaptcha.jpeg");
		OutputStream os;
		try {
			os = new FileOutputStream(f);
			ImageIO.write(cpimg,"jpeg",os);
			os.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	

	}

}
