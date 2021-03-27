package com.example.demo.mapper;

import com.example.demo.model.ChatMessage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ChatMessageMapper {
    @Select("SELECT * FROM MESSAGES")
    List<ChatMessage> getAllMessages();

    @Insert("INSERT INTO MESSAGES (username, messageText) " +
            "VALUES(#{username}, #{messageText})")
    @Options(useGeneratedKeys = true, keyProperty = "messageId")
    int insert(ChatMessage message);
}
