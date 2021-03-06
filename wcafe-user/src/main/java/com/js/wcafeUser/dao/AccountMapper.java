package com.js.wcafeUser.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.js.wcafeUser.dto.Account;

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

    
    
}