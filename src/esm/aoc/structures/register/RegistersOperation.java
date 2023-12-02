package esm.aoc.structures.register;

import java.util.function.Function;

public class RegistersOperation {

    private final String register;
    private final Function<Integer, Integer> operation;

    public RegistersOperation(final String register, final Function<Integer, Integer> operation) {
        this.register = register;
        this.operation = operation;
    }

    public String getRegister() {
        return register;
    }

    public Function<Integer, Integer> getOperation() {
        return operation;
    }

}
