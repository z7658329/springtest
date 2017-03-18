package spring.autowire;

public class TestA {
	private String username;
	private TestB testb;
	public void init() {
		System.out.println("A is chu初始化方法niit!!");
	}
	public TestA() {
		super();
		System.out.println("testa is init ");
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public TestB getTestb() {
		return testb;
	}
	public void setTestb(TestB testb) {
		this.testb = testb;
	}
	@Override
	public String toString() {
		return "TestA [username=" + username + ", testb=" + testb + "]";
	}
	
}
