/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hellotvxlet;

import java.awt.Color;
import java.awt.event.KeyEvent;
import org.bluray.ui.event.HRcEvent;
import org.dvb.event.EventManager;
import org.dvb.event.UserEvent;
import org.dvb.event.UserEventListener;
import org.dvb.event.UserEventRepository;
import org.havi.ui.HComponent;
import org.havi.ui.HScene;
import org.havi.ui.HSceneFactory;
import org.havi.ui.HVisible;

/**
 *
 * @author student
 */
public class Vraag extends HComponent{
    
    Subject su;
    String vraag = "";
    String antwoord1 = "";
    String antwoord2 = "";
    String antwoord3 = "";
    String antwoord4 = "";
    int juistAntwoord = 0;
    Sprite[] knoppen = new Sprite[4];
    Color zwart = Color.BLACK;
    Color wit = Color.WHITE;
    boolean canAnswer = false;
    boolean hasAnswered = false;
    HScene scene;

            
    public Vraag(String vrg, String antw1, String antw2, String antw3, String antw4, int juistAntw){
        vraag = vrg;
        antwoord1 = antw1;
        antwoord2 = antw2;
        antwoord3 = antw3;
        antwoord4 = antw4;
        juistAntwoord = juistAntw;
        canAnswer = true;
        this.setBounds(0,0,720,576);
        
    }
    
    public void drawQuestion(){
        scene = HSceneFactory.getInstance().getDefaultHScene();
        Observer ob1 = new Observer(); 
        Subject sub = new Subject();
        scene.add(ob1);
        Sprite knop1 = new Sprite(null,100,100);
        knop1.setBounds(100,100,400,100);
        knop1.setTextContent(antwoord1, HVisible.NORMAL_STATE);
        knop1.setBackgroundMode(HVisible.BACKGROUND_FILL);
        scene.add(knop1);
        
        Sprite knop2 = new Sprite(null,100,100);
        knop2.setBounds(100,200,400,100);
        knop2.setForeground(zwart);
        knop2.setTextContent(antwoord2, HVisible.NORMAL_STATE);
        knop2.setBackgroundMode(HVisible.BACKGROUND_FILL);
        scene.add(knop2);
        
        Sprite knop3 = new Sprite(null,100,100);
        knop3.setBounds(100,300,400,100);
        knop3.setForeground(zwart);
        knop3.setTextContent(antwoord3, HVisible.NORMAL_STATE);
        knop3.setBackgroundMode(HVisible.BACKGROUND_FILL);
        scene.add(knop3);
        
        Sprite knop4 = new Sprite(null,100,100);
        knop4.setBounds(100,400,400,100);
        knop4.setForeground(zwart);
        knop4.setTextContent(antwoord4, HVisible.NORMAL_STATE);
        knop4.setBackgroundMode(HVisible.BACKGROUND_FILL);
        scene.add(knop4);
        
        Sprite vraagBox = new Sprite(null,100,100);
        vraagBox.setBounds(100,0,400,100);
        vraagBox.setTextContent(vraag, HVisible.NORMAL_STATE);
        scene.add(vraagBox);
       
        knoppen[0] = knop1;
        knoppen[1] = knop2;
        knoppen[2] = knop3;
        knoppen[3] = knop4;
        
        scene.validate();
        scene.setVisible(true);
    }

    public void changeAnswer(int currentAnswer){
        //System.out.println(currentAnswer);
        for(int i = 0; i < knoppen.length; i++){
            knoppen[i].setForeground(zwart);
            knoppen[i].repaint();
        }
        knoppen[currentAnswer-1].setForeground(wit);
        knoppen[currentAnswer-1].repaint();
    }
    
    public boolean selectAnswer(int answer){
        if(answer == juistAntwoord){
            knoppen[answer-1].setBackground(Color.GREEN);
            knoppen[answer-1].repaint();
            return true;
        }
        else {
            knoppen[answer-1].setBackground(Color.RED);
            knoppen[answer-1].repaint();
            return false;
        }
    }
    
    public void endQuestion(){
        canAnswer = false;
        scene.dispose();
        
     
    }
    

}
