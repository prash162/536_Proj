import java.util.*;

public class SymTable {
	private List<HashMap<String,Sym>> SymbolTable;
	
	public SymTable(){
		HashMap<String,Sym> Map = new HashMap<String,Sym>();
		SymbolTable = new LinkedList<HashMap<String,Sym>>();
		SymbolTable.add(Map);
		}
	
	public void addDecl(String name,Sym sym) throws DuplicateSymException,EmptySymTableException{
	// CHECK IF LIST IS EMPTY
			if(!SymbolTable.isEmpty())
			{
				//CHECK IF EITHER OR BOTH ARGUMENTS TO FUNCTION ARE NULL
				if( (name != null)  && (sym != null) && (sym.getType() != null)){

						//CHECK IF DUPLICATE KEYS IN FIRST HASHMAP IN LIST
							if(!((LinkedList<HashMap<String,Sym>>)SymbolTable).getFirst().containsKey(name)){
									((LinkedList<HashMap<String,Sym>>)SymbolTable).getFirst().put(name, sym);
								}
							else
									throw(new DuplicateSymException());
											
					}				
					else					
						throw(new NullPointerException());
					
			}
			else
				throw(new EmptySymTableException());
	}
	
	public void addScope(){
		HashMap<String,Sym> Map = new HashMap<String,Sym>();
		((LinkedList<HashMap<String,Sym>>)SymbolTable).addFirst(Map);
	}
	
	public Sym lookupLocal(String name){
		if(((LinkedList<HashMap<String,Sym>>)SymbolTable).isEmpty()){
			return null;
		}
		else{
			if(!((LinkedList<HashMap<String,Sym>>)SymbolTable).getFirst().containsKey(name))
					return null;
			else
					return ((LinkedList<HashMap<String,Sym>>)SymbolTable).getFirst().get(name);
		}
	}
	
	public Sym lookupGlobal(String name){
		Iterator<HashMap<String,Sym>> it = SymbolTable.iterator();
		while (it.hasNext()) {
			HashMap<String, Sym> hashMap = (HashMap<String, Sym>) it.next();
				if(hashMap.containsKey(name))
					return hashMap.get(name);
		}
		return null;
	}
	
	public void removeScope() throws EmptySymTableException{
			if(!SymbolTable.isEmpty())
				((LinkedList<HashMap<String,Sym>>)SymbolTable).remove();
			else
				throw(new EmptySymTableException());
	}
	
	public void print(){
		System.out.println("\nSym Table\n");
		Iterator<HashMap<String,Sym>> it = SymbolTable.iterator();
		while (it.hasNext()) {
			HashMap<String, Sym> hashMap = (HashMap<String, Sym>) it.next();
			System.out.println(hashMap.toString() + "\n");
		}
	}
	
}
