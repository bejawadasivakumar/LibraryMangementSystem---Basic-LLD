package Library.serviceImpl;

import Library.DataBase.DataBase;
import Library.service.MemberRuleService;

public class StudentRuleServiceImpl implements MemberRuleService {

	@Override
	public int maxBorrowLimit() {
		return DataBase.MAX_BOOKS_BORROWED_LIMIT_PER_STUDENT;
	}

	@Override
	public double calulateFine(int days) {
		return days * DataBase.DAY_FINE_FOR_STUDENT;
	}
}
/*
if(member.getBorrowedBooks().size() >= member.getBorrowLimit()){
throw new RuntimeException("Borrow limit exceeded");
}
*/