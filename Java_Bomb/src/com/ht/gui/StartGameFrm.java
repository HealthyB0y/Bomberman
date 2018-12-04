package com.ht.gui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.ht.audio.SoundManager;

public class StartGameFrm extends JPanel implements MouseListener {
	private JLabel txtBG, txtStart, txtGuide, txtSetting;
	private GUI gui;
	private SoundManager soundMgr;

	public StartGameFrm(GUI gui) {
		setLayout(null);
		setBounds(0, 0, 650, 650);
		init();
		this.gui = gui;
		soundMgr = new SoundManager();
		soundMgr.startBG();
	}

	public void init() {
		txtBG = new JLabel();
		txtBG.setIcon(new ImageIcon(getClass().getResource("/IMG/BG.png")));
		txtBG.setBounds(0, 0, 650, 720);

		txtGuide = new JLabel();
		txtGuide.setIcon(new ImageIcon(getClass().getResource("/IMG/GUIDE.png")));
		txtGuide.setBounds(320, 500, 300, 100);

		txtSetting = new JLabel();
		txtSetting.setIcon(new ImageIcon(getClass().getResource("/IMG/SETTING.png")));
		txtSetting.setBounds(320, 400, 300, 100);

		txtStart = new JLabel();
		txtStart.setIcon(new ImageIcon(getClass().getResource("/IMG/START.png")));
		txtStart.setBounds(320, 300, 300, 100);

		add(txtSetting);
		add(txtStart);
		add(txtGuide);
		add(txtBG);

		txtStart.addMouseListener(this);
		txtGuide.addMouseListener(this);
		txtSetting.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == txtStart) {
			soundMgr.stopBG();
			setVisible(false);
			gui.startGame();
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		soundMgr.getSoundMouseClick();
		if (e.getSource() == txtStart) {
			txtStart.setBounds(330, 310, 300, 100);
		}
		if (e.getSource() == txtGuide) {
			txtGuide.setBounds(330, 510, 300, 100);
		}
		if (e.getSource() == txtSetting) {
			txtSetting.setBounds(330, 410, 300, 100);
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource() == txtStart) {
			txtStart.setBounds(320, 300, 300, 100);
		}
		if (e.getSource() == txtGuide) {
			txtGuide.setBounds(320, 500, 300, 100);
		}
		if (e.getSource() == txtSetting) {
			txtSetting.setBounds(320, 400, 300, 100);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

}
