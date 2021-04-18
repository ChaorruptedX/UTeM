/**
 * 
 */
package itemProduct;

import java.io.Serializable;

/**
 * @author - Chaorrupted X -
 *
 */
public class ItemProduct implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer itemProductId;
	private String name;
	private Float price;
	
	/**
	 * 
	 */
	public ItemProduct() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public void setItemProducId(Integer itemProductId) {
		this.itemProductId = itemProductId;
	}
	
	public int getItemProducId() {
		
		return itemProductId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		
		return name;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Float getPrice() {
		return price;
	}

}

