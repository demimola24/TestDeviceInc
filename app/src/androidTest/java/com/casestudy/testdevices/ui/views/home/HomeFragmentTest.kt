package com.casestudy.testdevices.ui.views.home

import androidx.lifecycle.MutableLiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.casestudy.data.model.MyDevice
import com.casestudy.domain.DataOutput
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.contrib.RecyclerViewActions



/**
 * Created by Demimola on 1/30/22.
 */

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class HomeFragmentTest {

    @BindValue
    @JvmField
    val viewModel = mockk<HomeViewModel>(relaxed = true)

    private val devices = MutableLiveData<DataOutput<List<MyDevice>>>()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        every { viewModel.devices } returns devices
    }


    @Test
    fun test_item_shown_in_recycle_view() {

        TODO("Fix build errors because Mockito and Mockk dependencies are present in the project.")

//        val scenario = launchFragmentInContainer<HomeFragment>()
//
//        val item  = MyDevice(title = "TestTitle", description = "TestDest",currency = "NGN", price = 0,imageUrl = "", isFavorite = false,id = "", type = "Sensor" )
//        devices.postValue(DataOutput.success("Success", mutableListOf(item)))


//        onView(withId(R.id.recyclerView))
//            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
//            .check(matches(hasDescendant(withText("TestTitle"))))


    }




}