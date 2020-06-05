package com.superflower.market.service.impl;

import com.superflower.market.entity.Report;
import com.superflower.market.mapper.ReportMapper;
import com.superflower.market.service.ReportService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zz
 * @since 2020-05-10
 */
@Service
public class ReportServiceImpl extends ServiceImpl<ReportMapper, Report> implements ReportService {

    @Resource
    private ReportMapper reportMapper;
//
//    @Resource
//    private UserMapper userMapper;

    @Override
    public void saveReport(String id, String userId, String content) {
        Report report = new Report();
        report.setCreateTime(new Date());
        report.setUserId(userId);
        report.setGoodsId(id);
        report.setReportContent(content);

        save(report);
    }
}
