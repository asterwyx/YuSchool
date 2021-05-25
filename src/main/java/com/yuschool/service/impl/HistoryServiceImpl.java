package com.yuschool.service.impl;

import com.yuschool.bean.History;
import com.yuschool.constants.enums.Access;
import com.yuschool.constants.enums.RetCode;
import com.yuschool.mapper.HistoryMapper;
import com.yuschool.service.HistoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {

    private final HistoryMapper historyMapper;

    public HistoryServiceImpl(HistoryMapper historyMapper) {
        this.historyMapper = historyMapper;
    }


    @Override
    public List<History> getAllHistory(int userId) {
        return this.historyMapper.selectHistoriesByUserId(userId);
    }

    @Override
    public List<History> getAllPubHistory(int userId) {
        return this.historyMapper.selectPubHistoriesByUserId(userId);
    }

    @Override
    public List<History> getAllPriHistory(int userId) {
        return this.historyMapper.selectPriHistoriesByUserId(userId);
    }

    @Override
    public RetCode addHistory(int userId, String detail, Access access) {
        return null;
    }

    @Override
    public boolean deleteHistory(int historyId) {
        return false;
    }
}
