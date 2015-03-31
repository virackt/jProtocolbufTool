package me.veryvic.game.tool;

import me.veryvic.game.tool.util.AppConfig;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by vic on 15-3-26.
 */
public class TestAppConfig {

    @Test
    public void test(){
        Assert.assertEquals(AppConfig.getIntValue("startIndex"), 1000);
    }
}
