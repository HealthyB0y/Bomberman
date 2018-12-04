package com.ht.model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.ht.common.ImgManager;

public class Water {
	private int x, y, vt = 0, o;
	private long time;
	private Image img;

	public Water(int x, int y, long time) {
		super();
		this.x = x;
		this.y = y;
		this.time = time;
	}

	public long getTime() {
		return time;
	}

	public int getO() {
		return o;
	}

	public void update() {
		img = ImgManager.arrWaterImg.get(vt % 6);
		vt++;
	}

	public void drawWater(Graphics2D g2d) {
		g2d.drawImage(ImgManager.arrWaterImg.get(0), x, y, null);
	}
}
