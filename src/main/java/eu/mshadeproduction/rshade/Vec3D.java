package eu.mshadeproduction.rshade;

public class Vec3D {

    public final double x;
    public final double y;
    public final double z;

    public Vec3D(double x, double y, double z) {
        if (x == -0.0D) {
            x = 0.0D;
        }

        if (y == -0.0D) {
            y = 0.0D;
        }

        if (z == -0.0D) {
            z = 0.0D;
        }

        this.x = x;
        this.y = y;
        this.z = z;
    }

}
