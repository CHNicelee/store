package com.ice.mapping;

import com.ice.entity.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by asd on 9/20/2017.
 */
public interface QuestionMapper {

	
	@Insert("INSERT INTO Question(question)\n" +
            "        VALUES (#{question})" )
    @Options(useGeneratedKeys = true,keyProperty = "id")
    void insertQuestion(Question question);

	@Update(" UPDATE Question SET  question=#{question}\n" +
            "        WHERE  id=#{id}")
    void updateQuestion(Question question);

	@Select("SELECT * FROM Question WHERE id=#{id}")
    Question getQuestion(int id);

	@Select("SELECT * FROM Question")
    List<Question> getAllQuestions();

}
