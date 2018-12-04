package com.ht.model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.ht.common.ImgManager;

public class Item {
	private int x, y, type;

	public Item(int x, int y, int type) {
		super();
		this.x = x;
		this.y = y;
		this.type = type;
	}

	public int getZone() {
		return (y / 50) * 13 + x / 50;
	}

	public void drawItem(Graphics2D g2d) {
		g2d.drawImage(ImgManager.arrItemImg.get(type), x, y, null);
	}
}
