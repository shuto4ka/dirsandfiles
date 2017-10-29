package testtask.dirsandfiles.repository;

import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

public class SimpleSqlConditionBuilder {
    private StringBuilder condition = new StringBuilder();
    private List<Object> tempArgs = new ArrayList<>();
    private List<Object> args;
    private boolean prevConditionAdded = false;

    public SimpleSqlConditionBuilder(List<Object> args) {
        Assert.notNull(args, "Array of arguments must not be null");
        Assert.isTrue(args.size() == 0, "Array of arguments must be empty");
        this.args = args;
    }

    public SimpleSqlConditionBuilder addCondition(String column, ComparingType type, Object newArg) {
        if (newArg != null) {
            prevConditionAdded = true;
            if (tempArgs.size() == 0) condition.append("WHERE ");

            condition.append(String.format("%s%s? AND ", column, type.value));
            tempArgs.add((type == ComparingType.ILIKE) ? "%" + newArg + "%" : newArg);
        }
        return this;
    }

    public SimpleSqlConditionBuilder and() {
        if (prevConditionAdded) {
            prevConditionAdded = false;
            condition.append("AND ");
        }
        return this;
    }

    public String build() {
        args.addAll(tempArgs);
        return condition.toString();
    }

    public enum ComparingType {
        EQUAL("="),
        MORE_OR_EQUAL(">="),
        LESS("<"),
        ILIKE(" ILIKE ");

        private String value;

        ComparingType(String value) {
            this.value = value;
        }
    }
}
