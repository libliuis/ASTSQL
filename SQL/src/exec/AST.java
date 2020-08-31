package exec;

import java.util.Stack;

public class AST {
	Stack<ASTNode> SA = new Stack<ASTNode>();
	
	public void eqstack(String tmp) {
		if(SA.peek().getSQL()=="==") {
			SA.pop();
			EqualNode equalNode = new EqualNode(SA.pop(), new NameNode(tmp));
			if(SA.isEmpty()) {
				SA.push(equalNode);
			}
			else {
				gtstack(equalNode);
			}
		}
		else if(SA.peek().getSQL()=="<>") {
			SA.pop();
			InequalNode inequalNode = new InequalNode(SA.pop(), new NameNode(tmp));
			if(SA.isEmpty()) {
				SA.push(inequalNode);
			}
			else {
				gtstack(inequalNode);
			}
		}
		else {
			SA.push(new NameNode(tmp));
		}
	}
	
	public void gtstack(ASTNode astNode) {
		if(SA.isEmpty()||SA.peek().getSQL()=="(") {
			SA.push(astNode);
			return;
		}
		while(!SA.isEmpty()) {
			if(SA.peek().getSQL()=="OR") {
				SA.pop();
				SA.push(new ORNode(SA.pop(), astNode));
			}
			else if(SA.peek().getSQL()=="AND") {
				SA.pop();
				SA.push(new ANDNode(SA.pop(), astNode));
			}
			else if(SA.peek().getSQL()=="NOT") {
				SA.push(new NOTNode(new NameNode(""), astNode));
			}
			else {
				break;
			}
		}
	}

	public void parse(String word) {
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) == ' ')
				continue;
			if (word.charAt(i) == '"')
				continue;
			if (word.charAt(i) == '\'')
				continue;
			if (word.charAt(i) == '(') {
				SA.push(new NameNode("("));
				continue;
			}
			if(word.charAt(i)=='='&&word.charAt(i+1)=='=') {
				i++;
				SA.push(new NameNode("=="));
				continue;
			}
			if(word.charAt(i)=='<'&&word.charAt(i+1)=='>') {
				i++;
				SA.push(new NameNode("<>"));
				continue;
			}
			if(word.charAt(i)==')') {
				ASTNode astNode = SA.pop();
				SA.pop();
				gtstack(astNode);
			}
			if (word.charAt(i) >= 'A' && word.charAt(i) <= 'Z' || word.charAt(i) >= 'a' && word.charAt(i) <= 'z'
					|| word.charAt(i) >= '0' && word.charAt(i) <= '9') {
				int j = i;
				String tmp = new String();
				while(j<word.length()) {
					if (word.charAt(j) >= 'A' && word.charAt(j) <= 'Z' || word.charAt(j) >= 'a' && word.charAt(j) <= 'z'
							|| word.charAt(j) >= '0' && word.charAt(j) <= '9')
					{
						tmp+=word.charAt(j);
						j++;
					}
					else {
						j--;
						i=j;
						break;
					}
				}
				if(j==word.length())i=j;
				if(tmp.equals("OR"))
				{
					SA.push(new NameNode("OR"));
					continue;
				}
				if(tmp.equals("AND")) {
					SA.push(new NameNode("AND"));
					continue;
				}
				if(tmp.equals("NOT")) {
					SA.push(new NameNode("NOT"));
					continue;
				}
				if(!SA.isEmpty())eqstack(tmp);
				else SA.push(new NameNode(tmp));
			}
		}
	}

	public String getSQL() {
		return "Select * from table where " + SA.peek().getSQL();
	}
}
