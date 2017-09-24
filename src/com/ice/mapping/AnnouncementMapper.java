package com.ice.mapping;

import com.ice.entity.Announcement;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by asd on 9/20/2017.
 */
public interface AnnouncementMapper {

    @Insert("insert announcement (text,level) values(#{text},#{level})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    void insertAnnouncement(Announcement announcement);

    @Delete("delete from announcement where id =#{id}")
    void deleteAnnouncement(int id);

    @Select("select * from announcement")
    List<Announcement> getAllAnnouncement();

    @Update("update announcement set text=#{text},level=#{level}")
    void updateAnnouncement(Announcement announcement);
}
