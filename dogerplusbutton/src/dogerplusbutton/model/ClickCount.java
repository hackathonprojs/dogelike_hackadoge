package dogerplusbutton.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * number of clicks a person did on each website.  
 * @author t
 *
 */
@Entity
public class ClickCount {
	@Id
	private long id;
	
	/** user id */
	private String uid;
	/** domain that is receiving the tip */
	private String domain;
	/** how many times it is clicked */
	private long count;
	
	public ClickCount() {
		
	}
	
	public ClickCount(String uid, String domain, long count) {
		this.uid = uid;
		this.domain = domain;
		this.count = count;
	}
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	
	
}
