package com.longpc.tuto.api.data.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Long PC
 * 28/12/2023| 16:13 | 2023
 **/
public abstract class BaseManager<T> {
    @Autowired
    protected MongoTemplate mongoTemplate;
    protected String collectionName;
    protected Class<T> tClass;

    public BaseManager(String collectionName, Class<T> tClass) {
        this.collectionName = collectionName;
        this.tClass = tClass;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public T save(T o) {
        return mongoTemplate.save(o);
    }

    public T getById(String id) {
        return this.mongoTemplate.findById(id, this.tClass, this.collectionName);
    }
}
