/**
 * 
 */
package fr.n7.stl.minijava.ast.type.declaration;

import java.util.List;

import debug.Debugger;
import fr.n7.stl.minic.ast.SemanticsUndefinedException;
import fr.n7.stl.minic.ast.instruction.Instruction;
import fr.n7.stl.minic.ast.instruction.declaration.FunctionDeclaration;
import fr.n7.stl.minic.ast.scope.Declaration;
import fr.n7.stl.minic.ast.scope.HierarchicalScope;
import fr.n7.stl.minic.ast.scope.SymbolTable;
import fr.n7.stl.minic.ast.type.Type;
import fr.n7.stl.minijava.ast.type.ClassType;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Register;
import fr.n7.stl.tam.ast.TAMFactory;

/**
 * 
 */
public class ClassDeclaration implements Instruction, Declaration {
	
	protected List<ClassElement> elements;
	
	protected boolean concrete;
	
	protected String name;
	
	protected String ancestor;

	protected HierarchicalScope<Declaration> scope;

	/**
	 * 
	 */
	public ClassDeclaration(boolean _concrete, String _name, String _ancestor, List<ClassElement> _elements) {
		this.concrete = _concrete;
		this.name = _name;
		this.ancestor = _ancestor;
		this.elements = _elements;
	}
	
	/**
	 * 
	 */
	public ClassDeclaration(boolean _concrete, String _name, List<ClassElement> _elements) {
		this( _concrete, _name, null, _elements);
	}

	@Override
	public boolean collectAndPartialResolve(HierarchicalScope<Declaration> _scope) {
		
		Debugger.print(">>>>>>>> ClassDeclaration :" + _scope.contains(name) + "  " + name);

		if (!_scope.contains(name)) {
			_scope.register(this);
			
			//PENSE AU METHODE ET ATTRIBUT QUE J'AI DECIDE DE PAS GERER ICI !!!!
	
 		} else {
			return false;
		}
		/* // Laissé à l'allocation pour le moment
		for(ClassElement c : elements){

			if (c instanceof AttributeDeclaration ad){

			}

		}		*/
		Debugger.print(">>>>>>>> ClassDeclaration : return TRUE");
		return true;
		
		//MODIFIE
		//throw new SemanticsUndefinedException( "Semantics collect is undefined in ClassDeclaration.");
	}

	@Override
	public boolean collectAndPartialResolve(HierarchicalScope<Declaration> _scope, FunctionDeclaration _container) {
		throw new SemanticsUndefinedException( "Semantics resolve is undefined in ClassDeclaration.");
	}

	@Override
	public boolean completeResolve(HierarchicalScope<Declaration> _scope) {

		return true;
		//MODIFIE
		//throw new SemanticsUndefinedException( "Semantics resolve is undefined in ClassDeclaration.");
	}


	/*

	
		protected List<ClassElement> elements;
	
	protected boolean concrete;
	
	protected String name;
	
	protected String ancestor;
	
	*/


	@Override
	public boolean checkType() {

		boolean ok = true;

		for (ClassElement ce : elements) {
			
			if (ce instanceof ConstructorDeclaration cd) {
				ok &= cd.checkType();
			} else if (ce instanceof MethodDeclaration md) {
				ok &= md.checkType();
			}
		}

		System.out.println(ok);

		return ok;
		
		//MODIFIE
		//throw new SemanticsUndefinedException( "Semantics check type is undefined in ClassDeclaration.");
	}

	@Override
	public int allocateMemory(Register _register, int _offset) {
		throw new SemanticsUndefinedException( "Semantics allocation memory is undefined in ClassDeclaration.");
	}

	@Override
	public Fragment getCode(TAMFactory _factory) {
		throw new SemanticsUndefinedException( "Semantics get code is undefined in ClassDeclaration.");
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public Type getType() {
		return new ClassType(name);
	}
	
	@Override
	public String toString() {
		String image = "";
		if (! this.concrete) {
			image += "abstract ";
		}
		image += "class " + this.name + " ";
		if (this.ancestor != null) {
			image += "extends " + this.ancestor + " ";
		}
		image += "{\n";
		for (ClassElement e : this.elements) {
			image += e;
		}
		image += "}\n";
		return image;
	}


	//////////////////////////////////////////FCT UTILITAIRES/////////////////////
	public boolean constructorExist(Type ... types)  {

		boolean ok = false;
		for (ClassElement elem : elements) {
			if (elem instanceof ConstructorDeclaration cd) {

				ok |= cd.compatibleWith(types); 

			}
			
		}

		return ok;
	}

	public boolean getMethods(String name, Type ... types)  {

		boolean ok = false;
		for (ClassElement elem : elements) {
			if (elem instanceof MethodDeclaration cd) {
				if (elem.getName().equals(name)){
					ok |= cd.compatibleWith(types); 
				}

			}
			
		}

		return ok;
	}

}
