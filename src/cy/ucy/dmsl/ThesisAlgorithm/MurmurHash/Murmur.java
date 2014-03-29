package cy.ucy.dmsl.ThesisAlgorithm.MurmurHash;

public class Murmur {
	public static int hash(byte[] data, int seed) {
		int m = 0x5bd1e995;
		int r = 24;

		int h = seed ^ data.length;

		int len = data.length;
		int len_4 = len >> 2;

		for (int i = 0; i < len_4; i++) {
			int i_4 = i << 2;
			int k = data[i_4 + 3];
			k = k << 8;
			k = k | (data[i_4 + 2] & 0xff);
			k = k << 8;
			k = k | (data[i_4 + 1] & 0xff);
			k = k << 8;
			k = k | (data[i_4 + 0] & 0xff);
			k *= m;
			k ^= k >>> r;
			k *= m;
			h *= m;
			h ^= k;
		}

		int len_m = len_4 << 2;
		int left = len - len_m;

		if (left != 0) {
			if (left >= 3) {
				h ^= (int) data[len - 3] << 16;
			}
			if (left >= 2) {
				h ^= (int) data[len - 2] << 8;
			}
			if (left >= 1) {
				h ^= (int) data[len - 1];
			}

			h *= m;
		}

		h ^= h >>> 13;
		h *= m;
		h ^= h >>> 15;

		return h;
	}

	// Testing ...
	static int NUM = 1000;

	public static void main(String[] args) {
		// byte[] bytes = new byte[4];
		// for (int i = 0; i < NUM; i++) {
		// bytes[0] = (byte)(i & 0xff);
		// bytes[1] = (byte)((i & 0xff00) >> 8);
		// bytes[2] = (byte)((i & 0xff0000) >> 16);
		// bytes[3] = (byte)((i & 0xff000000) >> 24);
		// System.out.println(Integer.toHexString(i) + " " +
		// Integer.toHexString(hash(bytes, 1)));
		// }

		String s = "and";
		System.out
				.println(s + " " + Integer.toHexString(hash(s.getBytes(), 1)));

		MurmurHash m = new MurmurHash();
		
		long f = m.hash64("hello");
		
		System.out.println ("f:" + f);

	}
}