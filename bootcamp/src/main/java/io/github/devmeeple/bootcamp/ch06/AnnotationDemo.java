package io.github.devmeeple.bootcamp.ch06;

class Demo {

    public static void main(String[] args) {
        Parent parent = new Child();
        parent.method();
    }
}

class Parent {

    public void method() {
        System.out.println("parent");
    }
}

class Child extends Parent {

    @Override
    public void method() {
        System.out.println("child");
    }
}
