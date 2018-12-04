package com.ht.model;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class MgrWater {
	private ArrayList<Water> arrWater = new ArrayList<Water>();

	public void addWater(int x, int y, long time) {
		arrWater.add(new Water(x, y, time));
	}

	public void drawAllWater(Graphics2D g2d) {
		for (int i = 0; i < arrWater.size(); i++) {
			arrWater.get(i).drawWater(g2d);
		}
	}

	public ArrayList<Water> getArrWater() {
		return arrWater;
	}

	public void update() {
		for (int i = 0; i < arrWater.size(); i++) {
			arrWater.get(i).update();
		}
	}
}
