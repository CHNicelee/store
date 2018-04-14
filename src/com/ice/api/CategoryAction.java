package com.ice.api;

import com.ice.entity.Category;
import com.ice.mapping.CategoryMapper;
import com.ice.mapping.ProductMapper;
import com.ice.util.PermissionUtil;
import com.ice.util.ReturnUtil;
import org.apache.struts2.interceptor.SessionAware;

import java.util.List;

/**
 * Created by asd on 9/21/2017.
 */
public class CategoryAction extends BaseAction implements SessionAware{

    private CategoryMapper mapper= sqlSession.getMapper(CategoryMapper.class);

    /**
     * 添加分类
     */
    public Category category = new Category();
    public String addCategory(){
        if(!hasPermission(PermissionUtil.PERMISSION_MODIFY_CATEGORY))return SUCCESS;
        try {
            mapper.insertCategory(category);
            result.put("data",category);
            ReturnUtil.success(result);
        }catch (Exception e){
            e.printStackTrace();
            ReturnUtil.error(result,"添加失败"+e.getMessage());
        }
        close();
        return SUCCESS;
    }


    /**
     * 获得根目录
     * @return
     */
    public int id;
    public int adminId;
    public String getRootCategory(){
        List<Category> list = mapper.getRootCategory();
        for (Category c : list) {
            c.setParent(isParent(c.getId()));//设置是否下面还有分类
        }
        result.put("data",list);
        ReturnUtil.success(result);
        close();
        return SUCCESS;
    }

    private boolean isParent(int id){
        List<Category> list = mapper.getChildrenCategory(id);
        return list.size() > 0;
    }

    /**
     * 获得子分类
     * @return
     */
    public String getChildrenCategory(){
        List<Category> list = mapper.getChildrenCategory(id);
        result.put("data",list);
        ReturnUtil.success(result);
        close();
        return SUCCESS;
    }

    /**
     * 更新分类信息
     * @return
     */
    public String updateCategory(){
        if(!hasPermission(PermissionUtil.PERMISSION_MODIFY_CATEGORY))return SUCCESS;
        try {
            mapper.updateCategory(category);
            ReturnUtil.success(result);
            result.put("data",category);
        }catch (Exception e){
            e.printStackTrace();
            ReturnUtil.error(result,"发生错误"+ e.getMessage());
        }
        close();
        return SUCCESS;
    }

    /**
     * 删除分类
     * @return
     */
    public String deleteCategory(){
        if(!hasPermission(PermissionUtil.PERMISSION_MODIFY_CATEGORY))return SUCCESS;
        ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
        if(isParent(id) ||productMapper.getProductByCategoryId(id).size()>0){
            ReturnUtil.error(result,"分类下有小分类或商品，无法删除");
            return SUCCESS;
        }
        try {
            mapper.deleteCategory(id);
            ReturnUtil.success(result);
        }catch (Exception e){
            e.printStackTrace();
            ReturnUtil.error(result,"修改失败"+e.getMessage());
        }
        close();
        return SUCCESS;
    }

    public String name;
    public String searchCategory() throws Exception {
        List<Category> list = mapper.searchCateogry("%"+name+"%");
        result.put("data",list);
        ReturnUtil.success(result);
        return "success";
    }
}
