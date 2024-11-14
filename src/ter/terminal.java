package ter;

public class terminal {
	static {
        System.loadLibrary("terminal");
    }

    public native static void init();
    public native static void terminate();
    public native static void setInstantInputMode(boolean enable);
    public native static int instantGetChar();

	public native static void setCursorPos(int x, int y);
	public native static void setCursorVisibility(boolean enable);
	public native static void setAltBuf(boolean enable);
}
