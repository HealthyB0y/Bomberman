package com.ht.control;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

import com.ht.audio.SoundManager;
import com.ht.common.ImgManager;
import com.ht.common.Value;
import com.ht.maps.MapManager;
import com.ht.maps.Wall;
import com.ht.model.MgrBomb;
import com.ht.model.MgrCreeps;
import com.ht.model.MgrItem;
import com.ht.model.MgrWater;
import com.ht.model.Player;

public class Control {
	private MapManager mapMgr = new MapManager(1);
	private Player player = new Player(275, 510, 40, 1);
	private MgrBomb bombMgr = new MgrBomb();
	private MgrWater waterMgr = new MgrWater();
	private MgrCreeps creepMgr = new MgrCreeps();
	private MgrItem itemMgr = new MgrItem();
	private Random rd = new Random();
	private long timePush;
	private boolean isPush = false;

	public void setPush(boolean isPush) {
		this.isPush = isPush;
	}

	public boolean isPush() {
		return isPush;
	}

	public void setTimePush(long timePush) {
		this.timePush = timePush;
	}

	public void addBomb(long time) {
		int x = (player.getX() + 25) / 50 + (player.getY() + 65) / 50 * 13;
		if (mapMgr.getArrUnits().get(x).getType() == 0 && player.getCountBomb() != 0) {
			bombMgr.addBomb(((player.getX() + 25) / 50) * 50, (((player.getY() + 65) / 50)) * 50, time,
					player.getLengBomb(), x);
			mapMgr.getArrUnits().get(x).setType(-1);
			player.setCountBomb(player.getCountBomb() - 1);
		}
	}

	public void timeBomb(long time) {
		if (time % 80 == 0) {
			bombMgr.update();
		}
	}

	public void bustBoob(long time) {
		for (int i = 0; i < waterMgr.getArrWater().size(); i++) {
			if (time - waterMgr.getArrWater().get(i).getTime() >= 200) {
				waterMgr.getArrWater().remove(i);
			}
		}
		for (int i = 0; i < bombMgr.getArrBomb().size(); i++) {
			if (time - bombMgr.getArrBomb().get(i).getTime() >= 4000) {

				test(i, time);
			}
		}
		for (int i = 0; i < mapMgr.getArrUnits().size(); i++) {
			if (mapMgr.getArrUnits().get(i).getType() == -2) {
				mapMgr.getArrUnits().get(i).setType(0);
			}
		}
	}

	public void test(int x, long time) {
//			if(player.getZone() == bombMgr.getArrBomb().get(x).getO()){
//				System.out.println(12);
//			}
		player.setCountBomb(player.getCountBomb() + 1);
		mapMgr.getArrUnits().get(bombMgr.getArrBomb().get(x).getO()).setType(-2);
		waterMgr.addWater(((bombMgr.getArrBomb().get(x).getO()) % 13) * 50,
				((bombMgr.getArrBomb().get(x).getO()) / 13) * 50, time);
		for (int j = 1; j <= 4; j++) {
			int t = 0;
			switch (j) {
			case 1:
				t = -1;
				break;
			case 2:
				t = -13;
				break;
			case 3:
				t = 1;
				break;
			case 4:
				t = 13;
				break;
			default:
				break;
			}
			for (int k = 1; k <= player.getLengBomb(); k++) {
				itemMgr.check(mapMgr);
				if (bombMgr.getArrBomb().get(x).getO() + k * t > 169) {
					continue;
				}
				int o = bombMgr.getArrBomb().get(x).getO() + k * t;
				int type = mapMgr.getArrUnits().get(o).getType(); // lấy thuộc tính ô của quả bom thứ x,
																	// kiểm tra type của ô này

				if (player.getZone() == o || player.getZone() == bombMgr.getArrBomb().get(x).getO()) {
					player.setDie(true, 0);
				}
				creepMgr.checkDie(o);
				if (mapMgr.getArrUnits().get(o).getItem() != -1 && mapMgr.getArrUnits().get(o).getType() == 0) {
					mapMgr.getArrUnits().get(o).setItem(-1); // xoa item
				}
				if (type == 3) {
					// nổ thùng rảnh thì làm hiệu ứng
					mapMgr.getArrUnits().get((bombMgr.getArrBomb().get(x).getO() + k * t))
							.setImg(ImgManager.arrUnitImages.get(0));
					mapMgr.getArrUnits().get((bombMgr.getArrBomb().get(x).getO() + k * t)).setType(0);
					if (mapMgr.getArrUnits().get((bombMgr.getArrBomb().get(x).getO() + k * t)).getItem() != -1) {
						itemMgr.addItem(bombMgr.getArrBomb().get(x).getO() + k * t,
								mapMgr.getArrUnits().get((bombMgr.getArrBomb().get(x).getO() + k * t)).getItem());
					}
					break;
				} else if (type == -1) {
					test(bombMgr.searchBomb(bombMgr.getArrBomb().get(x).getO() + k * t), time); // đệ quy chính nó
				} else if (type == 0) {
					waterMgr.addWater((o % 13) * 50, (o / 13) * 50, time);
				} else {
					break;
				}
			}
		}
		bombMgr.getArrBomb().remove(x);

	}

