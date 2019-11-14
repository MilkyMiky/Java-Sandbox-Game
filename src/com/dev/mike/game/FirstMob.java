/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.mike.game;

import java.awt.*;

/**
 *
 * @author katya
 */
public class FirstMob extends Mob {
    public FirstMob(int x, int y, int width, int height){
        super(x, y, width, height, Tile.FirstMob);
    }
    
    public void tick(){
       super.tick();
    }  
   
    public void render(Graphics g){
       super.render(g);
      
    }
}
