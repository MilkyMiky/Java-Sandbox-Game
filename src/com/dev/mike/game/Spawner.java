/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.mike.game;

import java.util.Random;

/**
 *
 * @author katya
 */
public class Spawner implements Runnable{
public boolean isRunning = false;
public int mobX = 20 * Tile.tileSize, mobY = 17 * Tile.tileSize;  
public static int mobAmmount = 6;
    public Spawner(){
        isRunning = true;
        new Thread(this).start();
    }
    public void spawnMob(Mob mob){
        Component.mob.add(mob);
    }

    @Override
    public void run() {
        while(isRunning){
            if(Component.mob.toArray().length <= mobAmmount){
            spawnMob(new FirstMob(mobX, mobY, Tile.tileSize,Tile.tileSize*2));
            }
            try{
                Thread.sleep(new Random().nextInt(8000)+5000);
         }catch(Exception e){
                
            }
        }
            
    }
}
