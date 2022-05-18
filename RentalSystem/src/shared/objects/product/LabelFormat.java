package shared.objects.product;

/**
 * LabelFormat defines size by labels, example: XL, L, M, S
 */
public class LabelFormat implements Size {
    private String label;

    /**
     * Constructor
     * @param label Defines label, values may be 3XL, 2XL, XL, L, M, S.
     */
    public LabelFormat(String label) {
        this.label = label;
    }

    /**
     * Converts LabelFormat to string.
     * @return Returns LabelFormat in string format.
     */
    public String toString() {
        return getSize();
    }

    /**
     * Equals method.
     * @param obj Object we are checking if the LabelFormat is equal to.
     * @return Returns true if the passed object equals this class, returns false if not.
     */
    public boolean equals(Object obj) {
        if(!(obj instanceof LabelFormat labelFormat))
            return false;

        return labelFormat.label.equals(label);
    }

    /**
     * Copy method.
     * @return Returns exact copy of LabelFormat.
     */
    public LabelFormat copy() {
        return new LabelFormat(label);
    }

    /**
     * Returns label String.
     * @return Returns Label String.
     */
    @Override
    public String getSize() {
        return label;
    }

    @Override
    public String getSizeWithoutUnit() {
        return label;
    }
}
