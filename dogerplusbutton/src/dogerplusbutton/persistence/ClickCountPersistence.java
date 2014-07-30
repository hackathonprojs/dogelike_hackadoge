package dogerplusbutton.persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

import dogerplusbutton.model.ClickCount;

public class ClickCountPersistence {

	public static final String ENTITY_NAME = "ClickCount";
	
	public static Entity create(String uid, String domain, long count) {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		Entity clickCount = new Entity(ENTITY_NAME);

		clickCount.setProperty("uid", uid);
		clickCount.setProperty("domain", domain);
		clickCount.setProperty("count", count);

		Date lastClickTime = new Date();
		clickCount.setProperty("lastClickTime", lastClickTime);

		datastore.put(clickCount);
		
		return clickCount;
	}
	
	/**
	 * if not found, then create the record.  
	 * if found, simply increment the record.  
	 * 
	 * @param uid
	 * @param domain
	 * @param count - increment the count by this much
	 */
	public static Entity incrementCount(String uid, String domain, long count) {
		Entity result = null;
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

		Filter uidFilter =
		  new FilterPredicate("uid",
		                      FilterOperator.EQUAL,
		                      uid);

		Filter domainFilter =
		  new FilterPredicate("domain",
		                      FilterOperator.EQUAL,
		                      domain);

		//Use CompositeFilter to combine multiple filters
		Filter combinedFilter =
		  CompositeFilterOperator.and(uidFilter, domainFilter);


		// Use class Query to assemble a query
		Query q = new Query(ENTITY_NAME).setFilter(combinedFilter);

		// Use PreparedQuery interface to retrieve results
		PreparedQuery pq = datastore.prepare(q);
		
		// find first and increment the first one.
		Iterator<Entity> iter = pq.asIterator();
		if (iter.hasNext()) {
			result = iter.next();
			long resultCount = (long) result.getProperty("count");
			result.setProperty("count", (resultCount + count));
			datastore.put(result);
		} else {
			result = create(uid, domain, count);
		}
				
		return result;
	}
	
	/**
	 * return the total count by summing the count from all the records that fits the filter criteria.  
	 * filter are fine.  
	 * @param uid
	 * @param domain
	 * @return
	 */
	public static long totalByUidDomain(String uid, String domain) {
		long total = 0;
		List<ClickCount> results = findByUidDomain(uid, domain);
		for (ClickCount cc:results) {
			total += cc.getCount();
		}
		return total;
	}
	
	/**
	 * find by uid and domain
	 */
	public static List<ClickCount> findByUidDomain(String uid, String domain) {
		List<ClickCount> results = new ArrayList<ClickCount>();
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

		Filter uidFilter =
		  new FilterPredicate("uid",
		                      FilterOperator.EQUAL,
		                      uid);

		Filter domainFilter =
		  new FilterPredicate("domain",
		                      FilterOperator.EQUAL,
		                      domain);

		//Use CompositeFilter to combine multiple filters
		Filter combinedFilter =
		  CompositeFilterOperator.and(uidFilter, domainFilter);


		// Use class Query to assemble a query
		Query q = new Query(ENTITY_NAME).setFilter(combinedFilter);

		// Use PreparedQuery interface to retrieve results
		PreparedQuery pq = datastore.prepare(q);


		for (Entity result : pq.asIterable()) {
			String resultUid = (String) result.getProperty("uid");
			String resultDomain = (String) result.getProperty("domain");
			long resultCount = (long) result.getProperty("count");

			System.out.println("{ uid: " + resultUid + ", domain: " + resultDomain + ", count: " + resultCount + " }");
			results.add(new ClickCount(resultUid, resultDomain, resultCount));
		}
		
		return results;
	}
	
	
}
