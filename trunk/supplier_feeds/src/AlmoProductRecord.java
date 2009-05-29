

import java.io.Serializable;


public class AlmoProductRecord implements Serializable{

	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = 741125106549503616L;
	
	private String brand;
	private String partNumber;
	private String description;
	private String price;
	private String availableAllWhse;
	private String whs126Ohio;
	private String whs134Philadelphia;
	private String whs141NorthCarolina;
	private String whs166California;
	private String whs167Harrisburg;
	private String whs180Minnesota;
	private String whs185Seattle;
	private String whs186Denver;
	private String whs187Dallas;
	private String whs191Wisconsin;
	private String whs195StLouis;
	private String upcCode;
	private String upsShip;
	private String weight;
	
	
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getPartNumber() {
		return partNumber;
	}
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getAvailableAllWhse() {
		return availableAllWhse;
	}
	public void setAvailableAllWhse(String availableAllWhse) {
		this.availableAllWhse = availableAllWhse;
	}
	public String getWhs126Ohio() {
		return whs126Ohio;
	}
	public void setWhs126Ohio(String whs126Ohio) {
		this.whs126Ohio = whs126Ohio;
	}
	public String getWhs134Philadelphia() {
		return whs134Philadelphia;
	}
	public void setWhs134Philadelphia(String whs134Philadelphia) {
		this.whs134Philadelphia = whs134Philadelphia;
	}
	public String getWhs141NorthCarolina() {
		return whs141NorthCarolina;
	}
	public void setWhs141NorthCarolina(String whs141NorthCarolina) {
		this.whs141NorthCarolina = whs141NorthCarolina;
	}
	public String getWhs167Harrisburg() {
		return whs167Harrisburg;
	}
	public void setWhs167Harrisburg(String whs167Harrisburg) {
		this.whs167Harrisburg = whs167Harrisburg;
	}
	public String getWhs180Minnesota() {
		return whs180Minnesota;
	}
	public void setWhs180Minnesota(String whs180Minnesota) {
		this.whs180Minnesota = whs180Minnesota;
	}
	public String getWhs185Seattle() {
		return whs185Seattle;
	}
	public void setWhs185Seattle(String whs185Seattle) {
		this.whs185Seattle = whs185Seattle;
	}
	public String getWhs186Denver() {
		return whs186Denver;
	}
	public void setWhs186Denver(String whs186Denver) {
		this.whs186Denver = whs186Denver;
	}
	public String getWhs187Dallas() {
		return whs187Dallas;
	}
	public void setWhs187Dallas(String whs187Dallas) {
		this.whs187Dallas = whs187Dallas;
	}
	public String getWhs191Wisconsin() {
		return whs191Wisconsin;
	}
	public void setWhs191Wisconsin(String whs191Wisconsin) {
		this.whs191Wisconsin = whs191Wisconsin;
	}
	public String getWhs195StLouis() {
		return whs195StLouis;
	}
	public void setWhs195StLouis(String whs195StLouis) {
		this.whs195StLouis = whs195StLouis;
	}
	public String getUpcCode() {
		return upcCode;
	}
	public void setUpcCode(String upcCode) {
		this.upcCode = upcCode;
	}
	public String getUpsShip() {
		return upsShip;
	}
	public void setUpsShip(String upsShip) {
		this.upsShip = upsShip;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	
	public String getWhs166California() {
		return whs166California;
	}
	public void setWhs166California(String whs166California) {
		this.whs166California = whs166California;
	}
	public String toCsv(char delem) {
		StringBuffer buffer = new StringBuffer("");
			buffer.append("\"").append((brand != null) ? brand : "").append("\"").append(delem)
				.append("\"").append((partNumber != null) ? partNumber : "").append("\"").append(delem)
				.append("\"").append((description != null) ? description : "").append("\"").append(delem)
				.append("\"").append((price != null) ? price : "").append("\"").append(delem)
				.append("\"").append((availableAllWhse != null) ? availableAllWhse : "").append("\"").append(delem)
				.append("\"").append((whs126Ohio != null) ? whs126Ohio : "").append("\"").append(delem)
				.append("\"").append((whs134Philadelphia != null) ? whs134Philadelphia : "").append("\"").append(delem)
				.append("\"").append((whs141NorthCarolina != null) ? whs141NorthCarolina : "").append("\"").append(delem)
				.append("\"").append((whs166California != null) ? whs166California : "").append("\"").append(delem)
				.append("\"").append((whs167Harrisburg != null) ? whs167Harrisburg : "").append("\"").append(delem)
				.append("\"").append((whs180Minnesota != null) ? whs180Minnesota : "").append("\"").append(delem)
				.append("\"").append((whs185Seattle != null) ? whs185Seattle : "").append("\"").append(delem)
				.append("\"").append((whs186Denver != null) ? whs186Denver : "").append("\"").append(delem)
				.append("\"").append((whs187Dallas != null) ? whs187Dallas : "").append("\"").append(delem)
				.append("\"").append((whs191Wisconsin != null) ? whs191Wisconsin : "").append("\"").append(delem)
				.append("\"").append((whs195StLouis != null) ? whs195StLouis : "").append("\"").append(delem)
				.append("\"").append((upcCode != null) ? upcCode : "").append("\"").append(delem)
				.append("\"").append((upsShip != null) ? upsShip : "").append("\"").append(delem)
				.append("\"").append((weight != null) ? weight : "").append("\"");
		return buffer.toString();
	}
	
}
