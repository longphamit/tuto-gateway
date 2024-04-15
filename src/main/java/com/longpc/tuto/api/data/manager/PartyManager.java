package com.longpc.tuto.api.data.manager;

import com.longpc.tuto.api.data.entity.authen.Party;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

/**
 * Long PC
 * 28/12/2023| 16:12 | 2023
 **/
@Component
public class PartyManager extends BaseManager<Party> {
    public PartyManager() {
        super("party", Party.class);
    }
}
