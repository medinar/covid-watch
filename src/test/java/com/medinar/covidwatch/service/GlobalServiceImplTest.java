package com.medinar.covidwatch.service;

import com.medinar.covidwatch.client.GlobalClientImpl;
import com.medinar.covidwatch.domain.GlobalTotal;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
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
public class GlobalServiceImplTest {
    
    @Mock
    GlobalClientImpl mockClient;
    
    @InjectMocks
    GlobalServiceImpl service;
    
    public GlobalServiceImplTest() {
    }

    /**
     * Test of getTotal method, of class GlobalServiceImpl.
     * @throws java.lang.Exception
     */
    @Test
    public void testGetGlobalTotal() throws Exception {
        System.out.println("getTotal");
        
        GlobalTotal mockGlobalTotal = new GlobalTotal();
        mockGlobalTotal.setCases(100);
        mockGlobalTotal.setActive(9);
        
        when(mockClient.getGlobalTotal(false, false, false))
                .thenReturn(mockGlobalTotal);
                
        GlobalTotal result = service.getTotal(false, false, false);
        
        assertNotNull(result);
        assertEquals(100, result.getCases());
        assertEquals(9, result.getActive());

    }
    
}
