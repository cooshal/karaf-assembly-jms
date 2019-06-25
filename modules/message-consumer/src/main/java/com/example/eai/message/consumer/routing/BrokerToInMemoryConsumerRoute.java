package com.example.eai.message.consumer.routing;

import org.apache.camel.builder.RouteBuilder;

/**
 *
 * @author Cooshal
 */
public class BrokerToInMemoryConsumerRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("eai-consumer:queue:tst.tst.message")
                .to("log:info");
    }

}
