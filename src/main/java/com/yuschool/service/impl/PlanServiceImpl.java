package com.yuschool.service.impl;

import com.yuschool.bean.Plan;
import com.yuschool.constants.enums.RetCode;
import com.yuschool.mapper.PlanMapper;
import com.yuschool.service.PlanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static com.yuschool.constants.enums.RetCode.*;

@Service
public class PlanServiceImpl implements PlanService {

    public static final Logger logger = LoggerFactory.getLogger(PlanServiceImpl.class);
    private final PlanMapper planMapper;

    public PlanServiceImpl(PlanMapper planMapper) {
        this.planMapper = planMapper;
    }

    @Override
    public RetCode addPlan(int courseId, String plan) {
        Plan p = new Plan();
        p.setCourseId(courseId);
        p.setPlan(plan);
        int infNum = planMapper.insert(p);
        if (infNum <= 0) {
            logger.error("插入学习计划失败");
            return FAIL_OP;
        } else {
            return SUCCESS;
        }
    }

    @Override
    public RetCode stagePlan(int courseId, String plan) {
        String old = planMapper.selectStage(courseId);
        if (old == null) {
            int infNum = planMapper.addStage(courseId, plan);
            if (infNum <= 0) {
                logger.error("插入学习计划暂存记录失败");
                return FAIL_OP;
            } else {
                return SUCCESS;
            }
        } else {
            int infNum = planMapper.updateStage(courseId, plan);
            if (infNum <= 0) {
                logger.error("更新学习计划暂存记录失败");
                return FAIL_OP;
            } else {
                return SUCCESS;
            }
        }
    }

    @Override
    public Plan getPlanByCourseId(int courseId) {
        return planMapper.selectByCourseId(courseId);
    }

    @Override
    public RetCode savePlan(int courseId) {
        String plan = planMapper.selectStage(courseId);
        Plan p = new Plan();
        p.setCourseId(courseId);
        p.setPlan(plan);
        int infNum = planMapper.update(p);
        if (infNum <= 0) {
            logger.error("更新学习计划失败");
            return FAIL_OP;
        } else {
            return SUCCESS;
        }
    }
}
