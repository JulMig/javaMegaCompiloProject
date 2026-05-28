package fr.n7.stl.minijava.ast.type.declaration;

import java.util.Iterator;
import java.util.List;

import debug.Debugger;
import fr.n7.stl.minic.ast.Block;
import fr.n7.stl.minic.ast.instruction.declaration.FunctionDeclaration;
import fr.n7.stl.minic.ast.instruction.declaration.ParameterDeclaration;
import fr.n7.stl.minic.ast.scope.Declaration;
import fr.n7.stl.minic.ast.scope.HierarchicalScope;
import fr.n7.stl.minic.ast.scope.SymbolTable;
import fr.n7.stl.minic.ast.type.Type;
import fr.n7.stl.util.Logger;

public class MethodDeclaration  extends ClassElement {
	
	protected boolean concrete;
	
	protected List<ParameterDeclaration> parameters;
	
	protected Block body;
	
	protected Type type;

	protected String label;
	
	public MethodDeclaration(String _name, Type _type, List<ParameterDeclaration> _parameters, Block _body) {
		super( _name );
		this.parameters = _parameters;
		this.body = _body;
		this.concrete = (_body != null);
		this.type = _type;
	}
	
	public MethodDeclaration(String _name, Type _type, List<ParameterDeclaration> _parameters) {
		this( _name, _type, _parameters, null);
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
	
	@Override
	public String toString() {
		String image = "";
		if (! this.concrete) {
			image += "abstract ";
		}
		image += this.accessRight + " " + this.type + " " + this.name + "( ";
		Iterator<ParameterDeclaration> iterator = this.parameters.iterator();
		if (iterator.hasNext()) {
			ParameterDeclaration parameter = iterator.next();
			image += parameter;
			while (iterator.hasNext()) {
				 parameter = iterator.next();
				 image += " ," + parameter;
			}
		}
		image += ")";
		if (this.concrete) {
			image += this.body; 
		} else {
			image += ";";
		}
		return image;
	}

	public Block getBody() {
		return body;
	}

	@Override
	public Type getType() {
		return type;
	}

	public boolean compatibleWith(Type ... types) {
		boolean ok = types.length  == parameters.size();

		for (int i = 0; i  < types.length; i++) {
			ok &= types[i].compatibleWith(parameters.get(i).getType());
		}


		return ok;


	}

	
	public boolean checkType() {

		//return body.checkType();
		return true;

	}

	// rajouté par NOUS !!!
	public boolean collectAndPartialResolve(HierarchicalScope<Declaration> _scope, ClassDeclaration classe) {
			HierarchicalScope<Declaration> _paramScope = new SymbolTable(_scope);
			
			((SymbolTable) _paramScope).enregistre("this", classe);
			System.out.println("OOOOOOOOOOOO - ScopeMéthode :");
			System.out.println(_paramScope.toString());
			
			for (ParameterDeclaration param : this.parameters) {
				if (_paramScope.accepts(param)) {
					_paramScope.register(param);
					// debug
					System.out.println(param.getName() + " registered in parameter scope.");
				} else {
					Logger.error("Parameter : " + this.name + " is already defined.");
              		return false;
				}
			}
			return this.body.collectAndPartialResolve(_paramScope);
      
	}


	public boolean completeResolve(HierarchicalScope<Declaration> _scope) {
		return this.body.completeResolve(_scope);
	}

}
