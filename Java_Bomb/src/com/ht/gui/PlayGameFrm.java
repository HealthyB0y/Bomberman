package com.ht.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.BitSet;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import com.ht.audio.SoundManager;
import com.ht.common.ImgManager;
import com.ht.common.Value;
import com.ht.control.Control;
import com.ht.maps.MapManager;
import com.ht.model.Bomb;
import com.ht.model.Creeps;
import com.ht.model.MgrBomb;
import com.ht.model.MgrCreeps;
import com.ht.model.Player;

public class PlayGameFrm extends JPanel implements KeyListener, Runnable {

	public static int level = 1;
	private long time = 0;
	public static int numPlayer = 3;
	private Graphics2D g;
	private BitSet bitset;
	private boolean isPause = false;
	private Thread thread;
	private Control control;
	private JLabel txtEnd;
	private boolean over = false;
	private SoundManager soundMgr = new SoundManager();
	private JProgressBar jProgressBar;

	public PlayGameFrm() {
		setLayout(null);
		setBounds((Value.WIDTH_FRAME - Value.MAP_SIZE) / 2, Value.PADDING_TOP, Value.MAP_SIZE, Value.MAP_SIZE);
		setupGame();
		initProgressBar();
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println(e.getX() + " " + e.getY());
				super.mouseClicked(e);
			}
		});
		setFocusable(true);
	}

	public void initProgressBar() {
		jProgressBar = new JProgressBar();
		jProgressBar.setBounds(0, 650, 649, 30);
		jProgressBar.setMaximum(120000);
		jProgressBar.setBackground(Color.BLUE);
		add(jProgressBar);

	}

	public void startGame() {
		thread = new Thread(this);
		thread.start();
	}

	private void setupGame() {
		isPause = false;
		numPlayer = 3;
		control = new Control();
		bitset = new BitSet(256);
		bitset.clear();
		addKeyListener(this);
	}

	@Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		g = (Graphics2D) graphics;
		control.drawBottomUnit(g);
	}

	public void dialog(int count) {
		if (count == 0) {
			txtEnd = new JLabel();
			txtEnd.setIcon(ImgManager.imgSacNuoc);
			txtEnd.setBounds(100, 200, 464, 250);
			add(txtEnd);
		}
		if (count == 1) {
			txtEnd = new JLabel();
			txtEnd.setIcon(ImgManager.imgMan1);
			txtEnd.setBounds(100, 150, 464, 250);
			add(txtEnd);
		}
		if (count == 2) {
			txtEnd = new JLabel();
			txtEnd.setIcon(ImgManager.imgMan2);
			txtEnd.setBounds(100, 150, 464, 250);
			add(txtEnd);
		}
		if (count == 3) {
			txtEnd = new JLabel();
			txtEnd.setIcon(ImgManager.imgKetthuc);
			txtEnd.setBounds(100, 150, 464, 150);
			add(txtEnd);
		}
		if (count == 4) {
			txtEnd = new JLabel();
			txtEnd.setIcon(ImgManager.imgBatDau);
			txtEnd.setBounds(100, 200, 464, 150);
			add(txtEnd);
		}
		txtEnd.setVisible(true);
	}

	@Override
	public void run() {
		dialog(4);
		soundMgr.startGame();
		soundMgr.getSoundClickStart();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		while (true) {
			jProgressBar.setValue((int) time);
			txtEnd.setVisible(false);
			time++;
			if (time % 1000 == 0)
				if (time == 60000) {
					dialog(0);
					break;
				}
			try {
				runBitset();
				Thread.sleep(1);
				control.movePlayer(time);
				control.moveCreep(time);
				control.timeBomb(time);
				control.bustBoob(time);
				if (control.checkDie()) {
					dialog(0);
					break;
				}
				if (control.checkWin()) {
					dialog(3);
					break;
				}
				repaint();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		soundMgr.startGameOver();
		soundMgr.stopGame();
		try {
			Thread.sleep(4000);
			setVisible(false);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		soundMgr.startBG();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		bitset.set(e.getKeyCode());
		if (!control.isPush()) {
			control.setTimePush(time);
			control.setPush(true);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		bitset.clear(e.getKeyCode());
		control.setPush(false);
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

	public void runBitset() {
		if (bitset.get(KeyEvent.VK_LEFT)) {
			if (!control.getPlayer().isRun()) {
				control.getPlayer().setRun(true);
				control.getPlayer().setMove(Value.LEFT, control.getMove(Value.LEFT, time));
			}
		}
		if (bitset.get(KeyEvent.VK_RIGHT)) {
			if (!control.getPlayer().isRun()) {
				control.getPlayer().setRun(true);
				control.getPlayer().setMove(Value.RIGHT, control.getMove(Value.RIGHT, time));
			}
		}
		if (bitset.get(KeyEvent.VK_UP)) {
			if (!control.getPlayer().isRun()) {
				control.getPlayer().setRun(true);
				control.getPlayer().setMove(Value.UP, control.getMove(Value.UP, time));
			}
		} else if (bitset.get(KeyEvent.VK_DOWN)) {
			if (!control.getPlayer().isRun()) {
				control.getPlayer().setRun(true);
				control.getPlayer().setMove(Value.DOWN, control.getMove(Value.DOWN, time));
			}
		}
		if (bitset.get(KeyEvent.VK_SPACE)) {
			if (time % 100 == 0) {
				control.addBomb(time);
			}
		}
	}

}
