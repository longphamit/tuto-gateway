package com.longpc.tuto.api.authen.service;

import com.longpc.tuto.api.constant.TypeEnum;
import com.longpc.tuto.api.data.entity.authen.Account;
import com.longpc.tuto.api.data.manager.AccountManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;

/**
 * Long PC
 * 29/4/24| 15:29 | 2024
 **/
@Service
public class AccountService {
    @Autowired
    AccountManager accountManager;

    public List<Account> getAccounts() {
        List<Account> accounts = accountManager.getAccounts();
        if (!ObjectUtils.isEmpty(accounts)) {
            accounts.forEach(account -> {
                account.setPassword(null);
            });
            Collections.reverse(accounts);
        }
        return accounts;
    }

    public void inActive(String id) {
        accountManager.updateAttribute(id, "status", TypeEnum.Status.IN_ACTIVE.getValue());
    }

    public void active(String id) {
        accountManager.updateAttribute(id, "status", TypeEnum.Status.ACTIVE.getValue());
    }
}
