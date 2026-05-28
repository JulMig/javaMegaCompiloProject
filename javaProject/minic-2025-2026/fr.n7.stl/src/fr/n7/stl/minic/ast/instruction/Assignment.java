/**
 * 
 */
package fr.n7.stl.minic.ast.instruction;

import debug.Debugger;
import fr.n7.stl.minic.ast.SemanticsUndefinedException;
import fr.n7.stl.minic.ast.expression.Expression;
import fr.n7.stl.minic.ast.expression.assignable.AssignableExpression;
import fr.n7.stl.minic.ast.instruction.declaration.FunctionDeclaration;
import fr.n7.stl.minic.ast.scope.Declaration;
import fr.n7.stl.minic.ast.scope.HierarchicalScope;
import fr.n7.stl.minic.ast.type.Type;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Register;
import fr.n7.stl.tam.ast.TAMFactory;

/**
 * Implementation of the Abstract Syntax Tree node for an array type.
 * @author Marc Pantel
 *
 */
public class Assignment implements Instruction, Expression {

	protected Expression value;
	protected AssignableExpression assignable;

	/**
	 * Create an assignment instruction implementation from the assignable expression
	 * and the assigned value.
	 * @param _assignable Expression that can be assigned a value.
	 * @param _value Value assigned to the expression.
	 */
	public Assignment(AssignableExpression _assignable, Expression _value) {
		this.assignable = _assignable;
		this.value = _value;
		/* This attribute will be assigned to the appropriate value by the resolve action */
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.assignable + " = " + this.value.toString() + ";\n";
	}
	
	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.instruction.Instruction#collect(fr.n7.stl.block.ast.scope.HierarchicalScope)
	 */
	@Override
	public boolean collectAndPartialResolve(HierarchicalScope<Declaration> _scope) {
		
		boolean ok = this.value.collectAndPartialResolve(_scope);
		ok &= this.assignable.collectAndPartialResolve(_scope);
		
		return ok;
		
		//MODIFIE
		//throw new SemanticsUndefinedException( "Semantics collect is undefined in Assignment.");
	}
	
	@Override
	public boolean collectAndPartialResolve(HierarchicalScope<Declaration> _scope, FunctionDeclaration _container) {
		
		return this.collectAndPartialResolve(_scope);
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.instruction.Instruction#resolve(fr.n7.stl.block.ast.scope.HierarchicalScope)
	 */
	@Override
	public boolean completeResolve(HierarchicalScope<Declaration> _scope) {
		
		boolean ok = this.value.completeResolve(_scope);
		ok &= this.assignable.completeResolve(_scope);
		
		return ok;
		
		//MODIFIE
		
		//throw new SemanticsUndefinedException( "Semantics resolve is undefined in Assignment.");
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.expression.Expression#getType()
	 */
	@Override
	public Type getType() {
		
		return this.assignable.getType();
		
		//MODIFIE
		//throw new SemanticsUndefinedException( "Semantics getType is undefined in Assignment.");
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Instruction#checkType()
	 */
	@Override
	public boolean checkType() {
		
		Type valType = value.getType();
		
		Type assignType = assignable.getType();
		
		
		return assignType.compatibleWith(valType);
		
		//MODIFIE
		//throw new SemanticsUndefinedException( "Semantics checkType is undefined in Assignment.");
	}
	
	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Instruction#allocateMemory(fr.n7.stl.tam.ast.Register, int)
	 */
	@Override
	public int allocateMemory(Register _register, int _offset) {
		
		return _offset;
		
		//MODIFIE
		//throw new SemanticsUndefinedException( "Semantics allocateMemory is undefined in Assignment.");
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Instruction#getCode(fr.n7.stl.tam.ast.TAMFactory)
	 */
	@Override
	public Fragment getCode(TAMFactory _factory) {
		
		System.out.println("--------------- Assignment : " + assignable.toString() + " = " + value.toString());

		Fragment result = value.getCode(_factory);
		result.append(assignable.getCode(_factory));
		result.add(_factory.createStoreI(assignable.getType().length()));
		//
		
		return result;
		
		//MODIFIE
		//throw new SemanticsUndefinedException( "Semantics getCode is undefined in Assignment.");
	}

}
