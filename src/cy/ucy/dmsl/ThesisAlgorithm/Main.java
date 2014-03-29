package cy.ucy.dmsl.ThesisAlgorithm;

import java.util.BitSet;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MinHashSignature minHashSignature1 = new MinHashSignature();
		minHashSignature1.insertSignature("1");
		minHashSignature1.insertSignature("hello");
		minHashSignature1.insertSignature("android");				
		minHashSignature1.insertSignature("costas");				

		
		MinHashSignature minHashSignature2 = new MinHashSignature();
		minHashSignature2.insertSignature("1");
		minHashSignature2.insertSignature("hello");
		
		
		BitSet a = new BitSet (minHashSignature1.getBitSetLength());
		BitSet b = new BitSet (minHashSignature1.getBitSetLength());
		
		a.or(minHashSignature1.getBitSet()); //take the bitset of the first signature by applying bitwise or
		a.and(minHashSignature2.getBitSet()); // take the bitwise and of the first with the second signature
		
		b.or(minHashSignature1.getBitSet()); //take the bitset of the first signature by applying bitwise or
		b.or(minHashSignature2.getBitSet()); // take the bitwise or of the first with the second signature
		
		int cardinality1 = a.cardinality();
		int cardinality2 = b.cardinality();
		
		
		System.out.println ("a cardinality (AND) = " + cardinality1 + "\nb cardinality (OR) = " + cardinality2 + "\nsimilarity = " + (1.0 * cardinality1 / cardinality2));
		
		
	}

}
