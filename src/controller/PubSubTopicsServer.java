package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.JMSException;
import javax.naming.NamingException;

/**
 *
 * @author adam
 */
public class PubSubTopicsServer implements Runnable {

    private   TopicSubscriber subscribeServer;
    private   TopicPublisher publisherServer;
    private   Thread subsThread;
    private   Thread pubThread;
    private String message;
    private List<String> topics;

    public PubSubTopicsServer() throws NamingException, JMSException, InterruptedException {
        this.topics =  new ArrayList<>();
        this.topics.add("General");
        this.subscribeServer = new TopicSubscriber();
        this.subscribeServer.subscribe(new MessageListner());
        this.subsThread = new Thread(subscribeServer);
        this.subsThread.setDaemon(false);
        this.subsThread.start();
        
    }
    
    public boolean isUniqTopic(String newTopic){
        boolean isNewTopic = true;
        for(String topic : topics){
              if(topic == null ? newTopic == null : topic.equals(newTopic)){
                   isNewTopic = false;
              }
        }
        if(isNewTopic)
            topics.add(newTopic);
        
        
       return isNewTopic;
            
    }

    public PubSubTopicsServer publishInTopic(String message) throws JMSException, InterruptedException, NamingException {
           if(this.isUniqTopic(message)){
           this.publisherServer = new TopicPublisher();
           this.pubThread = new Thread(publisherServer);
           this.pubThread.setDaemon(false);
           this.message = message+"::message";
           this.pubThread.start();
           }

       
        return this;
    }
    
    public void closeServer() throws JMSException{
        this.publisherServer.connection.publisher().close();
        this.subscribeServer.connection.subscriber().close();
    }
   

    @Override
    public void run() {
        try {
  
            this.publisherServer.publish(message);
         } catch (JMSException ex) {
            Logger.getLogger(PubSubTopicsServer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(PubSubTopicsServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) throws Exception {
      PubSubTopicsServer pubsub = new PubSubTopicsServer();
      pubsub.publishInTopic("Server Started ::message").run();
//      pubsub.publishInTopic("HI").run();
    }
    

}
