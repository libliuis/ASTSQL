package exec;

public class NOTNode extends ASTNode{

	public NOTNode(ASTNode a, ASTNode b) {
		this.left = a;
		this.right = b;
	}
	@Override
	public String getSQL() {
		return "NOT " + right.getSQL();
	}

}
