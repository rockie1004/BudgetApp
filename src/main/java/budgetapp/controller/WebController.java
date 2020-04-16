package budgetapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import budgetapp.beans.BudgetPeriod;
import budgetapp.beans.BudgetedBills;
import budgetapp.beans.BudgetedIncome;
import budgetapp.beans.DiscretionaryCategory;
import budgetapp.repository.BudgetPeriodRepository;
import budgetapp.repository.BudgetedBillsRepository;
import budgetapp.repository.BudgetedIncomeRepository;
import budgetapp.repository.DiscretionaryCategoryRepository;

@Controller
public class WebController {
	@Autowired
	BudgetPeriodRepository repoBudgetPeriod;
	@Autowired
	BudgetedBillsRepository repoBudgetedBills;
	@Autowired
	BudgetedIncomeRepository repoBudgetedIncome;
	@Autowired
	DiscretionaryCategoryRepository repoDiscretionaryCategory;
	
//	@GetMapping({ "/","/index", "/index.html"})
//	public String index() {
//		return "index.html";
//	}
	
	@GetMapping({ "/viewAllBudgetPeriods" })
	public String viewAllBudgetPeriods(Model model) {
		if(repoBudgetPeriod.findAll().isEmpty()) {
			return addNewBudgetPeriod(model);
		}
		
		model.addAttribute("BudgetPeriods", repoBudgetPeriod.findAll());
		return "resultsPeriod";
	}

	@GetMapping("/inputBudgetPeriod")
	public String addNewBudgetPeriod(Model model) {
		BudgetPeriod p = new BudgetPeriod();
		model.addAttribute("newBudgetPeriod", p);
		return "inputPeriod";
	}

	@GetMapping("/editBudgetPeriod/{id}")
	public String showUpdateBudgetPeriod(@PathVariable("id") long id, Model model) {
		BudgetPeriod p = repoBudgetPeriod.findById(id).orElse(null);
		System.out.println("ITEM TO EDIT: " + p.toString());
		model.addAttribute("newBudgetPeriod", p);
		return "inputPeriod";
	}

	@PostMapping("/updateBudgetPeriod/{id}")
	public String reviseBudgetPeriod(BudgetPeriod p, Model model) {
		repoBudgetPeriod.save(p);
		return viewAllBudgetPeriods(model);
	}
	
	@GetMapping("/deleteBudgetPeriod/{id}")
	public String deleteBudgetPeriod(@PathVariable("id") long id, Model model) {
		BudgetPeriod p = repoBudgetPeriod.findById(id).orElse(null);
	    repoBudgetPeriod.delete(p);
	    return viewAllBudgetPeriods(model);
	}
///something like this to select the item to add linked objects to...
//	@GetMapping("/selectBudgetPeriod/{id}")
//	public BudgetPeriod selectBudgetPeriod(@PathVariable("id") long id, Model model) {
//		BudgetPeriod p = repoBudgetPeriod.findById(id).orElse(null);
//	    return p;
//	}
///	
	@GetMapping("/updateBudgetedBill")
	public String newBudgetedBill(Model model) {
		BudgetedBills p = new BudgetedBills();
		model.addAttribute("newBudgetedBill", p);
		return "BudgetedBill";
	}
	
	@GetMapping({ "/viewAllBudgetedBills" })
	public String viewAllBudgetedBills(Model model) {
		if(repoBudgetedBills.findAll().isEmpty()) {
			return newBudgetedBill(model);
		}
		
		model.addAttribute("BudgetedBills", repoBudgetedBills.findAll());
		return "resultsBudgetedBills";
	}
	@GetMapping("/editBudgetedBill/{id}")
	public String showUpdateBudgetedBill(@PathVariable("id") long id, Model model) {
		BudgetedBills p = repoBudgetedBills.findById(id).orElse(null);
		System.out.println("ITEM TO EDIT: " + p.toString());
		model.addAttribute("newBudgetedBills", p);
		return "inputBudgetedBills";
	}

	@PostMapping("/updateBudgetedBills/{id}")
	public String reviseBudgetedBills(BudgetedBills p, Model model) {
		repoBudgetedBills.save(p);
		return viewAllBudgetedBills(model);
	}
	
	@GetMapping("/deleteBudgetedBills/{id}")
	public String deleteBudgetedBills(@PathVariable("id") long id, Model model) {
		BudgetedBills p = repoBudgetedBills.findById(id).orElse(null);
	    repoBudgetedBills.delete(p);
	    return viewAllBudgetedBills(model);
	}
	
	////////////////
	@GetMapping({ "/viewAllBudgetedIncomes" })
	public String viewAllBudgetedIncomes(Model model) {
		if(repoBudgetedIncome.findAll().isEmpty()) {
			return addNewBudgetedIncome(model);
		}
		
		model.addAttribute("BudgetedIncomes", repoBudgetedIncome.findAll());
		return "resultsIncome";
	}

	@GetMapping("/inputBudgetedIncome")
	public String addNewBudgetedIncome(Model model) {
		BudgetedIncome b = new BudgetedIncome();
		model.addAttribute("newBudgetedIncome", b);
		return "inputIncome";
	}

