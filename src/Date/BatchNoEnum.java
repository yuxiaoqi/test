package Date;

import lombok.Getter;

/**
 * Created by yangjun on 2016/4/6.
 */
public enum BatchNoEnum {
    A(1, "A"),
    B(2, "B"),
    C(3, "C"),
    D(4, "D"),
    E(5, "E"),
    F(6, "F"),
    G(7, "G"),
    H(8, "H"),
    I(9, "I"),
    J(10, "J"),
    K(11, "K"),
    L(12, "L");


    @Getter
    private int key;

    @Getter
    private String name;

    private BatchNoEnum(int key, String name) {
        this.key = key;
        this.name = name;
    }

    public static BatchNoEnum getEnum(int key) {
        for(BatchNoEnum item : BatchNoEnum.values()) {
            if(item.key == key) {
                return item;
            }
        }
        return null;
    }

    public static String getName(int key) {
        BatchNoEnum item = getEnum(key);
        return item != null ? item.name : null;
    }
}
