package exec;

public class BiggerNode extends ASTNode{
	ASTNode a;
	ASTNode b;
	public BiggerNode(ASTNode a, ASTNode b) {
		this.a = a;
		this.b = b;
	}
	
	@Override
	public String getSQL() {
		return this.a.getSQL() + " > '" + this.b.getSQL() + "'";
	}
}
