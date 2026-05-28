package fr.n7.stl.minijava.expression;

import fr.n7.stl.minic.ast.SemanticsUndefinedException;
import fr.n7.stl.minic.ast.expression.Expression;
import fr.n7.stl.minic.ast.scope.Declaration;
import fr.n7.stl.minic.ast.scope.HierarchicalScope;
import fr.n7.stl.minic.ast.type.Type;

public abstract class AbstractSuper  <ObjectKind extends Expression> implements Expression {

	public AbstractSuper() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean collectAndPartialResolve(HierarchicalScope<Declaration> _scope) {
		// TODO Auto-generated method stub
		throw new SemanticsUndefinedException( "Semantics collect and partial resolve is undefined in AbstractSuper.");
		//return false;
	}

	@Override
	public boolean completeResolve(HierarchicalScope<Declaration> _scope) {
		// TODO Auto-generated method stub
		throw new SemanticsUndefinedException( "Semantics complete resolve is undefined in AbstractSuper.");
		//return false;
	}

	@Override
	public Type getType() {
		// TODO Auto-generated method stub
		throw new SemanticsUndefinedException( "AbstractSuper getType");
		//return null;
	}
	
	@Override
	public String toString() {
		return "super";
	}

}
