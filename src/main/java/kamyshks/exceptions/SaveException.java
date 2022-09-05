package kamyshks.exceptions;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class SaveException extends Exception {

    private ErrorCode errorCode;

    public SaveException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

}
