package ru.khrip.laba5;

public class SomeBean{
    @AutoInjectable
    private SomeInterface field1;
    @AutoInjectable
    private SomeOtherInterface field2;
    public String foo(){
        return field1.doSomething() + field2.doSomeOther();
    }
}
