package kamyshks.utils;

import kamyshks.exceptions.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Error {
    private ErrorCode errorCode;

    private String message;
}
