/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.mike.game;

import java.awt.*;

import java.awt.event.*;

/**
 *
 * @author katya
 */
public class Inventory {
    
    public static Cell[] invBar = new Cell[Tile.invLength];
    public static Cell[] invBag = new Cell[Tile.invLength * Tile.invHeight];
    public static boolean isOpen = false;
    public static boolean isHolding = false;
    
    public static int[] holdingID = Tile.air;
    
    public static int selected = 0;
    
    public Inventory(){
        for(int i = 0; i < invBar.length; i++){
           invBar[i]  = new Cell(new Rectangle(
                   Tile.invBorderSpace + i*(Tile.invCellSize + Tile.invCellSpace),
                   Tile.invBorderSpace,
                   Tile.invCellSize,Tile.invCellSize),Tile.air);
           
        }
        
        int x = 0, y = 0;
        for(int i = 0; i < invBag.length; i++){
            invBag[i] = new Cell(new Rectangle(
                Tile.invBorderSpace + x * (Tile.invCellSize + Tile.invCellSpace),
                Tile.invBorderSpace + (Tile.invHeight * (Tile.invCellSize + Tile.invBorderSpace)
                        -(y * (Tile.invCellSize+Tile.invCellSpace))),
                   Tile.invCellSize,Tile.invCellSize),Tile.air);
            x++;
            if(x == Tile.invLength){
               x = 0;
               y++;
            }
        }        
        invBar[0].id = Tile.earth;
        invBar[1].id = Tile.grass;
        invBar[2].id = Tile.sand;
        invBar[3].id = Tile.stone;
        invBar[4].id = Tile.wood;
        invBar[5].id = Tile.leaves;
        invBar[6].id = Tile.glass;
        invBar[7].id = Tile.stoneWall;
        
    }
    
    public static void click(MouseEvent e){
        if(e.getButton() == 1 ){
            if(isOpen){
                for(int i = 0; i < invBar.length; i ++){
                    if(invBar[i].contains(Component.mse)){
                        if(invBar[i].id != Tile.air && !isHolding){
                            holdingID = invBar[i].id;
                            invBar[i].id = Tile.air;

                            isHolding  = true;
                        } 
                        else if((isHolding )&& (invBar[i].id == Tile.air)){
                               invBar[i].id = holdingID;
                               isHolding = false;
                        }
                        else if((isHolding )&& (invBar[i].id != Tile.air)){
                            int[] temp_cell = invBar[i].id;
                            invBar[i].id = holdingID;
                            holdingID = temp_cell;
                        }
                   }
                }
                
                for(int i = 0; i < invBag.length; i ++){
                    if(invBag[i].contains(Component.mse)){
                        if(invBag[i].id != Tile.air && !isHolding){
                            holdingID = invBag[i].id;
                            invBag[i].id = Tile.air;

                            isHolding  = true;
                        } 
                        else if((isHolding )&& (invBag[i].id == Tile.air)){
                               invBag[i].id = holdingID;
                               isHolding = false;
                        }
                        else if((isHolding )&& (invBag[i].id != Tile.air)){
                            int[] temp_cell = invBag[i].id;
                            invBag[i].id = holdingID;
                            holdingID = temp_cell;
                        }
                   }
                }
            }        
        }
    }
    
    public void render(Graphics g){
         
        for(int i = 0; i < invBar.length; i++){
            boolean isSelected = false;
            if(i == selected){
                 isSelected = true;
            }
            invBar[i].render(g, isSelected);
        }
         
        if(isOpen){
             for(int i = 0; i < invBag.length; i++){
                 invBag[i].render(g, false);
            }
        }
         
        if(isHolding){
             g.drawImage(Tile.tileset_terrain, Component.mse.x,Component.mse.y,
                   Component.mse.x + Tile.tileSize,
                   Component.mse.y + Tile.tileSize,
                   holdingID[0]* Tile.tileSize,
                   holdingID[1] *Tile.tileSize,
                   holdingID[0]* Tile.tileSize + Tile.tileSize,
                   holdingID[1] *Tile.tileSize+Tile.tileSize,null);
              
        }
    }
    
}
