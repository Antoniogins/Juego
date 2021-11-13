/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.awt.Graphics;

/**
 * Defines a common interface for the views
 * @author japf
 */
public interface IAWTGameView {
    
    /**
     * Draw the view
     * @param g drawing object use by geme panel (in paintComponent).
     */
    public void draw(Graphics g);
}
