package Section_2;

import java.util.*;

public class Version_4_24_Points {
	public static void main(String[] args) {
		int[] card = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
				1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
				1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
				1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

		long startTime = System.currentTimeMillis();
		
		EvaluateExpression evaluate = new EvaluateExpression();
		
		int totalNumberOfCombinations = 0;
		int solutionCount = 0;
		
		for (int i1 = 0; i1 < card.length; i1++) 
			for (int i2 = i1 + 1; i2 < card.length; i2++) 
				for (int i3 = i2 + 1; i3 < card.length; i3++) 
					for (int i4 = i3 + 1; i4 < card.length; i4++) {
						totalNumberOfCombinations++;

						int a = card[i1];
						int b = card[i2];
						int c = card[i3];
						int d = card[i4];
						String s = evaluate.findSolution(a, b, c, d);

						if (!s.equals("No solution"))
							solutionCount++;
					}

		System.out.println("Total number of combinations is " 
				+ totalNumberOfCombinations);
		System.out.println("Total number of combinations with solutions is " 
				+ solutionCount);
		System.out.println("The solution ratio is " 
				+ 1.0 * solutionCount / totalNumberOfCombinations);

		long endTime = System.currentTimeMillis();
		System.out.println("Total time spent " + (endTime - startTime) / 1000);
	}
}
