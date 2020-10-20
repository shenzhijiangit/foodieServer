package com.imooc.service;


import com.imooc.pojo.Category;
import com.imooc.pojo.vo.CategoryVO;
import com.imooc.pojo.vo.NewItemsVO;

import java.util.List;

public interface CategoryService {


    /**
     * 查询所有一级分类
     * @return 返回查询到的所有一级分类集合
     */
    List<Category> queryAllRootLevelCat();

    /**
     * 查询二级分类
     * @param rootCatId 一级分类ID
     * @return 返回查询到的list
     */
    List<CategoryVO> getSubCatList(Integer rootCatId);

    /**
     * 查询首页每个一级分类下的6条最新商品数据
     * @param rootCatId 一级分类ID
     * @return 返回查询到的list
     */
    List<NewItemsVO> getSixNewItemsLazy(Integer rootCatId);

}
