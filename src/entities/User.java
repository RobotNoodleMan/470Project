package entities;

public abstract class User {
	
	private SearchCriteria getSearchCrit;
	
	public User()
	{
		getSearchCrit = new SearchCriteria();
	}

	public SearchCriteria getSearchCrit() {
		return getSearchCrit;
	}

	public void setSearchCrit(SearchCriteria searchCrit) {
		this.getSearchCrit = searchCrit;
	}

}
