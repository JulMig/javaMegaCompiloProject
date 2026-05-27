/**
 * 
 */
package fr.n7.stl.minic.ast.expression;

import java.security.InvalidParameterException;
import java.util.Iterator;
import java.util.List;

import fr.n7.stl.minic.ast.SemanticsUndefinedException;
import fr.n7.stl.minic.ast.expression.accessible.AccessibleExpression;
import fr.n7.stl.minic.ast.instruction.declaration.FunctionDeclaration;
import fr.n7.stl.minic.ast.scope.Declaration;
import fr.n7.stl.minic.ast.scope.HierarchicalScope;
import fr.n7.stl.minic.ast.type.FunctionType;
import fr.n7.stl.minic.ast.type.Type;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Register;
import fr.n7.stl.tam.ast.TAMFactory;

/**
 * Abstract Syntax Tree node for a function call expression.
 * @author Marc Pantel
 *
 */
public class FunctionCall implements AccessibleExpression {

	/**
	 * Name of the called function.
	 * TODO : Should be an expression.
	 */
	protected String name;
	
	/**
	 * Declaration of the called function after name resolution.
	 * TODO : Should rely on the VariableUse class.
	 */
	protected FunctionDeclaration function;
	
	/**
	 * List of AST nodes that computes the values of the parameters for the function call.
	 */
	protected List<AccessibleExpression> arguments;
	
	/**
	 * @param _name : Name of the called function.
	 * @param _arguments : List of AST nodes that computes the values of the parameters for the function call.
	 */
	public FunctionCall(String _name, List<AccessibleExpression> _arguments) {
		this.name = _name;
		this.function = null;
		this.arguments = _arguments;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String _result = ((this.function == null)?this.name:this.function) + "( ";
		Iterator<AccessibleExpression> _iter = this.arguments.iterator();
		if (_iter.hasNext()) {
			_result += _iter.next();
		}
		while (_iter.hasNext()) {
			_result += " ," + _iter.next();
		}
		return  _result + ")";
	}
	
	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.expression.Expression#collect(fr.n7.stl.block.ast.scope.HierarchicalScope)
	 */
	@Override
	public boolean collectAndPartialResolve(HierarchicalScope<Declaration> _scope) {
		//System.out.println(_scope.toString());
		//System.out.println(name + " est dedans ? " + Boolean.toString(_scope.knows(name)));
		
		if (_scope.knows(name)) { 
			if (this.function == null) {
				try { 
					//System.out.println("DEBUG !!!");
					this.function = (FunctionDeclaration) _scope.get(name);
					System.out.println(this.function.getName());
				} catch (Exception e) {
					System.err.println("Exception occured : " + name + " is not a function !");;
					return false;
				}  			
			} //else {System.out.println("BONJOUR LE MONDE :!");} 
			boolean ok = true; //this.function.collectAndPartialResolve(_scope);
			for (AccessibleExpression ae : arguments) {
				ok &= ae.collectAndPartialResolve(_scope);
			}
			return ok;
		} else {
			throw new InvalidParameterException("Function : " + name + " is undefined !");
		} 	
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.expression.Expression#resolve(fr.n7.stl.block.ast.scope.HierarchicalScope)
	 */
	@Override
	public boolean completeResolve(HierarchicalScope<Declaration> _scope) {
		boolean ok = true;//this.function.completeResolve(_scope);
		for (AccessibleExpression ae : arguments) {
			ok &= ae.completeResolve(_scope);
		}
		return ok;//throw new SemanticsUndefinedException( "Semantics resolve is undefined in FunctionCall.");
	}
	
	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Expression#getType()
	 */
	@Override
	public Type getType() {
		return this.function.getType();
		//throw new SemanticsUndefinedException( "Semantics getType is undefined in FunctionCall.");
	}

	/* (non-Javadoc)
	 * @see fr.n7.stl.block.ast.Expression#getCode(fr.n7.stl.tam.ast.TAMFactory)
	 */
	@Override
	public Fragment getCode(TAMFactory _factory) {

		Fragment f = _factory.createFragment();

		for (AccessibleExpression a : arguments) {
			f.append(a.getCode(_factory));
		}

		f.add(_factory.createCall(name, Register.LB));

		return f;
		//throw new SemanticsUndefinedException( "Semantics getCode is undefined in FunctionCall.");
	}

}
