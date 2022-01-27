package com.liucj.mapper;

import com.liucj.pojo.vo.CategoryVO;
import com.liucj.pojo.vo.MyCommentVO;
import com.liucj.pojo.vo.NewItemsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Mapper
public interface ItemsCommentsMapperCustom {

    public void saveComments(Map<String, Object> map);

    public List<MyCommentVO> queryMyComments(@Param("paramsMap") Map<String, Object> map);

}