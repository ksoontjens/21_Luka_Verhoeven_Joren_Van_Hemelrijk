/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hellotvxlet;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.MediaTracker;
import org.dvb.ui.DVBColor;
import org.havi.ui.HComponent;
import org.havi.ui.HScene;
import org.havi.ui.HSceneFactory;
import org.havi.ui.HVisible;

/**
 *
 * @author student
 */
public class Observer extends HComponent implements ObserverInterface {
    
    Sprite timer;
    HScene scene;
    
    public Observer(){
        
    }
    
    public void paint(int tijd){     
        timer.setTextContent(Integer.toString(tijd), HVisible.NORMAL_STATE);
        //System.out.println("tijd " + tijd);
        timer.repaint();       
        
    }
    
    public void showTimer(){
        this.setBounds(0,0,200,200);
        scene = HSceneFactory.getInstance().getDefaultHScene();
        timer = new Sprite(null,100,100);
        timer.setBounds(0,0,100,100);
        scene.add(timer);
    }
    
    
    public void update(int tijd, Vraag vraag) {
       
        
        
       if(tijd == 5){
            showTimer();
        } 
        
       if(tijd == 0){
            vraag.canAnswer = false; 
            showInfo();
        }
        
        if(tijd >= 0){
            paint(tijd);
        }
        
        
    }
    
    public void showInfo(){
        System.out.println("test");
        Sprite info = new Sprite(null,100,100);
        info.setBounds(100,505,400,65);
        info.setTextContent("Klik op spatie om verder te gaan.", HVisible.NORMAL_STATE);
        scene.add(info);
        scene.repaint();
    }
    
    

}
