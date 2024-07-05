package hutech.example.phuongtrinh.model;

public class Common {
    public static String PTB1 (float a, float b) {
        if (a==0){
            if (b==0)
                return "PTVSN";
            else
                return "PTVN";
        }else {
            float x=-b/a;
            return ""+x;
        }
    }

    //Giải phương trình bậc 2
    public static String PTB2(float a,float b,float c) {
        if (a==0){
            return PTB1(b,c);
        }else{
            float d=b*b-4*a*c;
            if(d<0){
                return "PTVN";
            }else if(d==0){
                return "x1=x2"+(-b/a);
            }else{ //d>0
                float x1=(float)(-b+ Math.sqrt(d))/(2*a);
                float x2=(float)(-b- Math.sqrt(d))/(2*a);
                return "x1="+x1+",x2="+x2;
            }
        }
    }
    public static String pheptoan(int a,int b){
        return ""+(a+b);
    }
    public static String pheptoan(float a,float b){
        return ""+(a+b);
    }
    public static String pheptoan(double a,double b){
        return ""+(a+b);
    }
    public static String pheptoan(String a,String b){

        return ""+(Float.parseFloat(a)+Float.parseFloat(b));
    }
    public static String pheptoantru(int a,int b){
        return ""+(a-b);
    }
    public static String pheptoantru(float a,float b){
        return ""+(a-b);
    }
    public static String pheptoantru(double a,double b){
        return ""+(a-b);
    }
    public static String pheptoantru(String a,String b){

        return ""+(Float.parseFloat(a)-Float.parseFloat(b));
    }
    public static String pheptoannhan(int a,int b){
        return ""+(a*b);
    }
    public static String pheptoannhan(float a,float b){
        return ""+(a*b);
    }
    public static String pheptoannhan(double a,double b){
        return ""+(a*b);
    }
    public static String pheptoannhan(String a,String b){

        return ""+(Float.parseFloat(a)*Float.parseFloat(b));
    }
}
