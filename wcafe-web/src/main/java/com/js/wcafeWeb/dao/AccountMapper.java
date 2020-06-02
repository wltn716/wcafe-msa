package com.js.wcafeWeb.dao;

import java.util.List;

import com.js.wcafeWeb.dto.Account;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AccountMapper {
	
	List<Account> readAllUsers();
    Account readAccount(String id);
    List<String> readAuthorities(String id);
    int isAuthorityExist(String authority);

    void insertUser(@Param("account") Account account);
    void insertAuthority(String authority);
    void insertUserAuthority(@Param("id") String id, @Param("authority") String authority);

    void setTokenInfo(@Param("account") Account account);

    
    
}