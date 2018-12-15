package util;

public class Dictionary {
	private boolean valid = false;
	public Dictionary()
	{
		////System.out.println("New dictionary created");
	}
	
	private char getType(char c)
	{
		if(c == 's') return 's';
		if(c == 'g') return 'g';
		if(c == 'F' ||c == 'J' ||c == '7' ||c == 'L' ) return 'r';
		if(c == '|' ||c == '-' ) return '-';
		if(c == ' ' ) return ' ';
		return c;
	}
	
	private int getDictionaryIntVal(char c)
	{
		if(c == 's') return 5;
		if(c == 'g') return 6;
		if(c == 'L' ) return 1;
		if(c == 'F' ) return 2;
		if(c == '7' ) return 3;
		if(c == 'J' ) return 4;
		if(c == '|' ) return 7;		
		if(c == '-' ) return 8;
		if(c == ' ' ) return 0;
		return c;
	}
	
	public int getRotationRight(char current, char wanted) {
		if(getType(current) != getType(wanted)) return -1;
		if(getType(current) == 'r')
		{
			int a = getDictionaryIntVal(current);
			int b = getDictionaryIntVal(wanted);
			////System.out.println("current:" + current + "\nwanted:" + wanted +"\nrotation: " +  (4-(a-b))%4 +"\n------");
			return (4-(a-b))%4;
		}
		if(getType(current) == '-')
		{
			if(current != wanted) return 1;
		}
		return 0;
	}
	public boolean validate(char c1,char tryToConnect,char dir)
	{
		////System.out.println("validate: validate("+c1+","+tryToConnect+","+dir+")");
		switch(c1)
		{
			case ' ':
				break;
			case 's':
				NodeS n01 = new NodeS();
				if(dir == 'u') 	valid = n01.up(tryToConnect);
				else if(dir == 'd') valid = n01.down(tryToConnect);
				else if(dir == 'l') valid = n01.left(tryToConnect);
				else if(dir == 'r') valid = n01.right(tryToConnect);
				break;
			case 'g':
				NodeG n02 = new NodeG();
				if(dir == 'u') 	valid = n02.up(tryToConnect);
				else if(dir == 'd') valid = n02.down(tryToConnect);
				else if(dir == 'l') valid = n02.left(tryToConnect);
				else if(dir == 'r') valid = n02.right(tryToConnect);
				break;
			case 'L':
				NodeL n = new NodeL();
				if(dir == 'u') 	valid = n.up(tryToConnect);
				else if(dir == 'd') valid = n.down(tryToConnect);
				else if(dir == 'l') valid = n.left(tryToConnect);
				else if(dir == 'r') valid = n.right(tryToConnect);
				break;
			case 'J':
				NodeJ n2 = new NodeJ();
				if(dir == 'u') 	valid = n2.up(tryToConnect);
				else if(dir == 'd') valid = n2.down(tryToConnect);
				else if(dir == 'l') valid = n2.left(tryToConnect);
				else if(dir == 'r') valid = n2.right(tryToConnect);
				break;
			case '7':
				Node7 n3 =new Node7();
				if(dir == 'u') 	valid = n3.up(tryToConnect);
				else if(dir == 'd') valid = n3.down(tryToConnect);
				else if(dir == 'l') valid = n3.left(tryToConnect);
				else if(dir == 'r') valid = n3.right(tryToConnect);
				break;
			case 'F':
				NodeF n4 = new NodeF();
				if(dir == 'u') 	valid = n4.up(tryToConnect);
				else if(dir == 'd') valid = n4.down(tryToConnect);
				else if(dir == 'l') valid = n4.left(tryToConnect);
				else if(dir == 'r') valid = n4.right(tryToConnect);
				break;
			case '-':
				NodeDsash n11 = new NodeDsash();
				if(dir == 'u') 	valid = n11.up(tryToConnect);
				else if(dir == 'd') valid = n11.down(tryToConnect);
				else if(dir == 'l') valid = n11.left(tryToConnect);
				else if(dir == 'r') valid = n11.right(tryToConnect);
				break;
			case '|':
				NodeUpDsash n12 = new NodeUpDsash() ;
				if(dir == 'u') 	valid = n12.up(tryToConnect);
				else if(dir == 'd') valid = n12.down(tryToConnect);
				else if(dir == 'l') valid = n12.left(tryToConnect);
				else if(dir == 'r') valid = n12.right(tryToConnect);
				break;
		}
		////System.out.println("Vlidated: " + valid);
		return valid;
	}


	private class Node
	{
		public Node()
		{
			
		}
		public boolean up(char tryToConnect)
		{
			if(tryToConnect == 'g'|| tryToConnect == 's' ||tryToConnect == '7' || tryToConnect == 'F'|| tryToConnect == '|' )
				return true;
			return false;
		}
		public boolean right(char tryToConnect)
		{
			if(tryToConnect == 'g'|| tryToConnect == 's' ||tryToConnect == '7' || tryToConnect == '-'|| tryToConnect == 'J' )
				return true;
			return false;
		}
		public boolean down(char tryToConnect)
		{
			if(tryToConnect == 'g'|| tryToConnect == 's' ||tryToConnect == 'J' || tryToConnect == 'L'|| tryToConnect == '|' )
				return true;
			return false;
		}
		public boolean left(char tryToConnect)
		{
			if(tryToConnect == 'g'|| tryToConnect == 's' ||tryToConnect == 'L' || tryToConnect == 'F'|| tryToConnect == '-' )
				return true;
			return false;
		}
	}
	private class NodeL extends Node
	{
		@Override
		public boolean left(char tryToConnect)
		{
			return false;
		}
		@Override
		public boolean down(char tryToConnect)
		{
			return false;
		}
	}
	private class NodeF extends Node
	{
		@Override
		public boolean up(char tryToConnect)
		{
			return false;
		}
		@Override
		public boolean left(char tryToConnect)
		{
			return false;
		}
	}
	private class NodeJ extends Node
	{
		@Override
		public boolean right(char tryToConnect)
		{
			return false;
		}
		@Override
		public boolean down(char tryToConnect)
		{
			return false;
		}
	}
	private class Node7 extends Node
	{
		@Override
		public boolean right(char tryToConnect)
		{
			return false;
		}
		@Override
		public boolean up(char tryToConnect)
		{
			return false;
		}
	}
	
	private class NodeDsash extends Node
	{
		@Override
		public boolean up(char tryToConnect)
		{
			return false;
		}
		@Override
		public boolean down(char tryToConnect)
		{
			return false;
		}
	}
	private class NodeUpDsash extends Node
	{
		@Override
		public boolean right(char tryToConnect)
		{
			return false;
		}
		@Override
		public boolean left(char tryToConnect)
		{
			return false;
		}
	}
	private class NodeS extends Node
	{
	}
	private class NodeG extends Node
	{
	}

	public static void main(String[]args)
	{
		Dictionary dic = new Dictionary();
		//System.out.println(dic.validate('s', 'L', 'd'));
		//System.out.println(dic.validate('s', '7', 'd'));;

	}
	
}




