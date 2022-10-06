package com.example.demo

import com.ninjasquad.springmockk.SpykBean
import io.mockk.confirmVerified
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
internal class CustomControllerSpyTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @SpykBean
    private lateinit var service: CustomService

    @Test
    internal fun `testing customs`() {

        mockMvc.perform(get("/customs")).andExpect(status().isAccepted)

        verify { service.doWork() }
        confirmVerified(service)
    }
}
