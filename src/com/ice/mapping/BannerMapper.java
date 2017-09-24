package com.ice.mapping;

import com.ice.entity.Banner;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by asd on 9/20/2017.
 */
public interface BannerMapper {

    @Insert("insert banner(title,productId) values(#{title},#{productId})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    void insertBanner(Banner banner);

    @Delete("delete from banner where id =#{id}")
    void deleteBanner(int id);

    @Select("select * from banner")
    List<Banner> getBanners();

}
