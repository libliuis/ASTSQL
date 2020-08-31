package exec;

public class ORNode extends ASTNode{
	public ORNode(ASTNode a, ASTNode b) {
		this.left = a;
		this.right = b;
	}
	@Override
	public String getSQL() {
		return "(" + left.getSQL() + " OR " + right.getSQL() + ")";
	}

}
