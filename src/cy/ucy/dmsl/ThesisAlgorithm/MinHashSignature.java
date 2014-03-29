package cy.ucy.dmsl.ThesisAlgorithm;

import java.util.BitSet;

import cy.ucy.dmsl.ThesisAlgorithm.FNVHash.FNV1a64;
import cy.ucy.dmsl.ThesisAlgorithm.MurmurHash.MurmurHash;

public class MinHashSignature {

	private BitSet bitSet = null;
	private int bitSetLength = 0;
	private int offset = 0; // start always from the begining
	private long seed = Utils.seed; // this is used for the fnv hashing
	private FNV1a64 fnv1a64 = new FNV1a64();
	
	
	public MinHashSignature() {
		int howMany = Utils.KBYTES * Utils.BYTES_PER_KBYTE
				* Utils.BITS_PER_BYTE + Utils.BYTES * Utils.BITS_PER_BYTE;
		this.bitSetLength = howMany;
		setBitSet(new BitSet(howMany));

	}
	
		

	public BitSet getBitSet() {
		return bitSet;
	}

	public void setBitSet(BitSet bitSet) {
		this.bitSet = bitSet;
	}

	public void insertSignature(String str) {

		int signature1 = (int) (Math.abs(fnv1a64.fnv(str.getBytes(), 0,
				str.length(), this.seed)) % bitSetLength);
		int signature2 = (int) (Math.abs(fnv1a64.fnv(str.getBytes(), 0,
				str.length(), this.seed + 200)) % bitSetLength);
		int signature3 = (int) (Math.abs(MurmurHash.hash64(str.getBytes(),
				str.length(), (int) this.seed)) % bitSetLength);
		//
		// System.out.println ("signature1: " + signature1);
		// System.out.println ("signature2: " + signature2);
		// System.out.println ("signature3: " + signature3);
		
		bitSet.set(signature1);
		bitSet.set(signature2);
		bitSet.set(signature3);

	}

	
	public int getBitSetLength() {
		return bitSetLength;
	}

	public void setBitSetLength(int bitSetLength) {
		this.bitSetLength = bitSetLength;
	}
}
