package exec;

public class ANDNode extends ASTNode{
	public ANDNode(ASTNode a, ASTNode b) {
		this.left = a;
		this.right = b;
	}

	@Override
	public String getSQL() {
		return "(" + left.getSQL() + " AND " + right.getSQL() + ")"; 
	}

}
