
public class P1 {

	public static void main(String[] args) {
			SymTable table = new SymTable();
			Sym var = new Sym("Variables");	
			Sym empty = new Sym(null); // added to check if sym is null condition
			
			//Check empty table i.e while removing scope check if the list is empty
			//EmptySymTableException only thrown when global scope is removes and then removeScope is called again
			System.out.println(" Empty symTable exception Test: EXPECTED print Empty Table Found");
			System.out.println("========================");
			try {
				table.removeScope();
				table.removeScope();
			} catch (EmptySymTableException e) {
				System.out.println("Empty Table Found!!");
			}
			System.out.println("========================");
			
			System.out.println();
			System.out.println();
			
			table.addScope(); //add a scope to front of map
			
			System.out.println("Null args Test: EXPECTED: Print null arguments found twice");
			System.out.println("========================");
			
				try {
					table.addDecl("a",var);
				} catch (DuplicateSymException e1) {
					System.out.println("Duplicate Found!!");
				} catch (EmptySymTableException e1) {
					System.out.println("Empty Table!!");
				} catch(NullPointerException e1){
					System.out.println("One or both arguments is/are null ");
				}
				
				
				//TRY to add null sym
				try {
					table.addDecl("emp",empty);
				} catch (DuplicateSymException e1) {
					System.out.println("Duplicate Found!!");
				} catch (EmptySymTableException e1) {
					System.out.println("Empty Table!!");
				} catch(NullPointerException e1){
					System.out.println("One or both arguments is/are null for key emp ");
				}
				
				//Try to add null string key
				try {
					table.addDecl(null,var);
				} catch (DuplicateSymException e1) {
					System.out.println("Duplicate Found!!");
				} catch (EmptySymTableException e1) {
					System.out.println("Empty Table!!");
				} catch(NullPointerException e1){
					System.out.println("One or both arguments is/are null for key null");
				}
				System.out.println("========================");
				
				System.out.println();
				System.out.println();
					
				table.addScope();
				
				String[] syms= new String[5];
				syms[0] = "a";
				syms[1] = "b";
				syms[2] = "c";
				syms[3] = "a";// is duplicate key will trigger duplicate sym exception
				syms[4] = "d"; 
				
				System.out.println("Duplicate Key Test: EXPECTED:Will Find one duplicate key for key = a");
				System.out.println("========================");
				for(int i=0; i<5 ; i++){
					try {
						table.addDecl(syms[i],var);
					} catch (DuplicateSymException e1) {
						System.out.println("Duplicate Found!! for key " +syms[i]);
					} catch (EmptySymTableException e1) {
						System.out.println("Empty Table!!");
					} catch(NullPointerException e1){
						System.out.println("One or both arguments is/are null "+ syms[i]);
					}	

				}
				
				System.out.println("========================");
				
				System.out.println();
				System.out.println();
				
				
				table.print(); //should have two scopes 
				
				
				System.out.println("Lookup Local test: EXPECTED: key a found,key z not found,null on lookup (no scope)");
				System.out.println("========================");
				
				String[] findLoc = new String[2];
				findLoc[0]="a";
				findLoc[1]="z";//should return null on local lookup
				
				for(int i=0;i<2;i++){
					if (table.lookupLocal(findLoc[i]) != null)
						System.out.println("found "+ findLoc[i]+" in first scope of table");
					else
						System.out.println("did not find"+ findLoc[i]+" in first scope of table");
				}
				
				try {
					table.removeScope();
					table.removeScope();
				} catch (EmptySymTableException e) {
					System.out.println("Empty Table!!");
				}
				
					
		   //NOw No scope available try to lookupLocal should return null
				if(table.lookupLocal(findLoc[0])==null)
					System.out.println("No Scope Available: empty table");
				else
					System.out.println("Problem: scope should have been empty");

				System.out.println("========================");
				
				System.out.println();
				System.out.println();
				
				
				
				//add scope and some keys
				table.addScope();
				syms[3] = "e"; // was a duplicate key before so changed to unique key
				for(int i=0; i<5 ; i++){
					try {
						table.addDecl(syms[i],var);
					} catch (DuplicateSymException e1) {
						System.out.println("Duplicate Found!! for key " +syms[i]);
					} catch (EmptySymTableException e1) {
						System.out.println("Empty Table!!");
					} catch(NullPointerException e1){
						System.out.println("One or both arguments is/are null "+ syms[i]);
					}	

				}
				
				
				table.addScope();
				
				
					try {
						table.addDecl("f",var);
					} catch (DuplicateSymException e1) {
						System.out.println("Duplicate Found!! for key f");
					} catch (EmptySymTableException e1) {
						System.out.println("Empty Table!!");
					} catch(NullPointerException e1){
						System.out.println("One or both arguments is/are null while adding f ");
					}	
					
					//Global Lookup Test
					System.out.println("Global Lookup test: EXPECTED : key =a found but null should be returned for key =y");
					System.out.println("========================");			
					
					String[] globLook= new String[2];
					globLook[0]= "a"; //will return the value
					globLook[1]= "y"; // Will return null
				
				
					for(int i=0;i<2;i++){
						if(table.lookupGlobal(globLook[i])==null)
							System.out.println(globLook[i] + " Not found in global scope");
						else
							System.out.println(globLook[i] + " found in global scope");
					}
					
					System.out.println("========================");
					
					System.out.println();
					System.out.println();
					
					
					table.print();
	}			
}
