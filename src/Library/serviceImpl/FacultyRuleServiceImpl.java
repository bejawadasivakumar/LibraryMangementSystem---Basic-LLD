package Library.serviceImpl;

import Library.DataBase.DataBase;
import Library.service.MemberRuleService;

public class FacultyRuleServiceImpl implements MemberRuleService{

	@Override
	public int maxBorrowLimit() {
		return DataBase.MAX_BOOKS_BORROWED_LIMIT_PER_FACULTY;
	}

	@Override
	public double calulateFine(int days) {
		return days * DataBase.DAY_FINE_FOR_FACULTY;
	}

}
