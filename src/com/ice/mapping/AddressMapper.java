package com.ice.mapping;

import com.ice.entity.Address;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by asd on 9/20/2017.
 */
public interface AddressMapper {
	@Insert("INSERT INTO Address (userId, phone, address,name,postcode)\n" +
            "        VALUES (#{userId}, #{phone}, #{address},#{name},#{postcode})" )
    void insertAddress(Address address);

	@Select("SELECT * FROM Address WHERE id=#{id}")
    Address getAddressById(int id);
	
	@Select("SELECT * FROM Address WHERE userId=#{userId}")
    List<Address> getAddressByUserId(int userId);
	
	@Delete("delete from user where id=#{id}")
    void deleteAddress(int id);
	
	@Update(" UPDATE Address SET  phone=#{phone}, address=#{address},postcode=#{postcode}\n" +
            "        WHERE  id=#{id}")
    void updateAddress(Address address);

}