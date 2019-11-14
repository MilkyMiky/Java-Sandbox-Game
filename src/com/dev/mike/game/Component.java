/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dev.mike.game;
import java.applet.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 *
 *  @author katya
 */

    public class Component extends Applet implements Runnable{
        static ImageIcon icon = new ImageIcon("src/com/dev/mike/Textures/icon2.png");
        private static final long serialVersionID = 1L;
        
        public static int moveFromBorder = 0;
        public static double sX = moveFromBorder, sY = moveFromBorder;
        public static double dir = 0;
        
        public static int pixelSize = 1;
        public static Dimension size = new Dimension(700, 560);
        public static Dimension pixel = new Dimension(size.width / pixelSize, size.height / pixelSize);
       
        public static Point mse = new Point(0,0);
        
        public static String name = "Terraria by Mike";

        public static boolean isMoving = false;
        public static boolean isRunning = false;
        public static boolean isJumping = false;
        public static boolean isMouseLeft = false;
        public static boolean isMouseRight = false;
        
        private Image screen;
        public static Level level;  
        public static Character character;
        public static Inventory inventory;
        public static Sky sky;
        
        public static Spawner spawner;
        public static ArrayList <Mob> mob = new ArrayList <Mob>();
        
    
    public Component(){
        setPreferredSize(size);
        addKeyListener(new Listening());
        addMouseListener(new Listening());
        addMouseMotionListener(new Listening());
        addMouseWheelListener(new Listening());
    }
    
    @Override
    public void start(){
        
        new Tile();
        level = new Level();
        character = new Character(Tile.tileSize, Tile.tileSize*2);
        inventory = new Inventory();
        sky = new Sky();
        spawner = new Spawner();
        isRunning = true;
        new Thread(this).start();
    }
    
    @Override
    public void stop(){
        isRunning = false;
    }
    
    public static void main(String args[]){
        Component  component = new Component();
        
        JFrame frame = new JFrame();
        frame.add(component);
        frame.pack();
        frame.setTitle(name);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setIconImage(icon.getImage());
        
        component.start();
    }
    
    public void tick(){
        
        character.tick();
        level.tick((int)sX,  (int)sY,(pixel.width/ Tile.tileSize)+2,
                (pixel.height/ Tile.tileSize) +2  );
        sky.tick();
        
        for(int i = 0 ; i < mob.toArray().length; i ++){
            mob.get(i).tick();
        }
    }
    
    public void render(){
        Graphics g = screen.getGraphics();
        sky.render(g);
     
        
        level.render(g,(int)sX , (int)sY, (pixel.width/ Tile.tileSize)+2,
                (pixel.height/ Tile.tileSize) +2);
        character.render(g);
        
        for(int i = 0 ; i < mob.toArray().length; i ++){
            mob.get(i).render(g);
        }
        
        inventory.render(g);
        
        g = getGraphics();
        g.drawImage(screen, 0, 0, size.width, size.height,0, 0, pixel.width, pixel.height,null);
        g.dispose();
    }
    
    @Override
    public void run(){
        screen = createVolatileImage(pixel.width, pixel.height);
        while(isRunning){
          tick();
          render();
          try {
              Thread.sleep(5);   
          } catch(Exception e) {
              
          }            
        }
    }
}
