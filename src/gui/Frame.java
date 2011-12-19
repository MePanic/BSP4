package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Algo.BuddyAlgo;


public class Frame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JButton buttonAlloc;
	JButton buttonDeAlloc;
	JButton buttonSize;
	double xfaktor,yfaktor;
	int pointSize = 4;
	int originX,originY;
	BuddyAlgo ba;
	List<ArrayList<Integer>> blocks = new LinkedList<ArrayList<Integer>>();
	double faktor = 4;
	int x,y;
	String status = "Waiting for Memory...";

	Frame(int x,int y){
		super("Buddy Algorithmus");
		setSize(x, y);
		this.x = x;
		this.y = y;
		
		final JTextField numFieldSize = new JTextField();
		numFieldSize.setHorizontalAlignment(JTextField.RIGHT);

		
		final JTextField numFieldAlloc = new JTextField();
		numFieldAlloc.setHorizontalAlignment(JTextField.RIGHT);

		
		final JTextField numFieldIndex = new JTextField();
		numFieldIndex.setHorizontalAlignment(JTextField.RIGHT);
		

		buttonSize = new JButton("<HTML><CENTER><BODY>new<BR>Memory</BODY></HTML>");
		buttonSize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				status = "Everything okay";
				try{
				ba = new BuddyAlgo(Integer.parseInt(numFieldSize.getText()));
				}catch(IllegalArgumentException e){
					status = "Invalid memorysize";
					repaint();
					return;
				}
				ba.toString();
				blocks = ba.blocks();
				repaint();
				
				
				}
			}
		);
		
		buttonAlloc = new JButton("<HTML><CENTER><BODY>Alloc<BR>Size</BODY></HTML>");
		buttonAlloc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {

				try{
				ba.allocateBlock(Integer.parseInt(numFieldAlloc.getText()));
				}catch(Exception e){
					status = "Not enough memory";
					repaint();
					return;
				}
				ba.toString();
				blocks = ba.blocks();
				repaint();
				
				
				}
			}
		);

		buttonDeAlloc = new JButton("<HTML><CENTER><BODY>DeAlloc<BR>Block</BODY></HTML>");
		buttonDeAlloc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {

				try{
				ba.deallocateBlock(Integer.parseInt(numFieldIndex.getText()));
				}catch(Exception e){
					status = "Unknown ID";
					repaint();
					return;
				}
				ba.toString();
				blocks = ba.blocks();
				repaint();

				}
			}
		);

		Container content = getContentPane();
		
		JPanel menu = new JPanel();
		menu.setLayout(new GridLayout(1, 2));
		menu.setPreferredSize(new Dimension(x/2, 50));
		
		menu.add(buttonSize, BorderLayout.WEST);
		menu.add(numFieldSize, BorderLayout.WEST);
		menu.add(buttonAlloc, BorderLayout.WEST);
		menu.add(numFieldAlloc, BorderLayout.WEST);
		menu.add(buttonDeAlloc, BorderLayout.WEST);
		menu.add(numFieldIndex, BorderLayout.WEST);

		content.add(BorderLayout.NORTH, menu);
	}
	
	
	
	  public void paint( Graphics g ){
        super.paint(g);
        
        int next = 30;
		faktor =  1.5;
		int oversize = 0;
		int offset = -5;
		

        for(ArrayList<Integer> b : blocks){
        	
        	if(b.get(0) < 5){
        		faktor = 3;
        	}
        }
        
        for(ArrayList<Integer> b : blocks){
        	if(next +30 + (b.get(0)* faktor) > 1000 ){
        		offset = -offset;
        		int t = (int) (1000 - (next +30));
        		
                g.setColor(Color.RED);
                if(b.get(1) == 1)
                	g.setColor(Color.GREEN);
            	g.fillRect(next,((oversize+1) * 150),(int) ( t),100);

            	g.setColor(Color.BLACK);

            	g.drawString(b.get(0).toString() + " ...",next,((oversize+1) * 144)+offset);
            	g.drawRect(next,((oversize+1) * 150),(int) ( t),100);
                if(b.get(1) == 1)
                	g.drawString(b.get(2).toString(),next,(((oversize) *150) +  266)+offset);
            	
        		next = 30;
        		oversize ++;
        		offset = 5;
        		
                g.setColor(Color.RED);
                if(b.get(1) == 1)
                	g.setColor(Color.GREEN);
            	g.fillRect(next,((oversize+1) * 150),(int) ((b.get(0)*faktor) - t),100);

            	g.setColor(Color.BLACK);

            	g.drawString(" ..." + b.get(0).toString(),next-20,((oversize+1) * 150)-6+offset);
            	g.drawRect(next,((oversize+1) * 150),(int) ((b.get(0)*faktor) - t),100);
                if(b.get(1) == 1)
                	g.drawString(b.get(2).toString(),next,(((oversize) *150) +  266)+offset);
            	

            	next += ((b.get(0)*faktor) - t);
            	
            	
                	
        		
        	}else{
        		offset = -offset;
            g.setColor(Color.RED);
            if(b.get(1) == 1)
            	g.setColor(Color.GREEN);
        	g.fillRect(next,((oversize+1) * 150),(int) (b.get(0)*faktor),100);

        	g.setColor(Color.BLACK);

        	g.drawString(b.get(0).toString(),next,((oversize+1) * 150)-6+offset);
        	g.drawRect(next,((oversize+1) * 150),(int) (b.get(0)*faktor),100);
        	
            if(b.get(1) == 1)
            	g.drawString(b.get(2).toString(),next,(((oversize) *150) +  266)+offset);
            	
        	next += b.get(0) *faktor;
        	}
        }
        
        g.drawString(status,(x/2)-(status.length()*4),(y+(oversize * 150))-50);
		status = "Everything okay";
        
        if(next > 340 && (oversize == 0)){
        	x = next + 30;
        }
        if(oversize > 0){
        	x = 1024;
        }
        
        
        setSize(x,(Math.min(y+(oversize * 150), 768)));
        
	 }
    }

