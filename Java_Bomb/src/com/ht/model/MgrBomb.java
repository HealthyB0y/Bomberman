package com.ht.model;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class MgrBomb {
	private ArrayList<Bomb> arrBomb = new ArrayList<Bomb>();

	public void addBomb(int x, int y, long time, int leng, int vt) {
		arrBomb.add(new Bomb(x, y, time, leng, vt));
	}

	public void drawAllBomb(Graphics2D g2d) {
		for (int i = 0; i < arrBomb.size(); i++) {
			arrBomb.get(i).drawBoom(g2d);
		}
	}

	public ArrayList<Bomb> getArrBomb() {
		return arrBomb;
	}

	public void update() {
		for (int i = 0; i < arrBomb.size(); i++) {
			arrBomb.get(i).update();
		}
	}

	public int searchBomb(int zone) {
		for (int i = 0; i < arrBomb.size(); i++) {
			if (arrBomb.get(i).getO() == zone) {
				return i;
			}
		}
		return -1;
	}
}
