package com.longpc.tuto.api.data.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

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

    public void updateAttribute(String id, String name, Object value) {
        Query query = Query.query(Criteria.where("_id").is(id));
        this.mongoTemplate.updateFirst(query, Update.update(name, value), this.collectionName);
    }
}
