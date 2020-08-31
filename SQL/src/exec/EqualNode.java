package exec;

public class EqualNode extends ASTNode{
	ASTNode a;
	ASTNode b;
	public EqualNode(ASTNode a, ASTNode b) {
		this.a = a;
		this.b = b;
	}
	@Override
	public String getSQL() {
		return this.a.getSQL() + " = '" + this.b.getSQL() + "'";
	}
	
	
}
