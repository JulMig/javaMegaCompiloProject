/**
 * 
 */
package fr.n7.stl.minic.ast.instruction;

import java.util.Optional;

import fr.n7.stl.minic.ast.Block;
import fr.n7.stl.minic.ast.SemanticsUndefinedException;
import fr.n7.stl.minic.ast.expression.Expression;
import fr.n7.stl.minic.ast.instruction.declaration.FunctionDeclaration;
import fr.n7.stl.minic.ast.scope.Declaration;
import fr.n7.stl.minic.ast.scope.HierarchicalScope;
import fr.n7.stl.minic.ast.type.AtomicType;
import fr.n7.stl.minic.ast.type.Type;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Register;
import fr.n7.stl.tam.ast.TAMFactory;

/**
 * Implementation of the Abstract Syntax Tree node for a conditional instruction.
 * @author Marc Pantel
 *
 */
public class Conditional implements Instruction {

	protected Expression condition;
	protected Block thenBranch;
	protected Block elseBranch;

	public Conditional(Expression _condition, Block _then, Block _else) {
		this.condition = _condition;
		this.thenBranch = _then;
		this.elseBranch = _else;
	}

	public Conditional(Expression _condition, Block _then) {
		this.condition = _condition;
		this.thenBranch = _then;
		this.elseBranch = null;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "if (" + this.condition + " )" + this.thenBranch + ((this.elseBranch != null)?(" else " + this.elseBranch):"");
	}
	
	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.instruction.Instruction#collect(fr.n7.stl.block.ast.scope.Scope)
	 */
	@Override
	public boolean collectAndPartialResolve(HierarchicalScope<Declaration> _scope) {
		
		boolean ok = true;
		
		ok &= this.condition.collectAndPartialResolve(_scope);
		ok &= this.thenBranch.collectAndPartialResolve(_scope);
		if (this.elseBranch != null)
			ok &= this.elseBranch.collectAndPartialResolve(_scope);
		
		return ok;
		
		//MODIFIE
		//throw new SemanticsUndefinedException( "Semantics collect is undefined in Conditional.");
	}
	
	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.instruction.Instruction#collect(fr.n7.stl.block.ast.scope.Scope)
	 */
	@Override
	public boolean collectAndPartialResolve(HierarchicalScope<Declaration> _scope, FunctionDeclaration _container) {
		
		throw new SemanticsUndefinedException( "Semantics collect is undefined in Conditional.");
	}
	
	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.instruction.Instruction#resolve(fr.n7.stl.block.ast.scope.Scope)
	 */
	@Override
	public boolean completeResolve(HierarchicalScope<Declaration> _scope) {

		boolean ok = true;
		
		ok &= this.condition.completeResolve(_scope);
		ok &= this.thenBranch.completeResolve(_scope);
		if (this.elseBranch != null)
			ok &= this.elseBranch.completeResolve(_scope);
		
		return ok;
		
		//MODIFIE
		//throw new SemanticsUndefinedException( "Semantics resolve is undefined in Conditional.");
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Instruction#checkType()
	 */
	@Override
	public boolean checkType() {
		Type condType = condition.getType();
		boolean check = condType.compatibleWith(AtomicType.BooleanType);
		check &= thenBranch.checkType();
		if (elseBranch != null) check &= elseBranch.checkType();
		
		return check;
		
		//MODIFIE
		//throw new SemanticsUndefinedException( "Semantics checkType is undefined in Conditional.");
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Instruction#allocateMemory(fr.n7.stl.tam.ast.Register, int)
	 */
	@Override
	public int allocateMemory(Register _register, int _offset) {
		
		thenBranch.allocateMemory(_register, _offset);
		if (elseBranch != null )elseBranch.allocateMemory(_register, _offset);
		
		return _offset;
		//MODIFIE
		//throw new SemanticsUndefinedException( "Semantics allocateMemory is undefined in Conditional.");
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Instruction#getCode(fr.n7.stl.tam.ast.TAMFactory)
	 */
	@Override
	public Fragment getCode(TAMFactory _factory) {
		
		String etq_sinon = "sinon_";
		String etq_fin_si = "fin_si_";
		
		int num_label = _factory.createLabelNumber();

		//Code de la condition
		Fragment result = condition.getCode(_factory);
		
		//Saut à la fin ou branche sinon
		if (elseBranch != null)
		{
			result.add(_factory.createJumpIf(etq_sinon + num_label, 0));
		}
		else 
		{
			result.add(_factory.createJumpIf(etq_fin_si + num_label, 0));
		}
		
		//Code du if
		result.append(thenBranch.getCode(_factory));
		if (elseBranch != null)
		{
			result.add(_factory.createJump(etq_fin_si + num_label));
		//Code du sinon
			//ETIQUETTE sinon_n
			Fragment else_frag = elseBranch.getCode(_factory);
			else_frag.addPrefix(etq_sinon + num_label);
			
			result.append(else_frag);
		}
		//ETIQUETTE fin_si_n
		result.addSuffix(etq_fin_si + num_label);
		
		
		return result;
		//MODIFIE
		//throw new SemanticsUndefinedException( "Semantics getCode is undefined in Conditional.");
	}

}