	public void checkItem() {
		if (mapMgr.getArrUnits().get(player.getZone()).getItem() != -1) {
			SoundManager soundMgr = new SoundManager();
			soundMgr.getSoundLunchItem();
			switch (mapMgr.getArrUnits().get(player.getZone()).getItem()) {
			case 0:
				player.setLengBomb();
				break;
			case 1:
				player.setCountBomb(player.getCountBomb() + 1);
				break;
			case 2:
				player.setSpeed();
			default:
				break;
			}
			mapMgr.getArrUnits().get(player.getZone()).setItem(-1);
		}
		itemMgr.check(mapMgr);
	}

	public Player getPlayer() {
		return player;
	}

	public void movePlayer(long time) {
		checkItem();
		player.movePlayer(time);
	}

	public void pushWall(int o, int orient, long time) {
		if (time - timePush >= 1000 && time != -1) {
			switch (orient) {
			case Value.DOWN:
				if (mapMgr.getArrUnits().get(o + 13).getType() == 3 && mapMgr.getArrUnits().get(o + 26).getType() == 0
						&& !creepMgr.checkZone(o + 26)) {
					// neu o dang day type = 3 va o duoi no la 0 va k co crep thi cho phep day
					mapMgr.getArrUnits().get(o + 13).setType(0);
					mapMgr.getArrUnits().get(o + 26).setType(3);
					mapMgr.getArrUnits().get(o + 13).setImg(ImgManager.arrUnitImages.get(0));
					mapMgr.getArrUnits().get(o + 26).setImg(ImgManager.arrUnitImages.get(3));
					mapMgr.getArrUnits().get(o + 13).setItem(-1);
					mapMgr.getArrUnits().get(o + 26).setItem(-1);
					isPush = false;
				}
				break;
			case Value.UP:
				if (mapMgr.getArrUnits().get(o - 13).getType() == 3 && mapMgr.getArrUnits().get(o - 26).getType() == 0
						&& !creepMgr.checkZone(o - 26)) {
					// neu o dang day type = 3 va o duoi no la 0 va k co crep thi cho phep day
					mapMgr.getArrUnits().get(o - 13).setType(0);
					mapMgr.getArrUnits().get(o - 26).setType(3);
					mapMgr.getArrUnits().get(o - 13).setImg(ImgManager.arrUnitImages.get(0));
					mapMgr.getArrUnits().get(o - 26).setImg(ImgManager.arrUnitImages.get(3));
					mapMgr.getArrUnits().get(o - 13).setItem(-1);
					mapMgr.getArrUnits().get(o - 26).setItem(-1);
					isPush = false;
				}
				break;
			case Value.RIGHT:
				if (mapMgr.getArrUnits().get(o + 1).getType() == 3 && mapMgr.getArrUnits().get(o + 2).getType() == 0
						&& !creepMgr.checkZone(o + 2)) {
					// neu o dang day type = 3 va o duoi no la 0 va k co crep thi cho phep day
					mapMgr.getArrUnits().get(o + 1).setType(0);
					mapMgr.getArrUnits().get(o + 2).setType(3);
					mapMgr.getArrUnits().get(o + 1).setImg(ImgManager.arrUnitImages.get(0));
					mapMgr.getArrUnits().get(o + 2).setImg(ImgManager.arrUnitImages.get(3));
					mapMgr.getArrUnits().get(o + 1).setItem(-1);
					mapMgr.getArrUnits().get(o + 2).setItem(-1);
					isPush = false;
				}
				break;
			case Value.LEFT:
				if (mapMgr.getArrUnits().get(o - 1).getType() == 3 && mapMgr.getArrUnits().get(o - 2).getType() == 0
						&& !creepMgr.checkZone(o - 2)) {
					// neu o dang day type = 3 va o duoi no la 0 va k co crep thi cho phep day
					mapMgr.getArrUnits().get(o - 1).setType(0);
					mapMgr.getArrUnits().get(o - 2).setType(3);
					mapMgr.getArrUnits().get(o - 1).setImg(ImgManager.arrUnitImages.get(0));
					mapMgr.getArrUnits().get(o - 2).setImg(ImgManager.arrUnitImages.get(3));
					mapMgr.getArrUnits().get(o - 1).setItem(-1);
					mapMgr.getArrUnits().get(o - 2).setItem(-1);
					isPush = false;
				}
				break;
			default:
				break;
			}
		}
	}

