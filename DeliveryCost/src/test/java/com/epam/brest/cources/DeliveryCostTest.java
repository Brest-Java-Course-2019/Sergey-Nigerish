package com.epam.brest.cources;

import org.junit.jupiter.api.*;

import java.io.*;

import static com.epam.brest.cources.DeliveryCost.main;

class DeliveryCostTest {
    private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private static final InputStream originalIn = System.in;
    private static final PrintStream originalOut = System.out;
    private static final PrintStream originalErr = System.err;
    private static int i = 0;

    @BeforeAll
    public static void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }


//    @BeforeEach
//    public void clearOutStream() {
//        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outContent));
//    }

    @Test
    void DeliveryCostTest() throws Exception {
        String greetings = "10\n10\n";
        byte[] bytes = greetings.getBytes();
        InputStream inputStream = new ByteArrayInputStream(bytes);
        System.setIn(inputStream);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        main(new String[] {"ugg"});
        String str;
        while ((str = reader.readLine())!= null) {
            System.out.println(str);
        }
        String sout = "Enter the weight of cargo (t): Error in input! Please restart.\n";

        Assertions.assertNotEquals(sout, outContent.toString());

    }

    @Test
    void ErrDeliveryCostTest() throws Exception {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        String greetings = "t\nt\n";
        byte[] bytes = greetings.getBytes();
        InputStream inputStream = new ByteArrayInputStream(bytes);
        System.setIn(inputStream);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        main(new String[] {});
        String str;
        while ((str = reader.readLine())!= null) {
            System.out.println(str);
        }
        String sout = "Enter the weight of cargo (t): Error in input! Please restart.\n";

        Assertions.assertEquals(sout, outContent.toString());
    }

    @AfterAll
    public static void restoreStreams() {
        System.setIn(originalIn);
        System.setOut(originalOut);
        System.setErr(originalErr);
    }
}