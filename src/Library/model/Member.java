package Library.model;


public abstract class Member {
	
	private String name;
	private String email;
	private SpecificMember memberType;
	
	public Member() {

	}
	public Member(String name, String email, SpecificMember member) {
		super();
		this.name = name;
		this.email = email;
		this.memberType = member;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public SpecificMember getMember() {
		return memberType;
	}
	public void setMember(SpecificMember member) {
		this.memberType = member;
	}
	@Override
	public String toString() {
		return "Member [name=" + name + ", email=" + email + ", member=" + memberType + "]";
	}
	
}
