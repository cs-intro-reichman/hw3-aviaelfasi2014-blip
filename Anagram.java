public class Anagram {
	public static void main(String args[]) {
		System.out.println(isAnagram("silent","listen"));  // true
		System.out.println(isAnagram("William Shakespeare","I am a weakish speller")); // true
		System.out.println(isAnagram("Madam Curie","Radium came")); // true
		System.out.println(isAnagram("Tom Marvolo Riddle","I am Lord Voldemort")); // true
		System.out.println(preProcess("What? No way!!!"));
		
		System.out.println("silent and " + randomAnagram("silent") + " are anagrams.");
		
		// Performs a stress test of randomAnagram 
		String str = "1234567";
		Boolean pass = true;
		//// 10 can be changed to much larger values, like 1000
		for (int i = 0; i < 10; i++) {
			String randomAnagram = randomAnagram(str);
			System.out.println(randomAnagram);
			pass = pass && isAnagram(str, randomAnagram);
			if (!pass) break;
		}
		System.out.println(pass ? "test passed" : "test Failed");
	}  

	public static boolean isAnagram(String str1, String str2) {
		str1 = preProcess(str1);
		str2 = preProcess(str2);
		if (str1.length() != str2.length()){
			return false;
		}  
		for (int i = 0; i <str1.length(); i++){
			char c = str1.charAt(i);
			int index = str2.indexOf(c);
			if (index == -1){
				return false;
			}
		str2 = str2.substring(0, index) + str2.substring(index + 1);
	} return true; 
	}	   

	
	public static String preProcess(String str) {
		String result = "";
		for (int i=0; i<str.length() ; i++){
			char c = str.charAt(i);
		if (Character.isLetter(c)){
			result += Character.toLowerCase(c);
		}
		}
		return result;
	} 

	public static String randomAnagram(String str) {
		String result = "";
		String copyString = str;
		while(copyString.length() >0){
			int index = (int) (Math.random() * copyString.length());
			char c = copyString.charAt(index);
			result += c;
			copyString = copyString.substring(0, index) + copyString.substring(index + 1);

		}
		return result;
	}
}
