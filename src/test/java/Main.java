public class Main {


    public Main() throws Exception {

        int a = 0;
        for (int i = 0; i < 15; i++) {
            a |= 1 << i;
        }
        System.out.println(a);
        System.out.println(Integer.bitCount(a));

        System.out.println(35 >> 4);
        int i = (35 << 4) | 14;
        System.out.println(i >> 8 & 255);
        System.out.println((35 << 4));
        System.out.println(i >> 4);
        System.out.println(i - (i - ((i >> 4) << 4)));
        System.out.println(inverseShiftBinary(120));
    }

    public static void main(String[] args) throws Exception {
        new Main();
    }


    public static int inverseShiftBinary(int i) {
        String s = Integer.toBinaryString(i < 0 ? i * (-1) : i);
        boolean signe = i >= 0;
        if (s.length() <= 4) return i;
        int a = Integer.parseInt(s.substring(s.length() - 4), 2);
        return signe ? a : a * (-1);
    }

}
