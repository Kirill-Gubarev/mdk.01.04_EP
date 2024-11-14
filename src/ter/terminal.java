package ter;

public class terminal {
	static {
        System.loadLibrary("terminal");
    }

    public native static void init();
    public native static void setInstantInputMode(boolean enable);
    public native static int instantGetChar();
}
