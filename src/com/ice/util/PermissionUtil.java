package com.ice.util;

import com.ice.entity.Admin;
import com.ice.mapping.AdminMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * Created by asd on 9/23/2017.
 */
public class PermissionUtil {
    public static final int PERMISSION_MODIFY_PRODUCT = 1;
    public static final int PERMISSION_MODIFY_CATEGORY = 2;
    public static final int PERMISSION_MODIFY_ANNOUNCEMENT = 4;
    public static final int PERMISSION_MODIFY_USER = 8;
    public static final int PERMISSION_MODIFY_ORDER = 16;

    /**
     * 检查是否具有权限
     */
    public static boolean hasPermission(Integer adminId,int permission){
        if(adminId == null) return false;
        SqlSessionFactory factory= MybatisUtil.getFactory();
        SqlSession session=factory.openSession(true);
        AdminMapper adminMapper = session.getMapper(AdminMapper.class);
        Admin admin = adminMapper.getAdmin(adminId);
        session.close();
        System.out.println("permission auth:"+admin.getAuth());
        System.out.println("permission permission:"+permission);
        return (admin.getAuth()&permission)>0;
    }

}
