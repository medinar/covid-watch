package com.medinar.covidwatch.service;

import com.medinar.covidwatch.client.InternationalClientImpl;
import com.medinar.covidwatch.domain.InternationalTotal;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author Rommel Medina
 */
@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class InternationalServiceImplTest {

    @Mock
    InternationalClientImpl mockClient;

    @InjectMocks
    InternationalServiceImpl service;

    public InternationalServiceImplTest() {
    }

    /**
     * Test of getTotals method, of class InternationalServiceImpl.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetInternationalTotalsHappyPath() throws Exception {
        System.out.println("getTotals");
        String country1 = "Canada";
        String country2 = "USA";
        InternationalTotal mock1 = new InternationalTotal();
        mock1.setCountry(country1);
        InternationalTotal mock2 = new InternationalTotal();
        mock2.setCountry(country2);

        List<InternationalTotal> mockList = new ArrayList<>();
        mockList.add(mock1);
        mockList.add(mock2);

        when(mockClient.getInternationalTotals(false, false, "", false))
                .thenReturn(mockList);

        List<InternationalTotal> result = service.getTotals(false, false, "", false);

        assertNotNull(result);
        assertEquals(country1, result.get(0).getCountry());
        assertEquals(country2, result.get(1).getCountry());
    }

    /**
     * Test of getTotal method, of class InternationalServiceImpl.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetInternationalTotalHappyPath() throws Exception {
        System.out.println("getTotal");
        String country = "Canada";

        InternationalTotal mockInternationalTotal = new InternationalTotal();
        mockInternationalTotal.setCases(100);
        mockInternationalTotal.setCountry(country);

        when(mockClient.getInternationalTotal(country, true, true, true, true))
                .thenReturn(mockInternationalTotal);

        InternationalTotal result = service.getTotal(country, true, true, true, true);

        assertNotNull(result);
        assertEquals(country, result.getCountry());
    }

    /**
     * Test of getTotalsbyContinent method, of class InternationalServiceImpl.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetInternationalTotalsbyContinentHappyPath() throws Exception {
        System.out.println("getTotalsbyContinent");
        String continent = "Asia";
        String countryPh = "Philippines";
        String countryJp = "Japan";
        InternationalTotal mock1 = new InternationalTotal();
        mock1.setContinent(continent);
        mock1.setCountry(countryPh);
        InternationalTotal mock2 = new InternationalTotal();
        mock2.setContinent(continent);
        mock2.setCountry(countryJp);

        List<InternationalTotal> list = new ArrayList<>();
        list.add(mock1);
        list.add(mock2);

        when(mockClient.getInternationalTotalsbyContinent(
                continent,
                false,
                false,
                true,
                false
        )).thenReturn(list);

        List<InternationalTotal> result = service.getTotalsbyContinent(
                continent,
                false,
                false,
                true,
                false
        );

        assertNotNull(result);
        assertEquals(continent, result.get(0).getContinent());
        assertEquals(countryPh, result.get(0).getCountry());
        assertEquals(countryJp, result.get(1).getCountry());

    }

}
