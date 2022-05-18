package shared.objects.product;

import java.io.Serializable;

public interface Size extends Serializable
{
    String getSize();
    String getSizeWithoutUnit();
}
