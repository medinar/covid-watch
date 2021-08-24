package com.medinar.covidwatch.service;

import com.medinar.covidwatch.client.ContinentalClient;
import com.medinar.covidwatch.domain.ContinentalTotal;
import com.medinar.covidwatch.exception.ContinentalCasesNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.ExpectedException;
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
public class ContinentalServiceImplTest {

    @Mock
    ContinentalClient mockClient;

    @InjectMocks
    ContinentalServiceImpl service;

    public ContinentalServiceImplTest() {
    }

    /**
     * Test of getTotal method, of class ContinentalServiceImpl.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetContinentalTotalHappyPath() throws Exception {
        System.out.println("getTotal");
        String continent = "Africa";
        int cases = 98;

        ContinentalTotal total = new ContinentalTotal();
        total.setContinent(continent);
        total.setCases(cases);

        Optional<ContinentalTotal> mockTotal = Optional.of(total);

        when(mockClient.getContinentalTotal(continent, false, false, true, false))
                .thenReturn(mockTotal);

        Optional<ContinentalTotal> result = service.getTotal(continent, false, false, true, false);

        assertTrue(result.isPresent());
        assertEquals(continent, result.get().getContinent());
        assertEquals(cases, result.get().getCases());

    }

    /**
     * Test of getTotal method, of class ContinentalServiceImpl.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetContinentalTotalUnhappyPath() throws Exception {
        System.out.println("getContinentalTotal");
        String continent = "Pangea";
        String expectedMessage = "Continent not found: " + continent;

        Exception exception = assertThrows(ContinentalCasesNotFoundException.class, () -> {
            Optional<ContinentalTotal> result = service
                    .getTotal(continent, false, false, true, false);

        });

        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Test of getTotals method, of class ContinentalServiceImpl.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testGetContinentalTotalsHappyPath() throws Exception {
        System.out.println("getTotals");
        String continent = "Africa";
        int cases1 = 48;
        int cases2 = 98;
        ContinentalTotal mock1 = new ContinentalTotal();
        mock1.setContinent(continent);
        mock1.setCases(cases1);
        ContinentalTotal mock2 = new ContinentalTotal();
        mock2.setContinent(continent);
        mock2.setCases(cases2);

        List<ContinentalTotal> mockList = new ArrayList<>();
        mockList.add(mock1);
        mockList.add(mock2);

        when(mockClient.getContinentalTotals(false, false, true, false))
                .thenReturn(mockList);

        List<ContinentalTotal> result = service.getTotals(
                false, false, true, false
        );

        assertNotNull(result);
        assertEquals(continent, result.get(0).getContinent());
        assertEquals(cases1, result.get(0).getCases());
        assertEquals(continent, result.get(1).getContinent());
        assertEquals(cases2, result.get(1).getCases());

    }

}
