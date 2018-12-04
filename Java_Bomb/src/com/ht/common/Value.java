package com.ht.common;

import java.awt.Toolkit;

public class Value {
	public static final int MAP_SIZE = 650;
	public static final int LEFT_RIGHT_SIZE = 250;
	public static int WIDTH_FRAME;
	public static int HEIGHT_FRAME;
	public static int PADDING_LEFT;
	public static int PADDING_TOP;
	public static int RIGHT_START_X;
	public static boolean SOUND = true;
	public static final int SIZE = 50;

	public static final int LEFT = 3, RIGHT = 2, UP = 0, DOWN = 1;

	public static final int COUNT_CREEPS = 3;

	public Value() {
		WIDTH_FRAME = Toolkit.getDefaultToolkit().getScreenSize().width;
		HEIGHT_FRAME = Toolkit.getDefaultToolkit().getScreenSize().height;
		PADDING_TOP = (HEIGHT_FRAME - MAP_SIZE) / 2;
		PADDING_LEFT = (WIDTH_FRAME - MAP_SIZE - 2 * LEFT_RIGHT_SIZE) / 4;
		RIGHT_START_X = WIDTH_FRAME - PADDING_LEFT - LEFT_RIGHT_SIZE;

	}
}
