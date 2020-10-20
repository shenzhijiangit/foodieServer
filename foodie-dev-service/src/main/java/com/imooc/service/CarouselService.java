package com.imooc.service;


import com.imooc.pojo.Carousel;

import java.util.List;

public interface CarouselService {


    /**
     * 查询所有轮播图列表
     * @param isShow isShow
     * @return 返回集合
     */
    public List<Carousel> queryAll (Integer isShow);
}
