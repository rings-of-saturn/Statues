package rings_of_saturn.github.io.fragments.entity.components.custom.types;

import org.ladysnake.cca.api.v3.component.Component;

public interface BooleanComponent extends Component {

    boolean getValue();
    void set(boolean newValue);

}
