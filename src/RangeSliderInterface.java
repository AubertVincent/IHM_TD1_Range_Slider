public interface RangeSliderInterface {

	/**
	 * 
	 * @return the low value of the slider
	 */
	public int getLow();

	/**
	 * 
	 * @return the high value of the slider
	 */
	public int getHigh();

	/**
	 * 
	 * @return the maximum value of the slider
	 */
	public int getMaximum();

	/**
	 * 
	 * @return the minimum value of the slider
	 */
	public int getMinimum();

	/**
	 * 
	 * @param low the new low value
	 */
	public void setLow(int low);

	/**
	 * 
	 * @param high the new low value
	 */
	public void setHigh(int high);

	/**
	 * 
	 * @param min the new minimum value
	 */
	public void setMinimum(int min);

	/**
	 * 
	 * @param max the new maximum value
	 */
	public void setMaximum(int max);


}
