package com.yuschool.mapper;

import com.yuschool.bean.Message;
import com.yuschool.bean.MessageRecord;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MessageMapper {
    Message selectMsg(int id);
    Message selectMsgBySenderId(int senderId);
    MessageRecord selectRecord(int id);
    MessageRecord selectRecordByMsgId(int msgId);
    List<Message> selectMsgs(List<Integer> ids);
    List<Message> selectMsgsByReceiverId(int receiverId);
    int insertMsg(Message msg);
    int updateMsg(Message msg);
    int addRefCnt(int step, int id);
    int delRefCnt(int step, int id);
    int deleteMsg(int id);
    int insertMsgRecord(MessageRecord msgRecord);
    int updateMsgRecord(MessageRecord msgRecord);
    int setRecordRead(int id);
    int setRecordReadByReceiver(int msgId, int receiverId);
    int setRecordUnread(int id);
    int setRecordUnreadByReceiver(int msgId, int receiverId);
    int deleteMsgRecord(int id);
}
