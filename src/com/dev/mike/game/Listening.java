/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dev.mike.game;

import java.awt.event.*;

/**
 *
 * @author katya
 */
public class Listening implements KeyListener,MouseWheelListener, MouseListener,MouseMotionListener{

   
 

    @Override
    public void keyPressed(KeyEvent e) {
          int key = e.getKeyCode();
        
        
        switch(key){
        case KeyEvent.VK_D:           
            Component.isMoving = true;
            Component.dir = Component.character.movementSpeed ;
            break;            
        case KeyEvent.VK_A:
            Component.isMoving = true;
            Component.dir = -Component.character.movementSpeed ;
            break;           
        case KeyEvent.VK_SPACE:
            Component.isJumping = true;
            break;             
        case KeyEvent.VK_E:
           if(Inventory.isOpen) Inventory.isOpen = false;
           else Inventory.isOpen = true; 
           break;           
        case KeyEvent.VK_0:
           Inventory.selected = 0 ;
           break;
        case KeyEvent.VK_1:
           Inventory.selected = 1 ;
           break;
        case KeyEvent.VK_2:
           Inventory.selected = 2 ;
           break;
        case KeyEvent.VK_3:
           Inventory.selected = 3 ;
           break;
        case KeyEvent.VK_4:
           Inventory.selected = 4 ;
           break;
        case KeyEvent.VK_5:
           Inventory.selected = 5 ;
           break;
        case KeyEvent.VK_6:
           Inventory.selected = 6 ;
           break;
        case KeyEvent.VK_7:
           Inventory.selected = 7 ;
           break;
        case KeyEvent.VK_8:
           Inventory.selected = 8 ;
           break;
        case KeyEvent.VK_9:
           Inventory.selected = 9 ;
           break;
        }
  
    }

    @Override
    public void keyReleased(KeyEvent e) {   
        int key = e.getKeyCode();
        switch(key){
        case KeyEvent.VK_D:          
            if( Component.dir == Component.character.movementSpeed ){
                Component.isMoving = false;     
            }
            break; 
        case KeyEvent.VK_A:         
            if( Component.dir == -Component.character.movementSpeed ){
                Component.isMoving = false; 
            }
            break;
        case KeyEvent.VK_SPACE:
            Component.isJumping = false;
            break;  
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Component.mse.setLocation(e.getX(), e.getY());        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Component.mse.setLocation(e.getX(), e.getY());
    }

    @Override
    public void mouseClicked(MouseEvent e) {        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1 ){
            Component.isMouseLeft = true;
        }else if(e.getButton() == MouseEvent.BUTTON3){
            Component.isMouseRight = true;
        }
        
        Inventory.click(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1 ){
            Component.isMouseLeft = false;
          
        }else if(e.getButton() == MouseEvent.BUTTON3){
            Component.isMouseRight = false;
            
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
   }

    @Override
    public void mouseExited(MouseEvent e) {
  }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if(e.getWheelRotation()> 0){
            if(Inventory.selected < Tile.invLength -1){
                Inventory.selected +=1 ;
            }else{
                Inventory.selected = 0;
            }
           // System.out.println("down");
        }else if(e.getWheelRotation()< 0){
            if(Inventory.selected > 0){
                Inventory.selected -=1 ;
            }else{
                Inventory.selected = Tile.invLength-1;
            }
             //System.out.println("up");
        }
    }
    
    
}
