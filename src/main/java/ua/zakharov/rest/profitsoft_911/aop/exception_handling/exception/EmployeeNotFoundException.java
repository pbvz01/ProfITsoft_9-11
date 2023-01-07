package ua.zakharov.rest.profitsoft_911.aop.exception_handling.exception;

public class EmployeeNotFoundException extends RuntimeException {
    private static final String message = "Employee was not found";
    public EmployeeNotFoundException() {
        super(message);
    }
}
