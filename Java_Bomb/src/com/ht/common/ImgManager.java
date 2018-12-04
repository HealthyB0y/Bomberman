package com.ht.common;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class ImgManager {
	public static ArrayList<Image> arrUnitImages;
	public static ArrayList<Image> arrPlayerImg;
	public static ArrayList<Image> arrCreepImg;
	public static ArrayList<Image> arrItemImg;
	public static ArrayList<Image> arrWaterImg;
	public static ImageIcon imgSacNuoc, imgMan1, imgMan2, imgKetthuc, imgBatDau;

	public void loadPlayer() {
		arrPlayerImg = new ArrayList<Image>();
		try {
			BufferedImage bfi = ImageIO.read(getClass().getResourceAsStream("/IMG/player1.png"));
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 7; j++) {
					arrPlayerImg.add(bfi.getSubimage(j * 100, i * 100, 100, 100));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadCreep() {
		arrCreepImg = new ArrayList<Image>();
		try {
			BufferedImage bfi = ImageIO.read(getClass().getResourceAsStream("/IMG/boss.png"));
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					arrCreepImg.add(bfi.getSubimage(j * 75, i * 75, 75, 75));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadItem() {
		arrItemImg = new ArrayList<Image>();
		try {
			BufferedImage bfi = ImageIO.read(getClass().getResourceAsStream("/IMG/item.png"));
			for (int i = 0; i < 3; i++) {
				arrItemImg.add(bfi.getSubimage(0, i * 50, 50, 50));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void loadWater() {
		arrWaterImg = new ArrayList<Image>();
		try {
			BufferedImage bfi = ImageIO.read(getClass().getResource("/IMG/Bomwater.png"));
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 3; j++) {
					arrWaterImg.add(bfi.getSubimage(j * 50, i * 50, 50, 50));
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void loadDialog() {
		imgMan1 = new ImageIcon(getClass().getResource("/IMG/man1.png"));
		imgMan2 = new ImageIcon(getClass().getResource("/IMG/man2.png"));
		imgKetthuc = new ImageIcon(getClass().getResource("/IMG/ketthuc.png"));
		imgSacNuoc = new ImageIcon(getClass().getResource("/IMG/sacnuoc.png"));
		imgBatDau = new ImageIcon(getClass().getResource("/IMG/batdau.png"));
	}

	public void addImgWall() {
		arrUnitImages = new ArrayList<Image>();
		arrUnitImages.add(new ImageIcon(getClass().getResource("/IMG/wall0.png")).getImage());
		arrUnitImages.add(new ImageIcon(getClass().getResource("/IMG/wall1.png")).getImage());
		arrUnitImages.add(new ImageIcon(getClass().getResource("/IMG/wall2.png")).getImage());
		arrUnitImages.add(new ImageIcon(getClass().getResource("/IMG/wall3.png")).getImage());
	}

	public ImgManager() {
		addImgWall();
		loadPlayer();
		loadCreep();
		loadItem();
		loadWater();
		loadDialog();
	}

}
