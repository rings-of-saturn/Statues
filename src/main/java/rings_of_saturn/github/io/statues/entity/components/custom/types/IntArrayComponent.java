package rings_of_saturn.github.io.statues.entity.components.custom.types;

import net.minecraft.util.math.Vec3i;
import org.ladysnake.cca.api.v3.component.Component;

public interface IntComponent extends Component {
    Vec3i getValue();
    void increment();
    void decrement();
    void decrement(int amount);
    void increment(int amount);
    void set(int amount);
}
