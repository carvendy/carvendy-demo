package com.carvendy.db.mongo;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

public class MongoJDBC {
	
	
	public static void main(String args[]) {
		// 连接到 mongodb 服务
		Mongo mongoClient = null;
		try {
			mongoClient = new Mongo("192.168.20.59", 27017);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		// 连接到数据库
		DB db = mongoClient.getDB("test");
		insert(db);
		//select(db);
		
		//update(db);
	}
	
	private static void select(DB db){
		DBCollection collection = db.getCollection("student");
		BasicDBObject dbObject = new BasicDBObject();
		dbObject.put("name", "a30000000");
		//dbObject.put("age", 50000000);
		long start = System.currentTimeMillis();
		DBObject findOne = collection.findOne(dbObject);
		//字段name,1-23,5000000-1738,9999999-3606
		//字段name+age,1-23,5000000-1736,9999999-3469
		
		//name加索引条件加name,1-63，5000000-31，9999999-16
		//name加索引条件加name+age,1-32，5000000-31，9999999-16
		
		//(1亿数据)name加索引条件加name,1-63，5000000-21635，9999999-28
		//(1亿数据)name加索引条件加name+age,1-23，50000000-23123，99999999-49998
		//30000000,10187
		
		long end = System.currentTimeMillis();
		System.out.println(findOne+","+(end-start));
		
	}

	private static void insert(DB db) {
		long startTime = System.currentTimeMillis();

		try {
			
			DBCollection collection = db.getCollection("student_c");

			for (int i = 1; i <= 100000; i++) {
				BasicDBObject dbObject = new BasicDBObject();
				dbObject.append("name", "a" + i);
				dbObject.append("age", i);
				collection.insert(dbObject);
			}

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}

		long endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime + "ms");
		// insert 100000 3417ms
		// insert 10000000 452736ms
		// insert(加索引之后) 10000000 3921121ms
	}
	
	
	private static void update(DB db){
		long startTime = System.currentTimeMillis();

		try {
			DBCollection collection = db.getCollection("student");
			
			for (int i = 5000000; i <= 5000000 + 10000; i++) {
				BasicDBObject objWhere = new BasicDBObject();
				objWhere.append("name", "a" + i);
				
				BasicDBObject newObj =new BasicDBObject().append("$set",
						new BasicDBObject().append("name", "b"+i) );
				collection.update(objWhere, newObj);
			}

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}

		//(5000w数据)10000-171861ms/107558 

		long endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime + "ms");
	}
	
	
}