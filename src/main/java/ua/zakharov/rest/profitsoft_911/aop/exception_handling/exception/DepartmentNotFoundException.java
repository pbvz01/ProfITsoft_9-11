package ua.zakharov.rest.profitsoft_911.aop.exception_handling.exception;

public class DepartmentNotFoundException extends RuntimeException {
    private static final String message = "Department was not found";
    public DepartmentNotFoundException() {
        super(message);
    }
}
