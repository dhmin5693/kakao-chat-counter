package presentation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class KakaoChatCountRequestV1 {
    private final String filePath;
    private final String startDate;
}
