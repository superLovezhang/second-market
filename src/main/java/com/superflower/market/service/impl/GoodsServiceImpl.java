package com.superflower.market.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.superflower.market.entity.Goods;
import com.superflower.market.entity.Report;
import com.superflower.market.entity.Subject;
import com.superflower.market.entity.Want;
import com.superflower.market.entity.vo.GoodsVo;
import com.superflower.market.mapper.GoodsMapper;
import com.superflower.market.mapper.ReportMapper;
import com.superflower.market.mapper.SubjectMapper;
import com.superflower.market.mapper.WantMapper;
import com.superflower.market.service.GoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Resource
    private SubjectMapper subjectMapper;

    @Resource
    private GoodsMapper goodsMapper;

    @Resource
    private WantMapper wantMapper;

    @Resource
    private ReportMapper reportMapper;

    public IPage<Goods> searchByQuery(Map map, Integer page, Integer size) {
        String keyword = (String) map.get("keyword");
        String subjectId =  (String) map.get("subjectId");

        QueryWrapper<Goods> wrapper = new QueryWrapper<>();

        if (StringUtils.isNotEmpty(keyword)) {
            wrapper.like("name", keyword);
        }
        if (!subjectId.equals("0")) {
            Subject subject = subjectMapper.selectById(subjectId);
            if (subject.getPid() == 0) {
                wrapper.eq("parent_subject", subjectId);
            } else {
                wrapper.eq("subject", subjectId);
            }
        }

        wrapper.eq("is_delete", 0);
        wrapper.eq("status", 0);
        wrapper.eq("sold_status", 0);
        wrapper.orderByDesc("recommend");
        wrapper.orderByDesc("update_time");

        Page<Goods> Ipage = new Page<Goods>(page, size);
        return goodsMapper.selectPage(Ipage, wrapper);
    }

    @Override
    public GoodsVo findGoodsInfo(String id) {
        QueryWrapper<Goods> goodsQuery = new QueryWrapper<>();
        QueryWrapper<Want> wantQuery = new QueryWrapper<>();
        QueryWrapper<Report> reportQuery = new QueryWrapper<>();
        wantQuery.eq("user_id", id);
        wantQuery.eq("is_delete", 0);
        reportQuery.eq("user_id", id);
        goodsQuery.eq("user_id", id);

        List<Goods> goodsList = baseMapper.selectList(goodsQuery);
        List<Want> wantList = wantMapper.selectList(wantQuery);
        List<Report> reportList = reportMapper.selectList(reportQuery);
        ArrayList<Goods> reportsList = new ArrayList<>();

        for (Report report : reportList) {
            String goodsId = report.getGoodsId();
            Goods goods = baseMapper.selectById(goodsId);
            if (goods != null) reportsList.add(goods);

        }

        GoodsVo goodsVo = new GoodsVo();
        goodsVo.setPublishGoodsList(goodsList);
        goodsVo.setWantGoodsList(wantList);
        goodsVo.setReportGoodsList(reportsList);

        return goodsVo;
    }

    @Override
    public Integer getCurrentTimeGoodsNum(String date) {
        return goodsMapper.findGoodsNumByDate(date);
    }

    @Override
    public IPage<Goods> findAll(Page<Goods> goodsPage, Map<String, String> map) {
        if (map == null) {
            IPage<Goods> goodsIPage = baseMapper.selectPage(goodsPage, null);
            return goodsIPage;
        }
        String keyword = (String) map.get("keyword");
        String subjectId =  (String) map.get("subjectId");

        QueryWrapper<Goods> wrapper = new QueryWrapper<>();

        if (StringUtils.isNotEmpty(keyword)) {
            wrapper.like("name", keyword);
        }
        if (!subjectId.equals("0")) {
            Subject subject = subjectMapper.selectById(subjectId);
            if (subject.getPid() == 0) {
                wrapper.eq("parent_subject", subjectId);
            } else {
                wrapper.eq("subject", subjectId);
            }
        }

        wrapper.orderByDesc("recommend");
        wrapper.orderByDesc("update_time");

        return goodsMapper.selectPage(goodsPage, wrapper);
    }
}
