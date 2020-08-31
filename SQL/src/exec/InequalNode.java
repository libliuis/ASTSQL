package exec;

public class InequalNode extends ASTNode{
	ASTNode a;
	ASTNode b;
	
	public InequalNode(ASTNode a, ASTNode b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public String getSQL() {
		return this.a.getSQL() + " <> '" + this.b.getSQL() + "'";
	}
}
