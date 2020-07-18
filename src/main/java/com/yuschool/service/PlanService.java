package com.yuschool.service;

import com.yuschool.constants.enums.RetCode;

public interface PlanService {

    RetCode addPlan(int courseId, String plan);

    RetCode stagePlan(int courseId, String plan);

    RetCode savePlan(int courseId);
}
