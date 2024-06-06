package com.awstraining.backend.business.notifyme;

import org.springframework.stereotype.Service;

@Service
public class NotifyMeService {

    private final MessageSender messageSender;
    private final Translator translator;
    private final Sentiment sentimentDetector;

    public NotifyMeService(MessageSender messageSender, Translator translator, Sentiment sentimentDetector) {
        this.messageSender = messageSender;
        this.translator = translator;
        this.sentimentDetector = sentimentDetector;
    }

    public String notifyMe(NotifyMeDO notifyMe) {
        String translatedText = translator.translate(notifyMe);
        messageSender.send(translatedText);
//        sentimentDetector.detectSentiment()
        // TODO: lab3
        //  1. Detect sentiment of translated message.
        //  2. Change sending of text to "setiment: translated text" and return it.
        return translatedText;
    }

}
