package exec;

public class NameNode extends ASTNode{
	String name;
    public NameNode(String name) {
		this.name = name;
	}
	
	@Override
	public String getSQL() {
		return name.toString();
	}
	
	
}
