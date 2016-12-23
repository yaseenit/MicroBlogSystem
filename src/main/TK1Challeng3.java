/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javax.jms.JMSException;
import javax.naming.NamingException;
import javax.swing.UnsupportedLookAndFeelException;
import view.LoginGui;

/**
 *
 * @author tk1
 */
public class TK1Challeng3 {

    /**
     * @param args the command line arguments
     * @throws java.lang.ClassNotFoundException
     * @throws java.lang.InstantiationException
     * @throws java.lang.IllegalAccessException
     * @throws javax.swing.UnsupportedLookAndFeelException
     * @throws javax.naming.NamingException
     * @throws javax.jms.JMSException
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException, NamingException, JMSException, InterruptedException {
          for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }

           new LoginGui().setVisible(true);
              
          
//           pubsub.publishInTopic("Confirmation Message").run();
           
    }
    
}
