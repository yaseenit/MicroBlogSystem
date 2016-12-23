package controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.swing.DefaultListModel;
import javax.swing.JTextArea;

/**
 *
 * @author adam
 */
public class MessageListner implements MessageListener {

    public String topic = null;
    public String priviousMessage = null;
    public String priviousTopic = null;
    public DefaultListModel model;

    public MessageListner() {

    }

    public MessageListner(DefaultListModel model) {
        this.model = model;
    }

    @Override
    public void onMessage(Message msg) {

        try {

            if (((TextMessage) msg).getText().contains("message")) {
                if (priviousMessage != ((TextMessage) msg).getText().split("::")[1]) {
                    priviousMessage = ((TextMessage) msg).getText().split("::")[1];
                
                    System.out.println("_______________________________________");
                    System.out.println(((TextMessage) msg).getText().split("::")[0]+((TextMessage) msg).getText().split("::")[1]);
                    System.out.println(" ..................................... ");
                  
                }
            } 
            if (((TextMessage) msg).getText().contains("topic")) {
                
                if(priviousTopic != ((TextMessage) msg).getText().split("::")[0]) {
                    priviousTopic = ((TextMessage) msg).getText().split("::")[0];
                model.addElement(((TextMessage) msg).getText().split("::")[0]);
                System.out.println("_______________________________________");
                System.out.println("New topic::>"+((TextMessage) msg).getText().split("::")[0]);
                System.out.println(" ..................................... ");

              }   
            }

//              }
        } catch (JMSException ex) {
            Logger.getLogger(TopicSubscriber.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
