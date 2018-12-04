package com.ht.maps;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

import com.ht.common.ImgManager;
import com.ht.model.Bomb;
import com.ht.model.MgrBomb;
import com.ht.model.MgrCreeps;
import com.ht.model.MgrItem;
import com.ht.model.MgrWater;
import com.ht.model.Player;
import com.ht.model.Water;

public class MapManager {

	private ArrayList<Wall> arrUnits;
	private Random rd = new Random();

	public MapManager(int level) {
		arrUnits = new ArrayList<>();
		setArrUnits("src/MAPS/map" + level);
	}

	public ArrayList<Wall> getArrUnits() {
		return arrUnits;
	}

	public void setArrUnits(String path) {
		try {
			File file = new File(path);
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
			String content = "";
			int j = 0;
			while ((content = in.readLine()) != null) {
				for (int i = 0; i < content.length(); i++) {
					int index = Integer.parseInt(content.charAt(i) + "");
					int item = -1;
					if (rd.nextInt(1000) >= 500 && index != 0) {
						item = rd.nextInt(3);
					}
					arrUnits.add(new Wall(ImgManager.arrUnitImages.get(index), i, j, index, item));
				}
				j++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void drawBottomUnit(Graphics2D g) {
		for (int i = 0; i < arrUnits.size(); i++) {
			arrUnits.get(i).drawUnit(g);
		}
	}

}
