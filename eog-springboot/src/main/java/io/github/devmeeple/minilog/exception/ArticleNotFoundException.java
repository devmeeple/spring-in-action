package io.github.devmeeple.minilog.exception;

public class ArticleNotFoundException extends RuntimeException {

    public ArticleNotFoundException(String message) {
        super(message);
    }
}
