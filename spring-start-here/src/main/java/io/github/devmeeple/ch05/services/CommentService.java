package io.github.devmeeple.ch05.services;

import io.github.devmeeple.ch05.model.Comment;
import io.github.devmeeple.ch05.processors.CommentProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service("commentServiceCh05")
public class CommentService {

    @Autowired
    private ApplicationContext context;

    public void sendComment(Comment comment) {
        CommentProcessor processor = context.getBean(CommentProcessor.class);

        processor.setComment(comment);
        processor.processComment(comment);
        processor.validateComment(comment);

        comment = processor.getComment();
    }
}
