package package1;


import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;
import java.io.FileNotFoundException;


public class Program {
    public static void main(String[] args) {
        String content="";//dosya bunu icine okunacak 
        //arsg[0] yerine denenecek dosya ayný isimdeyse java -jar Program.java isim seklinde degilse java -jar Program.java filepath/filename seklinde giris yapilarak 
        //calistirilir
        File f=new File(args[0]);//Dosya komut satýrý parametesi olarak girilir
      String copy1="";
            try{
                Scanner scanner = new Scanner(f);
                while (scanner.hasNext()){
                	copy1=scanner.nextLine();
                    content=content.concat(copy1+"\n");
                 }
                scanner.close();
            }
            catch(NullPointerException e){
                System.out.println("The file name may have been a null string.\nProgram Terminating." + e);
            }
            catch(FileNotFoundException e){
                System.out.println("No file with the name " + args[0]
                        + "\nProgram terminating." + e);
            }
        //olusturulan patternler dosyadan okunmus olan string icinde aranacak taske gore ya delete ya da count islemleri yapilacak
            
         String tmp=content;//dosya iceriginin asil icerigi tutuluyor islemler kopyasi uzerinde yapilacak
        
        Pattern patternMulComm=Pattern.compile("//.*|/\\*((.|\\n)(?!=*/))+\\*/");//"/**/" islem rahatligi icin formatindaki commentler silindi
        Pattern patternSingleComm=Pattern.compile("(?s)/\\*(.)*?\\*/");//ayni sekilde tek "//" satirlik commentlerin silinmesi icin pattern
         //(?s)ifadenin geri kalaný için "nokta yeni satýrla eþleþir" baðlaminda kullanildi. 
        //tek satirlik yorumlar icin
        Matcher matcherComm=patternSingleComm.matcher(tmp);
        tmp=matcherComm.replaceAll("");//eslesen patternlerin hepsini parantez icindeki degere atandi yani silindi
        //Multiline veye /*..*/ seklindeki commentlerin silinmesi icin
         matcherComm=patternMulComm.matcher(tmp);
        tmp=matcherComm.replaceAll("");// once yorum satirlarini sildik
        tmp=tmp.replaceAll("\\s+","");// sonrasinda islem kolayligi icin string icindeki butun bosluklar silindi
        //bosluklarin silinmesinin sebebi pattern eslesmesini sayarken bosluklarla ugrasmamak icin yapildi.
        //System.out.println(tmp);
        //clean code olmasi amaciyla diger matcherlar tekrar tekrar kullanilmak yerine her seferinde yenisi declare edildi
        //<,<=,>,>=,==,!=' list,arraylist da map..<> icerebilir class<T> seklinde de olabilir hepsine uyan pattern yazmak regex
        //ile zor oldugu icin ve javanin syntaxinin libraryler ile birlikte çok genis oldugu ve pdfde ornekteki gibi verilecegi
        //icin çok fazla detaya girilmeden temel sartlarý saglayan pattern olusturuldu.
        Pattern patternRelate=Pattern.compile("[!=]=|[<>]=?" );
        Matcher matcherRelate=patternRelate.matcher(tmp);
                while(matcherRelate.find()) {
             // eslesmelerin hangi indexler arasinda oldugunu kontrol icin asagidaki gibivjava.util.regexin classlari-metodlari
             //kullanilabilir
        	/*System.out.print("Start index: " + matcherRelate.start());
            System.out.print(" End index: " + matcherRelate.end() + " ");
            System.out.println(matcherRelate.group());*/
                	Lexical.setRelationalNum();
                }
                tmp=matcherRelate.replaceAll("");// iliskisel opetatorler de bulunduktan sonra silinecek
                //   System.out.println(tmp);//logical ifadeler silindikten sonra kalan string sekildeki gibidir
      
       
       
       Pattern patternlogic=Pattern.compile("([&|]{2})|(!{1}(?=[^=]))");//logical operator sayisi icin pattern 
       //[^=] & veya |'den sonra = gelmedigini garanti etmek iliskisel operatorle karismamasini saglmak icin patterne eklendi
       Matcher matcherlogic=patternlogic.matcher(tmp);
               while(matcherlogic.find()) {
            	   Lexical.setLogicalNum();
               	}
               tmp=matcherlogic.replaceAll("");//bulunduktan sonra string icinden silinecek diger operatrlerle karismamasi saglandi
               
       //assignment operator '=,+=,-=,/=,*=,%=,&=,|=,^= ' outputta gosterilmez hesaplamada kullanilir
        Pattern patternAtama=Pattern.compile("([\\-\\+/*%&|^]={1}|\\b={1}\\b)");
        Matcher matcherAtama=patternAtama.matcher(tmp);
		      while(matcherAtama.find()) {
		          Lexical.setAssignmentNum();
		      }
	      tmp=matcherAtama.replaceAll("");// atama operatorleri silindi
	      	
	      // tekli- yani unary operator sayisi \b sonrasinda boundary gelmedigini garanti icin \\ kullanilmasinin sebebi javada escape
	      //karakteri olmasindan dolayi
	      Pattern patternUnary=Pattern.compile(" (([\\+\\-]){2}(\\w)*)|((\\w)([+-]{2})|([^\\w][\\-\\+]\\b){1}(\\w))");
	      Matcher matcherUnary=patternUnary.matcher(tmp);
			      while(matcherUnary.find()) {
			    	 Lexical.setUnaryNum();
			      }
      tmp=matcherUnary.replaceAll("");
     
      Pattern patternikili=Pattern.compile("(?<=\\w)\\)*[\\+\\-*&%|^/]{1}(?=\\w)\\)*");//logical operator sayisi bulundu
      Matcher matcherikili=patternikili.matcher(tmp);
              while(matcherikili.find()) {
            	  Lexical.setBinaryNum();
              }
              //en son hepsi print edildi
              tmp=matcherikili.replaceAll("");
              
              System.out.println("Tekli operator sayisi = " +Lexical.getUnaryNum());
              System.out.println("Ýkili operator sayisi = " +Lexical.getBinaryNum());
              System.out.println("Sayisal operator sayisi = "+Lexical.getNumericNum());
              System.out.println("Iliskisel operator sayisi = "+Lexical.getRelationalNum());
              System.out.println("Mantiksal operator sayisi = "+Lexical.getLogicalNum());
              Lexical.setOperandNum();
              System.out.println("Operarand sayisi="+Lexical.getOperandNum());
      
      
      }
}