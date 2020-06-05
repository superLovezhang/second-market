package com.superflower.market.service;

import com.superflower.market.entity.Report;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zz
 * @since 2020-05-10
 */
public interface ReportService extends IService<Report> {

    void saveReport(String id, String userId, String content);
}
