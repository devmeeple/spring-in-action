package io.github.devmeeple.bootcamp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@Controller
public class ProductController {

    public ProductController() {
        System.out.println("ProductController Bean 생성 - Spring IoC 컨테이너 동작 확인");
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getProduct() {
        return "NoteBook";
    }
}
