package freqAnalisys;

import java.io.BufferedReader;
import java.io.*;
import java.util.*;
public class CharAnalisys 
{
	public static void wordAnalis()
	{ 
		try
		{ 
			FileInputStream fis = new FileInputStream("voyna-i-mir-tom-1.txt"); 
			BufferedReader br = new BufferedReader(new InputStreamReader(fis, "Cp1251"));
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out, "Cp1251"), true);
			String text; String words[];
			Map<String, Integer> wordToCount = new HashMap<>();
			text = br.readLine();
			while( text != null)
			{ 
				text = text.replaceAll("[^à-ÿÀ-ßa-zA-Z\\s]", ""); 
				words = text.split(" "); 
				for (int i = 0; i < words.length; i++) 
				{
					for (String word : words) 
					{
						if (!word.equals("")) 
						{ 
							if (!wordToCount.containsKey(word.toLowerCase())) 
							{
								wordToCount.put(word.toLowerCase(), 0);
								
							} wordToCount.put(word.toLowerCase(), wordToCount.get(word.toLowerCase()) + 1);
							} 
						} 
					} 
				text = br.readLine();
				} 
			br.close();
			List<Map.Entry<String, Integer>> a = new ArrayList<>(wordToCount.entrySet());
			Collections.sort(a, new Comparator() {
				public int compare(Object o1, Object o2) {
					Map.Entry e1 = (Map.Entry) o1;
					Map.Entry e2 = (Map.Entry) o2;
					return ((Comparable) e2.getValue()).compareTo(e1.getValue());
					} 
				}); 
			for (Map.Entry<String, Integer> e : a) {
				System.out.println(e.getKey() + " " + e.getValue()); 
				} 
			} 
		catch(Exception e) 
		{
			System.out.println(e); 
			
		}
		
	}
	
	
	public static void main(String[] args) {
		try {
			wordAnalis();
			FileInputStream fis = new FileInputStream("voyna-i-mir-tom-1.txt"); 
			BufferedReader br = new BufferedReader(new InputStreamReader(fis, "Cp1251")); 
			String str;
			Character chars[] = {};
			while ((str = br.readLine()) != null) { 
				str = str.replaceAll("[^à-ÿÀ-ßa-zA-Z\\s]", "").toLowerCase(); 
				Character[] charObjectArray = str.chars().filter(c ->c != (int)' ').mapToObj(c ->(char) c).toArray(Character[]::new); 
				chars = concat(chars, charObjectArray); 
			}
			for (int l = 0; l < (chars.length/1000); l++) {
				Character x[] = majority(chars, 1000 * l, 1000 * (l + 1), 15); 
				for (int i = 0; i < 15 && i < x.length; i++)
					System.out.print(x[i].charValue() + " ");
				System.out.println();
			}
			br.close();
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	private static Character[] majority(Character[] array, int B, int N, int k) {
		
		LinkedHashMap<Character, Integer> candidates = new LinkedHashMap<Character, Integer>();
		for (int i = B; i < N; i++) {

			
			if (candidates.containsKey(array[i]))
				candidates.put(array[i], candidates.get(array[i]).intValue() + 1);
			else 
				if (candidates.size() < k - 1)
					candidates.put(array[i], 1);
				else 
				{
					Character x[] = (Character[]) candidates.keySet().toArray(new Character[candidates.size()]);
					for (Character l : x)
						if (candidates.get(l).intValue() == 0) 
							candidates.remove(l); 
						else 
							candidates.put(l, candidates.get(l).intValue() - 1);
				}
		}

		return (Character[]) candidates.keySet().toArray(new Character[candidates.size()]); 
	}

	
	static Character[] concat(Character[] A, Character[] B) {
		int aLen = A.length;
		int bLen = B.length;
		Character[] C = new Character[aLen + bLen];
		System.arraycopy(A, 0, C, 0, aLen);
		System.arraycopy(B, 0, C, aLen, bLen);
		return C;
	}
	
}

