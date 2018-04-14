package com.ice.api;

import com.hankcs.hanlp.seg.common.Term;
import com.ice.entity.Attribute;
import com.ice.entity.OrderDetail;
import com.ice.entity.OrderInfo;
import com.ice.entity.Product;
import com.ice.mapping.*;
import com.ice.util.DataUtil;
import com.ice.util.HanLPUtil;
import com.ice.util.PermissionUtil;
import com.ice.util.ReturnUtil;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by asd on 9/22/2017.
 */
public class ProductAction extends BaseAction  {
    private ProductMapper productMapper = sqlSession.getMapper(ProductMapper.class);
    private HistoryMapper historyMapper = sqlSession.getMapper(HistoryMapper.class);
    private CollectionMapper collectionMapper = sqlSession.getMapper(CollectionMapper.class);
    OrderDetailMapper orderDetailMapper = sqlSession.getMapper(OrderDetailMapper.class);
    OrderInfoMapper orderMapper = sqlSession.getMapper(OrderInfoMapper.class);
    ImageMapper imageMapper = sqlSession.getMapper(ImageMapper.class);
    AttributeMapper attributeMapper = sqlSession.getMapper(AttributeMapper.class);

    /**
     * 添加商品
     */
    public Product product = new Product();
    public String addProduct(){
        if(!hasPermission(PermissionUtil.PERMISSION_MODIFY_PRODUCT))return SUCCESS;
        productMapper.insertProduct(product);
        result.put("data",product);
        ReturnUtil.success(result);
        close();
        return SUCCESS;
    }

    /**
     * 通过商品分类获得商品
     * 不同规格的商品不会都返回  只会返回其中一种
     */
    public  int categoryId;
    public String getProductByCategoryId(){
        List<Product> list = productMapper.getProductByCategoryId(categoryId);
        getAttributes(list);
//        list= distinctList(list);
        setImageUrls(list);
        ReturnUtil.success(result);
        result.put("data",list);
        return SUCCESS;
    }

    public void getAttributes(Product product){
        product.setAttr(attributeMapper.getAttributesByProductId(product.getId()));
    }

    public void getAttributes(List<Product> list){
        for (Product product1 : list) {
            getAttributes(product1);
        }
    }

    public String getProductById(){
        product = productMapper.getProductById(id);
        getAttributes(product);
        setImageUrls(product);
        result.put("data",product);
        ReturnUtil.success(result);
        close();
        return SUCCESS;
    }

    public String name;
    public String searchProduct(){
        //先搜索完整的名词
        List<Product> list = productMapper.searchProductByName("%"+name+"%");
        //再进行分词
        List<Term> words = HanLPUtil.standardWord(name);
        //将各个名词进行搜索
        words.forEach(term -> list.addAll(productMapper.searchProductByName("%"+term.word+"%")));
        //排除重复项
        Map<Integer,Boolean> map = new HashMap<>();
        List<Product> resultList = new ArrayList<>();
        for (Product product1 : list) {
            if(map.get(product1.getId())==null){
                map.put(product1.getId(),true);
                resultList.add(product1);
            }
        }
        System.out.println(list);
        System.out.println(resultList);
        getAttributes(resultList);
//        list= distinctList(list);
        setImageUrls(resultList);
        result.put("data",resultList);
        List<String> wordList = new ArrayList<>();
        for (Term word : words) {
            wordList.add(word.word);
        }
        result.put("words",wordList);
        ReturnUtil.success(result);
        return SUCCESS;
    }

    private void setImageUrls(List<Product> productList){
        for (Product product : productList) {
            product.setImageUrls(imageMapper.getImageUrl(product.getId()));
        }
    }

    private void setImageUrls(Product product){
        product.setImageUrls(imageMapper.getImageUrl(product.getId()));
    }

    private List<Product> distinctList(List<Product> list){
        Map<String,Boolean> map = new HashMap<>();
        List<Product> resultList = new ArrayList<>();
        for (Product p : list) {

            if(p!=null && !map.containsKey(p.getName())){
                map.put(p.getName(),true);
                resultList.add(p);
            }
        }
        return resultList;
    }

    /**
     * 获得最新商品
     * @return
     * @throws Exception
     */
    public String getLatestProduct() throws Exception {
        List<Product> list = productMapper.getLatestProduct();
        getAttributes(list);
//        list= distinctList(list);
        setImageUrls(list);
        ReturnUtil.success(result);
        result.put("data",list);
        return "success";
    }

    public int startId;
    public String getMore() throws Exception {
        Map<String, Integer> map = new HashMap<>();
        map.put("categoryId",categoryId);
        map.put("startId",startId);
        map.put("endId",startId+30);
        List<Product> list = productMapper.getMore(map);
        getAttributes(list);
//        list= distinctList(list);
        setImageUrls(list);
        result.put("data",list);
        ReturnUtil.success(result);
        return "success";
    }

    /**
     * 热门商品
     * @return
     * @throws Exception
     */
    public String getHot() throws Exception {
        AttributeMapper attributeMapper = sqlSession.getMapper(AttributeMapper.class);
        List<OrderInfo> list = orderMapper.getOrderInfoByTimestamp(DataUtil.getSpecifiedDay(-5));
        List<Product> hotList = new ArrayList<>();
        for (OrderInfo orderInfo : list) {
            List<OrderDetail> detailList = orderDetailMapper.getOrderDetails(orderInfo.getId());
            Collections.sort(detailList,((o1, o2) -> o1.getNumber() - o2.getNumber()));
            hotList.addAll(detailList.stream().map(orderDetail -> productMapper.getProductById(orderDetail.getProductId())).collect(Collectors.toList()));
        }
//        Collections.sort(hotList, (o1, o2) -> o1.getCount() - o2.getCount());
        Map<Integer,Boolean> map = new HashMap<>();
        List<Product> resultList = new ArrayList<>();
        System.out.println(hotList);
        hotList.stream().filter(product -> map.get(product.getId()) == null).forEach(product -> {
            map.put(product.getId(), true);
            resultList.add(product);
        });
        getAttributes(resultList);
//        list= distinctList(hotList);
        setImageUrls(resultList);
        result.put("data",resultList);
        ReturnUtil.success(result);
        return "success";
    }

    public static void main(String[] args) {
        ProductAction action = new ProductAction();
        try {
            action.getHot();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int id;
    public String deleteProductById() throws Exception {
        if(!hasPermission(PermissionUtil.PERMISSION_MODIFY_PRODUCT))return SUCCESS;
        if(orderDetailMapper.getOrderDetailsByProductId(id).size()>0){
            ReturnUtil.error(result,"订单中有该商品，无法删除");
            return SUCCESS;
        }
        productMapper.deleteProduct(id);
        ReturnUtil.success(result);
        return "success";
    }

    public String updateProduct() throws Exception {
        if(!hasPermission(PermissionUtil.PERMISSION_MODIFY_PRODUCT)) return SUCCESS;
        productMapper.updateProduct(product);
        ReturnUtil.success(result);
        return "success";
    }

    public Attribute attr = new Attribute();
    public String addAttr() throws Exception {
        attributeMapper.insertAttribute(attr);
        result.put("data",attr);
        ReturnUtil.success(result);
        return "success";
    }
}
