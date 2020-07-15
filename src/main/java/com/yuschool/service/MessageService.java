package com.yuschool.service;

import com.yuschool.bean.Message;
import com.yuschool.constants.enums.RetCode;

import java.util.List;

public interface MessageService {

    List<Message> getAllMessages(int userId);

    RetCode sendMsg(int senderId, int receiverId, String content);

    boolean deleteMsg(int msgId);

    RetCode readMsg(int msgId);

}
