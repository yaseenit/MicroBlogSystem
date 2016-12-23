/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.Properties;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author tk1
 */
public class ConnectionController {

    private Properties properties;
    private TopicConnection topicConnection;
    public TopicSession topicSession;
    public Topic topic;
    public TopicConnectionFactory topicConnectionFactory;
    private InitialContext ctx;
    private javax.jms.TopicPublisher publisher;
    private javax.jms.TopicSubscriber subscriber;
    private ConnectionController ConnectionController;

    public ConnectionController() throws NamingException, JMSException {
        this.properties = new Properties();
        this.properties.put("java.naming.factory.initial", ConnectionConstants.INITIAL_CONTEXT_FACTORY);
        this.properties.put("connectionfactory.QueueConnectionFactory", ConnectionConstants.PROVIDER_URL);
        this.properties.put("topic." + ConnectionConstants.TOPIC, ConnectionConstants.TOPIC);
        this.ctx = new InitialContext(properties);
        this.topicConnectionFactory = (TopicConnectionFactory) ctx.lookup("QueueConnectionFactory");
        this.topicConnection = topicConnectionFactory.createTopicConnection();
        this.topicSession = topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

        this.topic = (Topic) ctx.lookup(ConnectionConstants.TOPIC);

        this.topicConnection.start();
        System.out.println(this.topic.toString());
         
    }

    public ConnectionController(String topicName) throws NamingException, JMSException {
        this.properties = new Properties();
        this.properties.put("java.naming.factory.initial", ConnectionConstants.INITIAL_CONTEXT_FACTORY);
        this.properties.put("connectionfactory.QueueConnectionFactory", ConnectionConstants.PROVIDER_URL);
        this.properties.put("topic." + topicName, topicName);
        this.ctx = new InitialContext(properties);
        this.topicConnectionFactory = (TopicConnectionFactory) ctx.lookup("QueueConnectionFactory");
        this.topicConnection = topicConnectionFactory.createTopicConnection();
        this.topicSession = topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

        this.topic = (Topic) ctx.lookup(topicName);

        this.topicConnection.start();
    }

    public  javax.jms.TopicPublisher publisher() throws JMSException {
        this.publisher = this.topicSession.createPublisher(this.topic);
         return this.publisher ;
    }

    public  javax.jms.TopicSubscriber subscriber() throws JMSException {
        this.subscriber = this.topicSession.createSubscriber(this.topic);
         return   this.subscriber;
    }

   

}
