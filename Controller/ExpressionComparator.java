package Controller;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import net.bytebuddy.asm.Advice.AllArguments;

public class ExpressionComparator {
	List<Alternatives> subExpressions = new LinkedList<>();

	public void addAll(String [] expressions) {
		for (String expression : expressions) {
			subExpressions.add(new Alternatives(expression.toLowerCase()));
		}
	}
	
	public void add(Alternatives alternatives) {
		this.subExpressions.add(alternatives);
	}


	public boolean compare(String text) {
		text = text.toLowerCase();
		for (Alternatives subExpression : subExpressions) {
			if(!subExpression.presentIn(text)) {
				return false;
			}
		}
		return true;
	}
}
