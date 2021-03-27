package presentation.controller;

import domain.KakaoChatCounter;
import presentation.dto.KakaoChatCountRequest;
import presentation.dto.KakaoChatCountResult;

public class KakaoChatCounterController {

    public KakaoChatCountResult countChat(KakaoChatCountRequest request) {

        var kakaoChatCounter =
            new KakaoChatCounter(request.getFilePath(),
                                 request.getStartDate());

        return new KakaoChatCountResult(kakaoChatCounter.extractRanking());
    }
}
