package com.ht.model;

import java.awt.Graphics2D;
import java.util.ArrayList;

import com.ht.audio.SoundManager;
import com.ht.common.ImgManager;
import com.ht.common.Value;
import com.ht.gui.PlayGameFrm;
import com.ht.maps.MapManager;

public class MgrCreeps {
	private Creeps creeps;

	private ArrayList<Creeps> arrCreeps;
	private SoundManager soudManager = new SoundManager();

	public MgrCreeps() {
		arrCreeps = new ArrayList<Creeps>();
		addCreeps();
	}

	public void moveAllCreeps(MapManager map, Player player, long time) {
		for (int i = 0; i < arrCreeps.size(); i++) {
			arrCreeps.get(i).moveCreeps(map, player, time);
			if (arrCreeps.get(i).getVt() == 4 && arrCreeps.get(i).isDie()) {
				arrCreeps.remove(i);
			}
		}
	}

	public void checkImpactPlayer(Player player) {
		for (int i = 0; i < arrCreeps.size(); i++) {
			if (arrCreeps.get(i).getZone() == player.getZone()) {
				if (!player.isDie()) {
					soudManager.getSoundBomNo();
					player.setDie(true, 8);
				}
			}
		}
	}

	public void drawAllCreeps(Graphics2D g2d) {
		for (int i = 0; i < arrCreeps.size(); i++) {
			arrCreeps.get(i).drawCreeps(g2d);
		}
	}

	public void addCreeps() {
		for (int i = 0; i < Value.COUNT_CREEPS; i++) {
			arrCreeps.add(new Creeps(40, Value.SIZE * 4 * i + 30, 70, 2));
		}
	}

	public void checkDie(int o) {
		for (int i = 0; i < arrCreeps.size(); i++) {
			if (arrCreeps.get(i).getZone() == o) {
				arrCreeps.get(i).setDie(true);
			}
		}
	}

	public boolean checkZone(int o) {
		for (int i = 0; i < arrCreeps.size(); i++) {
			if (arrCreeps.get(i).getZone() == o) {
				return true;
			}
		}
		return false;
	}

	public ArrayList<Creeps> getArrCreeps() {
		return arrCreeps;
	}

}
