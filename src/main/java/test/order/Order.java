package test.order;

import org.springframework.context.annotation.Scope;

import javax.faces.bean.ManagedBean;
import javax.inject.Named;

@Named("order")
@Scope("session")
@ManagedBean(name = "order")
public class Order {

    private String val1;
    private String val2;

    public Order() {
    }

    public String getVal1() {
        return val1;
    }

    public void setVal1(String val1) {
        this.val1 = val1;
    }

    public String getVal2() {
        return val2;
    }

    public void setVal2(String val2) {
        this.val2 = val2;
    }
}
