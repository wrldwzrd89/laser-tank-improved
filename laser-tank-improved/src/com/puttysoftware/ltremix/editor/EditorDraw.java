package com.puttysoftware.ltremix.editor;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.puttysoftware.ltremix.resourcemanagers.ImageManager;
import com.puttysoftware.ltremix.utilities.DrawGrid;

class EditorDraw extends JPanel {
    private static final long serialVersionUID = 35935343464625L;
    private final DrawGrid drawGrid;

    public EditorDraw() {
	super();
	final int vSize = EditorViewingWindowManager.getViewingWindowSize();
	final int gSize = ImageManager.getGraphicSize();
	this.setPreferredSize(new Dimension(vSize * gSize, vSize * gSize));
	this.drawGrid = new DrawGrid(vSize);
    }

    @Override
    public void paintComponent(final Graphics g) {
	super.paintComponent(g);
	if (this.drawGrid != null) {
	    final int gSize = ImageManager.getGraphicSize();
	    final int vSize = EditorViewingWindowManager.getViewingWindowSize();
	    for (int x = 0; x < vSize; x++) {
		for (int y = 0; y < vSize; y++) {
		    g.drawImage(this.drawGrid.getImageCell(y, x), x * gSize, y * gSize, gSize, gSize, null);
		}
	    }
	}
    }

    public DrawGrid getGrid() {
	return this.drawGrid;
    }
}
