package com.example.eai.message.consumer.tests;

import java.util.Dictionary;
import java.util.Hashtable;
import javax.jms.ConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.test.blueprint.CamelBlueprintHelper;
import org.osgi.framework.BundleContext;



public class Main {
    
    public static void main(String[] args) throws Exception{
        //create context and embedded broker
        BundleContext context = CamelBlueprintHelper.createBundleContext("eai-message-consumer", "OSGI-INF/blueprint/consumer-context.xml", false);
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

        connectionFactory.setUserName("system");
        connectionFactory.setPassword("manager");

        Dictionary<String, String> properties = new Hashtable<String, String>();
        properties.put("osgi.jndi.service.name", "jms/eai.consumer");
        context.registerService(ConnectionFactory.class, connectionFactory, properties);
    }

}