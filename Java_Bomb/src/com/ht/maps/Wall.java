package com.ht.maps;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import com.ht.common.Value;

public class Wall {
	private Image img;
	private int x;
	private int y;
	private int type, item;

	public Wall(Image img, int x, int y, int type, int item) {
		super();
		this.img = img;
		this.x = x;
		this.y = y;
		this.type = type;
		this.item = item;
	}

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getItem() {
		return item;
	}

	public void setItem(int item) {
		this.item = item;
	}

	public void drawUnit(Graphics2D g) {
		g.drawImage(img, x * Value.SIZE, y * Value.SIZE, null);
	}

}
