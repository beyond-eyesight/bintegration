package beyondeyesight.fellowship.domain.service;

import beyondeyesight.fellowship.domain.model.chat.ChatRoom;
import beyondeyesight.fellowship.domain.repository.ChatRoomRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;

    public List<ChatRoom> getAll() {
        return chatRoomRepository.findAll();
    }
}
