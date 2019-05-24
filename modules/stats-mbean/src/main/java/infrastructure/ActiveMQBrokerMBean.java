/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infrastructure;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import services.BrokerMBean;

/**
 *
 * @author Kushal
 */
public class ActiveMQBrokerMBean implements BrokerMBean {

    private final Connection brokerConnection;

    public ActiveMQBrokerMBean(ConnectionFactory connectionFactory) throws JMSException {
        this.brokerConnection = connectionFactory.createConnection();
    }
    
    @Override
    public String getQueueInfo(String queue) {
        try {
            return this.brokerConnection.getMetaData().getJMSProviderName();
        } catch (JMSException ex) {
            Logger.getLogger(ActiveMQBrokerMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "No Connection";
    }
    
}
