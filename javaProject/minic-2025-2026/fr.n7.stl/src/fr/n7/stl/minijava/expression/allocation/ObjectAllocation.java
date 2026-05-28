package fr.n7.stl.minijava.expression.allocation;

import java.util.Iterator;
import java.util.List;

import debug.Debugger;
import fr.n7.stl.minic.ast.SemanticsUndefinedException;
import fr.n7.stl.minic.ast.expression.accessible.AccessibleExpression;
import fr.n7.stl.minic.ast.expression.assignable.AssignableExpression;
import fr.n7.stl.minic.ast.scope.Declaration;
import fr.n7.stl.minic.ast.scope.HierarchicalScope;
import fr.n7.stl.minic.ast.type.Type;
import fr.n7.stl.minijava.ast.type.ClassType;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.TAMFactory;

public class ObjectAllocation  implements AccessibleExpression, AssignableExpression {
	
	protected String name;
	
	protected List<AccessibleExpression> arguments;

	public ObjectAllocation(String _name, List<AccessibleExpression> _arguments) {
		this.name = _name;
		this.arguments = _arguments;
	}

	@Override
	public boolean collectAndPartialResolve(HierarchicalScope<Declaration> _scope) {
		//Vérifier que la classe existe
		Debugger.print("------------------------\n" + _scope.toString() + "\n-----------------------");
		Debugger.print("ObjectAllocation : " + name + " " + _scope.knows(name));
		boolean ok = true;
		for (AccessibleExpression e : arguments) {
			ok &= e.collectAndPartialResolve(_scope);
		}
	
		return ok;
			

		
	}

	@Override
	public boolean completeResolve(HierarchicalScope<Declaration> _scope) {
		
		if (_scope.knows(name)) {
			boolean ok = true;
			for (AccessibleExpression e : arguments) {
				ok &= e.completeResolve(_scope);
			}
			Debugger.print(">>>>>>>>>>>>>>>>><<Resolve ObjectAllocation : \n" + _scope.get(name).getClass());
			Debugger.print("Resolve ObjectAllocation : " + ok);
			return ok;
			//Collect sur laes arg
		} else {
			Debugger.print("Resolve ObjectAllocation : -false-");
			return false;
		}


		
	
	}

	@Override
	public Type getType() {
		// TODO Auto-generated method stub
		return new ClassType(name);
	}

	@Override
	public Fragment getCode(TAMFactory _factory) {
		// TODO Auto-generated method stub
		
		throw new SemanticsUndefinedException( "Semantics getCode is undefined in ObjectAllocation.");
		//return null;
	}
	
	@Override
	public String toString() {
		String image = "";
		image += "new " + this.name + "( ";
		Iterator<AccessibleExpression> iterator = this.arguments.iterator();
		if (iterator.hasNext()) {
			AccessibleExpression argument = iterator.next();
			image += argument;
			while (iterator.hasNext()) {
				 argument = iterator.next();
				 image += " ," + argument;
			}
		}
		image += ")";
		return image;
	}

}
