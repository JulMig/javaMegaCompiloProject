package fr.n7.stl.minijava.expression.assignable;

import fr.n7.stl.minic.ast.SemanticsUndefinedException;
import fr.n7.stl.minic.ast.expression.assignable.AssignableExpression;
import fr.n7.stl.minic.ast.scope.Declaration;
import fr.n7.stl.minic.ast.scope.HierarchicalScope;
import fr.n7.stl.minijava.expression.AbstractThis;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.TAMFactory;

public class ThisAssignment extends AbstractThis<AssignableExpression> implements AssignableExpression {

	public ThisAssignment() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getCode(TAMFactory _factory) {
		
		Fragment f =  _factory.createFragment();
		f.add(_factory.createLoadL(this.content.getOffset()));
		//ADRESSE DE LA CLASSE
		return f;
		
		//throw new SemanticsUndefinedException( "Semantics getCode is undefined in ThisAssignment.");
		//return null;
	}

}
