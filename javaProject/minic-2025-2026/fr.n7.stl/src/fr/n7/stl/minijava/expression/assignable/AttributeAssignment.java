package fr.n7.stl.minijava.expression.assignable;


import fr.n7.stl.minic.ast.SemanticsUndefinedException;
import fr.n7.stl.minic.ast.expression.accessible.BinaryOperator;
import fr.n7.stl.minic.ast.expression.assignable.AssignableExpression;
import fr.n7.stl.minic.ast.scope.Declaration;
import fr.n7.stl.minic.ast.scope.HierarchicalScope;
import fr.n7.stl.minic.ast.type.Type;
import fr.n7.stl.minijava.expression.AbstractAttribute;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.TAMFactory;
import fr.n7.stl.tam.ast.TAMInstruction;

public class AttributeAssignment extends AbstractAttribute<AssignableExpression> implements AssignableExpression {

	public AttributeAssignment(AssignableExpression _object, String _name) {
		super( _object, _name);
	}

	@Override
	public Fragment getCode(TAMFactory _factory) {

		//CHARGER L'ADRESSE DE L'INSTANCE DE LA CLASSE ?
		Fragment f = this.object.getCode(_factory);
		//CHARGER LA POSITION DE L'ATTRIBUT ?
		f.add(_factory.createLoadL(this.attribute.getPos()));
		//ADDITIONNER LES DEUX
		f.add(TAMFactory.createBinaryOperator(BinaryOperator.Add));

		return f;
		//throw new SemanticsUndefinedException( "Semantics getCode is undefined in AttributeAssignment.");
		//return null;
	}

}
