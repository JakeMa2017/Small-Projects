// Jianqiao Ma 4753162


class NameTable<Base>
{
	private class TreeNode
	{
		private String key;
		private Base value;
		private TreeNode left;
		private TreeNode right;
		private TreeNode(String key, Base value)
		{
			this.key = key;
			this.value = value;
			left = null;
			right = null;
		}
	}

	private class StackNode
	{
		private TreeNode root;
		private StackNode next;
		private StackNode (TreeNode root, StackNode next)
		{
			this.root = root;
			this.next = next;
		}
	}

	private StackNode stackTop;
	private TreeNode treeRoot;
	private TreeNode treeHead;

	private TreeNode result;    // A pointer that point to the node which is found by has(). Declare it
								// outside the method can make it easier for writing get();
	

	public NameTable()
	{
		stackTop = null;
		treeRoot = null;
		treeHead = new TreeNode("", null);  // Every letter's index is greater than an empty string.
	}


// Take in an key and a stack, return the TreeNode with the key inside that stack
	private TreeNode search (String key, StackNode currentStack) 
	{
		TreeNode subtree = currentStack.root;
		while (subtree != null)
		{
			int test = key.compareTo(subtree.key);
			if (test<0)
			{
				subtree = subtree.left;
			}
			else if (test>0)
			{
				subtree = subtree.right;
			}
			else
			{
				return subtree;
			}
		}
		return null;
	}

	public Base get (String key)
	{
		if (has(key))
			return result.value;
		else
			throw new IllegalArgumentException("No such key.");
	}

	public boolean has (String key)
	{
		result = null;
		StackNode finder = stackTop;
		while (finder != null)
		{
			result = search(key, finder);
			if (result != null)
			{
				break;
			}
			else
			{
				finder = finder.next;
			}
		}
		return result != null;
	}

	public boolean isEmpty()
	{
		return stackTop == null;
	}

	public void pop()
	{
		if (isEmpty())
		{
			throw new IllegalStateException("Stack is empty.");
		}
		else
		{
			stackTop = stackTop.next;
		}
	}

	public void push()
	{
		stackTop = new StackNode(null, stackTop);
	}

	public void put (String key, Base value)
	{
		if (stackTop.root != null && search(key, stackTop).key.equals(key))
		{
			throw new IllegalArgumentException("Variable already exists.");
		}
		else
		{
			stackTop.root = treeHead;
			treeHead.right = treeRoot;
			TreeNode treePut = treeHead;
			
			while (treePut != null)
			{
				int test = key.compareTo(treePut.key);

				if (test < 0)
				{
					if (treePut.left == null)
					{
						treePut.left = new TreeNode (key, value);
						break;
					}
					else
					{
						treePut = treePut.left;
					}
				}
				else if (test > 0)
				{
					if (treePut.right == null)
					{
						treePut.right = new TreeNode (key, value);
						break;
					}
					else
					{
						treePut = treePut.right;
					}
				}
			}
			stackTop.root = treeHead.right;
		}
	}
}

// I have added some extra words to make the output clearer.
class ScopesTrial  
{  
  public static void main(String [] args)  
  {  
    NameTable<Integer> table = new NameTable<Integer>();  
    System.out.println(table.isEmpty());  //  Print true.  
  
    table.push();                         //  Enter a scope.  
    System.out.println(table.isEmpty());  //  Print false.  
  
    table.put("a", 1);                    //  Add variable "a".  
    System.out.println(table.has("a") + " has a");   //  Print true.  
  
    table.push();                         //  Enter a scope.  
    table.put("t", 2);                    //  Add variable "t".  
    System.out.println(table.has("a") + " has a");   //  Print true.  
    System.out.println(table.has("t") + " has t");   //  Print true.  
  
    table.push();                         //  Enter a scope.  
    table.put("k", 3);                    //  Add variable "k".  
    System.out.println(table.has("a") + " has a");   //  Print true.  
    System.out.println(table.has("t") + " has t");   //  Print true.  
    System.out.println(table.has("k") + " has k");   //  Print true.  
  
    table.push();                         //  Enter a scope.  
    table.put("e", 4);                    //  Add variable "e".  
    System.out.println(table.has("a") + " has a");   //  Print true.  
    System.out.println(table.has("t") + " has t");   //  Print true.  
    System.out.println(table.has("k") + " has k");   //  Print true.  
    System.out.println(table.has("e") + " has e");   //  Print true.  

    // Test the get() method.
    System.out.println(table.get("a")+ " " +table.get("t")+ " " +table.get("k") + " " +table.get("e"));
  
    table.pop();                          //  Exit a scope.
    System.out.println("Begin poping.");  
    System.out.println(table.has("a") + " has a");   //  Print true.  
    System.out.println(table.has("t") + " has t");   //  Print true.  
    System.out.println(table.has("k") + " has k");   //  Print true.  
    System.out.println(table.has("e") + " has e");   //  Print false.  
  
    table.pop();                          //  Exit a scope.  
    System.out.println(table.has("a") + " has a");   //  Print true.  
    System.out.println(table.has("t") + " has t");   //  Print true.  
    System.out.println(table.has("k") + " has k");   //  Print false.  
    System.out.println(table.has("e") + " has e");   //  Print false.  
  
    table.pop();                          //  Exit a scope.  
    System.out.println(table.has("a") + " has a");   //  Print true.  
    System.out.println(table.has("t") + " has t");   //  Print false.  
    System.out.println(table.has("k") + " has k");   //  Print false.  
    System.out.println(table.has("e") + " has e");   //  Print false.  
  
    table.pop();                          //  Exit a scope.  
    System.out.println(table.has("a") + " has a");   //  Print false.  
    System.out.println(table.has("t") + " has t");   //  Print false.  
    System.out.println(table.has("k") + " has k");   //  Print false.  
    System.out.println(table.has("e") + " has e");   //  Print false.  
  
    System.out.println(table.isEmpty());  //  Print true.  

    // Test adding same key in two different stack.
    System.out.println("Test 1");
    table.push();
    table.put("x", 5);
    System.out.println(table.has("x") + " has x.");
    table.push();
    table.put("x", 5);
    System.out.println(table.has("x") + " hax x.");

    // Test adding same key in the same stack.
    System.out.println("Test 2");
    table.push();
    table.put("y", 6);
    System.out.println(table.has("y") + " has y.");
    table.put("y", 678);

  }  
}

/*-----------------------------Output-----------------------------
true
false
true has a
true has a
true has t
true has a
true has t
true has k
true has a
true has t
true has k
true has e
1 2 3 4
Begin poping.
true has a
true has t
true has k
false has e
true has a
true has t
false has k
false has e
true has a
false has t
false has k
false has e
false has a
false has t
false has k
false has e
true
Test 1
true has x.
true hax x.
Test 2
true has y.
Exception in thread "main" java.lang.IllegalArgumentException: Variable already exists.
	at NameTable.put(project4.java:124)
	at ScopesTrial.main(project4.java:242)
*/


























