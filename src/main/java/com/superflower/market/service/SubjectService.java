package com.superflower.market.service;

import com.superflower.market.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.superflower.market.entity.vo.SubjectVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zz
 * @since 2020-05-08
 */
public interface SubjectService extends IService<Subject> {

    List<SubjectVo> findAll();

    List<Subject> findSubjectByPid(Integer pid);
}
