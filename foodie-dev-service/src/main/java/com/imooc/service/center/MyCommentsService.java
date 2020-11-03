package com.imooc.service.center;

import com.imooc.pojo.OrderItems;
import com.imooc.pojo.bo.center.OrderItemsCommentBO;
import com.imooc.utils.PagedGridResult;

import java.util.List;

public interface MyCommentsService {

    /**
     * 根据订单id查询关联的商品
     * @param orderId 订单ID
     * @return 订单信息
     */
     List<OrderItems> queryPendingComment(String orderId);

    /**
     * 保存用户的评论
     * @param orderId 订单ID
     * @param userId 用户ID
     * @param commentList 评论信息
     */
     void saveComments(String orderId, String userId, List<OrderItemsCommentBO> commentList);


    /**
     * 我的评价查询 分页
     * @param userId 用户ID
     * @param page 分页
     * @param pageSize 分页大小
     * @return
     */
     PagedGridResult queryMyComments(String userId, Integer page, Integer pageSize);
}
