package com.longpc.tuto.api.service;

import com.longpc.tuto.api.constant.TypeEnum;
import com.longpc.tuto.api.data.entity.Log;
import com.longpc.tuto.api.data.manager.LogManager;
import com.longpc.tuto.api.data.manager.PartyManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Long PC
 * 28/12/2023| 16:21 | 2023
 **/
public class BaseService {
    @Autowired
    private LogManager logManager;
    @Autowired
    private PartyManager partyManager;

    protected void addLog(String performerId, String referId, TypeEnum.LogAction action, String content) {
//        Log log = Log.builder()
//                .action(action.name())
//                .content(content)
//                .referId(referId)
//                .partyId(performerId)
//                .build();
//        log.setCreatedTime(new Date());
//        logManager.save(log);
    }

    protected String getNameParty(String id) {
        return partyManager.getById(id).getName();
    }

}
