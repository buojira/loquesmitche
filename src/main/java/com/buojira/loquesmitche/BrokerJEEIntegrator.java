package com.buojira.loquesmitche;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.fluig.broker.BrokerClient;
import com.fluig.broker.controller.ChannelController;
import com.fluig.broker.domain.ChannelControllerDTO;
import com.fluig.broker.domain.ConnectionVO;
import com.fluig.broker.domain.builder.ChannelControllerDTOBuilder;
import com.fluig.broker.domain.builder.ConnectionVOBuilder;
import com.fluig.broker.exception.BrokerException;

@Service
public class BrokerJEEIntegrator {

    private BrokerProperties properties;

    public BrokerJEEIntegrator(BrokerProperties properties) {
        this.properties = properties;
        consume();
    }

    private void consume() {
        try {

            ConnectionVO vo = ConnectionVOBuilder.of()
                    .host(properties.getBrokerHost())
                    .virtualHost(properties.getVirtualHost())
                    .port(Integer.valueOf(properties.getBrokerPort()))
                    .userName(properties.getBrokerUserName())
                    .password(properties.getBrokerPassword())
                    .build();

            BrokerClient client = new BrokerClient(vo);
            System.out.println("client created");

            ChannelControllerDTO dto = ChannelControllerDTOBuilder.of()
                    .exchangeName("MYFLUIG.LOCKSMITH-FANOUT-REPLICATOR.V1")
                    .routingKey("LOQUESMI_TCHE-LOCKSMITH-REPLICATOR.V1")
                    .queueName("LOQUESMI_TCHE-LOCKSMITH-REPLICATOR.V1")
                    .build();

            System.out.println("DataConnection: " + dto);

            ChannelController controller = client.createFanoutChannel(dto);

            System.out.println("Fanout Channel Opened");

            controller.addListener(new Listener(controller.getChannel(), "some-tag"));
            System.out.println("I am listening ... ");

        } catch (BrokerException e) {
            e.printStackTrace();
        }
    }
}
