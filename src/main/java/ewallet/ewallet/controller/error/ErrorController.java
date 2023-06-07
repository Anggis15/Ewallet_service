package ewallet.ewallet.controller.error;

import ewallet.ewallet.constant.ErrorCode;
import ewallet.ewallet.entity.response.ErrorResponse;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.management.BadAttributeValueExpException;
import javax.persistence.EntityExistsException;
import java.util.ArrayList;
import java.util.List;

public class ErrorController {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleDataNotFoundException(NotFoundException notFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(ErrorCode.NFE.toString(), notFoundException.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllException(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(ErrorCode.EE.toString(), exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgument(MethodArgumentNotValidException methodArgumentNotValidException) {
        List<FieldError> fieldErrors = methodArgumentNotValidException.getBindingResult().getFieldErrors();
        List<String> errors = new ArrayList<>();

        for (FieldError err : fieldErrors) {
            errors.add(err.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(ErrorCode.VE.toString(), errors.toString()));
    }

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<ErrorResponse> handleEntityExistViolationException(EntityExistsException entityExistException) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ErrorResponse(ErrorCode.EEE.toString(), entityExistException.getMessage()));
    }

    @ExceptionHandler(BadAttributeValueExpException.class)
    public ResponseEntity<ErrorResponse> handleAttributeValueExpException(BadAttributeValueExpException badAttributeValueExpException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(ErrorCode.EEE.toString(), badAttributeValueExpException.getMessage()));
    }
}
