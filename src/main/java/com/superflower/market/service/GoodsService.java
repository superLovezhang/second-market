package com.superflower.market.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.superflower.market.entity.Goods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.superflower.market.entity.vo.GoodsVo;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zz
 * @since 2020-05-08
 */
public interface GoodsService extends IService<Goods> {

    IPage<Goods> searchByQuery(Map map, Integer page, Integer size);

    GoodsVo findGoodsInfo(String id);

    Integer getCurrentTimeGoodsNum(String date);

    IPage<Goods> findAll(Page<Goods> goodsPage, Map<String, String> map);
}
