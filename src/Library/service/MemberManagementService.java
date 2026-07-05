package Library.service;

import java.util.List;

import Library.model.Member;

public interface MemberManagementService {
	
	void addMember(Member member);
	void updateMember(Member member);
	void deleteMember(String name);
	List<Member> getMembers();

}
