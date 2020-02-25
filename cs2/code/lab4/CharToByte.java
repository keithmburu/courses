public class CharToByte {   
    public static void main(String[] args) {
	String str = "12345";
	byte digit = Byte.parseByte(Character.toString(str.charAt(0)));
	System.out.println(digit);
    }
}
