package ua.zakharov.rest.profitsoft_911.aop.exception_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ua.zakharov.rest.profitsoft_911.aop.exception_handling.exception.EmployeeNotFoundException;
import ua.zakharov.rest.profitsoft_911.aop.exception_handling.wrapper.EmployeeIncorrectData;

@ControllerAdvice
public class EmployeeGlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<EmployeeIncorrectData> handlerException(EmployeeNotFoundException exception) {
        EmployeeIncorrectData expData = new EmployeeIncorrectData();
        expData.setInfo(exception.getMessage());
        return new ResponseEntity<>(expData, HttpStatus.NOT_FOUND);
    }
}
