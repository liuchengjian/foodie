package com.liucj.controller;

import com.liucj.common.enums.YesOrNo;
import com.liucj.common.utils.ServerJSONResult;
import com.liucj.pojo.Carousel;
import com.liucj.pojo.Category;
import com.liucj.pojo.vo.CategoryVO;
import com.liucj.service.CarouselService;
import com.liucj.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

//@Controller
@Api(value = "首页", tags = {"首页展示的相关接口"})
@RestController
@RequestMapping("index")
public class IndexController {
    @Autowired
    private CarouselService carouselService;

    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "首页轮播图",notes = "首页轮播图",httpMethod = "GET")
    @GetMapping("/carousel")
    public ServerJSONResult carousel(){
        List<Carousel> carousels= carouselService.queryAll(YesOrNo.YES.type);
        return ServerJSONResult.ok(carousels);
    }


    /**
     * 首页分类展示需求：
     * 1. 第一次刷新主页查询大分类，渲染展示到首页
     * 2. 如果鼠标上移到大分类，则加载其子分类的内容，如果已经存在子分类，则不需要加载（懒加载）
     */
    @ApiOperation(value = "获取商品分类(一级分类)", notes = "获取商品分类(一级分类)", httpMethod = "GET")
    @GetMapping("/cats")
    public ServerJSONResult cats() {
        List<Category>list = categoryService.queryAllRootLevelCat();
        return ServerJSONResult.ok(list);
    }

    @ApiOperation(value = "获取商品子分类", notes = "获取商品子分类", httpMethod = "GET")
    @GetMapping("/subCat/{rootCatId}")
    public ServerJSONResult subCat(
            @ApiParam(name = "rootCatId", value = "一级分类id", required = true)
            @PathVariable Integer rootCatId) {

        if (rootCatId == null) {
            return ServerJSONResult.errorMsg("分类不存在");
        }

        List<CategoryVO> list = categoryService.getSubCatList(rootCatId);
        return ServerJSONResult.ok(list);
    }
}
