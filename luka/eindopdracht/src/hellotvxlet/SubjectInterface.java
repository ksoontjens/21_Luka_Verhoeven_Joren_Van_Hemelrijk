/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hellotvxlet;

/**
 *
 * @author student
 */
public interface SubjectInterface {

    void register(ObserverInterface ob, Vraag vraag);
    void unregister(ObserverInterface ob);
}
