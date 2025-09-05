package rings_of_saturn.github.io.statues.entity.components.custom.types;

import org.ladysnake.cca.api.v3.component.Component;

public interface IntArrayComponent extends Component {
    int[] getValue();
    void set(int[] amount);
}
