package com.example.wizytydomowe.Doctor;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import com.example.wizytydomowe.Appointment.Importance;
import com.example.wizytydomowe.HereApi.DistanceService;
import com.example.wizytydomowe.HereApi.PriceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

class AppointmentDetailsControllerTest {

    private MockMvc mockMvc;

    @Mock
    private DoctorService doctorService;

    @Mock
    private DistanceService distanceService;

    @Mock
    private PriceService priceService;

    @InjectMocks
    private AppointmentDetailsController appointmentDetailsController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = standaloneSetup(appointmentDetailsController).build();
    }

    @Test
    public void testFindAvailableDoctors() throws Exception {
        LocationDto locationDto = new LocationDto(20.0, 10.0, Importance.MEDIUM, "Cardiology");
        List<DoctorDto> doctors = Arrays.asList(new DoctorDto(1L, "Jacob", "Smith",
                "110430943","Cardiology", "kubah2012@gmail.com", 10.0, 20.0));
        given(doctorService.findBySpecialization("Cardiology")).willReturn(doctors);
        given(distanceService.calculateDistance(any(DoctorDto.class), eq(10.0), eq(20.0))).willReturn(30.0);
        given(priceService.calculateVisitPrice(any(DoctorDto.class), eq(10.0), eq(20.0))).willReturn(100.0);

        List<DoctorWithPriceDto> expectedDoctorsWithPrices = Arrays.asList(new DoctorWithPriceDto(new DoctorDto(1L,
                "Jacob", "Smith",
                "110430943", "Cardiology", "kubah2012@gmail.com", 10.0, 20.0),
                100.0));
        ObjectMapper objectMapper = new ObjectMapper();
        String locationDtoJson = objectMapper.writeValueAsString(locationDto);

        mockMvc.perform(post("/location")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(locationDtoJson))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedDoctorsWithPrices)));
    }
}