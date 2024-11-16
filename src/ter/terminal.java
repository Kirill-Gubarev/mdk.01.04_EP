package ter;

public class terminal {
	private terminal(){}
	static {
        System.loadLibrary("terminal");
    }

    public native static void init();
    public native static void terminate();

	public native static void setAltBuf(boolean enable);
    public native static void setInstantInputMode(boolean enable);
    public native static int instantGetChar();

	public static void setCursorPos(Point p){
		setCursorPos(p.x, p.y);
	}
	public native static void setCursorPos(int x, int y);
	public native static Point getCursorPos();
	public native static void setCursorVisibility(boolean enable);

	public static void hExit(){
		terminate();
		System.exit(0);
	}
}