	@GetMapping("/editBudgetedIncome/{id}")
	public String showUpdateBudgetedIncome(@PathVariable("id") long id, Model model) {
		BudgetedIncome b = repoBudgetedIncome.findById(id).orElse(null);
		System.out.println("ITEM TO EDIT: " + b.toString());
		model.addAttribute("newBudgetedIncome", b);

		return "inputIncome";
	}

	@PostMapping("/updateBudgetedIncome/{id}")
	public String reviseBudgetedIncome(BudgetedIncome b, Model model) {
		repoBudgetedIncome.save(b);
		return viewAllBudgetedIncomes(model);
	}
	
	@GetMapping("/deleteBudgetedIncome/{id}")
	public String deleteBudgetedIncome(@PathVariable("id") long id, Model model) {
		BudgetedIncome b = repoBudgetedIncome.findById(id).orElse(null);
	    repoBudgetedIncome.delete(b);
	    return viewAllBudgetedIncomes(model);
	}
	
	// ------------------------------
	// DiscretionaryCategory Mappings 
	// ------------------------------
	@GetMapping("/mainDiscretionaryCategory")
	public String addNewDiscretionaryCategory(Model model) {
		DiscretionaryCategory dc = new DiscretionaryCategory();
		
		model.addAttribute("discretionaryCategory", dc);	
		
		if(repoDiscretionaryCategory.findAll().isEmpty()) {
			model.addAttribute("allDiscretionaryCategories", "EMPTY");
		} else {
			model.addAttribute("allDiscretionaryCategories", repoDiscretionaryCategory.findAll());
		}
		
		return "discretionaryCategory";
	}
	
	@PostMapping("/updateDiscretionaryCategory/{id}")
	public String reviseDiscretionaryCategory(DiscretionaryCategory dc, Model model) {
		repoDiscretionaryCategory.save(dc);
		
		return addNewDiscretionaryCategory(model);
	}
	
	@GetMapping("/editDiscretionaryCategory/{id}")
	public String showUpdateDiscretionaryCategory(@PathVariable("id") long discCategoryId, Model model) {
		DiscretionaryCategory dc = repoDiscretionaryCategory.findById(discCategoryId).orElse(null);
			
		model.addAttribute("discretionaryCategory", dc);
		
		if(repoDiscretionaryCategory.findAll().isEmpty()) {
			model.addAttribute("allDiscretionaryCategories", "EMPTY");
		} else {
			model.addAttribute("allDiscretionaryCategories", repoDiscretionaryCategory.findAll());
		}
		
		return "discretionaryCategory";
	}
	
	@GetMapping("/deleteDiscretionaryCategory/{id}")
	public String deleteUser(@PathVariable("id") long discCategoryId, Model model) {
		DiscretionaryCategory dc = repoDiscretionaryCategory.findById(discCategoryId).orElse(null);
		repoDiscretionaryCategory.delete(dc);
		
		return addNewDiscretionaryCategory(model);
	}
	
//	*** KEEPING FOR NOW IN CASE I NEED TO REVERT BACK TO THIS ***
//	@GetMapping({"/selectDiscretionaryCategories"})
//	public String viewAllDiscretionaryCategories(Model model) {
//		if(repoDiscretionaryCategory.findAll().isEmpty()) {
//			return addNewDiscretionaryCategory(model);
//		}
//		
//		model.addAttribute("allDiscretionaryCategories", repoDiscretionaryCategory.findAll());
//		
//		return "selectDiscretionaryCategory";
//	}
//	
//	@GetMapping("/newDiscretionaryCategory")
//	public String addNewDiscretionaryCategory(Model model) {
//		DiscretionaryCategory dc = new DiscretionaryCategory();
//		
//		model.addAttribute("discretionaryCategory", dc);	
//		
//		return "addDiscretionaryCategory";
//	}
//	
////	@PostMapping("/newDiscretionaryCategory")
////	public String addNewDiscretionaryCategory(@ModelAttribute DiscretionaryCategory dc, Model model) {
////		repoDiscretionaryCategory.save(dc);
////		
////		return addNewDiscretionaryCategory(model);
////	}
//	
//	@PostMapping("/updateDiscretionaryCategory/{id}")
//	public String reviseDiscretionaryCategory(DiscretionaryCategory dc, Model model) {
//		repoDiscretionaryCategory.save(dc);
//		
//		return addNewDiscretionaryCategory(model);
//	}
//	
//	@GetMapping("/editDiscretionaryCategory/{id}")
//	public String showUpdateDiscretionaryCategory(@PathVariable("id") long discCategoryId, Model model) {
//		DiscretionaryCategory dc = repoDiscretionaryCategory.findById(discCategoryId).orElse(null);
//			
//		model.addAttribute("discretionaryCategory", dc);
//		
//		return "addDiscretionaryCategory";
//	}
//	
//	@GetMapping("/deleteDiscretionaryCategory/{id}")
//	public String deleteUser(@PathVariable("id") long discCategoryId, Model model) {
//		DiscretionaryCategory dc = repoDiscretionaryCategory.findById(discCategoryId).orElse(null);
//		repoDiscretionaryCategory.delete(dc);
//		
//		return addNewDiscretionaryCategory(model);
//	}
}