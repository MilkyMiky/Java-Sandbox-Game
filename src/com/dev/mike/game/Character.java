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
public class Character extends DoubleRectangle{
    
    public double fallingSpeed = 1.2;
    public double movementSpeed = 0.6;
    public double jumpingSpeed = 1;
    
    public int jumpingHeight = 100, jumpingCount = 0;
    public int animation = 0;
    public int animationFrame = 0, animationTime = 30;
    public  boolean isJumping = false;
    
    public Character( int width, int height){
        setBounds(20*Tile.tileSize, 20*Tile.tileSize ,
                width,height);  
    }
    
    public void tick(){
        if(!isJumping && !isCollidingWithBlock(new Point((int)x + 2, (int)(y + height)),
                new Point((int)(x+width-2),(int)(y + height)) ) ){
            y += fallingSpeed;
            com.dev.mike.game.Component.sY +=fallingSpeed;
        }  else {
               if(com.dev.mike.game.Component.isJumping && !Inventory.isOpen){
                   isJumping = true;
               }     
        }
        
        if(com.dev.mike.game.Component.isMoving && !Inventory.isOpen){
            boolean canMove = false;
            
            if(com.dev.mike.game.Component.dir == movementSpeed){
               
                canMove = isCollidingWithBlock(new Point((int)(x + width),(int)y),
                        new Point((int)(x + width), (int)(y +( height-2))));
            }else if(com.dev.mike.game.Component.dir == -movementSpeed){
                
                 canMove = isCollidingWithBlock(new Point((int)x-1,(int)y), 
                         new Point((int)x-1,(int)(y +(height-2))));
            }
            
            if(animationFrame >=animationTime){
               if(animation > 1){
                   animation = 1;
               } else{
                  animation +=1; 
               }
               animationFrame = 0; 
               
            }else{
                animationFrame +=1;
            }
            if(!canMove){
            
                x += com.dev.mike.game.Component.dir;
                com.dev.mike.game.Component.sX += com.dev.mike.game.Component.dir;
            }
        }else{
            animation = 0;
        }
        
        if(isJumping ){
            if(!isCollidingWithBlock(new Point((int)x + 2, (int)y), 
                    new Point((int)(x+width - 2), (int)y))){
                if(jumpingCount >= jumpingHeight){
                    isJumping = false;
                    jumpingCount = 0;
                }else{
                    y -= jumpingSpeed;
                    com.dev.mike.game.Component.sY -= jumpingSpeed;
                    jumpingCount += 1;
                }
            }else{
               isJumping = false;
               jumpingCount = 0;
            }
        }
       
    }

    public boolean isCollidingWithBlock(Point pt1, Point pt2){
        for(int x = (int) (this.x/Tile.tileSize); x < (int) (this.x/Tile.tileSize+3); x++){
           for(int y = (int) (this.y/Tile.tileSize); y < (int) (this.y/Tile.tileSize+3); y++){
              if(x >=0 && y>=0 && x < com.dev.mike.game.Component.level.block.length && y < com.dev.mike.game.Component.level.block[0].length)
               if(!Tile.backgroundSet.contains(com.dev.mike.game.Component.level.block[x][y].id)){
                    if(com.dev.mike.game.Component.level.block[x][y].contains(pt1) || com.dev.mike.game.Component.level.block[x][y].contains(pt2)){
                        return true;
                    }
                }
            } 
        }
        return false;
    }
    
    public void render(Graphics g){

       if(com.dev.mike.game.Component.dir == movementSpeed) {
            g.drawImage(Tile.tileset_terrain, (int)(x - com.dev.mike.game.Component.sX), (int)(y - com.dev.mike.game.Component.sY),
                (int)(x+width - com.dev.mike.game.Component.sX), (int)(y+height - com.dev.mike.game.Component.sY),
                (Tile.Character[0] * Tile.tileSize)+(Tile.tileSize * animation), 
                 Tile.Character[1] * Tile.tileSize,
                (Tile.Character[0] * Tile.tileSize)+(Tile.tileSize* animation) + (int)width,
                 Tile.Character[1] * Tile.tileSize +(int)height, null);
        }else{
             g.drawImage(Tile.tileset_terrain, (int)(x - com.dev.mike.game.Component.sX), (int)(y - com.dev.mike.game.Component.sY),
                (int)(x+width - com.dev.mike.game.Component.sX), (int)(y+height - Component.sY),
                (Tile.Character[0] * Tile.tileSize)+(Tile.tileSize * animation) + (int)width, 
                 Tile.Character[1] * Tile.tileSize,
                (Tile.Character[0] * Tile.tileSize)+(Tile.tileSize* animation),
                 Tile.Character[1] * Tile.tileSize +(int)height, null);
    
        }
    }
    
}
