package svm;

import java.util.HashMap;
import java.util.Map;

class Bytecode {
    public static final int NUL = 0;
    public static final int HALT = 255;

    public static final int CONST = 1;
    public static final int POP = 2;
    public static final int LDC = 3;

    public static final int ADD = 30;
    public static final int SUB = 31;
    public static final int MUL = 32;
    public static final int DIV = 33;
    public static final int MOD = 34;

    public static final int GSTORE = 60;
    public static final int GLOAD = 61;
}

public class Main {

    Map<Integer, Object> constantPool = new HashMap<>();

    int[] memory = new int[100];

    Object[] stack = new Object[100];
    int sp = -1;

    public void push(Object it) { stack[++sp] = it; }
    public Object pop() { return stack[sp--]; }

    int ip = -1;

    public void execute(int[] code) {
        for (ip = 0; ip < code.length; ip++)
        {
            int opcode = code[ip];
            switch (opcode) {
                case Bytecode.NUL:
                    break;

                case Bytecode.HALT:
                    System.exit(0);
                    break;

                case Bytecode.CONST:
                    Object toBePushed = code[++ip];
                    push(toBePushed);
                    break;

                case Bytecode.LDC:
                    int index = code[++ip];
                    push(constantPool.get(index));
                    break;

                case Bytecode.POP:
                    pop();
                    break;

                case Bytecode.ADD:
                case Bytecode.SUB:
                case Bytecode.MUL:
                case Bytecode.DIV:
                case Bytecode.MOD:
                {
                    int rhs = (int)pop();
                    int lhs = (int)pop();
                    int result = 0;
                    switch (opcode) {
                        case Bytecode.ADD: result = lhs + rhs; break;
                        case Bytecode.SUB: result = lhs - rhs; break;
                        case Bytecode.MUL: result = lhs * rhs; break;
                        case Bytecode.DIV: result = lhs / rhs; break;
                        case Bytecode.MOD: result = lhs % rhs; break;
                    }
                    push(result);
                    break;
                }

                case Bytecode.GSTORE: {
                    int address = code[++ip];
                    int value = (int)pop();
                    memory[address] = value;
                    break;
                }
                case Bytecode.GLOAD: {
                    int address = code[++ip];
                    push(memory[address]);
                    break;
                }
                default:
                    System.err.println("Unrecognized opcode: " + opcode);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Simple JAXMainz VM v0.1");
        Main app = new Main();

        int[] code1 = { Bytecode.NUL };
        //app.execute(code1);

        app.constantPool.put(0, 5);
        int[] code2 = {
        //        Bytecode.CONST, 5,
                Bytecode.LDC, 0
        };
        app.execute(code2);
        Integer shouldBe5 = (Integer)app.pop();
        System.out.println(shouldBe5);

        int[] code3 = {
                Bytecode.CONST, 5, // lhs
                Bytecode.GSTORE, 0, // "a" == memory[0]
                Bytecode.CONST, 7, // rhs
                Bytecode.GSTORE, 1, // "b" == memory[1]

                Bytecode.GLOAD, 0,
                Bytecode.GLOAD, 1,

                Bytecode.SUB
        };
        app.execute(code3);
        Integer shouldBe2 = (Integer)app.pop();
        System.out.println(shouldBe2);
    }
}
