package com.ht.model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Bomb {
	private int x, y, leng, vt = 0, o;
	private long time;
	private ArrayList<Image> arrImg = new ArrayList<Image>();
	private Image img;

	public Bomb(int x, int y, long time, int leng, int o) {
		super();
		this.x = x;
		this.y = y;
		this.time = time;
		this.leng = leng;
		this.o = o;
		initArrImg();
		img = arrImg.get(0);
	}

	public int getLeng() {
		return leng;
	}

	public long getTime() {
		return time;
	}

	public int getO() {
		return o;
	}

	public void initArrImg() {
		try {
			BufferedImage bfi = ImageIO.read(getClass().getResource("/IMG/boom1.png"));
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 4; j++) {
					arrImg.add(bfi.getSubimage(j * 81, i * 87, 81, 87));
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void update() {
		img = arrImg.get(vt % 8);
		vt++;
	}

	public void drawBoom(Graphics2D g2d) {
		g2d.drawImage(img, x - 12, y - 20, 75, 75, null);
	}
}
