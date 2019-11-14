/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.mike.game;

/**
 *
 * @author katya
 */
public class DoubleRectangle {
    public double x, y, width, height;
    
    public DoubleRectangle(){
        setBounds(0,0,0,0);
    }
    
    public DoubleRectangle(double x, double y, double width, double height){
         setBounds(x,y,width,height);
    }
    
    public void setBounds(double x, double y, double width, double height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
