package fi.solita.utils.functional;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;

import org.junit.Test;

import fi.solita.utils.functional.Pair;

public class PairTests {
    
    class NonSerializable {}

    @Test
    public void isMapEntry() throws Exception {
        Pair<Integer,String> pair = Pair.of(42, "foo");
        
        assertThat(pair, instanceOf(Map.Entry.class));
        assertThat(pair.left, equalTo(pair._1));
        assertThat(pair.right, equalTo(pair._2));
    }
    
    @Test
    public void nonSerializableInstantiation() {
        Pair<NonSerializable,String> pair = Pair.of(new NonSerializable(), "foo");
        assertThat(pair, not(instanceOf(Serializable.class)));
        
        assertEquals(pair.left, pair._1);
        assertEquals(pair.right, pair._2);
    }
    
    @Test
    public void serializableInstantiation() {
        Pair<Integer,String> pair = Pair.of(42, "foo");
        assertThat(pair, instanceOf(Serializable.class));
    }
    
    @Test
    public void serialization() throws Exception {
        Pair<Integer,String> pair = Pair.of(42, "foo");
        assertThat(pair, instanceOf(Serializable.class));
        
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bao);
        out.writeObject(pair);
        out.close();
        
        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bao.toByteArray()));
        Pair<?,?> deserializedPair = (Pair<?, ?>) in.readObject();
        in.close();
        
        assertThat(deserializedPair, instanceOf(Serializable.class));
        assertEquals(pair, deserializedPair);
        
        assertEquals(pair.left, deserializedPair.left);
        assertEquals(pair.right, deserializedPair.right);
        assertEquals(pair._1, deserializedPair._1);
        assertEquals(pair._2, deserializedPair._2);
    }
}
