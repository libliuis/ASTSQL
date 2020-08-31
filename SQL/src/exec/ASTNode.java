package exec;

public abstract class ASTNode {
	ASTNode left;
	ASTNode right;
	public abstract String getSQL();
}
