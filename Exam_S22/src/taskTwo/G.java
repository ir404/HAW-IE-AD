package taskTwo;

public class G extends A  implements D {
    private S s;        // aggregate
    private double c;

    public G(int i) {
        super(i);
        s = new S(this);
    }

    @Override
    public int b() {
        return 0;
    }

    @Override
    public void v() { }
}
