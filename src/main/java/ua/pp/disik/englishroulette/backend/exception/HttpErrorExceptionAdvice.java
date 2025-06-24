package ua.pp.disik.englishroulette.backend.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class HttpErrorExceptionAdvice extends ResponseEntityExceptionHandler {
    private final ObjectMapper objectMapper;

    public HttpErrorExceptionAdvice(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @ExceptionHandler(HttpErrorException.class)
    @SneakyThrows
    public ResponseEntity<Object> handle(HttpErrorException exception) {
        String body = objectMapper.writeValueAsString(exception);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");

        HttpStatus status;
        try {
            status = HttpStatus.valueOf(exception.getCode());
        } catch (IllegalArgumentException e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(body, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex,
            Object body,
            HttpHeaders headers,
            HttpStatusCode statusCode,
            WebRequest request
    ) {
        return handle(new HttpErrorException(statusCode.value(), ex.getMessage()));
    }
}
