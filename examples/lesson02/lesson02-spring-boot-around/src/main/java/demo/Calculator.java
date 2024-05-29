package demo;

import org.springframework.stereotype.Service;

@Service
public class Calculator {

    public int add(int x, int y) {
        System.out.println("in Calculator with values x= " + x + " and y= " + y);
        return x + y;
    }
}
