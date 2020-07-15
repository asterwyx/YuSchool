package com.yuschool.utils.comparator;

import com.yuschool.bean.Message;
import com.yuschool.constants.TypeConstants;

import java.util.Comparator;

public class MessageComparator implements Comparator<Message> {
    @Override
    public int compare(Message o1, Message o2) {
        if (o1.getIsRead() == o2.getIsRead()) {
            // o1和o2的状态相同，这时候就看时间
            return Long.compare(o1.getSentTime().getTime(), o2.getSentTime().getTime());
        } else if (o1.getIsRead() == TypeConstants.READ) {
            // o1已读，o2未读
            return TypeConstants.LESS;
        } else {
            // o1未读，o2已读
            return TypeConstants.GREATER;
        }
    }
}
