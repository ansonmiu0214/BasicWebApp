package com.develogical;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class QueryProcessorTest {

    QueryProcessor queryProcessor = new QueryProcessor();

    @Test
    public void returnsEmptyStringIfCannotProcessQuery() throws Exception {
        assertThat(queryProcessor.process("test"), is(""));
    }

    @Test
    public void knowsAboutShakespeare() throws Exception {
        assertThat(queryProcessor.process("Shakespeare"), containsString("playwright"));
    }

    @Test
    public void isNotCaseSensitive() throws Exception {
        assertThat(queryProcessor.process("shakespeare"), containsString("playwright"));
    }

    @Test
    public void additionQuery() throws Exception {
        assertThat(queryProcessor.process("hash: what is 18 plus 8"), is("26"));
    }

    @Test
    public void minusQuery() throws Exception {
        assertThat(queryProcessor.process("hash: what is 18 minus 8"), is("10"));
    }

    @Test
    public void multiplyQuery() throws Exception {
        assertThat(queryProcessor.process("hash: what is 18 multiplied by 8"), is("144"));
    }

    @Test
    public void compoundMathQuery() throws Exception {
        assertThat(queryProcessor.process("hash: what is 18 minus 8 plus 3"), is("13"));
    }

    @Test
    public void largestQuery() throws Exception {
        assertThat(queryProcessor.process("which of the following numbers is the largest: 140, 39"), containsString("140"));
    }

    @Test
    public void badLargestQuery() throws Exception {
        assertThat(queryProcessor.process("which of the following numbers is the largest:totallynota,badstringlmao"), is(""));
    }

    @Test
    public void fibonacci() throws Exception {
        assertThat(queryProcessor.process("what is the 8th number in the Fibonacci sequence"), containsString("21"));
    }

    @Test
    public void fibonacci2() throws Exception {
        assertThat(queryProcessor.process("what is the 6th number in the Fibonacci sequence"), is("8"));
    }

    @Test
    public void primeQuery() throws Exception {
        assertThat(queryProcessor.process("which of the following numbers are primes: 2, 3, 4"), containsString("2, 3"));
    }

    @Test
    public void theresaMayQuery() throws Exception {
        assertThat(queryProcessor.process("which year was Theresa May first elected as the Prime Minister of Great Britain"), is("2016"));
    }

    @Test
    public void eiffelTowerQuery() throws Exception {
        assertThat(queryProcessor.process("which city is the Eiffel tower in"), is("Paris"));
    }

    @Test
    public void squareAndCubeQuery() throws Exception {
        assertThat(queryProcessor.process("which of the following numbers is both a square and a cube: 893, 875, 1, 169"), is("1"));
    }

    @Test
    public void powerQuery() throws Exception {
        assertThat(queryProcessor.process("what is 19 to the power of 14"), is("7.990066857828841E17"));
    }
}
