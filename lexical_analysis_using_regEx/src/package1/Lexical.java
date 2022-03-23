package package1;


public final class Lexical {//final class static classa benzer bir yapýda calýsýr bunu kullanmamýn sebebi program boyunca degerleri korumak icin icinde static
	//degisken tutabilmek
	//ve instance of object olusturmaya gerek olmadigi icin
	   private static int unaryOp=0;// unary operator(tekli)'+,-,++,--'
	   private static int binaryOp=0;//binary operator(ikili)' %,&,+,-,*,/,|,^' (assignment operatorleri de bu sayiya eklenir)
	   private static int logicalOp=0;//(logical operator pdfde istendigi binaryOp'a(ikili) eklenir) '&&, ||, !'
	   private static int relationalOp=0;//iliþkisel operator '<,<=,>,>=,==,!='
	   private static int assignmentOp=0;// atama operatoru'=,+=,-=,/=,*=,%=,&=,|=,^= '
	   private static int numericOp=0;//+,++,-,--,*,/,%,&,|,^,=,+=,-=,/=,*=,%=,&=,|=,^= binary+unary
	   private static int operandNum=0;//ikililer cift tekli operatorler tek operand alir
	  
	  
	   public static void setUnaryNum() {
		   unaryOp++;
	   }
	   public static int getUnaryNum() {
		 	 return unaryOp;
		}
	   public static void setLogicalNum() {
		   logicalOp++;
	   }
	   public static int getLogicalNum() {
		   return logicalOp;
	   }
	   public static void setRelationalNum() {
		 	 relationalOp++;;
		}
	   public static int getRelationalNum() {
		 	 return relationalOp;
		}
	   public static void setAssignmentNum() {
		 	 assignmentOp++;
		}
	   public static int getAssignmentNum() {
		   return assignmentOp;
	   }
	   public static void setBinaryNum() {
		   binaryOp++;
	   }
	   public static int getBinaryNum() {//pdfde explicitliy belirtilen ikili operatorden arastirmalar sonunda anladigim ikili operatorlere atama operatorlerinin eklenmesi sonucu toplam
		   binaryOp+=assignmentOp;
		   return binaryOp;
	   }
	   public static int getNumericNum() {
		  numericOp=unaryOp+binaryOp;
		  return numericOp;
	   }
	   public static void setOperandNum() {
		   operandNum=binaryOp*2+relationalOp*2+logicalOp*2+unaryOp;
	   }
	   public static int getOperandNum() {
		   setOperandNum();
		   return operandNum;
	   }
	 
	 
	   
	   
		
	}


