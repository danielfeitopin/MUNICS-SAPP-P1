package es.storeapp.business.entities;

import es.storeapp.common.Constants;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity(name = Constants.PRODUCT_ENTITY)
@Table(name = Constants.PRODUCTS_TABLE)
public class Product implements Serializable{    

    private static final long serialVersionUID = 70079876312519893L;
    
    @Id
    private Long productId;
    
    @ManyToOne
    @JoinColumn(name = "category", nullable = false)
    private Category category;
    
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    
    @Column(name = "description", nullable = false)
    private String description;
    
    @Column(name = "icon", nullable = false)
    private String icon;
    
    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "totalScore", nullable = false)
    private Integer totalScore;

    @Column(name = "totalComments", nullable = false)
    private Integer totalComments;
    
    @Column(name = "sales", nullable = false)
    private Integer sales;
    
    @OneToMany(mappedBy = "product")
    private List<Comment> comments = new ArrayList<>();
    
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public Integer getTotalComments() {
        return totalComments;
    }

    public void setTotalComments(Integer totalComments) {
        this.totalComments = totalComments;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
    
    public Integer getRating() {
        return totalComments == 0 ? 0 : totalScore / totalComments;
    }

    @Override
    public String toString() {
        return String.format("Product{productId=%s, category=%s, name=%s, description=%s, icon=%s, price=%s, totalScore=%s, totalComments=%s, sales=%s}", 
            productId, category, name, description, icon, price, totalScore, totalComments, sales);
    }
    
    
    
}
