package com.ice.api;

import com.ice.entity.Announcement;
import com.ice.mapping.AnnouncementMapper;
import com.ice.util.ReturnUtil;

import java.util.List;

/**
 * Created by asd on 9/20/2017.
 */
public class AnnounceAction extends BaseAction {
    private AnnouncementMapper mapper= sqlSession.getMapper(AnnouncementMapper.class);

     /**
     * 获得所有的公告
     * @return
     */
    public String getAllAnnouncement(){
        List<Announcement> list = mapper.getAllAnnouncement();
        ReturnUtil.success(result);
        result.put("data",list);
        close();
        return SUCCESS;
    }

    /**
     * 根据id删除公告
     * @return
     */
    public int id;
    public String deleteAnn(){
        try {
            mapper.deleteAnnouncement(id);
            ReturnUtil.success(result);
        }catch (Exception e){
            e.printStackTrace();
            ReturnUtil.error(result,"未知错误");
        }
        close();
        return SUCCESS;
    }

    /**
     * 更新公告
     * @return
     */
    public Announcement ann = new Announcement();

    /**
     * 根据id更新公告
     * @return
     */
    public String updateAnnouncement(){
        mapper.updateAnnouncement(ann);
        ReturnUtil.success(result);
        result.put("data",ann);
        return SUCCESS;
    }

    /**
     * 添加公告
     * @return
     */
    public String addAnnouncement(){
        System.out.println(ann);
        mapper.insertAnnouncement(ann);
        ReturnUtil.success(result);
        result.put("data",ann);
        return SUCCESS;
    }


    //测试方法

    public void addAnnouncementTest(){
        ann.setText("这是公告");
        ann.setLevel(3);
        addAnnouncement();
    }

    public void updateAnnTest(){
        ann.setText("修改过了");
        updateAnnouncement();
    }

    public void deleteAnnTest(){
        id=2;
        deleteAnn();
        System.out.println(result);
    }

    public void getAllTest(){
        getAllAnnouncement();
        System.out.println(result);
    }

    public static void main(String[] args) {
        //addAnnouncement测试
        AnnounceAction action = new AnnounceAction();
//        action.addAnnouncementTest();
//        action.updateAnnTest();
//        action.deleteAnnTest();
//        action.getAllTest();
    }

}
