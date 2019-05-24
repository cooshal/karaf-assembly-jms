# Custom karaf distro and provisioning

## Introduction

This project demonstrates a simple usecase of building a custom karaf distribution, which will pre-install a broker bundle, and an mbean bundle.

The target of this project is to install both of these bundles (datasources/broker-datasource) and mbean module (modules/stats-mbean) as a bootFeature.

## Build

`> mvn clean install`

## Demo

If the bundle is deployed by copying it into the depoloy folder, it works completely fine. Thus, an mbean is exposed via Jolokia endpoint in the address: `http://localhost:8181/jolokia`. 

This mbean can be executed by passing this argument:

```json
{
    "type": "exec",
    "mbean": "com.example.eai:type=management,name=broker",
    "operation": "getQueueInfo(java.lang.String)",
    "arguments": ["q"]
}
```

And, a typical response we will get out of it is:

```json
{
    "request": {
        "mbean": "com.example.eai:name=broker,type=management",
        "arguments": [
            "q"
        ],
        "type": "exec",
        "operation": "getQueueInfo(java.lang.String)"
    },
    "value": "ActiveMQ",
    "timestamp": 1558730192,
    "status": 200
}
```

This MBean does nothing special but just to make sure that it is getting a non-null datasource, following code block is implemented in the mbean(`modules/stats-mbean/src/main/java/infrastructure/ActiveMQBrokerMBean.java`):

```java
@Override
public String getQueueInfo(String queue) {
    try {
        return this.brokerConnection.getMetaData().getJMSProviderName();
    } catch (JMSException ex) {
        Logger.getLogger(ActiveMQBrokerMBean.class.getName()).log(Level.SEVERE, null, ex);
    }
    return "No Connection";
}
```