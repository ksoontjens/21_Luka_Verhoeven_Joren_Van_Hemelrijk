/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hellotvxlet;

import java.awt.Color;
import java.awt.Image;
import java.awt.MediaTracker;
import org.havi.ui.HIcon;
import org.havi.ui.HTextButton;
import org.havi.ui.HVisible;

/**
 *
 * @author student
 */
public class Sprite extends HIcon implements ObserverInterface {

    Color kleur = Color.GRAY;
    Image img;
    
    public Sprite(String bitmap_naam, int x, int y){
        
        
        this.setGraphicContent(img, HVisible.NORMAL_STATE);
        this.setBackground(kleur);
        this.repaint();
        
    }
    
    
    public void update(int tijd, Vraag vraag) {
        /*if(kleur == Color.RED) kleur = Color.GREEN; else kleur=Color.RED;
        this.setBackground(kleur);
        this.repaint();*/
    }

}
