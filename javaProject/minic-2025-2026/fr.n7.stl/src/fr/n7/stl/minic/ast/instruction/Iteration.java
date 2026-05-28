/**
 * 
 */
package fr.n7.stl.minic.ast.instruction;

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
public class Iteration implements Instruction {

	protected Expression condition;
	protected Block body;

	public Iteration(Expression _condition, Block _body) {
		this.condition = _condition;
		this.body = _body;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "while (" + this.condition + " )" + this.body;
	}
	
	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.instruction.Instruction#collect(fr.n7.stl.block.ast.scope.Scope)
	 */
	@Override
	public boolean collectAndPartialResolve(HierarchicalScope<Declaration> _scope) {
		
		boolean ok = this.condition.collectAndPartialResolve(_scope);
		ok &= this.body.collectAndPartialResolve(_scope);
		
		return ok;
		
		//throw new SemanticsUndefinedException( "Semantics collect is undefined in Iteration.");
	}
	
	@Override
	public boolean collectAndPartialResolve(HierarchicalScope<Declaration> _scope, FunctionDeclaration _container) {
		
		boolean ok = this.condition.collectAndPartialResolve(_scope);
		ok &= this.body.collectAndPartialResolve(_scope, _container);
		
		return ok;
		// MODIFIE
		//throw new SemanticsUndefinedException( "Semantics collect is undefined in Iteration.");
	}
	
	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.instruction.Instruction#resolve(fr.n7.stl.block.ast.scope.Scope)
	 */
	@Override
	public boolean completeResolve(HierarchicalScope<Declaration> _scope) {
		
		boolean ok = this.condition.completeResolve(_scope);
		ok &= this.body.completeResolve(_scope);
		
		return ok;
		
		//throw new SemanticsUndefinedException( "Semantics resolve is undefined in Iteration.");
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Instruction#checkType()
	 */
	@Override
	public boolean checkType() {
		
		Type condType = condition.getType();
		boolean check = condType.compatibleWith(AtomicType.BooleanType);
		check &= body.checkType();
		
		return check;
		
		//MODIFIE
		//throw new SemanticsUndefinedException( "Semantics checkType is undefined in Iteration.");
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Instruction#allocateMemory(fr.n7.stl.tam.ast.Register, int)
	 */
	@Override
	public int allocateMemory(Register _register, int _offset) {
		
		body.allocateMemory(_register, _offset);
		return _offset;
		
		//MODIFIE
		//throw new SemanticsUndefinedException( "Semantics allocateMemory is undefined in Iteration.");
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Instruction#getCode(fr.n7.stl.tam.ast.TAMFactory)
	 */
	@Override
	public Fragment getCode(TAMFactory _factory) {
		
		String debut = "debut_tant_que_";
		String fin = "fin_tant_que_";
		
		int label_num = _factory.createLabelNumber();
		
		Fragment result = condition.getCode(_factory);
		//ETIQUETTE DEBUT
		result.addPrefix(debut + label_num);
		result.add(_factory.createJumpIf(fin+label_num, 0));
		result.append(body.getCode(_factory));
		result.add(_factory.createJump(debut+label_num));
		//ETIQUETTE FIN
		result.addSuffix(fin+label_num);
		
		return result;
		
		//MODIFIE
		//throw new SemanticsUndefinedException( "Semantics getCode is undefined in Iteration.");
	}

}
