package presentation.dto;

import domain.Ranking;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class KakaoChatCountResult {
    private final Ranking ranking;
}
