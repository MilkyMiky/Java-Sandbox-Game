/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.mike.game;

import java.awt.*;
import static java.lang.Math.abs;
import java.util.*;


/**
 *
 * @author katya
 */
public class Level {
    public int treeLength; 
    public int treeSpace = 3;
    public boolean plantTree = false;
    public int treeFreak = 20;
    
    public int worldW = 100, worldH = 100;
    public Block[][] block = new Block[worldW][worldH];

    public Level(){
        for(int x = 0; x<block.length;x++){
            for(int y = 0; y < block[0].length; y++){
                block[x][y] = new Block(new Rectangle(x * Tile.tileSize,
                        y * Tile.tileSize, Tile.tileSize,Tile.tileSize), Tile.air);
            }
        } 
        generateLevel();
    }
          
    
    public void generateLeaves(int x,int y, int treeLength){
        block[x][y - (treeLength)].id = Tile.leaves;
        block[x][y - (treeLength+1)].id = Tile.leaves;
        for(int i = treeLength;i > treeLength/2;i --){
            if(block[x-1][y-i].id == Tile.air) block[x-1][y-i].id = Tile.leaves;
            if(block[x+1][y-i].id == Tile.air) block[x+1][y-i].id = Tile.leaves;
        }
        for(int i = treeLength-1;i > treeLength/2+1;i --){
            if(block[x-2][y-i].id == Tile.air) block[x-2][y-i].id = Tile.leaves;
            if(block[x+2][y-i].id == Tile.air) block[x+2][y-i].id = Tile.leaves;
        }
    }
    
    public void generateGrass(){
        for(int y = 0; y<block.length;y++){
            for(int x = 0; x<block[0].length;x++){               
                if(block[x][y].id == Tile.earth && block[x][y-1].id == Tile.air){
                    block[x][y].id = Tile.grass;  
                    if(new Random().nextInt(20) < 10)block[x][y-1].id = Tile.grass2;
                }
            }
        } 
    }
    public void generateTrees(){
        for(int x = treeSpace; x< block[0].length-treeSpace;x++) {
            for(int y = 0; y < block.length;y++){               
                try{
                    plantTree = true;  
                    for(int j = 0; j < block.length; j++){
                        if(block[x-1][j].id == Tile.wood  ||
                                block[x+1][j].id == Tile.wood){
                            plantTree = false;
                        }
                    }
                    if(plantTree){
                        if(block[x][y].id == Tile.earth && 
                                block[x][y-1].id == Tile.air){
                            if((new Random().nextInt(100) < treeFreak)){   
                                for(int i = 0; i < ((new Random().nextInt(7)+7)); i++){
                                        block[x][y-i].id = Tile.wood;
                                        treeLength=i;
                                }
                                generateLeaves(x,y,treeLength);
                            }
                        }  
                    }
              }catch(Exception e){   }
            }
        } 
    }
    
    public void generateGround(){
         for(int y = 0; y<block.length;y++){
            for(int x = 0; x<block[0].length;x++){
                if(y > worldH / 4){
                    if(new Random().nextInt(100) > 20){
                        try{
                            if(block[x-1][y-1].id == Tile.earth){
                                block[x][y].id = Tile.earth;
                            }
                        }catch(Exception e){}
                    }                                      
                    if(new Random().nextInt(100) > 30){
                        try{
                             if(block[x+1][y-1].id == Tile.earth){
                                block[x][y].id = Tile.earth;
                            }
                        }catch(Exception e){}                    
                    }                                    
                    try{
                        if(block[x][y-1].id == Tile.earth){
                                block[x][y].id = Tile.earth;
                        }
                    }catch(Exception e){}
                                       
                    if(new Random().nextInt(100) < 2){
                        block[x][y].id = Tile.earth;
                    }
              
                }
            }
        }
    }
    public void generateLevel(){
       
        generateGround(); 
        generateTrees();
        generateGrass();
        
        for(int y = 0; y < block.length;y++){
            for(int x = 0; x < block[0].length;x++){               
               if(x == 0 || y ==0 || x == block[0].length-1 ||
                       y == block[0].length-1){
                   block[x][y].id = Tile.mapBorders;
               }
            }
        } 
    }
    
    
    public void  building(int camX, int camY, int renW, int renH){
        if(Component.isMouseLeft || Component.isMouseRight ){
            for(int x = (camX / Tile.tileSize); x<(camX/Tile.tileSize)+renW;x++){
                for(int y = (camY / Tile.tileSize); y <(camY/Tile.tileSize)+renH; y++){
                    if(x >= 0 && y >= 0 && x < worldW && y < worldH) { 
                       if(block[x][y].contains(new Point((Component.mse.x  / Component.pixelSize) + (int)Component.sX,
                               (Component.mse.y / Component.pixelSize)+ (int)Component.sY ))){      
                            int sid[] = Inventory.invBar[Inventory.selected].id; 
                           
                            if(Component.isMouseLeft){
                                if(block[x][y].id != Tile.mapBorders){
                                   block[x][y].id = Tile.air; 
                                }
                                
                                if(block[x][y-1].id == Tile.grass2){
                                    block[x][y-1].id = Tile.air;
                                }
                                
                            } else if(Component.isMouseRight){
                                if(block[x][y].id == Tile.air){
                                    if(Tile.blockSet.contains(sid))
                                    {
                                        block[x][y].id = sid;

                                        if(block[x][y+1].id == Tile.grass){
                                            block[x][y+1].id = Tile.earth;
                                        }    
                                                 
                                    }
                                }
                                if(block[x][y].id == Tile.grass2){
                                    block[x][y].id = sid;
                                    block[x][y+1].id = Tile.earth;
                                }
                                
                            }
                           break;
                       }
                    }
                }
            }
        }
    }
    
    public void tick(int camX, int camY, int renW, int renH){
        if(!Inventory.isOpen){
            building( camX,  camY,  renW,  renH);
        }
    }

    public void render(Graphics g, int camX, int camY, int renW, int renH){
        for(int x = (camX / Tile.tileSize); x<(camX/Tile.tileSize)+renW;x++){
            for(int y = (camY / Tile.tileSize); y <(camY/Tile.tileSize)+renH; y++){                                    
                if(x >= 0 && y >= 0 && x < worldW && y < worldH) {                   
                    block[x][y].render(g);    
                }
                
               /*test 
               if(block[x][y].id != Tile.air){
                    if(block[x][y].contains(new Point((Component.mse.x  / Component.pixelSize) + (int)Component.sX,
                                  (Component.mse.y / Component.pixelSize)+ (int)Component.sY ))){
                        g.setColor(new Color(255,255,255, 60));
                        g.fillRect(block[x][y].x - camX,block[x][y].y - camY,block[x][y].width,block[x][y].height);
                      
              //  g.setColor(new Color(0, 0, 0, Sky.skyAlpha));
               //     g.fillRect(0, 0, Component.pixel.width, Component.pixel.height);
                    } 
                }  */   
            }
        }
    }
}
