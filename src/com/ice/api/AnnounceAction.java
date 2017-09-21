package com.ice.api;

import com.ice.entity.Announcement;
import com.ice.mapping.AnnouncementMapper;
import com.ice.util.MybatisUtil;
import com.ice.util.ReturnUtil;
import com.opensymphony.xwork2.Action;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by asd on 9/20/2017.
 */
public class AnnounceAction implements Action {
    private SqlSessionFactory factory= MybatisUtil.getFactory();
    private SqlSession session=factory.openSession(true);
    private AnnouncementMapper mapper=session.getMapper(AnnouncementMapper.class);

    private void close(){
        session.commit();
        session.close();
    }

    public Map<String,Object> result = new HashMap<>();

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
        close();
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
        close();
        return SUCCESS;
    }

    @Override
    public String execute() throws Exception {
        return null;
    }
}
