package contain.basic.crud_jwt;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="pc")
public class Laptop {
    @Id
    @Column(name = "pc_model")
    private String laptopModel;
    @Column(name = "pc_cost")
    private int laptopPrice;
    @Column(name = "pc_memory")
    private int laptopSsd;
    @Override
    public String toString() {
        return "Laptop [laptopModel=" + laptopModel + ", laptopPrice=" + laptopPrice + ", laptopSsd=" + laptopSsd + "]";
    }
    public Laptop() {
    }
    public Laptop(String laptopModel, int laptopPrice, int laptopSsd) {
        this.laptopModel = laptopModel;
        this.laptopPrice = laptopPrice;
        this.laptopSsd = laptopSsd;
    }
    public String getLaptopModel() {
        return laptopModel;
    }
    public void setLaptopModel(String laptopModel) {
        this.laptopModel = laptopModel;
    }
    public int getLaptopPrice() {
        return laptopPrice;
    }
    public void setLaptopPrice(int laptopPrice) {
        this.laptopPrice = laptopPrice;
    }
    public int getLaptopSsd() {
        return laptopSsd;
    }
    public void setLaptopSsd(int laptopSsd) {
        this.laptopSsd = laptopSsd;
    }    
}
