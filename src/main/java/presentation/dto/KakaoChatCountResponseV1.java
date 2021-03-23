package presentation.dto;

import domain.Ranking;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class KakaoChatCountResponseV1 {
    private final Ranking ranking;
}
