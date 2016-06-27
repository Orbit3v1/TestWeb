package test.order;

import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.persistence.Query;

@Named("testInit")
@Scope("request")
public class TestInit {

    private Order order;

    @PostConstruct
    public void init() {
        //update();

    }

    public void update(){
        System.out.println("TestInit.update");
        order = new Order();
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
