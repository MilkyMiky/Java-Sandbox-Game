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
public class Sky {
    
 
    public int night = 1;
    public int day = 0;
    public int time = day;
    public int r1 = 70, g1 = 120 , b1 = 230;//day
    public int r2 = 15, g2 = 15 , b2 = 70;//day
    public int r = r1, g = g1 , b = b1;//day
    public int dayFrame = 0, dayTime = 10000;
    public int changeFrame = 0, changeTime = 4;
    public static int dayAlpha = 0, nightAlpha = 5, skyAlpha = dayAlpha;
    
    public Sky(){
        if(time == day){
            r = r1;
            g = g1;
            b = b1;
            
        } else if(time == night){
            r = r2;
            g = g2;
            b = b2;
            
        }
    }
    
    public void tick(){
        if(dayFrame >= dayTime){
           
            if(time == day){
                time = night;
            } else if(time == night){
                time = day;
            }
            dayFrame = 0;
            
        }else {
            dayFrame+=1;
         
        }
        
        
        if(changeFrame >= changeTime){
            if(time == day){
                     
                if(r < r1) {r += 1;}
                if(g < g1) {g += 1;}
                if(b < b1) {b += 1;}
            

            }else if(time == night){
                if(r > r2) {r -= 1;}
                if(g > g2) {g -= 1;}
                if(b > b2) {b -= 1;}
            }
            changeFrame = 0;
        } else{
            changeFrame ++;
        }
        
        
        if(time == night)
        {
            if(skyAlpha < nightAlpha)
            {
                skyAlpha++;
            }
        } else {
            if(skyAlpha > dayAlpha) {
            skyAlpha--;
            }
        }
    }
   
    public void render(Graphics gr){
        gr.setColor(new Color(r,g,b));
        gr.fillRect(0, 0, Component.pixel.width,  Component.pixel.height);
       
      
    }
}
