package config;

import java.nio.file.Path;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

public final class ImageConfig {

    public enum ShapeKind {
        TRIANGLE, RECTANGLE, CIRCLE
    }

    public enum FitnessMetric {MSE, RMSE, DELTA_E}

    private final Path targetPath;
    private final int width;
    private final int height;
    private final int backgroundColor;       // packed ARGB, e.g. 0xFF000000
    private final int initialShapeCount;
    private final Set<ShapeKind> shapeKinds;
    private final double minAlpha;
    private final double maxAlpha;
    private final boolean allowOverflow;     // shapes may extend past the canvas
    private final boolean antialiasing;
    //private final FitnessMetric fitnessMetric;
    //private final Path resultsDir;
    private final int saveEveryNGenerations;

    private ImageConfig(Builder b) {
        this.targetPath = Objects.requireNonNull(b.targetPath, "Target path must but non-empty");
        if (b.width <= 0 || b.height <= 0) {
            throw new IllegalArgumentException(
                    "Width and height must both be positive" + "Width:" + b.width + " Height:" + b.height
            );
        }

        // --- assign ---
        this.width = b.width;
        this.height = b.height;
        this.backgroundColor = b.backgroundColor;
        this.initialShapeCount = b.initialShapeCount;
        this.shapeKinds = Collections.unmodifiableSet(EnumSet.copyOf(b.shapeKinds));
        this.minAlpha = b.minAlpha;
        this.maxAlpha = b.maxAlpha;
        this.allowOverflow = b.allowOverflow;
        this.antialiasing = b.antialiasing;
        this.saveEveryNGenerations = b.saveEveryNGenerations;
    }

    public Path targetPath() {
        return targetPath;
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }

    public int backgroundColor() {
        return backgroundColor;
    }

    public int initialShapeCount() {
        return initialShapeCount;
    }

    public Set<ShapeKind> shapeKinds() {
        return shapeKinds;
    }

    public double minAlpha() {
        return minAlpha;
    }

    public double maxAlpha() {
        return maxAlpha;
    }

    public boolean allowOverflow() {
        return allowOverflow;
    }

    public boolean antialiasing() {
        return antialiasing;
    }

/*    public FitnessMetric fitnessMetric() {
        return fitnessMetric;
    }

    public Path resultsDir() {
        return resultsDir;
    }*/

    public int saveEveryNGenerations() {
        return saveEveryNGenerations;
    }

    public static Builder builder() {
        return new Builder();
    }


    //getters
    public static final class Builder {
        // no default for "must-decide" fields; defaults only for obviously-sensible booleans/ranges
        private Path targetPath;
        private int width = -1, height = -1;
        private int backgroundColor = 0xFF000000;
        private int initialShapeCount = -1;
        private Set<ShapeKind> shapeKinds;
        private double minAlpha = 0.0, maxAlpha = 1.0;
        private boolean allowOverflow = true;
        private boolean antialiasing = true;
        //private FitnessMetric fitnessMetric = FitnessMetric.MSE;
        private Path resultsDir;
        private int saveEveryNGenerations = 50;

        public Builder targetPath(Path p) {
            this.targetPath = p;
            return this;
        }

        public Builder width(int w) {
            this.width = w;
            return this;
        }

        public Builder height(int h) {
            this.height = h;
            return this;
        }

        public Builder initialShapeCount(int n) {
            this.initialShapeCount = n;
            return this;
        }

        public Builder shapeKinds(Set<ShapeKind> s) {
            this.shapeKinds = s;
            return this;
        }

        private double minAlpha(double a) {
            this.minAlpha = a;
            return minAlpha;
        }

        private double maxAlpha(double a) {
            this.maxAlpha = a;
            return maxAlpha;
        }

        public Builder resultsDir(Path p) {
            this.resultsDir = p;
            return this;
        }

        public Builder saveEveryNGenerations(int n) {
            this.saveEveryNGenerations = n;
            return this;
        }

        public Builder allowOverflow(boolean v) {
            this.allowOverflow = v;
            return this;
        }

        public Builder antialiasing(boolean v) {
            this.antialiasing = v;
            return this;
        }

   /*     public Builder fitnessMetric(FitnessMetric m) {
            this.fitnessMetric = m;
            return this;
        }*/

        public ImageConfig build() {
            return new ImageConfig(this);
        }
    }
}
