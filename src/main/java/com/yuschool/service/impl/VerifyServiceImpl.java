package com.yuschool.service.impl;

import com.yuschool.service.VerifyService;
import org.springframework.stereotype.Service;

@Service
public class VerifyServiceImpl implements VerifyService {

    @Override
    public boolean checkInvitationCode(String invitationCode) {
        return false;
    }

}
