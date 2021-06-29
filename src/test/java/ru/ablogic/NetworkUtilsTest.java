package ru.ablogic;

import org.junit.Test;


public class NetworkUtilsTest {
    @Test (timeout = 1000)
    public void getConnShouldReturnFasterTatOneSecondTimeout(){
        NetworkUtils.getConn();
    }

}
