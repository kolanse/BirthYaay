package com.example.birthyaay

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class Checking {

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("com.example.birthyaay", appContext.packageName)
    }


    @Test
    fun addition_isSecondCorrect() {
        Assert.assertEquals(5, 2 + 2)
    }
}