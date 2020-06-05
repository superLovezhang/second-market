package com.superflower.market.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.superflower.market.entity.Subject;
import com.superflower.market.entity.vo.SubjectVo;
import com.superflower.market.mapper.SubjectMapper;
import com.superflower.market.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zz
 * @since 2020-05-08
 */
@Service
@Transactional
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Resource
    private SubjectMapper subjectMapper;

    @Override
    public List<SubjectVo> findAll() {
        List<Subject> list = list(null);
        ArrayList<SubjectVo> subjectVos = new ArrayList<>(30);
        for (Subject subject : list) {
            if (subject.getPid() == 0) {
                SubjectVo subjectVo = new SubjectVo();
                BeanUtils.copyProperties(subject, subjectVo);
                subjectVo.setChildren(new ArrayList<SubjectVo>());
                subjectVos.add(subjectVo);
            }
        }

        for (SubjectVo subjectVo : subjectVos) {
            for (Subject subject : list) {
                if (subject.getPid() == subjectVo.getId()) {
                    SubjectVo subjectVo1 = new SubjectVo();
                    BeanUtils.copyProperties(subject, subjectVo1);
                    List<SubjectVo> children = subjectVo.getChildren();
                    children.add(subjectVo1);
                }
            }
        }

        return subjectVos;
    }

    @Override
    public List<Subject> findSubjectByPid(Integer pid) {
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("pid", pid);
        return list(wrapper);
    }
}
