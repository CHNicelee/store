package com.ice.api;

import com.ice.util.Constant;
import com.ice.util.MybatisUtil;
import com.ice.util.PermissionUtil;
import com.ice.util.ReturnUtil;
import com.opensymphony.xwork2.Action;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.struts2.interceptor.SessionAware;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by asd on 9/22/2017.
 */
public  class BaseAction implements Action,SessionAware {

    protected SqlSessionFactory factory= MybatisUtil.getFactory();
    protected SqlSession sqlSession =factory.openSession(true);
    public HashMap<String,Object> result = new HashMap<>();

    protected void close(){
        if (Constant.debug) return;
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    final public String execute() throws Exception {
        return null;
    }

    protected Map<String,Object> session;
    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public boolean hasPermission(int permission){
        Integer id = (Integer) this.session.get(Constant.USER_ID);
        System.out.println("id:"+this.session.get(Constant.USER_ID));
        System.out.println("type:"+this.session.get(Constant.USER_TYPE));

        if(id == null || (Integer)this.session.get(Constant.USER_TYPE)!=Constant.ADMIN){
            //没有权限
            ReturnUtil.error(result,"没有权限");
            return false;
        }

        if(!PermissionUtil.hasPermission(id,permission)){
            //没有权限
            ReturnUtil.error(result,"没有权限");
            return false;
        }
        return true;
    }

}
