package hellotvxlet;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Timer;
import javax.tv.xlet.Xlet;
import javax.tv.xlet.XletContext;
import javax.tv.xlet.XletStateChangeException;
import org.bluray.ui.event.HRcEvent;
import org.dvb.event.EventManager;
import org.dvb.event.UserEvent;
import org.dvb.event.UserEventListener;
import org.dvb.event.UserEventRepository;
import org.havi.ui.HScene;
import org.havi.ui.HSceneFactory;
import org.havi.ui.HVisible;




public class HelloTVXlet implements Xlet, UserEventListener {

    Vraag vraag1 =  new Vraag("Hoeveel is 150/5", "50", "30", "20", "25", 2);
    Vraag vraag2 =  new Vraag("Hoeveel is 3 * 16 ", "38", "42", "48", "37", 3);
    Vraag vraag3 =  new Vraag("Hoeveel is 100 - 151", "3", "-51", "-49", "51", 2);
    Vraag vraag4 =  new Vraag("Wat is de vierkantswortel van 121", "11", "12", "13", "99", 1);
    Vraag vraag5 =  new Vraag("Hoeveel is 5 ^ 42", "245245", "72100444", "28771", "I don't have a f*cking clue", 4);
    
    Vraag[] vragenlijst = new Vraag[5];
    
    HScene scene;

    int antwoordNr = 1;
    int currentQuestion = 0;
    Color zwart = Color.BLACK;
    Color wit = Color.WHITE;
    int score = 0;
    
    Observer ob1;
    Subject sub;
    MijnTimer mtt;
            
    public void initXlet(XletContext arg0) throws XletStateChangeException {
       
        vragenlijst[0] = vraag1;
        vragenlijst[1] = vraag2;
        vragenlijst[2] = vraag3;
        vragenlijst[3] = vraag4;
        vragenlijst[4] = vraag5;
        
        ob1 = new Observer();       
        sub = new Subject();
        sub.register(ob1, vraag1);
        vraag1.drawQuestion();
        mtt = new MijnTimer(sub);
        Timer tim = new Timer();
        tim.scheduleAtFixedRate(mtt, 0, 1000);
        
         
      

    }
    
     public void startXlet() throws XletStateChangeException {
        UserEventRepository mijnrep = new UserEventRepository("naam");
        mijnrep.addAllArrowKeys();
        mijnrep.addKey(HRcEvent.VK_ENTER);
        mijnrep.addKey(HRcEvent.VK_SPACE);
        EventManager mijnManager = EventManager.getInstance();
        mijnManager.addUserEventListener((UserEventListener) this,mijnrep);
        
        
    }
     
     public void userEventReceived(UserEvent e){        
        if(e.getType() == KeyEvent.KEY_PRESSED && !vragenlijst[currentQuestion].hasAnswered && vragenlijst[currentQuestion].canAnswer){
            if(e.getCode() == HRcEvent.VK_DOWN){
                if(antwoordNr >= 4){
                    antwoordNr = 1;
                }
                else{
                    antwoordNr++;                    
                }
                vragenlijst[currentQuestion].changeAnswer(antwoordNr);
   
            }
            else if(e.getCode() == HRcEvent.VK_UP){
                if(antwoordNr <= 1){
                    antwoordNr = 4;
                }
                else{
                    antwoordNr--;                
                }
                vragenlijst[currentQuestion].changeAnswer(antwoordNr);
  
            }
            else if(e.getCode() == HRcEvent.VK_ENTER){
                if(vragenlijst[currentQuestion].canAnswer){                    
                    if(vragenlijst[currentQuestion].selectAnswer(antwoordNr)){
                        score++;
                    };
                    vragenlijst[currentQuestion].hasAnswered = true;
                }               
                
            }
        }
        if(e.getType() == KeyEvent.KEY_PRESSED && !vragenlijst[currentQuestion].canAnswer){
            if(e.getCode() == HRcEvent.VK_SPACE && currentQuestion < vragenlijst.length-1){
                vragenlijst[currentQuestion].endQuestion();
                sub.unregister(ob1);
                currentQuestion++;
                antwoordNr = 1;
                mtt.tijd = 6;
                sub.register(ob1, vragenlijst[currentQuestion]);           
                vragenlijst[currentQuestion].drawQuestion();
            }
            else if(e.getCode() == HRcEvent.VK_SPACE){
                vragenlijst[currentQuestion].endQuestion();
                sub.unregister(ob1);
                
                
                scene = HSceneFactory.getInstance().getDefaultHScene();
                Sprite endtext = new Sprite(null,100,100);
                endtext.setBounds(100,250,400,100);
                endtext.setTextContent("Uw score is: " + score + "/" + vragenlijst.length, HVisible.NORMAL_STATE);
                endtext.setBackgroundMode(HVisible.BACKGROUND_FILL);
                scene.add(endtext);
                scene.validate();
                scene.setVisible(true);
               
            }
        }
    }

    public void pauseXlet() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void destroyXlet(boolean arg0) throws XletStateChangeException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

  
}

