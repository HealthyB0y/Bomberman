package com.ht.gui;

import java.awt.CardLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import com.ht.audio.SoundManager;
import com.ht.common.Value;
import com.ht.common.ImgManager;

public class GUI extends JFrame {
	private static final long serialVersionUID = 1L;
	public static boolean IS_STOP = false;
	private StartGameFrm startGameFrm;
	private PlayGameFrm playGameFrm;
	private SoundManager soundMgr;

	public GUI() {
		init();
		setSize(665, 720);
		setLayout(new CardLayout());
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void init() {
		soundMgr = new SoundManager();
		new Value();
		new ImgManager();
		startGameFrm = new StartGameFrm(this);
		add(startGameFrm);
	}

	public void startGame() {
		playGameFrm = new PlayGameFrm();
		add(playGameFrm);
		playGameFrm.startGame();
		startGameFrm.setVisible(false);
		playGameFrm.setVisible(true);
		playGameFrm.requestFocusInWindow();
	}

	WindowAdapter adapter = new WindowAdapter() {
		public void windowClosing(WindowEvent e) {
			IS_STOP = true;
			System.exit(0);
		}
	};

}
