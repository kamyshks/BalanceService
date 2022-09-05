package kamyshks.controller;

import kamyshks.exceptions.ErrorCode;
import kamyshks.dto.ErrorDto;
import kamyshks.exceptions.SaveException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class ErrorController {

    private final Map<String, ErrorCode> errorsMap;
    private final Map<String, HttpStatus> statusesMap;

    public ErrorController() {
        this.errorsMap = new HashMap<>();
        this.statusesMap = new HashMap<>();

        this.statusesMap.put(SaveException.class.getName(), HttpStatus.METHOD_NOT_ALLOWED);
        this.statusesMap.put(EntityNotFoundException.class.getName(), HttpStatus.NOT_FOUND);
        this.statusesMap.put(javax.persistence.NoResultException.class.getName(), HttpStatus.BAD_REQUEST);
        this.statusesMap.put(NumberFormatException.class.getName(), HttpStatus.BAD_REQUEST);
        this.statusesMap.put(HttpMediaTypeNotSupportedException.class.getName(), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        this.statusesMap.put(HttpRequestMethodNotSupportedException.class.getName(), HttpStatus.METHOD_NOT_ALLOWED);

        this.errorsMap.put(NumberFormatException.class.getName(), ErrorCode.INCORRECT_DATA_FORMAT);

    }

    protected Object handleDefaultError(Throwable throwable, String errorName) {
        HttpStatus statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
        throwable.printStackTrace();

        if (this.statusesMap.containsKey(errorName)) statusCode = this.statusesMap.get(errorName);
        if (this.errorsMap.containsKey(errorName)) errorCode = this.errorsMap.get(errorName);
        return ResponseEntity
                .status(statusCode)
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        ErrorDto.builder()
                                .error(errorCode.name())
                                .message(throwable.getMessage())
                                .build()
                );
    }

    protected Object handleSaveException(Throwable throwable) {
        SaveException exception = (SaveException) throwable;

        ErrorCode errorCode = ErrorCode.CREATE_OR_UPDATE_BALANCE_ERROR;
        if (exception.getErrorCode() != null) errorCode = exception.getErrorCode();

        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        ErrorDto.builder()
                                .error(errorCode.name())
                                .message(throwable.getMessage())
                                .build()
                );
    }

    protected Object handleCauseException(Throwable throwable)
            throws IOException {
        Exception causedException = (Exception) throwable;
        if (causedException.getCause() != null) return handleException(causedException.getCause());
        else return handleDefaultError(throwable, throwable.getClass().getName());
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    protected Object handleException(Throwable throwable) throws IOException {
        String errorName = throwable.getClass().getName();
        switch (errorName) {
            case "kamyshks.exceptions.SaveException":
                return handleSaveException(throwable);
            default:
                return handleCauseException(throwable);
        }
    }
}
