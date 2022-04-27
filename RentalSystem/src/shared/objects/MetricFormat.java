package shared.objects;

/**
 * MetricFormat defines size by width and height.
 */
public class MetricFormat implements Size {
    private double width;
    private double height;

    /**
     * Constructor
     * @param width Defines width of the item.
     * @param height Defines height of the item.
     */
    public MetricFormat(double width, double height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Getter for width
     * @return Returns width.
     */
    public double getWidth() {
        return width;
    }

    /**
     * Getter for height.
     * @return Returns height.
     */
    public double getHeight() {
        return height;
    }

    /**
     * Converts MetricFormat to string.
     * @return Returns MetricFormat in string format.
     */
    public String toString() {
        return getSize();
    }

    /**
     * Equals method.
     * @param obj Object we are checking if the MetricFormat is equal to.
     * @return Returns true if the passed object equals this class, returns false if not.
     */
    public boolean equals(Object obj) {
        if(!(obj instanceof MetricFormat metricFormat))
            return false;

        return metricFormat.width == width && metricFormat.height == height;
    }

    /**
     * Copy method.
     * @return Returns exact copy of MetricFormat.
     */
    public MetricFormat copy() {
        return new MetricFormat(width, height);
    }

    /**
     * Returns width and height in string format, format is "(width)cm x (height)cm".
     * @return Returns width and height in a string.
     */
    @Override
    public String getSize() {
        return String.format("%fcm x %fcm", width, height);
    }
}
