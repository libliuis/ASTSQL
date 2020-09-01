package exec;

public class SmallerNode extends ASTNode{
	ASTNode a;
	ASTNode b;
	public SmallerNode(ASTNode a, ASTNode b) {
		this.a = a;
		this.b = b;
	}
	
	@Override
	public String getSQL() {
		return this.a.getSQL() + " < '" + this.b.getSQL() + "'";
	}
}
