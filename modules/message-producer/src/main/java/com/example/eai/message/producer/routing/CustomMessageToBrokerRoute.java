/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.eai.message.producer.routing;

import java.util.HashMap;
import java.util.Map;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

/**
 *
 * @author Cooshal
 */
public class CustomMessageToBrokerRoute extends RouteBuilder {

    Long counter;
    
    public CustomMessageToBrokerRoute() {
        counter = 0L;
    }
    
    @Override
    public void configure() throws Exception {
        from("timer://superTimer?period=1000")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        Map<String, Object> in = new HashMap<>();
                        in.put("message", "Dummy Message - " + counter++);
                        exchange.getIn().setBody(in);
                    }
                })
                .to("eai-producer:queue:tst.tst.message");
    }

}
