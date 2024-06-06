package com.awstraining.backend.business.notifyme.controller;

import com.awstraining.backend.api.rest.v1.NotifyMeApi;
import com.awstraining.backend.api.rest.v1.model.NotifyMe;
import com.awstraining.backend.api.rest.v1.model.SentMessage;
import com.awstraining.backend.business.notifyme.NotifyMeDO;
import com.awstraining.backend.business.notifyme.NotifyMeService;
import com.awstraining.backend.business.notifyme.Translator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("notification/v1")
public class NotifyMeController implements NotifyMeApi {

    private final NotifyMeService notifyMeService;

    public NotifyMeController(NotifyMeService notifyMeService) {
        this.notifyMeService = notifyMeService;
    }

    @Override
    public ResponseEntity<SentMessage> notifyMe(NotifyMe notifyMe) {
        NotifyMeDO notifyMeDO = map(notifyMe);
        String s = notifyMeService.notifyMe(notifyMeDO);
        return ResponseEntity.ok(map(s));
    }

    private static SentMessage map(String message) {
        return new SentMessage().text(message);
    }

    private NotifyMeDO map(NotifyMe notifyMe) {
        return new NotifyMeDO(notifyMe.getText(), notifyMe.getSourceLc(), notifyMe.getTargetLc());
    }
}