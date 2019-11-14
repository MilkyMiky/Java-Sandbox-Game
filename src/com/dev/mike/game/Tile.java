/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.mike.game;

import java.awt.image.*;
import java.io.*;
import java.util.HashSet;
import javax.imageio.*;

/**
 *
 * @author katya
 */
public class Tile {
    public static int tileSize = 20;
    public static int invLength= 10;
    public static int invHeight = 4;
    
    public static int invCellSize = 26;
    public static int invCellSpace = 4;
    public static int invBorderSpace = 4;
    public static int invItemBorder = 5;
    
    public static int[] mapBorders = {-2,-2};
    public static int[] air = {-1,-1}; 
    public static int[] earth = {0, 0};
    public static int[] grass = {1, 0};
    public static int[] sand = {2, 0};
    public static int[] stone = {3, 0};
    public static int[] wood = {4, 0};
    public static int[] leaves = {5, 0};
    public static int[] glass = {6, 0};
    
    public static int[] grass2 = {0, 3};
    public static int[] stoneWall = {0,2};
    
 /* public static int[] earthWall = {0, 0};
    public static int[] grass = {1, 0};
    public static int[] sand = {2, 0};
    public static int[] stoneWall = {3, 0};
    public static int[] woodWall = {4, 0};
    public static int[] leaves = {5, 0};
    public static int[] glassWall = {6, 0};*/
    
    public static int[] Character = {0,8};
    public static int[] FirstMob = {0,6};
    public static BufferedImage tileset_terrain;
    public static BufferedImage tile_cell;
    public static BufferedImage tile_select;
    
   public static HashSet<int[]> blockSet;
   public static HashSet<int[]> backgroundSet; 
    public Tile() {
      try{
          Tile.tileset_terrain = ImageIO.read(new File("src/com/dev/mike/Textures/Tiles.png"));
          
          Tile.tile_cell = ImageIO.read(new File("src/com/dev/mike/Textures/cell.png"));
          
          Tile.tile_select = ImageIO.read(new File("src/com/dev/mike/Textures/cell3.png"));
   
        } catch(Exception e){   }
        blockSet = new HashSet<int[]>();
        blockSet.add(wood);
        blockSet.add(grass);
        blockSet.add(sand);
        blockSet.add(stone);
        blockSet.add(leaves);
        blockSet.add(earth);
        blockSet.add(glass);
        blockSet.add(stoneWall);
        
        backgroundSet = new HashSet<int[]>();
        backgroundSet.add(air);
        backgroundSet.add(grass2);
        backgroundSet.add(leaves);
        backgroundSet.add(wood);
        backgroundSet.add(stoneWall);
    }
    
}
