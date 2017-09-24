package com.ice.test;

import com.ice.api.UserAction;

/**
 * Created by asd on 9/20/2017.
 */
public class UserTest extends UserAction{

/*    public User getUser(){
        User user = new User();
        user.setUsername("ice");
        user.setQuestionId(1);
        user.setPassword("icelee");
        user.setAnswer("哎哟不错哦");
        user.setAvatar("ice97.jpg");
        return user;
    }
*/
/*

    public static void main(String[] args) {
        UserTest entity = new UserTest();
        SqlSessionFactory factory= MybatisUtil.getFactory();
        SqlSession sqlSession=factory.openSession(true);
        //使用反射的方法
        UserMapper mapper=sqlSession.getMapper(UserMapper.class);

        //删除
        mapper.deleteUserByUsername("ice");

        //插入
        User user = entity.getUser();
        mapper.insertUser(user);
        //修改User
        user.setAvatar("修改过了");
        mapper.updateUser(user);

        //获得问题
        Question question = mapper.getQuestion(user.getQuestionId());
        System.out.println(question.getQuestion());
        //查询
        Map<String,Integer> map = new HashMap<>();
        map.put("start",0);
        map.put("offset",1);
        System.out.println(mapper.getUserList(map));
        sqlSession.close();
    }
*/

    public static void main(String[] args) {
        UserTest userTest = new UserTest();
//        userTest.addUserTest();
//        userTest.isExistUserTest();
//        userTest.loginTest();
        userTest.modifyPasswordTest();
    }

    private void loginTest() {
        username = "iceee3";
        password = "iced";
        login();
        System.out.println(result);
    }

    private void addUserTest() {
        user.setUsername("zzzzzz");
        user.setPassword("zzzzz");
        user.setQuestionId(1);
        user.setAnswer("回答");

        addUser();

    }

    public boolean isExistUserTest() {
        username="ice";
        isExistUser();
        System.out.println(result);
        return true;
    }

    public void modifyPasswordTest(){
        username = "zzzzzz";
        newPassword = "icddedddd";
        answer = "回答";
        try {
            modifyPassword();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
