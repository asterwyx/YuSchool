package com.yuschool.service;

import com.yuschool.bean.Message;
import com.yuschool.constants.enums.RetCode;

import java.util.List;

public interface MessageService {

    List<Message> getAllMessages(int userId);

    RetCode sendMsg(int messageId, int receiverId);

    RetCode sendMsgs(int messageId, List<Integer> receivers);

    RetCode addMsg(int senderId, String content);

    RetCode addMsgRecord(int messageId, int receiverId);

    boolean deleteMsg(int msgId);

    RetCode readMsg(int msgId, int receiverId);

}
