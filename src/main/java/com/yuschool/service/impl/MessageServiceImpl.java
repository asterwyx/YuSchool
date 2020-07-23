package com.yuschool.service.impl;

import com.yuschool.bean.Message;
import com.yuschool.bean.MessageRecord;
import com.yuschool.constants.enums.RetCode;
import com.yuschool.mapper.MessageMapper;
import com.yuschool.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.yuschool.constants.enums.RetCode.*;

@Service
public class MessageServiceImpl implements MessageService {

    public static final Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);
    @Autowired
    MessageMapper messageMapper;

    @Override
    public List<Message> getAllMessages(int userId) {
        return messageMapper.selectMsgsByReceiverId(userId);
    }

    @Override
    public RetCode sendMsg(int messageId, int receiverId) {
        RetCode rc = addMsgRecord(messageId, receiverId);
        if (rc != SUCCESS) {
            logger.error("发送单条消息失败");
        } else {
            messageMapper.addRefCnt(1, messageId);
        }
        return rc;
    }

    @Override
    public RetCode sendMsgs(int messageId, List<Integer> receivers) {
        int succeedNum = 0;
        for (int receiverId : receivers) {
            if (addMsgRecord(messageId, receiverId) == SUCCESS) {
                succeedNum++;
            }
        }
        messageMapper.addRefCnt(succeedNum, messageId);
        if (succeedNum != receivers.size()) {
            logger.error("消息未完全发送");
            return INCOMPLETE_OP;
        } else {
            return SUCCESS;
        }
    }

    @Override
    public RetCode addMsg(int senderId, String content) {
        Message msg = new Message();
        msg.setDetail(content);
        msg.setRefCnt(0);
        msg.setSenderId(senderId);
        int infNum = messageMapper.insertMsg(msg);
        if (infNum <= 0) {
            logger.error("发送者" + senderId + "插入消息失败");
            return FAIL_OP;
        } else {
            return SUCCESS;
        }
    }

    @Override
    public RetCode addMsgRecord(int messageId, int receiverId) {
        MessageRecord msgRecord = new MessageRecord();
        msgRecord.setMessageId(messageId);
        msgRecord.setRead(false);
        msgRecord.setReceiverId(receiverId);
        int infNum = messageMapper.insertMsgRecord(msgRecord);
        if (infNum <= 0) {
            logger.error("为接收者" + receiverId + "插入消息" + messageId + "记录失败");
            return FAIL_OP;
        } else {
            return SUCCESS;
        }
    }

    @Override
    public boolean deleteMsg(int msgId) {
        return messageMapper.deleteMsg(msgId) > 0;
    }

    @Override
    public RetCode readMsg(int msgId, int receiverId) {
        int infNum = messageMapper.setRecordReadByReceiver(msgId, receiverId);
        if (infNum <= 0) {
            logger.error("用户" + receiverId + "已读消息" + msgId + "失败");
            return FAIL_OP;
        } else {
            return SUCCESS;
        }
    }
}
