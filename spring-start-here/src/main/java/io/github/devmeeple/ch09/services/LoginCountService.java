package io.github.devmeeple.ch09.services;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

@ApplicationScope
@Service("loginCountServiceCh09")
public class LoginCountService {

    private int count;

    public void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}
