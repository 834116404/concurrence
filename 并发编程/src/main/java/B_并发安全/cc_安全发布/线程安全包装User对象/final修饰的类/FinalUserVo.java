package B_并发安全.cc_安全发布.线程安全包装User对象.final修饰的类;

/**
 * 类说明：
 * 如果是final无法继承的话， 那么久无法包装User对象了。
 *
 */
public final class FinalUserVo {
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
