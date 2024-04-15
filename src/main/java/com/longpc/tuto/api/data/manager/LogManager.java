package com.longpc.tuto.api.data.manager;

import com.longpc.tuto.api.data.entity.Log;
import org.springframework.stereotype.Component;

/**
 * Long PC
 * 28/12/2023| 16:20 | 2023
 **/
@Component
public class LogManager extends BaseManager<Log> {

    public LogManager() {
        super("log", Log.class);
    }
}
