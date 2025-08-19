import com.tesla.Main;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class RobotTest {
    private final InputStream originalSystemIn = System.in;
    private final PrintStream originalSystemOut = System.out;
    private ByteArrayOutputStream outputStreamCaptor;

    @BeforeEach
    void setUp() {
        outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void tearDown() {
        System.setIn(originalSystemIn);
        System.setOut(originalSystemOut);
    }

    @Test
    void test1() {
        String simulatedInput = """
                5 6
                ######
                #@E $#
                # N  #
                #X   #
                ######
                """;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        Main.run(new Scanner(System.in));
        assertEquals("""
                SOUTH
                EAST
                NORTH
                EAST
                EAST
                """, outputStreamCaptor.toString());
    }

    @Test
    void test2() {
        String simulatedInput = """
                10 10
                ##########
                #        #
                #  S   W #
                #        #
                #  $     #
                #        #
                #@       #
                #        #
                #E     N #
                ##########
                """;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        Main.run(new Scanner(System.in));
        assertEquals("""
                SOUTH
                SOUTH
                EAST
                EAST
                EAST
                EAST
                EAST
                EAST
                NORTH
                NORTH
                NORTH
                NORTH
                NORTH
                NORTH
                WEST
                WEST
                WEST
                WEST
                SOUTH
                SOUTH
                """, outputStreamCaptor.toString());
    }

    @Test
    void test3() {
        String simulatedInput = """
                10 10
                ##########
                # @      #
                # B      #
                #XXX     #
                # B      #
                #    BXX$#
                #XXXXXXXX#
                #        #
                #        #
                ##########
                """;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        Main.run(new Scanner(System.in));
        assertEquals("""
                SOUTH
                SOUTH
                SOUTH
                SOUTH
                EAST
                EAST
                EAST
                EAST
                EAST
                EAST
                """, outputStreamCaptor.toString());
    }

    @Test
    void test4() {
        String simulatedInput = """
                10 10
                ##########
                #    I   #
                #        #
                #       $#
                #       @#
                #        #
                #       I#
                #        #
                #        #
                ##########
                """;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        Main.run(new Scanner(System.in));
        assertEquals("""
                SOUTH
                SOUTH
                SOUTH
                SOUTH
                WEST
                WEST
                WEST
                WEST
                WEST
                WEST
                WEST
                NORTH
                NORTH
                NORTH
                NORTH
                NORTH
                NORTH
                NORTH
                EAST
                EAST
                EAST
                EAST
                EAST
                EAST
                EAST
                SOUTH
                SOUTH
                """, outputStreamCaptor.toString());
    }

    @Test
    void test5() {
        String simulatedInput = """
                10 10
                ##########
                #    1   #
                #        #
                #        #
                #        #
                #@       #
                #        #
                #        #
                #    1  $#
                ##########
                """;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        Main.run(new Scanner(System.in));
        assertEquals("""
                SOUTH
                SOUTH
                SOUTH
                EAST
                EAST
                EAST
                EAST
                EAST
                EAST
                EAST
                SOUTH
                SOUTH
                SOUTH
                SOUTH
                SOUTH
                SOUTH
                SOUTH
                """, outputStreamCaptor.toString());
    }

    @Test
    void test6() {
        String simulatedInput = """
                5 5
                #####
                #   #
                # $ #
                # @ #
                #####
                """;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        Main.run(new Scanner(System.in));
        assertEquals("""
                LOOP
                """, outputStreamCaptor.toString());
    }

    @Test
    void test7() {
        String simulatedInput = """
                5 5
                #####
                # S #
                # $ #
                # @ #
                #####
                """;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        Main.run(new Scanner(System.in));
        assertEquals("""
                EAST
                NORTH
                NORTH
                WEST
                SOUTH
                """, outputStreamCaptor.toString());
    }

    @Test
    void test8() {
        String simulatedInput = """
                10 10
                ##########
                #    1 I #
                #        #
                #I       #
                #       X#
                #@       #
                #        #
                #        #
                #    1  $#
                ##########
                """;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        Main.run(new Scanner(System.in));
        assertEquals("""
                SOUTH
                SOUTH
                SOUTH
                EAST
                EAST
                EAST
                EAST
                EAST
                EAST
                EAST
                SOUTH
                SOUTH
                WEST
                WEST
                WEST
                WEST
                WEST
                WEST
                WEST
                SOUTH
                EAST
                EAST
                EAST
                EAST
                EAST
                EAST
                SOUTH
                SOUTH
                SOUTH
                SOUTH
                EAST
                """, outputStreamCaptor.toString());
    }

    @Test
    void test9() {
        String simulatedInput = """
                10 10
                ##########
                #    1 I #
                #        #
                #        #
                #       X#
                #@       #
                #        #
                #        #
                #    1  $#
                ##########
                """;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        Main.run(new Scanner(System.in));
        assertEquals("""
                LOOP
                """, outputStreamCaptor.toString());
    }

    @Test
    void test10() {
        String simulatedInput = """
                10 10
                ##########
                #    1 I #
                #        #
                #        #
                #       X#
                #@       #
                ##########
                #        #
                #1      $#
                ##########
                """;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        Main.run(new Scanner(System.in));
        assertEquals("""
                LOOP
                """, outputStreamCaptor.toString());
    }

    @Test
    void test11() {
        String simulatedInput = """
                10 10
                ##########
                #    1 I #
                #        #
                #        #
                #       X#
                #@      B#
                ##########
                #        #
                #1      $#
                ##########
                """;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        Main.run(new Scanner(System.in));
        assertEquals("""
                EAST
                EAST
                EAST
                EAST
                EAST
                EAST
                EAST
                NORTH
                NORTH
                NORTH
                NORTH
                WEST
                WEST
                WEST
                NORTH
                EAST
                EAST
                EAST
                EAST
                EAST
                EAST
                EAST
                SOUTH
                """, outputStreamCaptor.toString());
    }

    @Test
    void test12() {
        String simulatedInput = """
                10 10
                ##########
                #    1 IX#
                #       X#
                #       X#
                #       X#
                #@      B#
                ##########
                #        #
                #1      $#
                ##########
                """;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        Main.run(new Scanner(System.in));
        assertEquals("""
                EAST
                EAST
                EAST
                EAST
                EAST
                EAST
                EAST
                NORTH
                NORTH
                NORTH
                NORTH
                WEST
                WEST
                WEST
                NORTH
                EAST
                EAST
                EAST
                EAST
                EAST
                EAST
                EAST
                SOUTH
                """, outputStreamCaptor.toString());
    }

    @Test
    void test13() {
        String simulatedInput = """
                10 10
                ##########
                #    1 IX#
                #       X#
                #       B#
                #       X#
                #@      B#
                ##########
                #        #
                #1      $#
                ##########
                """;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        Main.run(new Scanner(System.in));
        assertEquals("""
                LOOP
                """, outputStreamCaptor.toString());
    }

    @Test
    void test14() {
        String simulatedInput = """
                10 10
                ##########
                #    1 IX#
                #       X#
                #    I  B#
                #       X#
                #@      B#
                ##########
                #        #
                #1      $#
                ##########
                """;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        Main.run(new Scanner(System.in));
        assertEquals("""
                EAST
                EAST
                EAST
                EAST
                EAST
                EAST
                EAST
                NORTH
                NORTH
                WEST
                WEST
                WEST
                WEST
                WEST
                WEST
                WEST
                NORTH
                NORTH
                EAST
                EAST
                EAST
                EAST
                EAST
                EAST
                EAST
                EAST
                EAST
                EAST
                EAST
                """, outputStreamCaptor.toString());
    }

    @Test
    void test15() {
        String simulatedInput = """
                10 10
                ##########
                #    1 I #
                #        #
                #    I   #
                #XXXXXXXX#
                #@       #
                #XXXXXXXX#
                #        #
                #1      $#
                ##########
                """;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        Main.run(new Scanner(System.in));
        assertEquals("""
                LOOP
                """, outputStreamCaptor.toString());
    }

    @Test
    void test16() {
        String simulatedInput = """
                10 10
                ##########
                #    1 I #
                #        #
                # ########
                #XXXXXXXX#
                #@      B#
                #XXXXXXXX#
                #       2#
                #12     $#
                ##########
                """;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        Main.run(new Scanner(System.in));
        assertEquals("""
                EAST
                EAST
                EAST
                EAST
                EAST
                EAST
                EAST
                NORTH
                WEST
                WEST
                WEST
                WEST
                WEST
                WEST
                WEST
                NORTH
                NORTH
                NORTH
                EAST
                EAST
                EAST
                EAST
                EAST
                NORTH
                WEST
                WEST
                WEST
                WEST
                WEST
                WEST
                WEST
                SOUTH
                EAST
                EAST
                EAST
                EAST
                EAST
                EAST
                SOUTH
                EAST
                """, outputStreamCaptor.toString());
    }

    @Test
    void test17() {
        String simulatedInput = """
                10 10
                ##########
                #    1 I #
                #        #
                # ########
                #XXXXXXXX#
                #@      B#
                #XXXXXXX##
                #       2#
                #12     $#
                ##########
                """;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        Main.run(new Scanner(System.in));
        assertEquals("""
                LOOP
                """, outputStreamCaptor.toString());
    }

    @Test
    void test18() {
        String simulatedInput = """
                10 10
                ##########
                #    1   #
                #        #
                # ########
                #XXXXXXXX#
                #@      B#
                #XXXXXX3##
                #       2#
                #12    3$#
                ##########
                """;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        Main.run(new Scanner(System.in));
        assertEquals("""
                EAST
                EAST
                EAST
                EAST
                EAST
                EAST
                EAST
                NORTH
                WEST
                WEST
                WEST
                WEST
                WEST
                WEST
                WEST
                NORTH
                NORTH
                NORTH
                EAST
                EAST
                EAST
                EAST
                EAST
                WEST
                WEST
                WEST
                WEST
                WEST
                WEST
                WEST
                NORTH
                EAST
                EAST
                EAST
                EAST
                EAST
                EAST
                EAST
                """, outputStreamCaptor.toString());
    }

    @Test
    void test19() {
        String simulatedInput = """
                10 10
                ##########
                #        #
                #        #
                #       $#
                #   @    #
                #        #
                #   S   W#
                #        #
                #   E   N#
                ##########
                """;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        Main.run(new Scanner(System.in));
        assertEquals("""
                LOOP
                """, outputStreamCaptor.toString());
    }

    @Test
    void test20() {
        String simulatedInput = """
                10 10
                ##########
                #        #
                #        #
                #       $#
                #   @    #
                #        #
                #   S   W#
                #        #
                #   E I N#
                ##########
                """;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        Main.run(new Scanner(System.in));
        assertEquals("""
                SOUTH
                SOUTH
                SOUTH
                SOUTH
                EAST
                EAST
                EAST
                EAST
                NORTH
                NORTH
                WEST
                WEST
                WEST
                NORTH
                NORTH
                NORTH
                NORTH
                NORTH
                EAST
                EAST
                EAST
                SOUTH
                SOUTH
                """, outputStreamCaptor.toString());
    }
}