# Custom karaf distro and provisioning

## Introduction

This project demonstrates a simple use case of building a custom karaf distribution, which will pre-install a broker bundle, and an mbean bundle.The broker bundle provides two connection factories using pax-jms (producer and consumer).

## Build

`> mvn clean install`