//  SUPER. Turn an integer into a string of Unicode superscript digits, with an
//  optional minus sign.

class Super
{

//  DIGITS. Return a string of superscript digits that represents NUMBER.

  public static String digits(int number)
  {
    if (number < 0)
    {
      return "⁻" + digiting(number);
    }
    else if (number > 0)
    {
      return digiting(- number);
    }
    else
    {
      return "⁰";
    }
  }

//  DIGITING. Do all the work for DIGITS. NUMBER is always negative, because in
//  2's complement arithmetic, there is one more negative integer (−2147483648)
//  than there are positive integers. See:
//
//  James R. Low. "A Short Note on Scanning Integers."  SIGPLAN Notices. Volume
//  14. Number 1. January 1979. Pages 55–56.

  private static String digiting(int number)
  {
    if (number == 0)
    {
      return "";
    }
    else
    {
      return digiting(number / 10) + "⁰¹²³⁴⁵⁶⁷⁸⁹".charAt(-(number % 10));
    }
  }
}


class Poly
{
	private class Term
	{
		private int coef;
		private int expo;
		private Term next;

		private Term (int coef, int expo, Term next)
		{
			this.coef = coef;
			this.expo = expo;
			this.next = next;
		}
	}

	private Term head;

	public Poly()
	{
		head = new Term (0, -1, null);
		head.next = head;
	}

	public Poly add(int coef, int expo)
	{
		Term left = head;
		Term right = left.next;

		if (expo < 0)
		{
			throw new IllegalArgumentException("Can't add negative power terms.");
		}
		else
		{
			while (true)
			{
				if (right.expo > expo)
				{
					left = right;
					right = left.next;
				}
				else if (right.expo == expo)
				{
					right.coef += coef;
					if (right.coef == 0)
					{
						left.next = right.next;
					}
					break;
				}
				else if (right.expo < expo)
				{
					Term temp = new Term(coef, expo, right);
					left.next = temp;
					break;
				}
			}
		}
		return this;
	}

	public Poly minus()
	{
		Term tempNew = head.next;
		Poly polyMinus = new Poly();
		while (tempNew != head)
		{
			polyMinus = polyMinus.add(tempNew.coef * -1, tempNew.expo);
			tempNew = tempNew.next;
		}
		return polyMinus;
	}

	public Poly plus(Poly other)
	{
		Poly polyThis = this;
		Term tempThis = head.next;
		Poly polyPlus = new Poly();
		Term tempPlus = other.head.next;
		while (tempPlus.expo != -1)
		{
			polyPlus = polyPlus.add(tempPlus.coef, tempPlus.expo);
			tempPlus = tempPlus.next;
		}

		while (tempThis != head)
		{
			polyPlus = polyPlus.add(tempThis.coef, tempThis.expo);
			tempThis = tempThis.next;
		}

		return polyPlus;
	}

	public String toString()
	{
		Term tempPrint = head.next;
		String polygo = "";

		if (head.next == head)
		{
			return ("0");
		}
		else
		{
			while (tempPrint != head)
			{
				if (tempPrint.next != head)
				{
					polygo += (tempPrint.coef + " x" + Super.digits(tempPrint.expo) + " + ");
				}
				else
				{
					polygo += (tempPrint.coef + " x" + Super.digits(tempPrint.expo));
				}
				tempPrint = tempPrint.next;
			}
			return polygo;
		}

	}
}

class PiningForTheFjords  
{  
  public static void main(String [] args)  
  {  
    Poly p1 = new Poly().add(1, 3).add(1, 1).add(1, 2);  
    Poly p2 = new Poly().add(2, 1).add(3, 2);  
    Poly p3 = p2.minus();  
  
    System.out.println(p1);           //  1 x³ + 1 x² + 1 x¹  
    System.out.println(p2);           //  3 x² + 2 x¹ 
    System.out.println(p3);           //  -3 x² + -2 x¹

    System.out.println(p1.plus(p2));  //  1 x³ + 4 x² + 3 x¹
    System.out.println(p1.plus(p3));  //  1 x³ + -2 x² + -1 x¹

    Poly p4 = p1.minus();
    Poly p5 = p4.plus(p3);

    System.out.println(p1.plus(p4));  //  0
    System.out.println(p5);           //  -1 x³ + -4 x² + -3 x¹
    Poly exception = p4.add(1, -1);   //  Throw an exception
	}
}

//--------------------------Outcome------------------------------
//1 x³ + 1 x² + 1 x¹
//3 x² + 2 x¹
//-3 x² + -2 x¹
//1 x³ + 4 x² + 3 x¹
//1 x³ + -2 x² + -1 x¹
//0
//-1 x³ + -4 x² + -3 x¹
//Exception in thread "main" java.lang.IllegalArgumentException: Can't add negative power terms.
//	at Poly.add(project3.java:77)
//	at PiningForTheFjords.main(project3.java:191)



























