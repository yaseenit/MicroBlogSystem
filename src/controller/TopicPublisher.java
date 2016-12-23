/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.logging.Level;
import java.util.logging.Logger;
 import javax.jms.JMSException;

import javax.jms.TextMessage;

 
import javax.naming.NamingException;

/**
 *
 * @author tk1
 */
public class TopicPublisher implements Runnable{

    public ConnectionController connection;
    public TextMessage textMessage;

    public TopicPublisher() throws NamingException, JMSException {
        connection = new ConnectionController();
    }
    
    public TopicPublisher(String topic) throws NamingException, JMSException {
        connection = new ConnectionController(topic);
    }

    public synchronized TopicPublisher publish(String message) throws JMSException, InterruptedException {
       
         textMessage = connection.topicSession.createTextMessage(message);
        return this;
    }

 

    public static void main(String[] args) throws NamingException, JMSException, InterruptedException {
        TopicPublisher publisher = new TopicPublisher();
        publisher.publish("Well Say Something").run();
     }

    @Override
    public void run() {
        try {
            connection.publisher().publish(textMessage);
        } catch (JMSException ex) {
            Logger.getLogger(TopicPublisher.class.getName()).log(Level.SEVERE, null, ex);
        }
      }

}
