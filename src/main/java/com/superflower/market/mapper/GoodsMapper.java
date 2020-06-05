package com.superflower.market.mapper;

import com.superflower.market.entity.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zz
 * @since 2020-05-08
 */
public interface GoodsMapper extends BaseMapper<Goods> {

    @Select("select count(*) from goods where Date(update_time) = #{date} and sold_status = 1")
    public Integer findGoodsNumByDate(String date);
}
