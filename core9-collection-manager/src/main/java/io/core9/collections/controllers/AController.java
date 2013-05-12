package io.core9.collections.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.core9.collections.Connection;
import io.core9.collections.helpers.impl.JsonToClassObject;
import io.core9.generator.ConvertName;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.TermQuery;
import org.infinispan.Cache;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.json.JsonObject;
import org.infinispan.query.CacheQuery;
import org.infinispan.query.Search;
import org.infinispan.query.SearchManager;

import com.google.gson.Gson;

/**
 * @author jesse
 * @version 1.0
 * @created 02-Mar-2013 10:24:40 PM
 */
public abstract class AController {

	protected Connection connection;
	protected Message<JsonObject> message;
	protected Cache<String, Object> cache;
	protected SearchManager sm;
	protected String className;
	protected String id;
	private JsonObject body;
	private JsonObject document;
	protected JsonObject result = new JsonObject();

	public AController() {

	}

	/**
	 * 
	 * @exception Throwable
	 */
	public void finalize() throws Throwable {

	}

	public JsonObject getResult() {
		return result;
	}

	protected boolean getStatus(List<?> result) {

		int nrResult = result.size();
		if (nrResult > 1) {
			return true;
		}
		return false;
	}

	protected CacheQuery getFullQuery(Class<?> myClass, JsonObject matcher) {
		BooleanQuery q = new BooleanQuery();

		Map<String, String> params = jsonToMap(matcher);

		Set<String> fields = params.keySet();
		for (String field : fields) {
			q.add(new TermQuery(new Term(field, params.get(field))),
					BooleanClause.Occur.SHOULD);
		}

		CacheQuery cq = sm.getQuery(q, myClass);
		return cq;
	}

	public void setConnection(Connection infinispanCachManager) {
		this.connection = infinispanCachManager;

	}

	public void setCache(Cache<String, Object> cache) {
		this.cache = cache;
		this.sm = Search.getSearchManager(cache);
	}

	public boolean run() {
		return false;
	}

	public void setMessage(Message<JsonObject> message) {
		this.message = message;
		body = message.body();
		document = body.getObject("document");
		className = body.getString("collection");

	}

	protected JsonObject getJsonObjFromList(List<Object> result) {

		JsonObject resultObj = new JsonObject();

		int i = 0;
		for (Object obj : result) {

			Gson gson = new Gson();
			String json = gson.toJson(obj);
			resultObj.putString(Integer.toString(i), json);
			i++;
		}

		return resultObj;
	}

	protected boolean getIfIdExistsStatus(Boolean shouldExists, Cache<String, Object> cache) {

		Class<?> myClass = getClazz();
		CacheQuery cq = getQuery(myClass, cache);

		int result = cq.getResultSize();
		if (result == 1 && shouldExists) {
			return true;
		}
		if (result < 1 && !shouldExists) {
			return true;
		}

		return false;
	}

	protected Class<?> getClazz() {
		ClassLoader myClassLoader = ClassLoader.getSystemClassLoader();
		Class<?> myClass = null;
		try {
			myClass = myClassLoader.loadClass(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return myClass;
	}

	private CacheQuery getQuery(Class<?> myClass, Cache<String, Object> cache) {
		BooleanQuery q = new BooleanQuery();

		Map<String, String> params = new HashMap<>();
		params.put("id", id);

		Set<String> fields = params.keySet();
		for (String field : fields) {
			q.add(new TermQuery(new Term(field, params.get(field))),
					BooleanClause.Occur.SHOULD);
		}

		this.sm = Search.getSearchManager(cache);
		CacheQuery cq = sm.getQuery(q, myClass);
		return cq;
	}

	protected Object prepareMessageForSave() {

		id = document.getString("id");
		JsonToClassObject obj = new JsonToClassObject();
		obj.setJsonObj(body);
		Object classObj = obj.getObject();
		return classObj;
	}

	protected Map<String, String> jsonToMap(JsonObject obj) {

		Map<String, String> map = new HashMap<>();

		Set<String> array = obj.getFieldNames();

		for (String field : array) {
			String fld = new ConvertName(field).getName();
			// System.out.println(fld + " : " + document.getString(field));
			map.put(fld, obj.getString(field));
		}

		return map;

	}
}