	public int getMove(int orient, long time) {
		int x = player.getX() + 50;
		int y = player.getY() + 85;
		int o = x / 50 + (y / 50) * 13;
		if (orient == Value.DOWN) {
			if (y % 50 == 45) {
				if (x % 50 != 0) { // đang đứng giữa 1 ô
					if (mapMgr.getArrUnits().get(o + 13).getType() == 0) {
						isPush = false;
					}
					pushWall(o, orient, time);
					if (mapMgr.getArrUnits().get(o + 13).getType() != 0) {
						return 0;
					}
				} else { // đứng giữa 2 ô
					if (mapMgr.getArrUnits().get(o + 12).getType() != 0
							&& mapMgr.getArrUnits().get(o + 13).getType() != 0) {
						return 0;
					} else {
						if (mapMgr.getArrUnits().get(o + 12).getType() == 0
								&& mapMgr.getArrUnits().get(o + 13).getType() != 0) {
							return 2;
							// di chuyen 25 toa do ve ben trai // orient == botton va x--;
						} else if (mapMgr.getArrUnits().get(o + 13).getType() == 0
								&& mapMgr.getArrUnits().get(o + 12).getType() != 0) {
							return 3;
							// di chuyen 25 toa do ve ben phai // orient == botton va x++;
						}
					}
				}
			} else {
				return 1;
			}
		}

		if (orient == Value.UP) {
			if (y % 50 == 45) {
				if (x % 50 != 0) {
					if (mapMgr.getArrUnits().get(o - 13).getType() == 0) {
						isPush = false;
					}
					pushWall(o, orient, time);
					if (mapMgr.getArrUnits().get(o - 13).getType() != 0) {
						return 0;
					}
				} else {
					if (mapMgr.getArrUnits().get(o - 13).getType() != 0
							&& mapMgr.getArrUnits().get(o - 14).getType() != 0) {
						return 0;
					} else {
						if (mapMgr.getArrUnits().get(o - 14).getType() == 0
								&& mapMgr.getArrUnits().get(o - 13).getType() != 0) {
							return 2;
							// di chuyen 25 toa do ve ben trai // orient == botton va x--;
						} else if (mapMgr.getArrUnits().get(o - 13).getType() == 0
								&& mapMgr.getArrUnits().get(o - 14).getType() != 0) {
							return 3;
							// di chuyen 25 toa do ve ben phai // orient == botton va x++;
						}
					}
				}
			} else {
				return 1;
			}
		}
		if (orient == Value.LEFT) {
			if (x % 50 != 0) {
				if (y % 50 == 45) {
					if (mapMgr.getArrUnits().get(o - 1).getType() == 0) {
						isPush = false;
					}
					pushWall(o, orient, time);
					if (mapMgr.getArrUnits().get(o - 1).getType() != 0) {
						return 0;
					}
				} else {
					if (mapMgr.getArrUnits().get(o - 1).getType() != 0
							&& mapMgr.getArrUnits().get(o - 14).getType() != 0) {
						return 0;
					} else {
						if (mapMgr.getArrUnits().get(o - 14).getType() == 0
								&& mapMgr.getArrUnits().get(o - 1).getType() != 0) {
							return 2;
							// di chuyen 25 toa do ve ben trai // orient == botton va x--;
						} else if (mapMgr.getArrUnits().get(o - 1).getType() == 0
								&& mapMgr.getArrUnits().get(o - 14).getType() != 0) {
							return 3;
							// di chuyen 25 toa do ve ben phai // orient == botton va x++;
						}
					}
				}
			} else {
				return 1;
			}
		}
		if (orient == Value.RIGHT) {
			if (x % 50 != 0) {
				if (y % 50 == 45) {
					if (mapMgr.getArrUnits().get(o + 1).getType() == 0) {
						isPush = false;
					}
					pushWall(o, orient, time);
					if (mapMgr.getArrUnits().get(o + 1).getType() != 0) {
						return 0;
					}
				} else {
					if (mapMgr.getArrUnits().get(o + 1).getType() != 0
							&& mapMgr.getArrUnits().get(o - 12).getType() != 0) {
						return 0;
					} else {
						if (mapMgr.getArrUnits().get(o - 12).getType() == 0
								&& mapMgr.getArrUnits().get(o + 1).getType() != 0) {
							return 2;
							// di chuyen 25 toa do ve ben trai // orient == botton va x--;
						} else if (mapMgr.getArrUnits().get(o + 1).getType() == 0
								&& mapMgr.getArrUnits().get(o - 12).getType() != 0) {
							return 3;
							// di chuyen 25 toa do ve ben phai // orient == botton va x++;
						}
					}
				}
			} else {
				return 1;
			}
		}
		return 1;
	}

	public void moveCreep(long time) {
		creepMgr.checkImpactPlayer(player);
		creepMgr.moveAllCreeps(mapMgr, player, time);
	}

	public void drawBottomUnit(Graphics2D g) {
		for (int i = 0; i < mapMgr.getArrUnits().size(); i++) {
			mapMgr.getArrUnits().get(i).drawUnit(g);
		}
		waterMgr.drawAllWater(g);
		bombMgr.drawAllBomb(g);
		creepMgr.drawAllCreeps(g);
		itemMgr.drawAllItem(g);
		player.drawPlayer(g);
	}

	public boolean checkDie() {
		if (player.isDie()) {
			return true;
		}
		return false;
	}

	public boolean checkWin() {
		if (creepMgr.getArrCreeps().size() == 0) {
			return true;
		}
		return false;
	}
}
