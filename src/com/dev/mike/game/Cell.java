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
public class Cell extends Rectangle {
    private static final long serialVersionID = 1L;
     
    public int[] id = {0,0};
     
    public Cell(Rectangle size, int[] id){
         setBounds(size);
         this.id = id;
    }
    
    public void render(Graphics g, boolean isSelected){
        g.drawImage(Tile.tile_cell,x,y,width,height,null);
        
        if(id!=Tile.air){
            g.drawImage(Tile.tileset_terrain,x + Tile.invItemBorder ,y + Tile.invItemBorder,
                    x-Tile.invItemBorder + width,y-Tile.invItemBorder+ height,id[0]* Tile.tileSize,
               id[1] *Tile.tileSize, id[0]* Tile.tileSize+ Tile.tileSize,id[1] *Tile.tileSize+Tile.tileSize,null);
      
        }
        if(Inventory.isOpen && contains(Component.mse)){
            g.drawImage(Tile.tile_select,x,y,width,height,null);
         
        }
        
        if(isSelected && !Inventory.isOpen){
          g.drawImage(Tile.tile_select,x,y,width,height,null);   
      
        }
        
    }
}
