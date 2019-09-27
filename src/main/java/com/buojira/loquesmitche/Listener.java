package com.buojira.loquesmitche;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import com.fluig.broker.domain.BrokerRequestHeader;
import com.fluig.broker.domain.ChannelVO;
import com.fluig.broker.exception.BrokerException;
import com.fluig.broker.rabbit.RabbitMQAbstractConsumer;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Envelope;

public class Listener extends RabbitMQAbstractConsumer<String> {

    public Listener(ChannelVO channel, String consumerTag) {
        super(channel, consumerTag);
    }

    @Override
    public void handleDelivery(String consumerTag,
            Envelope envelope, AMQP.BasicProperties properties, byte[] content) throws IOException {
        try {
            handleDelivery(null, getBody(content));
        } catch (Exception ex) {
            throw new IOException(ex);
        } finally {
            if (!isAutoAcknowledgeEnabled()) {
                acknowledge(envelope.getDeliveryTag(), false);
            }
        }
    }

    @Override
    protected String getBody(byte[] bytes) throws IOException {
        return new String(bytes, StandardCharsets.UTF_8);
    }

    @Override
    public void handleDelivery(BrokerRequestHeader brokerRequestHeader, String register) throws BrokerException {
        System.out.println("Eis o que chegou: ******************************");
        System.out.println(register);
    }

}
