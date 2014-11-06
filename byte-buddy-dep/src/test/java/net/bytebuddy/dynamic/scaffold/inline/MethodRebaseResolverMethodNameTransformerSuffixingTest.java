package net.bytebuddy.dynamic.scaffold.inline;

import net.bytebuddy.utility.ObjectPropertyAssertion;
import net.bytebuddy.utility.RandomString;
import org.junit.Test;

import java.util.Random;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

public class MethodRebaseResolverMethodNameTransformerSuffixingTest {

    private static final String FOO = "foo";

    @Test
    public void testTransformation() throws Exception {
        String transformed = new MethodRebaseResolver.MethodNameTransformer.Suffixing(new RandomString()).transform(FOO);
        assertThat(transformed, not(FOO));
        assertThat(transformed, startsWith(FOO));
    }

    @Test
    public void testObjectProperties() throws Exception {
        ObjectPropertyAssertion.of(MethodRebaseResolver.MethodNameTransformer.Suffixing.class).refine(new ObjectPropertyAssertion.Refinement<RandomString>() {
            @Override
            public void apply(RandomString mock) {
                when(mock.nextString()).thenReturn("" + new Random().nextInt());
            }
        }).apply();
    }
}
