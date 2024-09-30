package giuseppelongo.capstone.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorsResponseDTO {
    private String message;
    private LocalDateTime timestamp;
}
