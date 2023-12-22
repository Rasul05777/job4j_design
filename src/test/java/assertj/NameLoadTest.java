package assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("no data");
    }
    @Test
    void checkNoName() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Name is empty");
    }

    @Test
    void checkMissingEqualsSymbol() {
        NameLoad nameLoad = new NameLoad();
        String invalidName = "=value";
        assertThatThrownBy(() -> nameLoad.parse("=value"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(invalidName + " this name: %s does not contain the symbol ' = '");
    }
    @Test
    void checkMissingSymbolInKey() {
        NameLoad nameLoad = new NameLoad();
        String invalidName = "key=";
        assertThatThrownBy(() -> nameLoad.parse("key="))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(invalidName +  " this name: %s does not contain a key ' = '");
    }
    @Test
    void checkMissingSymbolInValue() {
        NameLoad nameLoad = new NameLoad();
        String invalidName = "value=";
        assertThatThrownBy(() -> nameLoad.parse("value="))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("this name: %s does not contain a value ' = '");

    }

}