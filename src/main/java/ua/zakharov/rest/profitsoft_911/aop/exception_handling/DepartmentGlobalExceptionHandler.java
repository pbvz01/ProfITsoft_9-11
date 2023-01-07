package ua.zakharov.rest.profitsoft_911.aop.exception_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ua.zakharov.rest.profitsoft_911.aop.exception_handling.exception.DepartmentNotFoundException;
import ua.zakharov.rest.profitsoft_911.aop.exception_handling.wrapper.DepartmentIncorrectData;


@ControllerAdvice
public class DepartmentGlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<DepartmentIncorrectData> handlerException(DepartmentNotFoundException exception) {
        DepartmentIncorrectData expData = new DepartmentIncorrectData ();
        expData.setInfo(exception.getMessage());
        return new ResponseEntity<>(expData, HttpStatus.NOT_FOUND);
    }
}
