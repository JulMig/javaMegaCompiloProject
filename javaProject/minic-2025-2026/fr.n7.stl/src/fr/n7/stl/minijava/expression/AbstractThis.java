package fr.n7.stl.minijava.expression;

import java.util.Optional;

import org.antlr.v4.codegen.model.decl.Decl;

import fr.n7.stl.minic.ast.SemanticsUndefinedException;
import fr.n7.stl.minic.ast.expression.Expression;
import fr.n7.stl.minic.ast.scope.Declaration;
import fr.n7.stl.minic.ast.scope.HierarchicalScope;
import fr.n7.stl.minic.ast.scope.SymbolTable;
import fr.n7.stl.minic.ast.type.Type;
import fr.n7.stl.minijava.ast.type.declaration.ClassDeclaration;
import fr.n7.stl.util.Logger;

public abstract class AbstractThis <ObjectKind extends Expression> implements Expression {

	protected ClassDeclaration content;

	public AbstractThis() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean collectAndPartialResolve(HierarchicalScope<Declaration> _scope) {
		//System.out.println("--------------------------------------------------------------");
		Optional<SymbolTable> scope = ((SymbolTable) _scope).getContext("this");
		System.out.println("ATTENTION (AbstractThis bizarre) !!!!!!!!!!!!!!!!!!!!!!!!");
		System.out.println("Scope collectAndPartialR : (contexte) " + scope.toString());
		if (!scope.isEmpty()) {
			if (scope.get().get("this") instanceof ClassDeclaration cd) {
				if (this.content == null) {content = cd;} 
				return true;
			} else {
				Logger.error("Mauvais 'typage' pour this");
				return false;
			} 
		} else {
			Logger.error("'this' pas dans la scope");
			return false;
		}
		
		//return false;
	}
	
	// RAJOUTE PAR NOUS !!!
	@Override
	public boolean completeResolve(HierarchicalScope<Declaration> _scope) {
		// TODO Auto-generated method stub
		//throw new UnsupportedOperationException("Unimplemented method 'completeResolve'");
		return true;
	}

	@Override
	public Type getType() {
		// TODO Auto-generated method stub
		return content.getType();
		//throw new SemanticsUndefinedException( "AbstractThis getType");
		//return null;
	}
	
	@Override
	public String toString() {
		return "this";
	}
}
