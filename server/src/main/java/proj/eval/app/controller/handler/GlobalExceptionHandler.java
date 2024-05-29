package proj.eval.app.controller.handler;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import proj.eval.app.util.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

  /*
   * Fallback for handling the validation errors and returning a map of field
   * names and error
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiResponse> handleValidationExceptions(
    MethodArgumentNotValidException ex
  ) {
    ApiResponse response = new ApiResponse();
    List<String> errors = new ArrayList<>();
    ex
      .getBindingResult()
      .getAllErrors()
      .forEach(error -> {
        String fieldName = ((FieldError) error).getField();
        String errorMessage = error.getDefaultMessage();
        errors.add(fieldName + ": " + errorMessage);
      });
    response.setErrors(errors);
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<ApiResponse> handleBadCredentialsException(
    BadCredentialsException ex
  ) {
    ApiResponse response = new ApiResponse();
    response.setErrors(List.of(ex.getMessage()));
    return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiResponse> handleGeneralException(Exception ex) {
    ApiResponse response = new ApiResponse();
    response.setErrors(
      List.of(
        "Something went wrong in the server.\nDetails:\n" +
        ex.getClass().getSimpleName() +
        " -> " +
        ex.getMessage()
      )
    );
    return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
