package com.awstraining.backend.business.notifyme.adapter;

import com.amazonaws.services.translate.AmazonTranslate;
import com.amazonaws.services.translate.model.TranslateTextRequest;
import com.amazonaws.services.translate.model.TranslateTextResult;
import com.awstraining.backend.business.notifyme.NotifyMeDO;
import com.awstraining.backend.business.notifyme.Translator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class TranslatorImpl implements Translator {

    private static final Logger LOGGER = LogManager.getLogger(TranslatorImpl.class);

    private final AmazonTranslate amazonTranslate;

    public TranslatorImpl(AmazonTranslate amazonTranslate) {
        this.amazonTranslate = amazonTranslate;
    }

    @Override
    public String translate(NotifyMeDO notifyMeDO) {
        TranslateTextRequest translateTextRequest = new TranslateTextRequest();
        translateTextRequest.setText(notifyMeDO.text());
        translateTextRequest.setTargetLanguageCode(notifyMeDO.targetLc());
        translateTextRequest.setSourceLanguageCode(notifyMeDO.sourceLc());
        TranslateTextResult translateTextResult = amazonTranslate.translateText(translateTextRequest);
        // TODO: lab2
        //  3. Log information about successful translated message.
        return translateTextResult.getTranslatedText();
    }
}
