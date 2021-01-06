package beyondeyesight.user.ui;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    @RequestMapping(value = "/")
    public String haha() {
        return "hello";
    }

}
