import by.epam.epamlab.LFUCashe;

public class Running {
	public static void main(String[] args) {
		LFUCashe cashe = new LFUCashe(4, 0.8);
		//adding and getting cycle of random data cache
		for(int i = 0; i <= 30; i++) {
			Character code = (char) (65 + Math.round((5 * Math.random())));
			System.out.println(code + " -> add: " + cashe.add(code.toString()));			
			cashe.getCashe();
			System.out.println();
			code = (char) (65 + Math.round((5 * Math.random())));
			System.out.println(code + " -> get: " + cashe.get(code.toString()));
			cashe.getCashe();
			System.out.println();
		}		
	}
}
