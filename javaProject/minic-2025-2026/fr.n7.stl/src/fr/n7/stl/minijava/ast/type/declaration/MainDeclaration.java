package fr.n7.stl.minijava.ast.type.declaration;

import java.util.List;

import debug.Debugger;
import fr.n7.stl.minic.ast.Block;
import fr.n7.stl.minic.ast.SemanticsUndefinedException;
import fr.n7.stl.minic.ast.instruction.Instruction;
import fr.n7.stl.minic.ast.instruction.declaration.FunctionDeclaration;
import fr.n7.stl.minic.ast.scope.Declaration;
import fr.n7.stl.minic.ast.scope.HierarchicalScope;
import fr.n7.stl.tam.ast.Fragment;
import fr.n7.stl.tam.ast.Register;
import fr.n7.stl.tam.ast.TAMFactory;

public class MainDeclaration implements Instruction {
	
	protected String name;
	
	protected List<Declaration> declarations;
	
	protected Block main;

	public MainDeclaration(String _name, List<Declaration> _declarations, Block _main) {
		this.name = _name;
		this.declarations = _declarations;
		this.main = _main;
	}

	@Override
	public boolean collectAndPartialResolve(HierarchicalScope<Declaration> _scope) {
	
		Debugger.print(">>>>>>>> MainDeclaration :  " + name);

		for(Declaration d : declarations) {
			Debugger.print(">>>>>>>> MainDeclaration :  " +_scope.contains(d.getName()) + " " +d.getName());
			if (!_scope.contains(d.getName())) {
				_scope.register(d);
				
			} else {
				return false;
			}
		}

		Debugger.print("-------------------\n"+_scope.toString()+"-------------------\n");
		return this.main.collectAndPartialResolve(_scope);
	}

	@Override
	public boolean collectAndPartialResolve(HierarchicalScope<Declaration> _scope, FunctionDeclaration _container) {
		// TODO Auto-generated method stub
		throw new SemanticsUndefinedException( "Semantics collect and partial resolve is undefined in MainDeclaration.");
		//return false;
	}

	@Override
	public boolean completeResolve(HierarchicalScope<Declaration> _scope) {
		// TODO Auto-generated method stub
	
		boolean ok = this.main.completeResolve(_scope);
		Debugger.print("Resolve MainDeclaration : " + ok);
		for (Declaration d : declarations) {
			ok &= d.getType().completeResolve(_scope);
		}
		//complete resolve sur le type des décla
		//MODIFIE
		return ok;
	}

	@Override
	public boolean checkType() {
		// TODO Auto-generated method stub
		return main.checkType();
	}

	@Override
	public int allocateMemory(Register _register, int _offset) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Fragment getCode(TAMFactory _factory) {
		
		Fragment f = _factory.createFragment();

		//AJOUTER LE CODE DES DECLARTIONS
		System.out.println("//////////////////////");
		System.out.println("Déclaration");
		for(Declaration d : declarations){
			System.out.println(" - " + d.toString());;
		} 
		System.out.println("//////////////////////");

		f.append(main.getCode(_factory));

		return f;
		//throw new SemanticsUndefinedException( "Semantics getCode is undefined in MainDeclaration.");
		//return null;
	}
	
	public String getName() {
		return this.name;
	}
	
	@Override
	public String toString() {
		String image = "";
		image += "public class " + this.name + " ";
		image += "{\n";
		image += "\n";
		for (Declaration uneDeclaration : this.declarations) {
			image += uneDeclaration;
			image += "\n";
		}
		image += "\tpublic static void Main( String[] args) ";
		image += this.main;
		image += "\n";
		image += "}\n";
		return image;
	}

}
