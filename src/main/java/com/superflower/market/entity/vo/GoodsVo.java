package com.superflower.market.entity.vo;

import com.superflower.market.entity.Goods;
import com.superflower.market.entity.Want;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class GoodsVo implements Serializable {

    private List<Goods> publishGoodsList;

    private List<Want> wantGoodsList;

    private List<Goods> reportGoodsList;

}
