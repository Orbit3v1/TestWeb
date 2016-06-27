package test.order;

import org.springframework.context.annotation.Scope;

import javax.inject.Named;

@Named("testInitRedirect")
@Scope("request")
public class TestInitRedirect {

    public void save(Order order){
        int a = 1;
        System.out.println("TestInitRedirect.save");
    }

}
