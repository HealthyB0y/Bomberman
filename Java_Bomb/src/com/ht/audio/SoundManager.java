package com.ht.audio;

import com.ht.common.Value;

public class SoundManager {
	public static SourceEffect soundBG;
	public static SourceEffect soundPlayGame;
	public static SourceEffect soundClickStart;
	public static SourceEffect soundGameOver;
	public static SourceEffect soundLunchItem;
	public static SourceEffect soundTimeOver;
	public static SourceEffect soundBomNo;
	public static SourceEffect soundMouseClick;

	public SoundManager() {
		soundBG = new SourceEffect("background.wav");
		soundPlayGame = new SourceEffect("background_in_game.wav");
		soundClickStart = new SourceEffect("click_start.wav");
		soundGameOver = new SourceEffect("gameover.wav");
		soundLunchItem = new SourceEffect("lunch_item.wav");
		soundMouseClick = new SourceEffect("mouseClick.wav");
		soundBomNo = new SourceEffect("BomNo.wav");

	}

	public void getSoundMouseClick() {
		if (Value.SOUND) {
			soundMouseClick.loop(1);
		}
	}

	public void getSoundBomNo() {
		if (Value.SOUND) {
			soundBomNo.play();
		}
	}

	public void startBG() {
		if (Value.SOUND) {
			soundBG.loop(1);
		}
	}

	public void stopBG() {
		if (Value.SOUND) {
			soundBG.stop();
		}
	}

	public void startGame() {
		if (Value.SOUND) {
			soundPlayGame.loop(1);
		}
	}

	public void stopGame() {
		if (Value.SOUND) {
			soundPlayGame.stop();
		}
	}

	public void getSoundClickStart() {
		if (Value.SOUND) {
			soundClickStart.play();
		}
	}

	public void startGameOver() {
		if (Value.SOUND) {
			soundGameOver.play();
		}
	}

	public void getSoundLunchItem() {
		if (Value.SOUND) {
			soundLunchItem.play();
		}
	}

}
