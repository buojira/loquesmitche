package com.buojira.loquesmitche;

import com.fluig.broker.BrokerClient;
import com.fluig.broker.controller.ChannelController;
import com.fluig.broker.domain.ChannelControllerDTO;
import com.fluig.broker.domain.builder.ChannelControllerDTOBuilder;
import com.fluig.broker.domain.builder.ConnectionVOBuilder;
import com.fluig.broker.exception.BrokerException;

public class BrokerJEEClient {

    public BrokerJEEClient() {
    }

    public void consume() {
        try {

            BrokerClient client = new BrokerClient(ConnectionVOBuilder.of().buildDefault());
            ChannelControllerDTO dto = ChannelControllerDTOBuilder.of()
                    .exchangeName("MYFLUIG.LOCKSMITH-FANOUT-REPLICATOR.V1")
                    .routingKey("LOQUESMI_TCHE-LOCKSMITH-REPLICATOR.V1")
                    .queueName("LOQUESMI_TCHE-LOCKSMITH-REPLICATOR.V1")
                    .build();

            ChannelController controller = client.createFanoutChannel(dto);
            controller.addListener(new Listener(controller.getChannel(), "some-tag"));

        } catch (BrokerException e) {
            e.printStackTrace();
        }
    }
}
