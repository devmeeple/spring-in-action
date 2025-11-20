package io.github.devmeeple.ch06.services;

import io.github.devmeeple.ch06.aspects.ToLog;
import io.github.devmeeple.ch06.model.Comment;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service("commentServiceCh06")
public class CommentService {

    private Logger logger = Logger.getLogger(CommentService.class.getName());

    @ToLog
    public String publishComment(Comment comment) {
        logger.info("Publishing comment:" + comment.getText());
        return "SUCCESS";
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
}
