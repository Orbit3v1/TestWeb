package test.order;

import org.springframework.context.annotation.Scope;

import javax.faces.bean.ManagedProperty;
import javax.inject.Inject;
import javax.inject.Named;

@Named("orderItem")
@Scope("session")
public class OrderItem {

    private String val1;
    @Inject
    private Order order;

    public OrderItem() {
    }

    public String getVal1() {
        return val1;
    }

    public void setVal1(String val1) {
        this.val1 = val1;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void save(){
        order.setVal1(val1);
    }
}
