package com.leadspace.mockito;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class MathTest {

    @Mock
    Math math; //object to mock

    @Test
    public void add2numbers() {
        assertSame(4,math.add2numbers(2,2));
    }

    @Before
    public void create () {
        initMocks(this);
        when(math.add2numbers(1,2)).thenReturn(3);
        when(math.add2numbers(2,2)).thenReturn(4);
    }

}