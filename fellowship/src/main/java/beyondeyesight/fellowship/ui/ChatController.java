package beyondeyesight.fellowship.ui;

import beyondeyesight.fellowship.domain.model.chat.ChatMessage;
import beyondeyesight.fellowship.domain.model.chat.ChatRoom;
import beyondeyesight.fellowship.domain.service.ChatMessageService;
import beyondeyesight.fellowship.domain.service.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ChatController {
    private final ChatMessageService chatMessageService;
    private final ChatRoomService chatRoomService;

    // todo dto로 바꾸기
    @MessageMapping("/chat/message")
    public void send(ChatMessage chatMessage) {
        //todo: implement
        chatMessageService.send(chatMessage);
    }

    @RequestMapping("/chat/rooms")
    @ResponseBody
    public List<ChatRoom> getAllChatRooms() {
        return chatRoomService.getAll();
    }
}
