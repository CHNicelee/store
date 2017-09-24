package com.ice.api;

import com.ice.entity.Banner;
import com.ice.mapping.BannerMapper;
import com.ice.util.ReturnUtil;

import java.util.List;

/**
 * Created by asd on 9/22/2017.
 */
public class BannerAction extends BaseAction{
    private BannerMapper mapper= sqlSession.getMapper(BannerMapper.class);

    public Banner banner = new Banner();
    public String addBanner() throws Exception {
        mapper.insertBanner(banner);
        ReturnUtil.success(result);
        result.put("data",banner);
        return "success";
    }

    public int id;
    public String deleteBanner() throws Exception {
        mapper.deleteBanner(id);
        ReturnUtil.success(result);
        return "success";
    }

    public String getBanners() throws Exception {
        List<Banner> list = mapper.getBanners();
        ReturnUtil.success(result);
        result.put("data",list);
        return "success";
    }

    public String updateBanner() throws Exception {
        return "success";
    }

}
