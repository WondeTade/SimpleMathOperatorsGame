package com.wonde.math;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.SwingUtilities;

/**
 * 
 * A Java class that creates a custom Java mouse cursor. The mouse cursor shows
 * the x/y coordinates of the mouse as the mouse is hovered over the buttons.
 *
 */

public class CustomMouseCursor extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				displayJFrame();
			}
		});
	}

	static void displayJFrame() {
		// Setting up the JFrame
		JFrame jFrame = new JFrame();
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setTitle("Mouse Cursor with X and Y coordinates");

		jFrame.setPreferredSize(new Dimension(400, 300));
		jFrame.pack();
		jFrame.setLocationRelativeTo(null);

		// create an instance of my custom mouse cursor component
		final XYCoordinateComponent xAndyCoordinate = new XYCoordinateComponent();

		// add my component to the DRAG_LAYER of the layered pane (JLayeredPane)
		JLayeredPane layeredPane = jFrame.getRootPane().getLayeredPane();
		layeredPane.add(xAndyCoordinate, JLayeredPane.DRAG_LAYER);
		xAndyCoordinate.setBounds(0, 0, jFrame.getWidth(), jFrame.getHeight());

		// add a mouse motion listener, and updates the custom mouse cursor with the x
		// and y
		// coordinates as the mouse hovers
		jFrame.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent e) {
				xAndyCoordinate.x = e.getX();
				xAndyCoordinate.y = e.getY();
				xAndyCoordinate.repaint();
			}
		});

		// Changing the shape of the currsor to cross hair
		jFrame.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));

		jFrame.setVisible(true);
	}

}

/**
 * This is the class that draws the x/y coordinates near the mouse
 * cursor/pointer.
 */
class XYCoordinateComponent extends JComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int x;
	public int y;

	public XYCoordinateComponent() {
		this.setBackground(Color.blue);
	}

	// use the xy coordinates to update the mouse cursor text/label
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		String s = x + ", " + y;
		g.setColor(Color.red);
		g.drawString(s, x, y);
	}
}