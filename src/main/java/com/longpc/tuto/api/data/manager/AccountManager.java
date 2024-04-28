package com.longpc.tuto.api.data.manager;

import com.longpc.tuto.api.constant.TypeEnum;
import com.longpc.tuto.api.data.entity.authen.Account;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Long PC
 * 28/12/2023| 16:13 | 2023
 **/
@Component
public class AccountManager extends BaseManager<Account> {

    public AccountManager() {
        super("account", Account.class);
    }

    public Account getByUsername(String username) {
       return mongoTemplate.findOne(Query.query(Criteria.where("username").is(username)), Account.class);
//        Account account= new Account();
//        account.setUsername("longpc");
//        account.setPartyId("111");
//        Set<String> roles= new HashSet<>();
//        roles.add(TypeEnum.Role.ADMIN.name());
//        account.setRole(new HashSet<>());
//        return account;
    }
    public boolean checkExistUsername(String username){
        return mongoTemplate.exists(Query.query(Criteria.where("username").is(username)),Account.class);
    }
}
