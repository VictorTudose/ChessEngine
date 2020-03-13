public class Utils {
    public static boolean black=false;

    public static boolean isBlack() {
        return black;
    }

    public static void setBlack(boolean black) {
        Utils.black = black;
    }
    public static int[] moveToPosition(String move)
    {
        int[] retee=new int[4];
        retee[0]=move.charAt(0)-'a';
        retee[1]=move.charAt(1)-'0';
        retee[2]=move.charAt(2)-'a';
        retee[3]=move.charAt(3)-'0';
        if(black) {
            retee[0] =(8-retee[0])%8;
            retee[2] =(8-retee[2])%8;
        }
        return retee;
    }
}
