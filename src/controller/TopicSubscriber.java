
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package controller;

 
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.naming.NamingException;

/**
 *
 * @author tk1
 */
public class TopicSubscriber implements Runnable {
 
    public ConnectionController connection;
    public  javax.jms.MessageListener messageListener ;
   
    public TopicSubscriber() throws NamingException, JMSException{
       this.connection = new ConnectionController();
    }
    
    public TopicSubscriber(String topic) throws NamingException, JMSException{
       this.connection = new ConnectionController(topic);
    }
 
    public TopicSubscriber subscribe( javax.jms.MessageListener messageListener ) throws JMSException{
         this.messageListener = messageListener;
        return this;
    }
 
 
    public static void main(String[] args) throws NamingException, JMSException {
        TopicSubscriber subscriber = new TopicSubscriber();
        subscriber.subscribe(new controller.MessageListner()).run();
    }

    @Override
    public void run() {
        try {
            this.connection.subscriber().setMessageListener(messageListener);
        } catch (JMSException ex) {
            Logger.getLogger(TopicSubscriber.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
 
}


 
