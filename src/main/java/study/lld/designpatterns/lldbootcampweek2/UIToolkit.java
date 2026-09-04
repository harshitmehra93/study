package study.lld.designpatterns.lldbootcampweek2;

// Abstract Factory
public class UIToolkit {
    public static void main(String[] args) {
        ToolkitFactory factory = WebToolkitFactory.getInstance();
        Button button = factory.createButton();
        TextField textField = factory.createTestField();
        button.click();
        textField.write();

        factory = AndroidToolkitFactory.getInstance();
        button = factory.createButton();
        textField = factory.createTestField();
        button.click();
        textField.write();

        factory = DesktopToolkitFactory.getInstance();
        button = factory.createButton();
        textField = factory.createTestField();
        button.click();
        textField.write();
    }
}

interface ToolkitFactory {

    Button createButton();

    TextField createTestField();
}

class WebToolkitFactory implements ToolkitFactory {

    public static WebToolkitFactory INSTANCE = new WebToolkitFactory();

    private WebToolkitFactory() {}

    static ToolkitFactory getInstance() {
        return INSTANCE;
    }

    @Override
    public Button createButton() {
        return new WebButton();
    }

    @Override
    public TextField createTestField() {
        return new WebTextField();
    }
}

class AndroidToolkitFactory implements ToolkitFactory {

    public static AndroidToolkitFactory INSTANCE = new AndroidToolkitFactory();

    private AndroidToolkitFactory() {}

    static ToolkitFactory getInstance() {
        return INSTANCE;
    }

    @Override
    public Button createButton() {
        return new AndroidButton();
    }

    @Override
    public TextField createTestField() {
        return new AndroidTextField();
    }
}

class DesktopToolkitFactory implements ToolkitFactory {

    public static DesktopToolkitFactory INSTANCE = new DesktopToolkitFactory();

    private DesktopToolkitFactory() {}

    static ToolkitFactory getInstance() {
        return INSTANCE;
    }

    @Override
    public Button createButton() {
        return new DesktopButton();
    }

    @Override
    public TextField createTestField() {
        return new DesktopTextField();
    }
}

interface Button {
    void click();
}

class WebButton implements Button {

    @Override
    public void click() {
        System.out.println("Clinking Button from Web");
    }
}

class AndroidButton implements Button {

    @Override
    public void click() {
        System.out.println("Clinking Button from Android");
    }
}

class DesktopButton implements Button {

    @Override
    public void click() {
        System.out.println("Clinking Button from Desktop");
    }
}

interface TextField {
    void write();
}

class WebTextField implements TextField {

    @Override
    public void write() {
        System.out.println("TextField for Web");
    }
}

class AndroidTextField implements TextField {

    @Override
    public void write() {
        System.out.println("TextField for Android");
    }
}

class DesktopTextField implements TextField {

    @Override
    public void write() {
        System.out.println("TextField for Desktop");
    }
}
