package shared.objects;

public class LabelFormat implements Size {
    private String label;

    public LabelFormat(String label) {
        this.label = label;
    }

    public String toString() {
        return getSize();
    }

    public boolean equals(Object obj) {
        if(!(obj instanceof LabelFormat labelFormat))
            return false;

        return labelFormat.label.equals(label);
    }

    public LabelFormat copy() {
        return new LabelFormat(label);
    }

    @Override
    public String getSize() {
        return label;
    }
}
