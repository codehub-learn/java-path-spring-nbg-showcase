package gr.codelearn.spring.showcase.lab.transfer;

public record ApiError(Integer status, String message, String path) {
}
