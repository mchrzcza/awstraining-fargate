package com.awstraining.backend.business.notifyme.adapter;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.awstraining.backend.business.notifyme.MessageSender;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MessageSnsAWSSender implements MessageSender {

    private static final Logger LOGGER = LogManager.getLogger(MessageSnsAWSSender.class);

    private final AmazonSNS amazonSNS;

    @Value("${notification.topicarn}")
    String topicArn;

    public MessageSnsAWSSender(AmazonSNS amazonSNS) {
        this.amazonSNS = amazonSNS;
    }

    @Override
    public void send(String text) {
        final PublishRequest publishRequest = new PublishRequest(topicArn, text);
        final PublishResult publishResult = amazonSNS.publish(publishRequest);
        LOGGER.info("Message was sent to topic: '{}' with id '{}'.",
                topicArn,
                publishResult.getMessageId());
        // TODO: lab1
        //  1. Create publishResult request.
        //  2. Publish request with sns.
        //  3. Log information about successful sent message to topic with topic arn and message id.
    }
}
