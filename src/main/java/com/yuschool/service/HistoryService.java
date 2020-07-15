package com.yuschool.service;

import com.yuschool.bean.History;
import com.yuschool.constants.enums.Access;
import com.yuschool.constants.enums.RetCode;

import java.util.List;

public interface HistoryService {

    List<History> getAllHistory(int userId);

    List<History> getAllPubHistory(int userId);

    List<History> getAllPriHistory(int userId);

    RetCode addHistory(int userId, String detail, Access access);

    boolean deleteHistory(int historyId);

}
