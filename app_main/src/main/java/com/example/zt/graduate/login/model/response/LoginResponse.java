package  com.example.zt.graduate.login.model.response;

import java.util.List;

/**
 * @author taozhu5
 * @date 2019/3/13 09:53
 * @description 登陆响应数据
 */
public class LoginResponse {

    /**
     * 状态码
     */
    private String status;

    private List<Data> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    static class Data {
        String id;
        String promotionModel;
        String title;
        String description;
        String imageUrl;
        String price;
        String stock;
        String sales;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPromotionModel() {
            return promotionModel;
        }

        public void setPromotionModel(String promotionModel) {
            this.promotionModel = promotionModel;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getStock() {
            return stock;
        }

        public void setStock(String stock) {
            this.stock = stock;
        }

        public String getSales() {
            return sales;
        }

        public void setSales(String sales) {
            this.sales = sales;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "id='" + id + '\'' +
                    ", promotionModel='" + promotionModel + '\'' +
                    ", title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    ", imageUrl='" + imageUrl + '\'' +
                    ", price='" + price + '\'' +
                    ", stock='" + stock + '\'' +
                    ", sales='" + sales + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "status='" + status + '\'' +
                ", data=" + data +
                '}';
    }
}
