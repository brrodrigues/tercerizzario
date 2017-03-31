/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tercerizzario.service.supplier.filter;

import java.util.Map;
import org.springframework.cloud.netflix.zuul.ZuulFilterInitializer;

/**
 *
 * @author bruno.rodrigues
 */
public class SupplierProxyFilter extends ZuulFilterInitializer{
    
    public SupplierProxyFilter(Map<String, com.netflix.zuul.ZuulFilter> filters) {
        super(filters);
    }
    
}
