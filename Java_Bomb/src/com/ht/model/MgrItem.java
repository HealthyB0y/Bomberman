package com.ht.model;

import java.awt.Graphics2D;
import java.util.ArrayList;

import com.ht.maps.MapManager;

public class MgrItem {
	private ArrayList<Item> arrItem = new ArrayList<Item>();

	public void addItem(int o, int type) {
		arrItem.add(new Item((o % 13) * 50, (o / 13) * 50, type));
	}

	public void check(MapManager map) {
		for (int i = 0; i < arrItem.size(); i++) {
			if (map.getArrUnits().get(arrItem.get(i).getZone()).getItem() == -1) {
				arrItem.remove(i);
			}
		}
	}

	public void drawAllItem(Graphics2D g2d) {
		for (int i = 0; i < arrItem.size(); i++) {
			arrItem.get(i).drawItem(g2d);
		}
	}
}
