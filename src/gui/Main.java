package gui;

import javax.swing.JFrame;

import gui.Frame;

public class Main {
	public static void main(String[] args) {
		Frame frame = new Frame(600,450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
