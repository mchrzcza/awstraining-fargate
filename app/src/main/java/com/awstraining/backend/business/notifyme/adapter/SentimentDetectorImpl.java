package com.awstraining.backend.business.notifyme.adapter;

import com.amazonaws.services.comprehend.AmazonComprehend;
import com.amazonaws.services.comprehend.model.DetectSentimentRequest;
import com.amazonaws.services.comprehend.model.DetectSentimentResult;
import com.awstraining.backend.business.notifyme.Sentiment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class SentimentDetectorImpl implements Sentiment {

    private static final Logger LOGGER = LogManager.getLogger(SentimentDetectorImpl.class);

    private final AmazonComprehend amazonComprehend;

    public SentimentDetectorImpl(AmazonComprehend amazonComprehend) {
        this.amazonComprehend = amazonComprehend;
    }

    @Override
    public String detectSentiment(String language, String text) {
        DetectSentimentRequest detectSentimentRequest = new DetectSentimentRequest();
        detectSentimentRequest.setLanguageCode(language);
        detectSentimentRequest.setText(text);
        DetectSentimentResult detectSentimentResult = amazonComprehend.detectSentiment(detectSentimentRequest);
        return detectSentimentResult.getSentiment();
    }
}
