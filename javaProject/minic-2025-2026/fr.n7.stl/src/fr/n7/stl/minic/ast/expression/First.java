/**
 * 
 */
package fr.n7.stl.minic.ast.expression;

import debug.Debugger;
import fr.n7.stl.minic.ast.SemanticsUndefinedException;
import fr.n7.stl.minic.ast.expression.accessible.AccessibleExpression;
import fr.n7.stl.minic.ast.scope.Declaration;
import fr.n7.stl.minic.ast.scope.HierarchicalScope;
import fr.n7.stl.minic.ast.type.AtomicType;
import fr.n7.stl.minic.ast.type.CoupleType;
import fr.n7.stl.minic.ast.type.NamedType;
import fr.n7.stl.minic.ast.type.Type;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.TAMFactory;

/**
 * Abstract Syntax Tree node for an expression extracting the first component in a couple.
 * @author Marc Pantel
 *
 */
public class First implements AccessibleExpression {

	/**
	 * AST node for the expression whose value must whose first element is extracted by the expression.
	 */
	protected AccessibleExpression target;

	/**
	 * Builds an Abstract Syntax Tree node for an expression extracting the first component of a couple.
	 * @param _target : AST node for the expression whose value must whose first element is extracted by the expression.
	 */
	public First(AccessibleExpression _target) {
		this.target = _target;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "(fst" + this.target + ")";
	}
	
	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.expression.Expression#collect(fr.n7.stl.block.ast.scope.HierarchicalScope)
	 */
	@Override
	public boolean collectAndPartialResolve(HierarchicalScope<Declaration> _scope) {
		
		return this.target.collectAndPartialResolve(_scope);
		
		//MODIFIE
		//throw new SemanticsUndefinedException("Semantics collect undefined in First.");
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.expression.Expression#resolve(fr.n7.stl.block.ast.scope.HierarchicalScope)
	 */
	@Override
	public boolean completeResolve(HierarchicalScope<Declaration> _scope) {
		
		return this.target.completeResolve(_scope);
		
		//MODIFIE
		//throw new SemanticsUndefinedException("Semantics resolve undefined in First.");
	}
	
	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Expression#getType()
	 */
	@Override
	public Type getType() {
		
		Type targetType = target.getType();
		if (targetType instanceof NamedType t) {
			targetType = t.getType();
		}
		if (targetType instanceof CoupleType c) {
			return c.getFirst();
		} else {
			return AtomicType.ErrorType;
		}
		
		//MODIFIE
		//throw new SemanticsUndefinedException("Semantics getType undefined in First.");
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Expression#getCode(fr.n7.stl.tam.ast.TAMFactory)
	 */
	@Override
	public Fragment getCode(TAMFactory _factory) {
		
		Fragment result = target.getCode(_factory);
		
		CoupleType type;
		Type typet = this.target.getType();
		if (typet instanceof NamedType t) {
			type = (CoupleType) t.getType();
		}
		else type = (CoupleType) typet;
	
		result.add(_factory.createPop(0, type.getSecond().length()));
		
		return result;
		//MODIFIE
		//throw new SemanticsUndefinedException("Semantics getCode undefined in First.");
	}

}
