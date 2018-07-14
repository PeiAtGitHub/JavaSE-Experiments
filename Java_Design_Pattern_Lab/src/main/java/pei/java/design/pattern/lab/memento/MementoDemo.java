package pei.java.design.pattern.lab.memento;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author pei
 */
public class MementoDemo {

    @Test
    public void testName() throws Exception {

        List<Originator.Memento> savedStates = new ArrayList<Originator.Memento>();

        Originator originator = new Originator();

        originator.setState("S1");
        assertEquals("S1", originator.getState());

        originator.setState("S2");
        savedStates.add(originator.saveToMemento());
        assertEquals("S2", originator.getState());

        originator.setState("S3");
        savedStates.add(originator.saveToMemento());
        assertEquals("S3", originator.getState());

        originator.setState("S4");
        assertEquals("S4", originator.getState());

        originator.restoreFromMemento(savedStates.get(1));
        assertEquals("S3", originator.getState());

        originator.restoreFromMemento(savedStates.get(0));
        assertEquals("S2", originator.getState());
    }

}

@Setter
@Getter
class Originator {

    private String state;

    public Memento saveToMemento() {
        return new Memento(state);
    }

    public void restoreFromMemento(Memento memento) {
        state = memento.getState();
    }

    @AllArgsConstructor
    @Getter
    public static class Memento {
        private final String state;
    }
}
