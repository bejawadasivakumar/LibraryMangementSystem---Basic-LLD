package Library.service;

public interface MemberRuleService {

	int maxBorrowLimit();
	double calulateFine(int days);
}
