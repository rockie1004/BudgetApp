package budgetapp.beans;

import java.util.List;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
public class BudgetPeriod {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private	long id;
	private String description;
	
    @DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date startDate;
   
    @DateTimeFormat(pattern = "MM/dd/yyyy")
	private Date endDate; 
    
	
 



    /*
	//cascade type merge so that it doesn't create duplicates of same item?
	@OneToMany(mappedBy="budgetPeriod", cascade=CascadeType.ALL)
	private List<BudgetedIncome> listOfBudgetedIncomes;
	
	   
	@OneToMany(mappedBy="budgetPeriod", orphanRemoval = true)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<BudgetedBills> listOfBudgetedBills;
	

	@OneToMany(mappedBy="budgetPeriod",  orphanRemoval = true)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<BudgetedDiscretionary> listOfBudgetedDiscretionaries;

	

	public List<BudgetedIncome> getListOfBudgetedIncomes() {
		return listOfBudgetedIncomes;
	}

	
	//https://stackoverflow.com/questions/5587482/hibernate-a-collection-with-cascade-all-delete-orphan-was-no-longer-referenc	
	public void setListOfBudgetedIncomes(List<BudgetedIncome> listOfBudgetedIncomes) {	
			 this.listOfBudgetedIncomes.clear();	
			    if (listOfBudgetedIncomes != null) {	
			        this.listOfBudgetedIncomes.addAll(listOfBudgetedIncomes);	
			    }		
		}
	*/
	/**
	 * @param id
	 */
	public BudgetPeriod(long id) {
		super();
		this.id = id;
	}
	
	/**
	 * @param id
	 * @param Description
	 */
	public BudgetPeriod(long id, String Description) {
		super();
		this.id = id;
		description = Description;
	}
	
	/**
	 * 
	 */
	public BudgetPeriod() {
		super();
	}
	
		
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * @return the Description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * @param Description the Description to set
	 */
	public void setDescription(String Description) {
		description = Description;
	}
	
	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}
	
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}	
	
	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}
	
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	


	@Override
	public String toString() {
		String tostr="BudgetPeriod [id=" + getId() + ", description="+ getDescription() + "] ";
		return tostr;
	}
	


}

