package logic;

import data.DataAccessorHardCodedValues;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.lessThan;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    public static String ThreadResult;
    Controller con;
    private Scanner scanner = new Scanner(System.in);

    @BeforeEach
    void setUp() {
        con = new Controller(new DataAccessorHardCodedValues(), new PriceCalculator());
    }

    @Test
    void go() throws Exception {

        ByteArrayInputStream in = new ByteArrayInputStream("25\n25\n2\n".getBytes());
        System.setIn(in);

        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));


        Thread.sleep(1000);
        ctthread ct = new ctthread(new Controller(new DataAccessorHardCodedValues(), new PriceCalculator()));
        ct.start();
        Thread.sleep(1000);

        final String standardOutput = myOut.toString();

        String[]price = standardOutput.split("\r\n")[5].split("kr. ")[1].split(",");
        String finalprice = price[0];
        System.out.println(standardOutput);
        Double dprice = Double.parseDouble(finalprice);

        assertThat(dprice,is(218.75));
    }
}

class ctthread extends Thread{
    Controller ct;
    public ctthread(Controller ct){
        this.ct = ct;
    }

    public void run(){
        System.out.println("I STARTED!");
        ct.go();
    }
}

class scannerthread extends Thread{

    public void run(){
        Scanner scanner = new Scanner(System.in);
        String result = scanner.nextLine();
        result = scanner.nextLine();
        result = scanner.nextLine();
        result = scanner.nextLine();
        result = scanner.nextLine();
        result = scanner.nextLine();
        result = scanner.nextLine();
        ControllerTest.ThreadResult = result;
    }
}