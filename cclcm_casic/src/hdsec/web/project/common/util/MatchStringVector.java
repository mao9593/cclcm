package hdsec.web.project.common.util;

import java.util.Vector;

/**
 * 继承至java.util.HashSet，覆盖了compare方法，实现各种匹配相等
 * 
 */
public class MatchStringVector<E> extends Vector<E> {
	private static final long serialVersionUID = 1L;
	public static final byte FRONT_MATCH_MODE = 1; // 前匹配
	public static final byte MIDDLE_MATCH_MODE = 2; // 中间匹配
	public static final byte BACK_MATCH_MODE = 3; // 后匹配
	private byte matchMode;
	
	public MatchStringVector() {
		super();
	}
	
	public MatchStringVector(byte matchMode) {
		super();
		this.matchMode = matchMode;
	}
	
	public MatchStringVector(byte matchMode, int initialCapacity) {
		super();
		if (matchMode > 3 && matchMode < 1)
			throw new IllegalArgumentException("Illegal match mode value: " + matchMode);
		if (initialCapacity < 0)
			throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
		this.matchMode = matchMode;
		this.elementData = new Object[initialCapacity];
	}
	
	/**
	 * Constructs an empty vector with the specified initial capacity and
	 * capacity increment.
	 * 
	 * @param initialCapacity
	 *            the initial capacity of the vector.
	 * @throws IllegalArgumentException
	 *             if the specified initial capacity is negative
	 */
	public MatchStringVector(int initialCapacity) {
		super();
		if (initialCapacity < 0)
			throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
		this.elementData = new Object[initialCapacity];
	}
	
	public byte getMatchMode() {
		return matchMode;
	}
	
	public void setMatchMode(byte matchMode) {
		this.matchMode = matchMode;
	}
	
	/**
	 * Tests if the specified object is a component in this vector.
	 * 
	 * @param elem
	 *            an object.
	 * @return <code>true</code> if and only if the specified object is the same
	 *         as a component in this vector, as determined by the
	 *         <tt>equals</tt> method; <code>false</code> otherwise.
	 */
	@Override
	public boolean contains(Object elem) {
		if (elem instanceof String)
			return matchIndexOf((String) elem, 0) >= 0;
		else
			return indexOf(elem, 0) >= 0;
	}
	
	/**
	 * Searches for the first occurence of the given argument, beginning the
	 * search at <code>index</code>, and testing for equality using the
	 * <code>equals</code> method.
	 * 
	 * @param elem
	 *            a String.
	 * @param index
	 *            the non-negative index to start searching from.
	 * @return the index of the first occurrence of the object argument in this
	 *         vector at position <code>index</code> or later in the vector,
	 *         that is, the smallest value <tt>k</tt> such that
	 *         <tt>elem.equals(elementData[k]) && (k &gt;= index)</tt> is
	 *         <tt>true</tt>; returns <code>-1</code> if the object is not
	 *         found. (Returns <code>-1</code> if <tt>index</tt> &gt;= the
	 *         current size of this <tt>Vector</tt>.)
	 * @throws IndexOutOfBoundsException
	 *             if <tt>index</tt> is negative.
	 * @see Object#equals(Object)
	 */
	public synchronized int matchIndexOf(String elem, int index) {
		if (elem == null) {
			for (int i = index; i < elementCount; i++)
				if (elementData[i] == null)
					return i;
		} else {
			Object obj;
			for (int i = index; i < elementCount; i++) {
				obj = elementData[i];
				if (obj instanceof String) {
					switch (matchMode) { // 参数为集合中某的元素的子串
						case FRONT_MATCH_MODE:
							if (((String) obj).startsWith(elem)) {
								return i;
							}
							break;
						case MIDDLE_MATCH_MODE:
							if (((String) obj).indexOf(elem) >= 0) {
								return i;
							}
							break;
						case BACK_MATCH_MODE:
							if (((String) obj).endsWith(elem)) {
								return i;
							}
							break;
						default:
							if (obj.equals(elem)) {
								return i;
							}
							break;
					}
				}
				if (elem.equals(elementData[i]))
					return i;
			}
		}
		return -1;
	}
}
