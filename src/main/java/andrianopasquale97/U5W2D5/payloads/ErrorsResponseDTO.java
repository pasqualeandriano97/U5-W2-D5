package andrianopasquale97.U5W2D5.payloads;

import java.time.LocalDateTime;

public record ErrorsResponseDTO(
        String message,
        LocalDateTime timeStamp
) {
}
