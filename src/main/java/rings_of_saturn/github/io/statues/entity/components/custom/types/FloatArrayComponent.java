package rings_of_saturn.github.io.statues.entity.components.custom.types;

import org.ladysnake.cca.api.v3.component.Component;

public interface FloatArrayComponent extends Component {
    float[] getValue();
    void set(float[] amount);
}
