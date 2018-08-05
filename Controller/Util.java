package Controller;

public class Util {
	public static boolean equals(float f1, float f2, float delta)
	{
		return (Math.abs(f1 - f2) < delta);
	}
	/**
	 * Uses <code>0.001f</code> for delta.
	 */
	public static boolean equals(float f1, float f2)
	{
		return equals(f1, f2, 0.001f);
	}
}
