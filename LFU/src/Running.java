import java.io.IOException;

import by.epam.epamlab.LFUCashe;

public class Running {
	public static void main(String[] args) throws InterruptedException, IOException {
		LFUCashe cashe = new LFUCashe(4, 0.8);
		for(int i = 0; i <= 30; i++) {
			Character code = (char) (65 + Math.round((10 * Math.random())));
			System.out.println(code + " -> add");
			cashe.add(code.toString());			
			cashe.getCashe();
			Thread.sleep(200);
			System.out.println();
			System.out.println(code + " -> get");
			cashe.get(code.toString());	
			cashe.getCashe();
			System.out.println();
		}		
	}
}
