package Utils;



/**
 * The Class Pair.
 *
 * @param <L> the generic type
 * @param <R> the generic type
 */
public class Pair<L,R> {

	/** The left. */
	private  L left;
	
	/** The right. */
	private  R right;

	/**
	 * Instantiates a new pair.
	 *
	 * @param left the left
	 * @param right the right
	 */
	public Pair(L left, R right) {
	    this.left = left;
	    this.right = right;
	 }

	/* 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((left == null) ? 0 : left.hashCode());
		result = prime * result + ((right == null) ? 0 : right.hashCode());
		return result;
	}

	/* 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pair<L, R> other = (Pair<L, R>) obj;
		if (left == null) {
			if (other.left != null)
				return false;
		} else if (!left.equals(other.left))
			return false;
		if (right == null) {
			if (other.right != null)
				return false;
		} else if (!right.equals(other.right))
			return false;
		return true;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public synchronized R getValue() {
		return right;
	}
	
	/**
	 * Sets the value.
	 *
	 * @param right the new value
	 */
	public synchronized void setValue(R right) {
		this.right=right;
	}
	
	/**
	 * Gets the key.
	 *
	 * @return the key
	 */
	public synchronized L getKey() {
		return left;
	}
}