package com.ice.api;

import com.ice.entity.Question;
import com.ice.mapping.QuestionMapper;
import com.ice.mapping.UserMapper;
import com.ice.util.ReturnUtil;

import java.util.List;


public class QuestionAction extends BaseAction{
	
	    private QuestionMapper mapper= sqlSession.getMapper(QuestionMapper.class);

	    public Question question = new Question();

	    public String addQuestion(){
	        try {
	        	 mapper.insertQuestion(question);
	            result.put("data",question);
	            ReturnUtil.success(result);
	        }catch (Exception e){
				e.printStackTrace();
	            ReturnUtil.error(result,"添加失败"+e.getMessage());
	        }

	        return SUCCESS;
	    }

	    public String updateQuestion(){
	        mapper.updateQuestion(question);
	        ReturnUtil.success(result);
	        result.put("data",question);
	        close();
	        return SUCCESS;
	    }
	    
	    public int id;
	    public String getQuestionById(){
	    	question = mapper.getQuestion(id);
	        if(question!=null) {
	            ReturnUtil.success(result);
	            result.put("data",question);
	        }else{
	            ReturnUtil.error(result,"查询失败");
	        }
	        close();
	        return SUCCESS;
	    }
	    
	    public String getAllQuestions(){
	        List<Question> list = mapper.getAllQuestions();
	        ReturnUtil.success(result);
	        result.put("data",list);
	        close();
	        return SUCCESS;
	    }

		public String username;
    	public String getQuestionByUsername() throws Exception {
        	return "success";
    	}
}
