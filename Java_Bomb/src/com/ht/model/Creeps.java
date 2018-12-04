package com.ht.model;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import com.ht.common.ImgManager;
import com.ht.common.Value;
import com.ht.gui.GUI;
import com.ht.maps.MapManager;
import com.ht.maps.Wall;

public class Creeps {

	public static final int LEFT = 3, RIGHT = 2, TOP = 0, BOTTOM = 1;
	private int x, y, speed, oirent;
	private Image img;
	private int vt = 0;
	Random rd = new Random();
	private boolean isRun = false, test = false, isDie = false;
	private int orient, dem = 0, setZone;
	private int a[];

	public Creeps(int x, int y, int speed, int oirent) {
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.oirent = oirent;
		img = ImgManager.arrCreepImg.get(oirent);
	}

	public void moveCreeps(MapManager map, Player player, long time) {
		if (time % speed != 0) {
			return;
		}
		if (isDie) {
			if (time % 30 == 0) {
				creepDie();
			}
			return;
		}
//			searchPath(map, player);
		switch (oirent) {
		case LEFT:
			img = ImgManager.arrCreepImg.get((vt % 3) * 4 + LEFT);
			x -= 5;
			vt++;
			if (map.getArrUnits().get(getZone() - 1).getType() != 0 && x % 50 == 40) {
				setOirent(RIGHT);
			}
			searchPath(map, player);
			break;
		case RIGHT:
			img = ImgManager.arrCreepImg.get((vt % 3) * 4 + RIGHT);
			x += 5;
			vt++;
			if (map.getArrUnits().get(getZone() + 1).getType() != 0 && x % 50 == 40) {
				setOirent(LEFT);
			}
			searchPath(map, player);
			break;
		case TOP:
			img = ImgManager.arrCreepImg.get((vt % 3) * 4 + TOP);
			y -= 5;
			vt++;
			if (map.getArrUnits().get(getZone() - 13).getType() != 0 && y % 50 == 30) {
				setOirent(BOTTOM);
			}
			searchPath(map, player);
			break;
		case BOTTOM:
			img = ImgManager.arrCreepImg.get((vt % 3) * 4 + BOTTOM);
			y += 5;
			vt++;
			if (map.getArrUnits().get(getZone() + 13).getType() != 0 && y % 50 == 30) {
				setOirent(TOP);
			}
			searchPath(map, player);
			break;
		default:
			break;
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getSpeed() {
		return speed;
	}

	public int getOirent() {
		return oirent;
	}

	public void setOirent(int oirent) {
		vt = 0;
		img = ImgManager.arrCreepImg.get(oirent);
		this.oirent = oirent;
	}

	public boolean isRun() {
		return isRun;
	}

	public void setRun(boolean isRun) {
		this.isRun = isRun;
	}

	public void setVt(int vt) {
		this.vt = vt;
	}

	public int getVt() {
		return vt;
	}

	public void drawCreeps(Graphics2D g2d) {
		g2d.drawImage(img, x, y, null);
	}

	public int getZone() {
		return ((y + 60) / 50) * 13 + (x + 40) / 50;
	}

	public void creepDie() {
		img = ImgManager.arrCreepImg.get(12 + vt);
		vt++;
	}

	public boolean isDie() {
		return isDie;
	}

	public void setDie(boolean isDie) {
		this.isDie = isDie;
		vt = 0;
	}

	public boolean searchPath(MapManager map, Player player) {
		if (player.getZone() == (getZone() + 1) || player.getZone() == (getZone() + 2)) {
			if (y % 50 == 30 && map.getArrUnits().get(getZone() + 1).getType() == 0
					&& map.getArrUnits().get(getZone() + 2).getType() == 0) {
				oirent = RIGHT;
			}
			return true;
		}
		if (player.getZone() == (getZone() - 1) || player.getZone() == (getZone() - 2)) {
			if (y % 50 == 30 && map.getArrUnits().get(getZone() - 1).getType() == 0
					&& map.getArrUnits().get(getZone() - 2).getType() == 0)
				oirent = LEFT;
			return true;
		}
		if (player.getZone() == (getZone() + 13) || player.getZone() == (getZone() + 26)) {
			if (x % 50 == 40 && map.getArrUnits().get(getZone() + 13).getType() == 0
					&& map.getArrUnits().get(getZone() + 26).getType() == 0)
				oirent = BOTTOM;
			return true;
		}
		if (player.getZone() == (getZone() - 13) || player.getZone() == (getZone() - 26)) {
			if (x % 50 == 40 && map.getArrUnits().get(getZone() - 13).getType() == 0
					&& map.getArrUnits().get(getZone() - 26).getType() == 0)
				oirent = TOP;
			return true;
		}
		return false;

	}
}
