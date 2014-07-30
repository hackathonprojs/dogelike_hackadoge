package dogerplusbutton.user;

/**
 * preference for that domain. 
 * including its payout address.  
 * 
 * @author sol wu
 *
 */
public class DomainPreference {

	//
	// todo: hack, hardcode. - begin
	//
	private static DomainPreference domain1 = new DomainPreference();
	private static DomainPreference domain2 = new DomainPreference();
	private static DomainPreference domain3 = new DomainPreference();
	
	static {
		domain1.setPayoutAddress("D9VRctTsbBceNrthg1YH4pD7fpcRHRYM6g");
		domain2.setPayoutAddress("D9VRctTsbBceNrthg1YH4pD7fpcRHRYM6g");
		domain3.setPayoutAddress("D9VRctTsbBceNrthg1YH4pD7fpcRHRYM6g");
	}
	//
	// hack - ends
	//
	
	
	/** payout address. where we will pay this account */
	private String payoutAddress = null;

	
	public String getPayoutAddress() {
		return payoutAddress;
	}


	public void setPayoutAddress(String payoutAddress) {
		this.payoutAddress = payoutAddress;
	}


	public static DomainPreference getDomainPreference(String domain) {
		// hack, hardcode
		if ("domain1".equals(domain)) {
			return domain1;
		} else if ("domain2".equals(domain)) {
			return domain2;
		} else if ("domain3".equals(domain)) {
			return domain3;
		}
		return domain1;
	}
}
