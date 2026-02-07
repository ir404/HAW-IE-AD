package taskTwo;

public class S extends F implements D {
    private G g;        // composite
    private int d;

    public S(A e) {
        super();
        if (e instanceof G) {
            g = (G) e;
        }
    }

    @Override
    public int b() {
        return 0;
    }

    @Override
    public void v() { }
}
