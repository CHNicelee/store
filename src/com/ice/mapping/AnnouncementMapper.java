package com.ice.mapping;

import com.ice.entity.Announcement;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by asd on 9/20/2017.
 */
public interface AnnouncementMapper {

    @Insert("insert announcement (text,level,startTime,endTime) values(#{text},#{level},#{startTime},#{endTime})")
    void insertAnnouncement(Announcement announcement);

    @Delete("delete from announcement where id =#{id}")
    void deleteAnnouncement(int id);

    @Select("select * from announcement")
    List<Announcement> getAllAnnouncement();

    @Update("update announcement set text=#{text},level=#{level},startTime=#{startTime},endTime=#{endTime}")
    void updateAnnouncement(Announcement announcement);
}
