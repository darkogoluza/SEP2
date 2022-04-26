package shared.objects;

public class MetricFormat implements Size {
    private double width;
    private double height;

    public MetricFormat(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public String toString() {
        return getSize();
    }

    public boolean equals(Object obj) {
        if(!(obj instanceof MetricFormat metricFormat))
            return false;

        return metricFormat.width == width && metricFormat.height == height;
    }

    public MetricFormat copy() {
        return new MetricFormat(width, height);
    }

    @Override
    public String getSize() {
        return String.format("%fcm x %fcm", width, height);
    }
}
