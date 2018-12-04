package com.ht.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.ht.common.ImgManager;
import com.ht.common.Value;
import com.ht.maps.Wall;

public class Player {
	private int x, y, speed, oirent, countBomb = 3, lengBomb = 2, typeRun;
	private Image img;
	private int vt = 0, jump = 5, zone, st;
	private boolean isRun = false, isDie = false;

	public Player(int x, int y, int speed, int oirent) {
		super();
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.oirent = oirent;
		img = ImgManager.arrPlayerImg.get(oirent);
	}

	public void drawPlayer(Graphics2D g2d) {
		g2d.drawImage(img, x, y, null);
	}

	public void movePlayer(long time) {
		if (isDie) {
			if (vt == 10) {
				img = null;
				return;
			}
			playerDieWater(time);
			return;
		}
		if (!isRun) {
			return;
		}
		if (time % speed != 0) {
			return;
		}
		if (typeRun == 1) { // nếu đang đứng ở giữa 1 ô
			switch (oirent) {
			case Value.LEFT:
				vt++;
				if (vt == 5) {
					img = ImgManager.arrPlayerImg.get(Value.LEFT);
					vt = 0;
					isRun = false;
				}
				img = ImgManager.arrPlayerImg.get(vt * 7 + Value.LEFT);
				x -= jump;
				break;
			case Value.RIGHT:
				vt++;
				if (vt == 5) {
					img = ImgManager.arrPlayerImg.get(Value.RIGHT);
					vt = 0;
					isRun = false;
				}
				img = ImgManager.arrPlayerImg.get(vt * 7 + Value.RIGHT);
				x += jump;
				break;
			case Value.UP:
				vt++;
				if (vt == 5) {
					img = ImgManager.arrPlayerImg.get(Value.UP);
					vt = 0;
					isRun = false;

				}
				img = ImgManager.arrPlayerImg.get(vt * 7 + Value.UP);
				y -= jump;
				break;
			case Value.DOWN:
				vt++;
				if (vt == 5) {
					img = ImgManager.arrPlayerImg.get(Value.DOWN);
					vt = 0;
					isRun = false;
				}
				img = ImgManager.arrPlayerImg.get(vt * 7 + Value.DOWN);
				y += jump;
				break;
			default:
				break;
			}
		} else { // nếu đang đứng ở giữa 2 ô
			switch (oirent) {
			case Value.LEFT:
				vt++;
				if (vt == 5) {
					img = ImgManager.arrPlayerImg.get(Value.LEFT);
					vt = 0;
					isRun = false;
				}
				img = ImgManager.arrPlayerImg.get(vt * 7 + Value.LEFT);
				if (typeRun == 2) {
					y -= jump;
				} else {
					y += jump;
				}
				break;
			case Value.RIGHT:
				vt++;
				if (vt == 5) {
					img = ImgManager.arrPlayerImg.get(Value.RIGHT);
					vt = 0;
					isRun = false;
				}
				img = ImgManager.arrPlayerImg.get(vt * 7 + Value.RIGHT);
				if (typeRun == 2) {
					y -= jump;
				} else {
					y += jump;
				}
				break;
			case Value.UP:
				vt++;
				if (vt == 5) {
					img = ImgManager.arrPlayerImg.get(Value.UP);
					vt = 0;
					isRun = false;

				}
				img = ImgManager.arrPlayerImg.get(vt * 7 + Value.UP);
				if (typeRun == 2) {
					x -= jump;
				} else {
					x += jump;
				}
				break;
			case Value.DOWN:
				vt++;
				if (vt == 5) {
					img = ImgManager.arrPlayerImg.get(Value.DOWN);
					vt = 0;
					isRun = false;
				}
				img = ImgManager.arrPlayerImg.get(vt * 7 + Value.DOWN);
				if (typeRun == 2) {
					x -= jump;
				} else {
					x += jump;
				}
				break;
			default:
				break;
			}
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

	public void setSpeed() {
		if (speed == 5) {
			this.speed = 5;
		} else {
			this.speed -= 5;
		}
	}

	public int getOirent() {
		return oirent;
	}

	public void setMove(int oirent, int move) {
		img = ImgManager.arrPlayerImg.get(oirent);
		if (move == 0) {
			jump = 0;
		} else {
			jump = 5;
			this.typeRun = move;
		}
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

	public void setCountBomb(int countBomb) {
		if (countBomb > 5) {
			this.countBomb = 5;
		} else {
			this.countBomb = countBomb;
		}
	}

	public void setLengBomb() {
		if (lengBomb == 5) {
			this.lengBomb = 5;
		} else {
			this.lengBomb++;
		}
	}

	public int getCountBomb() {
		return countBomb;
	}

	public int getLengBomb() {
		return lengBomb;
	}

	public int getZone() {
		return (x + 25) / 50 + (y + 65) / 50 * 13;
	}

	public boolean isDie() {
		if (vt == 10)
			return isDie;
		return false;
	}

	public void setDie(boolean isDie, int vt) {
		this.isDie = isDie;
		this.vt = vt;
	}

	public void playerDieWater(long time) {
		if (vt > 7) {
			vt--;
			img = ImgManager.arrPlayerImg.get((vt % 5) * 7 + vt / 5 + 4);
			vt += 2;
		} else {
			img = ImgManager.arrPlayerImg.get((vt % 5) * 7 + vt / 5 + 4);
			vt++;
		}
	}
}
