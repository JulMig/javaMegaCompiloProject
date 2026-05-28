package fr.n7.stl.minijava.expression;

import org.antlr.v4.codegen.model.decl.Decl;

import debug.Debugger;
import fr.n7.stl.minic.ast.SemanticsUndefinedException;
import fr.n7.stl.minic.ast.expression.Expression;
import fr.n7.stl.minic.ast.scope.Declaration;
import fr.n7.stl.minic.ast.scope.HierarchicalScope;
import fr.n7.stl.minic.ast.type.Type;
import fr.n7.stl.minic.ast.type.declaration.FieldDeclaration;
import fr.n7.stl.minijava.ast.type.ClassType;
import fr.n7.stl.minijava.ast.type.declaration.AttributeDeclaration;
import fr.n7.stl.minijava.ast.type.declaration.ClassDeclaration;
import fr.n7.stl.minijava.ast.type.declaration.ClassElement;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.TAMFactory;

public abstract class AbstractAttribute <ObjectKind extends Expression> implements Expression {
	
	protected ObjectKind object;
	protected String name;
	protected AttributeDeclaration attribute;

	public AbstractAttribute(ObjectKind _object, String _name) {
		this.object = _object;
		this.name = _name;

		System.out.println("NAME :       " + _name + " " + object.getClass());
	}

	@Override
	public boolean collectAndPartialResolve(HierarchicalScope<Declaration> _scope) {
		boolean ok = this.object.collectAndPartialResolve(_scope);

		System.out.println(_scope.toString());
		
		if (_scope.knows(this.object.getType().toString())) {
			Declaration cl = _scope.get(this.object.getType().toString());
			//System.out.println(cl.getClass());
			//System.out.println(cl.getType());
			if (cl instanceof ClassDeclaration c) {
				this.attribute = c.getAttribute(name);
			}
		}
		return ok;
		//MODIFIE
	}

	@Override
	public boolean completeResolve(HierarchicalScope<Declaration> _scope) {

		boolean ok = this.object.completeResolve(_scope);
		return ok;
		//MODIFIE
	}

	@Override
	public Type getType() {
		
		//System.out.println("OBJECT NAME                 :" + object.toString());
		//System.out.println("ATTRIBUTE TYPE                 : " + attribute.getName() + " " + attribute.getType());
		return this.attribute.getType();
		//throw new SemanticsUndefinedException( "AbstractAttribute getType");
		//return null;
	}
	
	@Override
	public String toString() {
		String image = "";
		image += this.object;
		image += ".";
		image += this.name;
		return image;
	}

}
