package Library.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import Library.DataBase.DataBase;
import Library.exception.MemberAlreadyExistsException;
import Library.exception.MemberNotFoundException;
import Library.model.Member;
import Library.service.MemberManagementService;

public class MemberManagementServiceImpl implements MemberManagementService{

	@Override
	public void addMember(Member member) {
		if(DataBase.members.containsKey(member.getName())) {
			throw new MemberAlreadyExistsException("Member already exists in the database...");
		}
		DataBase.members.put(member.getName(), member);
		System.out.println("New Member added successfully...");
		
	}

	@Override
	public void updateMember(Member member) {
		Member existingMember = DataBase.members.get(member.getName());
		if(existingMember == null) {
			throw new MemberNotFoundException("Member is not found to update...");
		}
		existingMember.setEmail(member.getEmail());
		existingMember.setName(member.getName());
		DataBase.members.put(existingMember.getName(), existingMember);
		System.out.println("Member is updated successfully...");
	}

	@Override
	public void deleteMember(String name) {
		Member existingMember = DataBase.members.get(name);
		if(existingMember == null) {
			throw new MemberNotFoundException("Member is not found to delete...");
		}
		DataBase.members.remove(name);
		System.out.println("Memeber is deleted successfully...");
		
	}

	@Override
	public List<Member> getMembers() {
		List<Member> members = new ArrayList<>();
		for(Member member : DataBase.members.values()) {
			members.add(member);
		}
		return members;
	}

}
