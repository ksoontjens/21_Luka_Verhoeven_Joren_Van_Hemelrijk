/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hellotvxlet;

import java.util.TimerTask;

/**
 *
 * @author student
 */
public class MijnTimer extends TimerTask {

    Subject su;
    int tijd = 6;

 
    public MijnTimer(Subject s){
        su = s;
    }
    public void run() {
        tijd--;
        if(su!=null) su.update_observers(tijd);
    }

}
