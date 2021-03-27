package presentation.controller;

import domain.KakaoChatCounter;
import lombok.RequiredArgsConstructor;
import presentation.dto.KakaoChatCountRequest;
import presentation.dto.KakaoChatCountResult;
import rule.DateValidator;

@RequiredArgsConstructor
public class KakaoChatCounterController {

    private final DateValidator dateValidator;

    public KakaoChatCountResult countChat(KakaoChatCountRequest request) {

        dateValidator.validate(request.getStartDate());

        var kakaoChatCounter =
            new KakaoChatCounter(request.getFilePath(),
                                 request.getStartDate());

        return new KakaoChatCountResult(kakaoChatCounter.extractRanking());
    }
}
