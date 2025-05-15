package mall.model;

public class ProductVO {
	
	private int prod_id ;
	private String prodName;
	
	public ProductVO() {
		super();
	}
	
	public int getProd_id() {
		return prod_id;
	}
	public void setProd_id(int prod_id) {
		this.prod_id = prod_id;
	}
	public String getProd() {
		return prodName;
	}
	public void setProd(String prodName) {
		this.prodName = prodName;
	}


}
