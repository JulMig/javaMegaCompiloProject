package fr.n7.stl.minijava.ast.type;

import fr.n7.stl.minic.ast.SemanticsUndefinedException;
import fr.n7.stl.minic.ast.scope.Declaration;
import fr.n7.stl.minic.ast.scope.HierarchicalScope;
import fr.n7.stl.minic.ast.type.Type;

public class ClassType implements Type {
	
	protected String name;

	public ClassType(String _name) {
		this.name = _name;
	}

	@Override
	public boolean equalsTo(Type _other) {
		// TODO Auto-generated method stub
		throw new SemanticsUndefinedException( "compatibleWith equalsTo.");
		//return false;
	}

	@Override
	public boolean compatibleWith(Type _other) {
		// TODO Auto-generated method stub
		
		//System.out.println(_other.getClass() + "               " + this.getClass());
		if (_other instanceof ClassType ct) {
			return ct.name.equals(this.name);
		}
		
		//throw new SemanticsUndefinedException( "compatibleWith ClassType.");
		return false;
	}

	@Override
	public Type merge(Type _other) {
		// TODO Auto-generated method stub
		throw new SemanticsUndefinedException( "compatibleWith merge.");
		//return null;
	}

	@Override
	public int length() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean completeResolve(HierarchicalScope<Declaration> _scope) {
		return _scope.knows(name);
	}
	
	public String toString() {
		// modifie
		return "" + this.name + "";
	}

	public String getName() {
		return name;
	}

}
